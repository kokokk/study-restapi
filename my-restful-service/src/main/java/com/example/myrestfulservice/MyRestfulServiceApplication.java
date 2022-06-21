package com.example.myrestfulservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@Slf4j
public class MyRestfulServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MyRestfulServiceApplication.class, args);

		for (String str : context.getBeanDefinitionNames()) {
			log.debug(str);
		}
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.KOREA);
		return sessionLocaleResolver;
	}

}
