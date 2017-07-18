package com.huike.base.tools;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.collections.MapUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class MapUtil {
	
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> transBean2Map(Object obj) {  
  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
  
        return map;  
  
    }  

	/**
	 * JSON格式字符串转Map
	 * @param str
	 * @return
	 */
	public static Map<String, Object> string2Map(String str) {
		Map<String, Object> map = null;
		if (str == null) {
			return map;
		}
		map = JSON.parseObject(str, new TypeReference<Map<String, Object>>() {
		});
		return map;
	}
	
	/**
	 * Map转json格式的字符串
	 * @param map
	 * @return
	 */
	public static String map2JsonString(Map<String, Object> map){
		JSONObject json=new JSONObject(map); 
		return json.toJSONString();
	}
	
	/**
	 * 构建包含传入的keys的map
	 * @param paramMap 原始map
	 * @param params 新map中含有的key
	 * @return
	 */
	public static Map<String, Object> mapForParam(Map<String, Object> paramMap, String[] params) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (params != null && params.length > 0) {
			for (String paramName : params) {
					map.put(paramName, paramMap.get(paramName));
			}
		}
		return map;
	}
	
	/**
	 * 从map中移除传入的keys的元素
	 * @param paramMap 原始map
	 * @param params 需要从原始map移除的key
	 * @return
	 */
	public static Map<String, Object> mapRemoveParam(Map<String, Object> paramMap, String[] params) {
		if (params != null && params.length > 0) {
			for (String paramName : params) {
				paramMap.remove(paramName);
			}
		}
		return paramMap;
	}
	
	
/*	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("other1", "othersafdsaf");
		map.put("other3", "othersa123fdsaf");
		map.put("other4", "oth54ersafdsaf");
		mapForParam(map,new String[]{"other1"});
	}*/

	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}

	/**
	 * 使用 Map按value进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());
		Collections.sort(entryList, new MapValueComparator());

		Iterator<Map.Entry<String, String>> iter = entryList.iterator();
		Map.Entry<String, String> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}
	
	/** 
	 * 方法名称:transStringToMap 
	 * 传入参数:mapString 形如 username'chenziwen^password'1234 
	 * 返回值:Map 
	*/  
	public static Map transStringToMap(String mapString){  
	  Map map = new HashMap();  
	  java.util.StringTokenizer items;  
	  for(StringTokenizer entrys = new StringTokenizer(mapString, ", ");entrys.hasMoreTokens();   
	    map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))  
	      items = new StringTokenizer(entrys.nextToken(), "=");  
	  return map;  
	}  
	
	public static void main(String[] args) {
		String str1 = "level=1, name=JJ, headUrl=http://baichaung.oss-cn-shanghai.aliyuncs.com/weiside//IMG_-1927833785133435ba160949a29f66105ad566d99a.jpg, id=47, userType=3, username=18969978220, status=1, cid=329839e928a2c2d8e8afdab7be6ed404";
		String str = "{level=1, name=JJ, headUrl=http://baichaung.oss-cn-shanghai.aliyuncs.com/weiside//IMG_-1927833785133435ba160949a29f66105ad566d99a.jpg, id=47, userType=3, username=18969978220, status=1, cid=329839e928a2c2d8e8afdab7be6ed404}";
		Map map = transStringToMap(str.substring(1, str.length()-1));
		System.out.println(map.get("cid"));
	}
}

class MapKeyComparator implements Comparator<String> {

	@Override
	public int compare(String str1, String str2) {

		return str1.compareTo(str2);
	}
}

class MapValueComparator implements Comparator<Map.Entry<String, String>> {

	@Override
	public int compare(Entry<String, String> me1, Entry<String, String> me2) {

		return me1.getValue().compareTo(me2.getValue());
	}
}
