package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

import BEAN.Sach;
import BEAN.ThuVien;
import Controller.EventControllerSach;
import Controller.EventControllerThuVien;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SachUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtNgayXuatBan;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SachUI frame = new SachUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SachUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 S\u00E1ch");
		lblNewLabel.setBounds(15, 16, 69, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn S\u00E1ch");
		lblNewLabel_1.setBounds(15, 80, 69, 20);
		contentPane.add(lblNewLabel_1);
		
		txtMaSach = new JTextField();
		txtMaSach.setBounds(15, 38, 252, 26);
		contentPane.add(txtMaSach);
		txtMaSach.setColumns(10);
		
		txtTenSach = new JTextField();
		txtTenSach.setBounds(15, 105, 252, 26);
		contentPane.add(txtTenSach);
		txtTenSach.setColumns(10);
		
		JRadioButton rdNN = new JRadioButton("Nước Ngoài");
		buttonGroup.add(rdNN);
		rdNN.setBounds(333, 16, 155, 29);
		contentPane.add(rdNN);
		rdNN.setSelected(true);
		
		JRadioButton rdVN = new JRadioButton("Việt Nam");
		buttonGroup.add(rdVN);
		rdVN.setBounds(333, 50, 155, 29);
		contentPane.add(rdVN);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(333, 105, 385, 26);
		contentPane.add(comboBox);
		EventControllerThuVien eventThuVien = new EventControllerThuVien();
		List<ThuVien> list = eventThuVien.getAllThuVien();
		for (ThuVien thuVien : list) {
			comboBox.addItem(thuVien.getMaThuVien());
		}
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MaSach = txtMaSach.getText();
				String TenSach = txtTenSach.getText();
				Date NgayXuatBan = convertStringToDate(txtNgayXuatBan.getText());
				String XuatXu = "Vietnamese";
				if(rdNN.isSelected()) {
					XuatXu = "foreign";
				}
				ThuVien tv = eventThuVien.getThuVienByID(Integer.parseInt(comboBox.getSelectedItem().toString()));
				Sach s = new Sach();
				s.setMaSach(Integer.parseInt(MaSach));
				s.setTenSach(TenSach);
				s.setNgayXuatBan(NgayXuatBan);
				s.setXuatXu(XuatXu);
				s.setTv(tv);
				EventControllerSach event = new EventControllerSach();
				Boolean check = event.ThemSach(s);
				if(check) {
					ShowMess("Thêm Thành Công");
				}else {
					ShowMess("Thêm Thất Bại");
				}
				ResetData();
			}
		});
		btnThem.setBounds(15, 164, 115, 29);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xo\u00E1");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaSach = Integer.parseInt(txtMaSach.getText());
				EventControllerSach event = new EventControllerSach();
				Boolean check = event.XoaSach(MaSach);
				if(check) {
					ShowMess("Xoá Thành Công");
				}else {
					ShowMess("Xoá Thất Bại");
				}
				ResetData();
			}
		});
		btnXoa.setBounds(152, 164, 115, 29);
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sach s = new Sach();
				s.setMaSach(Integer.parseInt(txtMaSach.getText()));
				s.setTenSach(txtTenSach.getText());
				s.setNgayXuatBan(convertStringToDate(txtNgayXuatBan.getText()));
				if(rdVN.isSelected()) {
					s.setXuatXu("Vietnamese");
				}else {
					s.setXuatXu("foreign");
				}
				
				// Nếu ComboBox được chọn trùng với MaThuVien thì sẽ gửi MaThuVien của TenThuVien đó lên cho SQL
				for (ThuVien thuVien : list) {
					if(comboBox.getSelectedItem().equals(thuVien.getMaThuVien())) {
						s.setTv(thuVien);
					}
				}
				EventControllerSach event = new EventControllerSach();
				Boolean check = event.SuaSach(s);
				if(check) {
					ShowMess("Sua Thanh Cong");
				}else {
					ShowMess("Sua Thất Bại");
				}
				ResetData();
			}
		});
		btnSua.setBounds(295, 164, 115, 29);
		contentPane.add(btnSua);
		
		JButton btnTim = new JButton("T\u00ECm Ki\u1EBFm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaThuVien = 0;
				for (ThuVien thuVien : list) {
					if(comboBox.getSelectedItem().equals(thuVien.getMaThuVien())) {
						MaThuVien = thuVien.getMaThuVien();
					}
				}
				ResetDataSearch(MaThuVien);
			}
		});
		btnTim.setBounds(449, 164, 115, 29);
		contentPane.add(btnTim);
		
		JButton btnHome = new JButton("Back Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeUI home = new HomeUI();
				home.setVisible(true);
				dispose();
			}
		});
		btnHome.setBounds(603, 164, 115, 29);
		contentPane.add(btnHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 221, 703, 131);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String MaSach = table.getValueAt(row, 0).toString();
				String TenSach = table.getValueAt(row, 1).toString();
				String NgayXuatBan = table.getValueAt(row, 3).toString();
				String XuatXu = table.getValueAt(row, 2).toString();
				String MaThuVien = table.getValueAt(row, 4).toString();
				
				txtMaSach.setText(MaSach);
				txtTenSach.setText(TenSach);
				txtNgayXuatBan.setText(NgayXuatBan);
				
				if(XuatXu.equals("Vietnamese")) {
					rdVN.setSelected(true);
				}else {
					rdNN.setSelected(true);
				}
				
				for (ThuVien thuVien : list) {
					if(MaThuVien.equals(thuVien.getTenThuVien())) {
						comboBox.setSelectedItem(thuVien.getMaThuVien());
					}
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch", "Xu\u1EA5t X\u1EE9", "Ng\u00E0y Xu\u1EA5t B\u1EA3n", "T\u00EAn Th\u01B0 Vi\u1EC7n"
			}
		));
		scrollPane.setViewportView(table);
		
		txtNgayXuatBan = new JTextField();
		txtNgayXuatBan.setBounds(486, 38, 232, 26);
		contentPane.add(txtNgayXuatBan);
		txtNgayXuatBan.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ng\u00E0y Xu\u1EA5t B\u1EA3n");
		lblNewLabel_2.setBounds(499, 16, 154, 20);
		contentPane.add(lblNewLabel_2);
		ResetData();
	}
	
	public void ResetData() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		EventControllerSach eventSach = new EventControllerSach();
		List<Sach> list = eventSach.getAllSach();
		for (Sach sach : list) {
			model.addRow(new Object[] {sach.getMaSach(), sach.getTenSach(),sach.getXuatXu() ,sach.getNgayXuatBan(), sach.getTv().getTenThuVien()});
		}
		model.fireTableDataChanged();
	}
	
	public void ResetDataSearch(int id) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		EventControllerSach eventSach = new EventControllerSach();
		List<Sach> list = eventSach.getSachByThuVien(id);
		for (Sach sach : list) {
			System.out.println(sach.toString());
			model.addRow(new Object[] {sach.getMaSach(), sach.getTenSach(),sach.getXuatXu() ,sach.getNgayXuatBan(), sach.getTv().getTenThuVien()});
		}
		model.fireTableDataChanged();
	}
	
	public Date convertStringToDate(String dateString) {
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date utilDate = dateFormat.parse(dateString);
            date = new Date(utilDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
	
	public void ShowMess(String mess) {
		JOptionPane.showMessageDialog(this, mess);
	}
}
