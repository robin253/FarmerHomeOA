package com.huike.base.tools.sms;

import org.apache.log4j.Logger;

public class ThreadPoolManager {

	/**
	 * 当前活动的线程
	 */
	private int activeCount;
	
	/**
	 * 完成的任务数
	 */
	private long completeTaskCount;
	
	/**
	 * 池出现过最大数
	 */
	private int largestPoolSize;
	
	/**
	 *  当前线程池大小
	 */
	private int poolSize;
	/**
	 *  设置的最大线程池大小
	 */
	private int maximumPoolSize;
	
	/**
	 * 当前任务数
	 */
	private long taskCount;
	
	/**
	 * 队列大小
	 */
	private int queueSize;
	
	
	private static ThreadPoolManager tpm = new ThreadPoolManager();
	
	private Logger log = Logger.getLogger(ThreadPoolManager.class);
	
	private ThreadPoolManager(){
		
	}
	
	public static ThreadPoolManager getTPM(){
		return tpm;
	}

	public void init(int activeCount, long completeTaskCount,
			int largestPoolSize, int poolSize, int maximumPoolSize,
			long taskCount, int queueSize) {
		this.activeCount = activeCount;
		this.completeTaskCount = completeTaskCount;
		this.largestPoolSize = largestPoolSize;
		this.poolSize = poolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.taskCount = taskCount;
		this.queueSize = queueSize;
		log.info("当前活动线程数：" + this.activeCount + ",当前任务数：" + this.taskCount
				+ ",池大小：" + this.poolSize + ",最大池：" + this.maximumPoolSize
				+ ",队列大小：" + this.queueSize);
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public long getCompleteTaskCount() {
		return completeTaskCount;
	}

	public void setCompleteTaskCount(long completeTaskCount) {
		this.completeTaskCount = completeTaskCount;
	}

	public int getLargestPoolSize() {
		return largestPoolSize;
	}

	public void setLargestPoolSize(int largestPoolSize) {
		this.largestPoolSize = largestPoolSize;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public long getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(long taskCount) {
		this.taskCount = taskCount;
	}

	public int getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
}
