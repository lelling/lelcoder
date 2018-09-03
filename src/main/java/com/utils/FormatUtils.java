package com.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FormatUtils.class);
	
	public static void sortArrayByLenth(String... strs){
		if (!Objects.isNull(strs)) {
			Arrays.sort(strs, new Comparator<String>(){
				@Override
				public int compare(String a, String b) {
					return b.length() - a.length();
				}
			});
		}
	}
	
	public static String removePrefix(String str, String... preFixs){
		assert(StringUtils.isNotBlank(str));
		String format = str;
		if (!Objects.isNull(preFixs)) {
			String[] newPre = preFixs;
			if (preFixs.length == 1) {
				newPre = preFixs[0].split(",");
			}
			sortArrayByLenth(preFixs);
			for(String prefix: newPre){
				if (format.startsWith(prefix)) {
					format = format.substring(prefix.length());
				}
			}
		}
		return format;
	}
	
	public static String removeSuffix(String str, String... sufFixs){
		assert(StringUtils.isNotBlank(str));
		String format = str;
		if (!Objects.isNull(sufFixs)) {
			String[] newSuf = sufFixs;
			if (sufFixs.length == 1) {
				newSuf = sufFixs[0].split(",");
			}
			sortArrayByLenth(newSuf);
			for(String sufFix: sufFixs){
				if (format.endsWith(sufFix)) {
					format = format.substring(0, str.length() - sufFix.length());
				}
			}
		}
		return format;
	}
	
	/**
	 * 下划线转驼峰-首字母大写
	 */
	public static String toHump(String str){
		logger.debug("驼峰转化-开始：" + str);
		if (StringUtils.isBlank(str)) {
			return str;
		}
		StringBuilder sBuilder = new StringBuilder();
		String[] strs = str.split("_");
		for(String item : strs){
			if (StringUtils.isNotEmpty(item)) {
				sBuilder.append(item.substring(0,1).toUpperCase()).append(item.substring(1));
			}
		}
		logger.debug("驼峰转化-结束：" + str + " - " + sBuilder.toString());
		return sBuilder.toString();
	}
	
	public static void main(String[] args){
		System.out.println(removePrefix("t_user_info", "t","t_"));
		
		System.out.println(removeSuffix("t_user_info", "t","_info","t_"));
		
		System.out.println(toHump("t_1user_3info"));
		System.out.println(toHump("_1user_3info_"));
		System.out.println(toHump("T_USER_INFO"));
	}
}
