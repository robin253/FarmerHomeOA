package com.huike.base.tools.upload;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.UrlResource;

public class GetFileUrl {
	
	public static String getFileUrl(){
		
		Map<String,String> m=new HashMap<String,String>();
    	
	    if(File.separator.equals("\\")){
				m.put("DOWN_URL","");
		}else{
				m.put("DOWN_URL", "/usr/local/tomcat/webapps/wsd-web/download/");
		}
	    return m.get("DOWN_URL");
		
	}
	
	public static String realPath(HttpServletRequest request){
		
		String realPath = request.getSession().getServletContext().getRealPath(GetFileUrl.getFileUrl());

		return realPath;
		
	}
	
	
	
	
}
