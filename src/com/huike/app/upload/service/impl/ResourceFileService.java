package com.huike.app.upload.service.impl;

import org.springframework.stereotype.Service;

import com.huike.app.upload.service.IResourceFileService;
import com.huike.base.service.BasicServiceSupport;

@Service("resourceFileService")
public class ResourceFileService extends BasicServiceSupport implements IResourceFileService{

	public ResourceFileService(){
		super("com.huike.app.upload.service.IResourceFileService");
	}
}
