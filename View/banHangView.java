package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import BUS.BUSHoaDon;
import BUS.CustomerBUS;
import BUS.ProductBUS;
import Check.CheckLoi;
import Modal.CustomerModel;
import Modal.DTOCTHD;
import Modal.DTOHoaDon;
import Modal.ProductModal;

public class banHangView extends JPanel {

	private JPanel leftPanel, rightPanel;
	private JPanel product_cart;
	private JPanel rightPanelTop, rightPanelBottom, rightPanelBottomLeft, rightPanelBottomRight, rightPanelBottomBottom;

	private JLabel wait_bill, phone, fullName, other_price, note, product_total, bill_total, customer_pay, remain_money,
			pay_type, state, state_text;
	private JTextField phone_text, fullName_text, other_price_text, product_total_text, bill_total_text,
			customer_pay_text, remain_money_text;
	private JTextArea note_text;
	private JButton bill_add, bill_hide, search_button, cancel_button, split_button, bottom_add_button, pay_button;
	private JComboBox<String> pay_type_combobox;
	private JLabel[] product_cart_items;

	private ArrayList<String> type;
	private String[] wait_heading = { "Mã HD", "Người tạo", "Khách hàng", "TG Tạo", "Trạng thái", "Ghi chú" };
	private String[] bill_heading = { "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền", "Ghi chú", "" };
	private String[] pay_type_combobox_arr = { "Chọn 1 loại thanh toán", "Tiền mặt", "Chuyển khoản" };

	// wait bill
	private DefaultTableModel wait_bill_model;
	private JTable wait_bill_table;

	// right table
	private DefaultTableModel bill_model;
	private JTable bill_table;

	private JScrollPane product_cart_scroll;
	private JScrollPane wait_bill_scroll;
	private JScrollPane bill_scroll;

	// color
	private Color background_color = new Color(250, 240, 235);
	private Color border_color = new Color(211, 211, 211);
	private Color button_color = new Color(236, 236, 236);
	private Color button_color_clicked = new Color(200, 200, 200);

	private JTabbedPane tabbedPane;

	// Icon
	ImageIcon searchIcon_link = new ImageIcon("src/img/searchIcon.png");
	Image searchIconImage = searchIcon_link.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
	ImageIcon searchIcon = new ImageIcon(searchIconImage);

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	// Block table
	private boolean block = true;

	// bus
	private ProductBUS product_bus;

	// model
	private ArrayList<ProductModal> product_model;

	// Customize JOptionPane
	private Object options[] = { "Có", "Không" };

	private Map<String, Object> hiddenTabData;

	public banHangView() {
		init();
	}

	public void init() {
		// bus
		product_bus = new ProductBUS();

		// Get data from database
		product_model = product_bus.getProducts();

		product_cart_items = new JLabel[product_model.size()];

		this.setLayout(new GridLayout(1, 2, 4, 4));

		leftPanel = new JPanel(null);
		rightPanel = new JPanel(null);

		rightPanelTop = new JPanel(null);

		Border border = BorderFactory.createLineBorder(border_color);

		hiddenTabData = new HashMap<>();

		// begin left
		wait_bill = new JLabel("Hóa đơn chờ");
		wait_bill_model = new DefaultTableModel(wait_heading, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		wait_bill_table = new JTable(wait_bill_model);
		wait_bill_scroll = new JScrollPane(wait_bill_table);

		// load data wait
		List<DTOHoaDon> list_wait_bill = BUSHoaDon.getInstance().getAllHoaDonWaitOrder();
		for (DTOHoaDon item : list_wait_bill) {
			wait_bill_model.addRow(new Object[] { item.getMaHD(), item.getTenNV(), item.getMaKH(),
					item.getThoiGianTao(), item.getTrangThai(), item.getGhiChu() });
		}

		renderProductCart();

		product_cart_scroll.setBounds(10, 100, 600, 400);
		wait_bill.setBounds(10, 502, 80, 24);
		wait_bill_scroll.setBounds(10, 522, 600, 234);

		leftPanel.add(wait_bill);
		leftPanel.add(wait_bill_scroll);
		// end left
		
		// begin right
		rightPanelTop.setBounds(6, 86, 616, 40);

		bill_add = new JButton("+");
		bill_add.setBounds(500, 10, 44, 30);
		bill_hide = new JButton("-");
		bill_hide.setBounds(556, 10, 44, 30);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 140, 614, 620);

		wait_bill_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

					JPanel rightPanelBottom = new JPanel(null);

					int selectedRow = wait_bill_table.getSelectedRow();

					if (selectedRow != -1) {
						Object rowData = wait_bill_table.getValueAt(selectedRow, 0);

						rightPanelBottomBottom = new JPanel(new GridLayout(1, 2, 2, 0));
						rightPanelBottomBottom.setBounds(0, 216, 620, 325);

						rightPanelBottomLeft = new JPanel(null);
						rightPanelBottomRight = new JPanel(null);

						phone = new JLabel("Số điện thoại");
						phone_text = new JTextField(20);

						search_button = new JButton();
						search_button.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								List<String> listSDT = CustomerBUS.getInstance().getAllSDT();
								Boolean success = false;
								String inputPhone = phone_text.getText().trim().replaceAll("[^0-9]", "");
								for (String sdt : listSDT) {
									if (sdt.equalsIgnoreCase(inputPhone)) {
										success = true;
										String name = CustomerBUS.getInstance().getNameBySDT(sdt);
										fullName_text.setText(name);
										String makh = BUSHoaDon.getInstance().getIDCustomerBySDT(sdt);
										String mahd = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
										BUSHoaDon.getInstance().updateIDCustomer(makh, mahd);
										wait_bill_model.setRowCount(0);

										List<DTOHoaDon> list = BUSHoaDon.getInstance().getAllHoaDonWaitOrder();
										for (DTOHoaDon item : list)
											wait_bill_model.addRow(new Object[] { item.getMaHD(), item.getTenNV(),
													item.getMaKH(), item.getThoiGianTao(), item.getTrangThai(),
													item.getGhiChu() });

									}
								}
								if (!success)
									JOptionPane.showMessageDialog(null,
											"Error: Không thể tìm thấy số điện thoại phù hợp");

							}

						});
						fullName = new JLabel("Họ tên");
						fullName_text = new JTextField(100);
						fullName_text.setEnabled(false);
						bottom_add_button = new JButton("+");
						other_price = new JLabel("Tiền thuế");
						other_price_text = new JTextField(100);
						other_price_text.setEnabled(false);
