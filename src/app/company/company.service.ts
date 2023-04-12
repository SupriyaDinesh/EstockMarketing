import {Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable } from 'rxjs';
import {Company } from './company.model';
 
@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  public apiPost ="http://localhost:8085/api/company/company/register";
  public apiGetAll ="http://localhost:8085/api/company/company/getall";
  public apiGetById ="http://localhost:8085/api/company/company/info";
  public apiDeleteById ="http://localhost:8085/api/company/company/delete";
  public apiGetAllStockPriceList ="http://localhost:8085/api/stock/stock/get";

  constructor(private http:HttpClient) { }
//Add
  addCompanyDetails(companyObject:Company):Observable<Company>{
    console.log(companyObject)
    return this.http.post<Company>(this.apiPost,companyObject);
  }
//Read
viewAllCompanyDetails():Observable<Array<Company>>{
  return this.http.get<Array<Company>>(this.apiGetAll);
}
viewCompanyDetailsById(code:string):Observable<Array<Company>>{
  return this.http.get<Array<Company>>(`${this.apiGetById}/${code}`);
}
//Delete
DeleteCompanyDetailsById(code:string):Observable<Company>{
  return this.http.delete<Company>(`${this.apiDeleteById}/${code}`)
}

//Read
getAllStockPriceList(code:string):Observable<Array<Number>>{
  return this.http.get<Array<Number>>(`${this.apiGetAllStockPriceList}/${code}`);
  }
}
