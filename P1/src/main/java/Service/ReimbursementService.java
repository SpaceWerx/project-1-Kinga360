package Service;
import Models.*;
import java.util.*;

import DAO.ReimbursementDAO;
import DAO.UserDAO;
/**
 * The ReimbursementService should handle the submission, processing 
 * and retrieval of Reimbursements for the ERS application for the ERS application.
 * @author jjadr
 *
 */
public class ReimbursementService {
	//Instantiating the DAO and user services to utilize in various methods
	static ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	static UserDAO userService = new UserDAO();
	
	public static ArrayList<Reimbursement>getALLReimbursement(){
		ArrayList<Reimbursement> temp = reimbursementDAO.getAllReimbursemnts();
		return temp;
	}
	/**
	 * This method is meant to return a list of reimbursement records from the database that they have a status of pending.
	 */
	public static List<Reimbursement> getResolvedReimbursement(){
		//Creating a temporary return list to combine the record queries from the getByStatus
		List<Reimbursement> resovledReimbursements = new ArrayList<>();
		
		//The addAll function adds a collection of records to the array list
		resovledReimbursements.addAll(reimbursementDAO.getByStatus(Status.Approved));
		resovledReimbursements.addAll(reimbursementDAO.getByStatus(Status.Denied));
		
		//return the combine list of records
		return resovledReimbursements;
	}
	public static List<Reimbursement> getPendingReimbursement(){
		//Creating a temporary return list to combine the record queries from the getByStatus
		List<Reimbursement> pendingReimbursements = new ArrayList<>();
		
		//The addAll function adds a collection of records to the array list
		pendingReimbursements.addAll(reimbursementDAO.getByStatus(Status.Pending));
		
		
		//return the combine list of records
		return pendingReimbursements;
	}
	/**
	 * This method will take in a new reimbursement submission
	 * The submission author must be an employee
	 * The method will return the new positive integer ID
	 */
	public void submitReimbursement (Reimbursement reimbursementToBeSubmitted) {
		//Getting the user information from the author ID attached to the reimbursement submission
		Users employee = userService.getUserById(reimbursementToBeSubmitted.getAuthor());
		
		//Checking if the user is an employee
		if (employee.getRole() != Roles.Employee) {
			//Throwing an exception if the user is not manager
			throw new IllegalArgumentException("Managers cannot submit reimbursement requests.");
		}
		else {
			// Setting the status as pending by default and calling the service create method 
			reimbursementDAO.create(reimbursementToBeSubmitted);
			//Returning the new Int ID from the create method
			//return reimbursementDAO.create(reimbursementToBeSubmitted);
		}
	}
	/**
	 * This method will pass in a reimbursement, manager ID, and new status.
	 * It is meant to update the respective fields and ensure the user has a manager role
	 * The full reimbursement will be returned with the update fields
	 */
	public static Reimbursement update(Reimbursement unprocessedReimbursement, int resolverID, Status status) {
		//Getting the user information from the resolver ID passed in
		Users manager = userService.getUserById(resolverID);
		
		//Checking if the user is a manager
		if(manager.getRole() != Roles.Manager) {
			//Throwing an exception if the user is not manager
			throw new IllegalArgumentException("An employee cannot process reimbursement requests.");
		}else {
			//Setting the respective fields with the passed in data
			unprocessedReimbursement.setResolver(resolverID);
			unprocessedReimbursement.setStatus(status);
			Reimbursement temp = reimbursementDAO.update(unprocessedReimbursement);
			//Returning the reimbursement with updated fields
			return temp;
		}
	}
	//This method is meant to retrieve a single record with the passed-in ID
	public static Reimbursement getReimbursementByID(int ID) {return reimbursementDAO.getReimbursementById(ID);}
	/**
	 * This method should retrieve all reimbursement records that are associated with the userID provided
	 */
	public static List<Reimbursement> getReimbursementByAuthor(int userID){
		return reimbursementDAO.getReimbursementByUser(userID);
	}
	public static List<Reimbursement> getPendingReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}
}
