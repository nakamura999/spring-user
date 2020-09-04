package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//自動生成したエンティティです。
import com.example.demo.model.SiteUser;

public class LoginUserDetails implements UserDetails {

	private static final long serialVersionUID = 713418355854042116L;
	//	private static final long serialVersionUID = 1L;
	private final SiteUser siteUser;	
    //権限情報を保存します
	private final Collection<GrantedAuthority> authorities;
	
    public LoginUserDetails(
            SiteUser siteUser, Collection<GrantedAuthority> authorities
            ){
            this.siteUser = siteUser;
            this.authorities = authorities;  
    }
    
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return siteUser.getPassword();
	}

	public Long getId() {
		return siteUser.getId();
	}
	
	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return siteUser.getUsername();
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
		return true;
	}

    public SiteUser getSiteUser() {
        return siteUser;
    }

}
