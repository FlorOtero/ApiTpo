package edu.uade.api.tpo.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.api.tpo.db.PersistenceModule;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.Domicilio;
import edu.uade.api.tpo.model.Password;
import edu.uade.api.tpo.model.SistemaUsuarios;
import edu.uade.api.tpo.model.Usuario;

public class RegistrarUsuario {
	private Domicilio domicilio;
	private JFrame frmRegistrarse;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersistenceModule.getInstance();
					RegistrarUsuario window = new RegistrarUsuario();
					window.frmRegistrarse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrarUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistrarse = new JFrame();
		frmRegistrarse.setTitle("Registrarse");
		frmRegistrarse.setBounds(100, 100, 502, 412);
		frmRegistrarse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setForeground(Color.BLACK);
		frmRegistrarse.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(91, 66, 61, 16);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(182, 61, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(91, 136, 61, 16);
		panel.add(lblDomicilio);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(91, 169, 61, 16);
		panel.add(lblEmail);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setBounds(38, 211, 132, 16);
		panel.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(75, 257, 94, 16);
		panel.add(lblContrasea);
		
		textField_2 = new JTextField();
		textField_2.setBounds(182, 164, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(182, 206, 130, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(182, 252, 130, 26);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user = new Usuario();
				Password password = new Password();
				user.setNombre(textField.getText());
				user.setApellido(textField_1.getText());
				user.setDomicilio(domicilio);
				user.setMail(textField_2.getText());
				password.setValor(textField_4.getText());
				password.setFechaModificacion(new Date());
				user.setPassword(password);
				user.setNombreUsuario(textField_3.getText());
				try {
					SistemaUsuarios.getInstance().altaUsuario(user);
					JOptionPane.showMessageDialog(null, "Se ha creado su usuario con exito", "Aviso", JOptionPane.PLAIN_MESSAGE);
					OpcionIngreso ingreso = new OpcionIngreso();
					ingreso.setVisible(true);
					frmRegistrarse.dispose();
				} catch(BusinessException | InvalidPasswordException e1 ) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Aviso", JOptionPane.PLAIN_MESSAGE);
					//TODO Manejar la exception y mostrar un mensaje de error cuando existe el usuario
				}
				
			}
		});
		btnRegistrarse.setBounds(232, 341, 117, 29);
		panel.add(btnRegistrarse);
		
		JButton btnCargarDomicilio = new JButton("Cargar domicilio");
		btnCargarDomicilio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domicilio= new Domicilio();
				CargarDomicilio domicilioUser = new CargarDomicilio(domicilio);
				domicilioUser.setVisible(true);
				
			}
		});
		btnCargarDomicilio.setBounds(178, 130, 134, 29);
		panel.add(btnCargarDomicilio);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(91, 108, 61, 16);
		panel.add(lblApellido);
		
		textField_1 = new JTextField();
		textField_1.setBounds(182, 99, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta= JOptionPane.showConfirmDialog(null,"Seguro que quiere salir?", "Aviso", JOptionPane.YES_NO_OPTION);
				if (JOptionPane.OK_OPTION == rta) {
					OpcionIngreso ingreso = new OpcionIngreso();
					ingreso.setVisible(true);
					frmRegistrarse.dispose();
				}
			}
		});
		btnCancelar.setBounds(379, 341, 117, 29);
		panel.add(btnCancelar);
	}
	public void setVisible(boolean isVisible) {
		this.frmRegistrarse.setVisible(isVisible);
	}
}
