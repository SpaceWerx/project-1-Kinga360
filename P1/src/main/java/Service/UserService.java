package Service;

import java.util.ArrayList;

import DAO.UserDAO;
import Mock_Data.MockUserData;
import Models.Roles;
import Models.Users;

public class UserService {
	private static MockUserData mockData = new MockUserData();
	private static  ArrayList<Users> users = new ArrayList<Users>();
	public static void getData() {
		if (users.isEmpty()) {
		
		users = UserDAO.getAllUsers();
		}
	}
	// returns the user name
	public Users getUserName(String name) {
		getData();
		users = UserDAO.getAllUsers();
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
		users = UserDAO.getAllUsers();
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
		users = UserDAO.getAllUsers();
		return UserDAO.getAllUsers();
	}
	// Checks to see if the user exists by ID
	public Users userExistById(String userName) {
		getData();
		users = UserDAO.getAllUsers();
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
