package com.huike.base.tools;

import java.util.HashMap;
import java.util.Map;

public class CacheHelp {
	
	public static Map<String, Object> SESSION_USER_MAP = new HashMap<String, Object>(); // 用于登录,保持登录安全校验

	public static Map<String, Object> SYS_CONFIG_MAP = new HashMap<String, Object>(); // 启动加载配置项
	
	public static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY"; // 图片验证码存储key
	
}
