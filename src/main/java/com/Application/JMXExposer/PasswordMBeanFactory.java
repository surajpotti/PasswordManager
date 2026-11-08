package com.Application.JMXExposer;


import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;
import javax.management.JMException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.StandardMBean;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import com.Application.FeatureClasses.Password;

@Service
public class PasswordMBeanFactory{
	
	/*
	 * Method to register the required beans to start the application.
	 */
	public void registerMBean() throws JMException{
		try {
			ObjectName objectName = new ObjectName("com.Application.FeatureClasses:name=Password");
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			server.registerMBean(new Password(), objectName);
		}catch(MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException e) {
			throw e;
		}
		
		/*
		 * To keep the application running.
		 */
		while(true) {
			
		}
         
    }
	

    /**
     * Scans the specified package and ALL its subpackages recursively, 
     * registering anything conforming to JMX standards or custom annotations.
     */
    @SuppressWarnings("unchecked")
	public void registerPackage(String basePackageName) {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            List<Class<?>> classes = new ArrayList<>();
            
            // Locate the root package directory
            String path = basePackageName.replace('.', '/');
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resource = classLoader.getResource(path);
            
            if (resource != null) {
                File rootDirectory = new File(resource.getFile());
                // Find all classes recursively
                findClassesRecursively(rootDirectory, basePackageName, classes);
            }

            // Register discovered classes based on their metadata patterns
            for (Class<?> clazz : classes) {
                if (clazz.isInterface()) continue;

                Object mbeanInstance;
                ObjectName objectName;
                String typeName = clazz.getSimpleName();
                String classPackage = clazz.getPackageName(); // Dynamic naming based on its actual subpackage

                // Pattern A: Detect explicit Spring @ManagedResource annotation
                if (clazz.isAnnotationPresent(org.springframework.jmx.export.annotation.ManagedResource.class)) {
                    org.springframework.jmx.export.annotation.ManagedResource anno = 
                        clazz.getAnnotation(org.springframework.jmx.export.annotation.ManagedResource.class);
                    
                    mbeanInstance = clazz.getDeclaredConstructor().newInstance();
                    String nameStr = anno.objectName().isEmpty() ? classPackage + ":type=" + typeName : anno.objectName();
                    objectName = new ObjectName(nameStr);
                    
                    registerOnServer(mbs, mbeanInstance, objectName);
                    System.out.println("MBean registered for the class: " + objectName);
                    continue;
                }

                // Pattern B: Detect MXBean Compliance
                Class<?> mxbeanInterface = findInterfaceMatching(clazz, "MXBean");
                if (mxbeanInterface != null) {
                    mbeanInstance = new StandardMBean(clazz.getDeclaredConstructor().newInstance(), (Class) mxbeanInterface, true);
                    objectName = new ObjectName(classPackage + ":type=" + typeName);
                    registerOnServer(mbs, mbeanInstance, objectName);

                    System.out.println("MBean registered for the class: " + objectName);
                    continue;
                }

                // Pattern C: Detect Standard MBean Compliance
                Class<?> mbeanInterface = findInterfaceMatching(clazz, "MBean");
                if (mbeanInterface != null) {
                    mbeanInstance = clazz.getDeclaredConstructor().newInstance();
                    objectName = new ObjectName(classPackage + ":type=" + typeName);
                    registerOnServer(mbs, mbeanInstance, objectName);

                    System.out.println("MBean registered for the class: " + objectName);
                }
                
            }
        } catch (Exception e) {
            System.err.println("Recursive package JMX registration failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Traverses directories recursively to build a complete list of compiled classes.
     */
    private static void findClassesRecursively(File directory, String packageName, List<Class<?>> classes) throws Exception {
        if (!directory.exists()) return;

        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                // If it's a folder, enter it and append its name to the package path string
                findClassesRecursively(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                // Convert file name back to full class canonical name
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }
    }

    private static void registerOnServer(MBeanServer mbs, Object instance, ObjectName name) throws Exception {
        if (!mbs.isRegistered(name)) {
            mbs.registerMBean(instance, name);
            System.out.println("🚀 Registered JMX MBean: " + name);
        }
    }

    private static Class<?> findInterfaceMatching(Class<?> clazz, String suffix) {
        for (Class<?> iface : clazz.getInterfaces()) {
            if (iface.getSimpleName().endsWith(suffix)) {
                return iface;
            }
        }
        return null;
    }
}

