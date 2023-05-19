package BUS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ConnectionDB.connectionDB;
import DAO.EmployeeDAO;
import Modal.EmployeeModal;

public class employeeBUS {
	private ArrayList<EmployeeModal> employeeList = null;
	private EmployeeDAO employeeDAO = null;

	public employeeBUS() {
		employeeList = new ArrayList<EmployeeModal>();
		employeeDAO = new EmployeeDAO();
		employeeList = employeeDAO.employee_readDB();
	}

	public void show() {
		employeeList.forEach((employee) -> {
			System.out.println(employee.getEmployee_id() + " ");
			System.out.println(employee.getEmployee_name() + " ");
			System.out.println(employee.getEmployee_gender() + " ");
			System.out.println(employee.getEmployee_dob() + " ");
			System.out.println(employee.getEmployee_homeTown() + " ");
			System.out.println(employee.getEmployee_cmnd() + " ");
			System.out.println(employee.getEmployee_phoneNumber() + " ");
			System.out.println(employee.getEmployee_email() + " ");
			System.out.println(employee.getEmployee_role() + " ");
			System.out.println(employee.getEmployee_state() + " ");
			System.out.println(employee.getEmployee_note() + " ");
		});
	}

	public String[] getHeader() {
		return new String[] { "Mã nhân viên", "Tên nhân viên", "Vai trò" };
	}

	public void readDB() {
		employeeList = employeeDAO.employee_readDB();
	}

	// Method for developer
	public ArrayList<EmployeeModal> readDB_() {
		employeeList = employeeDAO.employee_readDB();
		return employeeList;
	}

	public EmployeeModal getEmployee(String id) {
		for (EmployeeModal employee : employeeList)
			if (id.equalsIgnoreCase(employee.getEmployee_id()))
				return employee;
		return null;
	}

	public ArrayList<EmployeeModal> getEmployees() {
		return employeeList;
	}

	public Boolean add(EmployeeModal employee) {
		boolean check = employeeDAO.add(employee);

		if (check)
			employeeList.add(employee);
		return check;
	}

	public Boolean add(String employee_id, String employee_name, String employee_gender, Date employee_dob,
			String employee_homeTown, String employee_cmnd, String employee_phoneNumber, String employee_email,
			String employee_role, String employee_state, String employee_note) {
		EmployeeModal employee = new EmployeeModal();
		employee.setEmployee_id(employee_id);
		employee.setEmployee_name(employee_name);
		employee.setEmployee_gender(employee_gender);
		employee.setEmployee_dob(employee_dob);
		employee.setEmployee_homeTown(employee_homeTown);
		employee.setEmployee_cmnd(employee_cmnd);
		employee.setEmployee_phoneNumber(employee_phoneNumber);
		employee.setEmployee_email(employee_email);
		employee.setEmployee_role(employee_role);
		employee.setEmployee_state(employee_state);
		employee.setEmployee_note(employee_note);
		return add(employee);
	}

	public ArrayList<EmployeeModal> getProducts() {
		return employeeList;
	}

	public Boolean update(String employee_id, String employee_name, String employee_gender, Date employee_dob,
			String employee_homeTown, String employee_cmnd, String employee_phoneNumber, String employee_email,
			String employee_role, String employee_state, String employee_note, String employee_image) {
		Boolean check = employeeDAO.update(employee_id, employee_name, employee_gender, employee_dob, employee_homeTown,
				employee_cmnd, employee_phoneNumber, employee_email, employee_role, employee_state, employee_note,
				employee_image);

		if (check) {
			employeeList.forEach((employee) -> {
				if (employee.getEmployee_id().equals(employee_id)) {
					employee.setEmployee_name(employee_name);
					employee.setEmployee_gender(employee_gender);
					employee.setEmployee_dob(employee_dob);
					employee.setEmployee_homeTown(employee_homeTown);
					employee.setEmployee_cmnd(employee_cmnd);
					employee.setEmployee_phoneNumber(employee_phoneNumber);
					employee.setEmployee_email(employee_email);
					employee.setEmployee_role(employee_role);
					employee.setEmployee_state(employee_state);
					employee.setEmployee_note(employee_note);
					employee.setEmployee_image(employee_image);
				}
			});
		}

		return check;
	}

	public EmployeeModal findById(String id) throws SQLException {
		String sql = "SELECT * FROM nhanvien WHERE manv = ?";
		connectionDB con = new connectionDB();
		Connection connection = con.connectDB();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			EmployeeModal employee = new EmployeeModal();
			employee.setEmployee_id(id);
			employee.setEmployee_name(resultSet.getString("tenNV"));
			employee.setEmployee_gender(resultSet.getString("gioitinh"));
			employee.setEmployee_dob(resultSet.getDate("ngaysinh"));
			employee.setEmployee_homeTown(resultSet.getString("quequan"));
			employee.setEmployee_cmnd(resultSet.getString("CMND"));
			employee.setEmployee_phoneNumber(resultSet.getString("sdt"));
			employee.setEmployee_email(resultSet.getString("email"));
			employee.setEmployee_role(resultSet.getString("vaitro"));
			employee.setEmployee_state(resultSet.getString("trangthai"));
			employee.setEmployee_note(resultSet.getString("ghichu"));
			employee.setEmployee_image(resultSet.getString("hinhanh"));
			con.closeConnect();
			return employee;
		} else
			return null;
	}
}
