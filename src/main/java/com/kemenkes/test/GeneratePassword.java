package com.kemenkes.test;

import java.util.UUID;

public class GeneratePassword {

	public static void main(String[] args) {
		System.out.println(com.kemenkes.epu.common.util.SpringSecurityUtil.encodePassword("password"));
		
		String uuid=UUID.randomUUID().toString();
		System.out.println(uuid.length());
	}

}
