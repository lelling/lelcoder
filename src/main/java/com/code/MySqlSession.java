package com.code;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DataTypeEnum;
import com.code.dao.TableMapper;
import com.code.model.Column;
import com.code.model.FormatColumn;
import com.code.model.FormatTable;
import com.code.model.Table;
import com.lel.utils.freemarker.FreemarkerUtils;
import com.lel.utils.json.GsonUtils;
import com.utils.FormatUtils;

/**
 * 数据库方式获取元数据
 */
public class MySqlSession {
	
	private static final Logger logger = LoggerFactory.getLogger(MySqlSession.class);
	
	private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
	
	private static SqlSessionFactory sqlSessionFactory;
	
	private static final String CLASSPATH = MySqlSession.class.getClassLoader().getResource("").getPath();
	
	private static Properties dbConstant = new Properties();
	
	private static Properties constant = new Properties();
	
	private static Properties jdbcConstant = new Properties();
	
	static{
		logger.debug("------mybatis-config-------start---");
		try {
			String resource = "db/mybatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
		} catch (IOException e) {
			throw new RuntimeException("mybatis-初始化异常", e);
		}
		
		try {
			jdbcConstant.load(MySqlSession.class.getClassLoader().getResourceAsStream("db/jdbc.properties"));
			dbConstant.load(MySqlSession.class.getClassLoader().getResourceAsStream("db/dbConstant.properties"));
			constant.load(MySqlSession.class.getClassLoader().getResourceAsStream("constant.properties"));
		} catch (IOException e) {
			throw new RuntimeException("mybatis-读取constant配置异常", e);
		}
		logger.debug("------mybatis-config-------end---");
	}
	
	private static <T> T getMapper(Class<T> clazz){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.getMapper(clazz);
	}
	
	/**
	 * 格式化-数据库-表
	 */
	private static FormatTable toFormatTable(Table table){
		if (Objects.isNull(table)) {
			return null;
		}
		FormatTable formatTable = new FormatTable();
		try {
			BeanUtils.copyProperties(formatTable, table);
		} catch (Exception e) {
			throw new RuntimeException("bean-copy：表转化异常", e);
		}
		String formatTableName = formatTable.getTableName().toLowerCase();
		formatTableName = FormatUtils.removePrefix(formatTableName, dbConstant.getProperty("prefix.tablename"));
		formatTableName = FormatUtils.removeSuffix(formatTableName, dbConstant.getProperty("suffix.tablename"));
		formatTableName = FormatUtils.toHump(formatTableName);
		formatTable.setFormatTableName(formatTableName);
		return formatTable;
	}
	
	private static List<FormatColumn> toFormatColumnList(List<Column> columns){
		if (CollectionUtils.isEmpty(columns)) {
			return null;
		}
		List<FormatColumn> list = new ArrayList<>();
		for (Column col : columns) {
			list.add(toFormatColumn(col));
		}
		return list;
	}
	
	/**
	 * 格式化-数据库-列
	 */
	private static FormatColumn toFormatColumn(Column column){
		if (Objects.isNull(column)) {
			return null;
		}
		FormatColumn formatcolumn = new FormatColumn();
		try {
			BeanUtils.copyProperties(formatcolumn, column);
		} catch (Exception e) {
			throw new RuntimeException("bean-copy：列异常", e);
		}
		// 格式化名称
		String formatColumnName = formatcolumn.getColumnName().toLowerCase();
//		formatColumnName = FormatUtils.removePrefix(formatColumnName, "");
//		formatColumnName = FormatUtils.removeSuffix(formatColumnName, "");
		formatColumnName = FormatUtils.toHump(formatColumnName);
		formatcolumn.setFormatColumnName(formatColumnName);
		// 格式化字段类型
		String formatDataType = formatcolumn.getDataType().toLowerCase();
		formatcolumn.setDataType(formatDataType);
		DataTypeEnum dataTypeEnum = DataTypeEnum.getEnum(formatDataType);
		if (null == dataTypeEnum) {
			formatcolumn.setFormatDataType("Object");
		}else{
			formatcolumn.setFormatDataType(dataTypeEnum.getJavaType());
		}
		return formatcolumn;
	}
	
	/**
	 * 获取完整表信息
	 */
	public static FormatTable toFullFormatTable(String tableName, String dbName){
		assert(StringUtils.isNotBlank(tableName));
		TableMapper mapper = getMapper(TableMapper.class);
		Table qryTable = new Table();
		qryTable.setTableName(tableName);
		qryTable.setDbName(dbName);
		List<Table> tables = mapper.qryTablesFromAll(qryTable);
		if (CollectionUtils.isEmpty(tables)) {
			logger.error("未查询所需要的表：" + dbName + "." +tableName);
			return null;
		}
		Table table = null;
		for(Table t: tables){
			if (t.getTableName().equalsIgnoreCase(tableName)) {
				table = t;
				break;
			}
		}
		FormatTable formatTable = null;
		if (null != table) {
			formatTable = toFormatTable(table);
			List<Column> columns = mapper.qryOneTableColumnFromAll(table);
			formatTable.setColumns(toFormatColumnList(columns));
		}
		return formatTable;
		
	}
	
	public static void toFtl(){
//		FormatTable formatTable = toFullFormatTable("t_user_extend", "mw");
		FormatTable formatTable = toFullFormatTable("t_performance_item", "HTD_MANAGER");
		
		logger.info(GsonUtils.toJson(formatTable));
		Map<String, Object> map = new HashMap<>();
		map.put("formatTable", formatTable);
		String basePath = CLASSPATH;
		
		// 生成ety
		String etyType = constant.getProperty("suffix.ety");
		map.put("etyType", etyType);
//		FreemarkerUtils.processToFile(CLASSPATH + "/ftl", "ety.ftl", map, 
//				basePath + formatTable.getFormatTableName() + etyType + constant.getProperty("suffix.file.java"));
		
		
		// 生成mapper
		String mapper = constant.getProperty("suffix.mapper");
		map.put("etyType", etyType);
		map.put("mapperType", mapper);
//		FreemarkerUtils.processToFile(CLASSPATH + "/ftl/db", "mapper.ftl", map, 
//				basePath + formatTable.getFormatTableName() + mapper + constant.getProperty("suffix.file.java"));
		
		String dbType = jdbcConstant.getProperty("db.type");
		// 生成sqlMap
		map.put("etyType", etyType);
		map.put("mapperType", mapper);
		FreemarkerUtils.processToFile(CLASSPATH + "/ftl/db/" + dbType, "sqlMap.ftl", map, 
				basePath + formatTable.getFormatTableName() + constant.getProperty("suffix.file.xml"));
		
	}
	
}
