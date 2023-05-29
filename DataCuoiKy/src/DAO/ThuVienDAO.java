package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.ThuVien;
import DAL.DBContext;

public class ThuVienDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	public ThuVien getThuVienByID(int id) {
		ThuVien tv = new ThuVien();
		try {
			String sql = "select * from ThuVien where MaThuVien = ?";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				tv.setMaThuVien(rs.getInt(1));
				tv.setTenThuVien(rs.getString(2));
			}
			conn.close();
			pstm.close();
			rs.close();
		} catch (Exception e) {
			return null;
		}
		return tv;
	}
	
	public List<ThuVien> getAllThuVien(){
		List<ThuVien> list = new ArrayList<ThuVien>();
		try {
			String sql = "select * from ThuVien";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				ThuVien tv = new ThuVien();
				tv.setMaThuVien(rs.getInt(1));
				tv.setTenThuVien(rs.getString(2));
				list.add(tv);
			}
			conn.close();
			pstm.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public boolean ThemThuVien(ThuVien tv) {
		try {
			String sql = "insert into ThuVien values(?,?)";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, tv.getMaThuVien());
			pstm.setString(2, tv.getTenThuVien());
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
	
	public boolean SuaThuVien(ThuVien tv) {
		try {
			String sql = "update ThuVien set TenThuVien = ? where MaThuVien = ?";
			conn = new DBContext().getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tv.getTenThuVien());
			pstm.setInt(2, tv.getMaThuVien());
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
	
	public boolean XoaThuVien(int id) {
		try {
			String sql = "delete from ThuVien where MaThuVien = ?";
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
