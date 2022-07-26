package me.whiteship.demospringsecurityform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAsync
@SpringBootApplication
public class DemoSpringSecurityFormApplication {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
//		 NoOp을 권장하지 않는다. 이전에 인증은 평문으로 되어있었지만시큐리티 버전을 올리면 안된다.
//		return NoOpPasswordEncoder.getInstance();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringSecurityFormApplication.class, args);
	}

}
