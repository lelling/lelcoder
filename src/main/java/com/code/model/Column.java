package com.code.model;

import java.io.Serializable;

/**
 * 数据列
 * @author lel
 *
 */
public class Column implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8825558659755764265L;
	/**
	 * 字段序号
	 */
	private int columnId;
	/**
	 * 字段名称
	 */
	private String columnName;
	/**
	 * 备注/说明
	 */
	private String comments;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * 字符型-长度
	 */
	private String charLength;
	/**
	 * 数字型-位数
	 */
	private String dataPrecision;
	/**
	 * 数字型-精度
	 */
	private String dataScale;
	/**
	 * 是否允许空
	 * mysql: YES/NO
	 */
	private String nullable;
	/**
	 * 默认值
	 */
	private String defaultValue;
	/**
	 * 列类型
	 */
	private String columnType;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getCharLength() {
		return charLength;
	}
	public void setCharLength(String charLength) {
		this.charLength = charLength;
	}
	public String getDataPrecision() {
		return dataPrecision;
	}
	public void setDataPrecision(String dataPrecision) {
		this.dataPrecision = dataPrecision;
	}
	public String getDataScale() {
		return dataScale;
	}
	public void setDataScale(String dataScale) {
		this.dataScale = dataScale;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public int getColumnId() {
		return columnId;
	}
	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
	
}
