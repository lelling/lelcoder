package com.code.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据表
 * @author lel
 *
 */
public class Table implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3394157136213193662L;
	
	/**
	 * 归属库
	 */
	private String dbName;
	
	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 表备注
	 */
	private String comments;
	
	/**
	 * 表创建时间
	 */
	private Date createTime;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
