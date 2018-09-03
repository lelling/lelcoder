<#switch item.dataType>  
  	<#case "char">
  	<#case "nchar">
  	<#case "varchar">
  	<#case "nvarchar">
  	<#case "varchar2">
  	<#case "nvarchar2">
(${item.charLength})<#break>
	<#case "text">
	<#case "clob">
	<#case "blob">  
(${item.charLength})<#break>  
  	<#case "date">
  	<#case "timestamp">
  	<#case "datetime"><#break>
	<#case "number">
    <#case "decimal">
<#if item.charLength? default("")?length==0 >(${item.dataPrecision}<#if item.dataScale == 0>,${item.dataScale} </#if>)</#if><#break>
    <#case "bigint">
    <#case "smallint"><#break>
  	<#default> 
</#switch>