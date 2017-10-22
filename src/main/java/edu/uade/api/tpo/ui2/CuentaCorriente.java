package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class CuentaCorriente {

	private JFrame frmCuentaCorriente;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CuentaCorriente window = new CuentaCorriente();
					window.frmCuentaCorriente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CuentaCorriente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCuentaCorriente = new JFrame();
		frmCuentaCorriente.setTitle("Cuenta Corriente | API");
		frmCuentaCorriente.setBounds(100, 100, 780, 620);
		frmCuentaCorriente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmCuentaCorriente.setJMenuBar(menuBar);
		
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
		frmCuentaCorriente.getContentPane().setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Mi Cuenta > Cuenta Corriente");
		lblBreadcrumb.setBounds(10, 20, 760, 16);
		frmCuentaCorriente.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 780, 12);
		frmCuentaCorriente.getContentPane().add(separator);
		
		JLabel lblFiltrarResultados = new JLabel("Filtrar resultados:");
		lblFiltrarResultados.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblFiltrarResultados.setBounds(10, 60, 760, 20);
		frmCuentaCorriente.getContentPane().add(lblFiltrarResultados);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 90, 375, 16);
		frmCuentaCorriente.getContentPane().add(lblTipo);
		
		String[] tipoStrings = { "Todos", "Compras", "Ventas", "Comisiones"};
		JComboBox comboTipo = new JComboBox(tipoStrings);
		comboTipo.setBounds(10, 110, 375, 30);
		comboTipo.setSelectedIndex(0);
		frmCuentaCorriente.getContentPane().add(comboTipo);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(395, 92, 375, 16);
		frmCuentaCorriente.getContentPane().add(lblEstado);
		
		String[] estadoStrings = { "Todos", "Aprobadas", "Canceladas"};
		JComboBox comboEstado = new JComboBox(estadoStrings);
		comboEstado.setBounds(395, 110, 375, 30);
		comboEstado.setSelectedIndex(0);
		frmCuentaCorriente.getContentPane().add(comboEstado);
		
		String[] columnNames = {"Fecha", "Título", "Estado", "Tipo", "Monto", "Calificación"};
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, columnNames));
		table.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 150, 760, 300);
		table.setFillsViewportHeight(true);
		frmCuentaCorriente.getContentPane().add(scrollPane);
		
		JLabel lblSaldo = new JLabel("Saldo de la Cuenta Corriente:");
		lblSaldo.setBounds(10, 460, 190, 16);
		frmCuentaCorriente.getContentPane().add(lblSaldo);
		
		JLabel lblSaldoCtaCte = new JLabel("$1000");
		lblSaldoCtaCte.setBounds(200, 460, 570, 16);
		frmCuentaCorriente.getContentPane().add(lblSaldoCtaCte);
		
		JTextPane txtMensajes = new JTextPane();
		txtMensajes.setText("Aquí se muestran los mensajes");
		txtMensajes.setBackground(SystemColor.window);
		txtMensajes.setBounds(10, 490, 760, 40);
		frmCuentaCorriente.getContentPane().add(txtMensajes);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(650, 540, 120, 30);
		frmCuentaCorriente.getContentPane().add(btnVolver);
	}
}
