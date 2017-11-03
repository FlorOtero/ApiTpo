package edu.uade.api.tpo.ui;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.ExpiredPasswordException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.Domicilio;
import edu.uade.api.tpo.model.Password;
import edu.uade.api.tpo.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;

public class Registrarse {

	private JFrame frmRegistrarseApi;
	private Domicilio domicilio;
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
					Registrarse window = new Registrarse();
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
	public Registrarse() {
		this.domicilio = new Domicilio();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistrarseApi = new JFrame();
		frmRegistrarseApi.setTitle("Registrarse | API");
		frmRegistrarseApi.setBounds(100, 100, 320, 520);
		frmRegistrarseApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistrarseApi.getContentPane().setLayout(null);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(10, 20, 300, 16);
		frmRegistrarseApi.getContentPane().add(lblNombreDeUsuario);

		txtNombreDeUsuario = new JTextField();
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
		btnIngresarDomicilio.setBounds(10, 400, 300, 30);
		frmRegistrarseApi.getContentPane().add(btnIngresarDomicilio);

		/*
		 * TODO Se tiene que clickear una sola vez y luego habilitarlo si cerro la otra
		 * ventana
		 */
		btnIngresarDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// btnIngresarDomicilio.setEnabled(false);
				IngresarDomicilio domicilioUser = new IngresarDomicilio(domicilio);
				domicilioUser.setVisible(true);
			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(190, 460, 120, 30);
		frmRegistrarseApi.getContentPane().add(btnAceptar);

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Usuario user = new Usuario();
					user.setNombre(txtNombre.getText());
					user.setApellido(txtApellido.getText());
					user.setDomicilio(domicilio);
					user.setMail(txtEmail.getText());
					user.setNombreUsuario(txtNombreDeUsuario.getText());

					if (Arrays.equals(txtContrasena.getPassword(), txtConfirmarContrasena.getPassword())) {
						Password pwd = new Password();
						pwd.setFechaModificacion(new Date());
						pwd.setValor(new String(txtContrasena.getPassword()));
						user.setPassword(pwd);
					} else {
						throw new BusinessException("Las contraseñas no coinciden. Verifique, por favor.");
					}

					SistemaUsuarios.getInstance().altaUsuario(user);
					JOptionPane.showMessageDialog(null, "Se ha creado su usuario con exito", "Aviso",
							JOptionPane.PLAIN_MESSAGE);
					/**
					 * Tratamos de loguear automaticamente al usuario
					 */
					try {
						SistemaUsuarios su = SistemaUsuarios.getInstance();
						System.out.println("Trying to login with credentials: " + user.getNombreUsuario());
						su.login(user.getNombreUsuario(), new String(txtContrasena.getPassword()));
						Inicio inicio = new Inicio();
						inicio.setVisible(true);

					} catch (Exception loginException) {
						/**
						 * Si no se pudo loguear lo mandamos a iniciar sesion
						 */
						JOptionPane.showMessageDialog(null, loginException.getMessage(), "Aviso",
								JOptionPane.ERROR_MESSAGE);
						Ingresar ingreso = new Ingresar();
						ingreso.setVisible(true);
						
					}
					
					frmRegistrarseApi.dispose();

				} catch (BusinessException | InvalidPasswordException e1) {
					// TODO Manejar la exception y mostrar un mensaje de error cuando existe el
					// usuario
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(70, 460, 120, 30);
		frmRegistrarseApi.getContentPane().add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ingresar ingreso = new Ingresar();
				ingreso.setVisible(true);
				frmRegistrarseApi.dispose();
			}
		});
	}

	public void setVisible(boolean isVisible) {
		this.frmRegistrarseApi.setVisible(isVisible);
	}
}
