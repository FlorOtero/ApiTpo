package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;

public class BajaUsuario {

	private JFrame frmBajaUsuario;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaUsuario window = new BajaUsuario();
					window.frmBajaUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BajaUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBajaUsuario = new JFrame();
		frmBajaUsuario.setTitle("Baja Usuario");
		frmBajaUsuario.setBounds(100, 100, 450, 300);
		frmBajaUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBajaUsuario.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 434, 261);
		frmBajaUsuario.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(204, 27, 113, 17);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombreUsuario = new JLabel("Ingresar nombre de usuario:");
		lblNombreUsuario.setBounds(48, 29, 146, 15);
		panel.add(lblNombreUsuario);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(172, 65, 89, 23);
		panel.add(btnBuscar);
		
		JLabel lblMail = new JLabel("E-Mail:");
		lblMail.setBounds(129, 162, 47, 17);
		panel.add(lblMail);
		
		JLabel lblmostrarMail = new JLabel("(mostrar apellido)");
		lblmostrarMail.setBounds(217, 134, 100, 17);
		panel.add(lblmostrarMail);
		
		JLabel lblDireccion = new JLabel("Apellido:");
		lblDireccion.setBounds(129, 134, 61, 17);
		panel.add(lblDireccion);
		
		JLabel lblmostrarDireccion = new JLabel("(mostrar email)");
		lblmostrarDireccion.setBounds(217, 161, 89, 19);
		panel.add(lblmostrarDireccion);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(105, 214, 89, 23);
		panel.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(228, 214, 89, 23);
		panel.add(btnCancelar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(129, 109, 46, 14);
		panel.add(lblNombre);
		
		JLabel lblmostrarNombre = new JLabel("(mostrar nombre)");
		lblmostrarNombre.setBounds(217, 106, 100, 17);
		panel.add(lblmostrarNombre);
	}
}
