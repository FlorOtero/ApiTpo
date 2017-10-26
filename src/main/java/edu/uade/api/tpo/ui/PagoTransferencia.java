package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.uade.api.tpo.model.DatosPago;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PagoTransferencia {

	private JFrame frmPagoConTransferencia;
	private JTextField textField;
	private DatosPago datosPago;
	private float monto;



	/**
	 * Create the application.
	 */
	public PagoTransferencia(DatosPago dp, float monto) {
		this.datosPago= dp;
		this.monto= monto;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPagoConTransferencia = new JFrame();
		frmPagoConTransferencia.setTitle("Pago con Transferencia");
		frmPagoConTransferencia.setBounds(100, 100, 450, 300);
		frmPagoConTransferencia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPagoConTransferencia.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 450, 278);
		frmPagoConTransferencia.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setBounds(50, 64, 46, 14);
		panel.add(lblMonto);
		
		JButton btnConfirmarTransaccion = new JButton("Confirmar transaccion");
		btnConfirmarTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPagoConTransferencia.dispose();
			}
		});
		btnConfirmarTransaccion.setBounds(50, 213, 174, 28);
		panel.add(btnConfirmarTransaccion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(268, 216, 89, 23);
		panel.add(btnCancelar);
		
		JLabel lblNumeroDeCuenta = new JLabel("Numero de cuenta:");
		lblNumeroDeCuenta.setBounds(50, 98, 129, 16);
		panel.add(lblNumeroDeCuenta);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				datosPago.setNumeroCuenta(textField.getText());
			}
		});
		textField.setBounds(180, 93, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(180, 63, 61, 16);
		panel.add(lblNewLabel);	
	}
	
	public void setVisible(boolean isVisible) {
		this.frmPagoConTransferencia.setVisible(isVisible);
	}
}
