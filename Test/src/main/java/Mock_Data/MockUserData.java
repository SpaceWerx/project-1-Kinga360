package Mock_Data;

import java.util.*;

import Models.*;

public class MockUserData {
	private final  ArrayList<Users> users = new ArrayList<>();
	
	public ArrayList<Users> MockData() {
		Users Generic_Employee_1 = new Users(1,"genericEmployee1","genericPassword1",Roles.Employee);
		Users Generic_Employee_2 = new Users(2,"genericEmployee2","genericPassword2",Roles.Employee);
		Users Generic_Employee_3 = new Users(3,"genericEmployee3","genericPassword3",Roles.Employee);
		
		Users Generic_Fiance_Manager_1 = new Users(4,"genericManager4","genericPassword1",Roles.Manager);
		Users Generic_Fiance_Manager_2 = new Users(5,"genericManager5","genericPassword2",Roles.Manager);
		Users Generic_Fiance_Manager_3 = new Users(6,"genericManager6","genericPassword3",Roles.Manager);
		
		users.add(Generic_Employee_1);
		users.add(Generic_Employee_2);
		users.add(Generic_Employee_3);
		users.add(Generic_Fiance_Manager_1);
		users.add(Generic_Fiance_Manager_2);
		users.add(Generic_Fiance_Manager_3);
		return users;
		
	}
	public ArrayList<Users> getUsers(){return users;};

}
