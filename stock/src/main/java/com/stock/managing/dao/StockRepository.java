package com.stock.managing.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.stock.managing.entity.Stock;


@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

	@Query(value = "select * from stock where company_code=?1 and time_stamp between ?2 and ?3", nativeQuery = true)
	public List<Stock> getStockByIdBetweenTheGivenDates(String companyCode, LocalDate startDate, LocalDate endDate);
	
	@Modifying
	@Query(value="delete from Stock where company_code= ?1")
	public int deleteStockDetailsByCompanyCode(String companyCode);
	
	@Query(value="select stock_price from stock where company_code= ?1", nativeQuery = true)
	public List getStockPriceList(String ccode);
}
