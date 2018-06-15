package com.fraccaroli.springtab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringtabApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringtabApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
