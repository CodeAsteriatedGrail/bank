package com.example.demo.module.currency.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("currency")
@RestController
public class CurrencyController {
	/**
	 * TODO: 建立一張幣別與其對應中文名稱的資料表（需附建立 SQL 語法），並
	 * 提供 查詢 / 新增 / 修改 / 刪除 功能 API。
	 * @return
	 */
    @RequestMapping("/")
    public String findAll(){
        return "";
    }
}
