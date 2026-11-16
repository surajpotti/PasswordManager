package com.Application.SessionManager;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	// Spring pulls individual values straight out of your custom properties file
    @Value("${password-manager.userName}")
    private String configUsername;

    @Value("${password-manager.password}") // :0 provides a default fallback if missing
    private String configPassword;
    
    @Value("${password-manager.profileCount}")
    private int profileCount;
    
    
    
    
   public SignInManager() {
		super();
	}

   @PostConstruct
   public void init() {

	   	System.out.println("The init method in the SignInManager class is called.");	

	   	System.out.println("At init, the Username: " + this.configUsername + ", Password: " + this.configPassword);	
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
    
   
   public boolean userSignIn(String username, String password) {
	   
	   if(escapeSpecialCharacters(username)!=null || escapeSpecialCharacters(password)!=null) {
		   if(escapeSpecialCharacters(appConfigObj.getUserName()).equals(escapeSpecialCharacters(username)) && 
				   escapeSpecialCharacters(appConfigObj.getPassword()).equals(escapeSpecialCharacters(password))) {
			   System.out.print("Returned true");
			   return true;
		   }
	   }
	return false;
	   
   }
   
   
   public boolean createNewUser(String name, String username, String password) {
	   if(!userProfileObj.getAllUsers().containsKey(name)) {
		   try {
			userProfileObj.saveOrUpdateUser(name, username, password);
			return true;
		   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	   }
	   return false;
   }
   
   public boolean checkRunCounter() {
	   if(appConfigObj.getProfileCount() > 0) {
		   return true;
	   }
	   return false;
   }

}
