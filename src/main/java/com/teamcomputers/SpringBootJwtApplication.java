package com.teamcomputers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.teamcomputers.webhookMetaService.IncomingDataService;

@SpringBootApplication
public class SpringBootJwtApplication {

	
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootJwtApplication.class, args);
//		String str=IncomingDataService.token();
//		System.out.println(str);
		
	}

	
	
}
