package Controller;

import java.util.List;

import BEAN.ThuVien;
import DAO.ThuVienDAO;

public class EventControllerThuVien {
	public List<ThuVien> getAllThuVien(){
		ThuVienDAO dao = new ThuVienDAO();
		List<ThuVien> list = dao.getAllThuVien();
		return list;
	}
	
	public ThuVien getThuVienByID(int id) {
		ThuVienDAO dao = new ThuVienDAO();
		ThuVien tv = dao.getThuVienByID(id);
		return tv;
	}
	
	public boolean ThemThuVien(ThuVien tv) {
		ThuVienDAO dao = new ThuVienDAO();
		Boolean check = dao.ThemThuVien(tv);
		return check;
	}
	
	public boolean SuaThuVien(ThuVien tv) {
		ThuVienDAO dao = new ThuVienDAO();
		Boolean check = dao.SuaThuVien(tv);
		return check;
	}
	
	public boolean XoaThuVien(int id) {
		ThuVienDAO dao = new ThuVienDAO();
		Boolean check = dao.XoaThuVien(id);
		return check;
	}
}
