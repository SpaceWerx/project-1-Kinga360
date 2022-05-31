package mockData;

import java.util.ArrayList;
import java.util.List;

import Models.Role;
import Models.User;

public class MockUserData {
	private final List<User> users = new ArrayList<User>();
	
	public MockUserData() 
	{
	
		
		User GENERIC_EMPLOYEE_1 = new User(1,"genericEmployee1","genericPassword1",Role.EMPLOYEE);
		User GENERIC_EMPLOYEE_2 = new User(2,"genericEmployee2","genericPassword2",Role.EMPLOYEE);
		User GENERIC_EMPLOYEE_3 = new User(3,"genericEmployee3","genericPassword3",Role.EMPLOYEE);
		
		User GENERIC_FINANCE_MANAGER_1 = new User(4,"genericManager1","genericPassword1",Role.MANAGER);
		User GENERIC_FINANCE_MANAGER_2 = new User(5,"genericManager2","genericPassword2",Role.MANAGER);
		User GENERIC_FINANCE_MANAGER_3 = new User(6,"genericManager3","genericPassword3",Role.MANAGER);
		
		users.add(GENERIC_EMPLOYEE_1);
		users.add(GENERIC_EMPLOYEE_2);
		users.add(GENERIC_EMPLOYEE_3);
		users.add(GENERIC_FINANCE_MANAGER_1);
		users.add(GENERIC_FINANCE_MANAGER_2);
		users.add(GENERIC_FINANCE_MANAGER_3);
		
	}
	public List<User> getUsers()
	{
		return users;
	}

}
