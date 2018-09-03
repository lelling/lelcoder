package com.code.dao;

import java.util.List;

import com.code.model.Column;
import com.code.model.Table;

public interface TableMapper {
	
	/**
	 * 获取库中表<br>
	 * 注：表名模糊匹配<br>
	 * @param table
	 * @return
	 */
	List<Table> qryTablesFromAll(Table table);
	List<Table> qryTablesFromUser(Table table);
	/**
	 * 获取表的列信息<br>
	 * 注：表名精准匹配<br>
	 * @param table
	 * @return
	 */
	List<Column> qryOneTableColumnFromAll(Table table);
	List<Column> qryOneTableColumnFromUser(Table table);
}
