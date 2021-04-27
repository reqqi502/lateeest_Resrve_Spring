package com.basmaonline.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;


@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABETNUMERIQUE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generateUserId(int length) {
		
		StringBuilder returnValue = new StringBuilder(length);
		
		for(int i = 0; i < length ; i++) {
			returnValue.append(ALPHABETNUMERIQUE.charAt(RANDOM.nextInt(ALPHABETNUMERIQUE.length())));
		}
		return new String(returnValue);
		
	}
	
	
	
}
