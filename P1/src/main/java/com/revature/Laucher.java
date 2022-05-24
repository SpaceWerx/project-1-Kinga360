package com.revature;
import Models.*;
import Service.*;
import Utilities.ConnectionFactory;
import controller.AuthController;
import controller.ReimbursementController;
import controller.UserController;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
public class Laucher {

	public static void main(String[] args) {
		UserController userController = new UserController();
		ReimbursementController rc = new ReimbursementController();
		AuthController ac = new AuthController();
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
		app.get("/User", userController.getUsersHandler);
		
		app.post("/User",userController.insertUsersHandler);
		
		app.get("/Reimbursement", rc.getReimbursementHandler);
		app.post("/Reimbursement", rc.insertReimbursementHandler);
		app.get("/getReimbursementByID",rc.getByID);
		app.get("/getReimbursementByStatus",rc.getByStatus);
		app.get("/getReimbursementByAuthor",rc.getByAuthor);
		app.get("/Process",rc.Process);
		app.get("/getUserByUsername", userController.getByUsername);
		app.get("/getUserByID", userController.getUserByID);
		app.post("/postNewUser", ac.insertUserHandler);
		app.post("/login", ac.insertLogin);
		}
	} 


