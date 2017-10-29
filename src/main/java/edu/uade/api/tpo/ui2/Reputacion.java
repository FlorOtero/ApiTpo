package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.Transaccion;
import edu.uade.api.tpo.model.Usuario;

import javax.swing.JButton;

public class Reputacion {

	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
    private static final Logger logger = LoggerFactory.getLogger(MiCuentaCorriente.class);
	private Usuario user;
	private JFrame frmMiReputacion;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reputacion window = new Reputacion();
					window.frmMiReputacion.setVisible(true);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Reputacion() {
		loadUser();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMiReputacion = new JFrame();
		frmMiReputacion.setTitle("Mi Reputación");
		frmMiReputacion.setBounds(100, 100, 500, 380);
		frmMiReputacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmMiReputacion.setJMenuBar(menuBar);
		
		JButton btnInicio = new JButton("");
		String homePath = new File("src/main/resources/house.png").getAbsolutePath();
		btnInicio.setIcon(new ImageIcon(homePath));
		menuBar.add(btnInicio);
		
		btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmMiReputacion.dispose();
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
				frmMiReputacion.dispose();
			}
		});
		
		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		mntmMiReputacion.setEnabled(false);
		
		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		mntmMiUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiUsuario miUsuario = new MiUsuario();
				miUsuario.setVisible(true);
				frmMiReputacion.dispose();
			}
		});
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar ingreso = new Ingresar();
				ingreso.setVisible(true);
				frmMiReputacion.dispose();
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
				frmMiReputacion.dispose();
			}
		});
		
		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmMiReputacion.getContentPane().setLayout(null);
		mntmMisPublicaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MisPublicaciones misPublicaciones = new MisPublicaciones();
				misPublicaciones.setVisible(true);
				frmMiReputacion.dispose();
			}
		});
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Mi Cuenta > Mi Reputación");
		lblBreadcrumb.setBounds(10, 20, 480, 16);
		frmMiReputacion.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 500, 12);
		frmMiReputacion.getContentPane().add(separator);
		
		JLabel lblReputacion = new JLabel("Reputación:");
		lblReputacion.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblReputacion.setBounds(10, 60, 100, 22);
		frmMiReputacion.getContentPane().add(lblReputacion);
		
		/**
		 * Indicar la reputacion
		 */
		JLabel lblReputacionNumero = new JLabel(Float.toString(user.calcularReputacion()));
		lblReputacionNumero.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblReputacionNumero.setBounds(110, 60, 30, 22);
		frmMiReputacion.getContentPane().add(lblReputacionNumero);
		
		String starPath = new File("src/main/resources/star.png").getAbsolutePath();
		JLabel lblStar = new JLabel(new ImageIcon(starPath));
		lblStar.setBounds(140, 60, 16, 22);
		frmMiReputacion.getContentPane().add(lblStar);
		
		String[] columnNames = {"Fecha", "Título", "Usuario", "Calificación"};
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, columnNames));
		table.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 90, 480, 200);
		table.setFillsViewportHeight(true);
		frmMiReputacion.getContentPane().add(scrollPane);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		List<Transaccion> transacciones = user.getCuentaCorriente().getTransacciones();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		for(Transaccion tr : transacciones) {
			if(!tr.getContraparteId().equals(user.getId())){
				
				Usuario contraparte = SistemaUsuarios.getInstance().buscarUsuarioById(tr.getContraparteId());
				
				model.addRow(new Object[] {
						format.format(tr.getFecha()),
						tr.getPublicacion().getArticulo().getNombre(),
						contraparte.getNombreUsuario(),
						tr.getCalificacion().getCalificacion()
				});
			}
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(370, 300, 120, 30);
		frmMiReputacion.getContentPane().add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmMiReputacion.dispose();
			}
		});
		
	}
	
	public void setVisible(boolean isVisible) {
		this.frmMiReputacion.setVisible(isVisible);
	}
	
	private void loadUser() {
		String nombreUsuario = prefs.get("USERNAME", null);
		user = SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);
	}

}
