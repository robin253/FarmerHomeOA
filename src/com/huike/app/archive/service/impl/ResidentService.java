package com.huike.app.archive.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.huike.app.archive.service.IResidentService;
import com.huike.base.service.BasicServiceSupport;

@Service("residentService")
public class ResidentService extends BasicServiceSupport implements IResidentService {

	public ResidentService(){
		super("com.huike.app.archive.service.IResidentService");
	}
	
	/** 农户统计:按教育程度分组  */
	public List<Map<String, Object>> statisticsGroupByEducation(Map<String, Object> params) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = getBasicDao().query(getAllSpaceName("statisticsGroupByEducation"), params);
		return list;
	}
	
	/** 农户统计:按政治面貌分组 */
	public List<Map<String, Object>> statisticsGroupByPolitics(Map<String, Object> params) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = getBasicDao().query(getAllSpaceName("statisticsGroupByPolitics"), params);
		return list;
	}
	
	/** 农户统计:按乡分组 */
	public List<Map<String, Object>> statisticsGroupByXiang(Map<String, Object> params) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = getBasicDao().query(getAllSpaceName("statisticsGroupByXiang"), params);
		return list;
	}
	
	/** 农户统计:按村分组 */
	public List<Map<String, Object>> statisticsGroupByCun(Map<String, Object> params) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = getBasicDao().query(getAllSpaceName("statisticsGroupByCun"), params);
		return list;
	}
}
