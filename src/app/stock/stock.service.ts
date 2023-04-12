import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable } from 'rxjs';
import { Stock } from './stock.model';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  public apiPostById ="http://localhost:8085/api/stock/stock/add";
  public apiGetAllDetailsBetweenDates ="http://localhost:8085/api/stock/stock/get";
  public apiGetAllStockPriceList ="http://localhost:8085/api/stock/stock/get";

  constructor(private http:HttpClient) { }
//Add
addStockDetailsToCompany(stockObject:Stock,code:string):Observable<Stock>{
  return this.http.post<Stock>(`${this.apiPostById}/${code}`,stockObject);
}
//Read
getAllStcokDetailsBetweenTheDates(code:string,startDate:Date,endDate:Date):Observable<Array<Stock>>{
return this.http.get<Array<Stock>>(`${this.apiGetAllDetailsBetweenDates}/${code}/${startDate}/${endDate}`);
}

//Read
getAllStockPriceList(code:string):Observable<Array<number>>{
  return this.http.get<Array<number>>(`${this.apiGetAllStockPriceList}/${code}`);
  }

}
