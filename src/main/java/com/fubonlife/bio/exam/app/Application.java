package com.fubonlife.bio.exam.app;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fubonlife.bio.exam.config.WebSecurityConfig;


// Spring Data MongoDB
@EnableMongoRepositories("com.fubonlife.bio.mg.repository.mongo")

// Spring Data JPA for MySQL
//@EnableJpaRepositories(basePackages = "com.fubonlife.bio.mg.repository.jpa")
//@EntityScan("com.fubonlife.bio.entity.jpa") 

// Spring Security and Session Mongo
@Import({ WebSecurityConfig.class})

@PropertySource("classpath:config-${spring.profiles.active:local}.properties")
@ComponentScan(basePackages = { "com.fubonlife.bio.mg.controller", "com.fubonlife.bio.mg.service" })
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}

	}

}
