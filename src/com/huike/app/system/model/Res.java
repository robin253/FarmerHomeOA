package com.huike.app.system.model;

import java.util.List;

import com.huike.base.model.AbstractRes;

/**
 * 资源信息实体
 * @author LiCheng
 *
 */
@SuppressWarnings("serial")
public class Res extends AbstractRes{
	private List<Role> roles; // 权限集合

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
