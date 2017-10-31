package edu.uade.api.tpo.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.api.tpo.controller.SistemaTransacciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.Transaccion;
import edu.uade.api.tpo.model.Usuario;
import java.awt.SystemColor;

public class VerCalificacion {

	private static final Logger logger = LoggerFactory.getLogger(MiCuentaCorriente.class);
	private JFrame frmVerCalificacion;
	protected String trid;
	private Transaccion tr;
	private Usuario user;
	private String origen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerCalificacion window = new VerCalificacion();
					window.frmVerCalificacion.setVisible(true);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VerCalificacion(String trid, String origen) {
		this.trid = trid;
		this.origen = origen;
		loadUser();
		loadTransaccion();
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

		JLabel lblCalificacion = new JLabel(String.valueOf(tr.getCalificacion().getCalificacion()));
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
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		JLabel lblFechaCalificacion = new JLabel(format.format(tr.getCalificacion().getFecha()));
		lblFechaCalificacion.setBounds(60, 60, 100, 16);
		frmVerCalificacion.getContentPane().add(lblFechaCalificacion);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 90, 60, 16);
		frmVerCalificacion.getContentPane().add(lblUsuario);

		/**
		 * Indicar el nombre del usuario que califico
		 */
		String idCalificador;
		if (tr.getPublicacion().getUsuarioId().equals(user.getId())) {
			idCalificador = tr.getContraparteId();
		} else {
			idCalificador = tr.getPublicacion().getUsuarioId();
		}
		Usuario calificador = SistemaUsuarios.getInstance().buscarUsuarioById(idCalificador);
		JLabel lblUsuarioCalificador = new JLabel(calificador.getNombreUsuario());
		lblUsuarioCalificador.setBounds(70, 90, 420, 16);
		frmVerCalificacion.getContentPane().add(lblUsuarioCalificador);

		JLabel lblPublicacion = new JLabel("Publicación:");
		lblPublicacion.setBounds(10, 120, 85, 16);
		frmVerCalificacion.getContentPane().add(lblPublicacion);

		JButton btnVerPublicacion = new JButton("Ver Publicación");
		btnVerPublicacion.setBounds(95, 114, 140, 30);
		frmVerCalificacion.getContentPane().add(btnVerPublicacion);
		btnVerPublicacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VerPublicacion publicacion = new VerPublicacion(tr.getPublicacion());
				publicacion.setVisible(true);
			}
		});

		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setBounds(10, 150, 480, 16);
		frmVerCalificacion.getContentPane().add(lblObservaciones);

		/**
		 * Indicar las observaciones de la calificacion realizada
		 * 
		 * TODO armar el scrollpane + que calificacion ??
		 */
		JTextArea textAreaObservaciones = new JTextArea(tr.getCalificacion().getObservaciones());
		textAreaObservaciones.setBackground(SystemColor.window);
		textAreaObservaciones.setLineWrap(true);
		textAreaObservaciones.setWrapStyleWord(true);
		textAreaObservaciones.setEditable(false);

		JScrollPane scrollPaneDescripcion = new JScrollPane(textAreaObservaciones);
		scrollPaneDescripcion.setBounds(10, 180, 480, 150);
		scrollPaneDescripcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDescripcion.setPreferredSize(new Dimension(480, 150));
		frmVerCalificacion.getContentPane().add(scrollPaneDescripcion);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(370, 350, 120, 30);
		frmVerCalificacion.getContentPane().add(btnVolver);

		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (origen.equals("cuenta-corriente")) {
					MiCuentaCorriente mcc = new MiCuentaCorriente();
					mcc.setVisible(true);
				} else if (origen.equals("reputacion")) {
					Reputacion reputacion = new Reputacion();
					reputacion.setVisible(true);
				}
				frmVerCalificacion.dispose();
			}
		});

	}

	public void setVisible(boolean isVisible) {
		this.frmVerCalificacion.setVisible(isVisible);
	}

	private void loadUser() {
		user = SistemaUsuarios.getInstance().getUsuarioActivo();
	}

	private void loadTransaccion() {
		try {
			tr = SistemaTransacciones.getInstance().buscarTransaccionById(trid);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.PLAIN_MESSAGE);
		}
	}

}
