package Service;
import java.util.*;
import Models.*;

public class Reimbursement_Services {
	private static ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
	public void update(Reimbursement unprocessedReimbursement, int resolverId, Status UpdateStatus) {
		for (Reimbursement reimbursement : reimbursements) {
		if (reimbursement.getID() == unprocessedReimbursement.getID()) {
			reimbursement.setResolver(resolverId);
			reimbursement.setStatus(UpdateStatus);
			return;
		}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again");
	}
	public void submitReimbursement (Reimbursement reimbursementToBeSubmitted) {
		Reimbursement latestReimbursement;
		
			latestReimbursement = reimbursements.get(reimbursements.size() -1);
			int id = latestReimbursement.getID() + 1; //New ID is 1 higher than the highest
			
			reimbursementToBeSubmitted.setID(id);
			reimbursementToBeSubmitted.setStatus(Status.Pending);
			reimbursements.add(reimbursementToBeSubmitted);
		
			
		}
		
		
		
	public static List<Reimbursement> getResolvedReimbursements(){
		List <Reimbursement> resolvedReimbursements = new ArrayList<>();
		
		for (Reimbursement reimbursement: reimbursements) {
			if (reimbursement.getStatus() == Status.Approved || reimbursement.getStatus() == Status.Denied) {
				resolvedReimbursements.add(reimbursement);
			}
		}
		return resolvedReimbursements;
	}
	public static List<Reimbursement> getPendingReimbursements(){
		List <Reimbursement> pendingReimbursements = new ArrayList<>();
		
		for (Reimbursement reimbursement: reimbursements) {
			if (reimbursement.getStatus() == Status.Pending) {
				pendingReimbursements.add(reimbursement);
			}
		}
		return pendingReimbursements;
	}
	public Reimbursement getReimbursementbyID(int id) {
		
		for (Reimbursement reimbursement: reimbursements) {
			if (reimbursement.getID() == id) {
				return reimbursement;
			}
		}
		return null;
	}
	public static List<Reimbursement> getReimbursementByAuthor(int userId){
		List <Reimbursement> userReimbursements = new ArrayList<>();
		for (Reimbursement reimbursement: reimbursements) {
			if (reimbursement.getAuthor() == userId || reimbursement.getResolver() == userId) {
				userReimbursements.add(reimbursement);
	}
		}
		return userReimbursements;
	}
}
