package Modal;

import java.util.Date;

public class EmployeeModal {
	private String employee_id, employee_name, employee_gender, employee_email, employee_phoneNumber, employee_homeTown,
	employee_role, employee_note, employee_state, employee_cmnd, employee_image;
	private Date employee_dob;

	public EmployeeModal(String employee_id, String employee_name, String employee_gender, Date employee_dob,
		String employee_homeTown, String employee_cmnd, String employee_phoneNumber, String employee_email,
		String employee_role, String employee_state, String employee_note, String employee_image) {
	this.employee_id = employee_id;
	this.employee_name = employee_name;
	this.employee_gender = employee_gender;
	this.employee_dob = employee_dob;
	this.employee_email = employee_email;
	this.employee_phoneNumber = employee_phoneNumber;
	this.employee_homeTown = employee_homeTown;
	this.employee_role = employee_role;
	this.employee_note = employee_note;
	this.employee_state = employee_state;
	this.employee_cmnd = employee_cmnd;
	this.employee_image = employee_image;
	}
	
	public String getEmployee_cmnd() {
	return employee_cmnd;
	}
	
	public void setEmployee_cmnd(String employee_cmnd) {
	this.employee_cmnd = employee_cmnd;
	}
	
	public EmployeeModal() {
	
	}
	
	public String getEmployee_id() {
	return employee_id;
	}
	
	public void setEmployee_id(String employee_id) {
	this.employee_id = employee_id;
	}
	
	public String getEmployee_name() {
	return employee_name;
	}
	
	public void setEmployee_name(String employee_name) {
	this.employee_name = employee_name;
	}
	
	public String getEmployee_gender() {
	return employee_gender;
	}
	
	public void setEmployee_gender(String employee_gender) {
	this.employee_gender = employee_gender;
	}
	
	public Date getEmployee_dob() {
	return employee_dob;
	}
	
	public void setEmployee_dob(Date employee_dob2) {
	this.employee_dob = employee_dob2;
	}
	
	public String getEmployee_email() {
	return employee_email;
	}
	
	public void setEmployee_email(String employee_email) {
	this.employee_email = employee_email;
	}
	
	public String getEmployee_phoneNumber() {
	return employee_phoneNumber;
	}
	
	public void setEmployee_phoneNumber(String employee_phoneNumber) {
	this.employee_phoneNumber = employee_phoneNumber;
	}
	
	public String getEmployee_homeTown() {
	return employee_homeTown;
	}
	
	public void setEmployee_homeTown(String employee_password) {
	this.employee_homeTown = employee_password;
	}
	
	public String getEmployee_role() {
	return employee_role;
	}
	
	public void setEmployee_role(String employee_role) {
	this.employee_role = employee_role;
	}
	
	public String getEmployee_note() {
	return employee_note;
	}
	
	public void setEmployee_note(String employee_note) {
	this.employee_note = employee_note;
	}
	
	public String getEmployee_state() {
	return employee_state;
	}
	
	public void setEmployee_state(String employee_state) {
	this.employee_state = employee_state;
	}
	
	public String getEmployee_image() {
	return employee_image;
	}
	
	public void setEmployee_image(String employee_image) {
	this.employee_image = employee_image;
	}
}
