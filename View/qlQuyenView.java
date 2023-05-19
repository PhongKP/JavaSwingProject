package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import BUS.QLQUYENBUS;
import Controll.qlQuyenControll;
import Modal.QuyenModal;

public class qlQuyenView extends JPanel {
	private JLabel id, name;
	private JTextField idText, nameText, searchText;
	private JButton addButton, modifyButton, deleteButton, resetButton, searchButton;

	private DefaultTableModel modelTable;
	private QLQUYENBUS qlqBUS;
	private JTable table;
	private JScrollPane scroll;

	private JPanel topPart, bottomPart;

	private String[] permissionHeader = { "Id quyền", "Tên quyền" };

// 	Color
	private Color background_color = new Color(250, 240, 235);
	private Color border_color = new Color(211, 211, 211);
	private Color button_color = new Color(236, 236, 236);
	private Color button_color_clicked = new Color(200, 200, 200);
	private TableRowSorter rowSorter;

	public qlQuyenView() {
		this.setLayout(null);
		topPart = new JPanel(null);
		bottomPart = new JPanel(null);

		
		qlqBUS = new QLQUYENBUS();
		ArrayList<QuyenModal> dsq = qlqBUS.getDSQ();
		ActionListener ac = new qlQuyenControll(this);
		
		Font letterFont = new Font("Sans-serif", Font.PLAIN, 24);

		RoundedBorder border = new RoundedBorder(border_color, 20);

		id = new JLabel("ID: ");
		name = new JLabel("Name: ");
		idText = new JTextField(100);
		nameText = new JTextField(100);
		searchText = new JTextField(100);
		addButton = new JButton("Thêm");
		modifyButton = new JButton("Sửa");
		deleteButton = new JButton("Xóa");
		resetButton = new JButton("Reset");
		searchButton = new JButton("Tìm");
		modelTable = new DefaultTableModel(permissionHeader, 0);
		for (QuyenModal q : dsq)
		{
			modelTable.addRow(new Object[] {q.getMaQuyen(),q.getTenQuyen()});
		}
		table = new JTable(modelTable);
		scroll = new JScrollPane(table);
		
		topPart.setBounds(150, 50, 1000, 80);
		id.setBounds(30, 27, 80, 30);
		idText.setBounds(70, 27, 300, 30);
		name.setBounds(400, 27, 80, 30);
		nameText.setBounds(480, 27, 300, 30);

		id.setFont(letterFont);
		name.setFont(letterFont);
		idText.setFont(letterFont);
		nameText.setFont(letterFont);
		searchText.setFont(letterFont);

		idText.setBackground(background_color);
		nameText.setBackground(background_color);
		searchText.setBackground(background_color);

		id.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		idText.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		name.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		nameText.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		searchText.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

		bottomPart.setBounds(150, 140, 1000, 550);
		searchText.setBounds(30, 30, 300, 30);
		searchButton.setBounds(335, 30, 100, 30);
		resetButton.setBounds(800, 30, 100, 30);
		addButton.setBounds(880, 70, 100, 35);
		modifyButton.setBounds(880, 110, 100, 35);
		deleteButton.setBounds(880, 150, 100, 35);

//		ImageIcon search_icon = new ImageIcon("/img/searchIcon.png");
//		Image image_search = search_icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
//		ImageIcon searchIcon = new ImageIcon(image_search);
//		searchButton.setIcon(searchIcon);
//
//		ImageIcon reset_icon = new ImageIcon("/img/resetIcon.png");
//		Image image_reset = reset_icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
//		ImageIcon resetIcon = new ImageIcon(image_reset);
//		resetButton.setIcon(resetIcon);

		ImageIcon add_icon = new ImageIcon("/img/addIcon.png");
		Image image_add = add_icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon addIcon = new ImageIcon(image_add);
		addButton.setIcon(addIcon);

		ImageIcon modify_icon = new ImageIcon("/img/modifyIcon.png");
		Image image_modify = modify_icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon modifyIcon = new ImageIcon(image_modify);
		modifyButton.setIcon(modifyIcon);

		ImageIcon delete_icon = new ImageIcon("/img/deleteIcon.png");
		Image image_delete = delete_icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon deleteIcon = new ImageIcon(image_delete);
		deleteButton.setIcon(deleteIcon);

		searchButton.setBorder(border);
		resetButton.setBorder(border);
		addButton.setBorder(border);
		modifyButton.setBorder(border);
		deleteButton.setBorder(border);

		searchButton.setBackground(button_color);
		resetButton.setBackground(button_color);
		addButton.setBackground(button_color);
		modifyButton.setBackground(button_color);
		deleteButton.setBackground(button_color);

		searchButton.setFocusable(false);
		resetButton.setFocusable(false);
		addButton.setFocusable(false);
		modifyButton.setFocusable(false);
		deleteButton.setFocusable(false);

		addButton.addActionListener(ac);
		searchButton.addActionListener(ac);
		resetButton.addActionListener(ac);
		deleteButton.addActionListener(ac);
		modifyButton.addActionListener(ac);
		
		rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		
		scroll.setBounds(30, 70, 830, 450);

		this.setBackground(background_color);
		topPart.setBackground(background_color);
		bottomPart.setBackground(background_color);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())
				{
					int row = table.getSelectedRow();
					if (row != -1)
					{
						String maquyen = modelTable.getValueAt(row, 0).toString();
						String tenquyen = modelTable.getValueAt(row, 1).toString();
						idText.setText(maquyen);
						nameText.setText(tenquyen);
					}
				}
				
			}
		});

		topPart.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		bottomPart.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

		topPart.add(id);
		topPart.add(idText);
		topPart.add(name);
		topPart.add(nameText);

		bottomPart.add(searchText);
		bottomPart.add(searchButton);
		bottomPart.add(resetButton);
		bottomPart.add(addButton);
		bottomPart.add(modifyButton);
		bottomPart.add(deleteButton);
		bottomPart.add(scroll);

		this.add(topPart);
		this.add(bottomPart);
	}
	
	public JTextField getIdRole ()
	{
		return idText;
	}
	
	public JTextField getNameRole ()
	{
		return nameText;
	}
	
	public DefaultTableModel getModelTable ()
	{
		return modelTable;
	}
	
	public JTable getTable ()
	{
		return table;
	}
	
	public JTextField getSearchTextField ()
	{
		return searchText;
	}
	
	public TableRowSorter<TableModel> getTableSorter()
	{
		return rowSorter;
	}
}
