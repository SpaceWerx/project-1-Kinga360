package Service;
import java.util.*;
import Models.*;
import Mock_Data.*;
public class Reimbursement_Services {
	
	private static MockReimbursementData mockData = new MockReimbursementData();
	private static ArrayList<Reimbursement> reimbursements = new ArrayList <Reimbursement>(); 
	private static ArrayList <Reimbursement> pendingReimbursements = new ArrayList <Reimbursement>();
	private static ArrayList <Reimbursement> resolvedReimbursements = new ArrayList<Reimbursement>();
	
	public static void getData() {
		if (reimbursements.isEmpty()){
		mockData.mockData();
		reimbursements = mockData.getReimbursement();
		}
	}
	public static void clearData() {
		reimbursements.clear();
	}
	public static void update(Reimbursement unprocessedReimbursement, int resolverId, Status UpdateStatus) {
		getData();
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
			getData();
			Reimbursement latestReimbursement;
		
			latestReimbursement = reimbursements.get(reimbursements.size() -1);
			int id = latestReimbursement.getID() + 1; //New ID is 1 higher than the highest
			
			reimbursementToBeSubmitted.setID(id);
			reimbursementToBeSubmitted.setStatus(Status.Pending);
			reimbursements.add(reimbursementToBeSubmitted);
		
			
		}
		
		
		
	public static List<Reimbursement> getResolvedReimbursements(){
		getData();
		//reimbursements = mockData.mockData();
		if (resolvedReimbursements.isEmpty()) {
		for (Reimbursement reimbursement: reimbursements) {
			if (reimbursement.getStatus() == Status.Approved || reimbursement.getStatus() == Status.Denied) {
				resolvedReimbursements.add(reimbursement);
			}
		}
		}
		//clearData();
		return resolvedReimbursements;
	}
	public static List<Reimbursement> getPendingReimbursements(){
		getData();
		if (pendingReimbursements.isEmpty()) {
		//ArrayList <Reimbursement> pendingReimbursements = new ArrayList<>();
		
		for (Reimbursement reimbursement: reimbursements) {
				if (reimbursement.getStatus() == Status.Pending) {
					pendingReimbursements.add(reimbursement);	
			}
				
			}
		}
		//clearData();
		return pendingReimbursements;
	}
	
	public static Reimbursement getReimbursementbyID(int id) {
		getData();
		for (Reimbursement reimbursement: reimbursements) {
			if (reimbursement.getID() == id) {
				//clearData();
				return reimbursement;
				
			}
			
		}
		//clearData();
		return null;
	}
	public static List<Reimbursement> getReimbursementByAuthor(int userId){
		getData();
		List <Reimbursement> userReimbursements = new ArrayList<>();
		for (Reimbursement reimbursement: reimbursements) {
			if (reimbursement.getAuthor() == userId || reimbursement.getResolver() == userId) {
				userReimbursements.add(reimbursement);
	}
		}
		
		return userReimbursements;
	}
}
