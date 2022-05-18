package Utilities;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryUtility {
	 private static ConnectionFactoryUtility instance;
/*
 * The link connection factory connection link is responsible for leveraging a specific database driver to obtain an instance in the file
 * Typically this is accomplished via the use of the java.util.Driver class
 * @throws SQLException
 */
	 private ConnectionFactoryUtility() {
	        super();
	    }
	 /**
	     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
	     * <p>It is invoked via:</p>
	     *
	     * {@code ConnectionFactory.getInstance()}
	     */
	    public static ConnectionFactoryUtility getInstance() {
	        if(instance == null) {
	            instance = new ConnectionFactoryUtility();
	        }

	        return instance;
	    }
	public static Connection getConnection() throws SQLException {
		// For compatibility with other technologies, we use to register our Postgresql driver
		//This process makes the application aware of what database Driver (SQL dialect) we're using
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class wasn't found");
			e.printStackTrace();
		}
		//The url to the database schema
		String url = "project-1.c298uwttvwjb.us-east-1.rds.amazonaws.com";
		//Postgres username
		String userName = "postgres";
		//Postgress password
		String password = "password";
		return null;
	}

}
