package Controll;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BUS.QLCTCNBUS;
import Modal.QuyenModal;
import Modal.chucNangModal;
import Modal.ctCNModal;
import View.quyenView;

public class quyenControll implements ActionListener {

	private quyenView view;
	private QLCTCNBUS qlctcnBUS;
	
	public quyenControll (quyenView view)
	{
		qlctcnBUS = new QLCTCNBUS();
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.view.getAddButton()))
		{
			addRole();
		}
		
		if (e.getSource().equals(this.view.getDelButton()))
		{
			delRole();
		}
		
		if (e.getSource().equals(this.view.getFindButton()))
		{
			findRow();
		}
		
		if (e.getSource().equals(this.view.getResetButton()))
		{
			resetTable();
		}
	}
	
	public void addRole()
	{
		String idRole = (String) this.view.getChoiceOfRoleID().getSelectedItem();
		String functional = (String) this.view.getChoiceOfFunctional().getSelectedItem();
		String idCN = "";
		String nameRole = "";
		for (QuyenModal q : view.getDSQ())
		{
			if (q.getMaQuyen().equals(idRole))
			{
				nameRole = q.getTenQuyen();
			}
		}
		for (chucNangModal cn : view.getDSCN())
		{
			if (functional.equals(cn.getTenChucNang()))
			{
				idCN = cn.getMaChucNang();
			}
		}
		
		if (qlctcnBUS.checkKey(idCN, idRole))
		{
				JOptionPane.showMessageDialog(view,
						  "Error 600: Chức năng cần thêm đã tồn tại vui lòng thay đổi chức năng khác",
						  "Error",
						  JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			boolean success = qlctcnBUS.add(idCN, idRole);
			if (success)
			{
			   view.getTableModel().addRow(new Object[] {idRole,idCN,nameRole,functional});
			}
		}
		
	}
	
	public void delRole ()
	{
		DefaultTableModel model = (DefaultTableModel) this.view.getTableModel();
		int selectionRow = this.view.getTable().getSelectedRow();
		if (selectionRow != -1)
		{
			model.removeRow(selectionRow);
			qlctcnBUS.del(selectionRow);
			model.fireTableDataChanged();
		}
	}
	
	public void findRow()
	{
		String roleName = (String) this.view.getChoiceOfRoleName().getSelectedItem();
		String functionName = (String) this.view.getChoiceOfFunctionalName().getSelectedItem();
//		System.out.println(roleName);
//		System.out.println(functionName);
		if (roleName.trim().isEmpty() && functionName.trim().isEmpty())
		{
			this.view.getTableSorter().setRowFilter(null);
			return;
		}
		
		RowFilter<TableModel,Integer> rowFillter = null;
		if (!roleName.trim().isEmpty() && functionName.trim().isEmpty())
		{
			rowFillter = RowFilter.regexFilter("(?i)" + roleName);
			this.view.getTableSorter().setRowFilter(rowFillter);
		}
		else if (roleName.trim().isEmpty() && !functionName.trim().isEmpty())
		{
			rowFillter = RowFilter.regexFilter("(?i)" + functionName);
			this.view.getTableSorter().setRowFilter(rowFillter);
		}
		else
		{
//			System.out.println("Success");
			RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
			    public boolean include(Entry<? extends Object, ? extends Object> entry) {
			        Object column1Value = entry.getValue(2);
			        Object column2Value = entry.getValue(3);
			        return (column1Value != null && column1Value.toString().toLowerCase().contains(roleName.toLowerCase())) 
			                && (column2Value != null && column2Value.toString().toLowerCase().contains(functionName.toLowerCase()));
			    }
			};
			this.view.getTableSorter().setRowFilter(filter);
		}
	}
	
	public void resetTable ()
	{
		ArrayList<ctCNModal> dsctcn = qlctcnBUS.getDSCTCN();
		DefaultTableModel model = (DefaultTableModel) this.view.getTableModel();
		model.setRowCount(0);
		for (ctCNModal ctcn : dsctcn)
		{
			model.addRow(new Object[] {ctcn.getMaQuyen(),ctcn.getMaChucNang(),this.view.getRoleNameByID(ctcn.getMaQuyen()),this.view.getNameFunctionalByID(ctcn.getMaChucNang())});
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
