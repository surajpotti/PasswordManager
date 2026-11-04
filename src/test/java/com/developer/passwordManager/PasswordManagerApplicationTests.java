package com.developer.passwordManager;

import java.util.Date;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.Application.FeatureClasses.Password;
import com.Application.FeatureClasses.PasswordDirectory;
import com.Application.POJO.PasswordPOJO;
import com.Application.passwordManager.PasswordManagerApplication;

@SpringBootTest(classes = PasswordManagerApplication.class) 
class PasswordManagerApplicationTests {
	
	private Scanner scanner = new Scanner(System.in);
	private Password testObj = new Password();
	private PasswordDirectory testObj2 = new PasswordDirectory();
	
	@Test
	void testcheckifValidEmailAddress() {
		String email = scanner.nextLine();
		System.out.println("Validity of email address:" + testObj2.checkifValidEmailAddress(email));
	}

	@Test
	void testPasswordAlreadyExists() {
		PasswordPOJO newObj = new PasswordPOJO();
		newObj.setDateOfCreation(new Date().toString());
		newObj.setApplicationName(scanner.nextLine());
		newObj.setEmailAddress(scanner.nextLine());
		newObj.setApplicationType(scanner.nextLine());
		newObj.setPassword(scanner.nextLine());
		System.out.println("Does entry already exists: " + testObj2.checkforexistingEntry(newObj));
	}
	
	@Test
	void testupdatePasswordEntry() {
		System.out.println("Does entry already exists: " + testObj.updatePasswordEntry("Amazon","fakenotsuraj@gmail.com",""));
	}
}
