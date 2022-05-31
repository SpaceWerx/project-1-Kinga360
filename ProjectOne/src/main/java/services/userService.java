package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Role;
import Models.User;
import repositories.UserDAO;

public class userService {
	
	static UserDAO uDAO = new UserDAO();
	
	public static User getUserById(int id) 
	{
		return uDAO.getUserById(id);
	}
	
	@SuppressWarnings("static-access")
	public static User getByUserName(String username)
	{
		return (User) uDAO.getUserByUserName(username);
	}
	
	public static List<User> getAllUsers()
	{
		return uDAO.getAllUsers();
	}
	
	public void idExists(int id) 
	{
		for(User user : uDAO.getAllUsers()) 
		{
			if(user.getUser_ID() == id) 
			{
				System.out.println("This user exists");
				break;
			}
		}
		System.out.println("This user does not exists");
	}
	
	public static List<User> getByRole(Role role)
	{
		List<User> roles = new ArrayList<>();
		
		for(User user : uDAO.getAllUsers()) 
		{
			if(user.getRole() == role) 
			{
				roles.add(user);
			}
		}
		return roles;
	}

	public void addUser(User newEmployee) throws SQLException {
		
		UserDAO.createUser(newEmployee);
	}

	public static boolean checkUserExistsById(int id) {
		return false;
	}
}
