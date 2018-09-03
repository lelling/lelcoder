package com;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据库字段类型-java类型对应关系
 */
public enum DataTypeEnum {
	CHAR("char", "String"),
	NCHAR("nchar", "String"),
	VARCHAR2("varchar2", "String"),
	NVARCHAR2("nvarchar2", "String"),
	VARCHAR("varchar", "String"),
	TEXT("text", "String"),
	CLOB("clob", "String"),
	BLOB("blob", "String"),
	
	DATE("date", "Date"),
	DATETIME("datetime", "Date"),
	TIMESTAMP("timestamp", "Date"),
	
	SMALLINT("smallint", "int"),
	BIGINT("bigint", "Long"),
	
	NUMBER("number","BigDecimal"),
	DECIMAL("decimal","BigDecimal")
	;
	
	
	private String jdbcType;
	
	private String javaType;
	
	
	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	private DataTypeEnum(String jdbcType, String javaType){
		this.javaType = javaType;
		this.jdbcType = jdbcType;
	}
	
	public static DataTypeEnum getEnum(String jdbcType){
		if (StringUtils.isBlank(jdbcType)) {
			return null;
		}
		for(DataTypeEnum dataTypeEnum : DataTypeEnum.values()){
			if (dataTypeEnum.getJdbcType().equals(jdbcType)) {
				return dataTypeEnum;
			}
		}
		return null;
	}
}
