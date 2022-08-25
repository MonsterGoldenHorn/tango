package com.priva.tango.dynamicsql.service;

/**
 * @author tango
 */
public interface IDynamicDataService {

    /**
     * 处理接口数据，通过接口配置查询出表单id配置
     * 根据表接口拼接数据，进行插入或者修改
     * @param tableId
     * @param jsonData
     * @return
     */
    int insertOrUpdate(String tableId, String jsonData);
}
