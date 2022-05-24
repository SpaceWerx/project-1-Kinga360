package controller;

import java.util.List;

import org.eclipse.jetty.util.ajax.JSON;

import com.google.gson.Gson;

import DAO.UserDAO;
import Models.Reimbursement;
import Models.Users;
import Service.ReimbursementService;
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
	public Handler getByUsername = (ctx) -> {
		// TODO Auto-generated method stub
		String body = ctx.body();
		Gson gson = new Gson();
		String ID = gson.fromJson(body, String.class);
		Users us = UserDAO.getUserByUsernames(ID);
		String JSONObject = gson.toJson(us);
		ctx.result(JSONObject);
		ctx.status(210);
	};
	public Handler getUserByID = (ctx) ->{
		String body = ctx.body();
		Gson gson = new Gson();
		int id = gson.fromJson(body, Integer.class);
		Users u = UserService.getUserByID(id);
		String JSONObject = gson.toJson(u);
		ctx.result(JSONObject);
		ctx.status(213);
	};


}
