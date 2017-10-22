package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IngresarDomicilio {

	private JFrame frmIngresarDomicilio;
	private JTextField txtCalleNumero;
	private JTextField txtPisoDepto;
	private JTextField txtCodigoPostal;
	private JTextField txtCiudad;
	private JTextField txtProvincia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngresarDomicilio window = new IngresarDomicilio();
					window.frmIngresarDomicilio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IngresarDomicilio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIngresarDomicilio = new JFrame();
		frmIngresarDomicilio.setTitle("Ingresar Domicilio | API");
		frmIngresarDomicilio.setBounds(100, 100, 320, 340);
		frmIngresarDomicilio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIngresarDomicilio.getContentPane().setLayout(null);
		
		JLabel lblCalleNumero = new JLabel("Calle y número");
		lblCalleNumero.setBounds(10, 20, 300, 16);
		frmIngresarDomicilio.getContentPane().add(lblCalleNumero);
		
		txtCalleNumero = new JTextField();
		txtCalleNumero.setBounds(10, 40, 300, 30);
		frmIngresarDomicilio.getContentPane().add(txtCalleNumero);
		txtCalleNumero.setColumns(10);
		
		JLabel lblPisoDepto = new JLabel("Piso/Departamento");
		lblPisoDepto.setBounds(10, 80, 145, 16);
		frmIngresarDomicilio.getContentPane().add(lblPisoDepto);
		
		txtPisoDepto = new JTextField();
		txtPisoDepto.setBounds(10, 100, 145, 30);
		frmIngresarDomicilio.getContentPane().add(txtPisoDepto);
		txtPisoDepto.setColumns(10);
		
		JLabel lblCodigoPostal = new JLabel("Código Postal");
		lblCodigoPostal.setBounds(165, 80, 145, 16);
		frmIngresarDomicilio.getContentPane().add(lblCodigoPostal);
		
		txtCodigoPostal = new JTextField();
		txtCodigoPostal.setBounds(165, 100, 145, 30);
		frmIngresarDomicilio.getContentPane().add(txtCodigoPostal);
		txtCodigoPostal.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(10, 140, 300, 16);
		frmIngresarDomicilio.getContentPane().add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setBounds(10, 160, 300, 30);
		frmIngresarDomicilio.getContentPane().add(txtCiudad);
		txtCiudad.setColumns(10);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 200, 300, 16);
		frmIngresarDomicilio.getContentPane().add(lblProvincia);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(10, 220, 300, 30);
		frmIngresarDomicilio.getContentPane().add(txtProvincia);
		txtProvincia.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(190, 280, 120, 30);
		frmIngresarDomicilio.getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(70, 280, 120, 30);
		frmIngresarDomicilio.getContentPane().add(btnCancelar);
	}

}
