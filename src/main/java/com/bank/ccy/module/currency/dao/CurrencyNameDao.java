package com.bank.ccy.module.currency.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.ccy.module.currency.entity.CurrencyName;

@Repository
public interface CurrencyNameDao extends JpaRepository<CurrencyName, Long> {

	CurrencyName findByCode(String code);
}