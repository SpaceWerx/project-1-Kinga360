package Models;

public enum Roles {
	Employee,Manager;
	private String role;
	Roles (String role){
		this.role = role;
	}
	Roles() {
		// TODO Auto-generated constructor stub
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}

	

