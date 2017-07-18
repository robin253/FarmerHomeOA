package com.huike.base.tools.upload;


import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.huike.base.tools.CacheHelp;

import net.sf.json.JSONObject;


/**
 * <p class="detail">
 * 功能：后台签名
 * </p>
 * @ClassName: OssFileSign 
 * @version V1.0  
 * @date 2017年3月1日 
 * @author renp
 * Copyright 2015 tsou.com, Inc. All rights reserved
 */

public class OssFileSign {
	private static String endpoint = CacheHelp.SYS_CONFIG_MAP.get("enpoint")==null? "http://oss-cn-shanghai.aliyuncs.com": CacheHelp.SYS_CONFIG_MAP.get("enpoint").toString();  
    private static String accessId = CacheHelp.SYS_CONFIG_MAP.get("access_key_id")==null? "LTAIccJqvoE4hqUP":CacheHelp.SYS_CONFIG_MAP.get("access_key_id").toString();  
    private static String accessKey = CacheHelp.SYS_CONFIG_MAP.get("access_key_secret")==null? "Tic4IyaqNtQbUVrs5nohb9tSgA6rkW":CacheHelp.SYS_CONFIG_MAP.get("access_key_secret").toString();  
    private static String bucket = CacheHelp.SYS_CONFIG_MAP.get("bucket_name")==null?"baichaung":CacheHelp.SYS_CONFIG_MAP.get("bucket_name").toString();    

	private static final long serialVersionUID = 5522372203700422672L;

	protected void doSign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	    
	   // String endpoint = "*";
       // String accessId = "*";
       // String accessKey = "*";
       // String bucket = "*";
        String dir = "user-dir";
        String host = "http://" + bucket + "." + endpoint;
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try { 	
        	long expireTime = 30;
        	long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            //respMap.put("expire", formatISO8601Date(expiration));
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            JSONObject ja1 = JSONObject.fromObject(respMap);
            System.out.println(ja1.toString());
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST");
            response(request, response, ja1.toString());
            
        } catch (Exception e) {
            //Assert.fail(e.getMessage());
        }
    }
	
	private void response(HttpServletRequest request, HttpServletResponse response, String results) throws IOException {
		String callbackFunName = request.getParameter("callback");
		if (callbackFunName==null || callbackFunName.equalsIgnoreCase(""))
			response.getWriter().println(results);
		else
			response.getWriter().println(callbackFunName + "( "+results+" )");
		response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		doSign(request, response);
    }
}
