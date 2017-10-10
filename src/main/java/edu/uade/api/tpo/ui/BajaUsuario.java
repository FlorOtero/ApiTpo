package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.model.Password;
import edu.uade.api.tpo.model.SistemaUsuarios;
import edu.uade.api.tpo.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;

public class BajaUsuario {

	private JFrame frmBajaUsuario;
	private JTextField textField;
	private JTextField lblmostrarApellido;
	private JTextField lblmostrarNombre;
	private JTextField lblmostrarMail;
	private Usuario user;
	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);


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
		panel.setBounds(0, 0, 450, 278);
		frmBajaUsuario.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(204, 27, 113, 17);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNombreUsuario = new JLabel("Ingresar nombre de usuario:");
		lblNombreUsuario.setBounds(6, 29, 188, 15);
		panel.add(lblNombreUsuario);
		
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  { 
			user =	SistemaUsuarios.getInstance().buscarUsuario(textField.getText().toString());
			cargarUsuario();
				JOptionPane.showMessageDialog(null, "Se ha encontrado su usuario con exito", "Aviso", JOptionPane.PLAIN_MESSAGE);
		}
	});
		btnBuscar.setBounds(172, 65, 89, 23);
		panel.add(btnBuscar);
		


		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					SistemaUsuarios.getInstance().eliminarUsuario(user.getNombre());
					JOptionPane.showConfirmDialog(null,"Su usuario se ha eliminado con exito","Confirmacion",JOptionPane.PLAIN_MESSAGE);
					frmBajaUsuario.dispose();
				} catch(BusinessException e1) {
					//TODO Manejar la exception y mostrar un mensaje de error cuando existe el usuario
				}
			}
		});
		
		btnConfirmar.setBounds(105, 214, 89, 23);
		panel.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta= JOptionPane.showConfirmDialog(null,"Seguro que quiere salir?");
				if (JOptionPane.OK_OPTION == rta) {
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					frmBajaUsuario.dispose();
				}
			}
		});
		btnCancelar.setBounds(228, 214, 89, 23);
		panel.add(btnCancelar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(105, 109, 70, 14);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(105, 134, 85, 17);
		panel.add(lblApellido);
		
		JLabel lblMail = new JLabel("Email:");
		lblMail.setBounds(105, 162, 71, 17);
		panel.add(lblMail);
	
	
		 lblmostrarNombre = new JTextField();
		 lblmostrarNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				user.setNombre(lblmostrarNombre.getText());
			}
		});
		lblmostrarNombre.setBounds(217, 106, 100, 17);
		panel.add(lblmostrarNombre);
	
	
		lblmostrarApellido = new JTextField();
	   lblmostrarApellido.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
			user.setNombre(lblmostrarApellido.getText());
		}
	});
	lblmostrarApellido.setBounds(217, 134, 100, 17);
	panel.add(lblmostrarApellido);
	
	
	 lblmostrarMail = new JTextField();
	lblmostrarMail.addFocusListener(new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
			user.setNombre(lblmostrarMail.getText());
		}
	});
	lblmostrarMail.setBounds(217, 161, 89, 19);
	panel.add(lblmostrarMail);
	
	}
	public void setVisible(boolean isVisible) {
		this.frmBajaUsuario.setVisible(isVisible);
	}
	public void cargarUsuario(){		
		user= SistemaUsuarios.getInstance().buscarUsuario(textField.getText().toString());
		lblmostrarNombre.setText(user.getNombre());
		lblmostrarApellido.setText(user.getApellido());
		lblmostrarMail.setText(user.getMail());

	}
}

