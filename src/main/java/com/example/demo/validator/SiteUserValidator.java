package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.SiteUserRepository;

public class SiteUserValidator implements ConstraintValidator<LoginSt, String> {
	// ConstraintValidator 自作バリデーションを作成する場合使用
	private final SiteUserRepository userRepository;
	
	public SiteUserValidator() {
		this.userRepository = null;
	}
	
	@Autowired
	public SiteUserValidator(SiteUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// isValid チェックエラーの場合、return false を返す
		return userRepository == null || userRepository.findByUsername(value) ==  null;
	}
}
