<#-- java [mapping] -->

import java.util.List;

/**
 * 【<#if formatTable.comments??>${formatTable.comments}】数据库操作类<br></#if>
 */
public interface ${formatTable.formatTableName}${mapperType?cap_first}{
	
	/**
	 * 列表查询<br>
	 * 分页时使用PageHelper插件<br>
	 */
	public List<${formatTable.formatTableName?cap_first}${etyType?cap_first}> list(@Param(value = "ety")${formatTable.formatTableName?cap_first}${etyType?cap_first} ety);
	
	/**
	 * 单条新增<br>
	 */
	public int insert(@Param(value = "ety")${formatTable.formatTableName?cap_first}${etyType?cap_first} ety);
	
	/**
	 * 批量新增<br>
	 * 分两种：含主键序列/不含主键索引
	 */
	public int batInsert(@Param(value = "eties")List<${formatTable.formatTableName?cap_first}${etyType?cap_first}> eties);
	
	/**
	 * 更新<br>
	 * ety存放set值<br>
	 * qry存放where条件<br>
	 */
	public int update(
		@Param(value = "newEty") ${formatTable.formatTableName?cap_first}${etyType?cap_first} newEty,
		@Param(value = "ety") ${formatTable.formatTableName?cap_first}${etyType?cap_first} ety
	);
	
	/**
	 * 删除<br>
	 */
	public int delete(@Param(value = "ety")${formatTable.formatTableName?cap_first}${etyType?cap_first} ety);
	
}