package edu.uade.api.tpo.ui2;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.ExpiredPasswordException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.ui.IniciarSesion;
import edu.uade.api.tpo.ui2.custom.VPasswordField;
import edu.uade.api.tpo.ui2.custom.VTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ingresar {

	private static final Logger logger = LoggerFactory.getLogger(IniciarSesion.class);
	private JFrame frmIngresarApi;
	private VTextField txtNombreUsuario;
	private VPasswordField txtContrasena;
	private JButton btnIngresar;

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
		frmIngresarApi.setBounds(100, 100, 320, 260);
		frmIngresarApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIngresarApi.getContentPane().setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(10, 20, 300, 16);
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		frmIngresarApi.getContentPane().add(lblNombreDeUsuario);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(160, 142, 150, 30);
		frmIngresarApi.getContentPane().add(btnIngresar);
		
		txtNombreUsuario = new VTextField(btnIngresar);
		txtNombreUsuario.setBounds(10, 40, 300, 30);
		frmIngresarApi.getContentPane().add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		JLabel txtNombreDeUsuario = new JLabel("Contraseña");
		txtNombreDeUsuario.setBounds(10, 80, 300, 16);
		frmIngresarApi.getContentPane().add(txtNombreDeUsuario);
		
		txtContrasena = new VPasswordField(btnIngresar);
		txtContrasena.setBounds(10, 100, 300, 30);
		frmIngresarApi.getContentPane().add(txtContrasena);
		
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIngresar.setEnabled(false);
				if (login()) {
					Inicio inicio = new Inicio();
					inicio.setVisible(true);
					frmIngresarApi.dispose();
				}
				frmIngresarApi.setEnabled(true);
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 180, 320, 12);
		frmIngresarApi.getContentPane().add(separator);
		
		JLabel lblnoEstasRegistrado = new JLabel("¿No estás registrado?");
		lblnoEstasRegistrado.setBounds(10, 200, 140, 30);
		frmIngresarApi.getContentPane().add(lblnoEstasRegistrado);
		
		JButton btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.setBounds(160, 200, 150, 30);
		frmIngresarApi.getContentPane().add(btnCrearCuenta);
		
		btnCrearCuenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Registrarse registro = new Registrarse();
				registro.setVisible(true);
				frmIngresarApi.dispose();
			}
		});
		
	}
	
	public boolean login() {
		String nombreUsuario = txtNombreUsuario.getText();
		String password = new String(txtContrasena.getPassword());
		SistemaUsuarios su = SistemaUsuarios.getInstance();
		System.out.println("Trying to login with credentials: " + nombreUsuario);
		
		try {
			su.login(nombreUsuario, password);
			return true;
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
			btnIngresar.setEnabled(true);
			return false;
		} catch (ExpiredPasswordException e) {
			String newPassword= JOptionPane.showInputDialog(null, e.getMessage() + " Ingrese nueva contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
			e.getUsuario().getPassword().setValor(newPassword);
			try{
				su.getInstance().modificarUsuario(e.getUsuario());	
				JOptionPane.showConfirmDialog(null, "Su contraseña se ha modificado con exito", "Aviso", JOptionPane.PLAIN_MESSAGE);
			}catch (BusinessException | InvalidPasswordException e3){
				logger.error("Error modificando la contraseña del usuario", e3);
				JOptionPane.showMessageDialog(null, e3.getMessage(), "Aviso", JOptionPane.PLAIN_MESSAGE);
			}
			return false;
		} 
		
	}
	
	public void validateForm() {
		String nombreUsuario = txtNombreUsuario.getText();
		String password = new String(txtContrasena.getPassword());
		
		if (nombreUsuario.length() > 0 && password.length() > 0) {
			this.btnIngresar.setEnabled(true);
		} else {
			this.btnIngresar.setEnabled(false);
		}
	}
	
	public void setVisible(boolean isVisible) {
		this.frmIngresarApi.setVisible(isVisible);
	}
}
