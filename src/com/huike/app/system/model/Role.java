package com.huike.app.system.model;
import java.util.List;

import com.huike.base.model.AbstractRole;

/**
 * 角色实体
 * @author LiCheng
 *
 */
@SuppressWarnings("serial")
public class Role extends AbstractRole{
	private List<Res> ress; // 权限集合
	
//	private List<User> users; // 权限集合

	public List<Res> getRess() {
		return ress;
	}

	public void setRess(List<Res> ress) {
		this.ress = ress;
	}

	/*public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}*/

}
