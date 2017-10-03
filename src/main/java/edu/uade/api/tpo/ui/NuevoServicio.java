package edu.uade.api.tpo.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoServicio {

	private JFrame frmNuevoServicio;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoServicio window = new NuevoServicio();
					window.frmNuevoServicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevoServicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoServicio = new JFrame();
		frmNuevoServicio.getContentPane().setBackground(new Color(255, 255, 224));
		frmNuevoServicio.setTitle("Publicar servicio");
		frmNuevoServicio.setBounds(100, 100, 544, 565);
		frmNuevoServicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoServicio.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 53, 61, 16);
		frmNuevoServicio.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 93, 88, 16);
		frmNuevoServicio.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(34, 134, 61, 16);
		frmNuevoServicio.getContentPane().add(lblPrecio);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(34, 215, 88, 16);
		frmNuevoServicio.getContentPane().add(lblFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 256, 136, 16);
		frmNuevoServicio.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(221, 48, 130, 26);
		frmNuevoServicio.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 88, 130, 26);
		frmNuevoServicio.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 129, 130, 26);
		frmNuevoServicio.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(221, 252, 128, 23);
		frmNuevoServicio.getContentPane().add(chckbxEfectivo);
		
		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(221, 283, 142, 23);
		frmNuevoServicio.getContentPane().add(chckbxTarjetaDeCredito);
		
		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(221, 311, 197, 23);
		frmNuevoServicio.getContentPane().add(chckbxTransferenciaBancaria);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Se ha publicado con exito");
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmNuevoServicio.dispose();
			}
		});
		btnPublicar.setBounds(109, 508, 117, 29);
		frmNuevoServicio.getContentPane().add(btnPublicar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(313, 508, 117, 29);
		frmNuevoServicio.getContentPane().add(btnCancelar);
		
		JLabel lblTipoDeContratacion = new JLabel("Tipo de contratacion");
		lblTipoDeContratacion.setBounds(34, 379, 136, 16);
		frmNuevoServicio.getContentPane().add(lblTipoDeContratacion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Abono", "Unica vez"}));
		comboBox.setBounds(221, 374, 142, 29);
		frmNuevoServicio.getContentPane().add(comboBox);
	}
}

