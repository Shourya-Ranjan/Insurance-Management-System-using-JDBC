package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String dbName = "";
    

    public static String getPropertyString() {
        Properties properties = new Properties();

        String propertyFilePath = "C:\\Users\\shour\\eclipse-workspace\\InsuranceManagementSystem\\src\\util\\db.properties" ; // Specify the path to your property file

        try {
            FileInputStream input = new FileInputStream(propertyFilePath) ;
            properties.load(input);

            // Construct the connection string using properties
            // String file=properties.getProperty(propertyFilePath)
            String host = properties.getProperty("hostname");
             dbName = properties.getProperty("dbname");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String port = properties.getProperty("port");
            String connStr="jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + username + "&password=" + password;
            // System.out.println(connStr);
            return connStr;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!" +propertyFilePath);
        } catch (IOException e) {
            System.out.println("Error reading the property file");
            System.out.println(e.getMessage()); // Handle the exception appropriately
        }

        return null; // Return null if unable to read properties or construct connection string
    }
}