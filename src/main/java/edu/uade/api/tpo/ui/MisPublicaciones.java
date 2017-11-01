package edu.uade.api.tpo.ui;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MisPublicaciones {

	private static final Logger logger = LoggerFactory.getLogger(Ingresar.class);
	private JFrame frmMisPublicaciones;
	private JTable table;
	private TableRowSorter<DefaultTableModel> sorter;
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
					logger.error(e.getMessage());
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
		loadPublicaciones();
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

		JButton btnInicio = new JButton("");
		String homePath = new File("src/main/resources/house.png").getAbsolutePath();
		btnInicio.setIcon(new ImageIcon(homePath));
		menuBar.add(btnInicio);

		btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmMisPublicaciones.dispose();
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
				frmMisPublicaciones.dispose();
			}
		});

		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		mntmMiReputacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reputacion reputacion = new Reputacion();
				reputacion.setVisible(true);
				frmMisPublicaciones.dispose();
			}
		});

		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		mntmMiUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiUsuario miUsuario = new MiUsuario();
				miUsuario.setVisible(true);
				frmMisPublicaciones.dispose();
			}
		});

		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar ingreso = new Ingresar();
				ingreso.setVisible(true);
				frmMisPublicaciones.dispose();
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
				frmMisPublicaciones.dispose();
			}
		});

		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmMisPublicaciones.getContentPane().setLayout(null);
		mntmMisPublicaciones.setEnabled(false);

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
			public boolean isCellEditable(int row, int column) {
				return false;
			}

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
		
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) table.getModel());
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		table.setRowSorter(sorter);
	
		JScrollPane scrollPanePublicaciones = new JScrollPane(table);
		scrollPanePublicaciones.setBounds(10, 60, 480, 300);
		table.setFillsViewportHeight(true);
		frmMisPublicaciones.getContentPane().add(scrollPanePublicaciones);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(370, 370, 120, 30);
		frmMisPublicaciones.getContentPane().add(btnVolver);

		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmMisPublicaciones.dispose();
			}
		});
	}

	private void loadUserData() {
		user = SistemaUsuarios.getInstance().getUsuarioActivo();
	}

	private void loadPublicaciones() {

		List<Publicacion> publicaciones = user.getPublicaciones();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		for (Publicacion p : publicaciones) {
			String categoria = (p.getArticulo() instanceof Producto) ? "Producto" : "Servicio";
			String tipoPublicacion = (p instanceof Subasta) ? "subasta-16.png" : "compra-inmediata-16.png";
			String precio = (p instanceof Subasta) ? Float.toString(((Subasta) p).getPrecioActual())
					: Float.toString(p.getPrecio());
			ImageIcon tipoPub = new ImageIcon("src/main/resources/" + tipoPublicacion);
			String fecha = format.format(p.getFechaDesde());

			model.addRow(new Object[] { tipoPub, fecha, p.getArticulo().getNombre(), "$" + precio, categoria });
		}
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					Publicacion p = publicaciones.get(row);
					AltaPublicacion publicacion = new AltaPublicacion(p);
					publicacion.setVisible(true);
					frmMisPublicaciones.dispose();
				}
			}
		});
	}

	public void setVisible(boolean isVisible) {
		this.frmMisPublicaciones.setVisible(isVisible);
	}
}
