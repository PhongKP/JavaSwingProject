package Controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BUS.QLQUYENBUS;
import Modal.QuyenModal;
import View.mainFrameView;
import View.qlQuyenView;

public class qlQuyenControll implements ActionListener{

	private qlQuyenView view;
	private QLQUYENBUS qlqBUS;
	private ArrayList<QuyenModal> dsq;
	
	public qlQuyenControll (qlQuyenView view)
	{
		this.view = view;
		qlqBUS = new QLQUYENBUS();
		dsq = qlqBUS.getDSQ();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Thêm"))
		{
			String idRole = this.view.getIdRole().getText();
			String nameRole = this.view.getNameRole().getText();
			if (idRole.trim().isEmpty() && nameRole.trim().isEmpty())
			{
				JOptionPane.showMessageDialog(view,
						  "Error 601: Không thể thêm vì bạn chưa điền dữ liệu",
						  "Error",
						  JOptionPane.ERROR_MESSAGE);
			}
			else if (qlqBUS.checkIdRole(idRole))
			{
				System.out.println(qlqBUS.checkIdRole(idRole));
				JOptionPane.showMessageDialog(view,
						  "Error 602: Mã quyền hoặc tên quyền đã tồn tại",
						  "Error",
						  JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				boolean success = qlqBUS.add(this.view.getIdRole().getText(),this.view.getNameRole().getText());
				if (success)
				{
					this.view.getModelTable().addRow(new Object[] {this.view.getIdRole().getText(),this.view.getNameRole().getText()});
				}
			}
		}
		if (src.equals("Tìm"))
		{
			String key = this.view.getSearchTextField().getText();
			if (key.trim().length() == 0)
			{
				this.view.getTableSorter().setRowFilter(null);
			}
			else
			{
				this.view.getTableSorter().setRowFilter(RowFilter.regexFilter("(?i)" + key));
			}
		}
		
		if (src.equals("Xóa"))
		{
			DefaultTableModel model = (DefaultTableModel) this.view.getModelTable();
			int selectedRow = this.view.getTable().getSelectedRow();
			if (selectedRow != -1)
			{
				model.removeRow(selectedRow);
				qlqBUS.del(selectedRow);
				this.view.getIdRole().setText("");
				this.view.getNameRole().setText("");
				model.fireTableDataChanged();
			}
		}
		if (src.equals("Sửa"))
		{
			DefaultTableModel model = (DefaultTableModel) this.view.getModelTable();
			int selectionRow = this.view.getTable().getSelectedRow();
			if (selectionRow != -1)
			{
				String curValue = this.view.getIdRole().getText();
				if (curValue.equals(this.view.getTable().getValueAt(selectionRow, 0)))
				{
					model.setValueAt(this.view.getNameRole().getText(), selectionRow, 1);
					qlqBUS.update(this.view.getIdRole().getText(), this.view.getNameRole().getText());
				}
				else
				{
					JOptionPane.showMessageDialog(view, 
												  "Error 507: Không thể thay đổi mã quyền",
												  "Error",
												  JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		if (src.equals("Reset"))
		{
			dsq = qlqBUS.getDSQ();
			DefaultTableModel model = (DefaultTableModel) this.view.getTableSorter().getModel();
			model.setRowCount(0);
			for (QuyenModal q : dsq)
			{
				model.addRow(new Object[] {q.getMaQuyen(),q.getTenQuyen()});
//				System.out.println(q.getTenQuyen());
			}
			this.view.getTableSorter().setRowFilter(null);
			RowFilter<? super TableModel, ? super Integer> cur = this.view.getTableSorter().getRowFilter();
			if (cur != null)
			{
				this.view.getTableSorter().setRowFilter(cur);
			}
			model.fireTableDataChanged();
		}
			
	}
	
}
