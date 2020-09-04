package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.SiteUser;
import com.example.demo.repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
// Service このクラスがサービス層（業務ロジック）であることを示す
public class UserDetailsServiceImpl implements UserDetailsService {
	private final SiteUserRepository userRepository;
	
	public SiteUser findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	// ↓ UserDetailsServiceインターフェースは、ユーザを特定するために使用され。このインターフェースには、実装が必要なloadUserByUsername()というメソッドがある。
	// loadUserByUsername()メソッドの戻りつで、見つかったユーザーを返す
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SiteUser user = userRepository.findByUsername(username);
//		Long id = user.getId();
//		SiteUser userid = userRepository.findById(id);
		if (user == null) {
			throw new UsernameNotFoundException(username + " not found");
		}
		return createUserDetails(user);
	}
	
	public User createUserDetails(SiteUser user) {
		// GrantedAuthority（付与された権限）は、「ROLE_ADMIN」や「ROLE_USER」など、頭に「ROLE_」を付けた文字列を渡す
		Set<GrantedAuthority> grantedAuthories = new HashSet<>();
		grantedAuthories.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		return new User(user.getUsername(), user.getPassword(), grantedAuthories);
	}
}
