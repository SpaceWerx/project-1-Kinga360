package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.ConnectionEvent;

public class ConnectionFactoryUtility {
	
    private static Connection instance;
    
    private ConnectionFactoryUtility() {
        super();
    }
    
    public static Connection getInstance() {
        if(instance == null) {
            instance = (Connection) new ConnectionEvent(null);
        }

        return instance;
    }
    
    public static Connection getConnection() throws SQLException 
    {
    	try 
    	{
    		Class.forName("org.postgresql.Driver");
    	}
    	catch(ClassNotFoundException e) 
    	{
    		System.out.println("CLASS WAS NOT FOUND");
    		e.printStackTrace();
    	}
    	
    	String url = "jdbc:postgresql://project-1.c298uwttvwjb.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=project1";
    	
    	String username = "postgres";
    	String password = "password";
    	
    	return DriverManager.getConnection(url, username, password);
    	
    }
        


}
