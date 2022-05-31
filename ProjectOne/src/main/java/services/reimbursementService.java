package services;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Role;
import Models.Status;
import Models.User;
import repositories.ReimbursementDAO;

public class reimbursementService {
	
	public static ReimbursementDAO rDAO = new ReimbursementDAO();
	public static userService uService = new userService();
	public static ArrayList<Reimbursement> reimbursements = new ArrayList<>();
	
	public static List<Reimbursement> getPendingReimbursement()
	{
		return rDAO.getByStatus(Status.PENDING);
		
	}
	
	public static List<Reimbursement> getResolvedReimbursement()
	{
		List<Reimbursement> resolvedReimbursement = new ArrayList<Reimbursement>();
		
		resolvedReimbursement.addAll(rDAO.getByStatus(Status.APPROVED));
		resolvedReimbursement.addAll(rDAO.getByStatus(Status.DENIED));
		
		return resolvedReimbursement;
	}
	
	public static int submitReimbursement(Reimbursement reimbursementToBeSubmitted) 
	{
		User employee = uService.getUserById(reimbursementToBeSubmitted.getAuthor());
		
		if(employee.getRole() != Role.EMPLOYEE) 
		{
			throw new IllegalArgumentException("Managers can not submit reimbursement requests.");
			
		}
		else 
		{
			reimbursementToBeSubmitted.setStatus(Status.PENDING);
			
			return rDAO.create(reimbursementToBeSubmitted);
		}
	}
	
	public static Reimbursement update(Reimbursement unprocessedReimbursement, int resolverID, Status status) 
	{
		User manager = userService.getUserById(resolverID);
		
		if(manager.getRole() != Role.MANAGER) 
		{
			throw new IllegalArgumentException("An employee can not process reimbursement requests.");
		}
		else 
		{
			unprocessedReimbursement.setResolver(resolverID);
			unprocessedReimbursement.setStatus(status);
			
			rDAO.update(unprocessedReimbursement);
			
			return unprocessedReimbursement;
		}
	}
	
	public static Reimbursement getReimbursementById(int id) 
	{
		return rDAO.getReimbursementById(id);
	}
	
	public static List<Reimbursement> getReimbursementByAuthor(int userId)
	{
		return rDAO.getReimbursementsByUser(userId);
	}
	
	public static List<Reimbursement> getReimbursementByStatus(Status status)
	{
		return rDAO.getByStatus(status);
	}
	public void setUserService(userService us) 
	{
		reimbursementService.uService = us;
	}

}
