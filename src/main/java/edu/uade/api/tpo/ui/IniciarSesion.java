package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import edu.uade.api.tpo.model.SistemaUsuarios;

public class IniciarSesion {

	private JFrame frmIniciarSesion;
	private JTextField nombreUsuarioField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion window = new IniciarSesion();
					window.frmIniciarSesion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IniciarSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIniciarSesion = new JFrame();
		frmIniciarSesion.setTitle("Iniciar Sesion");
		frmIniciarSesion.setBounds(100, 100, 450, 300);
		frmIniciarSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIniciarSesion.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		nombreUsuarioField = new JTextField();
		passwordField = new JPasswordField();
		JButton btnAceptar = new JButton("Ingresar");
		JButton btnCancelar = new JButton("Cancelar");
		JLabel notification = new JLabel("");
		
		
		
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 450, 278);
		frmIniciarSesion.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setBounds(92, 58, 150, 14);
		panel.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setBounds(92, 101, 150, 14);
		panel.add(lblContrasea);
		
		
		nombreUsuarioField.setBounds(220, 55, 134, 20);
		nombreUsuarioField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				validateForm(btnAceptar, nombreUsuarioField.getText(), new String(passwordField.getPassword()));
			}
		});
		panel.add(nombreUsuarioField);
		nombreUsuarioField.setColumns(10);
		
		
		passwordField.setBounds(220, 98, 134, 20);
		passwordField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				validateForm(btnAceptar, nombreUsuarioField.getText(), new String(passwordField.getPassword()));
			}
		});
		panel.add(passwordField);
		
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptar.setEnabled(false);
				notification.setText("");
				
				// TODO: REMOVE THIS
				MenuPrincipal amenu = new MenuPrincipal();
				amenu.setVisible(true);
				frmIniciarSesion.dispose();
				
				boolean loginSuccess = login(nombreUsuarioField.getText(), new String(passwordField.getPassword())); 
				if (loginSuccess) {
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					frmIniciarSesion.dispose();
				} else {
					notification.setText("Usuario o Password no valido");
				}
				btnAceptar.setEnabled(true);
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(81, 168, 150, 23);
		panel.add(btnAceptar);
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notification.setText("");
				OpcionIngreso opcion = new OpcionIngreso();
				opcion.setVisible(true);
				frmIniciarSesion.dispose();
			}
		});
		btnCancelar.setBounds(252, 168, 150, 23);
		panel.add(btnCancelar);
		
		notification.setBounds(92, 220, 300, 14);
		panel.add(notification);
	}
	
	public void setVisible(boolean isVisible) {
		this.frmIniciarSesion.setVisible(isVisible);
	}
	
	public boolean login(String nombreUsuario, String password) {
		System.out.println("Trying to login with credentials: " + nombreUsuario);
		SistemaUsuarios su = SistemaUsuarios.getInstance();
		try {
			System.out.println(su.login(nombreUsuario, password).getNombre());
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void validateForm(JButton acceptButton, String nombreUsuario, String password) {
		if (nombreUsuario.length() > 0 && password.length() > 0) {
			acceptButton.setEnabled(true);
		} else {
			acceptButton.setEnabled(false);
		}
	}
}
