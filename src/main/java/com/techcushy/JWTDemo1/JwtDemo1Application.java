package com.techcushy.JWTDemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemo1Application.class, args);
	}


}
