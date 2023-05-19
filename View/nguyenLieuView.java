package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Modal.DTOCongThuc;
import Modal.DTONguyenLieu;
import Modal.ProductModal;

public class nguyenLieuView extends JPanel{
    JButton btnsubmit;
    JPanel paneltextfield;
    JTextField txtmanguyenlieu;
    JLabel lbmanguyenlieu;
    JTextField txttennguyenlieu;
    JTextField txtmota;
    JTextField txtsoluong;
    JButton btndeleteNguyenLieu;
    DefaultTableModel modelNguyenLieu;
    JTable tableNguyenLieu;
    DefaultTableModel modelSanpham;
    JTable tableSanpham;
    DefaultTableModel modelCongthuc;
    JTable tableCongthuc;
    JPanel panelcongthuc;
    JScrollPane scrollpanecongthuc;
    JTextField txtsoluongtoadd;
    JButton btnaddnguyenlieutosanpham;
    JPanel panelbtnadd;
    
    public nguyenLieuView() {
        Init();
        loadDataNguyenLieu();
        loadDataSanPham();
    }
    
    public void loadDataSanPham() {
        txtsoluongtoadd.setText("");
        panelbtnadd.setVisible(false);
        scrollpanecongthuc.setVisible(false);
        modelSanpham.setRowCount(0);
        modelCongthuc.setRowCount(0);
        java.util.List<ProductModal> list = BUS.BUSCongThuc.getInstance().getAllSanPham();
        for (ProductModal dto : list) {
            modelSanpham.addRow(new Object[]{dto.getProduct_id(), dto.getProduct_name(), dto.getProduct_state(), dto.getProduct_note()});
            
        }
    }
    
    public void loadDataNguyenLieu() {
        txtsoluongtoadd.setText("");
        panelbtnadd.setVisible(false);
        btndeleteNguyenLieu.setVisible(false);
        paneltextfield.setVisible(false);
        txtmanguyenlieu.setText("");
        txttennguyenlieu.setText("");
        txtmota.setText("");
        txtsoluong.setText("");
        modelNguyenLieu.setRowCount(0);
        java.util.List<DTONguyenLieu> list = BUS.BUSNguyenLieu.getInstance().getAll();
        for (DTONguyenLieu dto : list) {
            modelNguyenLieu.addRow(new Object[]{dto.getMaNguyenLieu(), dto.getTenNguyenLieu(), dto.getMota(), dto.getSoLuong()});
        }
        loadDataSanPham();
    }
    
