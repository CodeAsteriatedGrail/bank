package com.example.demo.module.currency.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.module.currency.entity.CurrencyName;

@Repository
public interface CurrencyDao extends JpaRepository<CurrencyName, Long> {

}