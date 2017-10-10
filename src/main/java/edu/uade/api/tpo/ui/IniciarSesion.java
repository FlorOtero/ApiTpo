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
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;

import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.ExpiredPasswordException;
import edu.uade.api.tpo.model.SistemaUsuarios;

public class IniciarSesion {

	private JFrame frmIniciarSesion;
	private JTextField nombreUsuarioField;
	private JPasswordField passwordField;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel notification;
	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);

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
		btnAceptar = new JButton("Ingresar");
		btnCancelar = new JButton("Cancelar");
		notification = new JLabel("");
		
		
		
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
				validateForm();
			}
		});
		panel.add(nombreUsuarioField);
		nombreUsuarioField.setColumns(10);
		
		
		passwordField.setBounds(220, 98, 134, 20);
		passwordField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				validateForm();
			}
		});
		panel.add(passwordField);
		
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAceptar.setEnabled(false);
				notification.setText("");
				
				if (login()) {
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					frmIniciarSesion.dispose();
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
	
	public boolean login() {
		String nombreUsuario = nombreUsuarioField.getText();
		String password = new String(passwordField.getPassword());
		SistemaUsuarios su = SistemaUsuarios.getInstance();
		System.out.println("Trying to login with credentials: " + nombreUsuario);

		try {
			su.login(nombreUsuario, password).getNombre();
			prefs.put("USERNAME", nombreUsuario);
			return true;
		} catch (BusinessException e) {
			notification.setText(e.getMessage());
			return false;
		} catch (ExpiredPasswordException e) {
			notification.setText(e.getMessage());
			return false;
		} 
		
	}
	
	public void validateForm() {
		String nombreUsuario = nombreUsuarioField.getText();
		String password = new String(passwordField.getPassword());
		
		if (nombreUsuario.length() > 0 && password.length() > 0) {
			this.btnAceptar.setEnabled(true);
		} else {
			this.btnAceptar.setEnabled(false);
		}
	}
}
