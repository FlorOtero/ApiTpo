package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import edu.uade.api.tpo.model.Subasta;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevaPublicacion {
	String opcion;
	private JFrame frmNuevaPublicacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaPublicacion window = new NuevaPublicacion();
					window.frmNuevaPublicacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevaPublicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevaPublicacion = new JFrame();
		frmNuevaPublicacion.setTitle("Nueva publicacion");
		frmNuevaPublicacion.getContentPane().setBackground(new Color(255, 255, 224));
		frmNuevaPublicacion.getContentPane().setLayout(null);
		
		JLabel lblQueTipoDe = new JLabel("Que tipo de publicación desea realizar?");
		lblQueTipoDe.setBounds(113, 40, 265, 16);
		frmNuevaPublicacion.getContentPane().add(lblQueTipoDe);
		
		JRadioButton rdbtnNuevoProducto = new JRadioButton("Nuevo Producto");
		rdbtnNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion="Producto";
			}
		});
		rdbtnNuevoProducto.setBounds(61, 99, 141, 23);
		frmNuevaPublicacion.getContentPane().add(rdbtnNuevoProducto);
		
		JRadioButton rdbtnNuevoServicio = new JRadioButton("Nuevo Servicio");
		rdbtnNuevoServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion="Servicio";
			}
		});
		rdbtnNuevoServicio.setBounds(61, 147, 141, 23);
		frmNuevaPublicacion.getContentPane().add(rdbtnNuevoServicio);
		
		JRadioButton rdbtnNuevaSubasta = new JRadioButton("Nueva Subasta");
		rdbtnNuevaSubasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcion="Subasta";
			}
		});
		rdbtnNuevaSubasta.setBounds(61, 191, 141, 23);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNuevaSubasta);
		group.add(rdbtnNuevoServicio);
		group.add(rdbtnNuevoProducto);
		
		frmNuevaPublicacion.getContentPane().add(rdbtnNuevaSubasta);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(opcion.equals("Producto")) {
					NuevoProducto producto = new NuevoProducto();
					producto.setVisible(true);
					frmNuevaPublicacion.dispose();
				}else {
					if(opcion.equals("Servicio")) {
						NuevoServicio servicio= new NuevoServicio();
						servicio.setVisible(true);
						frmNuevaPublicacion.dispose();
					}else {
						NuevaSubasta subasta= new NuevaSubasta();
						subasta.setVisible(true);
						frmNuevaPublicacion.dispose();
					}
				}
			}
		});
		btnSiguiente.setBounds(60, 243, 117, 29);
		frmNuevaPublicacion.getContentPane().add(btnSiguiente);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				frmNuevaPublicacion.dispose();
			}
		});
		btnCancelar.setBounds(221, 243, 117, 29);
		frmNuevaPublicacion.getContentPane().add(btnCancelar);
		frmNuevaPublicacion.setBounds(100, 100, 450, 300);
		frmNuevaPublicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setVisible(boolean isVisible) {
		this.frmNuevaPublicacion.setVisible(isVisible);
	}
}
