package mockData;

import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement;
import Models.Status;
import Models.reimbursementType;

public class MockReimbursementData {
	private static final List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
	
	public MockReimbursementData() 
	{
		MockUserData userData = new MockUserData();
		
		Reimbursement REIMBURSEMENT_TO_PROCESS_1 = new Reimbursement(1,1,0,"Oracle Java Certification",reimbursementType.OTHER,Status.PENDING,250.00);
		Reimbursement REIMBURSEMENT_TO_PROCESS_2 = new Reimbursement(2,2,0,"Travel to Reston HQ",reimbursementType.TRAVEL,Status.PENDING,600.00);
		Reimbursement REIMBURSEMENT_APPROVED_1 = new Reimbursement(3,1,3,"Free lunch offer from Sean",reimbursementType.FOOD,Status.APPROVED,25.00);
		Reimbursement REIMBURSEMENT_APPROVED_2 = new Reimbursement(4,2,4,"2-night hotel stay near Orlando Office for visit",reimbursementType.LODGING,Status.APPROVED,300.00);
		Reimbursement REIMBURSEMENT_DENIED_1 = new Reimbursement(5,1,3,"Rental Car to drive from Reston to Orlando",reimbursementType.TRAVEL,Status.DENIED,2500.00);
		
		reimbursements.add(REIMBURSEMENT_TO_PROCESS_1);
		reimbursements.add(REIMBURSEMENT_TO_PROCESS_2);
		reimbursements.add(REIMBURSEMENT_APPROVED_1);
		reimbursements.add(REIMBURSEMENT_APPROVED_2);
		reimbursements.add(REIMBURSEMENT_DENIED_1);
		
	}
	public static List<Reimbursement> getReimbursements()
	{
		return reimbursements;
	}

}
