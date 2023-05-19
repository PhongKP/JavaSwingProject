package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import BUS.QLCNBUS;
import BUS.QLCTCNBUS;
import BUS.QLQUYENBUS;
import Controll.quyenControll;
import Modal.QuyenModal;
import Modal.chucNangModal;
import Modal.ctCNModal;

public class quyenView extends JPanel{
	private JPanel topLeft, bottomPart, topRight, topPart;
	private JLabel permissionId, funtionalityId, permission, funtionality, permissionTopRight, functionalityTopRight,
			functionalityName, permissionName;
	private JTextField permissionIdText, funtionalityIdText, permissionText, funtionalityText;
	private JComboBox<String>permissionBox, functionalityBox, permissionNameBox, functionalityNameBox;
	private JButton addButton, deleteButton, viewButton, cancelButton;
	private QLQUYENBUS qlqBUS;
	private QLCNBUS qlcnBUS;
	private QLCTCNBUS qlctcnBUS;
	private ArrayList<QuyenModal> dsq;
	private ArrayList<chucNangModal> dscn;
	private ArrayList<ctCNModal> dsctcn;
	private ArrayList<String> listQuyenID = new ArrayList<>();
	private ArrayList<String> listCNName = new ArrayList<>();
	private ArrayList<String> listQuyenName = new ArrayList<>();
	private String[] heading = { "Id quyền", "Id chức năng", "Tên quyền", "Tên chức năng" };
	private DefaultTableModel modelTable;
	private JTable decentralizationTable;
	private JScrollPane scrollTable;
	private TableRowSorter<TableModel> tableSorter;

// 	Color
	private Color background_color = new Color(250, 240, 235);
	private Color border_color = new Color(211, 211, 211);
	private Color button_color = new Color(236, 236, 236);
//	private Color button_color_clicked = new Color(200, 200, 200);

