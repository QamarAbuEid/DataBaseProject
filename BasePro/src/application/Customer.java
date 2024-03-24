package application;

import java.time.LocalDate;

public class Customer {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private LocalDate dateOfBirth;
	private String gender;
	public Customer(int id, String name, String address, String phone, String email, LocalDate dateOfBirth,
			String gender) {
		super();
		this.id = id;
		name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomer_name() {
		return name;
	}
	public void setCustomer_name(String customer_name) {
		name = customer_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	

}
