package edu.uade.api.tpo.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.uade.api.tpo.model.Domicilio;

public class ModificarDomicilio {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Domicilio domicilio;
	
	public ModificarDomicilio(Domicilio domicilio) {
		this();
		this.domicilio = domicilio;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarDomicilio window = new ModificarDomicilio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificarDomicilio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 224));
		frame.getContentPane().setLayout(null);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(52, 65, 61, 16);
		frame.getContentPane().add(lblCalle);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domicilio.setlinea1(textField.getText());
			}
		});
		textField.setBounds(166, 60, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(domicilio.getlinea1());
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domicilio.setlinea2(textField_1.getText());
			}
		});
		textField_1.setBounds(166, 94, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(domicilio.getlinea2());
		
		JLabel lblNewLabel = new JLabel("Codigo postal");
		lblNewLabel.setBounds(52, 164, 88, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domicilio.setCp(textField_2.getText());
			}
		});
		textField_2.setBounds(166, 159, 130, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(domicilio.getCp());
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(52, 244, 61, 16);
		frame.getContentPane().add(lblCiudad);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(52, 291, 61, 16);
		frame.getContentPane().add(lblProvincia);
		
		textField_3 = new JTextField();
		textField_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domicilio.setCiudad(textField_3.getText());
			}
		});
		textField_3.setBounds(166, 239, 130, 26);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(domicilio.getCiudad());
		
		textField_4 = new JTextField();
		textField_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domicilio.setProvincia(textField_4.getText());
			}
		});
		textField_4.setBounds(166, 286, 130, 26);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(domicilio.getProvincia());
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnGuardar.setBounds(85, 362, 117, 29);
		frame.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancelar.setBounds(252, 362, 117, 29);
		frame.getContentPane().add(btnCancelar);
		frame.setBounds(100, 100, 497, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setVisible(boolean isVisible) {
		this.frame.setVisible(isVisible);
	}
}
