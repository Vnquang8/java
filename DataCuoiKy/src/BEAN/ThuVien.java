package BEAN;

import java.util.ArrayList;
import java.util.List;

public class ThuVien {
	private int MaThuVien;
	private String TenThuVien;
	private List<Sach> list = new ArrayList<Sach>();
	public ThuVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ThuVien(int maThuVien, String tenThuVien, List<Sach> list) {
		super();
		MaThuVien = maThuVien;
		TenThuVien = tenThuVien;
		this.list = list;
	}
	public int getMaThuVien() {
		return MaThuVien;
	}
	public void setMaThuVien(int maThuVien) {
		MaThuVien = maThuVien;
	}
	public String getTenThuVien() {
		return TenThuVien;
	}
	public void setTenThuVien(String tenThuVien) {
		TenThuVien = tenThuVien;
	}
	public List<Sach> getList() {
		return list;
	}
	public void setList(List<Sach> list) {
		this.list = list;
	}
}
