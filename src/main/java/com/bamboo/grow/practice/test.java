package com.bamboo.grow.practice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class test {


    /**
     * 判断公司名称是否重复(是否注册过)
     * @param companyName 公司名称
     */
    @RequestMapping(value = "/checkCompanyName", method = RequestMethod.GET)
    public void insertCompany(String companyName,String name) {
        System.out.println("aaaaa");
    }
}
