package com.example.demo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
// 使える場所を指定。メソッドとフィールド
@Retention(RetentionPolicy.RUNTIME)
// 保持期間を指定　RUNTIME いつでも使用可能
@Constraint(validatedBy = SiteUserValidator.class)
public @interface LoginSt {
	String message() default "このユーザー名は、すでに使用されています";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
