package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import BEAN.Sach;
import BEAN.ThuVien;
import DAL.DBContext;

public class SachDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	public List<Sach> getAllSach(){
		List<Sach> list = new ArrayList<Sach>();
		
		try {
			String sql = "select * from Sach";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Sach s = new Sach();
				ThuVienDAO dao = new ThuVienDAO();
				ThuVien tv = dao.getThuVienByID(rs.getInt(5));
				s.setMaSach(rs.getInt(1));
				s.setTenSach(rs.getString(2));
				s.setNgayXuatBan(rs.getDate(3));
				s.setXuatXu(rs.getString(4));
				s.setTv(tv);
				list.add(s);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
		return list;
	}
	
	public boolean ThemSach(Sach s) {
		try {
			String sql = "insert into Sach values(?,?,?,?,?)";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, s.getMaSach());
			pstm.setString(2, s.getTenSach());
			pstm.setDate(3, (Date) s.getNgayXuatBan());
			pstm.setString(4, s.getXuatXu());
			pstm.setInt(5, s.getTv().getMaThuVien());
			pstm.executeUpdate();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean XoaSach(int id) {
		try {
			String sql = "delete from Sach where MaSach = ?";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean SuaSach(Sach s) {
		try {
			String sql = "update Sach set TenSach = ?, NgayXuatBan = ?, XuatXu = ?, MaThuVien = ? where MaSach = ?";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, s.getTenSach());
			pstm.setDate(2, (Date) s.getNgayXuatBan());
			pstm.setString(3, s.getXuatXu());
			pstm.setInt(4, s.getTv().getMaThuVien());
			pstm.setInt(5, s.getMaSach());
			pstm.executeUpdate();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public List<Sach> getSachByThuVien(int id){
		List<Sach> list = new ArrayList<Sach>();
		try {
			String sql = "select * from Sach where MaThuVien = ?";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Sach s = new Sach();
				ThuVienDAO dao = new ThuVienDAO();
				ThuVien tv = dao.getThuVienByID(rs.getInt(5));
				s.setMaSach(rs.getInt(1));
				s.setTenSach(rs.getString(2));
				s.setNgayXuatBan(rs.getDate(3));
				s.setXuatXu(rs.getString(4));
				s.setTv(tv);
				list.add(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
				pstm.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
