package com.huike.base.tools.upload;

public class ProgressBarDto {
	
	private String fileName;
	private Long size;
	private String rate;
	private Long currentSize;

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(Long currentSize) {
		this.currentSize = currentSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

    

}
