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

public class ModificacionServicio {

	private JFrame frmModificarServicio;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificacionServicio window = new ModificacionServicio();
					window.frmModificarServicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificacionServicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarServicio = new JFrame();
		frmModificarServicio.getContentPane().setBackground(new Color(255, 255, 224));
		frmModificarServicio.setTitle("Modificar servicio");
		frmModificarServicio.setBounds(100, 100, 544, 565);
		frmModificarServicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarServicio.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Buscar Servicio");
		lblNombre.setBounds(34, 53, 136, 16);
		frmModificarServicio.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 93, 88, 16);
		frmModificarServicio.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(34, 134, 61, 16);
		frmModificarServicio.getContentPane().add(lblPrecio);
		
		JLabel lblFechaDePublicacion = new JLabel("Fecha de publicacion");
		lblFechaDePublicacion.setBounds(34, 181, 158, 16);
		frmModificarServicio.getContentPane().add(lblFechaDePublicacion);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(34, 215, 88, 16);
		frmModificarServicio.getContentPane().add(lblFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 256, 136, 16);
		frmModificarServicio.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 88, 130, 26);
		frmModificarServicio.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 129, 130, 26);
		frmModificarServicio.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(221, 252, 128, 23);
		frmModificarServicio.getContentPane().add(chckbxEfectivo);
		
		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(221, 283, 142, 23);
		frmModificarServicio.getContentPane().add(chckbxTarjetaDeCredito);
		
		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(221, 311, 197, 23);
		frmModificarServicio.getContentPane().add(chckbxTransferenciaBancaria);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Se ha modificado con exito");
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarServicio.dispose();
			}
		});
		btnModificar.setBounds(109, 508, 117, 29);
		frmModificarServicio.getContentPane().add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(313, 508, 117, 29);
		frmModificarServicio.getContentPane().add(btnCancelar);
		
		JLabel lblTipoDeContratacion = new JLabel("Tipo de contratacion");
		lblTipoDeContratacion.setBounds(34, 379, 136, 16);
		frmModificarServicio.getContentPane().add(lblTipoDeContratacion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Abono", "Unica vez"}));
		comboBox.setBounds(221, 374, 142, 29);
		frmModificarServicio.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"servicios que tiene publicado"}));
		comboBox_1.setBounds(221, 49, 130, 27);
		frmModificarServicio.getContentPane().add(comboBox_1);
	}

	public JFrame getFrmModificarServicio() {
		return frmModificarServicio;
	}

	public void setFrmModificarServicio(JFrame frmModificarServicio) {
		this.frmModificarServicio = frmModificarServicio;
	}
	public void setVisible(boolean isVisible) {
		this.frmModificarServicio.setVisible(isVisible);
	}
}

