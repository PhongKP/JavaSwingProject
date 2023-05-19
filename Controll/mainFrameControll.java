package Controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

import View.banHangView;
import View.hoaDonView;
import View.mainFrameView;
import View.nguyenLieuView;
import View.nhaCCView;
import View.nhanVienView;
import View.qlQuyenView;
import View.quyenView;
import View.sanPhamView;
import View.thongKeView;
import View.trangChuView;

public class mainFrameControll implements MouseListener,ActionListener{

	private mainFrameView view;
	
	public mainFrameControll (mainFrameView view)
	{
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		if (e.getSource() == view.trangChu)
//			this.view.setBG(view.trangChu);
//		else if (e.getSource() == view.banHang)
//			this.view.setBG(view.banHang);
//		else if (e.getSource() == view.hoaDon)
//			this.view.setBG(view.hoaDon);
//		else if (e.getSource() == view.sanPham)
//			this.view.setBG(view.sanPham);
//		else if (e.getSource() == view.thongKe)
//			this.view.setBG(view.thongKe);
//		else if (e.getSource() == view.nhanVien)
//			this.view.setBG(view.nhanVien);
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		if (e.getSource() == this.view.trangChu)
//			this.view.setDefaultBG(view.trangChu);
//		else if (e.getSource() == view.banHang)
//			this.view.setDefaultBG(view.banHang);
//		else if (e.getSource() == view.hoaDon)
//			this.view.setDefaultBG(view.hoaDon);
//		else if (e.getSource() == view.sanPham)
//			this.view.setDefaultBG(view.sanPham);
//		else if (e.getSource() == view.thongKe)
//			this.view.setDefaultBG(view.thongKe);
//		else if (e.getSource() == view.nhanVien)
//			this.view.setDefaultBG(view.nhanVien);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Trang Chủ"))
		{
			this.view.panelright.removeAll();
			trangChuView trangChu = new trangChuView();
			this.view.addPanel(trangChu);
		}
		else if (src.equals("Sản Phẩm"))
		{
			this.view.panelright.removeAll();
			sanPhamView sp = new sanPhamView();
			this.view.addPanel(sp);
		}
		else if (src.equals("Nhân Viên"))
		{
			this.view.panelright.removeAll();
			nhanVienView nv = new nhanVienView();
			this.view.addPanel(nv);
		}
		else if (src.equals("Hóa Đơn"))
		{
			this.view.panelright.removeAll();
			hoaDonView hd = new hoaDonView();
			this.view.addPanel(hd);
		}
		else if (src.equals("Thống Kê"))
		{
			this.view.panelright.removeAll();
			thongKeView tk = new thongKeView();
			tk.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			this.view.addPanel(tk);
		}
		else if (src.equals("Bán Hàng"))
		{
			this.view.panelright.removeAll();
			banHangView bh = new banHangView();
			banHangView.setInstance(bh);
			bh.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			this.view.addPanel(bh);
		}
		else if (src.equals("Tài Khoản"))
		{
			this.view.panelright.removeAll();
			quyenView q = new quyenView();
			q.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			this.view.addPanel(q);
		}
		else if (src.equals("Các Quyền"))
		{
			this.view.panelright.removeAll();
			qlQuyenView qlq = new qlQuyenView();
			qlq.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			this.view.addPanel(qlq);
		}
		else if (src.equals("Kho Hàng"))
		{
			this.view.panelright.removeAll();
			nhaCCView provideView = nhaCCView.getInstance();
			provideView.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			this.view.addPanel(provideView);
		}
		else if (src.equals("Nguyên Liệu"))
		{
			this.view.panelright.removeAll();
			nguyenLieuView nlView = new nguyenLieuView();
			nlView.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			this.view.addPanel(nlView);
		}
	}
	
	
}
