package Service;

import Models.*;
//
public class AuthService {
	
	  note: userToBeRegistered will have id=0.
	  After registration, the registration will be a positive integer.
	  Making new user object.
	 
	public int register(Users userToBeRegistered) {
		
		// checking if the user name already exist in the database.
		// if the method returns null, the user name is available.
		
		if (userDAO.getUserName(userToBeReristered != null)) {
		//	 Throws a null pointer exception if the user name is already taken.
			throw new NullPointerException("User name is already taken.");
		}
		 // take in the user object sent from the menu and send it to the userDAO to be inserted to the database
//		// After the entry has been made, the ID of the new user is immediately returned.
		 return userDAO.create(userToBeRegistered);
	}
	/**
	 * The login method is used to check the information given and verify their credentials.
	 * 
	 * @return User object
	 */
	  public Users login (String userName, String password) {
//		  Instantiating a temporary user.
		  Users user;
//		
//		// Try catch block will catch any exceptions thrown by the UserDAO method.
		  try {
			//  Retrieving the user data from the database from the user name given.
			  user = userDAO.getByUserName(userName);
		
			//  These conditional statements are checking for various contingencies.
//			 The first is checking if the user exists and that the password given matches the one stored.
			if (user != null && password.equals(user.getPassword())) {
//				
//				//If this one is true, the user object is returned and login is deemed successful.
				System.out.println("Logged in successful.");
				return user;
//				//The second is checking if the user exists and that the password given does not match the one stored.
			}else if(user != null && !password.equals(user.getPassword())){
//				//If this one is true and the previous is false, a null object is returned and login is deemed unsuccessful.
				System.out.println("Wrong password");
				return null;
			}
//			 //The third is the final contingency and will only occur if the user name does not exist in the database.
			else {
//				//This outcome will return a null object and login is deemed unsuccessful. 
				System.out.println("User does not exist.");
			return null;
			}
		  } catch (Exception e) {
			  System.out.println("Login unsuccessful");
			  e.printStackTrace(); //Helpful debugging tool.
		  }
//		// if the try catch does not run, a null object will be returned and login is deemed unsuccessful.
		return null;
	}
	
}
