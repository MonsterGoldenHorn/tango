package com.priva.tango.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.priva.tango.entry.ChTSinggerInfo;
//@Mapper
public interface ChTSinggerInfoMapper {
    ChTSinggerInfo selectByUniqueKey(@Param("singgerId")String singgerId);
}