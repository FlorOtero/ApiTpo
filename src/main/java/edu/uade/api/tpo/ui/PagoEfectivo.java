package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagoEfectivo {

	private JFrame frmPagoEnEfectivo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagoEfectivo window = new PagoEfectivo();
					window.frmPagoEnEfectivo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PagoEfectivo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPagoEnEfectivo = new JFrame();
		frmPagoEnEfectivo.getContentPane().setBackground(new Color(255, 255, 224));
		frmPagoEnEfectivo.setTitle("Pago en Efectivo");
		frmPagoEnEfectivo.setBounds(100, 100, 450, 300);
		frmPagoEnEfectivo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPagoEnEfectivo.getContentPane().setLayout(null);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setBounds(122, 36, 46, 14);
		frmPagoEnEfectivo.getContentPane().add(lblMonto);
		
		textField = new JTextField();
		textField.setBounds(200, 33, 86, 20);
		frmPagoEnEfectivo.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirmarTransaccion = new JButton("Confirmar transaccion");
		btnConfirmarTransaccion.setBounds(122, 84, 185, 23);
		frmPagoEnEfectivo.getContentPane().add(btnConfirmarTransaccion);
		
		JLabel lblNewLabel = new JLabel("Numero de referencia para pago:");
		lblNewLabel.setBounds(140, 118, 189, 34);
		frmPagoEnEfectivo.getContentPane().add(lblNewLabel);
		
		JLabel lblnumeroRandomPara = new JLabel("(Numero random para hacer el pago)");
		lblnumeroRandomPara.setBounds(140, 149, 189, 41);
		frmPagoEnEfectivo.getContentPane().add(lblnumeroRandomPara);
		
		JButton btnVolverAMenu = new JButton("Volver a menu principal");
		btnVolverAMenu.setBounds(45, 210, 143, 23);
		frmPagoEnEfectivo.getContentPane().add(btnVolverAMenu);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(287, 210, 80, 23);
		frmPagoEnEfectivo.getContentPane().add(btnCancelar);
	}

}
