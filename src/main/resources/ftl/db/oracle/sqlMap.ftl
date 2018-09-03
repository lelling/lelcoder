<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="**.${formatTable.formatTableName}${mapperType?cap_first}">
	
	<sql id = "${formatTable.formatTableName}Columns">
	<#if formatTable.columns??>
		<#list formatTable.columns as item>
		${item.columnName}<#if item_has_next>, </#if></#list></#if>
	</sql>
	
	<resultMap id="${formatTable.formatTableName}Map" type="**.${formatTable.formatTableName}${etyType?cap_first}" >
	<#if formatTable.columns??>
		<#list formatTable.columns as item>
		<result column="${item.columnName}" property="${item.formatColumnName?uncap_first}"<#if item.formatDataType == 'Date'> jdbcType = "timestamp"</#if>/>
		</#list>
	</#if>
	</resultMap>
	
	<sql id = "where${formatTable.formatTableName}">
	<#if formatTable.columns??>
		<#list formatTable.columns as item>
		<if test="null != ety.${item.formatColumnName?uncap_first} <#if item.formatDataType =='String'> and '' != ety.${item.formatColumnName?uncap_first} </#if>"> and ${item.columnName} = ${'#{'}ety.${item.formatColumnName?uncap_first}} </if>
		</#list>
	</#if>
	</sql>
	
	<select id = "list" resultMap="${formatTable.formatTableName}Map">
		select <include refid="${formatTable.formatTableName}Columns" />
		from ${formatTable.tableName}
		<where>
			<include refid="where${formatTable.formatTableName}" />
		</where>
	</select>
	
	<insert id ="insert">
		insert into ${formatTable.tableName}
		<trim prefix="(" suffix=")" suffixOverrides=",">
		<#if formatTable.columns??>
		<#list formatTable.columns as item>
		<if test="null != ety.${item.formatColumnName?uncap_first} <#if item.formatDataType =='String'> and '' != ety.${item.formatColumnName?uncap_first} </#if>"> ${item.columnName} ,</if>
		</#list>
		</#if>
		</trim>
		values 
		<trim prefix="(" suffix=")" suffixOverrides=",">
		<#if formatTable.columns??>
		<#list formatTable.columns as item>
		<if test="null != ety.${item.formatColumnName?uncap_first} <#if item.formatDataType =='String'> and '' != ety.${item.formatColumnName?uncap_first} </#if>"> ${'#{'}ety.${item.formatColumnName?uncap_first}} ,</if>
		</#list>
		</#if>
		</trim>
	</insert>
	
	<insert id="batInsert">
	insert into ${formatTable.tableName}(<include refid="${formatTable.formatTableName}Columns" />)
	select a.* <!-- ,seq_${formatTable.tableName}.nextval  -->
	from (
		<foreach collection="eties" item="item" index="index" separator="union all" >
		select <trim suffixOverrides=",">
		<#if formatTable.columns??>
		<#list formatTable.columns as item>
			nvl(${'#{'}item.${item.formatColumnName?uncap_first}}, <#switch item.formatDataType><#case "String">''<#break><#case "Date">sysdate<#break><#case "int"><#case "Long"><#case "BigDecimal">0<#break></#switch>), 
		</#list>
		</#if>
		</trim>
		from dual
		</foreach>
	) a
	</insert>
	
	<update id ="update">
		update ${formatTable.tableName}
		<set>
	<#if formatTable.columns??>
		<#list formatTable.columns as item>
		<if test="null != newEty.${item.formatColumnName?uncap_first} <#if item.formatDataType =='String'> and '' != newEty.${item.formatColumnName?uncap_first} </#if>"> ${item.columnName} = ${'#{'}newEty.${item.formatColumnName?uncap_first}} ,</if>
		</#list>
	</#if>
		</set>
		<where>
			<include refid="where${formatTable.formatTableName}" />
		</where>
	</update>
	
	<delete id ="delete">
		delete from ${formatTable.tableName}
		<where>
			<include refid="where${formatTable.formatTableName}" />
		</where>
	</delete>
	
</mapper>