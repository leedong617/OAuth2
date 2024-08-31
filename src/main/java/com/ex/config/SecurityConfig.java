package com.ex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer.UserInfoEndpointConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ex.oauth2.CustomClientRegistrationRepository;
import com.ex.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomOAuth2UserService customOAuth2UserService;
	private final CustomClientRegistrationRepository customClientRegistrationRepository;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable());

		http.formLogin((login) -> login.disable());
		
		http.httpBasic((basic) -> basic.disable());
		
		
		//customOAuth2UserService 등록
		http.oauth2Login((oauth2LoginConfigurer) -> oauth2LoginConfigurer
				.loginPage("/login")
				.clientRegistrationRepository(customClientRegistrationRepository.clientRegistrationRepository())
				//OAuth2 로그인 과정에서 사용자 정보를 가져오는 엔드포인트를 설정
				.userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
						// 사용자 정보를 가져오는 데 사용할 OAuth2UserService를 설정
						// 여기서 customOAuth2UserService는 커스터마이즈된 OAuth2UserService 객체
						.userService(customOAuth2UserService)));
		
		http.authorizeHttpRequests((auth) -> auth
		                .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
		                .anyRequest().authenticated());
		
		return http.build();
	}
	
}