    public void Init() {
        setBackground(new Color(250, 238, 232));
        setLayout(new BorderLayout());
        JPanel panelNguyenLieu = new JPanel();
        panelNguyenLieu.setBackground(new Color(250, 238, 232));
        panelNguyenLieu.setPreferredSize(new Dimension(550, 0));
        panelNguyenLieu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelNguyenLieu.setLayout(new GridLayout(2, 1));
        
        JPanel panelInformation = new JPanel();
        panelInformation.setBackground(new Color(250, 238, 232));
        panelInformation.setLayout(new BorderLayout());
        JPanel panelbutton = new JPanel();
        panelbutton.setBackground(new Color(250, 238, 232));
        panelbutton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelbutton.setPreferredSize(new Dimension(0, 30));
        panelbutton.setLayout(new FlowLayout(FlowLayout.LEADING));
        JButton btncreateNguyenLieu = new JButton("+");
        btncreateNguyenLieu.setPreferredSize(new Dimension(50, 20));
        btncreateNguyenLieu.setMaximumSize(new Dimension(50, 20));
        btncreateNguyenLieu.setMinimumSize(new Dimension(50, 20));
        btncreateNguyenLieu.setFocusable(false);
        btncreateNguyenLieu.setBackground(Color.WHITE);
        btncreateNguyenLieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btndeleteNguyenLieu.setVisible(false);
                paneltextfield.setVisible(true);
                txtmanguyenlieu.setText("");
                
                paneltextfield.remove(txtmanguyenlieu);
                paneltextfield.remove(lbmanguyenlieu);
                paneltextfield.revalidate();
                paneltextfield.repaint();
                
                txttennguyenlieu.setText("");
                txtmota.setText("");
                txtsoluong.setText("");
                tableNguyenLieu.clearSelection();
                btnsubmit.setText("Thêm");
                panelbtnadd.setVisible(false);
            }
            
        });
        btndeleteNguyenLieu = new JButton("-");
        btndeleteNguyenLieu.setFocusable(false);
        btndeleteNguyenLieu.setVisible(false);
        btndeleteNguyenLieu.setBackground(Color.WHITE);
        btndeleteNguyenLieu.setPreferredSize(new Dimension(50, 20));
        btndeleteNguyenLieu.setMaximumSize(new Dimension(50, 20));
        btndeleteNguyenLieu.setMinimumSize(new Dimension(50, 20));
        btndeleteNguyenLieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!BUS.BUSNguyenLieu.getInstance().isExists(txtmanguyenlieu.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Nguyên liệu không tồn tại");
                    loadDataNguyenLieu();
                    return;
                }
                if (!BUS.BUSNguyenLieu.getInstance().delete(txtmanguyenlieu.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Xoá nguyên liệu thất bại");
                    return;
                }
                loadDataNguyenLieu();
                
            }
            
        });
        panelbutton.add(btncreateNguyenLieu);
        panelbutton.add(btndeleteNguyenLieu);
        
        paneltextfield = new JPanel();
        paneltextfield.setBackground(new Color(250, 238, 232));
        paneltextfield.setVisible(false);
        paneltextfield.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 10, 0, 10), BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(10, 50, 10, 50))));
        paneltextfield.setLayout(new GridLayout(0, 1));
        lbmanguyenlieu = new JLabel("Mã nguyên liệu");
        txtmanguyenlieu = new JTextField();
        txtmanguyenlieu.setEnabled(false);
        JLabel lbtennguyenlieu = new JLabel("Tên nguyên liệu");
        txttennguyenlieu = new JTextField();
        JLabel lbmota = new JLabel("Mô tả");
        txtmota = new JTextField();
        JLabel lbsoluong = new JLabel("Số lượng");
        txtsoluong = new JTextField();
        JPanel panelsubmit = new JPanel();
        panelsubmit.setBackground(new Color(250, 238, 232));
        panelsubmit.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnsubmit = new JButton("Sửa");
        btnsubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txttennguyenlieu.getText().trim().equals("") || txtmota.getText().trim().equals("") || txtsoluong.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }
                try {
                    if (Integer.parseInt(txtsoluong.getText().trim()) < 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ");
                    return;
                }
                if (btnsubmit.getText().equals("Sửa")) {
                    if (!BUS.BUSNguyenLieu.getInstance().isExists(txtmanguyenlieu.getText().trim())) {
                        JOptionPane.showMessageDialog(null, "Nguyên liệu không tồn tại");
                        loadDataNguyenLieu();
                        return;
                    }
                    if (!BUS.BUSNguyenLieu.getInstance().edit(new DTONguyenLieu(txtmanguyenlieu.getText().trim(), txttennguyenlieu.getText().trim(), txtmota.getText().trim(), txtsoluong.getText().trim()))) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                        return;
                    }
                    
                } else {
                    
                    if (!BUS.BUSNguyenLieu.getInstance().add(new DTONguyenLieu(null, txttennguyenlieu.getText().trim(), txtmota.getText().trim(), txtsoluong.getText().trim()))) {
                        JOptionPane.showMessageDialog(null, "Thêm nguyên liệu thất bại");
                        return;
                    }
                    
                }
                loadDataNguyenLieu();
            }
            
        });
        btnsubmit.setFocusable(false);
        btnsubmit.setBackground(Color.WHITE);
        btnsubmit.setPreferredSize(new Dimension(200, 20));
        btnsubmit.setMaximumSize(new Dimension(200, 20));
        btnsubmit.setMinimumSize(new Dimension(200, 20));
        
        panelsubmit.add(btnsubmit);
        
        paneltextfield.add(lbmanguyenlieu);
        paneltextfield.add(txtmanguyenlieu);
        paneltextfield.add(lbtennguyenlieu);
        paneltextfield.add(txttennguyenlieu);
        paneltextfield.add(lbmota);
        paneltextfield.add(txtmota);
        paneltextfield.add(lbsoluong);
        paneltextfield.add(txtsoluong);
        paneltextfield.add(panelsubmit);
        
        panelInformation.add(panelbutton, BorderLayout.NORTH);
        panelInformation.add(paneltextfield, BorderLayout.CENTER);
        
        JPanel panelTableNguyenLieu = new JPanel();
        panelTableNguyenLieu.setBackground(new Color(250, 238, 232));
        panelTableNguyenLieu.setLayout(new GridLayout(1, 1));
        panelTableNguyenLieu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        modelNguyenLieu = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String columnNguyenLieu[] = {"Mã NL", "Tên nguyên liệu", "Mô tả", "Số lượng"};
        modelNguyenLieu.setColumnIdentifiers(columnNguyenLieu);
        tableNguyenLieu = new JTable();
        
        tableNguyenLieu.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableNguyenLieu.setModel(modelNguyenLieu);
        tableNguyenLieu.getColumnModel().getColumn(0).setPreferredWidth(70);
        tableNguyenLieu.getColumnModel().getColumn(0).setMaxWidth(70);
        tableNguyenLieu.getColumnModel().getColumn(0).setMinWidth(70);
        tableNguyenLieu.addMouseListener(
                new MouseAdapter() {
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e
            ) {
                int row = tableNguyenLieu.getSelectedRow();
                if (row == -1) {
                    return;
                }
                DTONguyenLieu dto = BUS.BUSNguyenLieu.getInstance().getNguyenLieuByID(tableNguyenLieu.getValueAt(row, 0).toString());
                if (dto == null) {
                    JOptionPane.showMessageDialog(null, "Nguyên liệu không tồn tại");
                    loadDataNguyenLieu();
                    return;
                }
                txtmanguyenlieu.setText(dto.getMaNguyenLieu());
                txttennguyenlieu.setText(dto.getTenNguyenLieu());
                txtmota.setText(dto.getMota());
                txtsoluong.setText(dto.getSoLuong());
                paneltextfield.setVisible(true);
                paneltextfield.add(lbmanguyenlieu,0);
                paneltextfield.add(txtmanguyenlieu,1);
                paneltextfield.revalidate();
                paneltextfield.repaint();
                
                btndeleteNguyenLieu.setVisible(true);
                btnsubmit.setText("Sửa");
                if (scrollpanecongthuc.isVisible()) {
                    panelbtnadd.setVisible(true);
                } else {
                    panelbtnadd.setVisible(false);
                }
            }
        }
        );
        JScrollPane scrollpanenguyenlieu = new JScrollPane(tableNguyenLieu);
        panelTableNguyenLieu.add(scrollpanenguyenlieu);
        panelNguyenLieu.add(panelInformation);
        panelNguyenLieu.add(panelTableNguyenLieu);
        
        JPanel panelbtnaddNguyenLieu = new JPanel();
        panelbtnaddNguyenLieu.setBackground(new Color(250, 238, 232));
        panelbtnaddNguyenLieu.setLayout(new GridLayout(2, 1));
        btnaddnguyenlieutosanpham = new JButton("=>");
        
        btnaddnguyenlieutosanpham.setBackground(Color.WHITE);
        btnaddnguyenlieutosanpham.setFocusable(false);
        btnaddnguyenlieutosanpham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtsoluongtoadd.getText().trim().equals("")) {
                    return;
                }
                try {
                    if (Integer.parseInt(txtsoluongtoadd.getText().trim()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ");
                    return;
                }
                if (!BUS.BUSNguyenLieu.getInstance().isExists(tableNguyenLieu.getValueAt(tableNguyenLieu.getSelectedRow(), 0).toString())) {
                    JOptionPane.showMessageDialog(null, "Nguyên liệu không còn tồn tại nữa");
                    loadDataNguyenLieu();
                    return;
                }
                if (!BUS.BUSCongThuc.getInstance().isExistsSanPham(tableSanpham.getValueAt(tableSanpham.getSelectedRow(), 0).toString())) {
                    JOptionPane.showMessageDialog(null, "Sản phẩm không còn tồn tại");
                    loadDataSanPham();
                    return;
                }
                if (!BUS.BUSCongThuc.getInstance().add(tableSanpham.getValueAt(tableSanpham.getSelectedRow(), 0).toString(), tableNguyenLieu.getValueAt(tableNguyenLieu.getSelectedRow(), 0).toString(), txtsoluongtoadd.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Thêm công thức thất bại");
                    return;
                }
                loadDataSanPham();
                
            }
            
        });
        JLabel lbsoluongtoadd = new JLabel("Số lượng");
        txtsoluongtoadd = new JTextField();
        
        panelbtnadd = new JPanel();
        panelbtnadd.setBackground(new Color(250, 238, 232));
        panelbtnadd.setLayout(new GridLayout(0, 1));
        panelbtnadd.setBorder(BorderFactory.createEmptyBorder(130, 0, 130, 0));
        panelbtnadd.add(lbsoluongtoadd);
        panelbtnadd.add(txtsoluongtoadd);
        panelbtnadd.add(btnaddnguyenlieutosanpham);
        JPanel temp = new JPanel();
        temp.setBackground(new Color(250, 238, 232));
        panelbtnaddNguyenLieu.add(temp);
        panelbtnaddNguyenLieu.add(panelbtnadd);
        
        panelcongthuc = new JPanel();
        panelcongthuc.setBackground(new Color(250, 238, 232));
        panelcongthuc.setPreferredSize(new Dimension(550, 0));
        panelcongthuc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panelcongthuc.setLayout(new GridLayout(2, 1, 0, 10));
        
        modelSanpham = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String columnSanpham[] = {"Mã SP", "Tên sản phẩm", "Trạng thái", "Ghi chú"};
        
        modelSanpham.setColumnIdentifiers(columnSanpham);
        tableSanpham = new JTable();
        
        tableSanpham.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableSanpham.setModel(modelSanpham);
        tableSanpham.getColumnModel().getColumn(0).setPreferredWidth(70);
        tableSanpham.getColumnModel().getColumn(0).setMaxWidth(70);
        tableSanpham.getColumnModel().getColumn(0).setMinWidth(70);
        JScrollPane scrollpaneSanpham = new JScrollPane(tableSanpham);
        tableSanpham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = tableSanpham.getSelectedRow();
                if (row == -1) {
                    return;
                }
                if (!BUS.BUSCongThuc.getInstance().isExistsSanPham(tableSanpham.getValueAt(row, 0).toString())) {
                    JOptionPane.showMessageDialog(null, "Sản phẩm không còn tồn tại");
                    loadDataSanPham();
                    return;
                }
                java.util.List<DTOCongThuc> list = BUS.BUSCongThuc.getInstance().getAllNguyenLieuByMaSP(tableSanpham.getValueAt(row, 0).toString());
                modelCongthuc.setRowCount(0);
                for (DTOCongThuc dto : list) {
                    modelCongthuc.addRow(new Object[]{dto.getMaNguyenLieu(), dto.getTenNguyenLieu(), dto.getSoLuong(), "X"});
                    
                }
                scrollpanecongthuc.setVisible(true);
                if (tableNguyenLieu.getSelectedRow() != -1) {
                    panelbtnadd.setVisible(true);
                }
            }
        }
        );
        
        modelCongthuc = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String columnCongthuc[] = {"Mã NL", "Tên nguyên liệu", "Số lượng", ""};
        
        modelCongthuc.setColumnIdentifiers(columnCongthuc);
        tableCongthuc = new JTable();
        
        tableCongthuc.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tableCongthuc.setModel(modelCongthuc);
        
        tableCongthuc.getColumnModel().getColumn(0).setMaxWidth(70);
        
        tableCongthuc.getColumnModel().getColumn(0).setMinWidth(70);
        
        tableCongthuc.getColumnModel().getColumn(0).setPreferredWidth(70);
        
        tableCongthuc.getColumnModel().getColumn(3).setMaxWidth(20);
        
        tableCongthuc.getColumnModel().getColumn(3).setMinWidth(20);
        
        tableCongthuc.getColumnModel().getColumn(3).setPreferredWidth(20);
        tableCongthuc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = tableCongthuc.getSelectedRow();
                int column = tableCongthuc.getSelectedColumn();
                
                if (row != -1 && column == 3) {
                    if (!BUS.BUSNguyenLieu.getInstance().isExists(tableCongthuc.getValueAt(row, 0).toString())) {
                        JOptionPane.showMessageDialog(null, "Nguyên liệu không còn tồn tại");
                        loadDataNguyenLieu();
                        loadDataSanPham();
                        return;
                    }
                    if (!BUS.BUSCongThuc.getInstance().isExistsSanPham(tableSanpham.getValueAt(tableSanpham.getSelectedRow(), 0).toString())) {
                        JOptionPane.showMessageDialog(null, "Sản phẩm không còn tồn tại");
                        loadDataSanPham();
                        return;
                    }
                    if (!BUS.BUSCongThuc.getInstance().isExistsNguyenLieuInSP(tableSanpham.getValueAt(tableSanpham.getSelectedRow(), 0).toString(), tableCongthuc.getValueAt(row, 0).toString())) {
                        JOptionPane.showMessageDialog(null, "Công thức của sản phẩm không còn chứa nguyên liệu này");
                        loadDataSanPham();
                        return;
                    }
                    if (!BUS.BUSCongThuc.getInstance().delete(tableSanpham.getValueAt(tableSanpham.getSelectedRow(), 0).toString(), tableCongthuc.getValueAt(row, 0).toString())) {
                        JOptionPane.showMessageDialog(null, "Xoá công thức thất bại");
                        return;
                    }
                    loadDataSanPham();
                }
            }
        });
        
        scrollpanecongthuc = new JScrollPane(tableCongthuc);
        
        panelcongthuc.add(scrollpaneSanpham);
        
        panelcongthuc.add(scrollpanecongthuc);
        
        add(panelNguyenLieu, BorderLayout.WEST);
        
        add(panelbtnaddNguyenLieu, BorderLayout.CENTER);
        
        add(panelcongthuc, BorderLayout.EAST);
        
    }
}
