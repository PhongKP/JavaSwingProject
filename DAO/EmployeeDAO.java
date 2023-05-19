package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ConnectionDB.connectionDB;
import Modal.EmployeeModal;

public class EmployeeDAO {
	private connectionDB connection = null;

	public EmployeeDAO() {

	}

	public ArrayList<EmployeeModal> employee_readDB() {
		connection = new connectionDB();
		ArrayList<EmployeeModal> employeeList = new ArrayList<EmployeeModal>();
		try {
			String query = "SELECT * FROM nhanvien";
			ResultSet resultSet = connection.sqlQuery(query, null);
			if (resultSet != null) {
				while (resultSet.next()) {
					String id = resultSet.getString("manv");
					String name = resultSet.getString("tenNV");
					String gender = resultSet.getString("gioitinh");
					Date dob = resultSet.getDate("ngaysinh");
					String homeTown = resultSet.getString("quequan");
					String cmnd = resultSet.getString("CMND");
					String phoneNumber = resultSet.getString("sdt");
					String email = resultSet.getString("email");
					String role = resultSet.getString("vaitro");
					String state = resultSet.getString("trangthai");
					String note = resultSet.getString("ghichu");
					String image = resultSet.getString("hinhanh");
					employeeList.add(new EmployeeModal(id, name, gender, dob, homeTown, cmnd, phoneNumber, email, role,
							state, note, image));
				}
			}
		} catch (SQLException e) {
			System.out.println("Read failed!");
		} finally {
			connection.closeConnect();
		}
		return employeeList;
	}

	public Boolean add(EmployeeModal employee) {
		connection = new connectionDB();
		Boolean check = connection.sqlUpdate(
				"INSERT INTO `nhanvien` (`manv`, `tenNV`, `gioitinh`, `ngaysinh`, `quequan`, `CMND`, `sdt`, `email`, `vaitro`, `trangthai`, `ghichu`, `hinhanh`) VALUES ('"
						+ employee.getEmployee_id() + "', '" + employee.getEmployee_name() + "', '"
						+ employee.getEmployee_gender() + "', '" + employee.getEmployee_dob() + "', '"
						+ employee.getEmployee_homeTown() + "', '" + employee.getEmployee_cmnd() + "', '"
						+ employee.getEmployee_phoneNumber() + "', '" + employee.getEmployee_email() + "', '"
						+ employee.getEmployee_role() + "', '" + employee.getEmployee_state() + "', '"
						+ employee.getEmployee_note() + "', '" + employee.getEmployee_image() + "');",
				null);
		connection.closeConnect();
		return check;
	}

	public Boolean delete(String id) {
		connection = new connectionDB();
		Boolean ok = connection.sqlUpdate("DELETE FROM `nhanvien` WHERE `nhanvien`.`manv` = '" + id + "'", null);
		connection.closeConnect();
		return ok;
	}

	public Boolean update(String employee_id, String employee_name, String employee_gender, Date employee_dob,
			String employee_homeTown, String employee_cmnd, String employee_phoneNumber, String employee_email,
			String employee_role, String employee_state, String employee_note, String employee_image) {
		connection = new connectionDB();
		Boolean check = connection.sqlUpdate("Update nhanvien Set " + "manv='" + employee_id + "',tenNV='"
				+ employee_name + "',gioitinh='" + employee_gender + "',ngaysinh='" + employee_dob + "',quequan='"
				+ employee_homeTown + "',CMND='" + employee_cmnd + "',sdt='" + employee_phoneNumber + "',email='"
				+ employee_email + "',vaitro='" + employee_role + "',trangthai='" + employee_state + "',ghichu='"
				+ employee_note + "',hinhanh='" + employee_image + "' where manv='" + employee_id + "'", null);
		connection.closeConnect();
		return check;
	}
}
