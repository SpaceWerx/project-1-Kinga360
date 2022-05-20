package Models;

import java.util.Objects;

public class Users {
	private String userName;
	private String Password;
	private Roles role;
	private int ID;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Users(int id, String username, String password, Roles role) {
		setID(id);
		setUserName(username);
		setPassword(password);
		setRole(role);
	}
	public void add( String password, Roles role) {
		
		setPassword(password);
		setRole(role);
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(ID, Password, role, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return ID == other.ID && Objects.equals(Password, other.Password) && role == other.role
				&& Objects.equals(userName, other.userName);
	}
	@Override
	public String toString() {
		return "Users [userName=" + userName + ", Password=" + Password + ", role=" + role + ", ID=" + ID + "]";
	}
	public void add(int int1, String string, String string2, Roles valueOf) {
		setID(int1);
		setUserName(string);
		setPassword(string2);
		setRole(valueOf);
		
	}
	
	
}
