package com.stock.marketing.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	@Id
	private String companyCode;	
	private String companyName;
	private String companyCEO;
	private Double companyTurnover;
	private String companyWebsite;
	private String stockExchange;
	private Double currentStockPrice;
	public Company() {
		super();
	}
	public Company(String companyCode, String companyName, String companyCEO, Double companyTurnover,
			String companyWebsite, String stockExchange, Double currentStockPrice) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.companyTurnover = companyTurnover;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
		this.currentStockPrice = currentStockPrice;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	public Double getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(Double companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public Double getCurrentStockPrice() {
		return currentStockPrice;
	}
	public void setCurrentStockPrice(Double currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}
	@Override
	public String toString() {
		return "Company [companyCode=" + companyCode + ", companyName=" + companyName + ", companyCEO=" + companyCEO
				+ ", companyTurnover=" + companyTurnover + ", companyWebsite=" + companyWebsite + ", stockExchange="
				+ stockExchange + ", currentStockPrice=" + currentStockPrice + "]";
	}
}
