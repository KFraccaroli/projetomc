package com.fraccaroli.springtab;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fraccaroli.springtab.services.S3Service;

@SpringBootApplication
public class SpringtabApplication implements CommandLineRunner{
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringtabApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		s3Service.uploadFile("C:\\Users\\kfrac\\OneDrive\\Documentos\\Projetos\\img\\cat1.jpg");
		
	}
}
