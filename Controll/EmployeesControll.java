package Controll;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Check.CheckLoi;
import Modal.EmployeeModal;
import View.nhanVienView;

public class EmployeesControll implements ActionListener, MouseInputListener{
	private nhanVienView employee_view;
	private File selectedFile;

	public EmployeesControll(nhanVienView employee_view) {
		this.employee_view = employee_view;
	}

	public nhanVienView getEmployee_view() {
		return employee_view;
	}

	public void setEmployee_view(nhanVienView employee_view) {
		this.employee_view = employee_view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// get values from the table when user selects in table
		try {
			rowClicked();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// add product to the table (button clicked)
		if (e.getActionCommand().equalsIgnoreCase("Chọn ảnh")) {
			selectImage();
		}

		// add product to the table (button clicked)
		if (e.getActionCommand().equalsIgnoreCase("Thêm"))
			try {
				addNewValue();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if (e.getActionCommand().equalsIgnoreCase("Cập nhật"))
			try {
				updateClicked();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

//		 find (right panel)
		if (e.getActionCommand().equalsIgnoreCase("Tìm"))
			updateTableName();
		else if (e.getSource() == employee_view.getEmployee_role_find_text()
				|| e.getSource() == employee_view.getEmployee_state_find_text()) {
			updateTableTypeAndState();
		}
	}

	private void updateTableTypeAndState() {
		String selectedRole = employee_view.getEmployee_role_find_text().getSelectedItem().toString();
		String selectedState = employee_view.getEmployee_state_find_text().getSelectedItem().toString();
		employee_view.getModel().setRowCount(0);

		// Fetch products from database based on selected type and state
		ArrayList<EmployeeModal> employeeList = employee_view.getBus().readDB_();
		ArrayList<EmployeeModal> filteredList = new ArrayList<EmployeeModal>(); // to store the product that is
																					// statified

		for (EmployeeModal employee : employeeList)
			if ((selectedRole.equalsIgnoreCase("Tất cả") || employee.getEmployee_role().equalsIgnoreCase(selectedRole))
					&& (selectedState.equalsIgnoreCase("Tất cả")
							|| employee.getEmployee_state().equalsIgnoreCase(selectedState)))
				filteredList.add(employee);

		// Add filtered products to table
		for (EmployeeModal employee : filteredList)
			employee_view.getModel().addRow(new Object[] { employee.getEmployee_id(), employee.getEmployee_name(),
					employee.getEmployee_role() });
	}

	private void updateTableName() {
		String name_find = employee_view.getEmployee_name_text_right().getText().toLowerCase();
		int _row = employee_view.getModel().getRowCount();
		int _column = employee_view.getModel().getColumnCount();

		if (name_find.isEmpty()) {
			// clear existing table
			employee_view.getModel().setRowCount(0);
			// fetch data from database and update table
			ArrayList<EmployeeModal> employeeList = employee_view.getBus().readDB_();
			for (EmployeeModal employee : employeeList) {
				employee_view.getModel().addRow(new String[] { employee.getEmployee_id(), employee.getEmployee_name(),
						employee.getEmployee_role() });
			}
		} else {
			for (int i = 0; i < _row; i++)
				for (int j = 0; j < _column; j++) {
					String cellValue = employee_view.getModel().getValueAt(i, j).toString();
					if (cellValue.contains(name_find))
						employee_view.getEmployee_table().changeSelection(i, j, false, false);
				}

			for (int i = _row - 1; i >= 0; i--) {
				String name = employee_view.getModel().getValueAt(i, 1).toString();
				if (!name.toLowerCase().contains(name_find))
					employee_view.getModel().removeRow(i);
			}
		}

	}

	public void rowClicked() throws SQLException {
		// get the selected row from the table
		int getSelectedRow = employee_view.getEmployee_table().getSelectedRow();
		EmployeeModal employee = null;
		if (getSelectedRow != -1) {
			String id = employee_view.getEmployee_table().getValueAt(getSelectedRow, 0).toString();
			employee = employee_view.getBus().findById(id);
			if (employee != null) {
				// set the attributes to the corresponding JTextFields
				employee_view.getEmployee_id_text().setText(employee.getEmployee_id());
				employee_view.getEmployee_name_text().setText(employee.getEmployee_name());
				if (employee.getEmployee_gender().equalsIgnoreCase("nam"))
					employee_view.getEmployee_sex_male().setSelected(true);
				else if (employee.getEmployee_gender().equalsIgnoreCase("nữ"))
					employee_view.getEmployee_sex_female().setSelected(true);
				employee_view.getDateChooser().setDate(employee.getEmployee_dob());
				employee_view.getEmployee_homeTown_text().setText(employee.getEmployee_homeTown());
				employee_view.getEmployee_cmnd_text().setText(employee.getEmployee_cmnd());
				employee_view.getEmployee_phone_text().setText(employee.getEmployee_phoneNumber());
				employee_view.getEmployee_email_text().setText(employee.getEmployee_email());
				employee_view.getEmployee_role_list().setSelectedItem(employee.getEmployee_role());
				employee_view.getEmployee_state_list().setSelectedItem(employee.getEmployee_state());
				employee_view.getEmployee_note_text().setText(employee.getEmployee_note());

				String imageString = employee.getEmployee_image();
				// To decode, we have to check the string is greater than 2 byte
				if (imageString != null && !imageString.isEmpty() && imageString.length() >= 2
						&& CheckLoi.isBase64(imageString)) {
					byte[] imageData = Base64.getDecoder().decode(imageString);
					ImageIcon imageIcon = new ImageIcon(imageData);
					Image scaleImage = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
					ImageIcon new_imageIcon = new ImageIcon(scaleImage);
					employee_view.getImage().setIcon(new_imageIcon);
				} else
					employee_view.getImage().setIcon(null);
			}
		}
	}

	// check whether email, phoneNumber, cmnd, id exists
	public Boolean checkExist(String email, String phoneNumber, String cmnd, String id) {
		ArrayList<EmployeeModal> employees = employee_view.getBus().getEmployees();
		for (int i = 0; i < employees.size(); i++)
			if (employee_view.getModel().getValueAt(i, 0).toString().equalsIgnoreCase(id)) {
				JOptionPane.showMessageDialog(null, "ID đã tồn tại!");
				return false;
			}

		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).getEmployee_phoneNumber().toString().equalsIgnoreCase(phoneNumber)) {
				JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!");
				return false;
			}
		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).getEmployee_email().toString().equalsIgnoreCase(email)) {
				JOptionPane.showMessageDialog(null, "Email đã tồn tại!");
				return false;
			}
		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).getEmployee_cmnd().toString().equalsIgnoreCase(cmnd)) {
				JOptionPane.showMessageDialog(null, "Số cmnd đã tồn tại!");
				return false;
			}

		return true;
	}

	// check whether email, phoneNumber, cmnd, id exists
	public Boolean checkExistForUpdate(String email, String phoneNumber, String cmnd, String id) {
		ArrayList<EmployeeModal> employees = employee_view.getBus().getEmployees();
		for (int i = 0; i < employees.size(); i++)
			if (employee_view.getModel().getValueAt(i, 0).toString().equalsIgnoreCase(id)
					&& i != employee_view.getEmployee_table().getSelectedRow()) {
				JOptionPane.showMessageDialog(null, "ID đã tồn tại!");
				return false;
			}

		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).getEmployee_phoneNumber().toString().equalsIgnoreCase(phoneNumber)
					&& !employees.get(i).getEmployee_id().equals(id)) {
				JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!");
				return false;
			}
		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).getEmployee_email().toString().equalsIgnoreCase(email)
					&& !employees.get(i).getEmployee_id().equals(id)) {
				JOptionPane.showMessageDialog(null, "Email đã tồn tại!");
				return false;
			}
		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).getEmployee_cmnd().toString().equalsIgnoreCase(cmnd)
					&& !employees.get(i).getEmployee_id().equals(id)) {
				JOptionPane.showMessageDialog(null, "Số cmnd đã tồn tại!");
				return false;
			}

		return true;
	}

	public void setDefaultValue() {
		// clear field after clicking the add and update button into the table
		employee_view.getEmployee_id_text().setText(null);
		employee_view.getEmployee_name_text().setText(null);
		employee_view.getGroup_radio().clearSelection();
		employee_view.getDateChooser().setDate(null);
		employee_view.getEmployee_homeTown_text().setText(null);
		employee_view.getEmployee_cmnd_text().setText(null);
		employee_view.getEmployee_phone_text().setText(null);
		employee_view.getEmployee_email_text().setText(null);
		employee_view.getEmployee_role_list().setSelectedItem(null);
		employee_view.getEmployee_state_list().setSelectedItem(null);
		employee_view.getEmployee_note_text().setText(null);
		employee_view.getImage().setIcon(null);
	}

	public void addNewValue() throws IOException {
		// get values from view
		String getid = employee_view.getEmployee_id_text().getText();
		String getName = employee_view.getEmployee_name_text().getText();
		String getGender = "";
		if (employee_view.getEmployee_sex_male().isSelected())
			getGender = employee_view.getEmployee_sex_male().getText();
		else if (employee_view.getEmployee_sex_female().isSelected())
			getGender = employee_view.getEmployee_sex_female().getText();
		java.util.Date selectedDate = employee_view.getDateChooser().getDate();
		String gethomeTown = employee_view.getEmployee_homeTown_text().getText();
		String getCMND = employee_view.getEmployee_cmnd_text().getText();
		String getphoneNumber = employee_view.getEmployee_phone_text().getText();
		String getEmail = employee_view.getEmployee_email_text().getText();
		String getRole = employee_view.getEmployee_role_list().getSelectedItem().toString();
		String getState = employee_view.getEmployee_state_list().getSelectedItem().toString();
		String getNote = employee_view.getEmployee_note_text().getText();
		String getImage = storeImage();
		// check whether fields are null
		if (getid.isEmpty() || getName.isEmpty() || getGender.isEmpty() || gethomeTown.isEmpty() || getCMND.isEmpty()
				|| getphoneNumber.isEmpty() || getEmail.isEmpty() || getRole.isEmpty() || getState.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không có giá trị!");
			return;
		}

		// set valuse to model
		EmployeeModal new_row_model = new EmployeeModal();
		if (CheckLoi.isEmployeeId(getid))
			new_row_model.setEmployee_id(getid);
		else {
			JOptionPane.showMessageDialog(null, "ID không hợp lệ");
			return;
		}
		new_row_model.setEmployee_name(getName);
		new_row_model.setEmployee_gender(getGender);
		if (selectedDate == null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày");
			return;
		}
		java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
		new_row_model.setEmployee_dob(sqlDate);
		new_row_model.setEmployee_homeTown(gethomeTown);
		if (CheckLoi.isCMND(getCMND))
			new_row_model.setEmployee_cmnd(getCMND);
		else {
			JOptionPane.showMessageDialog(null, "CMND không hợp lệ");
			return;
		}
		if (CheckLoi.isPhoneNumber(getphoneNumber))
			new_row_model.setEmployee_phoneNumber(getphoneNumber);
		else {
			JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
			return;
		}
		if (CheckLoi.isEmail(getEmail))
			new_row_model.setEmployee_email(getEmail);
		else {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ");
			return;
		}
		new_row_model.setEmployee_role(getRole);
		new_row_model.setEmployee_state(getState);
		new_row_model.setEmployee_note(getNote);
		new_row_model.setEmployee_image(getImage); // add image

		// check if an employee with the same ID already exists
		if (!checkExist(new_row_model.getEmployee_email(), new_row_model.getEmployee_phoneNumber(),
				new_row_model.getEmployee_cmnd(), new_row_model.getEmployee_id()))
			return;

		ArrayList<EmployeeModal> new_table = new ArrayList<EmployeeModal>();
		new_table.add(new_row_model); // add to the table

		// add to the table
		employee_view.getModel().addRow(new Object[] { getid, getName, getRole });

		// add to the database
		if (employee_view.getBus().add(new_row_model))
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		setDefaultValue();
	}

	public void updateClicked() throws IOException {
		// check the same id, email, phoneNumber and cmnd
		if (!checkExistForUpdate(employee_view.getEmployee_email_text().getText(),
				employee_view.getEmployee_phone_text().getText(), employee_view.getEmployee_cmnd_text().getText(),
				employee_view.getEmployee_id_text().getText()))
			return;

		// update the employee in the database
		boolean isSuccess = employee_view.getBus().update(employee_view.getEmployee_id_text().getText(),
				employee_view.getEmployee_name_text().getText(),
				employee_view.getEmployee_sex_male().isSelected() ? "nam" : "nữ",
				new java.sql.Date(employee_view.getDateChooser().getDate().getTime()),
				employee_view.getEmployee_homeTown_text().getText(), employee_view.getEmployee_cmnd_text().getText(),
				employee_view.getEmployee_phone_text().getText(), employee_view.getEmployee_email_text().getText(),
				employee_view.getEmployee_role_list().getSelectedItem().toString(),
				employee_view.getEmployee_state_list().getSelectedItem().toString(),
				employee_view.getEmployee_note_text().getText(), storeImage());

		// update the row in the JTable
		String[] newRow = { employee_view.getEmployee_id_text().getText(),
				employee_view.getEmployee_name_text().getText(),
				employee_view.getEmployee_role_list().getSelectedItem().toString() };
		int getSelectedRow = employee_view.getEmployee_table().getSelectedRow();
		for (int i = 0; i < employee_view.getEmployee_table().getColumnCount(); i++)
			employee_view.getEmployee_table().setValueAt(newRow[i], getSelectedRow, i);

		if (isSuccess)
			JOptionPane.showMessageDialog(null, "Cập nhật thành công");

		employee_view.getEmployee_table().repaint();
		setDefaultValue();
	}

	public void selectImage() {
		// create a file chooser dialog
		JFileChooser selectImage = new JFileChooser();
		selectImage.setDialogTitle("Chọn ảnh");
		selectImage.setFileSelectionMode(JFileChooser.FILES_ONLY);
		selectImage.setAcceptAllFileFilterUsed(false);

		// limit the selectable files to images only
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
		selectImage.addChoosableFileFilter(filter);

		// show the file chooser dialog
		int result = selectImage.showOpenDialog(employee_view);
		if (result == JFileChooser.APPROVE_OPTION) {
			// do something with the selected image file, such as displaying it on the
			// screen
			selectedFile = selectImage.getSelectedFile();
			ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
			Image scaledImage = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
			employee_view.getImage().setIcon(scaledIcon);
		}
	}

	public String storeImage() throws IOException {
		if (selectedFile == null) {
			// handle case where no file has been selected
			return null;
		}

		// Read the image data from the file
		byte[] imageBytes = new byte[(int) selectedFile.length()];
		FileInputStream fis = new FileInputStream(selectedFile);
		fis.read(imageBytes);
		fis.close();

		// Encode the image data as a Base64 string
		String imageDataString = Base64.getEncoder().encodeToString(imageBytes);

		// Return the encoded image data as a string
		return imageDataString;
	}

}
