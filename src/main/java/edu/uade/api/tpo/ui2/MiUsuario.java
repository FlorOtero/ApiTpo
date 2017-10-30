package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.Password;
import edu.uade.api.tpo.model.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class MiUsuario {
	private Usuario user = SistemaUsuarios.getUsuarioLoggeado();
	private JFrame frmRegistrarseApi;
	private JTextField txtNombreDeUsuario;
	private JPasswordField txtContrasena;
	private JPasswordField txtConfirmarContrasena;
	private JTextField txtEmail;
	private JTextField txtNombre;
	private JTextField txtApellido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiUsuario window = new MiUsuario();
					window.frmRegistrarseApi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiUsuario() {
		initialize();
		mostrarDatosUsuario();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistrarseApi = new JFrame();
		frmRegistrarseApi.setTitle("Mi Usuario | API");
		frmRegistrarseApi.setBounds(100, 100, 320, 600);
		frmRegistrarseApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistrarseApi.getContentPane().setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(10, 20, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblNombreDeUsuario);
		
		txtNombreDeUsuario = new JTextField();
		txtNombreDeUsuario.setText(user.getNombreUsuario());
		txtNombreDeUsuario.setEditable(false);
		txtNombreDeUsuario.setEnabled(false);
		txtNombreDeUsuario.setBounds(10, 40, 300, 30);
		frmRegistrarseApi.getContentPane().add(txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(10, 80, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblContrasena);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(10, 100, 300, 30);
		frmRegistrarseApi.getContentPane().add(txtContrasena);
		
		JLabel lblConfirmarContrasena = new JLabel("Confirmar contraseña");
		lblConfirmarContrasena.setBounds(10, 140, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblConfirmarContrasena);
		
		txtConfirmarContrasena = new JPasswordField();
		txtConfirmarContrasena.setBounds(10, 160, 300, 30);
		frmRegistrarseApi.getContentPane().add(txtConfirmarContrasena);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 200, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("usuario@example.com");
		txtEmail.setBounds(10, 220, 300, 30);
		frmRegistrarseApi.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 260, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 280, 300, 30);
		frmRegistrarseApi.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 320, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(10, 340, 300, 30);
		frmRegistrarseApi.getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 380, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblDomicilio);
		
		JButton btnIngresarDomicilio = new JButton("Ingresar Domicilio");
		btnIngresarDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarDomicilio ingresarDomicilio = new IngresarDomicilio();
				ingresarDomicilio.setVisible(true);
			}
		});
		btnIngresarDomicilio.setBounds(10, 400, 300, 30);
		frmRegistrarseApi.getContentPane().add(btnIngresarDomicilio);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(190, 460, 120, 30);
		frmRegistrarseApi.getContentPane().add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					user.setNombre(txtNombre.getText());
					user.setApellido(txtApellido.getText());
					user.setMail(txtEmail.getText());
					Password pw = new Password();	
					pw.setValor(new String(txtContrasena.getPassword()));
					user.setPassword(pw);
				//	System.out.println(user.getApellido());
				//	System.out.println(user.getPassword().getValor());
					SistemaUsuarios.getInstance().modificarUsuario(user);
					JOptionPane.showConfirmDialog(null,"Su usuario se ha modificado con exito","Confirmacion",JOptionPane.PLAIN_MESSAGE);
					Inicio inicio = new Inicio();
					inicio.setVisible(true);
					frmRegistrarseApi.dispose();
					
				} catch(BusinessException | InvalidPasswordException e1) {
					//TODO Manejar la exception y mostrar un mensaje de error cuando existe el usuario
				}
			}
		});
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(70, 460, 120, 30);
		frmRegistrarseApi.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmRegistrarseApi.dispose();
			}
		});
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 500, 320, 12);
		frmRegistrarseApi.getContentPane().add(separator);
		
		JLabel lblCerrarCuenta = new JLabel("¿Deseas cerrar tu cuenta?");
		lblCerrarCuenta.setBounds(10, 520, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblCerrarCuenta);
		
		JButton btnCerrarCuentaDe = new JButton("Cerrar cuenta de usuario");
		btnCerrarCuentaDe.setBounds(10, 540, 300, 30);
		btnCerrarCuentaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SistemaUsuarios.getInstance().eliminarUsuario(user.getNombre());
					JOptionPane.showConfirmDialog(null,"Su usuario se ha eliminado con exito","Confirmacion",JOptionPane.PLAIN_MESSAGE);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frmRegistrarseApi.getContentPane().add(btnCerrarCuentaDe);
	}
	
	private void mostrarDatosUsuario(){
		Password password = user.getPassword();					
		txtContrasena.setText(password.getValor());
		txtEmail.setText(user.getMail());
		txtNombre.setText(user.getNombre());
		txtApellido.setText(user.getApellido());
	}
	
}
