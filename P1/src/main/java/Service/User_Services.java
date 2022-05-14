package Service;
import java.util.*;

import Mock_Data.*;
import Models.*;
public class User_Services {
	
	private static MockUserData mockData = new MockUserData();
	private static  ArrayList<Users> users;
	public static void getData() {
		users = mockData.MockData();
	}
	// returns the user name
	public Users getUserName(String name) {
		getData();
		users = mockData.getUsers();
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
		users = mockData.getUsers();
		for (Users user: users) {
			if (user.getID() == id) {
				return user;
			}
		}
		return null;
	}
	// Returns the array list of users 
	public ArrayList<Users> getAllUsers(){
		getData();
		users = mockData.getUsers();
		return users;
	}
	// Checks to see if the user exists by ID
	public Users userExistById(String userName) {
		getData();
		users = mockData.getUsers();
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
	public static ArrayList<Users> getUserByRole(Roles role) {
		getData();
		for (Users user : users) {
			
			user.getRole();
		}
		return users;
	}
	
}
