package DAO;

import java.sql.*;
import java.util.*;

import Models.*;
import Utilities.*;

public class ReimbursementDAO {
	/**
	 * The update method is meant to process reimbursements.
	 * This method is void because we are only using it to update specific fields in a given record.
	 */
	public void update(Reimbursement unprocessReimbursement) {
		// try-catch block to catch sql exception that can be thrown with connection.
		try (Connection connection = ConnectionFactoryUtility.getConnection()){
			//Write the query that we want to send to the database and assign it to a string.
			String sql = "UPDATE ers_reimbursements SET resolver = ?, status = ?:: WHERE id =?";
			// Creating a prepared statement with the sql string we created.
			PreparedStatement preparedStatement =  connection.prepareStatement(sql);
			// Setting the update parameters (?'s) with their respective values.
			preparedStatement.setInt(1, unprocessReimbursement.getResolver());
			preparedStatement.setObject(2, unprocessReimbursement.getStatus());
			preparedStatement.setObject(3, unprocessReimbursement.getID());
			// executive the record update
			preparedStatement.executeUpdate();
			//Proclaim victory
			System.out.println("Reimbursement successfully update!");
			
		}catch(SQLException e) {
			System.out.println("Updating failed.");
			e.printStackTrace(); //useful debugging tool.
		}
		
	}
	/**
	 * This method is intended to extract any reimbursements from the database.
	 * that were submitted by a specific user, where ID is passed as a parameter.
	 * 
	 * @return List of reimbursements created by author with matching userID.
	 */
	public List<Reimbursement> getReimbursementByUser (int userID){
		// Try-catch block to catch sql exception that can be thrown with connection.
		try (Connection connection = ConnectionFactoryUtility.getConnection()){
			//SQL statement prepared as a string.
			// In the instance, we are filtering reimbursements by an author (user) ID.
			String sql = "SELECT * FROM ers_reimbursements WHERE author =?";
			//Preparing the sql statement to be executed once we fill in the query parameters.
			PreparedStatement preparedStatement =  connection.prepareStatement(sql);
			//Filling the missing query value (?) with the method parameter (userID).
			preparedStatement.setInt(1,userID);
			// Building a sql result set from the execution of the query statement.
			ResultSet resultSet = preparedStatement.executeQuery();
			// Initializing a new Reimbursement array list  to house and return with the data from the database.
			List<Reimbursement> reimbursements = new ArrayList<>();
			// This while loop will continue to add reimbursements to the list
			// Until all the data from the result set has run out.
			while (resultSet.next()) {
				// Adding reimbursements to the list with the data extracted from the database.
				reimbursements.add(new Reimbursement(resultSet.getInt("id"), resultSet.getInt("author")
						,resultSet.getInt("resolver"), resultSet.getString("description"),Status.valueOf(resultSet.getString("status")),
						Type.valueOf(resultSet.getString("Type")), resultSet.getDouble("amount")));
			}
			//return the list of reimbursements that have the matching author (user) id.
			return reimbursements;
			
			
		}catch (SQLException e) {
			//catching the sql exception (this is a good place to utilize custom exception handling).
			System.out.println("Someting went wrong obtaining your list.");
			e.printStackTrace();
		}
		//fail safe if the try catch block does not run.
		return null;
	}
	/**
	 * Should retrieve a reimbursement from the DB with the corresponding id or null if there is no match.
	 */
	public Reimbursement getReimbursementById(int id) {
		// Try-catch block to catch sql exception that can be thrown with connection.
		try (Connection connection = ConnectionFactoryUtility.getConnection()){
			String sql = "SELECT * FROM ers_reimbursements where id = ?";
			//When we need parameters we need to use a PREPARED statement, as opposed to a statement(seen above).
			PreparedStatement preparedStatement =  connection.prepareStatement(sql); //prepared statement as opposed to created statement.
			//insert the methods arguments(int id) as the first and (only) variable in the sql query.
			preparedStatement.setInt(1, id); //the 1 is referring to the first parameter(?) found in our sql string
			ResultSet resultSet = preparedStatement.executeQuery();
			// If there are results in the result set...
			if (resultSet.next()) {
				//return a reimbursement with the data to be returned to the service layer.
				return new Reimbursement(resultSet.getInt("id"), resultSet.getInt("author")
						,resultSet.getInt("resolver"), resultSet.getString("description"),Status.valueOf(resultSet.getString("status")),
						Type.valueOf(resultSet.getString("Type")), resultSet.getDouble("amount"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database");
			e.printStackTrace();
		}
		//fail safe if the try catch block does not run.
				return null;
	}
	/**
	 * Should retrieve a list of reimbursements from the db with the corresponding status or an empty list if it matches.
	 */
	public List<Reimbursement> getByStatus(Status status){
		// Try-catch block to catch sql exception that can be thrown with connection.
				try (Connection connection = ConnectionFactoryUtility.getConnection()){
					String sql = "SELECT * FROM ers_reimbursements where id = ?";
					//When we need parameters we need to use a PREPARED statement, as opposed to a statement(seen above).
					PreparedStatement preparedStatement =  connection.prepareStatement(sql); //prepared statement as opposed to created statement.
					//insert the methods arguments(int id) as the first and (only) variable in the sql query.
					preparedStatement.setInt(1, id); //the 1 is referring to the first parameter(?) found in our sql string
					ResultSet resultSet = preparedStatement.executeQuery();
					//Create an empty arraylist to be filled with the data from the database
					List <Reimbursement> reimbursements = new ArrayList<Reimbursement>();
					//While there are results in results
					while(resultSet.next()) {
					reimbursements.add(new Reimbursement(resultSet.getInt("id"), resultSet.getInt("author")
							,resultSet.getInt("resolver"), resultSet.getString("description"),Status.valueOf(resultSet.getString("status")),
							Type.valueOf(resultSet.getString("Type")), resultSet.getDouble("amount")));
				}
					return reimbursements;
				} catch (SQLException e) {
					System.out.println("Something went wrong with the database");
					e.printStackTrace();
				}
				return null;
				
	}
	/**
	 * The getallReimbursements methods retrieves all reimbursement records from the database.
	 * This method should return an array list of reimbursement or null if there is a connection error.
	 */
	public List<Reimbursement> getAllReimbursemnts(){
		// try catch block to catch sql  exception that can be thrown with connection
		try (Connection connection = ConnectionFactoryUtility.getConnection()){
		List<Reimbursement> reimbursements = new ArrayList<>();
		// write out the appropriate sql query testing.
		String sql = "SELECT * FROM ers_reimbursements";
		//we can use createStatement in this case because we do not have parameters in the query.
		Statement statement = connection.createStatement();
		//storing the records from the query in a result set
		ResultSet resultSet = statement.executeQuery(sql);
		// looping over the records from the query to then add to the return list.
		while(resultSet.next()) {
			reimbursements.add(new Reimbursement(resultSet.getInt("id"), resultSet.getInt("author")
					,resultSet.getInt("resolver"), resultSet.getString("description"),Status.valueOf(resultSet.getString("status")),
					Type.valueOf(resultSet.getString("Type")), resultSet.getDouble("amount")));
		}
			return reimbursements;
		} catch (SQLException e) {
			System.out.println("Something went wrong with the database");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * The create method is meant to create a new record in the database for new reimbursement submissions.
	 */
	public int create(Reimbursement reimbursementToBeSubmitted) {
		//try catch block to catch sql exception that can be thrown with connection.
		try (Connection connection = ConnectionFactoryUtility.getConnection()){
			//writing out the relatively complex sql insert string to create a new record.
			//we explicit ask the database to return the new id after entry.
			String sql = "INSERT INTO ers_reimbursements (author, description, type, status, amount)"+
			"VALUES (?,?,?::type, ?::status,?)"
			+"RETURNING ers_reimbursemtns.id";
			//We must use a Prepared Statement because we have parameters.
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//use the prepared statement objects method to inserts values into the query's ?s
			// the values will come from the Reimbursement object we send in.
			preparedStatement.setInt(1, reimbursementToBeSubmitted.getAuthor());
			preparedStatement.setObject(2, reimbursementToBeSubmitted.getDescription());
			preparedStatement.setObject(3, reimbursementToBeSubmitted.getType().name());
			preparedStatement.setObject(4, reimbursementToBeSubmitted.getStatus().name());
			preparedStatement.setObject(5, reimbursementToBeSubmitted.getAmount());
			//We need to use the result  set to retrieve the newly generated ID after entry of the new record
			ResultSet resultSet;
			//Here, we are checking that the sql query executed and returned the reimbursement record of the new id
			if ((resultSet = preparedStatement.executeQuery()) != null) {
				//must call this to get returned reimbursement record id
				resultSet.next();
				//finally returning new id.
				return resultSet.getInt(1);
			}	
		} catch (SQLException e) {
			System.out.println("Creating reimbursement has failed.");
			e.printStackTrace();
		}
		return 0;	
		
	}
}
