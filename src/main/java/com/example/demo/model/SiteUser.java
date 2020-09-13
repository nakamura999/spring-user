package com.example.demo.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import com.example.demo.validator.LoginSt;

@Getter
@Setter
@Entity
public class SiteUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 2, max=20)
	@LoginSt // 自作バリデーション追加
	private String username;
	
	@Size(min = 4, max = 255)
	private String password;
	
	@NotBlank
	@Email
	private String email;
	
	private int gender;
	private boolean admin;
	private String role;
	private boolean active = true;
	
	@OneToMany(mappedBy = "siteuser")
	private List<Product> products;

	
	//追加
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
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
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
//	private Collection<GrantedAuthority> authorities;
	
//	public SiteUser(Long id, String username, String email, String password,
//			Collection<? extends GrantedAuthority> authorities) {
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.authorities = (Collection<GrantedAuthority>) authorities;
//	}
//	public static UserDetails build(SiteUser user) {
//		List<GrantedAuthority> authorities = user.getRole()
//				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
//				.collect(Collectors.toList());
//
//		return new SiteUser(
//				user.getId(), 
//				user.getUsername(), 
//				user.getEmail(),
//				user.getPassword(), 
//				authorities);
//	}
}
