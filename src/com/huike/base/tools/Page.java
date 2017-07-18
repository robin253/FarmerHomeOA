package com.huike.base.tools;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据存储对象
 * 
 * @author shadow
 * @email 124010356@qq.com
 * @create 2012.04.28
 */
@SuppressWarnings("serial")
public class Page<T> implements Serializable {

	private int show = 5; // 显示数
	private int result; // 页数
	private int total; // 记录数
	private int current; // 当前页
	private int offset = 1; // 索引值

	private List<T> list;

	public Page(int show, int current) {
		super();
		this.show = show;
		this.current = current;
	}

	public int getOffset() {
		if (current == 0) {
			int index = this.getResult();
			if (index > 0) {
				if (current < 1)
					current = 1;
				if (result != 0 && current > result)
					current = result;
			}
			if (index <= 0)
				current = 1;
		}
		offset = (current - 1) * show;
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getShow() {
		return show;
	}

	public int getResult() {
		if (total % show == 0)
			result = total / show;
		else
			result = total / show + 1;
		return result;
	}

	public int getTotal() {
		return total;
	}

	public int getCurrent() {
		return current;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setShow(int show) {
		this.show = show;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

}