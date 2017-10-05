package edu.uade.api.tpo.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class ModificacionPublicacion extends JFrame {
	
	private JFrame frmModificar;
	private JPanel contentPane;
	String opcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificacionPublicacion window = new ModificacionPublicacion();
					window.frmModificar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ModificacionPublicacion() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	private void initialize() {
		frmModificar = new JFrame();
		frmModificar.getContentPane().setBackground(new Color(255, 255, 224));
		frmModificar.setTitle("Modificar ");
		frmModificar.setBounds(100, 100, 544, 565);
		frmModificar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificar.getContentPane().setLayout(null);
		
		JLabel lblModificar = new JLabel("Seleccione el tipo de publicacion que desea modificar");
		lblModificar.setBounds(52, 6, 344, 16);
		frmModificar.getContentPane().add(lblModificar);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Producto");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion="Producto";
			}
		});
		rdbtnNewRadioButton.setBounds(35, 106, 141, 23);
		frmModificar.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton1 = new JRadioButton("Servicio");
		rdbtnNewRadioButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion="Servicio";
			}
		});
		rdbtnNewRadioButton1.setBounds(35, 159, 141, 23);
		frmModificar.getContentPane().add(rdbtnNewRadioButton1);
		
		JRadioButton rdbtnNewRadioButton11 = new JRadioButton("Subasta");
		rdbtnNewRadioButton11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion="Subasta";
			}
		});
		rdbtnNewRadioButton11.setBounds(35, 57, 141, 23);
		frmModificar.getContentPane().add(rdbtnNewRadioButton11);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton1);
		group.add(rdbtnNewRadioButton11);
				
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(opcion.equals("Producto")) {
					ModificarProducto producto = new ModificarProducto();
					producto.setVisible(true);
					frmModificar.dispose();
				}else {
					if(opcion.equals("Servicio")) {
						ModificacionServicio servicio= new ModificacionServicio();
						servicio.setVisible(true);
						frmModificar.dispose();
					}else {
						ModificarSubasta subasta= new ModificarSubasta();
						subasta.setVisible(true);
						frmModificar.dispose();
					}
				}
			}
		});
		btnAceptar.setBounds(75, 229, 117, 29);
		frmModificar.getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				frmModificar.dispose();
			}
		});
		btnCancelar.setBounds(222, 229, 117, 29);
		frmModificar.getContentPane().add(btnCancelar);
	
		

	}
	public void setVisible(boolean isVisible) {
		this.frmModificar.setVisible(isVisible);
	}
}
