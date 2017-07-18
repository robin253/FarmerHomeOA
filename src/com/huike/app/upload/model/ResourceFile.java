package com.huike.app.upload.model;

import java.util.Date;

public class ResourceFile {

	private Long id; // 
	
	private	String sourceUrl; // 文件URL
	
	private String sourceSuffix; // 文件后缀名
	
	private Double sourceSize; // 文件大小
	
	private String sourceOldName; // 文件原名称
	
	private String sourceName; // 文件名称uuid
	
	private Date sourceCreatetime; // 文件创建时间
	
	private long sourceUserid; // 文件上传者userid
	
	private String sourceDuration; // 文件时长(视频和音频)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceSuffix() {
		return sourceSuffix;
	}

	public void setSourceSuffix(String sourceSuffix) {
		this.sourceSuffix = sourceSuffix;
	}

	public Double getSourceSize() {
		return sourceSize;
	}

	public void setSourceSize(Double sourceSize) {
		this.sourceSize = sourceSize;
	}

	public String getSourceOldName() {
		return sourceOldName;
	}

	public void setSourceOldName(String sourceOldName) {
		this.sourceOldName = sourceOldName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Date getSourceCreatetime() {
		return sourceCreatetime;
	}

	public void setSourceCreatetime(Date sourceCreatetime) {
		this.sourceCreatetime = sourceCreatetime;
	}

	public long getSourceUserid() {
		return sourceUserid;
	}

	public void setSourceUserid(long sourceUserid) {
		this.sourceUserid = sourceUserid;
	}

	public String getSourceDuration() {
		return sourceDuration;
	}

	public void setSourceDuration(String sourceDuration) {
		this.sourceDuration = sourceDuration;
	}
}
