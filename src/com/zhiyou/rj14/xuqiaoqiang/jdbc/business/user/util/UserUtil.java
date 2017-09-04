package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtil {
	public static boolean validateUserId(String userIdStr){
		if(userIdStr == null){
			return false;
		}
		if(userIdStr.length() == 0){
			return false;
		}
		Matcher mer = Pattern.compile("^[0-9]{1,10}$").matcher(userIdStr.trim());
		return mer.find();
	}
}
