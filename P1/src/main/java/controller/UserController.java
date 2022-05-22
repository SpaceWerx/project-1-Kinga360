package controller;

import Service.UserService;
import io.javalin.http.Handler;

public class UserController {
UserService us = new UserService();
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Handler getUsersHandler= (ctx) -> {
		// TODO Auto-generated method stub
		List<Users>users = us.getAllUsers();
		Gson gson = new Gson();
		String JSONObject = gson.toJson(users);
	};

	public Handler inserUsersHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
