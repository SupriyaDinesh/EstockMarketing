import { Stock } from "../stock/stock.model";

export class Company {
    companyCode: string | any;
    companyName:string | any;
    companyCEO:string | any;
    companyTurnover:number | any;
    companyWebsite:string | any;
    stockExchange: string| any;
    currentStockPrice:number |any;
    //stockList:Array<Stock> =[];
}
