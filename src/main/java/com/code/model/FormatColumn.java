package com.code.model;

public class FormatColumn extends Column{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4308455533711101167L;
	/**
	 * 格式化-列名<br>
	 * 驼峰式<br>
	 */
	private String formatColumnName;
	/**
	 * 格式化-数据类型<br>
	 * 对应java类型<br>
	 */
	private String formatDataType;

	public String getFormatColumnName() {
		return formatColumnName;
	}

	public void setFormatColumnName(String formatColumnName) {
		this.formatColumnName = formatColumnName;
	}

	public String getFormatDataType() {
		return formatDataType;
	}

	public void setFormatDataType(String formatDataType) {
		this.formatDataType = formatDataType;
	}
	
}
