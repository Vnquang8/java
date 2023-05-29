package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import BEAN.Sach;
import DAO.SachDAO;

public class EventControllerSach {
	public List<Sach> getAllSach() {
		SachDAO dao = new SachDAO();
		List<Sach> list = dao.getAllSach();
		return list;
	}
	
	public boolean ThemSach(Sach s) {
		SachDAO dao = new SachDAO();
		Boolean check = dao.ThemSach(s);
		return check;
	}
	
	public boolean XoaSach(int id) {
		SachDAO dao = new SachDAO();
		Boolean check = dao.XoaSach(id);
		return check;
	}
	
	public boolean SuaSach(Sach s) {
		SachDAO dao = new SachDAO();
		Boolean check = dao.SuaSach(s);
		return check;
	}
	
	public List<Sach> getSachByThuVien(int id){
		SachDAO dao = new SachDAO();
		List<Sach> list = dao.getSachByThuVien(id);
		return list;
	}
}
