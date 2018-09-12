package com.bamboo.mapper;

import com.bamboo.model.Company;
import com.bamboo.model.CompanyExample;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface CompanyMapper extends BaseMapper<Company> {
    long countByExample(CompanyExample example);

    int deleteByExample(CompanyExample example);

    int deleteByPrimaryKey(String companyId);

    int insert(Company record);

    int insertSelective(Company record);

    List<Company> selectByExampleWithBLOBs(CompanyExample example);

    List<Company> selectByExample(CompanyExample example);

    Company selectByPrimaryKey(String companyId);

    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByExampleWithBLOBs(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKeyWithBLOBs(Company record);

    int updateByPrimaryKey(Company record);
}