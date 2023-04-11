package com.stock.marketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stock.marketing.entity.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

}
