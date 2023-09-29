package com.security.basicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class BasicauthApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(BasicauthApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new BasicauthInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public FilterRegistrationBean<BasicauthFilter> basicAuthFilter() {
		FilterRegistrationBean<BasicauthFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new BasicauthFilter());
		registrationBean.addUrlPatterns("/rest/*");
		return registrationBean;
	}
}