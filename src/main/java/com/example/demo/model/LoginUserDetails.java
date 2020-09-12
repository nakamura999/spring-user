package com.example.demo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.SiteUser;

public class LoginUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2312397170822341238L;
	public SiteUser siteuser;
	private Collection<GrantedAuthority> authorities;
	
	public LoginUserDetails(SiteUser siteuser,Collection<GrantedAuthority> authorities){
		this.siteuser = siteuser;
		this.authorities = authorities;		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return siteuser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return siteuser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return siteuser.isEnabled();
	}

	public SiteUser getSiteUser() {
		return siteuser;
	}

}
