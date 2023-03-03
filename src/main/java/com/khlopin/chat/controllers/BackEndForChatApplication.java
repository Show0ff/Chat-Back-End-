package com.khlopin.chat.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BackEndForChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndForChatApplication.class, args);
	}

}
