package com.revature;
import Models.*;
import Service.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
public class Laucher {

	public static void main(String[] args) {
		//CLI_Menu_Service driver = new CLI_Menu_Service();
		//driver.displayMenu();
		try (Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection is successful");
		}catch(SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
		}
	} 

}
