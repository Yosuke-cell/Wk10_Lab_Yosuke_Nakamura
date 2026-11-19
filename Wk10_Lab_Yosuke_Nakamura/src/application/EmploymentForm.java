package application;

import java.sql.Date;

public class EmploymentForm {
/*
 * Add this attributes of the form with appropriate datatype
 * */
	
	private String fullName;
	private String address;
	private String phone;
	private String email;
	private String education;
	private String gender;
	private Date date;
	private String position;
	private double salary;
	private boolean legal;
	private boolean relative;
	private String relative_detail;
	
	
	
	public EmploymentForm(String fullName, String address, String phone, String email, String education, String gender, Date date,
			String position, double salary, boolean legal, boolean relative, String relative_detail) {
		this.fullName = fullName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.education = education;
		this.gender = gender;
		this.date = date;
		this.position = position;
		this.salary = salary;
		this.legal = legal;
		this.relative = relative;
		this.relative_detail = relative_detail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
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
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public boolean isLegal() {
		return legal;
	}
	public void setLegal(boolean legal) {
		this.legal = legal;
	}
	public boolean isRelative() {
		return relative;
	}
	public void setRelative(boolean relative) {
		this.relative = relative;
	}
	public String getRelative_detail() {
		return relative_detail;
	}
	public void setRelative_detail(String relative_detail) {
		this.relative_detail = relative_detail;
	}
	
	
}
