package com.Application.SessionManager;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;


@Component
public class SignInManager {
	

	// Spring pulls individual values straight out of your custom properties file
    @Value("${password-manager.userName}")
    private String configUsername;

    @Value("${password-manager.password}") // :0 provides a default fallback if missing
    private String configPassword;
    
    
    
   public SignInManager() {
		super();
	}

   @PostConstruct
   public void init() {

	   	System.out.println("The init method in the SignInManager class is called.");	

	   	System.out.println("At init, the Username: " + this.configUsername + ", Password: " + this.configPassword);	
   }
	
   

    /**
	* @return the configUsername
	*/
	public String getConfigUsername() {
		return configUsername;
	}

   /**
    * @param configUsername the configUsername to set
    */
   public void setConfigUsername(String configUsername) {
	this.configUsername = configUsername;
   }

   /**
    * @return the configPassword
    */
   public String getConfigPassword() {
	return configPassword;
   }

   /**
    * @param configPassword the configPassword to set
    */
   public void setConfigPassword(String configPassword) {
	this.configPassword = configPassword;
   }

   public boolean signIn(String username, String password) {
	   
	   if(escapeSpecialCharacters(username)!=null || escapeSpecialCharacters(password)!=null) {
		   if(escapeSpecialCharacters(this.getConfigUsername()).equals(escapeSpecialCharacters(username)) && 
				   escapeSpecialCharacters(this.getConfigPassword()).equals(escapeSpecialCharacters(password))) {
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
    

}
