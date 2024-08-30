package com.ex.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User{
	
	private final OAuth2Response oAuth2Response;
	
	private final String role;

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return role;
			}
		});
		return authorities;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return oAuth2Response.getName();
	}
	// 임의의 username 만들기
	public String getUsername() {
		return oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
	}

}
