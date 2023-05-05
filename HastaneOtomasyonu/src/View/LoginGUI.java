package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.BasHekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastatc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private JPasswordField fld_hastaPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("yapar.png")));
		lbl_logo.setBounds(190, 10, 95, 93);
		w_pane.add(lbl_logo);

		JTabbedPane w_tabbPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbPane.setBounds(10, 171, 466, 192);
		w_pane.add(w_tabbPane);

		JPanel w_hataLogin = new JPanel();
		w_hataLogin.setBackground(Color.WHITE);
		w_tabbPane.addTab("Hasta Girişi", null, w_hataLogin, null);
		w_hataLogin.setLayout(null);

		JLabel lbl_baslık_1 = new JLabel("T.C numaranız : ");
		lbl_baslık_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_baslık_1.setBounds(34, 24, 130, 21);
		w_hataLogin.add(lbl_baslık_1);

		JLabel lbl_baslık_1_1 = new JLabel("T.C Şifreniz :");
		lbl_baslık_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_baslık_1_1.setBounds(34, 67, 130, 21);
		w_hataLogin.add(lbl_baslık_1_1);

		fld_hastatc = new JTextField();
		fld_hastatc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_hastatc.setBounds(191, 25, 130, 19);
		w_hataLogin.add(fld_hastatc);
		fld_hastatc.setColumns(10);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
				Helper.showMsg("fill");
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_register.setBounds(34, 120, 100, 21);
		w_hataLogin.add(btn_register);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_hastaPass.setBounds(191, 71, 130, 19);
		w_hataLogin.add(fld_hastaPass);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_hastatc.getText().length()==0  && fld_hastaPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					
					boolean key =true;
					try {
						Connection con = conn.ConnDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						
						while (rs.next()) {
							if (fld_hastatc.getText().equals(rs.getString("tcno"))
									&& fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
								}
								key=false;
								
							}
						} 

						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("böyle bir kullanıcı bulunamadı ! Kayıt olunuz.");
					}
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_hastaLogin.setBounds(143, 120, 100, 21);
		w_hataLogin.add(btn_hastaLogin);

	

		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_tabbPane.addTab("Doktor Girişi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(196, 24, 130, 19);
		w_doktorLogin.add(fld_doctorTc);

		JLabel lbl_baslık_1_2 = new JLabel("T.C numaranız : ");
		lbl_baslık_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_baslık_1_2.setBounds(39, 23, 130, 21);
		w_doktorLogin.add(lbl_baslık_1_2);

		JLabel lbl_baslık_1_1_1 = new JLabel("T.C Şifreniz :");
		lbl_baslık_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_baslık_1_1_1.setBounds(39, 66, 130, 21);
		w_doktorLogin.add(lbl_baslık_1_1_1);

		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.ConnDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");

						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))
									&& fld_doctorPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("bashekim")) {
									BasHekim bhekim = new BasHekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
									
								}
								
							}
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_doctorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_doctorLogin.setBounds(39, 119, 209, 21);
		w_doktorLogin.add(btn_doctorLogin);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_doctorPass.setBounds(196, 70, 130, 19);
		w_doktorLogin.add(fld_doctorPass);

		JLabel lbl_baslık = new JLabel("Hastane Otomasyon Sistemine Hoşgeldiniz");
		lbl_baslık.setBounds(79, 113, 316, 21);
		w_pane.add(lbl_baslık);
		lbl_baslık.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
	}
}
