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

public class NuevoProducto {

	private JFrame frmNuevoProducto;
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
					NuevoProducto window = new NuevoProducto();
					window.frmNuevoProducto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevoProducto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoProducto = new JFrame();
		frmNuevoProducto.getContentPane().setBackground(new Color(255, 255, 224));
		frmNuevoProducto.setTitle("Nuevo producto");
		frmNuevoProducto.setBounds(100, 100, 529, 434);
		frmNuevoProducto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoProducto.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 53, 61, 16);
		frmNuevoProducto.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 93, 88, 16);
		frmNuevoProducto.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(34, 134, 61, 16);
		frmNuevoProducto.getContentPane().add(lblPrecio);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(34, 184, 88, 16);
		frmNuevoProducto.getContentPane().add(lblFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 256, 136, 16);
		frmNuevoProducto.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(221, 48, 130, 26);
		frmNuevoProducto.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 88, 130, 26);
		frmNuevoProducto.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 129, 130, 26);
		frmNuevoProducto.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(221, 252, 128, 23);
		frmNuevoProducto.getContentPane().add(chckbxEfectivo);
		
		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(221, 283, 142, 23);
		frmNuevoProducto.getContentPane().add(chckbxTarjetaDeCredito);
		
		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(221, 311, 197, 23);
		frmNuevoProducto.getContentPane().add(chckbxTransferenciaBancaria);
		
	
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Se ha publicado con exito");
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmNuevoProducto.dispose();
			}
		});
		btnPublicar.setBounds(123, 377, 117, 29);
		frmNuevoProducto.getContentPane().add(btnPublicar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPublicacion nuevaP = new NuevaPublicacion();
				nuevaP.setVisible(true);
				frmNuevoProducto.dispose();
			}
		});
		btnCancelar.setBounds(293, 377, 117, 29);
		frmNuevoProducto.getContentPane().add(btnCancelar);
	}
	public void setVisible(boolean isVisible) {
		this.frmNuevoProducto.setVisible(isVisible);
	}
}
