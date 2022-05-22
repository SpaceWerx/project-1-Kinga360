package Utilities;

import controller.AuthController;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

public class JavalinConfigurationUtility {
	//Instantiating respective controllers to access methods for the routes configure
	AuthController authController = new AuthController();
	UserController userController = new UserController();
	RemibursementController reimbursementController = new ReimbursementController();
	
public void start(int port) {
	//Starting the Javalin instance on the server
	this.app.start(port); 
}
//Creating the Javalin app to designate routes
//Enabling CORS for all origins to avoid http request constraints
Javalin app = Javalin.create(JavalinConfig::enablecoresforallorigins).routers()->{
	path("Login")
}

}
