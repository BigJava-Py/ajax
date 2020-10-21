package vo;

public class User {
	private String userName;
	private String chrName;
	private String password;
	private String mail;
	private String province;
	private String city;
	
	public User() {
		super();
	}
	public User(String userName, String chrName, String password) {
		super();
		this.userName = userName;
		this.chrName = chrName;
		this.password = password;
	}
	
	
	public User(String userName,  String password, String chrName,String mail, String province,
			String city) {
		super();
		this.userName = userName;
		this.chrName = chrName;
		this.password = password;
		this.mail = mail;
		this.province = province;
		this.city = city;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChrName() {
		return chrName;
	}
	public void setChrName(String chrName) {
		this.chrName = chrName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
}
