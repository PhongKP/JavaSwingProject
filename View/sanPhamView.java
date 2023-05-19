package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.ProductBUS;
import Controll.sanPhamControll;
import Modal.ProductModal;

public class sanPhamView extends JPanel {
//	left
	private JPanel jpanel_left, product_image;
	private JLabel product_id, product_name, product_type, product_price, prodcut_note, product_state, image,
			product_amount;
	private JTextField product_id_text, product_name_text, product_price_text, product_amount_text;
	private JComboBox product_type_text, product_state_text;
	private JTextArea product_note_text;
	private JButton product_add, product_update;

//	right
	private JPanel jpanel_right;
	private JLabel product_name_find, product_type_find, product_state_find;
	private JTextField product_name_text_right;
	private JComboBox product_type_find_text, product_state_find_text;

	private JButton product_name_button_find;

// color
	private Color background_color = new Color(250, 240, 235);
	private Color border_color = new Color(211, 211, 211);
	private Color button_color = new Color(236, 236, 236);
	private Color button_color_clicked = new Color(200, 200, 200);

// table 
	private DefaultTableModel model;
	private JTable product_table;
	List<ArrayList<Object>> content_table;
	private JScrollPane scroll;

// BUS 
	private ProductBUS bus;
	private ArrayList<ProductModal> productList;

// Chose image 
	private JButton product_choser_image_button;

