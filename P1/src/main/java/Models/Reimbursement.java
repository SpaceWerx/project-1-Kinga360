package Models;

import java.util.Objects;

public class Reimbursement {
	private int ID;
	private int author;
	private int resolver;
	private String Description;
	private Type type;
	private Status status;
	private double amount;
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
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
	@Override
	public int hashCode() {
		return Objects.hash(Description, ID, amount, author, resolver, status, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Objects.equals(Description, other.Description) && ID == other.ID
				&& Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && author == other.author
				&& resolver == other.resolver && status == other.status && type == other.type;
	}
	@Override
	public String toString() {
		return "Reimbursement [ID=" + ID + ", author=" + author + ", resolver=" + resolver + ", Description="
				+ Description + ", type=" + type + ", status=" + status + ", amount=" + amount + "]";
	}
	
}
