package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private static Clinic clinic = new Clinic();
	private JTable tbl_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData;
	private JTable tbl_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable tbl_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doctor";
		colAppoint[2] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for (int i = 0; i < appoint.getHastaList(hasta.getId()).size(); i++) {
			appointData[0] = appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}

		setResizable(false);
		setTitle("Hasta Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın : " + hasta.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 290, 26);
		w_pane.add(lblNewLabel);

		JButton btn_exit = new JButton("Çıkış Yap");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_exit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_exit.setBounds(594, 10, 132, 26);
		w_pane.add(btn_exit);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_tab.setBounds(10, 72, 716, 381);
		w_pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Al", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 39, 265, 296);
		w_appointment.add(w_scrollDoctor);

		tbl_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(tbl_doctor);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 10, 115, 21);
		w_appointment.add(lblNewLabel_1);

		JLabel lbl_clinicName = new JLabel("Poliklinik Adı");
		lbl_clinicName.setForeground(Color.BLACK);
		lbl_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_clinicName.setBounds(285, 39, 142, 27);
		w_appointment.add(lbl_clinicName);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(285, 70, 142, 33);
		select_clinic.addItem("-- Polikinik Seç--");
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			select_clinic
					.addItem(new Item(clinic.getClinicList().get(i).getId(), clinic.getClinicList().get(i).getName()));

		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					// System.out.println(item.getKey() + " - " + item.getValue() );
					DefaultTableModel clearModel = (DefaultTableModel) tbl_doctor.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					DefaultTableModel clearModel = (DefaultTableModel) tbl_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}

		});
		w_appointment.add(select_clinic);

		JLabel lbl_clinicName_1 = new JLabel("Doktor Seç");
		lbl_clinicName_1.setForeground(Color.BLACK);
		lbl_clinicName_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_clinicName_1.setBounds(285, 138, 142, 27);
		w_appointment.add(lbl_clinicName_1);

		JButton btn_selDoctor = new JButton("Seç");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl_doctor.getSelectedRow();
				if (row >= 0) {
					String value = tbl_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tbl_whour.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					tbl_whour.setModel(whourModel);
					selectDoctorID = id;
					selectDoctorName = tbl_doctor.getModel().getValueAt(row, 1).toString();
				} else {
					Helper.showMsg("Lütfen bir doktor seçiniz !");
				}
			}
		});
		btn_selDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_selDoctor.setBounds(285, 169, 142, 33);
		w_appointment.add(btn_selDoctor);

		JLabel lbl_whour = new JLabel("Randevu Saatleri");
		lbl_whour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lbl_whour.setBounds(436, 10, 115, 21);
		w_appointment.add(lbl_whour);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(437, 39, 265, 296);
		w_appointment.add(w_scrollWhour);

		tbl_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(tbl_whour);

		JLabel lbl_clinicName_1_1 = new JLabel("Randevu");
		lbl_clinicName_1_1.setForeground(Color.BLACK);
		lbl_clinicName_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_clinicName_1_1.setBounds(285, 239, 142, 27);
		w_appointment.add(lbl_clinicName_1_1);

		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_whour.getSelectedRow();
				if (selRow >= 0) {
					String date = tbl_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(), selectDoctorName,
								hasta.getName(), date);
						if (control) {
							Helper.showMsg("success");
							hasta.updateWhourStatuc(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen geçerli bir tarih seçiniz");
				}
			}
		});
		btn_addAppoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_addAppoint.setBounds(285, 270, 142, 33);
		w_appointment.add(btn_addAppoint);

		JPanel w_appoint = new JPanel();
		w_appoint.setBackground(Color.WHITE);
		w_tab.addTab("Randevularım", null, w_appoint, null);
		w_appoint.setLayout(null);

		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 10, 691, 325);
		w_appoint.add(w_scrollAppoint);

		tbl_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(tbl_appoint);
		tbl_whour.getColumnModel().getColumn(0).setPreferredWidth(5);

	}

	public void updateWhourModel(int doctorID) {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_whour.getModel();
		clearModel.setRowCount(0);
		try {
			for (int i = 0; i < whour.getWhourList(doctorID).size(); i++) {
				whourData[0] = whour.getWhourList(doctorID).get(i).getWdate();
				whourModel.addRow(whourData);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateAppointModel(int hastaId) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_appoint.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < appoint.getHastaList(hastaId).size(); i++) {
			appointData[0] = appoint.getHastaList(hastaId).get(i).getId();
			appointData[1] = appoint.getHastaList(hastaId).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hastaId).get(i).getAppDate();
			appointModel.addRow(appointData);

		}
	}
}
