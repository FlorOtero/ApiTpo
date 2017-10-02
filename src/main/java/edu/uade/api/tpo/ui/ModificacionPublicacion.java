package edu.uade.api.tpo.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ModificacionPublicacion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificacionPublicacion frame = new ModificacionPublicacion();
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
	public ModificacionPublicacion() {
		setTitle("Modificar Publicación");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModificar = new JLabel("Modificar");
		lblModificar.setBounds(52, 6, 61, 16);
		contentPane.add(lblModificar);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Producto");
		rdbtnNewRadioButton.setBounds(39, 33, 141, 23);
		contentPane.add(rdbtnNewRadioButton);
	}
}
