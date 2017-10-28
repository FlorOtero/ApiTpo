package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class VerCalificacion {

	private JFrame frmVerCalificacion;
	protected String trid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerCalificacion window = new VerCalificacion("");
					window.frmVerCalificacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VerCalificacion(String trid) {
		this.trid = trid;
		initialize();
	}
	
	public VerCalificacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVerCalificacion = new JFrame();
		frmVerCalificacion.setTitle("Ver Calificación | API");
		frmVerCalificacion.setBounds(100, 100, 500, 430);
		frmVerCalificacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JMenuBar menuBar = new JMenuBar();
		frmVerCalificacion.setJMenuBar(menuBar);
		
		JButton btnInicio = new JButton("");
		String homePath = new File("src/main/resources/house.png").getAbsolutePath();
		btnInicio.setIcon(new ImageIcon(homePath));
		menuBar.add(btnInicio);
		
		btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmVerCalificacion.dispose();
			}
		});
		
		JMenu mnMiCuenta = new JMenu("Mi Cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta Corriente");
		mnMiCuenta.add(mntmCuentaCorriente);
		mntmCuentaCorriente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiCuentaCorriente cc = new MiCuentaCorriente();
				cc.setVisible(true);
				frmVerCalificacion.dispose();
			}
		});
		
		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		mntmMiReputacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reputacion reputacion = new Reputacion();
				reputacion.setVisible(true);
				frmVerCalificacion.dispose();
			}
		});
		
		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		mntmMiUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiUsuario miUsuario = new MiUsuario();
				miUsuario.setVisible(true);
				frmVerCalificacion.dispose();
			}
		});
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar ingreso = new Ingresar();
				ingreso.setVisible(true);
				frmVerCalificacion.dispose();
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
				frmVerCalificacion.dispose();
			}
		});
		
		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmVerCalificacion.getContentPane().setLayout(null);
		mntmMisPublicaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MisPublicaciones misPublicaciones = new MisPublicaciones();
				misPublicaciones.setVisible(true);
				frmVerCalificacion.dispose();
			}
		});
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Mi Cuenta > Ver Calificación");
		lblBreadcrumb.setBounds(10, 20, 480, 16);
		frmVerCalificacion.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 500, 12);
		frmVerCalificacion.getContentPane().add(separator);
		
		JLabel lblCalificacionOtorgada = new JLabel("Calificación otorgada:");
		lblCalificacionOtorgada.setBounds(300, 60, 145, 16);
		frmVerCalificacion.getContentPane().add(lblCalificacionOtorgada);
		
		JLabel lblCalificacion = new JLabel("10");
		lblCalificacion.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCalificacion.setBounds(445, 57, 25, 22);
		frmVerCalificacion.getContentPane().add(lblCalificacion);
		
		String starPath = new File("src/main/resources/star.png").getAbsolutePath();
		JLabel lblStar = new JLabel(new ImageIcon(starPath));
		lblStar.setBounds(470, 57, 16, 22);
		frmVerCalificacion.getContentPane().add(lblStar);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 60, 50, 16);
		frmVerCalificacion.getContentPane().add(lblFecha);
		
		/**
		 * Indicar la fecha en que se realizo la calificacion
		 */
		JLabel lblFechaCalificacion = new JLabel("20-03-2017");
		lblFechaCalificacion.setBounds(60, 60, 100, 16);
		frmVerCalificacion.getContentPane().add(lblFechaCalificacion);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 90, 60, 16);
		frmVerCalificacion.getContentPane().add(lblUsuario);
		
		/**
		 * Indicar el nombre del usuario que califico
		 */
		JLabel lblUsuarioCalificador = new JLabel("cosmefulanito");
		lblUsuarioCalificador.setBounds(70, 90, 420, 16);
		frmVerCalificacion.getContentPane().add(lblUsuarioCalificador);
		
		JLabel lblPublicacion = new JLabel("Publicación:");
		lblPublicacion.setBounds(10, 120, 85, 16);
		frmVerCalificacion.getContentPane().add(lblPublicacion);
		
		JButton btnVerPublicacion = new JButton("Ver Publicación");
		btnVerPublicacion.setBounds(95, 114, 140, 30);
		frmVerCalificacion.getContentPane().add(btnVerPublicacion);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setBounds(10, 150, 480, 16);
		frmVerCalificacion.getContentPane().add(lblObservaciones);
		
		/**
		 * Indicar las observaciones de la calificacion realizada
		 * 
		 * TODO armar el scrollpane + que calificacion ??
		 */
		
		JTextPane textPaneObservaciones = new JTextPane();
		textPaneObservaciones.setBounds(10, 180, 480, 150);
		frmVerCalificacion.getContentPane().add(textPaneObservaciones);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(370, 350, 120, 30);
		frmVerCalificacion.getContentPane().add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiCuentaCorriente mcc = new MiCuentaCorriente();
				mcc.setVisible(true);
				frmVerCalificacion.dispose();
			}
		});

		
	}
	
	public void setVisible(boolean isVisible) {
		this.frmVerCalificacion.setVisible(isVisible);
	}

}
