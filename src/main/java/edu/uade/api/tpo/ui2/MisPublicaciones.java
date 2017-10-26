package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.model.Usuario;

public class MisPublicaciones {

	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	private JFrame frmMisPublicaciones;
	private JTable table;
	private Usuario user;

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
		loadUserData();
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

		String[] columnNames = { "Tipo", "Fecha", "Título", "Precio", "Categoría" };
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		});
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setMaxWidth(32);
		table.getColumnModel().getColumn(1).setMinWidth(90);
		table.getColumnModel().getColumn(1).setMaxWidth(90);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(100);

		JScrollPane scrollPanePublicaciones = new JScrollPane(table);
		scrollPanePublicaciones.setBounds(10, 60, 480, 300);
		table.setFillsViewportHeight(true);
		frmMisPublicaciones.getContentPane().add(scrollPanePublicaciones);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(370, 370, 120, 30);
		frmMisPublicaciones.getContentPane().add(btnVolver);
	}
	
	private void loadUserData() {
		//user = SistemaUsuarios.getUsuarioLoggeado();
		user = SistemaUsuarios.getInstance().buscarUsuario("flor");
		List<Publicacion> publicaciones = user.getPublicaciones();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		for(Publicacion p : publicaciones) {
			String categoria = (p.getArticulo() instanceof Producto) ? "Producto" : "Servicio";
			String tipoPublicacion = (p instanceof Subasta) ? "subasta-16.png" : "compra-inmediata-16.png";
			String precio = Float.toString(p.getPrecio());
			ImageIcon tipoPub = new ImageIcon("src/main/resources/"+tipoPublicacion);
			String fecha = format.format(p.getFechaDesde());
			
			model.addRow(new Object[]{
					tipoPub,
					fecha,
					p.getArticulo().getNombre(),
					"$" + precio,
					categoria
			});
		}
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		        		Publicacion p = publicaciones.get(row);
		        		VerPublicacion publicacion = new VerPublicacion(p);	
		        		publicacion.setVisible(true);
		        		frmMisPublicaciones.dispose();
		            System.out.println("CLICKED "+ p.getArticulo().getNombre());
		        }
		    }
		});
	}
	
}
