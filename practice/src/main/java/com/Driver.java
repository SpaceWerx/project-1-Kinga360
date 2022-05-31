package com;


	import java.sql.Connection;

	import java.sql.SQLException;

	import controller.AuthController;
	import controller.ReimbursementController;
	import controller.UserController;
	import io.javalin.Javalin;
	import io.javalin.core.JavalinConfig;
	import utilities.ConnectionFactoryUtility;
	import io.javalin.http.Context;
	import io.javalin.http.HttpCode;
	import services.menuService;


	public class Launcher {
		public static void main(String[] args){
			UserController uc = new UserController();
			AuthController ac = new AuthController();
			ReimbursementController rc = new ReimbursementController();
			
			
			try(Connection conn = ConnectionFactoryUtility.getConnection())
			{
				System.out.println("Connection Successful!");
			}
			catch(SQLException e) 
			{
				System.out.println("Connection Failed");
				e.printStackTrace();
			}
			menuService menu = new menuService();
			menu.displayLogInMenu();
//			
//			Javalin app = Javalin.create(
//					config -> {
//						config.enableCorsForAllOrigins(); //This is what allows the server to process JS
//					}	
//						).start(5000);
//				
			//Endpoint
			//app.get("/employee", uc.getEmployeesHandler);
//			app.get("/status", rc.handleGetReimbursementByStatus);
			//app.post("/status", );
			//app.post("/employee", uc.insertEmployeesHandler);
			
			//log in function
			//app.post("/login", null);
			
		}
		
	}

