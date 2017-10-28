package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.SwingConstants;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Usuario;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.FlowLayout;

public class VerPublicacion {

	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	private JFrame frmPublicacinApi;
	private Articulo articulo;
	private Publicacion publicacion;
	private Usuario user;
	
	public VerPublicacion(Publicacion p){
		this.publicacion = p;
		this.articulo = p.getArticulo();
		String nombreUsuario = prefs.get("USERNAME", null);
		user = SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerPublicacion window = new VerPublicacion();
					window.frmPublicacinApi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VerPublicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPublicacinApi = new JFrame();
		frmPublicacinApi.setTitle("Publicación | API");
		frmPublicacinApi.setBounds(100, 100, 500, 680);
		frmPublicacinApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPublicacinApi.setJMenuBar(menuBar);
		
		JButton btnInicio = new JButton("");
		String homePath = new File("src/main/resources/house.png").getAbsolutePath();
		btnInicio.setIcon(new ImageIcon(homePath));
		menuBar.add(btnInicio);
		
		btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmPublicacinApi.dispose();
			}
		});
		
		JMenu mnMiCuenta = new JMenu("Mi Cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta Corriente");
		mnMiCuenta.add(mntmCuentaCorriente);
		mntmCuentaCorriente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CuentaCorriente cc = new CuentaCorriente();
				cc.setVisible(true);
				frmPublicacinApi.dispose();
			}
		});
		
		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		mntmMiReputacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reputacion reputacion = new Reputacion();
				reputacion.setVisible(true);
				frmPublicacinApi.dispose();
			}
		});
		
		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		mntmMiUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiUsuario miUsuario = new MiUsuario();
				miUsuario.setVisible(true);
				frmPublicacinApi.dispose();
			}
		});
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar ingreso = new Ingresar();
				ingreso.setVisible(true);
				frmPublicacinApi.dispose();
			}
		});
		
		JMenu mnPublicaciones = new JMenu("Publicaciones");
		menuBar.add(mnPublicaciones);
		
		JMenuItem mntmNuevaPublicacion = new JMenuItem("Nueva Publicación");
		mnPublicaciones.add(mntmNuevaPublicacion);
		mntmNuevaPublicacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaPublicacion altaPublicacion = new AltaPublicacion();
				altaPublicacion.setVisible(true);
				frmPublicacinApi.dispose();
			}
		});
		
		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmPublicacinApi.getContentPane().setLayout(null);
		mntmMisPublicaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MisPublicaciones misPublicaciones = new MisPublicaciones();
				misPublicaciones.setVisible(true);
				frmPublicacinApi.dispose();
			}
		});
		
		JButton btnImagenSiguiente = new JButton(">");
		btnImagenSiguiente.setBounds(210, 300, 30, 30);
		frmPublicacinApi.getContentPane().add(btnImagenSiguiente);
		
		JButton btnImagenAnterior = new JButton("<");
		btnImagenAnterior.setBounds(15, 300, 30, 30);
		frmPublicacinApi.getContentPane().add(btnImagenAnterior);
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Ver Publicación");
		lblBreadcrumb.setBounds(10, 20, 480, 16);
		frmPublicacinApi.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 500, 12);
		frmPublicacinApi.getContentPane().add(separator);
		
		/**
		 * Reemplazar el texto del label con el título de la publicación (nombre de artículo)
		 */
		JLabel lblTituloPublicacion = new JLabel("Título de la Publicación");
		lblTituloPublicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTituloPublicacion.setBounds(10, 60, 480, 30);
		frmPublicacinApi.getContentPane().add(lblTituloPublicacion);
		
		JLabel lblImagen = new JLabel("");
		/**
		 * Indicar la ruta de la imagen
		 */
		String imagePath = new File("src/main/resources/default-image.png").getAbsolutePath();
		lblImagen.setBackground(Color.WHITE);
		lblImagen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		lblImagen.setIcon(new ImageIcon(imagePath));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(10, 100, 235, 235);
		frmPublicacinApi.getContentPane().add(lblImagen);
		
		/**
		 * Indicar el precio de la publicación. No olvidar el signo pesos!
		 */
		JLabel lblPrecio = new JLabel("$1000");
		lblPrecio.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblPrecio.setBounds(255, 100, 200, 32);
		frmPublicacinApi.getContentPane().add(lblPrecio);
		
		/**
		 * Indicar si es compra inmediata o subasta
		 */
		JLabel lblTipoPublicacion = new JLabel("Compra inmediata");
		lblTipoPublicacion.setBounds(255, 140, 235, 16);
		frmPublicacinApi.getContentPane().add(lblTipoPublicacion);
		
		/**
		 * Mostrar la imagen que corresponde al tipo de publicacion!!
		 */
		String compraInmediataPath = new File("src/main/resources/compra-inmediata.png").getAbsolutePath();
		String subastaPath = new File("src/main/resources/subasta.png").getAbsolutePath();
		/**
		 * Hay que pasarle como parametro el icono que queremos mostrar: compraInmediataPath o subastaPath
		 */
		JLabel lblTipoPublicacionIcon = new JLabel(new ImageIcon(compraInmediataPath));
		lblTipoPublicacionIcon.setBounds(455, 100, 32, 32);
		frmPublicacinApi.getContentPane().add(lblTipoPublicacionIcon);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(255, 160, 235, 12);
		frmPublicacinApi.getContentPane().add(separator_1);
		
		JLabel lblMediosDePago = new JLabel("Medios de Pago disponibles:");
		lblMediosDePago.setBounds(255, 180, 235, 16);
		frmPublicacinApi.getContentPane().add(lblMediosDePago);
		
		JPanel panelMediosDePago = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelMediosDePago.getLayout();
		flowLayout.setHgap(25);
		panelMediosDePago.setBounds(255, 200, 235, 24);
		frmPublicacinApi.getContentPane().add(panelMediosDePago);
		
		/**
		 * Hay que ocultar/mostrar los lblEfectivo, lblTarjeta y lblTransferencia de acuerdo
		 * a los medios de pago disponibles
		 */
		
		String efvoPath = new File("src/main/resources/cash.png").getAbsolutePath();
		JLabel lblEfectivo = new JLabel(new ImageIcon(efvoPath));
		panelMediosDePago.add(lblEfectivo);
		
		String tarjetaPath = new File("src/main/resources/credit-card.png").getAbsolutePath();
		JLabel lblTarjeta = new JLabel(new ImageIcon(tarjetaPath));
		panelMediosDePago.add(lblTarjeta);
		
		String transferenciaPath = new File("src/main/resources/money-transfer.png").getAbsolutePath();
		JLabel lblTransferencia = new JLabel(new ImageIcon(transferenciaPath));
		panelMediosDePago.add(lblTransferencia);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(255, 230, 235, 12);
		frmPublicacinApi.getContentPane().add(separator_2);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setBounds(255, 250, 70, 16);
		frmPublicacinApi.getContentPane().add(lblVendedor);
		
		/**
		 * Indicar el nombre del vendedor
		 */
		JLabel lblNombreVendedor = new JLabel("cosmefulanito");
		lblNombreVendedor.setBounds(330, 250, 160, 16);
		frmPublicacinApi.getContentPane().add(lblNombreVendedor);
		
		JLabel lblReputacion = new JLabel("Reputación:");
		lblReputacion.setBounds(255, 280, 80, 16);
		frmPublicacinApi.getContentPane().add(lblReputacion);
		
		JLabel lblReputacionVendedor = new JLabel("10");
		lblReputacionVendedor.setBounds(340, 280, 20, 16);
		frmPublicacinApi.getContentPane().add(lblReputacionVendedor);
		
		String starPath = new File("src/main/resources/star.png").getAbsolutePath();
		JLabel lblStar = new JLabel(new ImageIcon(starPath));
		lblStar.setBounds(360, 280, 16, 16);
		frmPublicacinApi.getContentPane().add(lblStar);
		
		/**
		 * El texto debe cambiar de acuerdo al tipo de publicacion
		 * 
		 * String txtBtnComprar = ( Es compra inmediata ) ? "Comprar" : "Ofertar";
		 * JButton btnComprar = new JButton(txtBtnComprar);
		 */	
		JButton btnComprar = new JButton("Comprar");
		btnComprar.setBounds(255, 310, 235, 30);
		frmPublicacinApi.getContentPane().add(btnComprar);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 350, 500, 12);
		frmPublicacinApi.getContentPane().add(separator_3);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblDescripcion.setBounds(10, 370, 480, 30);
		frmPublicacinApi.getContentPane().add(lblDescripcion);
		
		JTextPane txtpnDescripcion = new JTextPane();
		txtpnDescripcion.setText("Descripción de la publicacion");
		txtpnDescripcion.setBounds(10, 410, 480, 170);
		frmPublicacinApi.getContentPane().add(txtpnDescripcion);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(370, 600, 120, 30);
		frmPublicacinApi.getContentPane().add(btnVolver);

	}
	
	public void setVisible(boolean isVisible) {
		this.frmPublicacinApi.setVisible(isVisible);
	}
}
