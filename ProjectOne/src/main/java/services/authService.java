package services;

import Models.User;

import repositories.UserDAO;

public class authService {
	//@SuppressWarnings("static-access")
	public static User login(String username, String password) 
	{
		User user;
		//UserDAO ud = new UserDAO();
		
		try 
		{
			user = UserDAO.getUserByUserName(username);
			if(user!=null && password.equals(user.getPassWord())) 
			{
				System.out.println("Logged in Successfully");
				return user;
			}
			else if(user!=null && !password.equals(user.getPassWord())) 
			{
				System.out.println("Incorrect password");
				return null;
			}
			else 
			{
				System.out.println("User does not exist");
				return null;
			}
			
		}
		catch(Exception e) 
		{
			System.out.println("Log in unsuccessful");
			e.printStackTrace();
		}
		return null;
	}
	public static int register(User userToBeRegistered) 
	{
		if(UserDAO.getUserByUserName(userToBeRegistered.getUserName())!= null)
		{
			throw new NullPointerException("Username is already taken");
		}
	
		return UserDAO.createUser(userToBeRegistered);
		
	}
}
