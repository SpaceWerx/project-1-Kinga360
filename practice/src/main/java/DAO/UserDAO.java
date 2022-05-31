package DAO;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Role;
import Models.User;
import utilities.ConnectionFactoryUtility;

public class UserDAO {
	
	
	public User getUserById(int id) 
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			String sql = "SELECT * FROM ers_users WHERE id = ?";
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setInt(1, id);
			
			ResultSet rs = prepStatement.executeQuery();
			
//			List<User> userList = new ArrayList<User>();
			
//			while(rs.next()) 
//			{
//				User u = new User(
//						rs.getInt("id"),
//						rs.getString("username"),
//						rs.getString("password"),
//						Role.valueOf(rs.getString("role"))
//						);
//				userList.add(u);
//			}
//			return userList;
			if (rs.next()) {
				return new User(
					rs.getInt("id"),	
					rs.getString("username"),
					rs.getString("password"),
					Role.valueOf(rs.getString("role"))						
						);
			}


		}
		catch(SQLException e) 
		{
			System.out.println("Something went wrong with the database");
			e.printStackTrace();
		}
		return null;
	}
	
	public static User getUserByUserName(String username) 
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			String sql = "SELECT * FROM ers_users WHERE username = ?";
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setString(1, username);
			
			ResultSet rs = prepStatement.executeQuery();
			
			List<User> userList = new ArrayList<User>();
			
			if(rs.next()) 
			{
				User u = new User(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"),
						Role.valueOf(rs.getString("Role"))
						);
				userList.add(u);
			}
			try
			{
				User user = userList.get(0);
				return user;
			}
			catch(NullPointerException e) 
			{
				System.out.println("No existing user");
				return null;
			}
			
		}
		catch(SQLException e) 
		{
			System.out.println("This user name does not exist");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAllUsers()
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			List<User> userList = new ArrayList<User>();
			
			String sql = "select * from ers_users";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) 
			{
				userList.add(new User(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"),
						Role.valueOf(rs.getString("Role"))
						));
			}
			return userList;
		}
		catch(SQLException e) 
		{
			System.out.println("Something went wrong with the database");
			e.printStackTrace();
		}
		return null;
	}
	public static int createUser(User newUser) 
	{
		try(Connection connection = ConnectionFactoryUtility.getConnection())
		{
			String sql = "INSERT INTO ers_users(username,password,role)"
					+ "VALUES(?,?, ?::role)"
					+ "RETURNING ers_users.id";
			
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			
			prepStatement.setString(1,newUser.getUserName());
			prepStatement.setString(2, newUser.getPassWord());
			prepStatement.setObject(3, newUser.getRole());
			
			ResultSet rs;
			
			if((rs = prepStatement.executeQuery()) != null) 
			{
				rs.next();
				
				return rs.getInt(1);
			}
			
		}
		catch(SQLException e) 
		{
			System.out.println("User creation failed");
			e.printStackTrace();
		}
		return 0;
	}

}