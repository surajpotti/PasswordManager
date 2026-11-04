package com.Application.passwordManager;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.Application.JMXExposer.PasswordMBeanFactory;

@SpringBootApplication(scanBasePackages = "com.Application")
public class PasswordManagerApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(PasswordManagerApplication.class, args);
		PasswordMBeanFactory passInstance = context.getBean(PasswordMBeanFactory.class);
		passInstance.registerPackage("com.Application");
		
	}

}
