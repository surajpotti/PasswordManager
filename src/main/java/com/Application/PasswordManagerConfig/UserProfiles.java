package com.Application.PasswordManagerConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import jakarta.annotation.PostConstruct;


@Configuration
@ConfigurationProperties(prefix = "user")
@PropertySource("classpath:userprofile.properties") 
public class UserProfiles {
	
	
	
	
	private static final String FILE_PATH = "src/main/resources/userprofile.properties";
    private static final String PROPERTY_SOURCE_NAME = "URL [file:" + FILE_PATH + "]";

    private  ConfigurableEnvironment environment = new StandardEnvironment();

    // Spring automatically populates this map at startup from users.properties
    private Map<String, ProfilePOJO> accounts = new ConcurrentHashMap<>();

    public UserProfiles() {
    	
    }

    // Required Getters & Setters for @ConfigurationProperties binding
    public Map<String, ProfilePOJO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, ProfilePOJO> accounts) {
        this.accounts = accounts;
    }

    // READ (All Users)
    public Map<String, ProfilePOJO> getAllUsers() {
        return this.accounts;
    }

    // READ (Single User)
    public ProfilePOJO getUser(String name) {
        return this.accounts.get(name);
    }
    
    @PostConstruct
    public void init() {
    	System.out.println("Loading the user accounts: " + getAllUsers());
    }
    
    
    // CREATE or UPDATE
    public synchronized boolean saveOrUpdateUser(String name,String username, String password) {
    
    
		try {
			File file = new File(FILE_PATH);
	        if (!file.exists()) {
	            file.getParentFile().mkdirs();
	            file.createNewFile();
	        }
			Parameters params = new Parameters();
		    FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
		        new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
		            .configure(params.fileBased().setFile(file));

		    // Loads existing file while preserving comments, blank lines, and key order
		    PropertiesConfiguration config = builder.getConfiguration();

		    // Adds new keys at the bottom or updates existing keys in-place
		    config.setProperty("user.accounts." + username + ".name", name);
		    config.setProperty("user.accounts." + username + ".username", username);
		    config.setProperty("user.accounts." + username +".password", password);

		    // Saves cleanly without overwriting unrelated entries
		    builder.save();
	
		    System.out.println("The username here: " + username);
	        // Update local map directly
	        ProfilePOJO detail = new ProfilePOJO();
	        detail.setUserName(username);
	        detail.setPassword(password);
	        System.out.println("The username here: " + username);
	        this.accounts.put(username, detail);
	        // Sync Spring's @PropertySource in ConfigurableEnvironment
	        refreshEnvironment(loadFromDisk());
	        return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
    	
    	
    }

	// DELETE
    public synchronized boolean deleteUser(String id) throws IOException {
        Properties props = loadFromDisk();
        Object removedName = props.remove("user.accounts." + id + ".name");
        Object removedRole = props.remove("user.accounts." + id + ".role");

        if (removedName != null || removedRole != null) {
            saveToDisk(props);
            this.accounts.remove(id);
            refreshEnvironment(props);
            return true;
        }
        return false;
    }

    private void refreshEnvironment(Properties updatedProps) {
        PropertiesPropertySource newSource = new PropertiesPropertySource(PROPERTY_SOURCE_NAME, updatedProps);
        if (environment.getPropertySources().contains(PROPERTY_SOURCE_NAME)) {
            environment.getPropertySources().replace(PROPERTY_SOURCE_NAME, newSource);
        } else {
            environment.getPropertySources().addFirst(newSource);
        }
    }

    private Properties loadFromDisk() throws IOException {
        Properties props = new Properties();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file)) {
                props.load(in);
            }
        }
        return props;
    }

    private void saveToDisk(Properties props) throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        try (FileOutputStream out = new FileOutputStream(file)) {
            props.store(out, "Updated via Spring Boot");
        }
    }


}
