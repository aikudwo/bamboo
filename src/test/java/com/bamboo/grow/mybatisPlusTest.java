package com.bamboo.grow;

import com.bamboo.mapper.CompanyMapper;
import com.bamboo.model.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @autor wls on 2018/9/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class mybatisPlusTest {
    @Autowired
    CompanyMapper companyMapper;

    @Test
    public void companyTest(){
        List<Company> companies = companyMapper.selectList(null);
        for (Company company : companies) {
            System.out.println(company.getArea());
        }
    }
}
