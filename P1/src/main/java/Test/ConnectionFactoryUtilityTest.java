package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import DAO.*;
import Mock_Data.MockReimbursementData;
import Models.*;

import Service.*;

public class ConnectionFactoryUtilityTest {
	private static Reimbursement_Services reimbursementService;
	private static User_Services userService;
	private static ReimbursementDAO reimbursementDAO;
	private Reimbursement REIMBURSEMENT_TO_PROCESS;
	private List<Reimbursement> mockPendingReimbursement;
	private List<Reimbursement> mockApprovedReimbursement;
	private List<Reimbursement> mockDeniedReimbursement;
	private Users GENERIC_EMPLOYEE_1;
	private Users GENERIC_MANAGER_1;
	
@BeforeClass
public void setUpBeforeClass() throws Exception{
	//Instantiating a new reimbursement Service
	reimbursementService = new Reimbursement_Services();
}
//This method will be called before each test is instantiated
@Before
public void setUP() throws Exception{
	//Mocking the user service and reimbursementDAO because they are not directly tested here
	userService = mock(User_Services.class);
	reimbursementDAO = mock(ReimbursementDAO.class);
	
	//Retrieving the mock data we made in week 1 to leverage our tests
	MockReimbursementData mockReimbursementData = new MockReimbursementData();
	
	//We must assign the mocks to the instantiated reimbursement service to ensure it's not using real ones
	reimbursementService.reimbursementDAO = reimbursementDAO;
	reimbursementService.userService = userService;
	
	//Generic mock users to use in our tests
	GENERIC_EMPLOYEE_1 = new Users(1,"genericEmployee1","genericPassword",Roles.Employee);
	GENERIC_MANAGER_1 = new Users(1,"genericManager1","genericPassword",Roles.Manager);
	
	//mock reimbursement that can be tested by processing 
	REIMBURSEMENT_TO_PROCESS = new Reimbursement(2,GENERIC_EMPLOYEE_1.getID(),"Oracle Certification",Type.Other,Status.Pending,150.00 );
	
	//Creating lists of reimbursements from the mockReimbursementdata
	//These lists will be used in various tests of reimbursement services
	List<Reimbursement> mockReimbursements = mockReimbursementData.getReimbursement();
	 mockPendingReimbursement = new ArrayList<>();
	 mockApprovedReimbursement =  new ArrayList<>();
	 mockDeniedReimbursement = new ArrayList<>();
	 
	// Iterating through the mock reimbursements and adding these to their respective lists by status
	 for (Reimbursement reimbursement : mockReimbursements) {
		 if(reimbursement.getStatus() == Status.Pending) {
			 mockPendingReimbursement.add(reimbursement);
		 }
		 else if(reimbursement.getStatus() == Status.Approved) {
			 mockApprovedReimbursement.add(reimbursement);
		 }
		 else  {
			 mockDeniedReimbursement.add(reimbursement);
		 }
	 }
}
@Test
public void testUpdateThrowsIllegalArgumentExceptionWhenResolverNotManager() {
	//Telling the nested userService method to return an employee when called in the nested test update method.
	when(userService.getUserByID(anyInt())).thenReturn(GENERIC_EMPLOYEE_1);
	
	//checking to make sure the tested update method throws an exception we want.
	assertThrows(IllegalArgumentException.class,
	() -> Reimbursement_Services.update(REIMBURSEMENT_TO_PROCESS, GENERIC_EMPLOYEE_1.getID(),Status.Approved));
	//Verifying that the mocked reimbursementDAO update is never called
	//Verifying that the mocked userService getUserByID method is called
	verify(reimbursementDAO, never()).update(REIMBURSEMENT_TO_PROCESS);
	verify(userService).getUserByID(GENERIC_EMPLOYEE_1.getID());
}
@Test
public void testSubmitReimbursementThrowsIllegalArgumentExceptionWhenSubmittedByManager() {
	//Telling the nested userService method to return a Manager when called in the tested update method
	when(userService.getUserByID(anyInt())).thenReturn(GENERIC_MANAGER_1);
	//checking to make sure the tested update method throws an exception we want.
	assertThrows(IllegalArgumentException.class,
	() -> reimbursementService.submitReimbursement(REIMBURSEMENT_TO_PROCESS));
	//Verifying that the mocked reimbursementDAO update is never called
	//Verifying that the mocked userService getUserByID method is called
	verify(reimbursementDAO, never()).update(REIMBURSEMENT_TO_PROCESS);
	verify(userService).getUserByID(GENERIC_EMPLOYEE_1.getID());
}
@Test
public void testReimbursementStatusIsChangedAfterUpdate() {
	//Telling the nested userService method to return a Manager when called in the tested update method
	when(userService.getUserByID(anyInt())).thenReturn(GENERIC_MANAGER_1);
	//Checking to make sure the correct status is assigned accordingly when the update method is called.
	assertEquals(Status.Approved, reimbursementService.update(REIMBURSEMENT_TO_PROCESS),GENERIC_MANAGER_1.getID(),Status.Approved).getStatus());
	//Verifying that the mocked reimbursementDAO update method is called.
	//Verifying that the mocked userServices get user by ID is called
	verify(userService).getUserByID(GENERIC_EMPLOYEE_1.getID());
	verify(reimbursementDAO).update(REIMBURSEMENT_TO_PROCESS);
}
@Test
public void testResolverIsAssignedAfterReimbursementUpdate() {
	//Telling the nested userService method to return a manager when called in the tested update method.
	when(User_Services.getUserByID(anyInt())).thenReturn(GENERIC_MANAGER_1);
	assertEquals(GENERIC_MANAGER_1.getID(),reimbursementService.update(REIMBURSEMENT_TO_PROCESS,GENERIC_MANAGER_1.getID(),Status.Approved).getResolver());
	//Verifying that the mocked reimbursementDAO update method is called.
	//Verifying that the mocked userService getUserByID method is called.
	verify(userService).getUserByID(GENERIC_MANAGER_1.getID());
	verify(reimbursementDAO).update(REIMBURSEMENT_TO_PROCESS);
}
@Test
public void testGetResolvedReimburesementsReturnsOnlyApprovedAndDenied() {
	//Telling the nested reimbursementDAO getStatus method to return the mocked list of approved and denied reimbursements and called respectively.
	when(reimbursementDAO.getByStatus(Status.Approved)).thenReturn(mockApprovedReimbursement);
	when(reimbursementDAO.getByStatus(Status.Denied)).thenReturn(mockDeniedReimbursement);
	
	//Creating a new list that combine the mocked approved and mocked denied reimbursements(similar to how the service methods works)
	List<Reimbursement> resolvedReimbursements = new ArrayList<>();
	resolvedReimbursements.addAll(mockApprovedReimbursement);
	resolvedReimbursements.addAll(mockDeniedReimbursement);
	
	//Checking to make sure the service method  returns the correct data.
	assertEquals(resolvedReimbursements,reimbursementService.getResolvedReimbursements());
	
	// Verifying that the mocked  reimbursementDAO method getByStatus is called twice.
	verify(reimbursementDAO).getByStatus(Status.Approved);
	verify(reimbursementDAO).getByStatus(Status.Denied);	
}
@Test
public void testGetPendingReimbursementsReturnsOnlyPending() {
	// Telling the nested reimbiursementDAO getStatus method to return the mocked list of pending reimbursements when called.
	when(reimbursementDAO.getByStatus(any(Status.class))).thenReturn(mockPendingReimbursement);
	// Checking to make sure the service method returns the correct data.
	assertEquals(mockPendingReimbursement, reimbursementService.getPendingReimbursements());
	//Verifying that the mocked reimbursementDAO method getByStatus is called
	verify(reimbursementDAO).getByStatus(Status.Pending);
}
}