	public quyenView() {
		
		qlqBUS = new QLQUYENBUS();
		qlcnBUS = new QLCNBUS();
		qlctcnBUS = new QLCTCNBUS();
		dsctcn = qlctcnBUS.getDSCTCN();
		dsq = qlqBUS.getDSQ();
		dscn = qlcnBUS.getDSCN();
		
		ActionListener ac = new quyenControll(this);
		
		Font letterFont = new Font("Arial", Font.BOLD, 22);
		Font letterFontText = new Font("Arial", Font.PLAIN, 18);
		Font letterFontButton = new Font("Arial", Font.BOLD, 16);

		this.setLayout(new GridLayout(2, 1, 8, 8));
		topLeft = new JPanel(null);
		topRight = new JPanel(null);
		topPart = new JPanel(new GridLayout(1, 2, 8, 8));
		bottomPart = new JPanel(null);

		// begin top left
		permissionId = new JLabel("ID Quyền:");
		funtionalityId = new JLabel("ID Chức năng:");
		permission = new JLabel("Quyền:");
		funtionality = new JLabel("Chức năng:");

		permissionIdText = new JTextField(100);
		funtionalityIdText = new JTextField(100);
		permissionText = new JTextField(100);
		funtionalityText = new JTextField(100);

		permissionId.setBounds(20, 50, 120, 30);
		permission.setBounds(20, 230, 90, 30);
		funtionalityId.setBounds(265, 50, 168, 30);
		funtionality.setBounds(265, 230, 140, 30);

		permissionIdText.setBounds(125, 50, 130, 30);
		funtionalityIdText.setBounds(100, 230, 154, 30);
		permissionText.setBounds(420, 50, 150, 30);
		funtionalityText.setBounds(390, 230, 182, 30);

		permissionId.setFont(letterFont);
		funtionalityId.setFont(letterFont);
		permission.setFont(letterFont);
		funtionality.setFont(letterFont);

		permissionIdText.setFont(letterFontText);
		funtionalityIdText.setFont(letterFontText);
		permissionText.setFont(letterFontText);
		funtionalityText.setFont(letterFontText);

		permissionIdText.setBackground(background_color);
		funtionalityIdText.setBackground(background_color);
		permissionText.setBackground(background_color);
		funtionalityText.setBackground(background_color);
		// end top left

		// Set bottom border for label
		permissionId.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		funtionalityId.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		permission.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		funtionality.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

		// Set bottom border for text field
		permissionIdText.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		funtionalityIdText.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		permissionText.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		funtionalityText.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		// end top left

		// begin top right
		listQuyenID = getRoleID();
		listCNName = getRoleFunctionalName();
		permissionTopRight = new JLabel("Mã Quyền");
		functionalityTopRight = new JLabel("Chức năng");
		permissionBox = new JComboBox<>();
		permissionBox.setModel(new DefaultComboBoxModel<>(listQuyenID.toArray(new String[0])));
		functionalityBox = new JComboBox<>();
		functionalityBox.setModel(new DefaultComboBoxModel<>(listCNName.toArray(new String[0])));
		addButton = new JButton("Thêm");
		addButton.addActionListener(ac);
		deleteButton = new JButton("Xóa");
		deleteButton.addActionListener(ac);

		listQuyenName = getRoleName();
		permissionName = new JLabel("Tên quyền");
		functionalityName = new JLabel("Tên chức năng");
		permissionNameBox = new JComboBox<>();
		permissionNameBox.setModel(new DefaultComboBoxModel<>(listQuyenName.toArray(new String[0])));
		functionalityNameBox = new JComboBox<>();
		functionalityNameBox.setModel(new DefaultComboBoxModel<>(listCNName.toArray(new String[0])));
		viewButton = new JButton("Tìm");
		viewButton.addActionListener(ac);
		cancelButton = new JButton("Reset");
		cancelButton.addActionListener(ac);

		permissionTopRight.setBounds(100, 100, 140, 30);
		permissionBox.setBounds(230, 100, 180, 30);
		functionalityTopRight.setBounds(100, 160, 120, 30);
		functionalityBox.setBounds(230, 160, 180, 30);
		addButton.setBounds(100, 220, 150, 40);
		deleteButton.setBounds(260, 220, 150, 40);

		permissionName.setBounds(230, 32, 130, 30);
		functionalityName.setBounds(230, 106, 180, 30);
		permissionNameBox.setBounds(230, 70, 200, 30);
		functionalityNameBox.setBounds(230, 138, 200, 30);
		viewButton.setBounds(230, 186, 200, 40);
		cancelButton.setBounds(230, 235, 200, 40);

		permissionTopRight.setFont(letterFont);
		functionalityTopRight.setFont(letterFont);
		permissionBox.setFont(letterFontText);
		functionalityBox.setFont(letterFontText);
		addButton.setFont(letterFontButton);
		deleteButton.setFont(letterFontButton);
		addButton.setFocusable(false);
		deleteButton.setFocusable(false);

		permissionName.setFont(letterFont);
		functionalityName.setFont(letterFont);
		permissionNameBox.setFont(letterFontText);
		functionalityNameBox.setFont(letterFontText);
		viewButton.setFont(letterFontButton);
		cancelButton.setFont(letterFontButton);
		viewButton.setFocusable(false);
		cancelButton.setFocusable(false);
		// end top right

		// begin bottom part
		String functionalName = "";
		String roleName = "";
		modelTable = new DefaultTableModel(heading, 0);
		decentralizationTable = new JTable(modelTable);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		decentralizationTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		decentralizationTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		scrollTable = new JScrollPane(decentralizationTable);
		for (ctCNModal ctcn : dsctcn)
		{
			roleName = getRoleNameByID(ctcn.getMaQuyen());
			functionalName = getNameFunctionalByID(ctcn.getMaChucNang());
			modelTable.addRow(new Object[] {ctcn.getMaQuyen(),ctcn.getMaChucNang(),roleName,functionalName});
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		scrollTable.setBounds(10, 10, screenWidth - 350, 350);
		// end bottom part

		topRight.setBackground(background_color);
		topLeft.setBackground(background_color);
		bottomPart.setBackground(background_color);

		topRight.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		topLeft.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		bottomPart.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

		addButton.setBackground(button_color);
		deleteButton.setBackground(button_color);
		viewButton.setBackground(button_color);
		cancelButton.setBackground(button_color);

		addButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		deleteButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		viewButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));
		cancelButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(border_color, 3),
				BorderFactory.createEmptyBorder(0, 10, 0, 10)));

		topLeft.add(permissionTopRight);
		topLeft.add(functionalityTopRight);
		topLeft.add(addButton);
		topLeft.add(deleteButton);
		topLeft.add(permissionBox);
		topLeft.add(functionalityBox);

		topRight.add(permissionName);
		topRight.add(functionalityName);
		topRight.add(permissionNameBox);
		topRight.add(functionalityNameBox);
		topRight.add(viewButton);
		topRight.add(cancelButton);

		topPart.add(topLeft);
		topPart.add(topRight);
		
		tableSorter = new TableRowSorter<>(decentralizationTable.getModel());
		decentralizationTable.setRowSorter(tableSorter);

		bottomPart.add(scrollTable);
		
		
		decentralizationTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())
				{
					int row = decentralizationTable.getSelectedRow();
					if (row != -1)
					{
						String maquyen = modelTable.getValueAt(row, 0).toString();
						String tenchucnang = modelTable.getValueAt(row, 3).toString();
						permissionBox.setSelectedItem(maquyen);
						functionalityBox.setSelectedItem(tenchucnang);
					}
				}
				
			}
		});
		
		this.add(topPart);
		this.add(bottomPart);
	}
	
	public ArrayList<String> getRoleID ()
	{
		for (QuyenModal q : dsq)
		{
			listQuyenID.add(q.getMaQuyen());
		}
		return listQuyenID;
	}
	
	public ArrayList<String> getRoleFunctionalName ()
	{
		for (chucNangModal func : dscn)
		{
			listCNName.add(func.getTenChucNang());
		}
		return listCNName;
	}
	
	public ArrayList<String> getRoleName()
	{
		for (QuyenModal q : dsq)
		{
			listQuyenName.add(q.getTenQuyen());
		}
		return listQuyenName;
	}
	
	public JButton getAddButton ()
	{
		return addButton;
	}
	
	public JButton getDelButton ()
	{
		return deleteButton;
	}
	
	public JButton getFindButton ()
	{
		return viewButton;
	}
	
	public JButton getResetButton ()
	{
		return cancelButton;
	}
	
	public ArrayList<QuyenModal> getDSQ ()
	{
		return dsq;
	}
	
	public ArrayList<chucNangModal> getDSCN ()
	{
		return dscn;
	}
	
	public JComboBox<String> getChoiceOfRoleID ()
	{
		return permissionBox;
	}
	
	public JComboBox<String> getChoiceOfFunctional()
	{
		return functionalityBox;
	}
	
	public DefaultTableModel getTableModel ()
	{
		return modelTable;
	}
	
	public String getNameFunctionalByID (String id)
	{
		for (chucNangModal cn : dscn)
		{
			if (cn.getMaChucNang().equals(id))
			{
				return cn.getTenChucNang();
			}
		}
		return null;
	}
	
	public String getRoleNameByID (String id)
	{
		for (QuyenModal q : dsq)
		{
			if (q.getMaQuyen().equals(id))
			{
				return q.getTenQuyen();
			}
		}
		return null;
	}
	
	public JTable getTable()
	{
		return decentralizationTable;
	}
	
	public JComboBox<String> getChoiceOfRoleName()
	{
		return permissionNameBox;
	}
	
	public JComboBox<String> getChoiceOfFunctionalName()
	{
		return functionalityNameBox;
	}
	
	public TableRowSorter<TableModel> getTableSorter()
	{
		return tableSorter;
	}
	
	public ArrayList<ctCNModal> getDSCTCN ()
	{
		return dsctcn;
	}
	
}
