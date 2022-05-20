package com.revature;
import Models.*;
import Service.*;
import Utilities.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
public class Laucher {

	public static void main(String[] args) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection is successful");
			CLI_Menu_Service driver = new CLI_Menu_Service();
			driver.displayMenu();
		}catch(SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
		}
	} 

}
