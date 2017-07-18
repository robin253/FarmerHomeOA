package com.huike.app.system.service;

import java.util.List;
import java.util.Map;

import com.huike.base.service.IBasicService;

public interface IUserService extends IBasicService {
	
	public Map<String, Object> checkUser(Map<String, Object> map);
	
	public List<Map<String, Object>> findUserListByCun(Map<String, Object> map);
	
	/** 添加上传报表用户 */
	public Integer addReportUser(Map<String, Object> map);
	
	/** 删除上传报表用户 */
	public Integer deleteReportUser(Map<String, Object> map);
	
	/** 查询所有上传报表用户用于发送短信 */
	public List<Map<String, Object>> selectAllReportUserListForSMS(Map<String, Object> map);
	
	/** 查询所有上传报表用户用于归档 */
	public List<Map<String, Object>> selectAllReportUserListForManage(Map<String, Object> map);
	
	/** 根据级别查询所有人员 */
	public List<Map<String, Object>> queryUsersByLevel(Map<String, Object> map);
	
	/** 不分页查询人员 */
	public List<Map<String, Object>> queryUsersList(Map<String, Object> map);
	
	/** 校验用户名是否已经存在 */
	public boolean existUser(String username);
}
