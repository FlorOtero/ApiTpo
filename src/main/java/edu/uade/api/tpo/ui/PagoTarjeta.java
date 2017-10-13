package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.uade.api.tpo.model.DatosPago;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PagoTarjeta {

	private JFrame frmPagoConTarjeta;
	private JTextField textField_3;
	private JTextField textField_4;
	private DatosPago datosPago;
	private float monto;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public PagoTarjeta(DatosPago dp, float monto) {
		this.datosPago= dp;
		this.monto= monto;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPagoConTarjeta = new JFrame();
		frmPagoConTarjeta.setTitle("Pago con Tarjeta de Credito");
		frmPagoConTarjeta.setBounds(100, 100, 450, 323);
		frmPagoConTarjeta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPagoConTarjeta.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 450, 301);
		frmPagoConTarjeta.getContentPane().add(panel);
		
		JLabel lblNumeroDeTarjeta = new JLabel("Numero de tarjeta:");
		
		JLabel lblCodigoDeSeguridad = new JLabel("Codigo de seguridad:");
		
		JLabel lblMonto = new JLabel("Monto:");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		textField_4.setColumns(10);
		
		JButton btnConfirmarTransaccion = new JButton("Confirmar Transaccion");
		btnConfirmarTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datosPago.setNumeroTarjeta(textField_3.getText());
				frmPagoConTarjeta.dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(String.valueOf(monto));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblCodigoDeSeguridad, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNumeroDeTarjeta, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMonto, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(92, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(64)
					.addComponent(btnConfirmarTransaccion)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(60)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMonto)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumeroDeTarjeta))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCodigoDeSeguridad))
					.addGap(57)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConfirmarTransaccion)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(76))
		);
		panel.setLayout(gl_panel);
	}
	public void setVisible(boolean isVisible) {
		this.frmPagoConTarjeta.setVisible(isVisible);
	}
}