	public sanPhamView() {
// 		BUS
		bus = new ProductBUS();

//		listener
		sanPhamControll action = new sanPhamControll(this);

// 		Header
		String type[] = { "Tất cả", "Cà phê đen", "Cà phê sữa", "Cappuccino", "Espresso", "Mocha", "Americano" };
		String state[] = { "Tất cả", "Ngừng bán", "Đang bán" };

//		left
		jpanel_left = new JPanel();
		jpanel_right = new JPanel();
		product_id = new JLabel("Mã sản phẩm");
		product_name = new JLabel("Tên sản phẩm");
		product_type = new JLabel("Phân loại");
		product_price = new JLabel("Đơn giá");
		prodcut_note = new JLabel("Ghi chú");
		product_state = new JLabel("Trạng thái");
		product_amount = new JLabel("Số lượng");
		product_add = new JButton("Thêm");
		product_update = new JButton("Cập nhật");
		product_id_text = new JTextField(100);
		product_name_text = new JTextField(100);
		product_price_text = new JTextField(100);
		product_type_text = new JComboBox<String>(type);
		product_note_text = new JTextArea(100, 110);
		product_amount_text = new JTextField(100);
		product_amount_text.setEnabled(false);
		product_state_text = new JComboBox<String>(state);
		product_choser_image_button = new JButton("Chọn ảnh");
		product_image = new JPanel(new BorderLayout());
		image = new JLabel();
		product_image.add(image, BorderLayout.CENTER);
		this.setLayout(new GridLayout(1, 2, 4, 4));

		jpanel_left.setLayout(null);
		jpanel_right.setLayout(null);
		jpanel_left.setBackground(background_color);
		jpanel_right.setBackground(background_color);
		product_image.setPreferredSize(new Dimension(120, 120));

		product_image.setBorder(BorderFactory.createLineBorder(border_color, 1, false));
		product_image.setOpaque(true);
		product_image.setBackground(background_color);
		product_image.setBounds(280, 18, 120, 120);
		image.setPreferredSize(new Dimension(120, 120));
		product_choser_image_button.setBounds(405, 115, 88, 24);
		product_id.setBounds(40, 140, 200, 50);
		product_id_text.setBounds(150, 155, 400, 24);
		product_name.setBounds(40, 180, 200, 50);
		product_name_text.setBounds(150, 195, 400, 24);
		product_type.setBounds(40, 220, 200, 50);
		product_type_text.setBounds(150, 235, 400, 24);
		product_price.setBounds(40, 260, 200, 50);
		product_price_text.setBounds(150, 275, 400, 24);
		prodcut_note.setBounds(40, 300, 200, 50);
		product_note_text.setBounds(150, 318, 400, 110);
		product_state.setBounds(40, 446, 200, 24);
		product_state_text.setBounds(150, 446, 400, 24);
		product_amount.setBounds(40, 488, 200, 24);
		product_amount_text.setBounds(150, 488, 400, 24);
		product_update.setBounds(342, 530, 100, 40);
		product_add.setBounds(450, 530, 100, 40);

		product_update.setFocusable(false);
		product_add.setFocusable(false);
		product_update.setBackground(button_color);
		product_add.setBackground(button_color);

		product_id_text.setBorder(new LineBorder(border_color));
		product_name_text.setBorder(new LineBorder(border_color));
		product_price_text.setBorder(new LineBorder(border_color));
		product_note_text.setBorder(new LineBorder(border_color));
		product_type_text.setBorder(new LineBorder(border_color));
		product_state_text.setBorder(new LineBorder(border_color));

//		right
		product_name_find = new JLabel("Tên sản phẩm");
		product_type_find = new JLabel("Loại sản phẩm");
		product_state_find = new JLabel("Trạng thái");
		product_name_text_right = new JTextField(100);
		product_name_button_find = new JButton("Tìm");
		product_type_find_text = new JComboBox<String>(type);
		product_state_find_text = new JComboBox<String>(state);

		product_name_find.setBounds(20, 50, 100, 30);
		product_name_text_right.setBounds(20, 80, 200, 24);
		product_name_button_find.setBounds(222, 80, 55, 24);
		product_type_find.setBounds(290, 50, 100, 30);
		product_type_find_text.setBounds(290, 80, 150, 24);
		product_state_find.setBounds(455, 50, 100, 30);
		product_state_find_text.setBounds(455, 80, 150, 24);

		// table
		renderTable();

// 		set color and remove border
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (isSelected)
					c.setBackground(product_table.getSelectionBackground());
				else {
					if (column != 0) {
						if (row % 2 == 0)
							c.setBackground(button_color);
						else
							c.setBackground(Color.WHITE);

					} else {
						if (row % 2 == 0)
							c.setBackground(button_color);
						else
							c.setBackground(Color.WHITE);
					}
				}
				return c;
			}
		};
		product_table.setDefaultRenderer(Object.class, renderer);

		// Set the custom cell renderer for the table
		product_id_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_name_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_price_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_note_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_name_text_right.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_add.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_update.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_name_button_find.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_amount_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		product_choser_image_button.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 10),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));

		// action
		product_table.addMouseListener(action);
		product_add.addActionListener(action);
		product_name_button_find.addActionListener(action);
		product_type_find_text.addActionListener(action);
		product_state_find_text.addActionListener(action);
		product_update.addActionListener(action);
		product_choser_image_button.addActionListener(action);

		product_name_button_find.setFocusable(false);
		product_choser_image_button.setFocusable(false);
		product_name_button_find.setBackground(button_color);
		product_choser_image_button.setBackground(button_color);
		product_name_text_right.setBorder(new LineBorder(border_color));
		product_type_find_text.setBorder(new LineBorder(border_color));
		product_state_find_text.setBorder(new LineBorder(border_color));
		product_choser_image_button.setBorder(new LineBorder(border_color));

		jpanel_left.add(product_image);
		jpanel_left.add(product_id);
		jpanel_left.add(product_name);
		jpanel_left.add(product_type);
		jpanel_left.add(product_price);
		jpanel_left.add(prodcut_note);
		jpanel_left.add(product_state);
		jpanel_left.add(product_add);
		jpanel_left.add(product_update);
		jpanel_left.add(product_id_text);
		jpanel_left.add(product_name_text);
		jpanel_left.add(product_price_text);
		jpanel_left.add(product_note_text);
		jpanel_left.add(product_type_text);
		jpanel_left.add(product_state_text);
		jpanel_left.add(product_amount_text);
		jpanel_left.add(product_amount);
		jpanel_left.add(product_choser_image_button);

		jpanel_right.add(product_name_find);
		jpanel_right.add(product_name_text_right);
		jpanel_right.add(product_name_button_find);
		jpanel_right.add(product_type_find);
		jpanel_right.add(product_type_find_text);
		jpanel_right.add(product_state_find);
		jpanel_right.add(product_state_find_text);
		jpanel_right.add(scroll);

		this.add(jpanel_left);
		this.add(jpanel_right);
	}

	public void renderTable() {
		productList = bus.readDB_(); // get products from database

		model = new DefaultTableModel(bus.getHeader(), 0);
		for (ProductModal product : productList) {
			Object[] rowData = new Object[productList.size()];
			rowData[0] = product.getProduct_id();
			rowData[1] = product.getProduct_name();
			rowData[2] = product.getProduct_type();
			rowData[3] = product.getProduct_price();
			rowData[4] = product.getProduct_state();
			model.addRow(rowData);
		}
		product_table = new JTable(model);
		scroll = new JScrollPane(product_table);
		scroll.setBounds(20, 152, 586, 550);
		product_table.setDefaultEditor(Object.class, null);
		product_table.getTableHeader().setBackground(button_color);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		product_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		product_table.getColumnModel().getColumn(0).setPreferredWidth(30);
		product_table.getColumnModel().getColumn(1).setPreferredWidth(180);
		product_table.getColumnModel().getColumn(2).setPreferredWidth(100);
	}

	public JLabel getProduct_amount() {
		return product_amount;
	}

	public void setProduct_amount(JLabel product_amount) {
		this.product_amount = product_amount;
	}

	public JTextField getProduct_amount_text() {
		return product_amount_text;
	}

	public void setProduct_amount_text(JTextField product_amount_text) {
		this.product_amount_text = product_amount_text;
	}

	public ArrayList<ProductModal> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<ProductModal> productList) {
		this.productList = productList;
	}

	public JPanel getJpanel_left() {
		return jpanel_left;
	}

	public void setJpanel_left(JPanel jpanel_left) {
		this.jpanel_left = jpanel_left;
	}

	public JPanel getProduct_image() {
		return product_image;
	}

	public void setProduct_image(JPanel product_image) {
		this.product_image = product_image;
	}

	public JLabel getProduct_id() {
		return product_id;
	}

	public void setProduct_id(JLabel product_id) {
		this.product_id = product_id;
	}

	public JLabel getProduct_name() {
		return product_name;
	}

	public void setProduct_name(JLabel product_name) {
		this.product_name = product_name;
	}

	public JLabel getProduct_type() {
		return product_type;
	}

	public void setProduct_type(JLabel product_type) {
		this.product_type = product_type;
	}

	public JLabel getProduct_price() {
		return product_price;
	}

	public void setProduct_price(JLabel product_price) {
		this.product_price = product_price;
	}

	public JLabel getProdcut_note() {
		return prodcut_note;
	}

	public void setProdcut_note(JLabel prodcut_note) {
		this.prodcut_note = prodcut_note;
	}

	public JLabel getProduct_state() {
		return product_state;
	}

	public void setProduct_state(JLabel product_state) {
		this.product_state = product_state;
	}

	public JLabel getImage() {
		return image;
	}

	public void setImage(JLabel image) {
		this.image = image;
	}

	public JTextField getProduct_id_text() {
		return product_id_text;
	}

	public void setProduct_id_text(JTextField product_id_text) {
		this.product_id_text = product_id_text;
	}

	public JTextField getProduct_name_text() {
		return product_name_text;
	}

	public void setProduct_name_text(JTextField product_name_text) {
		this.product_name_text = product_name_text;
	}

	public JTextField getProduct_price_text() {
		return product_price_text;
	}

	public void setProduct_price_text(JTextField product_price_text) {
		this.product_price_text = product_price_text;
	}

	public JComboBox getProduct_type_text() {
		return product_type_text;
	}

	public void setProduct_type_text(JComboBox product_type_text) {
		this.product_type_text = product_type_text;
	}

	public JComboBox getProduct_state_text() {
		return product_state_text;
	}

	public void setProduct_state_text(JComboBox product_state_text) {
		this.product_state_text = product_state_text;
	}

	public JTextArea getProduct_note_text() {
		return product_note_text;
	}

	public void setProduct_note_text(JTextArea product_note_text) {
		this.product_note_text = product_note_text;
	}

	public JButton getProduct_add() {
		return product_add;
	}

	public void setProduct_add(JButton product_add) {
		this.product_add = product_add;
	}

	public JButton getProduct_update() {
		return product_update;
	}

	public void setProduct_update(JButton product_update) {
		this.product_update = product_update;
	}

	public JPanel getJpanel_right() {
		return jpanel_right;
	}

	public void setJpanel_right(JPanel jpanel_right) {
		this.jpanel_right = jpanel_right;
	}

	public JLabel getProduct_name_find() {
		return product_name_find;
	}

	public void setProduct_name_find(JLabel product_name_find) {
		this.product_name_find = product_name_find;
	}

	public JLabel getProduct_type_find() {
		return product_type_find;
	}

	public void setProduct_type_find(JLabel product_type_find) {
		this.product_type_find = product_type_find;
	}

	public JLabel getProduct_state_find() {
		return product_state_find;
	}

	public void setProduct_state_find(JLabel product_state_find) {
		this.product_state_find = product_state_find;
	}

	public JTextField getProduct_name_text_right() {
		return product_name_text_right;
	}

	public void setProduct_name_text_right(JTextField product_name_text_right) {
		this.product_name_text_right = product_name_text_right;
	}

	public JComboBox getProduct_type_find_text() {
		return product_type_find_text;
	}

	public void setProduct_type_find_text(JComboBox product_type_find_text) {
		this.product_type_find_text = product_type_find_text;
	}

	public JComboBox getProduct_state_find_text() {
		return product_state_find_text;
	}

	public void setProduct_state_find_text(JComboBox product_state_find_text) {
		this.product_state_find_text = product_state_find_text;
	}

	public JButton getProduct_name_button_find() {
		return product_name_button_find;
	}

	public void setProduct_name_button_find(JButton product_name_button_find) {
		this.product_name_button_find = product_name_button_find;
	}

	public Color getBackground_color() {
		return background_color;
	}

	public void setBackground_color(Color background_color) {
		this.background_color = background_color;
	}

	public Color getBorder_color() {
		return border_color;
	}

	public void setBorder_color(Color border_color) {
		this.border_color = border_color;
	}

	public Color getButton_color() {
		return button_color;
	}

	public void setButton_color(Color button_color) {
		this.button_color = button_color;
	}

	public Color getButton_color_clicked() {
		return button_color_clicked;
	}

	public void setButton_color_clicked(Color button_color_clicked) {
		this.button_color_clicked = button_color_clicked;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getProduct_table() {
		return product_table;
	}

	public void setProduct_table(JTable product_table) {
		this.product_table = product_table;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public List<ArrayList<Object>> getContent_table() {
		return content_table;
	}

	public void setContent_table(List<ArrayList<Object>> content_table) {
		this.content_table = content_table;
	}

	public ProductBUS getBus() {
		return bus;
	}

	public void setBus(ProductBUS bus) {
		this.bus = bus;
	}

	public JButton getProduct_choser_image_button() {
		return product_choser_image_button;
	}

	public void setProduct_choser_image_button(JButton product_choser_image_button) {
		this.product_choser_image_button = product_choser_image_button;
	}
}
