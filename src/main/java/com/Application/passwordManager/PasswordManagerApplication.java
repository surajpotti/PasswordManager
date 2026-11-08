package com.Application.passwordManager;



import java.awt.EventQueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.Application.FeatureClasses.Password;
import com.Application.GUI.PasswordManagerApp;
import com.Application.JMXExposer.PasswordMBeanFactory;

@SpringBootApplication(scanBasePackages = "com.Application")
public class PasswordManagerApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		ApplicationContext context = SpringApplication.run(PasswordManagerApplication.class, args);
		context.getBean(Password.class);
		//passInstance.registerPackage("com.Application");
		
		 // 3. Thread-safely launch the Swing GUI on the Event Dispatch Thread
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Ask Spring for the managed window instance (DO NOT use 'new MyMainWindow()')
                	context.getBean(PasswordManagerApp.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		
	}

}
