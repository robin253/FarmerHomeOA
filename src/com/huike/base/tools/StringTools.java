package com.huike.base.tools;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class StringTools {

	/**
	 * Deal with mybatis namespace
	 * @param basic
	 * @param queryStr
	 * @return
	 */
	public static String getNameSpace(String basic, String queryStr) {
		if (queryStr == null || "".equals(queryStr)) {
			throw new IllegalArgumentException(
					"Pease provide query id from mapper xml configuration!");
		} 
		if(basic == null || "".equals(basic))
			return queryStr;
		if (basic.endsWith(".")) {
			return basic + queryStr;
		} else {
			return basic + "." + queryStr;
		}
	}
	
	/**
	 * 中文编码
	 * @param text
	 * @param charset
	 * @return
	 */
	public static String encode(String text, String charset) {
		String _charset = null;
		StringBuffer result = new StringBuffer();
		if(charset == null || "".equals(charset)){
			_charset = "UTF-8";
		} else {
			_charset = charset;
		}
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				result.append(c);
			} else {
				/*byte[] b = new byte[0];
				try {
					b = Character.toString(c).getBytes(_charset);
				} catch (Exception ex) {
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					result.append("%" + Integer.toHexString(k).toUpperCase());
				}*/
				try {
					result.append(URLEncoder.encode(Character.toString(c), _charset));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return result.toString();
	}
	
	/**
     * 描述：获取UUID值
     * @param
     * @return String
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public static String replaceBlank(String trim) {
    	String str = trim.replaceAll("\\s*", "");
    	return str;
        /*String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;*/
    }
}