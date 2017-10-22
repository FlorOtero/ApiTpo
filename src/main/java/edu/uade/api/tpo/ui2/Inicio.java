package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;

public class Inicio {

	private JFrame frmInicioApi;
	private JTextField txtBuscador;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frmInicioApi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInicioApi = new JFrame();
		frmInicioApi.setTitle("Inicio | API");
		frmInicioApi.setBounds(100, 100, 500, 480);
		frmInicioApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmInicioApi.setJMenuBar(menuBar);
		
		JMenu mnMiCuenta = new JMenu("Mi Cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta Corriente");
		mnMiCuenta.add(mntmCuentaCorriente);
		
		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		
		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		
		JMenu mnPublicaciones = new JMenu("Publicaciones");
		menuBar.add(mnPublicaciones);
		
		JMenuItem mntmNuevaPublicacion = new JMenuItem("Nueva Publicación");
		mnPublicaciones.add(mntmNuevaPublicacion);
		
		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmInicioApi.getContentPane().setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenid@");
		lblBienvenido.setBounds(10, 20, 80, 16);
		frmInicioApi.getContentPane().add(lblBienvenido);
		
		/**
		 * HAY QUE SETEAR ESTE LABEL CON EL NOMBRE DEL USUARIO LOGUEADO!!!
		 */
		JLabel lblNombreDeUsuario = new JLabel("cosmefulanito!");
		lblNombreDeUsuario.setBounds(90, 20, 400, 16);
		frmInicioApi.getContentPane().add(lblNombreDeUsuario);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 500, 12);
		frmInicioApi.getContentPane().add(separator);
		
		JLabel lblBuscarPublicaciones = new JLabel("Buscar publicaciones:");
		lblBuscarPublicaciones.setBounds(10, 60, 480, 16);
		frmInicioApi.getContentPane().add(lblBuscarPublicaciones);
		
		txtBuscador = new JTextField();
		txtBuscador.setBounds(10, 80, 360, 30);
		frmInicioApi.getContentPane().add(txtBuscador);
		txtBuscador.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(373, 80, 120, 30);
		frmInicioApi.getContentPane().add(btnBuscar);
		
		table = new JTable();
		table.setBounds(10, 120, 480, 300);
		frmInicioApi.getContentPane().add(table);

	}
}
