package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ModificarProducto {

	private JFrame frmModificarProducto;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarProducto window = new ModificarProducto();
					window.frmModificarProducto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificarProducto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarProducto = new JFrame();
		frmModificarProducto.getContentPane().setBackground(new Color(255, 255, 224));
		frmModificarProducto.setTitle("Modificar producto");
		frmModificarProducto.setBounds(100, 100, 529, 434);
		frmModificarProducto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarProducto.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Buscar Producto");
		lblNombre.setBounds(34, 53, 117, 16);
		frmModificarProducto.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 93, 88, 16);
		frmModificarProducto.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(34, 134, 61, 16);
		frmModificarProducto.getContentPane().add(lblPrecio);
		
		JLabel lblFechaDePublicacion = new JLabel("Fecha de publicacion");
		lblFechaDePublicacion.setBounds(34, 181, 158, 16);
		frmModificarProducto.getContentPane().add(lblFechaDePublicacion);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(34, 215, 88, 16);
		frmModificarProducto.getContentPane().add(lblFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 256, 136, 16);
		frmModificarProducto.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 88, 130, 26);
		frmModificarProducto.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 129, 130, 26);
		frmModificarProducto.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(221, 252, 128, 23);
		frmModificarProducto.getContentPane().add(chckbxEfectivo);
		
		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(221, 283, 142, 23);
		frmModificarProducto.getContentPane().add(chckbxTarjetaDeCredito);
		
		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(221, 311, 197, 23);
		frmModificarProducto.getContentPane().add(chckbxTransferenciaBancaria);
		
	
		
		JButton btnPublicar = new JButton("Modificar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Se ha modificado con exito");
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarProducto.dispose();
			}
		});
		btnPublicar.setBounds(123, 377, 117, 29);
		frmModificarProducto.getContentPane().add(btnPublicar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarProducto.dispose();
			}
		});
		btnCancelar.setBounds(293, 377, 117, 29);
		frmModificarProducto.getContentPane().add(btnCancelar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"aca todos los productos"}));
		comboBox.setBounds(226, 49, 125, 27);
		frmModificarProducto.getContentPane().add(comboBox);
	}
}
