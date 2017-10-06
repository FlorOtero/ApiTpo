package edu.uade.api.tpo.ui;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuevaSubasta {
	/**
	 * Launch the application.
	 */
	private JFrame frmNuevaSubasta;
	private JTextField textField;
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
					NuevaSubasta window = new NuevaSubasta();
					window.frmNuevaSubasta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevaSubasta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevaSubasta = new JFrame();
		frmNuevaSubasta.getContentPane().setBackground(new Color(255, 255, 224));
		frmNuevaSubasta.setTitle("Publicar subasta");
		frmNuevaSubasta.setBounds(100, 100, 548, 488);
		frmNuevaSubasta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevaSubasta.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 53, 61, 16);
		frmNuevaSubasta.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 93, 88, 16);
		frmNuevaSubasta.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio minimo");
		lblPrecio.setBounds(34, 134, 107, 16);
		frmNuevaSubasta.getContentPane().add(lblPrecio);
		
		JLabel lblFechaHasta = new JLabel("Dias de vigencia");
		lblFechaHasta.setBounds(34, 245, 136, 16);
		frmNuevaSubasta.getContentPane().add(lblFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 287, 136, 16);
		frmNuevaSubasta.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(221, 48, 130, 26);
		frmNuevaSubasta.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 88, 130, 26);
		frmNuevaSubasta.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 129, 130, 26);
		frmNuevaSubasta.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(223, 300, 128, 23);
		frmNuevaSubasta.getContentPane().add(chckbxEfectivo);
		
		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(221, 335, 142, 23);
		frmNuevaSubasta.getContentPane().add(chckbxTarjetaDeCredito);
		
		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(221, 370, 197, 23);
		frmNuevaSubasta.getContentPane().add(chckbxTransferenciaBancaria);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Se ha publicado con exito");
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmNuevaSubasta.dispose();
				
			}
		});
		btnPublicar.setBounds(127, 431, 117, 29);
		frmNuevaSubasta.getContentPane().add(btnPublicar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPublicacion nuevaP = new NuevaPublicacion();
				nuevaP.setVisible(true);
				frmNuevaSubasta.dispose();				
			}
		});
		btnCancelar.setBounds(301, 431, 117, 29);
		frmNuevaSubasta.getContentPane().add(btnCancelar);
		
		textField_3 = new JTextField();
		textField_3.setBounds(221, 240, 130, 26);
		frmNuevaSubasta.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblPrecioInicial = new JLabel("Precio inicial");
		lblPrecioInicial.setBounds(34, 178, 107, 16);
		frmNuevaSubasta.getContentPane().add(lblPrecioInicial);
		
		
		textField_4 = new JTextField();
		textField_4.setBounds(221, 167, 130, 26);
		frmNuevaSubasta.getContentPane().add(textField_4);
		textField_4.setColumns(10);
	}
	public void setVisible(boolean isVisible) {
		this.frmNuevaSubasta.setVisible(isVisible);
	}
}
