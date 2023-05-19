package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BUS.employeeBUS;
import Controll.EmployeesControll;
import Modal.EmployeeModal;
import Modal.EmployeeModal;

public class nhanVienView extends JPanel {
//	left
	private JPanel jpanel_left, employee_image;;
	private JLabel employee_id, employee_name, employee_dob, employee_sex, employee_email, employee_phone,
			employee_homeTown, employee_role, employee_note, employee_state, image, employee_cmnd;
	private JRadioButton employee_sex_male, employee_sex_female;
	private ButtonGroup group_radio;
	private JTextField employee_id_text, employee_name_text, employee_dob_text, employee_email_text,
			employee_phone_text, employee_note_text, employee_homeTown_text, employee_cmnd_text;
	private JButton employee_add, employee_update;
	private JComboBox employee_role_list, employee_state_list;

//	right
	private JPanel jpanel_right;
	private JLabel employee_name_find, employee_type_find, employee_state_find;
	private JTextField employee_name_text_right;
	private JComboBox employee_role_find_text, employee_state_find_text;
	private JButton employee_name_button_find;

// 	Color
	private Color background_color = new Color(250, 240, 235);
	private Color border_color = new Color(211, 211, 211);
	private Color button_color = new Color(236, 236, 236);
	private Color button_color_clicked = new Color(200, 200, 200);

	private JDateChooser dateChooser = null;

// table
	List<ArrayList<Object>> content_table;
	ArrayList<EmployeeModal> employeeList;
	private DefaultTableModel model;
	private JTable employee_table;

	private JScrollPane scroll;

// 	Bus
	private employeeBUS bus = null;

// 	aciton
	private EmployeesControll action = null;

	// Chose image
	private JButton employee_choser_image_button;

