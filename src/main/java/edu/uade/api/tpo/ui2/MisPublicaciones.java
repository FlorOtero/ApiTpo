package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MisPublicaciones {

	private JFrame frmMisPublicaciones;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MisPublicaciones window = new MisPublicaciones();
					window.frmMisPublicaciones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MisPublicaciones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMisPublicaciones = new JFrame();
		frmMisPublicaciones.setTitle("Mis Publicaciones | API");
		frmMisPublicaciones.setBounds(100, 100, 500, 450);
		frmMisPublicaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMisPublicaciones.setJMenuBar(menuBar);
		
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
		frmMisPublicaciones.getContentPane().setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Publicaciones > Mis Publicaciones");
		lblBreadcrumb.setBounds(10, 20, 480, 16);
		frmMisPublicaciones.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 500, 12);
		frmMisPublicaciones.getContentPane().add(separator);
		
		String[] columnNames = {"Tipo", "Título", "Precio", "Categoría"};
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, columnNames));
		table.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPanePublicaciones = new JScrollPane(table);
		scrollPanePublicaciones.setBounds(10, 60, 480, 300);
		table.setFillsViewportHeight(true);
		frmMisPublicaciones.getContentPane().add(scrollPanePublicaciones);		

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(370, 370, 120, 30);
		frmMisPublicaciones.getContentPane().add(btnVolver);
	}
}
