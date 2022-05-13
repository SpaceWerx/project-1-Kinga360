package Service;
import java.util.*;

import Models.*;
public class User_Services {
	private static  ArrayList<Users> users = new ArrayList<Users>();
	// returns the user name
	public Users getUserName(String name) {
		for (Users user: users) {
			if (user.getUserName() == name) {
				return user;
			}
		}
		return null;
		}
	// Returns the user ID of the users
	public static Users getUserByID(int id) {
		
		for (Users user: users) {
			if (user.getID() == id) {
				return user;
			}
		}
		return null;
	}
	// Returns the array list of users 
	public ArrayList<Users> getAllUsers(){
		return users;
	}
	// Checks to see if the user exists by ID
	public Users userExistById(String userName) {
		boolean exists = false;
		
		while (!exists) {
			for(Users user: users) {
				if (user.getUserName().equals(userName)) {
					
					return user;
					}
				}
			
			}
		return null;
	}
	public static List<Users> getUserByRole(Roles role) {
		
		for (Users user : users) {
			user.getRole();
		}
		return users;
	}
	
}
