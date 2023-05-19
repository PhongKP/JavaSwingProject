package View;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import BUS.BUSHoaDon;
import Modal.DTOCTHD;

public class ButtonRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor{
	private JButton button;

	public ButtonRenderer() {
		this.button = new JButton("X");
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ("X".equalsIgnoreCase(e.getActionCommand())) {
					JTable table = (JTable) SwingUtilities.getAncestorOfClass(JTable.class, (Component) e.getSource());
					int modelRow = table.convertRowIndexToModel(table.getEditingRow());
					JTabbedPane tabpane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class,
							(Component) e.getSource());
					table.getCellEditor().stopCellEditing(); // stop editing
					String selectedProductID = table.getModel().getValueAt(modelRow, 0).toString();
					String selectedBillID = tabpane.getTitleAt(tabpane.getSelectedIndex());

					BUSHoaDon.getInstance().deleteCTHD(selectedBillID, selectedProductID);

					// new fix
					double sum = BUSHoaDon.getInstance().getAllThanhTien(selectedBillID);
					
					banHangView.getInstance().getProduct_total_text().setText(sum + "");

					banHangView.getInstance().getOther_price_text().setText((0.1 * sum) + "");					
					
					banHangView.getInstance().getBill_total_text().setText(
							"" + (sum + Double.parseDouble(banHangView.getInstance().getOther_price_text().getText())));

					List<DTOCTHD> list = BUSHoaDon.getInstance().getCTHDByID(selectedBillID);

					TableModel model = table.getModel();

					((DefaultTableModel) model).setRowCount(0);
					for (DTOCTHD dto : list)
						((DefaultTableModel) model).addRow(new Object[] { dto.getMaSP(), dto.getTenSP(),
								dto.getDonGia(), dto.getSoLuong(), dto.getThanhTien(), dto.getGhiChu() });

					table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
					table.getColumnModel().getColumn(6).setCellEditor(new ButtonRenderer());

					((AbstractTableModel) model).fireTableDataChanged();
					table.repaint();
					table.revalidate();

				}

			}
		});
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return this.button;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return this.button;
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

}
