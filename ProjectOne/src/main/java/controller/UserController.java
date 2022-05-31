package controller;

import java.util.List;

import com.google.gson.Gson;

import Models.User;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import services.userService;

public class UserController {
	userService us = new userService();
	public Handler getEmployeesHandler = (ctx) -> 
	{
		 List<User> allUsers = userService.getAllUsers();
			
		 Gson gson = new Gson();
		
		 String  JSONObject = gson.toJson(allUsers);
		 
		 ctx.result(JSONObject);
		 ctx.status(200);
	};
	
	public Handler insertEmployeesHandler = (ctx) ->
	{
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		User employee = gson.fromJson(body, User.class);
		
		us.addUser(employee);
		
		ctx.result("Employee successfully added!");
		ctx.status(201);
		
	};
	
	public void handleGetUserId(Context ctx) 
	{
		try 
		{	
			String idParam = ctx.pathParam("id");
			
			int id = Integer.parseInt(idParam);
			
			User user = userService.getUserById(id);
			
			if(user != null) 
			{
				ctx.status(HttpCode.OK);
				ctx.json(user);
			}
			else 
			{
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Could not retrieve the user ID");
			}
		}
		catch(Exception e)
		{
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) 
			{
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
		
	}
	
	public void handleGetUserByUserName(Context ctx) 
	{
		try 
		{
			String idParam = ctx.pathParam("username");
			
			User user = userService.getByUserName(idParam);
			
			if(user != null) 
			{
				ctx.status(HttpCode.OK);
				ctx.json(user);
			}
			else 
			{
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Could not retrieve the username");
			}
		}
		catch(Exception e)
		{
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			
			if(!e.getMessage().isEmpty()) 
			{
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}

	}
}
