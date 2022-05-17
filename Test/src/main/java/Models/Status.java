package Models;

public enum Status {
	Pending, Approved, Denied;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	Status(){
		
	}
	
}
