import { HttpClient } from '@angular/common/http';
import { Component, NgModule } from '@angular/core';
import { Company } from './company.model';
import { CompanyService } from './company.service';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})

export class CompanyComponent {

  companyObject:Company = new Company();
  companyArray:Array<Company> = [];
  data:any= {};
  stockType = new FormControl('');
  stockExchangeArray:String[]=["BSE","NSE"];
  displayedColumns: string[] = ['Company Code', 'Company Name', 'Company CEO', 'Company Turnover','Company Website','Current Stock Price','Stock Exchange','Delete/Remove'];
  public dataSource:Array<Company>=[];
  public dataSource1:Array<Company>= [];
  showViewAllTable: boolean = false;
  showViewByIdTable: boolean = false;
  constructor(private http:HttpClient,private service:CompanyService){}

  codeErrMessage:string|any;
  flag:boolean|any;
  checkIsCodeAlreadyExist(code:string){
    this.flag=false;
    console.log("Current Code is -->"+code)
    this.companyArray = this.viewAllCompany();
    console.log(this.companyArray.filter(c=>c.companyCode === code).length);
    if(this.companyArray.filter(c=>c.companyCode === code).length > 0){
      this.codeErrMessage = "This Company code is already exist!!, please enter unique code";
      this.flag=true;
    }
  }
  addFlag:boolean|any;
  successMesage:string="";
  addCompany(){
    this.service.addCompanyDetails(this.companyObject).subscribe(data=>
      {
        this.addFlag=false;
        console.log("Entered Company Details are:-->"+data.companyCode);
        this.data = JSON.stringify(data);
        this.companyArray.push(data);
        this.dataSource.push(data);
        setTimeout(function(){
          window.location.reload();
        },1500)
        this.addFlag = !this.addFlag;
        this.successMesage="Successfully Registered your Company!!"
          console.log(this.addFlag=" "+this.successMesage)
      },
      error=>{
        console.log("addComapny()==>"+error);
      })
  }
  viewFlag:boolean|any;
  noDataFoundMessage:string="";
  viewAllCompany():Array<Company>{
    this.viewFlag=false;
    this.service.viewAllCompanyDetails().subscribe(data=>{
      this.companyArray = data;
      this.dataSource= data;
      this.showViewAllTable = !this.showViewAllTable;
      console.log(this.dataSource); 
    },
    error=>{
      console.log("viewAllCompany()==>"+error);
      this.showViewAllTable = false;
      this.viewFlag=!this.viewFlag;
      this.noDataFoundMessage="No Data Found!!Please Register your Company to see the Data"
    })
    return this.companyArray;
  }
  compArray:Array<Company>=[];
  companyObjectForView:Company= new Company();
  idFlag:boolean|any;
  errorMessage:string="";
  viewCompanyById(code:string){
    this.idFlag = false;
    this.service.viewCompanyDetailsById(code).subscribe(data=>{
      this.compArray = Object.values(data);
      this.dataSource1 = this.compArray;
      this.showViewByIdTable = !this.showViewByIdTable;
      console.log(this.dataSource1);
      this.data = JSON.stringify(data);  
    },
    error=>{
      console.log("viewCompanyById()==>"+error);
      this.showViewByIdTable = false;
      this.idFlag=!this.idFlag;
      this.errorMessage = "No such Company Id Found!!Please Enter the Correct Company Code";
    })

  }
  companyObjectForDelete:Company = new Company();
  deleteCompanyById(code:string){
    this.service.DeleteCompanyDetailsById(code).subscribe(data=>{
      let idIndex = this.companyArray.findIndex(c=>c.companyCode == code);
      this.companyArray.splice(idIndex,1);
      this.viewAllCompany();
      this.showViewAllTable = true;
    },
    error=>{
      console.log("deleteCompany()==>"+error);
    })
  }
  setCompanyCode(code:string){
    console.log("code is-->"+code)
    sessionStorage.setItem("companyCode",code);
    debugger;

  }
}
