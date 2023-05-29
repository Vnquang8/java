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
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BEAN.Sach;
import BEAN.ThuVien;
import Controller.EventControllerSach;
import Controller.EventControllerThuVien;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThuVienUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaThuVien;
	private JTextField txtTenThuVien;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThuVienUI frame = new ThuVienUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThuVienUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("M\u00E3 Th\u01B0 Vi\u1EC7n");
		lblNewLabel.setBounds(157, 28, 132, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn Th\u01B0 Vi\u1EC7n");
		lblNewLabel_1.setBounds(505, 28, 152, 20);
		contentPane.add(lblNewLabel_1);
		
		txtMaThuVien = new JTextField();
		txtMaThuVien.setBounds(80, 79, 272, 26);
		contentPane.add(txtMaThuVien);
		txtMaThuVien.setColumns(10);
		
		txtTenThuVien = new JTextField();
		txtTenThuVien.setBounds(428, 79, 261, 26);
		contentPane.add(txtTenThuVien);
		txtTenThuVien.setColumns(10);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaThuVien = Integer.parseInt(txtMaThuVien.getText());
				String TenThuVien = txtTenThuVien.getText();
				ThuVien tv = new ThuVien(MaThuVien, TenThuVien, null);
				EventControllerThuVien event = new EventControllerThuVien();
				Boolean check = event.ThemThuVien(tv);
				if(check) {
					ShowMess("Thêm Thành Công");
				}else {
					ShowMess("Thêm Thất Bại");
				}
				ResetData();
			}
		});
		btnThem.setBounds(15, 171, 115, 29);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xo\u00E1");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int MaThuVien = Integer.parseInt(txtMaThuVien.getText());
				
				EventControllerSach event = new EventControllerSach();
				if(event.getSachByThuVien(MaThuVien).size() > 0) {
					ShowMess("Có Sách Trong Thư Viện Không Thể Xoá");
					return;
				}
				
				EventControllerThuVien eventThuVien = new EventControllerThuVien();
				Boolean check = eventThuVien.XoaThuVien(MaThuVien);
				if(check) {
					ShowMess("Xoá Thành Công");
				}else {
					ShowMess("Xoá Thất Bại");
				}
				ResetData();
			}
		});
		btnXoa.setBounds(200, 171, 115, 29);
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThuVien tv = new ThuVien();
				tv.setMaThuVien(Integer.parseInt(txtMaThuVien.getText()));
				tv.setTenThuVien(txtTenThuVien.getText());
				EventControllerThuVien event = new EventControllerThuVien();
				Boolean check = event.SuaThuVien(tv);
				if(check) {
					ShowMess("Sửa Thành Công");
				}else {
					ShowMess("Sửa Thất Bại");
				}
				ResetData();
			}
		});
		btnSua.setBounds(384, 171, 115, 29);
		contentPane.add(btnSua);
		
		JButton btnHome = new JButton("Back Home");
		btnHome.setBounds(589, 171, 115, 29);
		contentPane.add(btnHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 242, 723, 129);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String MaThuVien = table.getValueAt(row, 0).toString();
				String TenThuVien = table.getValueAt(row, 1).toString();
				txtMaThuVien.setText(MaThuVien);
				txtTenThuVien.setText(TenThuVien);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Th\u01B0 Vi\u1EC7n", "T\u00EAn Th\u01B0 Vi\u1EC7n", "S\u1ED1 L\u01B0\u1EE3ng S\u00E1ch"
			}
		));
		scrollPane.setViewportView(table);
		ResetData();
	}
	
	public void ResetData() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		EventControllerThuVien event = new EventControllerThuVien();
		EventControllerSach eventSach = new EventControllerSach();
		List<ThuVien> list = event.getAllThuVien();
		for (ThuVien thuvien : list) {
			model.addRow(new Object[] {thuvien.getMaThuVien(), thuvien.getTenThuVien(), eventSach.getSachByThuVien(thuvien.getMaThuVien()).size()});
		}
		model.fireTableDataChanged();
	}
	
	public void ShowMess(String mess) {
		JOptionPane.showMessageDialog(this, mess);
	}

}
