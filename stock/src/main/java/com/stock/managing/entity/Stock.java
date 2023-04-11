package com.stock.managing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // by default it is AUTO(unique in the DB)
	private Integer stockId;
	private double stockPrice;
	private String timeStamp;
	private String companyCode;

	public Stock() {
		super();
	}

	public Stock(Integer stockId, double stockPrice, String timeStamp, String companyCode) {
		super();
		this.stockId = stockId;
		this.stockPrice = stockPrice;
		this.timeStamp = timeStamp;
		this.companyCode = companyCode;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", stockPrice=" + stockPrice + ", timeStamp=" + timeStamp
				+ ", companyCode=" + companyCode + "]";
	}
	
}
