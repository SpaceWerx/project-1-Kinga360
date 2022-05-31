package controller;

import java.util.Objects;

import com.google.gson.Gson;

import DAO.UserDAO;
import Models.Reimbursement;
import Models.Roles;
import Models.Users;
import Service.AuthService;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

public class AuthController{
	public Handler insertUserHandler = (ctx) ->{
			String body = ctx.body();
			Gson gson = new Gson();
			Users user = gson.fromJson(body, Users.class);
			UserDAO uDAO = new UserDAO();
			UserDAO.create(user);
			ctx.result("User successfully added.");
			ctx.status(212);
		};
		public Handler insertLogin = (ctx) ->{
			
			String body = ctx.body();
			Gson gson = new Gson();
			Users user = gson.fromJson(body, Users.class);
			Users temp = AuthService.login(user.getUserName(), user.getPassword());
			if (temp == null) {
				ctx.result("username or password incorrect");
				ctx.status(401);	
			}
			else if (temp.getRole().equals(Roles.Employee)) {
				
			ctx.result("Username: " + temp.getUserName() + " ID: " + temp.getID() + " Role: " +temp.getRole());
			ctx.status(217);
			}
			else if (temp.getRole().equals(Roles.Manager)){
				ctx.result("Username: " + temp.getUserName() + " ID: " + temp.getID() + " Role: " +temp.getRole());
				ctx.status(218);
			}
			else {
				ctx.result("Incorrect username or password.");
				ctx.status(305);
			}
		};
	
	public void handleLogin(Context ctx) {
		//Reading the form parameters from the http request with the respective string keys.
		//Storing the form parameters in local variables
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		//Checking to make sure that the appropriate forms are provided.
		if (Objects.equals(username, "") || Objects.equals(password, "")) {
			//Returning bad request status and message
			ctx.status(HttpCode.BAD_REQUEST);
			
		}else {
			Users user = AuthService.login(username,password);
			
			//Ensuring the user was found and accepted
			//The service returns null if unsuccessful
			if (user != null) {
				//Sending accepted status code
				ctx.status(HttpCode.ACCEPTED);
				//Giving the front-end access to their headers
				ctx.header("Access-Control-Expose-Header","Current User");
				ctx.header("Current-User",""+user.getID());
				ctx.result(user.getRole().toString());
				ctx.status(260);
			}else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Invalid Creditionals");
				ctx.status(300);
			}
		}
	}
}
