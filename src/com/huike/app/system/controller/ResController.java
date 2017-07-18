package com.huike.app.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartframework.common.utils.ExStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huike.app.system.model.Res;
import com.huike.app.system.service.impl.ResService;
import com.huike.base.tools.ListUtil;
import com.huike.base.tools.Page;
import com.sunshine.common.utils.ExControllerUtils;

@RestController
@RequestMapping("/api")
public class ResController {
	
	protected static final Logger log = LoggerFactory.getLogger(ResController.class);
	
	@Qualifier("resService")
	@Autowired(required=true)
	private ResService resService;
	
	/**
	 * 添加资源
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/res/add", produces = "application/json")
	public Map addRes(HttpServletRequest request) {
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 非空判断
		Object moldObj = params.get("mold");
		if(moldObj == null || ExStringUtils.isBlank(moldObj.toString())){
			resultMap.put("resultMsg", "类型不能为空！");
			return  resultMap;
		}
		Object urlObj = params.get("url");
		if(urlObj == null || ExStringUtils.isBlank(urlObj.toString())){
			resultMap.put("resultMsg", "url不能为空！");
			return  resultMap;
		}
		Object expoundObj = params.get("expound");
		if(expoundObj == null || ExStringUtils.isBlank(expoundObj.toString())){
			resultMap.put("resultMsg", "描述信息不能为空！");
			return  resultMap;
		}
		
		Integer result = resService.save(params);
		
		if(result > 0){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
		}else {
			resultMap.put("resultMsg", "资源添加失败！");
		}
		return resultMap;
	}
	
	/**
	 * 为角色分配资源
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/res/addroleres", produces = "application/json")
	public Map addRoleRes(HttpServletRequest request) {
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 非空判断
		Object roleidObj = params.get("roleid");
		if(roleidObj == null || ExStringUtils.isBlank(roleidObj.toString())){
			resultMap.put("resultMsg", "请选择角色！");
			return  resultMap;
		}
		Integer result = resService.addRoleRes(params);
		
		if(result > 0){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
		}else {
			resultMap.put("resultMsg", "角色资源分配失败！");
		}
		return resultMap;
	}
	
	/**
	 * 为角色分配资源
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/res/grant", produces = "application/json")
	public Map Grant(HttpServletRequest request) {
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 非空判断
		Object roleidObj = params.get("roleid");
		if(roleidObj == null || ExStringUtils.isBlank(roleidObj.toString())){
			resultMap.put("resultMsg", "请选择角色！");
			return  resultMap;
		}
		Object resIdsObj = params.get("resids");
		if( resIdsObj == null){
			resultMap.put("resultMsg", "请要分配的资源！");
			return resultMap;
		}
		// 先删除角色-资源关系，再重新分配
		resService.removeRelationByRoleId(ExStringUtils.getIntValue(roleidObj.toString()));
		// 根据资源ID批量删除资源
		List<Integer> resid = ListUtil.handleString2Array(resIdsObj.toString(), ",");
		Integer result = 0;
		for(int i = 0; i < resid.size(); i++){
			params.put("resid", resid.get(i));
			result += resService.addRoleRes(params);
		}
		
		if(result > 0){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
		}else {
			resultMap.put("resultMsg", "角色资源分配失败！");
		}
		return resultMap;
	}
	
	/**
	 * 修改资源
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/res/update", produces = "application/json")
	public Map updateRole(HttpServletRequest request) {
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Integer result = resService.modify(params);
		if(result > 0){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
		}else {
			resultMap.put("resultMsg", "修改资源失败！");
		}
		return resultMap;
	}
	
	/**
	 * 根据资源ID查询资源
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/res/queryById", produces = "application/json")
	public Map queryById(HttpServletRequest request){
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object resIdObj = params.get("id");
		if( resIdObj == null || ExStringUtils.getIntValue(resIdObj.toString()) < 0){
			resultMap.put("resultMsg", "资源ID不能为空！");
			return resultMap;
		}
		Map res = resService.queryById(ExStringUtils.getIntValue(resIdObj.toString()));
		resultMap.put("resultCode", 0);
		resultMap.put("row", res);
		return resultMap;
	}
	
	/**
	 * 根据ID删除资源
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/back/res/removeById", produces = "application/json")
	public Map removeById(HttpServletRequest request) {
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object resIdObj = params.get("id");
		if( resIdObj == null || ExStringUtils.getIntValue(resIdObj.toString()) < 0){
			
			resultMap.put("resultMsg", "资源ID不能为空！");
			return resultMap;
		}
		// 删除资源
		Integer result = resService.removeById(ExStringUtils.getIntValue(resIdObj.toString()));
		// 删除角色-资源关系
		Integer resultRelation = resService.removeRelationByResId(ExStringUtils.getIntValue(resIdObj.toString()));
		if(result > 0){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "删除资源成功！");
		}else {
			resultMap.put("resultMsg", "删除资源失败！");
		}
		return resultMap;
	}
	
	/**
	 * 根据ID批量删除资源
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/back/res/removeBatchById", produces = "application/json")
	public Map removeBatchById(HttpServletRequest request) {
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object resIdsObj = params.get("ids");
		if( resIdsObj == null){
			resultMap.put("resultMsg", "请选择要删除的记录！");
			return resultMap;
		}
		// 根据资源ID批量删除资源
		Integer result = resService.deleteForBatch(ListUtil.handleString2Array(resIdsObj.toString(), ","));
		// 批量删除角色-资源关系
		Integer resultRelation = resService.deleteRelationByResIds(ListUtil.handleString2Array(resIdsObj.toString(), ","));
		if(result > 0){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "删除资源成功！");
		}else {
			resultMap.put("resultMsg", "删除资源失败！");
		}
		return resultMap;
	}
	
	
	/**
	 * 根据条件分页查询角色列表
	 * 
	 * @param request
	 * @return findForPageListSearch
	 */
	@RequestMapping(value = "/back/res/findForPageListSearch", produces = "application/json")
	public Map findForPageListSearch(HttpServletRequest request) {
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		List<Res> list = new ArrayList<Res>();
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			int pageIndex = ExControllerUtils.getPageIndex(params);
			int pageSize = ExControllerUtils.getPageSize(params);
			params.put("offset", new Page(pageSize, pageIndex).getOffset());
			params.put("show", pageSize);
			list = resService.findForPageListSearch(params);
			resultMap = ExControllerUtils.buildTableData(list, pageIndex, pageSize);
			resultMap.put("resultMsg", "ERROR");
			Integer resultCode = (Integer) resultMap.get("resultCode");
			if (resultCode == 0) {
				resultMap.put("resultMsg", "OK");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 根据条件分页查询角色总数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/res/findForPageTotalSearch", produces = "application/json")
	public Map findForPageTotalSearch(HttpServletRequest request) {
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			int pageIndex = ExControllerUtils.getPageIndex(params);
			int pageSize = ExControllerUtils.getPageSize(params);
			Integer result = resService.findForPageTotalSearch(params);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 查询所有菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/res/allMenu", produces = "application/json")
	public Map queryAllMenu(HttpServletRequest request){
		Map resultMap = new HashMap();
		List<Integer> resIdList = new ArrayList<Integer>();
		List<Map> rowData = new ArrayList<Map>();
		Map rows = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
			Object roleIdObj = params.get("roleid");
			if( roleIdObj != null && ExStringUtils.getIntValue(roleIdObj.toString()) > 0){
				// 通过角色ID查询资源ID
				resIdList = resService.queryResIdsByRoleId(ExStringUtils.getIntValue(roleIdObj.toString()));
				rows.put("resIds", resIdList);
			}
			List<Map> reslist = resService.queryAllMenu(params);
			rows.put("rows", reslist);
			rowData.add(rows);
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("rowData", rowData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 根据角色查询菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/back/res/menuByRole", produces = "application/json")
	public Map queryMenuByRole(HttpServletRequest request){
		HttpSession session = request.getSession();
		log.info("res session id: "+session.getId());
		session.getAttribute("roleid");
		Map resultMap = new HashMap();
		resultMap.put("resultCode", 1);
		Map params = new HashMap();
		try {
			params = ExControllerUtils.buildParametersMap(request);
//			Object roleIdObj = session.getAttribute("roleid");
			Object roleIdObj = params.get("roleid");
			if( roleIdObj == null || ExStringUtils.getIntValue(roleIdObj.toString()) < 0){
				resultMap.put("resultMsg", "角色ID不能为空！");
				return resultMap;
			}
			List<Map> reslist = resService.queryMenuByRole(ExStringUtils.getIntValue(roleIdObj.toString()));
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "OK");
			resultMap.put("rows", reslist);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
}
