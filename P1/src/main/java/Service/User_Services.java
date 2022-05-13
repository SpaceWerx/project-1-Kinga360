package Service;
import java.util.*;

import Mock_Data.*;
import Models.*;
public class User_Services {
	private static  List<Users> users = new ArrayList<Users>();
	private static MockUserData mockData = new MockUserData();
	public static void getData() {
		 
		mockData.MockData();
		users = mockData.getUsers();
	}
	// returns the user name
	public Users getUserName(String name) {
		getData();
		for (Users user: users) {
			if (user.getUserName() == name) {
				return user;
			}
		}
		return null;
		}
	// Returns the user ID of the users
	public static Users getUserByID(int id) {
		getData();
		for (Users user: users) {
			if (user.getID() == id) {
				return user;
			}
		}
		return null;
	}
	// Returns the array list of users 
	public List<Users> getAllUsers(){
		getData();
		return users;
	}
	// Checks to see if the user exists by ID
	public Users userExistById(String userName) {
		getData();
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
		getData();
		for (Users user : users) {
			user.getRole();
		}
		return users;
	}
	
}
