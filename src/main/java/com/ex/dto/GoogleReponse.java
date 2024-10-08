package com.ex.dto;

import java.util.Map;

public class GoogleReponse implements OAuth2Response{
	
	private final Map<String, Object> attribute;
	
	public GoogleReponse(Map<String, Object> attribute) {
		this.attribute = attribute;
	}
	
	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "google";
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return attribute.get("sub").toString();
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return attribute.get("email").toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return attribute.get("name").toString();
	}
}
