package controller;

import java.util.Objects;

import Models.Users;
import Service.AuthService;

public class AuthController{
	public void handleRegisterMethod(Context ctx) {
	try {
		//Storing the json body as a string
		String input = ctx.body();
		//Instantiating and using the object mapper
		//This will parse the input string to a user object and store it to a local variable
		ObjectManager mapper = new ObjectMapper();
		Users user = mapper.readValue(input, Users.class);
		
		// Once the user object is created, storing the integer ID from the user service method.
		int id = AuthService.register(user);
		//if id is still 0, the registration was unsuccessful.
		if (id ==0) {
			//Telling the client that registration has failed.
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			ctx.result("Registration unsuccessful");
		}else {
			//Proclaiming successful creation of new user
			ctx.status(HttpCode.CREATED);
			ctx.result("Registration succesful");
		}
	}catch (Exception e) {
		ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
		if (!e.getMessage().isEmpty()) {
			ctx.result(e.getMessage());
		}
		e.printStackTrace();
	}
	
	}
	public void handleLogin(Context ctx) {
		//Reading the form parameters from the http request with the respective string keys.
		//Storing the form parameters in local variables
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		//Checking to make sure that the appropriate forms are provided.
		if (Objects.equals(username, "") || Objects.equals(password, "")) {
			//Returning bad request status and message
			ctx.status(HttpCode.BADREQUEST);
			
		}else {
			Users user = AuthService.login(username,password);
			
			//Ensuring the user was found and accepted
			//The service returns null if unsuccessful
			if (user != null) {
				//Sending accepted status code
				ctx.status(HttpCode.Accepted);
				//Giving the front-end access to their headers
				ctx.header("Access-Control-Expose-Header","Current User");
				ctx.header("Current-User",""+user.getID());
				ctx.result(user.getRole().toString());
			}else {
				ctx.status(HttpCode.BADREQUEST);
				ctx.result("Invalid Creditionals");
			}
		}
	}
}
