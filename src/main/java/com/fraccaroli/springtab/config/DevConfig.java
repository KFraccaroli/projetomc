package com.fraccaroli.springtab.config;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fraccaroli.springtab.services.DBService;
import com.fraccaroli.springtab.services.EmailService;
import com.fraccaroli.springtab.services.MockEmailService;
import com.fraccaroli.springtab.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DevConfig.class);
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy; 
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
