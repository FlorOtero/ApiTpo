package edu.uade.api.tpo.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class AltaPublicacion {

	private JFrame frmNuevaPublicacion;
	private JTextField txtTitulo;
	private JPanel panelTipoPublicacion;
	private JPanel panelCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaPublicacion window = new AltaPublicacion();
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
	public AltaPublicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevaPublicacion = new JFrame();
		frmNuevaPublicacion.setTitle("Nueva Publicación | API");
		frmNuevaPublicacion.setBounds(100, 100, 780, 780);
		frmNuevaPublicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmNuevaPublicacion.setJMenuBar(menuBar);
		
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
		frmNuevaPublicacion.getContentPane().setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Publicaciones > Nueva Publicación");
		lblBreadcrumb.setBounds(10, 20, 760, 16);
		frmNuevaPublicacion.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 780, 12);
		frmNuevaPublicacion.getContentPane().add(separator);
		
		JLabel lblTitulo = new JLabel("Título de la Publicación");
		lblTitulo.setBounds(10, 60, 480, 16);
		frmNuevaPublicacion.getContentPane().add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(10, 80, 760, 30);
		frmNuevaPublicacion.getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(10, 120, 760, 16);
		frmNuevaPublicacion.getContentPane().add(lblDescripcion);
	
		JTextArea textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		JScrollPane scrollPaneDescripcion = new JScrollPane(textAreaDescripcion);
		scrollPaneDescripcion.setBounds(10, 145, 760, 150);
		scrollPaneDescripcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDescripcion.setPreferredSize(new Dimension(760, 150));
		frmNuevaPublicacion.getContentPane().add(scrollPaneDescripcion);
		
		JButton btnCargarImagenes = new JButton("Cargar imágenes");
		btnCargarImagenes.setBounds(570, 305, 200, 30);
		frmNuevaPublicacion.getContentPane().add(btnCargarImagenes);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 340, 760, 12);
		frmNuevaPublicacion.getContentPane().add(separator_1);
		
		JLabel lblTipoDePublicacion = new JLabel("Tipo de Publicación");
		lblTipoDePublicacion.setBounds(10, 360, 360, 16);
		frmNuevaPublicacion.getContentPane().add(lblTipoDePublicacion);
		
		String[] tipoPublicacionStrings = { "Compra inmediata", "Subasta"};
		JComboBox comboBoxTipoPublicacion = new JComboBox(tipoPublicacionStrings);
		comboBoxTipoPublicacion.setBounds(10, 380, 360, 27);
		comboBoxTipoPublicacion.setSelectedIndex(0);
		frmNuevaPublicacion.getContentPane().add(comboBoxTipoPublicacion);
		
		panelTipoPublicacion = new JPanel();
		panelTipoPublicacion.setBounds(10, 410, 360, 180);
		frmNuevaPublicacion.getContentPane().add(panelTipoPublicacion);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(384, 360, 12, 230);
		frmNuevaPublicacion.getContentPane().add(separator_2);
		
		JLabel lblCategoria = new JLabel("Categoría");
		lblCategoria.setBounds(410, 360, 360, 16);
		frmNuevaPublicacion.getContentPane().add(lblCategoria);
		
		String[] categoriaStrings = { "Producto", "Servicio"};
		JComboBox comboBoxCategoria = new JComboBox(categoriaStrings);
		comboBoxCategoria.setBounds(410, 380, 360, 27);
		frmNuevaPublicacion.getContentPane().add(comboBoxCategoria);
		
		panelCategoria = new JPanel();
		panelCategoria.setBounds(410, 410, 360, 180);
		frmNuevaPublicacion.getContentPane().add(panelCategoria);
		
		JLabel lblMedioDePago = new JLabel("Medio de Pago aceptados:");
		lblMedioDePago.setBounds(10, 600, 760, 16);
		frmNuevaPublicacion.getContentPane().add(lblMedioDePago);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(10, 630, 130, 23);
		frmNuevaPublicacion.getContentPane().add(chckbxEfectivo);
		
		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de Crédito");
		chckbxTarjetaDeCredito.setBounds(140, 630, 150, 23);
		frmNuevaPublicacion.getContentPane().add(chckbxTarjetaDeCredito);
		
		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia Bancaria");
		chckbxTransferenciaBancaria.setBounds(330, 630, 180, 23);
		frmNuevaPublicacion.getContentPane().add(chckbxTransferenciaBancaria);
		
		JLabel lblFinPublicacion = new JLabel("Fin de la Publicación:");
		lblFinPublicacion.setBounds(10, 670, 140, 16);
		frmNuevaPublicacion.getContentPane().add(lblFinPublicacion);
		
		JLabel lblFechaFinPublicacion = new JLabel("20-10-2017");
		lblFechaFinPublicacion.setBounds(150, 670, 340, 16);
		frmNuevaPublicacion.getContentPane().add(lblFechaFinPublicacion);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(650, 700, 120, 30);
		frmNuevaPublicacion.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(530, 700, 120, 30);
		frmNuevaPublicacion.getContentPane().add(btnCancelar);
		
		
	

		
	}
}
