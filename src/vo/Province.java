package vo;

public class Province {
	private String provinceid;
	private String proName;
	
	
	public Province() {
		super();
	}
	
	public Province(String provinceid, String proName) {
		super();
		this.provinceid = provinceid;
		this.proName = proName;
	}

	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
}
