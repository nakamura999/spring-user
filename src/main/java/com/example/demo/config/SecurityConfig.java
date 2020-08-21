package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
// ↑Spring Securityの機能を有効にする
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// パスワードの暗号化にbcryptを使用
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// セキュリティ設定を無視(ignoring)するパスを指定
		// css・js・imgなどの静的ソースを指定
		web.ignoring().antMatchers("/css/**", "/webjars/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// ログインをアクセス可能にする
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				// ログイン時のURLを指定
				.loginPage("/login")
				// 認証後にリダイレクトする場所を指定
				.defaultSuccessUrl("/")
				.and()
			// ログアウトの設定
			.logout()
				// ログアウト時のURL
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			// Remember-Meの認証を許可する。ブラウザを閉じてもログインしたままにできる
			.rememberMe();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			// ユーザー名 admin or user
			// パスワード両方ともpassword
			.withUser("admin")
				.password(passwordEncoder().encode("password"))
				.authorities("ROLE_ADMIN")
				.and()
			.withUser("user")
				.password(passwordEncoder().encode("password"))
				.authorities("ROLE_USER");
	}
}
