package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReputacionCalificaciones {

	private JFrame frmReputacionCalificaciones;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReputacionCalificaciones window = new ReputacionCalificaciones();
					window.frmReputacionCalificaciones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReputacionCalificaciones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReputacionCalificaciones = new JFrame();
		frmReputacionCalificaciones.setTitle("Reputacion / Calificaciones");
		frmReputacionCalificaciones.setBounds(100, 100, 450, 330);
		frmReputacionCalificaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReputacionCalificaciones.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 444, 308);
		frmReputacionCalificaciones.getContentPane().add(panel);
		
		JLabel lblReputacion = new JLabel("Reputacion:");
		lblReputacion.setBounds(71, 11, 93, 23);
		
		JLabel lblreputacion = new JLabel("(reputacion)");
		lblreputacion.setBounds(203, 13, 89, 19);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 70, 297, 145);
		
		JLabel lblCalificaciones = new JLabel("Calificaciones:");
		lblCalificaciones.setBounds(71, 41, 93, 23);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre usuario", "Calificacion"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(99);
		scrollPane.setViewportView(table);
		panel.setLayout(null);
		panel.add(lblReputacion);
		panel.add(lblreputacion);
		panel.add(scrollPane);
		panel.add(lblCalificaciones);
		
		JButton btnVolverAMenu = new JButton("Volver a menu principal");
		btnVolverAMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				frmReputacionCalificaciones.dispose();
			}
		});
		btnVolverAMenu.setBounds(115, 242, 177, 23);
		panel.add(btnVolverAMenu);
	}
	public void setVisible(boolean isVisible) {
		this.frmReputacionCalificaciones.setVisible(isVisible);
	}
}
