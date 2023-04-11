package com.stock.marketing.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stock.marketing.dao.CompanyRepository;
import com.stock.marketing.entity.Company;


@Service
public class CompanyService {
	Logger logger = LoggerFactory.getLogger(CompanyService.class);

	@Autowired
	CompanyRepository repo;

	// ADD-Company Registration or adding company details
	public Company addCompanyDetails(Company company) {
		return repo.save(company);

	}

	// READ-Get all Company Details with latest stock price
	public List<Company> viewAllCompanyDetails() {
		return repo.findAll();
	}

	public Optional<Company> viewCompanyDetailsById(String id) {
		return repo.findById(id);
	}

	// Delete-Delete the Company by Id
	public boolean deleteCompanyById(String companyCode) {
		repo.deleteById(companyCode);
		logger.info("Deleted by Id Successfully!!");
		return true;
	}

	// Update-not calling directly
	public boolean updateCompany(Company company) {
//		@SuppressWarnings("deprecation")
		logger.info("Comany detaisl for Update is-->"+company);
		Company companyData = repo.getById(company.getCompanyCode());
		if (companyData != null) {
			company.setCurrentStockPrice(company.getCurrentStockPrice());
			repo.saveAndFlush(company);
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public Company getCompanybyId(String id) {
		Company CompanyData = repo.getById(id);
		return CompanyData;
	}
}
