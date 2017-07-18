package com.huike.base.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 安全用户实体
 * 
 * @author shadow
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractUser implements UserDetails {

	// ===========以下属性为安全架构属性===========

	private String username; // 账号

	private String password; // 密码

	private boolean accountNonExpired = true; // 账号是否过期

	private boolean accountNonLocked = true; // 账号是否锁定

	private boolean credentialsNonExpired = true;

	private boolean enabled = true; // 是否可用

	private Collection<? extends GrantedAuthority> authorities; // 权限集合

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	// 获取当前登录用户详细信息必须重写hashCode和equals方法
	public int hashCode() {
		return this.getUsername().hashCode();
	}

	public boolean equals(Object object) {
		boolean flag = false;
		if (object instanceof UserDetails) {
			UserDetails user = (UserDetails) object;
			if (user.getUsername().equals(this.getUsername()))
				flag = true;
		}
		return flag;
	}

}