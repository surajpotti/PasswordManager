package com.Application.SessionManager;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Application.PasswordManagerConfig.ApplicationConfiguration;
import com.Application.PasswordManagerConfig.UserProfiles;

import jakarta.annotation.PostConstruct;


@Component
public class SignInManager {
	

	@Autowired
	UserProfiles userProfileObj;
	
	
	@Autowired
	ApplicationConfiguration appConfigObj;
	
    
    
    
    
   public SignInManager() {
		super();
	}

   @PostConstruct
   public void init() {

	   	System.out.println("The init method in the SignInManager class is called.");	

	   	System.out.println("At init, the Username: " + appConfigObj.getUserName() + ", Password: " + appConfigObj.getPassword());	
   }
	

   public boolean adminSignIn(String username, String password) {
	   
	   if(escapeSpecialCharacters(username)!=null || escapeSpecialCharacters(password)!=null) {
		   if(escapeSpecialCharacters(appConfigObj.getUserName()).equals(escapeSpecialCharacters(username)) && 
				   escapeSpecialCharacters(appConfigObj.getPassword()).equals(escapeSpecialCharacters(password))) {
			   System.out.print("Returned true");
			   return true;
		   }
	   }
	return false;
	   
   }
   
   private String escapeSpecialCharacters(String data) {
       String escapedData = data.replaceAll("\"", "\"\"");
       if (data.contains(",") || data.contains("\"") || data.contains("\n")) {
           escapedData = "\"" + escapedData + "\"";
       }
       return escapedData;
   }
    
   
   public int userSignIn(String username, String password) {
	   
	   if(userProfileObj.getAllUsers().containsKey(username)) {
		   if(userProfileObj.getAccounts().get(username).getPassword().equals(password)) {
			   return 2;
		   }else {
			   return 3;
		   }
	   }else {
		   return 1;
	   }
	   
   }
   
 
   public String createNewUser(String name, String username, String password) {
	   if(!userProfileObj.getAllUsers().containsKey(name)) {
		   if(userProfileObj.saveOrUpdateUser(name, username, password)) {
			   appConfigObj.updatepProfileCounter();
		   	return "Profile created for the: " + name;
		   }
	   }
	   return "Could not create profile for : " + name;
   }
   
   public boolean checkProfileCounter() {
	   if(appConfigObj.getProfileCount() > 0) {
		   return true;
	   }
	   return false;
   }

}
