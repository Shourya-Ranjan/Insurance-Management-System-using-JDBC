package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    
    public static Connection getConnection() {

        if (connection == null) {
            try {
                // Load the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("===================================================");
                System.out.println("Driver is loading!");
                System.out.println("Driver is loaded successfully!!");

                // Get the connection string from the property file
                String connectionString = PropertyUtil.getPropertyString();

                // Establish the connection("Most Important")
                connection = DriverManager.getConnection(connectionString); //Passes the connString from propertiesUtil File to driverManager.
                System.out.println("Database ("+PropertyUtil.dbName+") is connected Successfully!!\n");


            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return connection;
    }
}
