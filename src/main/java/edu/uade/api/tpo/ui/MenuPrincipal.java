package edu.uade.api.tpo.ui;

import javax.swing.*;
import java.awt.*;

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
		frmMenuPrincipal.setTitle("Menu Principal");
		frmMenuPrincipal.setBounds(100, 100, 450, 300);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipal.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 434, 261);
		frmMenuPrincipal.getContentPane().add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(41, 11, 211, 34);
		panel.add(menuBar);
		
		JMenu mnMiCuenta = new JMenu("Mi Cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmModificarCuenta = new JMenuItem("Modificar Cuenta");
		mnMiCuenta.add(mntmModificarCuenta);
		
		JMenuItem mntmEliminarCuenta = new JMenuItem("Eliminar Cuenta");
		mnMiCuenta.add(mntmEliminarCuenta);
		
		JMenuItem mntmEstadoCuentaCorriente = new JMenuItem("Estado Cuenta Corriente");
		mnMiCuenta.add(mntmEstadoCuentaCorriente);
		
		JMenuItem mntmReputacion = new JMenuItem("Reputacion");
		mnMiCuenta.add(mntmReputacion);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
		mnMiCuenta.add(mntmCerrarSesion);
		
		JMenu mnPublicaciones = new JMenu("Publicaciones");
		menuBar.add(mnPublicaciones);
		
		JMenuItem mntmNuevaPublicacion = new JMenuItem("Nueva Publicacion");
		mnPublicaciones.add(mntmNuevaPublicacion);
		
		JMenuItem mntmModificarPublicacion = new JMenuItem("Modificar Publicacion");
		mnPublicaciones.add(mntmModificarPublicacion);
		
		JMenuItem mntmEliminarPublicacion = new JMenuItem("Eliminar Publicacion");
		mnPublicaciones.add(mntmEliminarPublicacion);
		
		JMenuItem mntmConsultaDeComisiones = new JMenuItem("Consulta de comisiones pagadas");
		mnPublicaciones.add(mntmConsultaDeComisiones);
		
		JMenu mnPagos = new JMenu("Consultas");
		menuBar.add(mnPagos);
		
		JMenuItem mntmCalificaciones = new JMenuItem("Calificaciones");
		mnPagos.add(mntmCalificaciones);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Transacciones Confirmadas");
		mnPagos.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Transacciones Canceladas");
		mnPagos.add(mntmNewMenuItem_1);
		
		JMenuItem mntmMovimientosCuentaCorriente = new JMenuItem("Movimientos Cuenta Corriente");
		mnPagos.add(mntmMovimientosCuentaCorriente);
		
		JLabel lblIngresarProducto = new JLabel("Ingresar producto:");
		lblIngresarProducto.setBounds(95, 85, 91, 14);
		panel.add(lblIngresarProducto);
		
		textField = new JTextField();
		textField.setBounds(211, 82, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(163, 153, 89, 23);
		panel.add(btnBuscar);
	}

	public void setVisible(boolean isVisible) {
		this.frmMenuPrincipal.setVisible(isVisible);
	}

}
