package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;



public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcNo;
	private JPasswordField fld_password;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_name = new JLabel("Ad Soyad");
		lbl_name.setForeground(Color.BLACK);
		lbl_name.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_name.setBounds(12, 10, 264, 25);
		w_pane.add(lbl_name);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 39, 266, 25);
		w_pane.add(fld_name);
		
		JLabel lbl_tcNo = new JLabel("Tc No");
		lbl_tcNo.setForeground(Color.BLACK);
		lbl_tcNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_tcNo.setBounds(14, 79, 264, 25);
		w_pane.add(lbl_tcNo);
		
		fld_tcNo = new JTextField();
		fld_tcNo.setColumns(10);
		fld_tcNo.setBounds(12, 108, 264, 25);
		w_pane.add(fld_tcNo);
		
		JLabel lbl_password = new JLabel("Şifre");
		lbl_password.setForeground(Color.BLACK);
		lbl_password.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_password.setBounds(12, 140, 264, 25);
		w_pane.add(lbl_password);
		
		fld_password = new JPasswordField();
		fld_password.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		fld_password.setBounds(12, 172, 264, 25);
		w_pane.add(fld_password);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcNo.getText().length()==0 || fld_password.getText().length()==0||fld_name.getText().length()==0) {
					
					Helper.showMsg("fill");
				}else {
					try {
						boolean control= hasta.register(fld_tcNo.getText(),fld_password.getText(), fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_register.setBounds(10, 220, 264, 36);
		w_pane.add(btn_register);
		
		JButton btn_backTo = new JButton("Geri Dön");
		btn_backTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_backTo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_backTo.setBounds(12, 267, 264, 36);
		w_pane.add(btn_backTo);
	}
}
