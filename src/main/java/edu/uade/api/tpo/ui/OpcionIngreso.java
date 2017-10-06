package edu.uade.api.tpo.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpcionIngreso {

	private JFrame OpcionIngreso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpcionIngreso window = new OpcionIngreso();
					window.OpcionIngreso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpcionIngreso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		OpcionIngreso = new JFrame();
		OpcionIngreso.setTitle("Opciones de Ingreso");
		OpcionIngreso.setBounds(100, 100, 450, 300);
		OpcionIngreso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OpcionIngreso.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 450, 278);
		OpcionIngreso.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUsuario nuevoUsuario = new RegistrarUsuario();
				nuevoUsuario.setVisible(true);
				OpcionIngreso.dispose();
			}
		});
		btnRegistrarse.setBounds(138, 56, 130, 44);
		panel.add(btnRegistrarse);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesion iniciar = new IniciarSesion();
				iniciar.setVisible(true);
				OpcionIngreso.dispose();				
			}
		});
		btnIniciarSesion.setBounds(138, 136, 130, 44);
		panel.add(btnIniciarSesion);
	}
	public void setVisible(boolean isVisible) {
		this.OpcionIngreso.setVisible(isVisible);
	}
}