//						other_price_text.getDocument().addDocumentListener(new DocumentListener() {
//
//							@Override
//							public void removeUpdate(DocumentEvent e) {
//								// TODO Auto-generated method stub
//								int tongtienhoadon = Integer.parseInt(product_total_text.getText())
//										+ Integer.parseInt(other_price_text.getText());
//								bill_total_text.setText("" + tongtienhoadon);
//								//
//								int selectedTab = tabbedPane.getSelectedIndex();
//								String IDSelectedTab = tabbedPane.getTitleAt(selectedTab);
//							}
//
//							@Override
//							public void insertUpdate(DocumentEvent e) {
//								// TODO Auto-generated method stub
//								int tongtienhoadon = Integer.parseInt(product_total_text.getText())
//										+ Integer.parseInt(other_price_text.getText());
//								bill_total_text.setText("" + tongtienhoadon);
//								// BUSHoaDon.getInstance().udateHoaDon(new DTOHoaDon());
//								int selectedTab = tabbedPane.getSelectedIndex();
//								String IDSelectedTab = tabbedPane.getTitleAt(selectedTab);
//
//							}
//
//							@Override
//							public void changedUpdate(DocumentEvent e) {
//								// TODO Auto-generated method stub
//								int tongtienhoadon = Integer.parseInt(product_total_text.getText())
//										+ Integer.parseInt(other_price_text.getText());
//								bill_total_text.setText("" + tongtienhoadon);
//								// BUSHoaDon.getInstance().udateHoaDon(new DTOHoaDon());
//								int selectedTab = tabbedPane.getSelectedIndex();
//								String IDSelectedTab = tabbedPane.getTitleAt(selectedTab);
//
//							}
//						});

						note = new JLabel("Ghi chú");
						note_text = new JTextArea();

						bill_model = new DefaultTableModel(bill_heading, 0) {
							@Override
							public boolean isCellEditable(int row, int column) {
								return column == 6 ? true : false;
							};
						};
						bill_table = new JTable(bill_model);
						bill_scroll = new JScrollPane(bill_table);
						bill_table.getColumnModel().getColumn(1).setPreferredWidth(150);

						cancel_button = new JButton("Hủy");
						split_button = new JButton("Tách");
						pay_button = new JButton("Thanh toán");
						pay_button.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								int selectedIndex = tabbedPane.getSelectedIndex();
								String getHeaderSelectedTab = tabbedPane.getTitleAt(selectedIndex);
								String payType = (String) pay_type_combobox.getSelectedItem();

								if (Double.valueOf(customer_pay_text.getText()) < Double.valueOf(bill_total_text.getText()))
								{
									JOptionPane.showMessageDialog(null, "Vui lòng trả đủ tiền trước khi thanh toán");
									return;
								}
								
								if (phone_text.getText().isEmpty() && !CheckLoi.checkExistSDT(phone_text.getText()))
								{
									JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin khách trước khi thanh toán");
									return;
								}
								
								if (!payType.isEmpty()) {
									BUSHoaDon.getInstance().updatePayType(payType, getHeaderSelectedTab);
								} else {
									JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hình thức thanh toán");
								}
								
								if (!note_text.getText().isEmpty())
								{
									BUSHoaDon.getInstance().updateGhiChu(note_text.getText(), getHeaderSelectedTab);
								}

								if (!customer_pay_text.getText().isEmpty())
									BUSHoaDon.getInstance().updateCustomerPay(customer_pay_text.getText(),
											getHeaderSelectedTab);
								else
									JOptionPane.showMessageDialog(null,
											"Error: Vui lòng hãy thanh toán trước khi xác nhận");

								if (BUSHoaDon.getInstance().changeToWaitConfirm(getHeaderSelectedTab)) {
									long curTime = System.currentTimeMillis();
									String payTime = String.format("%tY-%tm-%td %tT", curTime, curTime, curTime,
											curTime);
									BUSHoaDon.getInstance().updatePayTime(payTime,
											tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
									JOptionPane.showMessageDialog(null, "Thanh toán hóa đơn thành công!");
								}

								wait_bill_model.setRowCount(0);

								List<DTOHoaDon> list = BUSHoaDon.getInstance().getAllHoaDonWaitOrder();
								for (DTOHoaDon item : list)
									wait_bill_model
											.addRow(new Object[] { item.getMaHD(), item.getTenNV(), item.getMaKH(),
													item.getThoiGianTao(), item.getTrangThai(), item.getGhiChu() });
								
								int selectTab = tabbedPane.getSelectedIndex();
								tabbedPane.removeTabAt(selectTab);
							}
						});

						product_total = new JLabel("Tổng tiền sản phẩm");
						product_total_text = new JTextField("0", 100);
						product_total_text.setEnabled(false);

						bill_total = new JLabel("Tổng tiền hóa đơn");

						bill_total_text = new JTextField("0", 100);
						bill_total_text.setEnabled(false);
						customer_pay = new JLabel("Tiền khách trả");
						customer_pay_text = new JTextField(100);
						remain_money = new JLabel("Tiền thừa");

						remain_money_text = new JTextField(100);
						remain_money_text.setEnabled(false);
						pay_type = new JLabel("Loại thanh toán");
						pay_type_combobox = new JComboBox<String>(pay_type_combobox_arr);
						state = new JLabel("Trạng thái");
						state_text = new JLabel("Chờ order");

						bill_scroll.setBounds(8, 8, 594, 200);

						phone.setBounds(10, 6, 100, 24);
						phone_text.setBounds(10, 30, 220, 24);
						search_button.setBounds(235, 30, 60, 23);

						fullName.setBounds(10, 60, 60, 24);
						fullName_text.setBounds(10, 84, 220, 24);
						bottom_add_button.setBounds(235, 84, 60, 23);

						other_price.setBounds(10, 112, 60, 24);
						other_price_text.setBounds(10, 136, 286, 24);

						note.setBounds(10, 168, 60, 24);
						note_text.setBounds(10, 194, 286, 122);

						cancel_button.setBounds(6, 546, 590 / 3, 40);
						split_button.setBounds(207, 546, 590 / 3, 40);
						pay_button.setBounds(408, 546, 590 / 3, 40);

						product_total.setBounds(10, 6, 120, 24);
						product_total_text.setBounds(10, 30, 286, 24);
						bill_total.setBounds(10, 60, 120, 24);
						bill_total_text.setBounds(10, 84, 286, 24);

						customer_pay.setBounds(10, 112, 100, 24);
						customer_pay_text.setBounds(10, 136, 280 / 2, 24);
						remain_money.setBounds(310 / 2, 112, 60, 24);
						remain_money_text.setBounds(310 / 2, 136, 280 / 2, 24);

						pay_type.setBounds(10, 168, 120, 24);
						pay_type_combobox.setBounds(10, 194, 280 / 2, 24);
						state.setBounds(310 / 2, 168, 60, 24);
						state_text.setBounds(310 / 2, 194, 280 / 2, 24);

						// customize
						search_button.setFocusable(false);
						bottom_add_button.setFocusable(false);
						cancel_button.setFocusable(false);
						split_button.setFocusable(false);
						pay_button.setFocusable(false);

						search_button.setBackground(button_color);
						bottom_add_button.setBackground(button_color);
						cancel_button.setBackground(button_color);
						split_button.setBackground(button_color);
						pay_button.setBackground(button_color);

						rightPanelBottomLeft.setBorder(border);
						rightPanelBottomRight.setBorder(border);

						search_button.setIcon(searchIcon);

						rightPanelBottomLeft.setBackground(background_color);
						rightPanelBottomRight.setBackground(background_color);
						rightPanelBottomBottom.setBackground(background_color);
						rightPanelBottom.setBackground(background_color);
						rightPanelTop.setBackground(background_color);

						rightPanelBottomLeft.add(phone);
						rightPanelBottomLeft.add(phone_text);
						rightPanelBottomLeft.add(search_button);
						rightPanelBottomLeft.add(fullName);
						rightPanelBottomLeft.add(fullName_text);
						rightPanelBottomLeft.add(bottom_add_button);
						rightPanelBottomLeft.add(other_price);
						rightPanelBottomLeft.add(other_price_text);
						rightPanelBottomLeft.add(note);
						rightPanelBottomLeft.add(note_text);

						rightPanelBottomRight.add(product_total);
						rightPanelBottomRight.add(product_total_text);
						rightPanelBottomRight.add(bill_total);
						rightPanelBottomRight.add(bill_total_text);

						rightPanelBottomRight.add(customer_pay);
						rightPanelBottomRight.add(customer_pay_text);
						rightPanelBottomRight.add(remain_money);
						rightPanelBottomRight.add(remain_money_text);

						rightPanelBottomRight.add(pay_type);
						rightPanelBottomRight.add(pay_type_combobox);
						rightPanelBottomRight.add(state);
						rightPanelBottomRight.add(state_text);

						rightPanelBottomBottom.add(rightPanelBottomLeft);
						rightPanelBottomBottom.add(rightPanelBottomRight);

						rightPanelBottom.add(bill_scroll);
						rightPanelBottom.add(rightPanelBottomBottom);
						rightPanelBottom.add(cancel_button);
						rightPanelBottom.add(split_button);
						rightPanelBottom.add(pay_button);

						int indexOfTab = tabbedPane.indexOfTab(rowData.toString());
						Object tabData = hiddenTabData.get(rowData.toString());

						// If tab already exists, switch to it, else create a new one
						if (indexOfTab != -1) {
							Component tabContents = tabbedPane.getComponentAt(indexOfTab);
							tabbedPane.setSelectedComponent(tabContents);
						} else if (tabData instanceof Component) {
							Component tabContents = (Component) tabData;
							tabbedPane.addTab(rowData.toString(), null, tabContents, null);
							tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
						} else {
							tabbedPane.addTab(rowData.toString(), null, rightPanelBottom, null);
							List<DTOCTHD> listcthd = BUSHoaDon.getInstance().getCTHDByID(rowData.toString());
							for (DTOCTHD item : listcthd) {
								bill_model.addRow(new Object[] { item.getMaSP(), item.getTenSP(), item.getDonGia(),
										item.getSoLuong(), item.getThanhTien(), item.getGhiChu() });
							}
							DTOHoaDon hoadon = BUSHoaDon.getInstance().getHoaDonByID(rowData.toString());
							phone_text.setText(hoadon.getSDT());
							fullName_text.setText(hoadon.getTenKH());
							other_price_text.setText(hoadon.getTienThue());
							note_text.setText(hoadon.getGhiChu());
							String idTab = wait_bill_model.getValueAt(selectedRow, 0).toString();
							Double tongtiensp = BUSHoaDon.getInstance().getAllThanhTien(idTab);
							product_total_text.setText("" + tongtiensp);
							bill_total_text.setText(hoadon.getTongGia());
							customer_pay_text.setText(hoadon.getTienKhachDua());
							Double tienThua = 0.00;
							if (Integer.valueOf(hoadon.getTienKhachDua()) >= Integer.valueOf(hoadon.getTongGia())) {
								tienThua = Double.parseDouble(hoadon.getTienKhachDua())
										- Double.parseDouble(hoadon.getTongGia());
							}
							remain_money_text.setText(tienThua + "");
							pay_type_combobox.setSelectedItem("Chọn 1 loại thanh toán");

							bill_table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
							bill_table.getColumnModel().getColumn(6).setCellEditor(new ButtonRenderer());
							tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
						}

						bill_model.fireTableDataChanged();
						bill_table.repaint();
						bill_table.revalidate();

						// action
						bottom_add_button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								displayCustomerPanel();
							}
						});

						
						pay_type_combobox.addItemListener(new ItemListener() {

							@Override
							public void itemStateChanged(ItemEvent e) {
								String selectedPay = pay_type_combobox.getSelectedItem().toString();
								if (selectedPay.equalsIgnoreCase("Chuyển Khoản")) {
									String tongTien = bill_total_text.getText();
									customer_pay_text.setText(tongTien);
									customer_pay_text.setEnabled(false);
									remain_money_text.setText("0");
								} else if (selectedPay.equalsIgnoreCase("Tiền mặt")) {
									customer_pay_text.setEnabled(true);
									KeyListener currentKeyListener = null;
									KeyListener[] keyListeners = customer_pay_text.getKeyListeners();
									for (KeyListener listener : keyListeners) {
									    if (listener instanceof KeyListener) {
									        currentKeyListener = listener;
									        break;
									    }
									}
									if (currentKeyListener != null) {
									    customer_pay_text.removeKeyListener(currentKeyListener);
									}
									customer_pay_text.addKeyListener(new KeyListener() {

										@Override
										public void keyTyped(KeyEvent e) {
											// TODO Auto-generated method stub

										}

										@Override
										public void keyReleased(KeyEvent e) {
											// TODO Auto-generated method stub

										}

										@Override
										public void keyPressed(KeyEvent e) {
											String input = customer_pay_text.getText();
											if (!input.isEmpty()) {
												if (isValidNumber(input)) {
													updateCustomerPay();
												} else {
													JOptionPane.showMessageDialog(null,
															"Error: Đặt lại định dạng phù hợp");
												}
											} else {
												remain_money_text.setText("0");
											}
										}
									});

								}
								

							}
						});

						cancel_button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int choice = JOptionPane.showOptionDialog(null, "Bạn muốn hủy hóa đơn này?",
										"Hủy hóa đơn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
										options, options[0]);

								// Get index of the current tab
								int selectedIndex = tabbedPane.getSelectedIndex();

								if (choice == JOptionPane.YES_OPTION && selectedIndex != -1) {
									// Get the header of the selected tab
									String getHeaderSelectedTab = tabbedPane.getTitleAt(selectedIndex);

									// Find the row in the table with the same invoice code
									int rowCount = wait_bill_table.getRowCount();
									int selectedRowModel = -1;
									for (int i = 0; i < rowCount; i++) {
										String invoiceCode = wait_bill_table.getValueAt(i, 0).toString();
										if (getHeaderSelectedTab.equalsIgnoreCase(invoiceCode)) {
											selectedRowModel = wait_bill_table.convertRowIndexToModel(i);
											break;
										}
									}

									if (selectedRowModel != -1) {
										// Get the contents of the selected tab
										JPanel selectedContents = (JPanel) tabbedPane.getComponentAt(selectedIndex);

										// Set null before removing tab
										for (Component c : selectedContents.getComponents()) {
											if (c instanceof JTextField)
												((JTextField) c).setText("");
											if (c instanceof JTextArea)
												((JTextArea) c).setText("");
										}
										pay_type_combobox.setSelectedItem("Chọn 1 loại thanh toán");

										// Remove the selected tab
										Component removedComponent = tabbedPane.getComponentAt(selectedIndex);
										hiddenTabData.remove(getHeaderSelectedTab, removedComponent);
										tabbedPane.remove(removedComponent);

										if (tabbedPane.getTabCount() == 0) {
											Container parent = tabbedPane.getParent();
											parent.remove(tabbedPane);
											// Update layout correctly
											parent.revalidate();
											parent.repaint();
										}

										if (BUSHoaDon.getInstance().Cancel(getHeaderSelectedTab))
											JOptionPane.showMessageDialog(null, "Hủy hóa đơn thành công!");

										wait_bill_model.setRowCount(0);

										List<DTOHoaDon> list = BUSHoaDon.getInstance().getAllHoaDonWaitOrder();
										for (DTOHoaDon item : list)
											wait_bill_model.addRow(new Object[] { item.getMaHD(), item.getTenNV(),
													item.getMaKH(), item.getThoiGianTao(), item.getTrangThai(),
													item.getGhiChu() });

									}
								} else if (selectedIndex == -1)
									rightPanel.remove(tabbedPane);
							}
						});

						split_button.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								displaySplitBillPanel();

							}
						});
					}
				}

				rightPanel.add(tabbedPane);

			}
		});

		// customize
		product_cart.setBorder(border);

		bill_add.setFocusable(false);
		bill_hide.setFocusable(false);

		bill_add.setBackground(button_color);
		bill_hide.setBackground(button_color);
		leftPanel.setBackground(background_color);
		rightPanel.setBackground(background_color);
		rightPanelTop.setBackground(background_color);
		tabbedPane.setBackground(background_color);

		// action
		bill_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Pay the bill before creating a new bill
				int choice = JOptionPane.showOptionDialog(null, "Bạn muốn tạo hóa đơn?", "Tạo hóa đơn",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if (choice == JOptionPane.YES_OPTION) {
					// Create id automatically

					String maHoaDon = BUSHoaDon.getInstance().getNewMaHD();

					// Create real time
					LocalDateTime realTime = LocalDateTime.now();
					String time_string = realTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

					// Create state
					String state_string = "Chờ order";

					// Mã NHân viên (Nhớ set)
					if (BUSHoaDon.getInstance()
							.addHoaDon(new DTOHoaDon(maHoaDon, time_string, null, "0",
									LoginView.getInstance().getTk().getUserName(), null, state_string, null, null, null,
									"Tiền mặt", "", "0", "0")))
						JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công!");

					wait_bill_model.setRowCount(0);

					List<DTOHoaDon> list = BUSHoaDon.getInstance().getAllHoaDonWaitOrder();
					for (DTOHoaDon item : list)
						wait_bill_model.addRow(new Object[] { item.getMaHD(), item.getTenNV(), item.getMaKH(),
								item.getThoiGianTao(), item.getTrangThai(), item.getGhiChu() });
				}
			}
		});

		bill_hide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showOptionDialog(null, "Bạn muốn ẩn hóa đơn này?", "Ẩn hóa đơn",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				int index = tabbedPane.getSelectedIndex();

				if (index != -1) {

					if (choice == JOptionPane.YES_OPTION) {
						// Remove the second tab from the JTabbedPane and store its data in the Map

						Component tabContents = tabbedPane.getComponentAt(index);
						hiddenTabData.put(tabbedPane.getTitleAt(index), tabContents);
						tabbedPane.removeTabAt(index);

						if (tabbedPane.getTabCount() == 0) {
							Container parent = tabbedPane.getParent();
							parent.remove(tabbedPane);
							// Update layout correctly
							parent.revalidate();
							parent.repaint();

						}
					}
				} else
					rightPanel.remove(tabbedPane);

			}
		});

		rightPanelTop.add(bill_add);
		rightPanelTop.add(bill_hide);

		rightPanel.add(rightPanelTop);
		rightPanel.repaint();
		rightPanel.revalidate();
		// end right

		this.add(leftPanel);
		this.add(rightPanel);
	}

	public boolean isValidNumber(String input) {
		try {
			Long.parseLong(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void updateCustomerPay() {
		try {
			Long tienKhachtra = Long.valueOf(customer_pay_text.getText());
			Double pay = (double) (tienKhachtra * 10);
			String selectedTabID = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
			Double sum = BUSHoaDon.getInstance().getAllThanhTien(selectedTabID);
			Double tax = 0.1 * sum;
			Long remain_money = (long) (pay - (sum + tax));
			remain_money_text.setText(remain_money.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	// Add a new customer
	public void displayCustomerPanel() {
		JFrame customerFrame = new JFrame();
		JPanel customer_panel = new JPanel(new GridLayout(1, 2, 4, 2));
		JPanel leftPanel = new JPanel(null);
		JPanel rightPanel = new JPanel(null);

		JLabel customer_id, customer_name, customer_phone, customer_address;
		JTextField customer_id_text, customer_name_text, customer_phone_text;
		JTextArea customer_address_text;
		JButton customer_add, customer_update;
		String[] customer_heading = { "Mã KH", "Tên KH", "Số ĐT" };
		DefaultTableModel customer_model;
		JTable customer_table;
		JScrollPane customer_scroll;

		customer_id = new JLabel("Mã KH");
		customer_name = new JLabel("Họ tên");
		customer_phone = new JLabel("Số ĐT");
		customer_address = new JLabel("Địa chỉ");
		customer_id_text = new JTextField(100);
		customer_id_text.setEnabled(false);
		customer_name_text = new JTextField(100);
		customer_phone_text = new JTextField(100);
		customer_address_text = new JTextArea();
		customer_add = new JButton("Thêm");
		customer_update = new JButton("Cập nhật");

		customer_model = new DefaultTableModel(customer_heading, 0);
		customer_table = new JTable(customer_model);
		customer_scroll = new JScrollPane(customer_table);
		List<CustomerModel> list = CustomerBUS.getInstance().getAllCustomer();
		for (CustomerModel dto : list) {
			customer_model.addRow(new Object[] { dto.getMakh(), dto.getTenkh(), dto.getSdt() });
		}

		customer_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = customer_table.getSelectedRow();
					if (selectedRow != -1) {
						String makh = customer_table.getValueAt(selectedRow, 0).toString();
						CustomerModel cus = CustomerBUS.getInstance().getCustomerByID(makh);
						if (cus != null) {
							customer_id_text.setText(cus.getMakh());
							customer_name_text.setText(cus.getTenkh());
							customer_phone_text.setText(cus.getSdt());
							customer_address_text.setText(cus.getDiachi());
						}

					}
				}
			}
		});

		String idKH = CustomerBUS.getInstance().getAutoIncresementID();
		customer_id_text.setText(idKH);

		customer_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (customer_name_text.getText().isEmpty() || customer_phone_text.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error: Chưa điền đủ thông tin để cập nhật!!!");
				} else if (CheckLoi.checkExistSDT(customer_phone_text.getText())) {
					JOptionPane.showMessageDialog(null, "Error: Số điện thoại đã tồn tại, Vui lòng thử số khác!!!");
				} else {

					if (CustomerBUS.getInstance().add(customer_id_text.getText(), customer_name_text.getText(),
							customer_phone_text.getText(), customer_address_text.getText())) {
						customer_model.addRow(new Object[] { customer_id_text.getText(), customer_name_text.getText(),
								customer_phone_text.getText() });
						String mahd = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
						fullName_text.setText(customer_name_text.getText());
						phone_text.setText(customer_phone_text.getText());
						BUSHoaDon.getInstance().updateIDCustomer(customer_id_text.getText(), mahd);
						wait_bill_model.setRowCount(0);
						List<DTOHoaDon> list = BUSHoaDon.getInstance().getAllHoaDonWaitOrder();
						for (DTOHoaDon item : list)
							wait_bill_model.addRow(new Object[] { item.getMaHD(), item.getTenNV(), item.getMaKH(),
									item.getThoiGianTao(), item.getTrangThai(), item.getGhiChu() });
					}

				}
			}
		});

		customer_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (customer_name_text.getText().isEmpty() || customer_phone_text.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error: Chưa điền đủ thông tin để cập nhật!!!");
				} else {
					int selectedRow = customer_table.getSelectedRow();
					if (selectedRow != -1) {
						customer_model.setValueAt(customer_id_text.getText(), selectedRow, 0);
						customer_model.setValueAt(customer_name_text.getText(), selectedRow, 1);
						customer_model.setValueAt(customer_phone_text.getText(), selectedRow, 2);
					}
					CustomerBUS.getInstance().update(customer_id_text.getText(), customer_name_text.getText(),
							customer_phone_text.getText(), customer_address_text.getText());
					customer_model.fireTableDataChanged();
				}

			}
		});

		customer_id.setBounds(12, 12, 60, 24);
		customer_name.setBounds(12, 64, 60, 24);
		customer_phone.setBounds(12, 116, 60, 24);
		customer_address.setBounds(12, 166, 60, 24);
		customer_id_text.setBounds(12, 34, 316, 24);
		customer_name_text.setBounds(12, 86, 316, 24);
		customer_phone_text.setBounds(12, 138, 316, 24);
		customer_address_text.setBounds(12, 188, 316, 150);
		customer_add.setBounds(12, 350, 310 / 2, 40);
		customer_update.setBounds((310 / 2) + 18, 350, 310 / 2, 40);
		customer_scroll.setBounds(12, 12, 316, 380);

		customer_add.setFocusable(false);
		customer_update.setFocusable(false);
		customer_add.setBackground(button_color);
		customer_update.setBackground(button_color);
		leftPanel.setBackground(background_color);
		rightPanel.setBackground(background_color);

		leftPanel.add(customer_id);
		leftPanel.add(customer_name);
		leftPanel.add(customer_phone);
		leftPanel.add(customer_address);
		leftPanel.add(customer_id_text);
		leftPanel.add(customer_name_text);
		leftPanel.add(customer_phone_text);
		leftPanel.add(customer_address_text);
		leftPanel.add(customer_add);
		leftPanel.add(customer_update);

		rightPanel.add(customer_scroll);

		customer_panel.add(leftPanel);
		customer_panel.add(rightPanel);
		customerFrame.add(customer_panel);

		customerFrame.setTitle("Thông tin khách hàng");
		customerFrame.setSize(700, 440);
		customerFrame.setResizable(false);
		customerFrame.setLocationRelativeTo(null);
		customerFrame.setVisible(true);

	}

	public void displaySplitBillPanel() {
		JFrame splitBillFrame = new JFrame();
		JPanel splitBill_panel = new JPanel(null);

		JLabel splitBill_id, splitBill_id_text;

		String[] splitBill_heading = { "Mã SP", "Tên SP", "Đơn giá", "Số lượng" };
//		String[] row_1 = { "SP01", "Cà phê mè", "68.000", "30" };
//		String[] row_2 = { "SP02", "Cà phê cốt dừa", "50.000", "60" };
//		String[] row_3 = { "SP03", "Cà phê muối", "49.000", "100" };

		DefaultTableModel splitBill_modelBefore;
		JTable splitBill_tableBefore;
		JScrollPane splitBill_scrollBefore;

		DefaultTableModel splitBill_modelAfter;
		JTable splitBill_tableAfter;
		JScrollPane splitBill_scrollAfter;

		JButton splitBill_splitButton, splitBill_confirmButton, splitBill_cancelButton;

		splitBill_id = new JLabel("Mã HD: ");
		splitBill_id_text = new JLabel("");
		splitBill_id_text.setText(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));

		splitBill_id.setBounds(10, 8, 60, 24);
		splitBill_id_text.setBounds(60, 8, 60, 24);

		splitBill_modelBefore = new DefaultTableModel(splitBill_heading, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return !block && column == 3;
			}
		};
		splitBill_tableBefore = new JTable(splitBill_modelBefore);
		splitBill_scrollBefore = new JScrollPane(splitBill_tableBefore);

		TableModel model = bill_table.getModel();
		for (int j = 0; j < bill_model.getRowCount(); j++) {
			Object[] rowData = new Object[4];
			for (int i = 0; i < 4; i++)
				rowData[i] = model.getValueAt(j, i);
			splitBill_modelBefore.addRow(rowData);
		}

		splitBill_scrollBefore.setBounds(10, 40, 514, 200);

		splitBill_modelAfter = new DefaultTableModel(splitBill_heading, 0);
		splitBill_tableAfter = new JTable(splitBill_modelAfter);
		splitBill_scrollAfter = new JScrollPane(splitBill_tableAfter);
		splitBill_scrollAfter.setBounds(10, 310, 514, 200);

		splitBill_splitButton = new JButton("Tách sản phẩm");
		splitBill_splitButton.setBounds(210, 250, 130, 50);

		splitBill_confirmButton = new JButton("Xác nhận");
		splitBill_confirmButton.setBounds(148, 520, 130, 34);

		splitBill_cancelButton = new JButton("Hủy");
		splitBill_cancelButton.setBounds(282, 520, 130, 34);

		// Customize
		splitBill_panel.setBackground(background_color);

		splitBill_splitButton.setBackground(button_color);
		splitBill_confirmButton.setBackground(button_color);
		splitBill_cancelButton.setBackground(button_color);

		splitBill_splitButton.setFocusable(false);
		splitBill_confirmButton.setFocusable(false);
		splitBill_cancelButton.setFocusable(false);

		// action
		splitBill_splitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (block) {
					block = false;
				} else {

					for (int i = 0; i < splitBill_tableBefore.getRowCount(); i++) {
						String idProduct = (String) splitBill_tableBefore.getValueAt(i, 0);
						Long quantity = Long.valueOf((String) splitBill_tableBefore.getValueAt(i, 3));
						Long quantityBefore = Long.valueOf(BUSHoaDon.getInstance()
								.getQuantity(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()), idProduct));
						if (quantityBefore < quantity) {
							JOptionPane.showMessageDialog(null, "Error: Số lượng tách phải bé hơn số lượng hiện tại");
							return;
						}
					}
					block = true;
					check: for (int i = 0; i < splitBill_tableBefore.getRowCount(); i++) {
						String idProduct = (String) splitBill_tableBefore.getValueAt(i, 0);
						String nameProduct = (String) splitBill_tableBefore.getValueAt(i, 1);
						String priceProduct = (String) splitBill_tableBefore.getValueAt(i, 2);
						Long quantity = Long.valueOf((String) splitBill_tableBefore.getValueAt(i, 3));
						Long quantityBefore = Long.valueOf(BUSHoaDon.getInstance()
								.getQuantity(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()), idProduct));
						if (quantity == quantityBefore)
							continue;
						if (quantity == 0) {
							splitBill_modelBefore.removeRow(i);
						}
						for (int j = 0; j < splitBill_tableAfter.getRowCount(); j++) {
							if (idProduct.equals(splitBill_tableAfter.getValueAt(j, 0))) {
								Long oldValue = Long.valueOf((String) splitBill_tableAfter.getValueAt(j, 3));
								Long newValue = oldValue + quantityBefore - quantity;
								splitBill_tableAfter.setValueAt(newValue.toString(), j, 3);
								continue check;
							}
						}
						splitBill_modelAfter.addRow(
								new Object[] { idProduct, nameProduct, priceProduct, quantityBefore - quantity + "" });
					}
				}
			}
		});

		splitBill_confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!phone_text.getText().isEmpty())
				{
					LocalDateTime realTime = LocalDateTime.now();
					String time_string = realTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					String maHoaDonAfter = BUSHoaDon.getInstance().getNewMaHD();
					String maspAfter = "";
					String tenspAfter = "";
					String dongiaAfter = "";
					String soluongAfter = "";
					String getIDhdAfter = maHoaDonAfter;
					int tonggia = 0;
					BUSHoaDon.getInstance().delAllCTHD(splitBill_id_text.getText());
					for (int i = 0; i < splitBill_modelBefore.getRowCount(); i++) {
						int dongia = Integer.valueOf(splitBill_modelBefore.getValueAt(i, 2).toString());
						int soluong = Integer.valueOf(splitBill_modelBefore.getValueAt(i, 3).toString());
						int thanhTien = dongia * soluong;
						BUSHoaDon.getInstance()
								.addCTHD(new DTOCTHD(splitBill_id_text.getText(),
										splitBill_modelBefore.getValueAt(i, 0).toString(), null,
										splitBill_modelBefore.getValueAt(i, 2).toString(),
										splitBill_modelBefore.getValueAt(i, 3).toString(), thanhTien + "", ""));
					}
					Double totalBillBefore = BUSHoaDon.getInstance().getAllThanhTien(splitBill_id_text.getText());
					int totalBill_BeforeCast = (int) (Math.round(totalBillBefore) * 1.1);
					BUSHoaDon.getInstance().updateTongGia(splitBill_id_text.getText(), totalBill_BeforeCast);
					String maKhNew = CustomerBUS.getInstance().getIDBySDT(phone_text.getText());
					String tax = "";
					Integer thanhTien = 0;
					for (int k =0;k<splitBill_modelAfter.getRowCount();k++)
					{
						thanhTien += Integer.valueOf(splitBill_modelAfter.getValueAt(k, 2).toString()) * Integer.valueOf(splitBill_modelAfter.getValueAt(k, 3).toString());
					}
					tax = (0.1*thanhTien) + "";
					BUSHoaDon.getInstance()
							.addHoaDon(new DTOHoaDon(maHoaDonAfter, time_string, null, null,
									LoginView.getInstance().getTk().getUserName(), maKhNew, "Chờ order",
									trangChuView.getInstance().getTenUser(LoginView.getInstance().getTk().getUserName()),
									null, null, "Tiền mặt", null, "0", tax));
					boolean success = false;
					for (int j = 0; j < splitBill_modelAfter.getRowCount(); j++) {
						maspAfter = (String) splitBill_modelAfter.getValueAt(j, 0);
						tenspAfter = (String) splitBill_modelAfter.getValueAt(j, 1);
						dongiaAfter = (String) splitBill_modelAfter.getValueAt(j, 2);
						soluongAfter = (String) splitBill_modelAfter.getValueAt(j, 3);
						success = BUSHoaDon.getInstance()
								.addCTHD(new DTOCTHD(getIDhdAfter, maspAfter, tenspAfter, dongiaAfter, soluongAfter,
										(Integer.valueOf(dongiaAfter) * Integer.valueOf(soluongAfter)) + "", ""));
					}
					if (success) {
						Double tongTien = BUSHoaDon.getInstance().getAllThanhTien(getIDhdAfter);
						tonggia = (int) Math.round(tongTien);
						BUSHoaDon.getInstance().updateTongGia(getIDhdAfter, tonggia);
					}
					wait_bill_model.setRowCount(0);
					List<DTOHoaDon> list = BUSHoaDon.getInstance().getAllHoaDonWaitOrder();
					for (DTOHoaDon item : list) {
						wait_bill_model.addRow(new Object[] { item.getMaHD(), item.getTenNV(), item.getTenKH(),
								item.getThoiGianTao(), item.getTrangThai(), item.getGhiChu() });
					}
					wait_bill_model.fireTableDataChanged();
					wait_bill_table.repaint();

					bill_model.setRowCount(0);
					List<DTOCTHD> listCTHDBefore = BUSHoaDon.getInstance().getCTHDByID(splitBill_id_text.getText());
					for (DTOCTHD item : listCTHDBefore) {
						bill_model.addRow(new Object[] { item.getMaSP(), item.getTenSP(), item.getDonGia(),
								item.getSoLuong(), item.getThanhTien(), item.getGhiChu() });
					}
					bill_model.fireTableDataChanged();
					bill_table.repaint();

					int selectTab = tabbedPane.getSelectedIndex();
					tabbedPane.removeTabAt(selectTab);

					BUSHoaDon.getInstance().updateTax((totalBillBefore * 0.1) + "", splitBill_id_text.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin trước khi tách hóa đơn");
				}

			}
		});

		splitBill_cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int choice = JOptionPane.showOptionDialog(null, "Bạn muốn hủy tách hóa đơn?", "Hủy",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				if (choice == JOptionPane.YES_NO_OPTION) {
					// Set default value before removing panel
					splitBill_modelAfter.setRowCount(0);
					splitBill_modelBefore.setRowCount(0);

					// Removing panel
					Container parent = splitBill_panel.getParent();
					parent.remove(splitBill_panel);
					splitBillFrame.setVisible(false);
					parent.revalidate();
					parent.repaint();
				}
			}
		});

		splitBill_panel.add(splitBill_id);
		splitBill_panel.add(splitBill_id_text);
		splitBill_panel.add(splitBill_scrollBefore);
		splitBill_panel.add(splitBill_scrollAfter);
		splitBill_panel.add(splitBill_splitButton);
		splitBill_panel.add(splitBill_confirmButton);
		splitBill_panel.add(splitBill_cancelButton);

		splitBillFrame.add(splitBill_panel);

		splitBillFrame.setTitle("Tách hóa đơn");
		splitBillFrame.setSize(550, 606);
		splitBillFrame.setResizable(false);
		splitBillFrame.setLocationRelativeTo(null);
		splitBillFrame.setVisible(true);
	}

	public void renderProductCart() {
		// color
		Color product_cart_color = new Color(140, 109, 99);
		Color product_cart_font = Color.WHITE;

		// product cart
		product_cart = new JPanel(new GridLayout(4, 4, 8, 8));
		product_cart_scroll = new JScrollPane(product_cart);

		Dimension product_cart_size = new Dimension(110, 170);

		// Option for each cart
		JPopupMenu menu = new JPopupMenu();
		JMenuItem item_addToBill = new JMenuItem("Thêm vào HĐ");
		menu.add(item_addToBill);

		// add items to product cart
		for (int i = 0; i < product_model.size(); i++) {
			product_cart_items[i] = new JLabel();
			product_cart_items[i].setLayout(null);

			JLabel product_cart_image = new JLabel();
			product_cart_image.setBounds(8, 8, 122, 120);
			product_cart_image.setOpaque(true);
			String stringImage = product_model.get(i).getProduct_image();
			if (stringImage != null && !stringImage.isEmpty() && stringImage.length() >= 2
					&& CheckLoi.checkBase64(stringImage)) {
				byte[] imageData = Base64.getDecoder().decode(stringImage);
				ImageIcon image = new ImageIcon(imageData);
				Image imageScale = image.getImage().getScaledInstance(134, 120, Image.SCALE_SMOOTH);
				ImageIcon new_image = new ImageIcon(imageScale);
				product_cart_image.setIcon(new_image);
			}

			String product_name = product_model.get(i).getProduct_name();
			JLabel product_cart_name = new JLabel(product_name);
			product_cart_name.setForeground(product_cart_font);
			product_cart_name.setBounds(8, 126, 122, 24);
			product_cart_name.setHorizontalAlignment(SwingConstants.CENTER);
			product_cart_name.setVerticalAlignment(SwingConstants.CENTER);

			String product_price = String.valueOf(product_model.get(i).getProduct_price());
			JLabel product_cart_price = new JLabel(product_price);
			product_cart_price.setForeground(product_cart_font);
			product_cart_price.setBounds(8, 144, 122, 24);
			product_cart_price.setHorizontalAlignment(SwingConstants.CENTER);
			product_cart_price.setVerticalAlignment(SwingConstants.CENTER);

			String product_id = product_model.get(i).getProduct_id();
			JLabel product_cart_id = new JLabel(product_id);

			product_cart_items[i].add(product_cart_image);
			product_cart_items[i].add(product_cart_name);
			product_cart_items[i].add(product_cart_price);
			product_cart_items[i].add(product_cart_id);

			product_cart_items[i].setPreferredSize(product_cart_size);

			product_cart_items[i].setOpaque(true);
			product_cart_items[i].setBackground(product_cart_color);

			product_cart.add(product_cart_items[i]);

			JLabel temp = product_cart_items[i];

			temp.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e))
						menu.show(temp, e.getX(), e.getY());
				}
			});

		}

		item_addToBill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Get selected product infor
				JLabel selectedItem = (JLabel) menu.getInvoker();
				JLabel product_name = (JLabel) selectedItem.getComponent(1);
				JLabel product_price = (JLabel) selectedItem.getComponent(2);
				JLabel product_id = (JLabel) selectedItem.getComponent(3);

				// Check if the selected product is already in the bill
				int selectedTab = tabbedPane.getSelectedIndex();
				String selectedTabID = tabbedPane.getTitleAt(selectedTab);

				JPanel selectedPane = (JPanel) tabbedPane.getSelectedComponent();
				JScrollPane selectedScrollPane = (JScrollPane) selectedPane.getComponent(0);
				JTable selectedTable = (JTable) selectedScrollPane.getViewport().getView();
				TableModel model_new = selectedTable.getModel();

				int selectedRow = -1;
				int billRowCount = model_new.getRowCount();
				for (int i = 0; i < billRowCount; i++) {
					String id = model_new.getValueAt(i, 0).toString();
					if (id.equalsIgnoreCase(product_id.getText())) {
						selectedRow = i;
						break;
					}
				}

				Double price = Double.parseDouble(product_price.getText());
				// If the selected product is already in the bill, increase the amount by one
				if (selectedRow != -1) {
					int amount = Integer.parseInt(model_new.getValueAt(selectedRow, 3).toString());
					amount++;
					price = Double.parseDouble(product_price.getText()) * amount;
				}

				BUSHoaDon.getInstance()
						.addCTHD(new DTOCTHD(selectedTabID, product_id.getText(), null, null, "1", "" + price, null));
				((DefaultTableModel) model_new).setRowCount(0);

				Double sum = BUSHoaDon.getInstance().getAllThanhTien(selectedTabID);
				Double tax = 0.1 * sum;
				Double customer_pay = 0.0;
				if (customer_pay_text.getText() != null) {
					customer_pay = Double.valueOf(customer_pay_text.getText());
				}
				other_price_text.setText(tax + "");
				product_total_text.setText(sum + "");
				bill_total_text.setText("" + (sum + Double.parseDouble(other_price_text.getText())));
				remain_money_text.setText(customer_pay - (sum + tax) + "");
				BUSHoaDon.getInstance().updateTongTienHoaDon(selectedTabID, sum + tax + "");
				BUSHoaDon.getInstance().updateTax(tax.toString(), selectedTabID);

				List<DTOCTHD> list = BUSHoaDon.getInstance().getCTHDByID(selectedTabID);
				for (DTOCTHD dto : list) {
					((DefaultTableModel) model_new).addRow(new Object[] { dto.getMaSP(), dto.getTenSP(),
							dto.getDonGia(), dto.getSoLuong(), dto.getThanhTien(), dto.getGhiChu() });

				}
				selectedTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
				selectedTable.getColumnModel().getColumn(6).setCellEditor(new ButtonRenderer());

				((AbstractTableModel) model_new).fireTableDataChanged();
				selectedTable.repaint();
				selectedTable.revalidate();

			}

		});

		leftPanel.add(product_cart_scroll);
		leftPanel.repaint();
		leftPanel.revalidate();
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public JPanel getproduct_cart() {
		return product_cart;
	}

	public void setproduct_cart(JPanel product_cart) {
		this.product_cart = product_cart;
	}

	public JPanel getRightPanelTop() {
		return rightPanelTop;
	}

	public void setRightPanelTop(JPanel rightPanelTop) {
		this.rightPanelTop = rightPanelTop;
	}

	public JPanel getRightPanelBottom() {
		return rightPanelBottom;
	}

	public void setRightPanelBottom(JPanel rightPanelBottom) {
		this.rightPanelBottom = rightPanelBottom;
	}

	public JPanel getRightPanelBottomLeft() {
		return rightPanelBottomLeft;
	}

	public void setRightPanelBottomLeft(JPanel rightPanelBottomLeft) {
		this.rightPanelBottomLeft = rightPanelBottomLeft;
	}

	public JPanel getRightPanelBottomRight() {
		return rightPanelBottomRight;
	}

	public void setRightPanelBottomRight(JPanel rightPanelBottomRight) {
		this.rightPanelBottomRight = rightPanelBottomRight;
	}

	public JPanel getRightPanelBottomBottom() {
		return rightPanelBottomBottom;
	}

	public void setRightPanelBottomBottom(JPanel rightPanelBottomBottom) {
		this.rightPanelBottomBottom = rightPanelBottomBottom;
	}

	public JLabel getWait_bill() {
		return wait_bill;
	}

	public void setWait_bill(JLabel wait_bill) {
		this.wait_bill = wait_bill;
	}

	public JLabel getPhone() {
		return phone;
	}

	public void setPhone(JLabel phone) {
		this.phone = phone;
	}

	public JLabel getFullName() {
		return fullName;
	}

	public void setFullName(JLabel fullName) {
		this.fullName = fullName;
	}

	public JLabel getOther_price() {
		return other_price;
	}

	public void setOther_price(JLabel other_price) {
		this.other_price = other_price;
	}

	public JLabel getNote() {
		return note;
	}

	public void setNote(JLabel note) {
		this.note = note;
	}

	public JLabel getProduct_total() {
		return product_total;
	}

	public void setProduct_total(JLabel product_total) {
		this.product_total = product_total;
	}

	public JLabel getBill_total() {
		return bill_total;
	}

	public void setBill_total(JLabel bill_total) {
		this.bill_total = bill_total;
	}

	public JLabel getCustomer_pay() {
		return customer_pay;
	}

	public void setCustomer_pay(JLabel customer_pay) {
		this.customer_pay = customer_pay;
	}

	public JLabel getRemain_money() {
		return remain_money;
	}

	public void setRemain_money(JLabel remain_money) {
		this.remain_money = remain_money;
	}

	public JLabel getPay_type() {
		return pay_type;
	}

	public void setPay_type(JLabel pay_type) {
		this.pay_type = pay_type;
	}

	public JLabel getState() {
		return state;
	}

	public void setState(JLabel state) {
		this.state = state;
	}

	public JLabel getState_text() {
		return state_text;
	}

	public void setState_text(JLabel state_text) {
		this.state_text = state_text;
	}

	public JTextField getPhone_text() {
		return phone_text;
	}

	public void setPhone_text(JTextField phone_text) {
		this.phone_text = phone_text;
	}

	public JTextField getFullName_text() {
		return fullName_text;
	}

	public void setFullName_text(JTextField fullName_text) {
		this.fullName_text = fullName_text;
	}

	public JTextField getOther_price_text() {
		return other_price_text;
	}

	public void setOther_price_text(JTextField other_price_text) {
		this.other_price_text = other_price_text;
	}

	public JTextField getProduct_total_text() {
		return product_total_text;
	}

	public void setProduct_total_text(JTextField product_total_text) {
		this.product_total_text = product_total_text;
	}

	public JTextField getBill_total_text() {
		return bill_total_text;
	}

	public void setBill_total_text(JTextField bill_total_text) {
		this.bill_total_text = bill_total_text;
	}

	public JTextField getCustomer_pay_text() {
		return customer_pay_text;
	}

	public void setCustomer_pay_text(JTextField customer_pay_text) {
		this.customer_pay_text = customer_pay_text;
	}

	public JTextField getRemain_money_text() {
		return remain_money_text;
	}

	public void setRemain_money_text(JTextField remain_money_text) {
		this.remain_money_text = remain_money_text;
	}

	public JTextArea getNote_text() {
		return note_text;
	}

	public void setNote_text(JTextArea note_text) {
		this.note_text = note_text;
	}

	public JButton getBill_add() {
		return bill_add;
	}

	public void setBill_add(JButton bill_add) {
		this.bill_add = bill_add;
	}

	public JButton getbill_hide() {
		return bill_hide;
	}

	public void setbill_hide(JButton bill_hide) {
		this.bill_hide = bill_hide;
	}

	public JButton getSearch_button() {
		return search_button;
	}

	public void setSearch_button(JButton search_button) {
		this.search_button = search_button;
	}

	public JButton getCancel_button() {
		return cancel_button;
	}

	public void setCancel_button(JButton cancel_button) {
		this.cancel_button = cancel_button;
	}

	public JButton getSplit_button() {
		return split_button;
	}

	public void setSplit_button(JButton split_button) {
		this.split_button = split_button;
	}

	public JButton getBottom_add_button() {
		return bottom_add_button;
	}

	public void setBottom_add_button(JButton bottom_add_button) {
		this.bottom_add_button = bottom_add_button;
	}

	public JButton getPay_button() {
		return pay_button;
	}

	public void setPay_button(JButton pay_button) {
		this.pay_button = pay_button;
	}

	public JComboBox<String> getPay_type_combobox() {
		return pay_type_combobox;
	}

	public void setPay_type_combobox(JComboBox<String> pay_type_combobox) {
		this.pay_type_combobox = pay_type_combobox;
	}

	public String[] getWait_heading() {
		return wait_heading;
	}

	public void setWait_heading(String[] wait_heading) {
		this.wait_heading = wait_heading;
	}

	public String[] getBill_heading() {
		return bill_heading;
	}

	public void setBill_heading(String[] bill_heading) {
		this.bill_heading = bill_heading;
	}

	public String[] getPay_type_combobox_arr() {
		return pay_type_combobox_arr;
	}

	public void setPay_type_combobox_arr(String[] pay_type_combobox_arr) {
		this.pay_type_combobox_arr = pay_type_combobox_arr;
	}

	public DefaultTableModel getWait_bill_model() {
		return wait_bill_model;
	}

	public void setWait_bill_model(DefaultTableModel wait_bill_model) {
		this.wait_bill_model = wait_bill_model;
	}

	public JTable getWait_bill_table() {
		return wait_bill_table;
	}

	public void setWait_bill_table(JTable wait_bill_table) {
		this.wait_bill_table = wait_bill_table;
	}

	public DefaultTableModel getBill_model() {
		return bill_model;
	}

	public void setBill_model(DefaultTableModel bill_model) {
		this.bill_model = bill_model;
	}

	public JTable getBill_table() {
		return bill_table;
	}

	public void setBill_table(JTable bill_table) {
		this.bill_table = bill_table;
	}

	public JScrollPane getproduct_cart_scroll() {
		return product_cart_scroll;
	}

	public void setproduct_cart_scroll(JScrollPane product_cart_scroll) {
		this.product_cart_scroll = product_cart_scroll;
	}

	public JScrollPane getWait_bill_scroll() {
		return wait_bill_scroll;
	}

	public void setWait_bill_scroll(JScrollPane wait_bill_scroll) {
		this.wait_bill_scroll = wait_bill_scroll;
	}

	public JScrollPane getBill_scroll() {
		return bill_scroll;
	}

	public void setBill_scroll(JScrollPane bill_scroll) {
		this.bill_scroll = bill_scroll;
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

	public ImageIcon getSearchIcon_link() {
		return searchIcon_link;
	}

	public void setSearchIcon_link(ImageIcon searchIcon_link) {
		this.searchIcon_link = searchIcon_link;
	}

	public Image getSearchIconImage() {
		return searchIconImage;
	}

	public void setSearchIconImage(Image searchIconImage) {
		this.searchIconImage = searchIconImage;
	}

	public ImageIcon getSearchIcon() {
		return searchIcon;
	}

	public void setSearchIcon(ImageIcon searchIcon) {
		this.searchIcon = searchIcon;
	}

	public Dimension getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JPanel getProduct_cart() {
		return product_cart;
	}

	public void setProduct_cart(JPanel product_cart) {
		this.product_cart = product_cart;
	}

	public JButton getBill_hide() {
		return bill_hide;
	}

	public void setBill_hide(JButton bill_hide) {
		this.bill_hide = bill_hide;
	}

	public JLabel[] getProduct_cart_items() {
		return product_cart_items;
	}

	public void setProduct_cart_items(JLabel[] product_cart_items) {
		this.product_cart_items = product_cart_items;
	}

	public ArrayList<String> getType() {
		return type;
	}

	public void setType(ArrayList<String> type) {
		this.type = type;
	}

	public JScrollPane getProduct_cart_scroll() {
		return product_cart_scroll;
	}

	public void setProduct_cart_scroll(JScrollPane product_cart_scroll) {
		this.product_cart_scroll = product_cart_scroll;
	}

	public ProductBUS getProduct_bus() {
		return product_bus;
	}

	public void setProduct_bus(ProductBUS product_bus) {
		this.product_bus = product_bus;
	}

	public ArrayList<ProductModal> getProduct_model() {
		return product_model;
	}

	public void setProduct_model(ArrayList<ProductModal> product_model) {
		this.product_model = product_model;
	}

	public Map<String, Object> getHiddenTabData() {
		return hiddenTabData;
	}

	public void setHiddenTabData(Map<String, Object> hiddenTabData) {
		this.hiddenTabData = hiddenTabData;
	}

	public static banHangView instance;

	public static banHangView getInstance() {
		if (instance == null)
			instance = new banHangView();
		return instance;
	}
	
	public static void setInstance (banHangView bh)
	{
		instance = bh;
	}
}