	public nhanVienView() {
// 		Bus
		bus = new employeeBUS();

		String role[] = { "Quản lý", "Nhân viên" };
		String role_find[] = { "Tất cả", "Quản lý", "Nhân viên" };
		String state[] = { "Đang làm" };

//		left
		jpanel_left = new JPanel();
		jpanel_right = new JPanel();
		employee_id = new JLabel("Mã");
		employee_name = new JLabel("Họ tên");
		employee_sex = new JLabel("Giới tính");
		employee_dob = new JLabel("Ngày sinh");
		employee_email = new JLabel("Email");
		employee_phone = new JLabel("SDT");
		employee_homeTown = new JLabel("Quê quán");
		employee_role = new JLabel("Vai trò");
		employee_note = new JLabel("Ghi chú");
		employee_state = new JLabel("Trạng thái");
		employee_sex_male = new JRadioButton("nam");
		employee_sex_female = new JRadioButton("nữ");
		employee_cmnd = new JLabel("CMND");
		group_radio = new ButtonGroup();

		employee_add = new JButton("Thêm");
		employee_update = new JButton("Cập nhật");
		employee_choser_image_button = new JButton("Chọn ảnh");
		employee_image = new JPanel();
		group_radio.add(employee_sex_male);
		group_radio.add(employee_sex_female);
		employee_id_text = new JTextField(100);
		employee_name_text = new JTextField(100);
		employee_dob_text = new JTextField(100);
		dateChooser = new JDateChooser();
		employee_email_text = new JTextField(100);
		employee_phone_text = new JTextField(100);
		employee_homeTown_text = new JTextField(100);
		employee_note_text = new JTextField(100);
		employee_role_list = new JComboBox<String>(role);
		employee_role_list.setEnabled(false);
		employee_state_list = new JComboBox<String>(state);
		employee_cmnd_text = new JTextField(100);
		this.setLayout(new GridLayout(1, 2, 4, 4));
		employee_image = new JPanel(new BorderLayout());
		image = new JLabel();
		employee_image.add(image, BorderLayout.CENTER);
		employee_image.setPreferredSize(new Dimension(120, 120));

		jpanel_left.setLayout(null);
		jpanel_right.setLayout(null);
		jpanel_left.setBackground(background_color);
		jpanel_right.setBackground(background_color);

		employee_image.setPreferredSize(new Dimension(100, 100));
		employee_image.setBorder(BorderFactory.createLineBorder(border_color, 1, false));
		employee_choser_image_button.setBounds(405, 115, 88, 24);
		employee_image.setOpaque(true);
		employee_image.setBackground(background_color);
		employee_image.setBounds(280, 18, 120, 120);
		employee_id.setBounds(40, 140, 200, 50);
		employee_id_text.setBounds(150, 155, 400, 24);
		employee_name.setBounds(40, 180, 200, 50);
		employee_name_text.setBounds(150, 195, 400, 24);
		employee_sex.setBounds(40, 220, 200, 50);
		employee_sex_male.setBounds(150, 235, 60, 24);
		employee_sex_female.setBounds(206, 235, 70, 24);
		employee_dob.setBounds(40, 260, 200, 50);
//		
		dateChooser.setBounds(150, 275, 400, 24);
		employee_email.setBounds(40, 300, 200, 50);
		employee_email_text.setBounds(150, 314, 400, 24);
		employee_phone.setBounds(40, 343, 200, 50);
		employee_phone_text.setBounds(150, 356, 400, 24);
		employee_homeTown.setBounds(40, 398, 200, 24);
		employee_homeTown_text.setBounds(150, 398, 400, 24);
		employee_role.setBounds(40, 438, 200, 24);
		employee_role_list.setBounds(150, 438, 400, 24);
		employee_note.setBounds(40, 477, 200, 24);
		employee_note_text.setBounds(150, 477, 400, 24);
		employee_state.setBounds(40, 513, 200, 24);
		employee_state_list.setBounds(150, 513, 400, 24);
		employee_cmnd.setBounds(40, 550, 400, 24);
		employee_cmnd_text.setBounds(150, 550, 400, 24);
		employee_update.setBounds(342, 590, 100, 40);
		employee_add.setBounds(450, 590, 100, 40);

		employee_sex_male.setFocusable(false);
		employee_sex_female.setFocusable(false);
		employee_update.setFocusable(false);
		employee_add.setFocusable(false);
		employee_sex_male.setBackground(button_color);
		employee_sex_female.setBackground(button_color);
		employee_update.setBackground(button_color);
		employee_add.setBackground(button_color);

		employee_id_text.setBorder(new LineBorder(border_color));
		employee_name_text.setBorder(new LineBorder(border_color));
		employee_dob_text.setBorder(new LineBorder(border_color));
		employee_email_text.setBorder(new LineBorder(border_color));
		employee_phone_text.setBorder(new LineBorder(border_color));
		employee_note_text.setBorder(new LineBorder(border_color));
		employee_homeTown_text.setBorder(new LineBorder(border_color));
		employee_sex_male.setBackground(background_color);
		employee_sex_female.setBackground(background_color);

		jpanel_left.add(employee_image);
		jpanel_left.add(employee_id);
		jpanel_left.add(employee_name);
		jpanel_left.add(employee_sex);
		jpanel_left.add(employee_dob);
		jpanel_left.add(employee_email);
		jpanel_left.add(employee_phone);
		jpanel_left.add(employee_homeTown);
		jpanel_left.add(employee_add);
		jpanel_left.add(employee_update);
		jpanel_left.add(employee_id_text);
		jpanel_left.add(employee_name_text);
		jpanel_left.add(employee_dob_text);
		jpanel_left.add(employee_email_text);
		jpanel_left.add(employee_phone_text);
		jpanel_left.add(employee_homeTown_text);
		jpanel_left.add(employee_sex_male);
		jpanel_left.add(employee_sex_female);
		jpanel_left.add(employee_role);
		jpanel_left.add(employee_note);
		jpanel_left.add(employee_state);
		jpanel_left.add(employee_role_list);
		jpanel_left.add(employee_note_text);
		jpanel_left.add(employee_state_list);
		jpanel_left.add(employee_cmnd);
		jpanel_left.add(employee_cmnd_text);
		jpanel_left.add(dateChooser);

//		right
		employee_name_find = new JLabel("Tên nhân viên");
		employee_type_find = new JLabel("Vai trò");
		employee_state_find = new JLabel("Trạng thái");
		employee_name_text_right = new JTextField(100);
		employee_name_button_find = new JButton("Tìm");
		employee_role_find_text = new JComboBox<String>(role_find);
		employee_state_find_text = new JComboBox<String>(state);

		employee_name_find.setBounds(20, 50, 100, 30);
		employee_name_text_right.setBounds(20, 80, 200, 24);
		employee_name_button_find.setBounds(222, 80, 55, 24);
		employee_type_find.setBounds(315, 50, 100, 30);
		employee_role_find_text.setBounds(310, 80, 150, 24);
		employee_state_find.setBounds(490, 50, 100, 30);
		employee_state_find_text.setBounds(490, 80, 120, 24);

		renderTable();

// 		set color and remove border
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (isSelected) {
					c.setBackground(employee_table.getSelectionBackground());
				} else {
					if (row % 2 == 0) {
						c.setBackground(button_color);
					} else {
						c.setBackground(Color.WHITE);
					}
				}
				return c;
			}
		};
		employee_table.setDefaultRenderer(Object.class, renderer);

		// Set the preferred width and background color for each column
		for (int i = 0; i < employee_table.getColumnCount(); i++) {
			employee_table.getColumnModel().getColumn(i).setPreferredWidth(100);
			employee_table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		// Set the background color of the first column cells
		employee_table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (isSelected)
					c.setBackground(employee_table.getSelectionBackground());
				else {
					if (row % 2 == 0)
						c.setBackground(button_color);
					else
						c.setBackground(Color.WHITE);
				}
				return c;
			}
		});

		// Set the custom cell renderer for the table
		employee_id_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_name_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_dob_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_email_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_phone_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_note_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_homeTown_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_name_text_right.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_name_button_find.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_add.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_update.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_cmnd_text.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 4),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		employee_choser_image_button.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 10),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		
		// action
		action = new EmployeesControll(this);
		employee_table.addMouseListener(action);
		employee_add.addActionListener(action);
		employee_update.addActionListener(action);
		employee_table.addMouseListener(action);
		employee_name_button_find.addActionListener(action);
		employee_role_find_text.addActionListener(action);
		employee_state_find_text.addActionListener(action);
		employee_choser_image_button.addActionListener(action);

		employee_name_button_find.setFocusable(false);
		employee_choser_image_button.setFocusable(false);
		employee_name_button_find.setBackground(button_color);
		employee_choser_image_button.setBackground(button_color);
		employee_name_text_right.setBorder(new LineBorder(border_color));
		employee_role_find_text.setBorder(new LineBorder(border_color));
		employee_state_find_text.setBorder(new LineBorder(border_color));
		employee_choser_image_button.setBorder(new LineBorder(border_color));
		dateChooser.setBorder(new LineBorder(border_color));

		jpanel_right.add(employee_name_find);
		jpanel_right.add(employee_name_text_right);
		jpanel_right.add(employee_name_button_find);
		jpanel_right.add(employee_type_find);
		jpanel_right.add(employee_role_find_text);
		jpanel_right.add(employee_state_find);
		jpanel_right.add(employee_state_find_text);
		jpanel_right.add(scroll);
		jpanel_left.add(employee_choser_image_button);

		this.add(jpanel_left);
		this.add(jpanel_right);
	}

	public void renderTable() {
		employeeList = bus.getEmployees();
		model = new DefaultTableModel(bus.getHeader(), 0); // create heading row
		for (EmployeeModal employee : employeeList) {
			Object[] rowData = new Object[employeeList.size()];
			rowData[0] = employee.getEmployee_id();
			rowData[1] = employee.getEmployee_name();
			rowData[2] = employee.getEmployee_role();
			model.addRow(rowData);
		}
		employee_table = new JTable(model);
		scroll = new JScrollPane(employee_table);
		scroll.setBounds(20, 152, 590, 550);
		employee_table.setDefaultEditor(Object.class, null);
		employee_table.getTableHeader().setBackground(button_color);
	}

	public JLabel getEmployee_cmnd() {
		return employee_cmnd;
	}

	public void setEmployee_cmnd(JLabel employee_cmnd) {
		this.employee_cmnd = employee_cmnd;
	}

	public JTextField getEmployee_cmnd_text() {
		return employee_cmnd_text;
	}

	public void setEmployee_cmnd_text(JTextField employee_cmnd_text) {
		this.employee_cmnd_text = employee_cmnd_text;
	}

	public EmployeesControll getAction() {
		return action;
	}

	public void setAction(EmployeesControll action) {
		this.action = action;
	}

	public JTextField getEmployee_dob_text() {
		return employee_dob_text;
	}

	public void setEmployee_dob_text(JTextField employee_dob_text) {
		this.employee_dob_text = employee_dob_text;
	}

	public JPanel getJpanel_left() {
		return jpanel_left;
	}

	public void setJpanel_left(JPanel jpanel_left) {
		this.jpanel_left = jpanel_left;
	}

	public JPanel getEmployee_image() {
		return employee_image;
	}

	public void setEmployee_image(JPanel employee_image) {
		this.employee_image = employee_image;
	}

	public JLabel getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(JLabel employee_id) {
		this.employee_id = employee_id;
	}

	public JLabel getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(JLabel employee_name) {
		this.employee_name = employee_name;
	}

	public JLabel getEmployee_dob() {
		return employee_dob;
	}

	public void setEmployee_dob(JLabel employee_dob) {
		this.employee_dob = employee_dob;
	}

	public JLabel getEmployee_sex() {
		return employee_sex;
	}

	public void setEmployee_sex(JLabel employee_sex) {
		this.employee_sex = employee_sex;
	}

	public JLabel getEmployee_email() {
		return employee_email;
	}

	public void setEmployee_email(JLabel employee_describe) {
		this.employee_email = employee_describe;
	}

	public JLabel getEmployee_phone() {
		return employee_phone;
	}

	public void setEmployee_phone(JLabel employee_phone) {
		this.employee_phone = employee_phone;
	}

	public JLabel getEmployee_homeTown() {
		return employee_homeTown;
	}

	public void setEmployee_homeTown(JLabel employee_homeTown) {
		this.employee_homeTown = employee_homeTown;
	}

	public JLabel getEmployee_role() {
		return employee_role;
	}

	public void setEmployee_role(JLabel employee_role) {
		this.employee_role = employee_role;
	}

	public JLabel getEmployee_note() {
		return employee_note;
	}

	public void setEmployee_note(JLabel employee_note) {
		this.employee_note = employee_note;
	}

	public JLabel getEmployee_state() {
		return employee_state;
	}

	public void setEmployee_state(JLabel employee_state) {
		this.employee_state = employee_state;
	}

	public JLabel getImage() {
		return image;
	}

	public void setImage(JLabel image) {
		this.image = image;
	}

	public JRadioButton getEmployee_sex_male() {
		return employee_sex_male;
	}

	public void setEmployee_sex_male(JRadioButton employee_sex_male) {
		this.employee_sex_male = employee_sex_male;
	}

	public JRadioButton getEmployee_sex_female() {
		return employee_sex_female;
	}

	public void setEmployee_sex_female(JRadioButton employee_sex_female) {
		this.employee_sex_female = employee_sex_female;
	}

	public ButtonGroup getGroup_radio() {
		return group_radio;
	}

	public void setGroup_radio(ButtonGroup group_radio) {
		this.group_radio = group_radio;
	}

	public JTextField getEmployee_id_text() {
		return employee_id_text;
	}

	public void setEmployee_id_text(JTextField employee_id_text) {
		this.employee_id_text = employee_id_text;
	}

	public JTextField getEmployee_name_text() {
		return employee_name_text;
	}

	public void setEmployee_name_text(JTextField employee_name_text) {
		this.employee_name_text = employee_name_text;
	}

	public JTextField getEmployee_price_text() {
		return employee_dob_text;
	}

	public void setEmployee_price_text(JTextField employee_price_text) {
		this.employee_dob_text = employee_price_text;
	}

	public JTextField getEmployee_email_text() {
		return employee_email_text;
	}

	public void setEmployee_email_text(JTextField employee_describe_text) {
		this.employee_email_text = employee_describe_text;
	}

	public JTextField getEmployee_phone_text() {
		return employee_phone_text;
	}

	public void setEmployee_phone_text(JTextField employee_phone_text) {
		this.employee_phone_text = employee_phone_text;
	}

	public JTextField getEmployee_note_text() {
		return employee_note_text;
	}

	public void setEmployee_note_text(JTextField employee_note_text) {
		this.employee_note_text = employee_note_text;
	}

	public JTextField getEmployee_homeTown_text() {
		return employee_homeTown_text;
	}

	public void setEmployee_homeTown_text(JTextField employee_homeTown_text) {
		this.employee_homeTown_text = employee_homeTown_text;
	}

	public JButton getEmployee_add() {
		return employee_add;
	}

	public void setEmployee_add(JButton employee_add) {
		this.employee_add = employee_add;
	}

	public JButton getEmployee_update() {
		return employee_update;
	}

	public void setEmployee_update(JButton employee_update) {
		this.employee_update = employee_update;
	}

	public JComboBox getEmployee_role_list() {
		return employee_role_list;
	}

	public void setEmployee_role_list(JComboBox employee_role_list) {
		this.employee_role_list = employee_role_list;
	}

	public JComboBox getEmployee_state_list() {
		return employee_state_list;
	}

	public void setEmployee_state_list(JComboBox employee_state_list) {
		this.employee_state_list = employee_state_list;
	}

	public JPanel getJpanel_right() {
		return jpanel_right;
	}

	public void setJpanel_right(JPanel jpanel_right) {
		this.jpanel_right = jpanel_right;
	}

	public JLabel getEmployee_name_find() {
		return employee_name_find;
	}

	public void setEmployee_name_find(JLabel employee_name_find) {
		this.employee_name_find = employee_name_find;
	}

	public JLabel getEmployee_type_find() {
		return employee_type_find;
	}

	public void setEmployee_type_find(JLabel employee_type_find) {
		this.employee_type_find = employee_type_find;
	}

	public JLabel getEmployee_state_find() {
		return employee_state_find;
	}

	public void setEmployee_state_find(JLabel employee_state_find) {
		this.employee_state_find = employee_state_find;
	}

	public JTextField getEmployee_name_text_right() {
		return employee_name_text_right;
	}

	public void setEmployee_name_text_right(JTextField employee_name_text_right) {
		this.employee_name_text_right = employee_name_text_right;
	}

	public JComboBox getEmployee_role_find_text() {
		return employee_role_find_text;
	}

	public void setEmployee_role_find_text(JComboBox employee_role_find_text) {
		this.employee_role_find_text = employee_role_find_text;
	}

	public JComboBox getEmployee_state_find_text() {
		return employee_state_find_text;
	}

	public void setEmployee_state_find_text(JComboBox employee_state_find_text) {
		this.employee_state_find_text = employee_state_find_text;
	}

	public JButton getEmployee_name_button_find() {
		return employee_name_button_find;
	}

	public void setEmployee_name_button_find(JButton employee_name_button_find) {
		this.employee_name_button_find = employee_name_button_find;
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

	public List<ArrayList<Object>> getContent_table() {
		return content_table;
	}

	public void setContent_table(List<ArrayList<Object>> content_table) {
		this.content_table = content_table;
	}

	public ArrayList<EmployeeModal> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<EmployeeModal> employeeList) {
		this.employeeList = employeeList;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getEmployee_table() {
		return employee_table;
	}

	public void setEmployee_table(JTable employee_table) {
		this.employee_table = employee_table;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public employeeBUS getBus() {
		return bus;
	}

	public void setBus(employeeBUS bus) {
		this.bus = bus;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	public JButton getEmployee_choser_image_button() {
		return employee_choser_image_button;
	}

	public void setEmployee_choser_image_button(JButton employee_choser_image_button) {
		this.employee_choser_image_button = employee_choser_image_button;
	}
}
