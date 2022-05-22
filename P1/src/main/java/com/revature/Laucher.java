package com.revature;
import Models.*;
import Service.*;
import Utilities.ConnectionFactory;
import controller.UserController;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
public class Laucher {

	public static void main(String[] args) {
		UserController userController = new UserController();
		try (Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection is successful");
			//CLI_Menu_Service driver = new CLI_Menu_Service();
			//driver.displayMenu();
		}catch(SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
		}
		//Javalin object setup which creates the connection.
		Javalin app = Javalin.create(
			config -> {
				config.enableCorsForAllOrigins(); // This allows javascript to be processed anywhere
			}
			).start(3000);
		//now we need our endpoints
		app.get("/User", userController.getUsersHandler());
		
		app.post("/User",userController.inserUsersHandler());
		
		app.post("/Login", null);
		}
	} 

}
