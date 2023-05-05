package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;
import java.util.Iterator;

import Model.BasHekim;
import Model.Clinic;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
//import java.awt.event.MouseAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Helper.*;

public class BashekimGUI extends JFrame {

	Clinic clinic = new Clinic();
	static BasHekim bashekim = new BasHekim();
	private JPanel w_pane;
	private JTextField fld_doctorName;
	private JTextField fld_doctorTcno;
	private JTextField fld_doctorPass;
	private JTextField fld_doctorId;
	private JTable tbl_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable tbl_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable tbl_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(BasHekim bashekim) throws SQLException {
		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "Tc no";
		colDoctorName[3] = "Şifre";

		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();

			doctorModel.addRow(doctorData);

		}
		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colClinicName[0] = "ID";
		colClinicName[1] = "Poliklinik";

		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();

			clinicModel.addRow(clinicData);

		}

		// Worker Model
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın :	" + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 290, 26);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnNewButton.setBounds(594, 10, 132, 26);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_tab.setBounds(10, 72, 716, 381);
		w_pane.add(w_tab);

		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel lbl_doctorAd = new JLabel("Ad Soyad");
		lbl_doctorAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_doctorAd.setForeground(Color.BLACK);
		lbl_doctorAd.setBounds(523, 31, 159, 22);
		w_doctor.add(lbl_doctorAd);

		fld_doctorName = new JTextField();
		fld_doctorName.setBounds(521, 63, 161, 19);
		w_doctor.add(fld_doctorName);
		fld_doctorName.setColumns(10);

		JLabel lbl_doctorTc = new JLabel("T.C. No");
		lbl_doctorTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_doctorTc.setBounds(523, 92, 159, 22);
		w_doctor.add(lbl_doctorTc);

		fld_doctorTcno = new JTextField();
		fld_doctorTcno.setBounds(523, 124, 159, 19);
		w_doctor.add(fld_doctorTcno);
		fld_doctorTcno.setColumns(10);

		JLabel lbl_doctorPass = new JLabel("Şifre");
		lbl_doctorPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_doctorPass.setBounds(523, 153, 159, 22);
		w_doctor.add(lbl_doctorPass);

		fld_doctorPass = new JTextField();
		fld_doctorPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		fld_doctorPass.setBounds(523, 185, 159, 21);
		w_doctor.add(fld_doctorPass);
		fld_doctorPass.setColumns(10);
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorName.getText().length() == 0 || fld_doctorTcno.getText().length() == 0
						|| fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");

				} else {
					try {
						boolean control = bashekim.addDoctor(fld_doctorTcno.getText(), fld_doctorPass.getText(),
								fld_doctorName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_doctorTcno.setText(null);
							fld_doctorPass.setText(null);
							fld_doctorName.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_addDoctor.setBounds(523, 216, 159, 21);
		w_doctor.add(btn_addDoctor);

		JLabel lbl_KullanıcıID = new JLabel("Kullanıcı ID");
		lbl_KullanıcıID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_KullanıcıID.setBounds(523, 244, 159, 22);
		w_doctor.add(lbl_KullanıcıID);

		fld_doctorId = new JTextField();
		fld_doctorId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		fld_doctorId.setBounds(523, 273, 159, 22);
		w_doctor.add(fld_doctorId);
		fld_doctorId.setColumns(10);

		JButton btn_doctorDelete = new JButton("Sİl");
		btn_doctorDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorId.getText().length() == 0) {
					Helper.showMsg("Lütfen bir doktor seçiniz.");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorId.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_doctorId.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_doctorDelete.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_doctorDelete.setBounds(523, 305, 156, 21);
		w_doctor.add(btn_doctorDelete);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 10, 491, 334);
		w_doctor.add(w_scrollDoctor);

		tbl_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(tbl_doctor);
		tbl_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorId.setText(tbl_doctor.getValueAt(tbl_doctor.getSelectedRow(), 0).toString());

				} catch (Exception ex) {

				}
			}
		});

		tbl_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectedID = Integer.parseInt(tbl_doctor.getValueAt(tbl_doctor.getSelectedRow(), 0).toString());
					String selectedName = tbl_doctor.getValueAt(tbl_doctor.getSelectedRow(), 1).toString();
					String selectedTcno = tbl_doctor.getValueAt(tbl_doctor.getSelectedRow(), 2).toString();
					String selectedPass = tbl_doctor.getValueAt(tbl_doctor.getSelectedRow(), 3).toString();
					try {
						boolean control = bashekim.updateDoctor(selectedID, selectedTcno, selectedPass, selectedName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 10, 257, 325);
		w_clinic.add(w_scrollClinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(tbl_clinic.getValueAt(tbl_clinic.getSelectedRow(), 0).toString());
				Clinic selectedClinic = clinic.getFetch(selectedID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectedClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});
			}

		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(tbl_clinic.getValueAt(tbl_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();

						} else {
							Helper.showMsg("error");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}

		});

		tbl_clinic = new JTable(clinicModel);
		tbl_clinic.setComponentPopupMenu(clinicMenu);
		tbl_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = tbl_clinic.rowAtPoint(point);
				tbl_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		w_scrollClinic.setViewportView(tbl_clinic);

		JLabel lbl_clinicName = new JLabel("Poliklinik Adı");
		lbl_clinicName.setBounds(277, 26, 151, 27);
		lbl_clinicName.setForeground(Color.BLACK);
		lbl_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_clinic.add(lbl_clinicName);

		fld_clinicName = new JTextField();
		fld_clinicName.setBounds(277, 55, 153, 27);
		fld_clinicName.setColumns(10);
		w_clinic.add(fld_clinicName);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.setBounds(277, 87, 152, 27);
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		w_clinic.add(btn_addClinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(438, 10, 263, 325);
		w_clinic.add(w_scrollWorker);

		tbl_worker = new JTable();
		w_scrollWorker.setViewportView(tbl_worker);

		JComboBox select_list = new JComboBox();
		select_list.setBounds(277, 264, 153, 27);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			select_list.addItem(new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		select_list.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(select_list);

		JButton btn_addWorker =  new JButton("Ekle");
		btn_addWorker.setBounds(276, 301, 152, 27);
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = tbl_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_list.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) tbl_worker.getModel();
							clearModel.setRowCount(0);

							for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}


						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		w_clinic.add(btn_addWorker);

		JLabel lbl_clinicName_1 = new JLabel("Poliklinik Adı");
		lbl_clinicName_1.setForeground(Color.BLACK);
		lbl_clinicName_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbl_clinicName_1.setBounds(277, 148, 151, 27);
		w_clinic.add(lbl_clinicName_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = tbl_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) tbl_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tbl_worker.setModel(workerModel);
				} else {
					Helper.showMsg("Lütfen Bir Poliklinik Seçiniz !");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_workerSelect.setBounds(277, 185, 152, 27);
		w_clinic.add(btn_workerSelect);
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();

			doctorModel.addRow(doctorData);

		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_clinic.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();

			clinicModel.addRow(clinicData);

		}
	}
}
