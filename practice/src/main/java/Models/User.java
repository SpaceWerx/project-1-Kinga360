package Models;

package Models;

public class User {
	private int user_ID;
	private String userName;
	private String passWord;
	//role
	private Role role;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int user_ID, String userName, String passWord, Role role) {
		super();
		this.user_ID = user_ID;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}
	public User(String userName, String passWord, Role role) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}


	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((passWord == null) ? 0 : passWord.hashCode());
//		result = prime * result + ((role == null) ? 0 : role.hashCode());
//		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
//		result = prime * result + user_ID;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		User other = (User) obj;
//		if (passWord == null) {
//			if (other.passWord != null)
//				return false;
//		} else if (!passWord.equals(other.passWord))
//			return false;
//		if (role != other.role)
//			return false;
//		if (userName == null) {
//			if (other.userName != null)
//				return false;
//		} else if (!userName.equals(other.userName))
//			return false;
//		if (user_ID != other.user_ID)
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "User [user_ID=" + user_ID + ", userName=" + userName + ", passWord=" + passWord + ", role=" + role
//				+ "]";
//	}

}