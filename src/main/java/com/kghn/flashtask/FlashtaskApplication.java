package com.kghn.flashtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class FlashtaskApplication {
	private static final Logger logger = LoggerFactory.getLogger(FlashtaskApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(FlashtaskApplication.class, args);
		logger.info("Application Started");
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
