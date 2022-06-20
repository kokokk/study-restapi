package com.example.myrestfulservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class MyRestfulServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MyRestfulServiceApplication.class, args);

		for (String str : context.getBeanDefinitionNames()) {
			log.debug(str);
		}
	}

}
