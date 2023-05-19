package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Modal.DTOCTPN;
import Modal.DTONguyenLieu;
import Modal.DTONhaCC;
import Modal.DTOPhieuNhap;

public class nhaCCView extends JPanel {
	DefaultTableModel tablemodel1;
	JTable table1;
	private static nhaCCView instance;
	JTextField txtsearch;
	JPanel center;
	JPanel panelnhaphang;
	DefaultTableModel modelnguyenlieu;
	JTable tableNguyenLieu;
	DefaultTableModel modelnhaphang;
	JTable tableNhapHang;
	DefaultTableModel modelctpn;
	JTable tableCTPN;
	JPanel panelrightnhaphang;
	JPanel panelmiddlenhaphang;
	JTextField txtmaphieunhap;
	JDateChooser datechooser;
	JComboBox comboboxnhacc;
	JTextField txttonggia;
	JButton btnsave;
	JPanel northInformation;
	JTextField txtmanhanvien;
	JLabel lbmanhanvien;
	JButton btndeletephieunhap;
	JScrollPane scrollpanectpn;
	JLabel lbmaphieunhap;
	JPanel panelInformation;

	public static nhaCCView getInstance() {
		if (instance == null) {
			instance = new nhaCCView();
		}
		return instance;
	}

	public nhaCCView() {
		Init();
		loadData();
		// panel nhaphang
		loadDataNhapHang();
		loadDataNguyenLieu();

	}

	public void loadDataNguyenLieu() {
		java.util.List<DTONguyenLieu> list = BUS.BUSNguyenLieu.getInstance().getAll();
		modelnguyenlieu.setRowCount(0);
		for (DTONguyenLieu dto : list) {
			modelnguyenlieu.addRow(
					new Object[] { dto.getMaNguyenLieu(), dto.getTenNguyenLieu(), dto.getMota(), dto.getSoLuong() });
		}
		panelmiddlenhaphang.setVisible(false);
		if (!btndeletephieunhap.isVisible() && scrollpanectpn.isVisible()) {
			modelctpn.setRowCount(0);
			txttonggia.setText("0");
		}
	}

	public void loadDataNhapHang() {
		java.util.List<DTOPhieuNhap> list = BUS.BUSPhieuNhap.getInstance().getAll();
		modelnhaphang.setRowCount(0);
		for (DTOPhieuNhap dto : list) {
			modelnhaphang.addRow(new Object[] { dto.getMaPN(), dto.getNgayNhap(), dto.getTongGia(), dto.getMaNhaCC(),
					dto.getMaNV() });
		}
		panelmiddlenhaphang.setVisible(false);
		panelInformation.setVisible(false);
		scrollpanectpn.setVisible(false);
		btndeletephieunhap.setVisible(false);
	}

	public void loadData() {
		txtsearch.setText("");
		tablemodel1.setRowCount(0);
		java.util.List<Modal.DTONhaCC> list = BUS.BUSNhaCC.getInstance().getAllNhaCC();
		for (Modal.DTONhaCC dto : list) {
			tablemodel1.addRow(new Object[] { dto.getMaNhaCC(), dto.getTenNhaCC(), dto.getDiaChi(), dto.getSDT(),
					dto.getEmail(), "Sửa", "Xoá" });
		}

	}

	public void loadDataFind() {
		if (txtsearch.getText().trim().equals("")) {
			loadData();
		}
		tablemodel1.setRowCount(0);

		java.util.List<Modal.DTONhaCC> list = BUS.BUSNhaCC.getInstance().getNhaCCByIDOrName(txtsearch.getText().trim());
		for (Modal.DTONhaCC dto : list) {
			tablemodel1.addRow(new Object[] { dto.getMaNhaCC(), dto.getTenNhaCC(), dto.getDiaChi(), dto.getSDT(),
					dto.getEmail(), "Sửa", "Xoá" });
		}
	}

