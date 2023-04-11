package com.stock.managing.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stock.managing.StockApplication;
import com.stock.managing.entity.Stock;
import com.stock.managing.exception.StockManagingException;
import com.stock.managing.service.StockService;
import com.stock.stockmanaging.dto.Company;


@RestController
@CrossOrigin("*")
public class StockController {
	Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	StockService stockService;

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${company.get.url}")
	private String getUrl;
	
	@Value("${company.update.url}")
	private String updateUrl;
	
	/**Add stock details to stock table  and update latest stock price in company table**/
	@PostMapping("stock/add/{id}")
	public ResponseEntity<Stock> addStock(@RequestBody Stock stock, @PathVariable String id) {
		logger.info("Inside addStock!!==>"+"=="+stock.toString()+"=="+stock.getStockPrice()+"=="+id);
			try {
				ResponseEntity<Company> response = restTemplate.exchange(getUrl+id, HttpMethod.GET, new HttpEntity(null), new ParameterizedTypeReference<Company>() {});
				logger.info("Done with getUrl exchange!!");
				logger.info("======"+response+"===");
				Company existCompany = response.getBody();
				if (existCompany != null) {
					existCompany.setCurrentStockPrice(stock.getStockPrice());
					stock.setCompanyCode(id);
				HttpHeaders headers = new HttpHeaders();
				headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
				HttpEntity<Company> httpEntity = new HttpEntity<>(existCompany,headers);
				logger.info("Request Body for PUT is-->"+httpEntity);
				ResponseEntity<Boolean> exchange = restTemplate.exchange(updateUrl, HttpMethod.PUT, httpEntity,Boolean.class);
				logger.info("Is Company Table Updated with latest Stock Price?->"+exchange);
				boolean addStock = stockService.addStock(stock);
				logger.info("Added Stock Price Successfully!!");			
			}
				else
				throw new StockManagingException("Failed to add Stock Price, Please check the logs for more details !!");
			}
			catch(StockManagingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
	}

	/**Read-Get the stock details of a company between the given time period**/
	@GetMapping("stock/get/{ccode}/{startDate}/{endDate}")
	public ResponseEntity<List<Stock>> getStockbyIdBetweentheGivenDates(@PathVariable String ccode,
			@DateTimeFormat(iso = ISO.DATE) @PathVariable LocalDate startDate, //in YYYY-MM-dd format
			@DateTimeFormat(iso = ISO.DATE) @PathVariable LocalDate endDate) {
		logger.info(ccode + "==" + startDate + "==" + endDate);
		 List<Stock> stockList = stockService.getStockByIdBetweenTheGivenDates(ccode, startDate, endDate);
		 logger.info("Stock List is-->"+stockList);
		 if(stockList.size() > 0)
			 return new ResponseEntity<List<Stock>>(stockList, HttpStatus.OK);
		 else
			 return new ResponseEntity<List<Stock>>(stockList, HttpStatus.NOT_FOUND);
	}
	
	/**Read-Get the list of stock price of a company**/
		@GetMapping("stock/get/{ccode}")
		public ResponseEntity<List> getListOfStockPriceById(@PathVariable String ccode) {
			logger.info("code is-->"+ccode);
			 List stockPriceList = stockService.getListOfStockPrice(ccode);
			 if(stockPriceList.size() > 0)
				 return new ResponseEntity<List>(stockPriceList, HttpStatus.OK);
			 else
				 return new ResponseEntity<List>(stockPriceList, HttpStatus.NOT_FOUND);
		}
		
		@DeleteMapping("stock/delete/{code}")
		public int deleteStockbyId(@PathVariable String code) {
			logger.info("code is-->"+code);
			 return stockService.deleteStockById(code);
		}
}
