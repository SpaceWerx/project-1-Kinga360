package controller;

import java.util.List;

import com.google.gson.Gson;

import DAO.UserDAO;
import Models.Users;
import Service.UserService;
import io.javalin.http.Handler;

public class UserController {
UserService us = new UserService();
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Handler getUsersHandler = (ctx) -> {
		// TODO Auto-generated method stub
		List<Users>users = us.getAllUsers();
		Gson gson = new Gson();
		String JSONObject = gson.toJson(users);
		ctx.result(JSONObject);
		ctx.status(200);
	};

	public Handler insertUsersHandler = (ctx) -> {
		// TODO Auto-generated method stub
		String body = ctx.body();
		Gson gson = new Gson();
		Users user = gson.fromJson(body, Users.class);
		us.add(user);
		UserDAO userDAO = new UserDAO();
		UserDAO.create(user);
		ctx.result("Employee successfully added.");
		ctx.status(201);
	};
	

}
