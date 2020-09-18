package com.example.demo.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// 追加
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.LoginUserDetails;
import com.example.demo.model.SiteUser;
import com.example.demo.repository.SiteUserRepository;
import com.example.demo.util.Role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class SecurityController {
	
	private final SiteUserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String showList(Authentication loginUser, Model model) {
		// Authentication 認証済みのユーザー情報を取得する
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());
		return "user";
	}
	
	@GetMapping("/show")
//	public String showUser(Model model, Principal principal) {
//		Authentication authentication = (Authentication)principal;
//		LoginUserDetails user = (LoginUserDetails)authentication.getPrincipal();
//		log.info("user:" + user.toString());
//		LoginUserDetails user2 = (LoginUserDetails)SecurityContextHolder
//				.getContext()
//				.getAuthentication()
//				.getPrincipal();
//		log.info("user2:"+user2.toString());
//	public String showUser(ModelMap model) {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = ((UserDetails)principal).getUsername();
//		Long id = ((UserDetails)principal).getId();
//		model.addAttribute("username", username);
//		model.addAttribute("id", id);
	public String showUser(Authentication loginUser, Model model) {
		UserDetails siteUser = (UserDetails)loginUser.getPrincipal();
		log.info(siteUser.toString());
		siteUser.toString();
		model.addAttribute("user", siteUser.toString());
		model.addAttribute("username", siteUser.getUsername());
//		model.addAttribute("email", siteUser.getSiteUser());
		return "show";
		
	}
	
	@GetMapping("/admin/list")
	public String showAdminList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "list";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("user") SiteUser user) {
		return "register";
	}
	
	@PostMapping("/register")
	public String process(@Validated @ModelAttribute("user") SiteUser user, BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (user.isAdmin()) {
			user.setRole(Role.ADMIN.name());
		} else {
			user.setRole(Role.USER.name());
		}
		userRepository.save(user);
		
		return "redirect:/login?register";
	}
	
	@GetMapping("/edit/{id}")
	public String edituser(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.findById(id));
		return "edituser";
	}
	
	@PutMapping("/userupdate")
	public String userupdate(@Validated @ModelAttribute("user") SiteUser user, BindingResult result) {
		if (result.hasErrors()) {
			return "edituser";
		}
		if (user.isAdmin()) {
			user.setRole(Role.ADMIN.name());
		} else {
			user.setRole(Role.USER.name());
		}
		userRepository.save(user);
		return "redirect:/admin/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteSiteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return "redirect:/admin/list";
	}

}
