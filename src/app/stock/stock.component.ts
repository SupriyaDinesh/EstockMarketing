import { Component, Inject, NgModule, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Stock } from './stock.model';
import { StockService } from './stock.service';
import { DatePipe, formatDate } from '@angular/common';
import { FormsModule }   from '@angular/forms';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit{
  stockObject:Stock = new Stock();
  stockArray:Array<Stock> = [];
  data:any= {};
  displayedColumns:string[]=["Stock Price","Date","Time"]
  companyCode:string | any;
  constructor(private http:HttpClient,private service:StockService){

  }
  ngOnInit(): void {
    this.companyCode = sessionStorage.getItem("companyCode");
    console.log(this.companyCode);
    this.findMinMaxAvgOfStockPrices(this.companyCode);
  }
  addFlag:boolean|any;
  successMesage:string="";
  addStock(){
    this.addFlag = false;
    this.service.addStockDetailsToCompany(this.stockObject,this.companyCode).subscribe(data=>
      {
        this.data = JSON.stringify(data);
        this.stockArray.push(data);
        setTimeout(function(){
          window.location.reload();
        },1500)
        this.addFlag = !this.addFlag;
        this.successMesage="Stock Price is Added Successfully!!"
          console.log(this.addFlag=" "+this.successMesage)

      },
      error=>{
        console.log("addStock()==>"+error);
      })
  }
  viewStockArray:Array<Stock> = [];
  formattedStartDate : Date|any;
  formattedEndDate:Date|any;
  datePipe = new DatePipe('en-US');
  showViewBetweenDatesTable: boolean = false;
  public dataSource:Array<Stock>=[];
  flag:boolean|any;
  noDataFoundMessage:string="";
  viewStockBetweenTheDates(startDate:Date,endDate:Date){
    this.flag=false;
    this.showViewBetweenDatesTable = !this.showViewBetweenDatesTable
    this.formattedStartDate =this.datePipe.transform(startDate, 'yyyy-MM-dd');
    this.formattedEndDate = this.datePipe.transform(endDate, 'yyyy-MM-dd');
    console.log(this.companyCode+"=="+this.formattedStartDate+"=="+this.formattedEndDate);
    this.service.getAllStcokDetailsBetweenTheDates(this.companyCode,this.formattedStartDate,this.formattedEndDate).subscribe(data=>{
      this.viewStockArray = Object.values(data);
      this.dataSource=this.viewStockArray;
      console.log(this.viewStockArray);
      console.log("Date is==>"+this.viewStockArray[0].timeStamp.split(" ")[0]);
      console.log("Time is==>"+this.viewStockArray[0].timeStamp.split(" ")[1].split(".")[0]);
    },
    error=>{
      console.log("viewStockBetweenTheDates()==>"+error);
      this.showViewBetweenDatesTable = false;
      this.flag=!this.flag;
      this.noDataFoundMessage="No Data Found between the given Dates!!Please enter the valid Dates"
    })
  }

  stockPriceArray:number[] = [];
  avg:any=0;
  min:any=0;
  max:any=0;
  total=0;
  findMinMaxAvgOfStockPrices(companyCode:string) {
  this.service.getAllStockPriceList(companyCode).subscribe(data=>{
    this.stockPriceArray = Object.values(data);
    console.log(this.stockPriceArray);
    this.min=Math.min(...this.stockPriceArray);
    this.max=Math.max(...this.stockPriceArray);
    for (var i in this.stockPriceArray) { 
      this.total += this.stockPriceArray[i]; 
    }
    this.avg = this.total/this.stockPriceArray.length;

  },
  error=>{
    console.log("findMinMaxAvgOfStockPrices()==>"+error);
  })
}
}