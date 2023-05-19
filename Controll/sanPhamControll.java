package Controll;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Check.CheckLoi;
import Modal.ProductModal;
import View.sanPhamView;

public class sanPhamControll implements ActionListener, MouseListener {

	private sanPhamView product_view;
	private File selectedFile;

	public sanPhamControll(sanPhamView product_view) {
		this.product_view = product_view;
	}

	public sanPhamControll() {
		this.product_view = new sanPhamView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// add product to the table (button clicked)
		if (e.getActionCommand().equalsIgnoreCase("Chọn ảnh")) {
			selectImage();
		}

		// add product
		if (e.getActionCommand().equalsIgnoreCase("Thêm"))
			try {
				addNewValue();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		else if (e.getActionCommand().equalsIgnoreCase("Cập nhật"))
			try {
				updateNewValue();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		// find product (name)
		if (e.getActionCommand().equalsIgnoreCase("Tìm"))
			updateTableName();
		// find product (type, state)
		else if (e.getSource() == product_view.getProduct_type_find_text()) {
			updateTableTypeAndState();
		} else if (e.getSource() == product_view.getProduct_state_find_text()) {
			updateTableTypeAndState();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// get value from the table
		int getSelectedRow = product_view.getProduct_table().getSelectedRow();

		// get amount from database
		ProductModal product = null;
		if (getSelectedRow != -1) {
			String new_id = product_view.getProduct_table().getValueAt(getSelectedRow, 0).toString();
			try {
				product = product_view.getBus().findById(new_id);
				if (product != null) {
					// set the attributes to the correspoding JTextFields
					product_view.getProduct_id_text().setText(product.getProduct_id());
					product_view.getProduct_name_text().setText(product.getProduct_name());
					product_view.getProduct_type_text().setSelectedItem(product.getProduct_type());
					product_view.getProduct_state_text().setSelectedItem(product.getProduct_state());
					product_view.getProduct_price_text().setText(String.valueOf(product.getProduct_price()));
					product_view.getProduct_amount_text().setText(String.valueOf(product.getProduct_amount()));
					product_view.getProduct_note_text().setText(product.getProduct_note());

					String imageString = product.getProduct_image();
					// To decode, we have to check the string is greater than 2 byte
					if (imageString != null && !imageString.isEmpty() && imageString.length() >= 2
							&& CheckLoi.checkBase64(imageString)) {
						byte[] imageData = Base64.getDecoder().decode(imageString);
						ImageIcon imageIcon = new ImageIcon(imageData);
						Image scaleImage = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
						ImageIcon new_imageIcon = new ImageIcon(scaleImage);
						product_view.getImage().setIcon(new_imageIcon);
					} else
						product_view.getImage().setIcon(null);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	public void setDefaultValue() {
		// set default after clicking the add and update button into the table
		product_view.getProduct_id_text().setText(null);
		product_view.getProduct_type_text().setSelectedItem("Tất cả");
		product_view.getProduct_state_text().setSelectedItem("Tất cả");
		product_view.getProduct_name_text().setText(null);
		product_view.getProduct_price_text().setText(null);
		product_view.getProduct_amount_text().setText(null);
		product_view.getImage().setIcon(null);

	}

	public void addNewValue() throws IOException {
		int _row = product_view.getModel().getRowCount();

		// get values from table
		String getid = product_view.getProduct_id_text().getText();
		String getType = product_view.getProduct_type_text().getSelectedItem().toString();
		String getState = product_view.getProduct_state_text().getSelectedItem().toString();
		String getName = product_view.getProduct_name_text().getText();
		String getPrice = product_view.getProduct_price_text().getText();
		String getNote = product_view.getProduct_note_text().getText();
		String getImage = storeImage();
		String[] new_row = { getid, getName, getType, getPrice, getState };

		// set value to product
		ProductModal new_row_model = new ProductModal();
		if (CheckLoi.isProductId(getid))
			new_row_model.setProduct_id(getid);
		else {
			JOptionPane.showMessageDialog(null, "ID không hợp lệ!");
			return;
		}
		new_row_model.setProduct_type(getType);
		new_row_model.setProduct_state(getState);
		new_row_model.setProduct_name(getName);
		new_row_model.setProduct_price(Float.parseFloat(getPrice));
		new_row_model.setProduct_amount("0");
		new_row_model.setProduct_note(getNote);
		new_row_model.setProduct_image(getImage);

		// check if id is the same or the field is null
		for (int i = 0; i < _row; i++)
			if (product_view.getModel().getValueAt(i, 0).toString().equalsIgnoreCase(getid)) {
				JOptionPane.showMessageDialog(null, "ID đã tồn tại!");
				return;
			}
		if (getid.isEmpty() || getName.isEmpty() || getPrice.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không có giá trị!");
			return;
		}

		// add to the table
		product_view.getModel().addRow(new_row);

		// add to the database
		if (product_view.getBus().add(new_row_model))
			JOptionPane.showMessageDialog(null, "Thêm thành công.");

		// set default values
		setDefaultValue();
	}

	public void updateNewValue() throws NumberFormatException, IOException {
		int _column = product_view.getModel().getColumnCount();
		int _row = product_view.getModel().getRowCount();
		int getSelectedRow = product_view.getProduct_table().getSelectedRow();
		String getSelectedId = product_view.getProduct_table().getValueAt(getSelectedRow, 0).toString();

		String getType = product_view.getProduct_type_text().getSelectedItem().toString();
		String getState = product_view.getProduct_state_text().getSelectedItem().toString();
		String getName = product_view.getProduct_name_text().getText();
		String getPrice = product_view.getProduct_price_text().getText();
		String getAmount = product_view.getProduct_amount_text().getText();
		String getNote = product_view.getProduct_note_text().getText();
		String getImage = storeImage();

		String[] new_row = { getSelectedId, getName, getType, getPrice, getState };

		// check if id is the same or the field is null
		for (int i = 0; i < _row; i++)
			if (product_view.getModel().getValueAt(i, 0).toString().equalsIgnoreCase(getSelectedId)
					&& i != getSelectedRow) {
				JOptionPane.showMessageDialog(null, "ID đã tồn tại!");
				return;
			}
		if (getSelectedId.isEmpty() || getName.isEmpty() || getPrice.isEmpty() || getAmount.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không có giá trị!");
			return;
		}

		if (getSelectedRow != -1) {
			for (int i = 0; i < _column; i++)
				product_view.getProduct_table().setValueAt(new_row[i], getSelectedRow, i); // update in the table

			if (product_view.getBus().update(getSelectedId, getName, Float.parseFloat(getPrice),
					Integer.parseInt(getAmount), getType, getImage, getState, getNote))
				JOptionPane.showMessageDialog(null, "Cập nhật thành công.");
			product_view.getProduct_table().repaint();
		}

		setDefaultValue();
	}

	public void updateTableName() {
		String name_find = product_view.getProduct_name_text_right().getText().toLowerCase().trim();
		int _row = product_view.getModel().getRowCount();
		int _column = product_view.getModel().getColumnCount();
		ArrayList<ProductModal> productList = product_view.getBus().readDB_();

		if (name_find.isEmpty()) {
			// clear existing table
			product_view.getModel().setRowCount(0);
			// fetch data from database and update table
			for (ProductModal product : productList)
				product_view.getModel().addRow(new Object[] { product.getProduct_id(), product.getProduct_name(),
						product.getProduct_type(), product.getProduct_price(), product.getProduct_state() });
		} else {
			for (int i = 0; i < _row; i++)
				for (int j = 0; j < _column; j++) {
					String cellValue = product_view.getModel().getValueAt(i, j).toString();
					if (cellValue.startsWith(name_find))
						product_view.getProduct_table().changeSelection(i, j, false, false);
				}

			for (int i = _row - 1; i >= 0; i--) {
				String name = product_view.getModel().getValueAt(i, 1).toString();
				if (!name.toLowerCase().contains(name_find))
					product_view.getModel().removeRow(i);
			}
		}
	}

	public void updateTableTypeAndState() {
		String selectedType = product_view.getProduct_type_find_text().getSelectedItem().toString();
		String selectedState = product_view.getProduct_state_find_text().getSelectedItem().toString();

		product_view.getModel().setRowCount(0);

		// Fetch products from database based on selected type and state
		ArrayList<ProductModal> productList = product_view.getBus().readDB_();
		ArrayList<ProductModal> filteredList = new ArrayList<ProductModal>(); // to store the product that is statified

		for (ProductModal product : productList)
			if ((selectedType.equalsIgnoreCase("Tất cả") || product.getProduct_type().equalsIgnoreCase(selectedType))
					&& (selectedState.equalsIgnoreCase("Tất cả")
							|| product.getProduct_state().equalsIgnoreCase(selectedState)))
				filteredList.add(product);

		// Add filtered products to table
		for (ProductModal product : filteredList)
			product_view.getModel().addRow(new Object[] { product.getProduct_id(), product.getProduct_name(),
					product.getProduct_type(), product.getProduct_price(), product.getProduct_state() });

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
		int result = selectImage.showOpenDialog(product_view);
		if (result == JFileChooser.APPROVE_OPTION) {
			// do something with the selected image file, such as displaying it on the
			// screen
			selectedFile = selectImage.getSelectedFile();
			ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
			Image scaledImage = imageIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon scaledIcon = new ImageIcon(scaledImage);
			product_view.getImage().setIcon(scaledIcon);
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
