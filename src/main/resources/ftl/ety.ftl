
import java.io.Serializable;

/**
 * <#if formatTable.comments??>${formatTable.comments}<br></#if>
 * 【${formatTable.tableName}】<br>
 * NA(nullable:是否允许为空，默认为Y; N：非空, Y:允许为null)
 * @author ${author!''}
 * @create ${.now}
 */
public class ${formatTable.formatTableName}${etyType?cap_first} implements Serializable{
	<#-- 属性 -->
	<#if formatTable.columns??>
		<#list formatTable.columns as item>
	/**
	 * <#if item.comments??>${item.comments}<br></#if>
	 * @Column ${item.columnName} - [${item.dataType}<#include "datatype.ftl">]<br>
	 * <#if item.nullable??>NA：${item.nullable}<br></#if>
	 */
	private ${item.formatDataType} ${item.formatColumnName?uncap_first};
	
		</#list>
	</#if>
}
