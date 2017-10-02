package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PagoTransferencia {

	private JFrame frmPagoConTransferencia;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagoTransferencia window = new PagoTransferencia();
					window.frmPagoConTransferencia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PagoTransferencia() {
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
		panel.setBounds(0, 0, 434, 261);
		frmPagoConTransferencia.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuarioOriginante = new JLabel("Usuario Originante: ");
		lblUsuarioOriginante.setBounds(70, 36, 121, 20);
		panel.add(lblUsuarioOriginante);
		
		textField = new JTextField();
		textField.setBounds(212, 36, 118, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsuarioDestinatario = new JLabel("Usuario Destinatario:");
		lblUsuarioDestinatario.setBounds(70, 67, 121, 20);
		panel.add(lblUsuarioDestinatario);
		
		textField_1 = new JTextField();
		textField_1.setBounds(212, 67, 118, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setBounds(70, 98, 46, 14);
		panel.add(lblMonto);
		
		textField_2 = new JTextField();
		textField_2.setBounds(212, 98, 118, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(212, 185, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnConfirmarTransaccion = new JButton("Confirmar transaccion");
		btnConfirmarTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setEnabled(true);
			}
		});
		btnConfirmarTransaccion.setBounds(136, 129, 174, 28);
		panel.add(btnConfirmarTransaccion);
		
		JLabel lblCalificacion = new JLabel("Calificacion:");
		lblCalificacion.setBounds(101, 188, 73, 14);
		panel.add(lblCalificacion);
		
		JButton btnGuardarYVolver = new JButton("Guardar y volver");
		btnGuardarYVolver.setBounds(51, 216, 113, 23);
		panel.add(btnGuardarYVolver);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(268, 216, 89, 23);
		panel.add(btnCancelar);
		
		
	}

}
