package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import java.awt.Color;

public class ModificarUsuario {

	private JFrame frmModificarUsuario;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario window = new ModificarUsuario();
					window.frmModificarUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificarUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarUsuario = new JFrame();
		frmModificarUsuario.setTitle("Modificar Usuario");
		frmModificarUsuario.setBounds(100, 100, 518, 338);
		frmModificarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarUsuario.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 502, 299);
		frmModificarUsuario.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblIngresarNombreDe = new JLabel("Ingresar nombre de usuario:");
		lblIngresarNombreDe.setBounds(65, 22, 139, 20);
		panel.add(lblIngresarNombreDe);
		
		textField = new JTextField();
		textField.setBounds(235, 22, 139, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(206, 53, 89, 23);
		panel.add(btnBuscar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(147, 87, 46, 14);
		panel.add(lblNombre);
		
		JEditorPane dtrpnmostrarNombre = new JEditorPane();
		dtrpnmostrarNombre.setText("(mostrar nombre)");
		dtrpnmostrarNombre.setBounds(246, 87, 128, 20);
		panel.add(dtrpnmostrarNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(147, 120, 46, 14);
		panel.add(lblApellido);
		
		JEditorPane dtrpnmostrarApellido = new JEditorPane();
		dtrpnmostrarApellido.setText("(mostrar apellido)");
		dtrpnmostrarApellido.setBounds(246, 120, 128, 20);
		panel.add(dtrpnmostrarApellido);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(147, 149, 46, 14);
		panel.add(lblDomicilio);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(147, 180, 46, 14);
		panel.add(lblEmail);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(147, 211, 76, 14);
		panel.add(lblContrasea);
		
		JEditorPane dtrpnmostrarDomicilio = new JEditorPane();
		dtrpnmostrarDomicilio.setText("(mostrar domicilio)");
		dtrpnmostrarDomicilio.setBounds(246, 149, 128, 20);
		panel.add(dtrpnmostrarDomicilio);
		
		JEditorPane dtrpnmostrarEmail = new JEditorPane();
		dtrpnmostrarEmail.setText("(mostrar email)");
		dtrpnmostrarEmail.setBounds(246, 180, 128, 20);
		panel.add(dtrpnmostrarEmail);
		
		JEditorPane dtrpnmostrarContrasea = new JEditorPane();
		dtrpnmostrarContrasea.setText("(mostrar contrase\u00F1a)");
		dtrpnmostrarContrasea.setBounds(246, 211, 128, 20);
		panel.add(dtrpnmostrarContrasea);
		
		JButton btnConfirmarModificacion = new JButton("Confirmar Modificacion");
		btnConfirmarModificacion.setBounds(82, 252, 154, 23);
		panel.add(btnConfirmarModificacion);
		
		JButton btnAceptar = new JButton("Cancelar");
		btnAceptar.setBounds(308, 252, 89, 23);
		panel.add(btnAceptar);
	}
}
