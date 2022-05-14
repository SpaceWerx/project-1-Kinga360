package Mock_Data;
import Models.*;
import java.util.*;
public class MockReimbursementData {
	private final ArrayList<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
	public  ArrayList<Reimbursement> mockData() {
	Reimbursement REIMBURSEMENT_TO_PROCESS_1 = new Reimbursement(1, 1, 0, "Oracle Java Certification", Type.Other, Status.Pending,250.00);
	Reimbursement REIMBURSEMENT_TO_PROCESS_2 = new Reimbursement(2, 2, 0, "Travel to Reston HQ", Type.Travel, Status.Pending,600.00);
	Reimbursement REIMBURSEMENT_TO_APPROVED_1 = new Reimbursement(3, 1, 3, "Free Lunch offer from Sean", Type.Food, Status.Approved,25.00);
	Reimbursement REIMBURSEMENT_TO_APPROVED_2 = new Reimbursement(4, 2, 4, "Two night hotel stay near Orlando office for visit.", Type.Lodging, Status.Approved,300.00);
	Reimbursement REIMBURSEMENT_TO_DENIED_1 = new Reimbursement(5, 1, 3, "Rental car to drive from Reston to Orlando.", Type.Travel, Status.Denied,2500.00);
	
	 reimbursement.add(REIMBURSEMENT_TO_PROCESS_1);	
	 reimbursement.add(REIMBURSEMENT_TO_PROCESS_2);	
	 reimbursement.add(REIMBURSEMENT_TO_APPROVED_1);
	 reimbursement.add(REIMBURSEMENT_TO_APPROVED_2);
	 reimbursement.add(REIMBURSEMENT_TO_DENIED_1);
	 
	 return reimbursement;
}
	public ArrayList<Reimbursement> getReimbursement(){
		return reimbursement;}
	public MockReimbursementData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void getNewReimbursement(int author, int resolver, String description, Type type, Status status, double amount) {
		Reimbursement pending = new Reimbursement(reimbursement.size() + 1, author, resolver, description, type, status, amount);
		/**
		for (int i = 0; i < reimbursement.size();i++) {
			
			if (pending.getID() != reimbursement.get(i).getID()) {
		reimbursement.add(pending);
		}
		}
		**/
		reimbursement.add(pending);
	}
	
}
