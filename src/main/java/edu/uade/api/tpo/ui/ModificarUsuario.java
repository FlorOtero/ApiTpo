package edu.uade.api.tpo.ui;

import java.awt.EventQueue;
import java.awt.Window;

import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.Domicilio;
import edu.uade.api.tpo.model.Password;
import edu.uade.api.tpo.model.SistemaUsuarios;
import edu.uade.api.tpo.model.Usuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import edu.uade.api.tpo.exceptions.BusinessException;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.util.Date;
import java.util.prefs.Preferences;
import java.awt.event.InputMethodEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ModificarUsuario {

	private JFrame frmModificarUsuario;
	private JFrame frmBajaUsuario;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private Usuario user;
	private Domicilio domicilio;
	private Password password;
	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	
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
		cargarUsuario();
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
		JButton btnDomicilio = new JButton("Cambiar domicilio");
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 518, 316);
		frmModificarUsuario.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(111, 87, 82, 14);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(111, 120, 82, 14);
		panel.add(lblApellido);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(111, 149, 82, 14);
		panel.add(lblDomicilio);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(111, 180, 82, 14);
		panel.add(lblEmail);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(111, 211, 112, 14);
		panel.add(lblContrasea);
		
		JButton btnConfirmarModificacion = new JButton("Confirmar Modificacion");
		btnConfirmarModificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					SistemaUsuarios.getInstance().modificarUsuario(user);
					MenuPrincipal menu = new MenuPrincipal();
					JOptionPane.showConfirmDialog(null,"Su usuario se ha modificado con exito","Confirmacion",JOptionPane.PLAIN_MESSAGE);
					menu.setVisible(true);
					frmModificarUsuario.dispose();
				} catch(BusinessException | InvalidPasswordException e1) {
					//TODO Manejar la exception y mostrar un mensaje de error cuando existe el usuario
				}
			}
		});
		btnConfirmarModificacion.setBounds(43, 252, 180, 23);
		panel.add(btnConfirmarModificacion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				frmModificarUsuario.dispose();
			}
		});
		btnCancelar.setBounds(396, 252, 89, 23);
		panel.add(btnCancelar);
		
		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				user.setNombre(textField_1.getText());
			}
		});
		textField_1.setBounds(229, 81, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				user.setApellido(textField_2.getText());
			}
		});
		textField_2.setBounds(229, 114, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				user.setMail(textField_3.getText());
			}
		});
		textField_3.setBounds(229, 174, 130, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);
		btnDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domicilio= user.getDomicilio();
				ModificarDomicilio cambiarDomicilio = new ModificarDomicilio(domicilio);
				cambiarDomicilio.setVisible(true);
				user.setDomicilio(domicilio);
			}
		});
		btnDomicilio.setBounds(229, 143, 139, 29);
		panel.add(btnDomicilio);
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				password.setValor(passwordField.toString());
				password.setFechaModificacion(new Date());
				user.setPassword(password);
			}
		});
		passwordField.setBounds(229, 205, 124, 26);
		panel.add(passwordField);
		
		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					SistemaUsuarios.getInstance().eliminarUsuario(user.getNombre());
					JOptionPane.showConfirmDialog(null,"Su usuario se ha eliminado con exito","Confirmacion",JOptionPane.PLAIN_MESSAGE);
					OpcionIngreso window = new OpcionIngreso();
					window.OpcionIngreso.setVisible(true);
					frmBajaUsuario.dispose();
				} catch(BusinessException e1) {
					//TODO Manejar la exception y mostrar un mensaje de error cuando existe el usuario
				}
			}
		});
		btnEliminarUsuario.setBounds(235, 250, 153, 26);
		panel.add(btnEliminarUsuario);
	}
	public void setVisible(boolean isVisible) {
		this.frmModificarUsuario.setVisible(isVisible);
	}
	public void cargarUsuario(){
		String nombreUsuario = prefs.get("USERNAME", null);				
		user= SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);
		textField_1.setText(user.getNombre());
		textField_2.setText(user.getApellido());
		textField_3.setText(user.getMail());
		password=user.getPassword();					
		passwordField.setText(password.getValor());
	}

}
