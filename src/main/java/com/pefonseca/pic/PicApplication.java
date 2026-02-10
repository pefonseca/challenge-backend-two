package com.pefonseca.pic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicApplication.class, args);
	}

}
