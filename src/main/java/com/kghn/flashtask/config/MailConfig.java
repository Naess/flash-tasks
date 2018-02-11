package com.kghn.flashtask.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {

	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSender mailSender = new JavaMailSenderImpl();
	    ((JavaMailSenderImpl) mailSender).setHost("smtp.gmail.com");
	    ((JavaMailSenderImpl) mailSender).setPort(587);
	     
	    ((JavaMailSenderImpl) mailSender).setUsername("my.gmail@gmail.com");
	    ((JavaMailSenderImpl) mailSender).setPassword("password");
	     
	    Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}
}