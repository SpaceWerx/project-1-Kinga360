package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Mock_Data.MockUserData;
import Models.Reimbursement;
import Models.Roles;
import Models.Status;
import Models.Type;
import Models.Users;
import Utilities.ConnectionFactory;


public class UserDAO {
	private static MockUserData mockData = new MockUserData();
	private static  ArrayList<Users> users = new ArrayList<Users>();
	public static void getData() {
		if (users.isEmpty()) {
		
		users = mockData.MockData();
		}
		try (Connection connection = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO er_users " + " (id, username, password, role) " 
					+ "values = (?,?,?, ?::role)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for (Users user : users) {
			preparedStatement.setInt(1, user.getID());
			preparedStatement.setObject(2, user.getUserName());
			preparedStatement.setObject(3, user.getPassword());
			preparedStatement.setObject(4, user.getRole());
			
			}
		} catch (SQLException e) {
			System.out.println("can not add users");
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "UserDAO []";
	}
	public static String getUserByUsername(String username){
		// Try-catch block to catch sql exception that can be thrown with connection.
		try (Connection connection = ConnectionFactory.getConnection()){
			//SQL statement prepared as a string.
			// In the instance, we are filtering reimbursements by an author (user) ID.
			String sql = "SELECT * FROM ers_users WHERE username =?";
			//Preparing the sql statement to be executed once we fill in the query parameters.
			PreparedStatement preparedStatement =  connection.prepareStatement(sql);
			//Filling the missing query value (?) with the method parameter (userID).
			preparedStatement.setString(1,username);
			// Building a sql result set from the execution of the query statement.
			ResultSet resultSet = preparedStatement.executeQuery();
			// Initializing a new Reimbursement array list  to house and return with the data from the database.
			ArrayList<Users> users = new ArrayList<Users>();
			users = getAllUsers();
			for(Users user : users) {
				if (username.equals(user.getUserName())) {
					return username;
				}
			}
			return null;
			// This while loop will continue to add reimbursements to the list
			// Until all the data from the result set has run out.
//			while (resultSet.next()) {
//				// Adding reimbursements to the list with the data extracted from the database.
//				user.setUserName(resultSet.getString("username"));
////				user.add(resultSet.getInt("id"), resultSet.getString("username")
////						,resultSet.getString("password"), Roles.valueOf(resultSet.getString("role")));
//			}
//			//return the list of reimbursements that have the matching author (user) id.
//			return user;
			
			
		}catch (SQLException e) {
			//catching the sql exception (this is a good place to utilize custom exception handling).
			System.out.println("Someting went wrong obtaining your list.");
			e.printStackTrace();
		}
		//fail safe if the try catch block does not run.
		return null;
	}
	public static Users getUserByUsernames(String username){
		// Try-catch block to catch sql exception that can be thrown with connection.
		try (Connection connection = ConnectionFactory.getConnection()){
			//SQL statement prepared as a string.
			// In the instance, we are filtering reimbursements by an author (user) ID.
			String sql = "SELECT * FROM ers_users WHERE username =?";
			//Preparing the sql statement to be executed once we fill in the query parameters.
			PreparedStatement preparedStatement =  connection.prepareStatement(sql);
			//Filling the missing query value (?) with the method parameter (userID).
			preparedStatement.setString(1,username);
			// Building a sql result set from the execution of the query statement.
			ResultSet resultSet = preparedStatement.executeQuery();
			// Initializing a new Reimbursement array list  to house and return with the data from the database.
			ArrayList<Users> users = new ArrayList<Users>();
			users = getAllUsers();
			for(Users user : users) {
				if (username.equals(user.getUserName())) {
					return user;
				}
			}
			return null;
			// This while loop will continue to add reimbursements to the list
			// Until all the data from the result set has run out.
//			while (resultSet.next()) {
//				// Adding reimbursements to the list with the data extracted from the database.
//				user.setUserName(resultSet.getString("username"));
////				user.add(resultSet.getInt("id"), resultSet.getString("username")
////						,resultSet.getString("password"), Roles.valueOf(resultSet.getString("role")));
//			}
//			//return the list of reimbursements that have the matching author (user) id.
//			return user;
			
			
		}catch (SQLException e) {
			//catching the sql exception (this is a good place to utilize custom exception handling).
			System.out.println("Someting went wrong obtaining your list.");
			e.printStackTrace();
		}
		//fail safe if the try catch block does not run.
		return null;
	}
	public Users getUserById(int id) {
		// Try-catch block to catch sql exception that can be thrown with connection.
		try (Connection connection = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM ers_users where id = ?";
			//When we need parameters we need to use a PREPARED statement, as opposed to a statement(seen above).
			PreparedStatement preparedStatement =  connection.prepareStatement(sql); //prepared statement as opposed to created statement.
			//insert the methods arguments(int id) as the first and (only) variable in the sql query.
			preparedStatement.setInt(1, id); //the 1 is referring to the first parameter(?) found in our sql string
			ResultSet resultSet = preparedStatement.executeQuery();
			// If there are results in the result set...
			if (resultSet.next()) {
				//return a reimbursement with the data to be returned to the service layer.
				return new Users( resultSet.getInt("id"), resultSet.getString("username")
						,resultSet.getString("password"), Roles.valueOf(resultSet.getString("role")));
			}
			
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database");
			e.printStackTrace();
		}
		//fail safe if the try catch block does not run.
				return null;
	}

