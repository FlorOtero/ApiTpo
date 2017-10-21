package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

public class Ingresar {

	private JFrame frmIngresarApi;
	private JTextField txtNombreUsuario;
	private JPasswordField txtContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingresar window = new Ingresar();
					window.frmIngresarApi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ingresar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIngresarApi = new JFrame();
		frmIngresarApi.setTitle("Ingresar | API");
		frmIngresarApi.setBounds(100, 100, 320, 280);
		frmIngresarApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIngresarApi.getContentPane().setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(10, 20, 300, 16);
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		frmIngresarApi.getContentPane().add(lblNombreDeUsuario);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(10, 40, 300, 30);
		frmIngresarApi.getContentPane().add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		JLabel txtNombreDeUsuario = new JLabel("Contraseña");
		txtNombreDeUsuario.setBounds(10, 80, 300, 16);
		frmIngresarApi.getContentPane().add(txtNombreDeUsuario);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(10, 100, 300, 30);
		frmIngresarApi.getContentPane().add(txtContrasena);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(160, 142, 150, 30);
		frmIngresarApi.getContentPane().add(btnIngresar);
		
		JLabel lblnoEstasRegistrado = new JLabel("¿No estás registrado?");
		lblnoEstasRegistrado.setBounds(10, 218, 140, 16);
		frmIngresarApi.getContentPane().add(lblnoEstasRegistrado);
		
		JButton btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.setBounds(160, 212, 150, 30);
		frmIngresarApi.getContentPane().add(btnCrearCuenta);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 190, 320, 12);
		frmIngresarApi.getContentPane().add(separator);
	}
}
