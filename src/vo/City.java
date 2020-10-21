package vo;

public class City {
	private String id;
	private String city;
	private String pid;
	
	public City() {
		super();
	}
	public City(String id, String city, String pid) {
		super();
		this.id = id;
		this.city = city;
		this.pid = pid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}
