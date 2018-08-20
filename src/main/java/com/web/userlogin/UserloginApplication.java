package com.web.userlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages={
        "com.web.userlogin", "com.web.userlogin.validator"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@Configuration
@ComponentScan(basePackages="com")
@EnableJpaRepositories(basePackages="com")
@EnableTransactionManagement
@EntityScan(basePackages="com")
public class UserloginApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UserloginApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(UserloginApplication.class, args);
	}
}
