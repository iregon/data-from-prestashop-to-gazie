package dataFromPrestashopToGazie;

public class PrestashopCostumer {
	private String firstname = "";
	private String lastname = "";
	private String address = "";
	private String postcode = "";
	private String city = "";
	private String phone = "";
	
	public PrestashopCostumer(String firstname, String lastname, String address, 
			String postcode, String city, String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.postcode = postcode;
		this.city = city;
		this.phone = phone;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getInsertQueryGazie() {
		return "";
	}
}
