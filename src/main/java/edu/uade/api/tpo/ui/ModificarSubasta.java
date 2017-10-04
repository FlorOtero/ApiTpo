package edu.uade.api.tpo.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarSubasta {
	/**
	 * Launch the application.
	 */
	private JFrame frmModificarSubasta;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarSubasta window = new ModificarSubasta();
					window.frmModificarSubasta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificarSubasta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarSubasta = new JFrame();
		frmModificarSubasta.getContentPane().setBackground(new Color(255, 255, 224));
		frmModificarSubasta.setTitle("Modificar subasta");
		frmModificarSubasta.setBounds(100, 100, 548, 488);
		frmModificarSubasta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarSubasta.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Buscar Subasta");
		lblNombre.setBounds(34, 53, 130, 16);
		frmModificarSubasta.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 93, 88, 16);
		frmModificarSubasta.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio minimo");
		lblPrecio.setBounds(34, 134, 107, 16);
		frmModificarSubasta.getContentPane().add(lblPrecio);
		
		JLabel lblFechaDePublicacion = new JLabel("Fecha de publicacion");
		lblFechaDePublicacion.setBounds(34, 217, 158, 16);
		frmModificarSubasta.getContentPane().add(lblFechaDePublicacion);
		
		JLabel lblFechaHasta = new JLabel("Dias de vigencia");
		lblFechaHasta.setBounds(34, 245, 136, 16);
		frmModificarSubasta.getContentPane().add(lblFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 287, 136, 16);
		frmModificarSubasta.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 88, 130, 26);
		frmModificarSubasta.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 129, 130, 26);
		frmModificarSubasta.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(223, 300, 128, 23);
		frmModificarSubasta.getContentPane().add(chckbxEfectivo);
		
		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(221, 335, 142, 23);
		frmModificarSubasta.getContentPane().add(chckbxTarjetaDeCredito);
		
		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(221, 370, 197, 23);
		frmModificarSubasta.getContentPane().add(chckbxTransferenciaBancaria);
		
		JButton btnPublicar = new JButton("Modificar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Se ha modificado con exito");
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarSubasta.dispose();
				
			}
		});
		btnPublicar.setBounds(127, 431, 117, 29);
		frmModificarSubasta.getContentPane().add(btnPublicar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarSubasta.dispose();
			}
		});
		btnCancelar.setBounds(301, 431, 117, 29);
		frmModificarSubasta.getContentPane().add(btnCancelar);
		
		textField_3 = new JTextField();
		textField_3.setBounds(221, 240, 130, 26);
		frmModificarSubasta.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblPrecioInicial = new JLabel("Precio inicial");
		lblPrecioInicial.setBounds(34, 178, 107, 16);
		frmModificarSubasta.getContentPane().add(lblPrecioInicial);
		
		textField_4 = new JTextField();
		textField_4.setBounds(221, 167, 130, 26);
		frmModificarSubasta.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"aca las subastas"}));
		comboBox.setBounds(222, 49, 129, 27);
		frmModificarSubasta.getContentPane().add(comboBox);
	}
	public void setVisible(boolean isVisible) {
		this.frmModificarSubasta.setVisible(isVisible);
	}

}
