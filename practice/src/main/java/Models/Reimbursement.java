package Models;

public class Reimbursement {
	private int id;
	private int author;
	private int resolver;
	private String description;
	//type
	private reimbursementType type;
	//status
	private Status status;
	private double amount;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int id, int author, int resolver, String description,reimbursementType type, Status status,
			double amount) {
		super();
		this.id = id;
		this.author = author;
		this.resolver = resolver;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
	}

	
//	public String toString() {
//		return "Reimbursement [id=" + id + ", author=" + author + ", resolver=" + resolver + ", description="
//				+ description + ", type=" + type + ", status=" + status + ", amount=" + amount + "]";
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public reimbursementType getType() {
		return type;
	}

	public void setType(reimbursementType type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
