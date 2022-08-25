package com.priva.tango.dynamicsql.service;

import com.priva.tango.dynamicsql.dto.TableJsonDto;
import com.priva.tango.dynamicsql.entry.Table;

import javax.swing.table.TableColumn;
import java.util.List;

/**
 * @author tango
 */
public interface IDynamicTableService {

    /**
     * 建表
     * @param jsonDto
     * @return
     */
    boolean createTable(TableJsonDto jsonDto);

    /**
     * 修改表结构
     * 1）新增字段
     * 2）修改字段名称
     * 3）字段类型修改时，删除创建新列
     * @param jsonDto
     * @return
     */
    boolean updateTable(TableJsonDto jsonDto);

    /**
     * 删除表结构以及配置
     * @return
     */
    boolean dropTable();

    /**
     * 查询所有表
     * @return
     */
    List<Table> queryAllTable();

    /**
     * 通过id查询表结构
     * @param id
     * @return
     */
    List<TableColumn> queryColumnById(String id);
}
