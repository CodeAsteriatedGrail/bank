package com.example.demo.module.gatway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("coindesk")
@RestController
public class CoindeskController {
	/**
	 * TODO: 呼叫 coindesk API原生
	 * @return
	 */
    @RequestMapping("/currentprice")
    public String find(){
        return "";
    }
    
    /**
     * TODO: 呼叫 coindesk 的 API，並進行資料轉換
     * @return
     */
    @RequestMapping("/currentprice/new")
    public String findForCovert(){
        return "";
    }
}
