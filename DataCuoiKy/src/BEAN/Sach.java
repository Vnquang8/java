package BEAN;

import java.util.Date;

public class Sach {
	private int MaSach;
	private String TenSach;
	private String XuatXu;
	private Date NgayXuatBan;
	private ThuVien tv;
	public Sach() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Sach [MaSach=" + MaSach + ", TenSach=" + TenSach + ", XuatXu=" + XuatXu + ", NgayXuatBan=" + NgayXuatBan
				+ ", tv=" + tv + "]";
	}
	public Sach(int maSach, String tenSach, String xuatXu, Date ngayXuatBan, ThuVien tv) {
		super();
		MaSach = maSach;
		TenSach = tenSach;
		XuatXu = xuatXu;
		NgayXuatBan = ngayXuatBan;
		this.tv = tv;
	}
	public int getMaSach() {
		return MaSach;
	}
	public void setMaSach(int maSach) {
		MaSach = maSach;
	}
	public String getTenSach() {
		return TenSach;
	}
	public void setTenSach(String tenSach) {
		TenSach = tenSach;
	}
	public String getXuatXu() {
		return XuatXu;
	}
	public void setXuatXu(String xuatXu) {
		XuatXu = xuatXu;
	}
	public Date getNgayXuatBan() {
		return NgayXuatBan;
	}
	public void setNgayXuatBan(Date ngayXuatBan) {
		NgayXuatBan = ngayXuatBan;
	}
	public ThuVien getTv() {
		return tv;
	}
	public void setTv(ThuVien tv) {
		this.tv = tv;
	}
}
