package com.ex.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CustomClientRegistrationRepository {
	
	private final SocialClientRegistration socialClientRegistration;
	
	//InMemoryClientRegistrationRepository 클래스를 사용하여 인메모리 방식으로 관리를 진행한다.
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(socialClientRegistration.naverClientRegistration(), socialClientRegistration.googleClientRegistration());
	}
	
}
