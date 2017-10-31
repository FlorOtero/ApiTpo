package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.Domicilio;
import edu.uade.api.tpo.model.Password;
import edu.uade.api.tpo.model.Usuario;
import edu.uade.api.tpo.ui.MenuPrincipal;
import edu.uade.api.tpo.ui2.custom.VPasswordField;
import edu.uade.api.tpo.ui2.custom.VTextField;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class MiUsuario {

	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	private static final Logger logger = LoggerFactory.getLogger(MiCuentaCorriente.class);
	private Usuario user;
	private Domicilio domicilio;
	private JFrame frmMiUsuarioApi;
	private VTextField txtNombreDeUsuario;
	private VPasswordField txtContrasena;
	private VPasswordField txtConfirmarContrasena;
	private VTextField txtEmail;
	private VTextField txtNombre;
	private VTextField txtApellido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiUsuario window = new MiUsuario();
					window.frmMiUsuarioApi.setVisible(true);
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
		loadUser();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMiUsuarioApi = new JFrame();
		frmMiUsuarioApi.setTitle("Mi Usuario | API");
		frmMiUsuarioApi.setBounds(100, 100, 320, 600);
		frmMiUsuarioApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMiUsuarioApi.getContentPane().setLayout(null);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setBounds(10, 20, 300, 16);
		frmMiUsuarioApi.getContentPane().add(lblNombreDeUsuario);

		txtNombreDeUsuario = new VTextField();
		txtNombreDeUsuario.setText(user.getNombreUsuario()); // NO OLVIDAR SETEAR EL NOMBRE DE USUARIO!!!
		txtNombreDeUsuario.setEditable(false);
		txtNombreDeUsuario.setEnabled(false);
		txtNombreDeUsuario.setBounds(10, 40, 300, 30);
		frmMiUsuarioApi.getContentPane().add(txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(10);

		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(10, 80, 300, 16);
		frmMiUsuarioApi.getContentPane().add(lblContrasena);

		txtContrasena = new VPasswordField();
		txtContrasena.setBounds(10, 100, 300, 30);
		frmMiUsuarioApi.getContentPane().add(txtContrasena);

		JLabel lblConfirmarContrasena = new JLabel("Confirmar contraseña");
		lblConfirmarContrasena.setBounds(10, 140, 300, 16);
		frmMiUsuarioApi.getContentPane().add(lblConfirmarContrasena);

		txtConfirmarContrasena = new VPasswordField();
		txtConfirmarContrasena.setBounds(10, 160, 300, 30);
		frmMiUsuarioApi.getContentPane().add(txtConfirmarContrasena);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 200, 300, 16);
		frmMiUsuarioApi.getContentPane().add(lblEmail);

		txtEmail = new VTextField();
		txtEmail.setText(user.getMail());
		txtEmail.setToolTipText("usuario@example.com");
		txtEmail.setBounds(10, 220, 300, 30);
		frmMiUsuarioApi.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 260, 300, 16);
		frmMiUsuarioApi.getContentPane().add(lblNombre);

		txtNombre = new VTextField();
		txtNombre.setText(user.getNombre());
		txtNombre.setBounds(10, 280, 300, 30);
		frmMiUsuarioApi.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 320, 300, 16);
		frmMiUsuarioApi.getContentPane().add(lblApellido);

		txtApellido = new VTextField();
		txtApellido.setText(user.getApellido());
		txtApellido.setBounds(10, 340, 300, 30);
		frmMiUsuarioApi.getContentPane().add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 380, 300, 16);
		frmMiUsuarioApi.getContentPane().add(lblDomicilio);

		JButton btnIngresarDomicilio = new JButton("Ingresar Domicilio");
		btnIngresarDomicilio.setBounds(10, 400, 300, 30);
		frmMiUsuarioApi.getContentPane().add(btnIngresarDomicilio);

		/*
		 * TODO Se tiene que clickear una sola vez y luego habilitarlo si cerro la otra
		 * ventana
		 */
		btnIngresarDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngresarDomicilio domicilioUser = new IngresarDomicilio(domicilio);
				domicilioUser.setVisible(true);
			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(190, 460, 120, 30);
		frmMiUsuarioApi.getContentPane().add(btnAceptar);

		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtContrasena.getPassword().toString().length() > 0) {
						if (Arrays.equals(txtContrasena.getPassword(), txtConfirmarContrasena.getPassword())) {
							Password pwd = new Password();
							pwd.setFechaModificacion(new Date());
							pwd.setValor(new String(txtContrasena.getPassword()));
							user.setPassword(pwd);
						} else {
							throw new BusinessException("Las contraseñas no coinciden. Verifique, por favor.");
						}
					}
					user.setMail(txtEmail.getText());
					user.setNombre(txtNombre.getText());
					user.setApellido(txtApellido.getText());
					user.setDomicilio(domicilio);

					SistemaUsuarios.getInstance().modificarUsuario(user);
					Inicio inicio = new Inicio();
					JOptionPane.showConfirmDialog(null, "Su usuario se ha modificado con exito", "Confirmacion", JOptionPane.PLAIN_MESSAGE);
					inicio.setVisible(true);
					frmMiUsuarioApi.dispose();
				} catch (BusinessException | InvalidPasswordException e1) {
					// TODO Manejar la exception y mostrar un mensaje de error cuando existe el
					// usuario
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					logger.error(e1.getMessage());
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(70, 460, 120, 30);
		frmMiUsuarioApi.getContentPane().add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmMiUsuarioApi.dispose();
			}
		});

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 500, 320, 12);
		frmMiUsuarioApi.getContentPane().add(separator);

		JLabel lblCerrarCuenta = new JLabel("¿Deseas cerrar tu cuenta?");
		lblCerrarCuenta.setBounds(10, 520, 300, 16);
		frmMiUsuarioApi.getContentPane().add(lblCerrarCuenta);

		JButton btnCerrarCuentaDe = new JButton("Cerrar cuenta de usuario");
		btnCerrarCuentaDe.setBounds(10, 540, 300, 30);
		frmMiUsuarioApi.getContentPane().add(btnCerrarCuentaDe);
	}

	public void setVisible(boolean isVisible) {
		this.frmMiUsuarioApi.setVisible(isVisible);
	}

	private void loadUser() {
		String nombreUsuario = prefs.get("USERNAME", null);
		user = SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);
		domicilio = user.getDomicilio();
	}
}