	public void Init() {
		setBackground(new Color(250, 238, 232));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.setBackground(new Color(250, 238, 232));
		north.setPreferredSize(new Dimension(0, 50));
		north.setLayout(new FlowLayout(FlowLayout.LEADING));
		JButton btnnhacungcap = new JButton("Quản lý nhà cung cấp");
		btnnhacungcap.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		btnnhacungcap.setBackground(new Color(141, 109, 99));
		btnnhacungcap.setFocusable(false);
		btnnhacungcap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nhaCCView.getInstance().remove(panelnhaphang);
				nhaCCView.getInstance().remove(center);
				nhaCCView.getInstance().add(center);
				nhaCCView.getInstance().revalidate();
				nhaCCView.getInstance().repaint();
				loadData();
			}
		});

		JButton btnnhaphang = new JButton("Quản lý nhập hàng");
		btnnhaphang.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		btnnhaphang.setBackground(new Color(190, 155, 146));
		btnnhaphang.setFocusable(false);
		btnnhaphang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nhaCCView.getInstance().remove(panelnhaphang);
				nhaCCView.getInstance().remove(center);

				nhaCCView.getInstance().add(panelnhaphang);
				nhaCCView.getInstance().revalidate();
				nhaCCView.getInstance().repaint();
				loadDataNguyenLieu();
				loadDataNhapHang();
			}
		});

		north.add(btnnhacungcap);
		north.add(btnnhaphang);

		center = new JPanel();
		center.setBackground(new Color(250, 238, 232));
		center.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		center.setLayout(new BorderLayout(10, 20));
		JPanel northcenter = new JPanel();
		northcenter.setBackground(new Color(250, 238, 232));
		northcenter.setPreferredSize(new Dimension(0, 50));
		northcenter.setLayout(new GridLayout(1, 2));
		JPanel leftnorthcenter = new JPanel();
		leftnorthcenter.setBackground(new Color(250, 238, 232));
		leftnorthcenter.setLayout(new FlowLayout(FlowLayout.LEADING));
		JButton btncreate = new JButton("Tạo Nhà Cung Cấp");
        btncreate.setPreferredSize(new Dimension(200, 40));
		btncreate.setBackground(Color.WHITE);
		btncreate.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		btncreate.setBackground(Color.WHITE);
		btncreate.setFocusable(false);
		btncreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddNhaCCFrame.getInstance();
			}

		});
		leftnorthcenter.add(btncreate);
		JPanel rightnorthcenter = new JPanel();
		rightnorthcenter.setBackground(new Color(250, 238, 232));
		rightnorthcenter.setLayout(new FlowLayout(FlowLayout.TRAILING));
		JLabel lbsearchnhacc = new JLabel("Tìm theo mã hoặc tên");
		txtsearch = new JTextField();
		txtsearch.setPreferredSize(new Dimension(140, 28));
		txtsearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				loadDataFind();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				loadDataFind();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				loadDataFind();
			}
		});
		rightnorthcenter.add(lbsearchnhacc);
		rightnorthcenter.add(txtsearch);

		northcenter.add(leftnorthcenter);
		northcenter.add(rightnorthcenter);

		JPanel centercenter = new JPanel();
		centercenter.setBackground(new Color(250, 238, 232));
		centercenter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		centercenter.setLayout(new GridLayout(1, 1));

		String columnName[] = { "Mã nhà CC", "Tên nhà CC", "Địa chỉ", "SDT", "Email", "", "" };
		tablemodel1 = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablemodel1.setColumnIdentifiers(columnName);
		table1 = new JTable();
		table1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table1.setModel(tablemodel1);
		table1.getColumnModel().getColumn(0).setPreferredWidth(80);
		table1.getColumnModel().getColumn(0).setMaxWidth(80);
		table1.getColumnModel().getColumn(0).setMinWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setMaxWidth(80);
		table1.getColumnModel().getColumn(3).setMinWidth(80);
		table1.getColumnModel().getColumn(5).setPreferredWidth(30);
		table1.getColumnModel().getColumn(5).setMaxWidth(30);
		table1.getColumnModel().getColumn(5).setMinWidth(30);
		table1.getColumnModel().getColumn(6).setPreferredWidth(30);
		table1.getColumnModel().getColumn(6).setMaxWidth(30);
		table1.getColumnModel().getColumn(6).setMinWidth(30);

		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = table1.getSelectedRow();
				int column = table1.getSelectedColumn();
				if (row != -1 && (column == 5 || column == 6)) {
					if (column == 5) {
						EditNhaccFrame.getInstance(
								BUS.BUSNhaCC.getInstance().getNhaCCByID(table1.getValueAt(row, 0).toString()));
					} else {
						if (!BUS.BUSNhaCC.getInstance().delete(table1.getValueAt(row, 0).toString())) {
							JOptionPane.showMessageDialog(null, "Xoá nhà cung cấp thất bại");
							return;
						}
						loadData();
					}

				}
			}
		});
		JScrollPane sc1 = new JScrollPane(table1);

		centercenter.add(sc1);
		center.add(northcenter, BorderLayout.NORTH);
		center.add(centercenter, BorderLayout.CENTER);

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

		///////////// Panel Nhap Hang
		panelnhaphang = new JPanel();
		panelnhaphang.setBackground(new Color(250, 238, 232));
		panelnhaphang.setLayout(new BorderLayout(10, 0));
		JPanel panelleftnhaphang = new JPanel();
		panelleftnhaphang.setBackground(new Color(250, 238, 232));
		panelleftnhaphang.setPreferredSize(new Dimension(550, 0));
		panelleftnhaphang.setLayout(new GridLayout(2, 1, 0, 10));
		modelnguyenlieu = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String columnnguyenlieu[] = { "Mã nguyên liệu", "Tên nguyên liệu", "Mô tả", "Số lượng" };
		modelnguyenlieu.setColumnIdentifiers(columnnguyenlieu);
		tableNguyenLieu = new JTable();
		tableNguyenLieu.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableNguyenLieu.setModel(modelnguyenlieu);
		tableNguyenLieu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (!btndeletephieunhap.isVisible() && scrollpanectpn.isVisible()) {
					panelmiddlenhaphang.setVisible(true);
				} else {
					panelmiddlenhaphang.setVisible(false);
				}
			}
		});
		JScrollPane scrollpaneNguyenLieu = new JScrollPane(tableNguyenLieu);

		modelnhaphang = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String columnNhapHang[] = { "Mã phiếu nhập", "Ngày nhập", "Tổng giá", "Mã nhà CC", "Mã nhân viên" };

		modelnhaphang.setColumnIdentifiers(columnNhapHang);
		tableNhapHang = new JTable();

		tableNhapHang.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableNhapHang.setModel(modelnhaphang);

		tableNhapHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = tableNhapHang.getSelectedRow();
				if (row != -1) {
					panelmiddlenhaphang.setVisible(false);
					scrollpanectpn.setVisible(true);
					panelInformation.setVisible(true);
					btndeletephieunhap.setVisible(true);

					DTOPhieuNhap dto = BUS.BUSPhieuNhap.getInstance()
							.getPhieuNhapByID(tableNhapHang.getValueAt(row, 0).toString());
					northInformation.add(lbmaphieunhap, 0);
					northInformation.add(txtmaphieunhap, 1);
					txtmaphieunhap.setText(dto.getMaPN());

					try {
						Date date = new SimpleDateFormat("yyyy-MM-dd 00:00:00").parse(dto.getNgayNhap());
						datechooser.setDate(date);
					} catch (Exception ex) {
						System.out.println(ex);
					}
					datechooser.setEnabled(false);

					northInformation.remove(btnsave);
					northInformation.add(lbmanhanvien, 4);
					northInformation.add(txtmanhanvien, 5);
					northInformation.revalidate();
					northInformation.repaint();
					txtmanhanvien.setText(dto.getMaNV());

					comboboxnhacc.setEnabled(false);
					comboboxnhacc.removeAllItems();
					comboboxnhacc.addItem(dto.getMaNhaCC());
					comboboxnhacc.setSelectedIndex(0);
					txttonggia.setText(dto.getTongGia());

					java.util.List<DTOCTPN> list = BUS.BUSPhieuNhap.getInstance()
							.getAllCTPNByMaPN(txtmaphieunhap.getText());
					modelctpn.setRowCount(0);
					for (DTOCTPN ctpn : list) {
						modelctpn.addRow(new Object[] { ctpn.getMaNguyenLieu(), ctpn.getSoLuong(), ctpn.getDonGia() });
					}
				}

			}

		});

		JScrollPane scrollpanenhaphang = new JScrollPane(tableNhapHang);

		panelleftnhaphang.add(scrollpaneNguyenLieu);

		panelleftnhaphang.add(scrollpanenhaphang);

		panelmiddlenhaphang = new JPanel();

		panelmiddlenhaphang.setBackground(new Color(250, 238, 232));
		panelmiddlenhaphang.setLayout(new GridLayout(2, 1));
		JPanel paneltranfer = new JPanel();

		paneltranfer.setBackground(new Color(250, 238, 232));
		paneltranfer.setLayout(new GridLayout(0, 1));
		paneltranfer.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		JLabel lbsoluong = new JLabel("Số lượng");
		JTextField txtsoluong = new JTextField();
		JLabel lbdongia = new JLabel("Đơn giá");
		JTextField txtdongia = new JTextField();
		JButton btntranfer = new JButton("=>");
		btntranfer.setBackground(Color.WHITE);
		btntranfer.setFocusable(false);
		btntranfer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtsoluong.getText().trim().equals("") || txtdongia.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng và đơn giá");
					return;
				}
				try {
					if (Integer.parseInt(txtsoluong.getText()) <= 0 || Integer.parseInt(txtdongia.getText()) < 0) {
						JOptionPane.showMessageDialog(null, "Giá trị không được bé hơn 0");
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Giá trị không hợp lệ");
					return;
				}
				int alreadynguyenlieurow = -1;
				for (int i = 0; i < modelctpn.getRowCount(); i++) {
					if (modelctpn.getValueAt(i, 0).toString()
							.equals(tableNguyenLieu.getValueAt(tableNguyenLieu.getSelectedRow(), 0).toString())) {
						alreadynguyenlieurow = i;
						break;
					}
				}
				if (alreadynguyenlieurow == -1) {
					modelctpn.addRow(
							new Object[] { tableNguyenLieu.getValueAt(tableNguyenLieu.getSelectedRow(), 0).toString(),
									txtsoluong.getText().trim(), txtdongia.getText().trim(), "X" });
				} else {
					modelctpn.setValueAt(Integer.parseInt(modelctpn.getValueAt(alreadynguyenlieurow, 1).toString())
							+ Integer.parseInt(txtsoluong.getText()), alreadynguyenlieurow, 1);
					modelctpn.setValueAt(Integer.parseInt(modelctpn.getValueAt(alreadynguyenlieurow, 2).toString())
							+ Integer.parseInt(txtdongia.getText()), alreadynguyenlieurow, 2);
				}
				// Tính tổng giá cho phiếu nhập
				int totalprice = 0;
				for (int i = 0; i < modelctpn.getRowCount(); i++) {
					totalprice += Integer.parseInt(modelctpn.getValueAt(i, 2).toString());
				}
				txttonggia.setText("" + totalprice);

			}
		});
		paneltranfer.add(lbsoluong);
		paneltranfer.add(txtsoluong);
		paneltranfer.add(lbdongia);
		paneltranfer.add(txtdongia);
		paneltranfer.add(btntranfer);

		JPanel paneltemp = new JPanel();
		paneltemp.setBackground(new Color(250, 238, 232));

		panelmiddlenhaphang.add(paneltranfer);
		panelmiddlenhaphang.add(paneltemp);

		panelrightnhaphang = new JPanel();
		panelrightnhaphang.setBackground(new Color(250, 238, 232));
		panelrightnhaphang.setPreferredSize(new Dimension(550, 0));
		panelrightnhaphang.setLayout(new GridLayout(2, 1, 0, 10));

		JPanel panelctpn = new JPanel();
		panelctpn.setBackground(new Color(250, 238, 232));
		panelctpn.setLayout(new BorderLayout());
		JPanel northctpn = new JPanel();

		northctpn.setBackground(new Color(250, 238, 232));
		northctpn.setLayout(new FlowLayout(FlowLayout.LEADING));
		northctpn.setPreferredSize(new Dimension(0, 30));
		JButton btncreatephieunhap = new JButton("+");

		btncreatephieunhap.setBackground(Color.WHITE);

		btncreatephieunhap.setFocusable(false);
		btncreatephieunhap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scrollpanectpn.setVisible(true);
				btndeletephieunhap.setVisible(false);
				tableNhapHang.clearSelection();
				panelInformation.setVisible(true);
				northInformation.remove(lbmaphieunhap);
				northInformation.remove(txtmaphieunhap);
				northInformation.remove(lbmanhanvien);
				northInformation.remove(txtmanhanvien);
				northInformation.revalidate();
				northInformation.repaint();
				northInformation.add(btnsave, 6);
				northInformation.revalidate();
				northInformation.repaint();
				datechooser.setEnabled(true);
				comboboxnhacc.setEnabled(true);
				modelctpn.setRowCount(0);
				LocalDateTime now = LocalDateTime.now();

				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
				datechooser.setDate(new Date(timestamp.getTime()));
				java.util.List<DTONhaCC> list = BUS.BUSNhaCC.getInstance().getAllNhaCC();
				comboboxnhacc.removeAllItems();
				for (DTONhaCC dto : list) {
					comboboxnhacc.addItem(dto.getMaNhaCC());
				}
				comboboxnhacc.setSelectedIndex(-1);
				txttonggia.setText("0");
				if (tableNguyenLieu.getSelectedRow() != -1) {
					panelmiddlenhaphang.setVisible(true);
				} else {
					panelmiddlenhaphang.setVisible(false);
				}
			}
		});
		btndeletephieunhap = new JButton("-");

		btndeletephieunhap.setBackground(Color.WHITE);

		btndeletephieunhap.setFocusable(false);
		btndeletephieunhap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!BUS.BUSPhieuNhap.getInstance()
						.delete(tableNhapHang.getValueAt(tableNhapHang.getSelectedRow(), 0).toString())) {
					JOptionPane.showMessageDialog(null, "Xoá phiếu nhập hàng thất bại");
					return;
				}
				loadDataNhapHang();
			}

		});
		northctpn.add(btncreatephieunhap);

		northctpn.add(btndeletephieunhap);
		JPanel centerctpn = new JPanel();

		centerctpn.setBackground(new Color(250, 238, 232));
		centerctpn.setLayout(new GridLayout(1, 1));
		modelctpn = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String columnCTPN[] = { "Mã nguyên liệu", "Số lượng", "Đơn giá", "" };

		modelctpn.setColumnIdentifiers(columnCTPN);
		tableCTPN = new JTable();

		tableCTPN.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableCTPN.setModel(modelctpn);
		tableCTPN.getColumnModel().getColumn(3).setPreferredWidth(15);
		tableCTPN.getColumnModel().getColumn(3).setMinWidth(15);
		tableCTPN.getColumnModel().getColumn(3).setMaxWidth(15);
		tableCTPN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = tableCTPN.getSelectedRow();
				int column = tableCTPN.getSelectedColumn();
				if (row != -1 && column == 3) {
					modelctpn.removeRow(row);
				}
			}

		});
		scrollpanectpn = new JScrollPane(tableCTPN);

		centerctpn.add(scrollpanectpn);

		panelctpn.add(northctpn, BorderLayout.NORTH);

		panelctpn.add(centerctpn, BorderLayout.CENTER);

		panelInformation = new JPanel();

		panelInformation.setBackground(new Color(250, 238, 232));
		panelInformation.setLayout(new BorderLayout());
		panelInformation.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
				BorderFactory.createEmptyBorder(10, 200, 10, 200)));

		northInformation = new JPanel();

		northInformation.setBackground(new Color(250, 238, 232));

		northInformation.setPreferredSize(new Dimension(0, 250));//
		northInformation.setLayout(new GridLayout(0, 1, 0, 5));
		lbmaphieunhap = new JLabel("Mã phiếu nhập");
		txtmaphieunhap = new JTextField();

		txtmaphieunhap.setEnabled(false);

		JLabel lbngaynhap = new JLabel("Ngày nhập");

		LocalDateTime now = LocalDateTime.now();
		java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(now);
		datechooser = new JDateChooser();

		datechooser.setDate(new Date(timestamp.getTime()));
		lbmanhanvien = new JLabel("Mã nhân viên");
		txtmanhanvien = new JTextField();

		txtmanhanvien.setEnabled(false);

		JLabel lbnhacc = new JLabel("Nhà cung cấp");
		comboboxnhacc = new JComboBox();
		JLabel lbtonggia = new JLabel("Tổng giá");
		txttonggia = new JTextField();

		txttonggia.setEnabled(false);

		btnsave = new JButton("Save");

		btnsave.setFocusable(false);
		btnsave.setBackground(Color.WHITE);
		btnsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (modelctpn.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa có nguyên liệu nào");
					return;
				}
				if (datechooser.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày");
					return;
				}
				if (comboboxnhacc.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp");
					return;
				}
				DTOCTPN[] listitem = new DTOCTPN[tableCTPN.getRowCount()];
				for (int i = 0; i < tableCTPN.getRowCount(); i++) {
					listitem[i] = new DTOCTPN(tableCTPN.getValueAt(i, 0).toString(),
							tableCTPN.getValueAt(i, 1).toString(), tableCTPN.getValueAt(i, 2).toString());
				}
				DTOPhieuNhap phieunhap = new DTOPhieuNhap(null,
						new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(datechooser.getDate()).toString(),
						txttonggia.getText(), comboboxnhacc.getSelectedItem().toString(),
						LoginView.getInstance().getTk().getUserName());
				if (!DAO.DAOPhieuNhap.getInstance().add(listitem, phieunhap)) {
					JOptionPane.showMessageDialog(null, "Thêm thất bại");
					return;
				}
				loadDataNhapHang();
				loadDataNguyenLieu();
			}

		});

		northInformation.add(lbmaphieunhap);

		northInformation.add(txtmaphieunhap);

		northInformation.add(lbngaynhap);

		northInformation.add(datechooser);

		northInformation.add(lbmanhanvien);

		northInformation.add(txtmanhanvien);

		northInformation.add(lbnhacc);

		northInformation.add(comboboxnhacc);

		northInformation.add(lbtonggia);

		northInformation.add(txttonggia);

		northInformation.add(btnsave);
		JPanel centerInformation = new JPanel();// Useless panel

		centerInformation.setBackground(new Color(250, 238, 232));
		panelInformation.add(northInformation, BorderLayout.NORTH);

		panelInformation.add(centerInformation, BorderLayout.CENTER);

		panelrightnhaphang.add(panelctpn);

		panelrightnhaphang.add(panelInformation);

		panelnhaphang.add(panelleftnhaphang, BorderLayout.WEST);

		panelnhaphang.add(panelmiddlenhaphang, BorderLayout.CENTER);

		panelnhaphang.add(panelrightnhaphang, BorderLayout.EAST);

	}
}