	public static ArrayList<Users> getAllUsers(){
		// try catch block to catch sql  exception that can be thrown with connection
		try (Connection connection = ConnectionFactory.getConnection()){
		ArrayList<Users> users = new ArrayList<>();
		// write out the appropriate sql query testing.
		String sql = "SELECT * FROM ers_users";
		//we can use createStatement in this case because we do not have parameters in the query.
		Statement statement = connection.createStatement();
		//storing the records from the query in a result set
		ResultSet resultSet = statement.executeQuery(sql);
		// looping over the records from the query to then add to the return list.
		while(resultSet.next()) {
			users.add(new Users(resultSet.getInt("id"), resultSet.getString("username")
					,resultSet.getString("password"), Roles.valueOf(resultSet.getString("role"))));
		}
			return users;
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database");
			e.printStackTrace();
		}
		return null;
	}
	public static void create(Users userToBeSubmitted) {
//		Scanner scan = new Scanner(System.in);
//		System.out.println("create a password.");
//		String password = scan.next();
//		userToBeSubmitted.setPassword(password);
//		
//		boolean roles = false;
//		System.out.println("What is the role?");
//		String role = scan.next();
//		while(roles == false) {
//		if (role.equals("Manager")) {
//			userToBeSubmitted.setRole(Roles.Manager);
//			roles = true;
//		}
//		else if(role.equals("Employee")) {
//			userToBeSubmitted.setRole(Roles.Employee);
//			roles = true;
//		}
//		else {
//			System.out.println("Please enter Manager or Employee");	
//		}
//		}
		ArrayList<Users> users = new ArrayList<Users>();
		users = getAllUsers();
		int userid = users.size() + 1;
		userToBeSubmitted.setID(userid);
		//try catch block to catch sql exception that can be thrown with connection.
		try (Connection connection = ConnectionFactory.getConnection()){
			//writing out the relatively complex sql insert string to create a new record.
			//we explicit ask the database to return the new id after entry.
			String sql = "INSERT INTO ers_users (id, username, password, role)"+
			"VALUES (?,?,?,?)"
			+"RETURNING ers_users.id";
			//We must use a Prepared Statement because we have parameters.
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//use the prepared statement objects method to inserts values into the query's ?s
			// the values will come from the Reimbursement object we send in.
			preparedStatement.setInt(1, userToBeSubmitted.getID());
			preparedStatement.setObject(2, userToBeSubmitted.getUserName());
			preparedStatement.setObject(3, userToBeSubmitted.getPassword());
			preparedStatement.setObject(4, userToBeSubmitted.getRole().toString());
		
			//We need to use the result  set to retrieve the newly generated ID after entry of the new record
			ResultSet resultSet;
			//Here, we are checking that the sql query executed and returned the reimbursement record of the new id
			if ((resultSet = preparedStatement.executeQuery()) != null) {
				//must call this to get returned reimbursement record id
				resultSet.next();
				//finally returning new id.
				
			}	
		} catch (SQLException e) {
			System.out.println("Creating user has failed.");
			e.printStackTrace();
		}
			
		
	}
}
