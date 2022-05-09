package Models;

public enum Type {
Lodging, Travel, Food, Other;
	private String type;
	Type(){
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
