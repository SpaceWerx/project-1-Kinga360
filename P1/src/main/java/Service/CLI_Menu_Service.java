package Service;
import java.util.*;
import Models.*;
import java.lang.*;


public class CLI_Menu_Service {
	
	public CLI_Menu_Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Scanner scan = new Scanner(System.in);
//	private ArrayList<Integer> validEntries = new ArrayList<Integer>();
	int[] validEntries = {0,1,2,3,4};
	public void submitReimbursement(Users employee) {
		Reimbursement reimbursementToBeSubmitted = new Reimbursement();
		reimbursementToBeSubmitted.setAuthor(employee.getID());
		
		System.out.println("What type of reimbursement would you like to submit?");
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
		System.out.println("1 -> Lodging");
		System.out.println("2 -> Travel");
		System.out.println("3 -> Food");
		System.out.println("4 -> Other");
	/**	validEntries.add(1);
		validEntries.add(2);
		validEntries.add(3);
		validEntries.add(4); **/
		int typeDecision = promptSelection(validEntries);
		switch (typeDecision) {
		case 1:
			reimbursementToBeSubmitted.setType(Type.Lodging);
			break;
			
		case 2:
			reimbursementToBeSubmitted.setType(Type.Travel);
			break;
			
		case 3:
			reimbursementToBeSubmitted.setType(Type.Food);
			break;
			
		case 4:
			reimbursementToBeSubmitted.setType(Type.Other);
			break;
			
		}
		
		System.out.println("Please enter the dollar amount you are requesting to be reimbursed: ");
		System.out.print("$");
		
		reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));
		if (reimbursementToBeSubmitted.getAmount() <=0) {
			System.out.println("Invalid amount is entered, please input the correct dollar amount.");
			boolean valid = false;
			while(!valid) {
				reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));	
				if (reimbursementToBeSubmitted.getAmount() != 0) {
					valid = true;
				}
			}
		}
		System.out.println("Please enter a description/reason for your reimbursement request.");
		
		reimbursementToBeSubmitted.setDescription(scan.nextLine());
		
		if (reimbursementToBeSubmitted.getDescription().trim().equals("")) {
			System.out.println("You cannot submit an empty description, please explain the reason for your request.");
			boolean valid = false;
		}
	}
	/**
	 * Prompts the user for input. <br>
	 * If the answer is provided input is not a valid option, the user will be prompted again until a valid answer is provided.
	 * 
	 * @param validEntries the valid int values that the user must choose from.
	 * @return the selection option as an int.
	 */
	public int promptSelection(ArrayList<Integer> entries) {
		int input;
		boolean valid = false; // flag to track if the input matched the entry.
		
		do { // do - while loop to continue to prompt user until the the response is valid.
			// process the input.
			input = parseIntegerInput(fetchInput());
			
			// check to see if the input matches a valid entry.
			for (int entry : entries) {
				// if it does, we activate the flag, which will end the do-while loop.
				valid = true;
				break; // stop searching for-loop since we already found match.
			}
			if (!valid) {
				System.out.println("Input recieved not a valid option, please try again.");
		}
		
		}while(!valid);
		return input;
	}
	public int promptSelection(int[] entries) {
		int input;
		boolean valid = false; // flag to track if the input matched the entry.
		
		do { // do - while loop to continue to prompt user until the the response is valid.
			// process the input.
			input = parseIntegerInput(fetchInput());
			
			// check to see if the input matches a valid entry.
			for (int entry : entries) {
				// if it does, we activate the flag, which will end the do-while loop.
				valid = true;
				break; // stop searching for-loop since we already found match.
			}
			if (!valid) {
				System.out.println("Input recieved not a valid option, please try again.");
		}
		
		}while(!valid);
		return input;
	}
	public void processReimbursement(Users manager) {
		boolean processPortal = true;
		System.out.println("--------------------------------------------------");
		System.out.println("Welcome to the Manager Processing Portal, " + manager.getUserName());
		System.out.println("--------------------------------------------------");
		System.out.println();
		
		while (processPortal) {
			List<Reimbursement> reimbursement = Reimbursement_Services.getPendingReimbursements();
			if (reimbursement.isEmpty()) {
			System.out.println("There are no reimbursements to process");
			System.out.println("Returning to the previous menu");
			return;	
		}
			int[] ids = new int[reimbursement.size()];
			for (int i = 0; i < reimbursement.size(); i++) {
				Reimbursement r = reimbursement.get(i);
				Users author = User_Services.getUserByID(r.getAuthor());
				System.out.println(r.getID() + "-> " + author.getUserName()+ " :$" + r.getAmount());
				ids[i] = r.getID();
			}
			System.out.println("Please enter the ID of the Reimbursement you wish to process.");
	}
		
}
	/** 
	 * Attempt to parse input from the user as an int to be useful for the control - flow.
	 * Leverages the built in Integer .parseInt method.
	 * Note: If there is not a valid int, such as "hello world",
	 * Integer.parseInt throws a numbersFormatException, which will need to be handled.
	 * 
	 * @param input the user that will be parsed into an int
	 * @return the input as an int or zero if it was malformed.
	 * 
	 */
	public int parseIntegerInput (String input) {
		try {
			return Integer.parseInt(input);
		}catch (NumberFormatException e) {
			System.out.println("The input recieved was malformed, please try agaain.");
			return -1; // return -1 by default to be rejected by other validation contingencies. 
		}
	}
	public double parseDoubleInput (String input) {
		try {
			return Double.parseDouble(input);
		}catch (NumberFormatException e) {
			System.out.println("The input recieved was malformed, please try agaain.");
			return -1; // return -1 by default to be rejected by other validation contingencies. 
		}
}
	public void handlePortal (Roles role) {
		// get the List of employees from the repository layer.
		List<Users> user  = User_Services.getUserByRole(role);
		
		int[] ids = new int[user.size() + 1];
		ids[user.size()] = 0;
		for (int i = 0; i < user.size(); i++) {
			ids[i] = user.get(i).getID();
		}
		// Ask for employee ID number to continue.
		System.out.println("-------------------------------------");
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE.");
		
		//Enhanced for-loop to print out all the users one by one.
		for (Users u : user) {
			System.out.println(u.getID() + " -> " + u.getUserName());
		}
		System.out.println("0 -> Return to Main menu");
		System.out.println();
		
		int userChoice = promptSelection(ids);
		
		if (userChoice == 0) {
			return;
		}
		Users employee = User_Services.getUserByID(userChoice);
		
		if (role == Roles.Manager) {
			System.out.println("Opening manager portal  for " + employee.getUserName());
			displayFinanceManagerMenu(employee);
		} else {
			System.out.println("Opening employee portal for " + employee.getUserName());
			displayEmployee(employee);
		}
	}
	/** 
	 * Prompts users for input, ignoring everything after the first whitespace.
	 * For example, "123 456" will be converted to "123", and the "456" will be discarded.
	 * This is known as the first "taken" of the users input.
	 * 
	 * @return the first taken of the users input on the next line.
	 */
	public String fetchInput() {
		//Scan.nextLine() obtains the entire line, such as "123 456"
		// split() turns into an array supported by whitespace, such as ["123 456"]
		//[0] keeps only the first element, leaving "123"
		return scan.nextLine().split( " ")[0];
	}
	public void displayPendingReimbursement() {
		List<Reimbursement> pendingReimbursements = Reimbursement_Services.getPendingReimbursements();
		
		if (pendingReimbursements.isEmpty()) {
			System.out.println("No pending requests");
			System.out.println("Returing to Previous menu...");
		}
		for (Reimbursement r : pendingReimbursements) {
			System.out.print(r);
		}
	}
	public void displayResolvedReimbursement() {
		List<Reimbursement> resolvedReimbursements;
		
			resolvedReimbursements = Reimbursement_Services.getResolvedReimbursements();
			if (resolvedReimbursements.isEmpty()) {
				System.out.println("No pending requests");
				System.out.println("Returing to Previous menu...");
			} 
			for (Reimbursement r : resolvedReimbursements) {
				System.out.print(r);
			}
		
			
		
		
		
	}
	public void displayPreviousRequests(Users employee) {
		List<Reimbursement> reimbursements;
	
			reimbursements = Reimbursement_Services.getReimbursementByAuthor(employee.getID());
			if (reimbursements.isEmpty()) {
				System.out.println("No pending requests");
				System.out.println("Returing to Previous menu...");
			} 
			for (Reimbursement r : reimbursements) {
				System.out.print(r);
			}	
		
			
		
		
	}
	public void displayMenu() {
		boolean menuOptions = true; // Using this to let the menu continue after user input.
		
		//Greetings for the user
		System.out.println("----------------------------------------");
		System.out.println("Welcome to Revature reimbursement system");
		System.out.println("----------------------------------------");
		System.out.println();
		
		// display the menu as long as the menuOptions boolean == true
		// display all menu options until boolean == false
		while(menuOptions) {
			// menu options
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> Employee Portal");
			System.out.println("2 -> Finance Manager Portal");
			System.out.println("0 -> Exit Application");
			
		/**	validEntries.add(1);
			validEntries.add(2);
			validEntries.add(0); **/
			
			//The user chooses a menu option and the scanner takes the input and put in into an int variable.
			//Calls the promptSelection() helper method to handle validation
			// The parameters list the valid options that the user must choose from.
			int firstChoice = promptSelection(validEntries);
			
			//Takes the user input and the switch statement executes the appropriate code
			switch(firstChoice){
				
			//A break in each case block so the other cases will not run.
			case 1:
				handlePortal(Roles.Employee);
				break;
				
			case 2:
				handlePortal(Roles.Manager);
				break;
				
			case 0:
				System.out.println("\n Have a great day! Goodbye.");
				menuOptions = false;
				break;
				
			}
			
		}//end this while loop
	} //end display method
	public void displayFinanceManagerMenu(Users manager) {
		boolean managerPortal = true;
		
		System.out.println("-----------------------------------------");
		System.out.println("Welcome to the Manager Portal, " + manager.getUserName());
		System.out.println("-----------------------------------------");
		System.out.println();
		
		while (managerPortal) {
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> View all pending reimburements");
			System.out.println("2 -> View all resolved reimburements");
			System.out.println("3 -> Process a reimbursement");
			System.out.println("0 -> Return to Main Menu");
			
		/**	validEntries.add(1);
			validEntries.add(2);
			validEntries.add(3);
			validEntries.add(0); **/
			//The user chooses a menu option and the scanner takes the input and put it into an int variable
			int firstChoice = promptSelection(validEntries); 
			switch(firstChoice) {
			//A break in each case block so the other cases will not run.
		case 1:
			displayPendingReimbursement();
			break;
		case 2:
			displayResolvedReimbursement();
			break;
		case 3:
			processReimbursement(manager);
			break;
		case 0:
			System.out.println("Returning to Main Menu...");
			managerPortal = false;
			break;
			}
			
		}
	}
	public void displayEmployee(Users employee) {
boolean employeePortal = true;
		
		System.out.println("-----------------------------------------");
		System.out.println("Welcome to the Employee Portal, " + employee.getUserName());
		System.out.println("-----------------------------------------");
		System.out.println();
		
		while (employeePortal) {
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> View all pending reimburements");
			System.out.println("2 -> View all resolved reimburements");
			System.out.println("3 -> Process a reimbursement");
			System.out.println("0 -> Return to Main Menu");
			
		/**	validEntries.add(1);
			validEntries.add(2);
			validEntries.add(3);
			validEntries.add(0); **/
			
			//The user chooses a menu option and the scanner takes the input and put it into an int variable
			int firstChoice = promptSelection(validEntries); 
			switch(firstChoice) {
			//A break in each case block so the other cases will not run.
		case 1:
			displayPendingReimbursement();
			break;
		case 2:
			displayResolvedReimbursement();
			break;
		case 3:
			processReimbursement(employee);
			break;
		case 0:
			System.out.println("Returning to Main Menu...");
			employeePortal = false;
			break;
			}
			
		}
	}
		
}

