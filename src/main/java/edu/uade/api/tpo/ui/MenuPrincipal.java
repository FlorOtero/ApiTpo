package edu.uade.api.tpo.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal {

	private JFrame frmMenuPrincipal;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frmMenuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setTitle("Menu");
		frmMenuPrincipal.getContentPane().setBackground(new Color(255, 255, 224));
		frmMenuPrincipal.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(176, 61, 273, 26);
		frmMenuPrincipal.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(189, 148, 117, 29);
		frmMenuPrincipal.getContentPane().add(btnBuscar);
		
		JLabel lblIngresarProducto = new JLabel("Ingresar producto");
		lblIngresarProducto.setBounds(46, 66, 117, 16);
		frmMenuPrincipal.getContentPane().add(lblIngresarProducto);
		
		JMenuBar menuBar = new JMenuBar();
		frmMenuPrincipal.setJMenuBar(menuBar);
		
		JMenu mnMiCuenta = new JMenu("Mi cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmModificarCuenta = new JMenuItem("Modificar cuenta");
		mntmModificarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnMiCuenta.add(mntmModificarCuenta);
		
		JMenuItem mntmEliminarCuenta = new JMenuItem("Eliminar cuenta");
		mnMiCuenta.add(mntmEliminarCuenta);
		
		JMenuItem mntmEstadoCuentaCorriente = new JMenuItem("Estado cuenta corriente");
		mnMiCuenta.add(mntmEstadoCuentaCorriente);
		
		JMenuItem mntmReputacion = new JMenuItem("Reputacion");
		mnMiCuenta.add(mntmReputacion);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesion");
		mnMiCuenta.add(mntmCerrarSesion);
		
		JMenu mnPublicaciones = new JMenu("Publicaciones");
		menuBar.add(mnPublicaciones);
		
		JMenuItem mntmNuevaPublicacin = new JMenuItem("Nueva publicación");
		mntmNuevaPublicacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPublicacion nuevaPublicacion = new NuevaPublicacion();
				nuevaPublicacion.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnPublicaciones.add(mntmNuevaPublicacin);
		
		JMenuItem mntmModificarPublicacin = new JMenuItem("Modificar publicación");
		mntmModificarPublicacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificacionPublicacion modificarPublicacion = new ModificacionPublicacion();
				modificarPublicacion.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnPublicaciones.add(mntmModificarPublicacin);
		
		JMenuItem mntmEliminarPublicacion = new JMenuItem("Eliminar publicación");
		mnPublicaciones.add(mntmEliminarPublicacion);
		
		JMenuItem mntmConsultaDeComisiones = new JMenuItem("Consulta de comisiones pagadas");
		mnPublicaciones.add(mntmConsultaDeComisiones);
		
		JMenu mnConsultar = new JMenu("Consultar");
		menuBar.add(mnConsultar);
		
		JMenuItem mntmCalificaciones = new JMenuItem("Calificaciones");
		mnConsultar.add(mntmCalificaciones);
		
		
		JMenuItem mntmTransaccionesConfirmadas = new JMenuItem("Transacciones confirmadas");
		mnConsultar.add(mntmTransaccionesConfirmadas);
		
		JMenuItem mntmTransaccionesCanceladas = new JMenuItem("Transacciones canceladas");
		mnConsultar.add(mntmTransaccionesCanceladas);
		
		JMenuItem mntmMovimientosCuentaCorriente = new JMenuItem("Movimientos Cuenta Corriente");
		mnConsultar.add(mntmMovimientosCuentaCorriente);
	}

	public void setVisible(boolean isVisible) {
		this.frmMenuPrincipal.setVisible(isVisible);
	}
}
