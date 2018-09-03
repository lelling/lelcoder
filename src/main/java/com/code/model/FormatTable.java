package com.code.model;

import java.util.List;

/**
 * 格式化表信息
 * @author lel
 *
 */
public class FormatTable extends Table {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6187261783468805090L;
	
	/**
	 * 格式化表名<br>
	 * 去除前缀、后缀且驼峰式<br>
	 */
	private String formatTableName;
	/**
	 * 字段列表
	 */
	private List<FormatColumn> columns;

	public String getFormatTableName() {
		return formatTableName;
	}
	
	public void setFormatTableName(String formatTableName) {
		this.formatTableName = formatTableName;
	}

	public List<FormatColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<FormatColumn> columns) {
		this.columns = columns;
	}
	
}
