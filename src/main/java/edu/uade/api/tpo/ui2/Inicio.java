package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Servicio;
import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.ui.DetallePublicacion;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Inicio {

	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	private JFrame frmInicioApi;
	private JTextField txtBuscador;
	private JTable table;
	private ArrayList<Publicacion> resultado;
	JButton btnBuscar;

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
		buscarPublicacion("");
		createTable();
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
		JLabel lblNombreDeUsuario = new JLabel(prefs.get("USERNAME", null)+"!");
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
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(373, 80, 120, 30);
		frmInicioApi.getContentPane().add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscar.setEnabled(false);
				buscarPublicacion(txtBuscador.getText());
				createTable();
			}
		});
		
		String[] columnNames = {"Tipo", "Título", "Precio", "Categoría"};
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, columnNames));
		table.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPanePublicaciones = new JScrollPane(table);
		scrollPanePublicaciones.setBounds(10, 120, 480, 300);
		table.setFillsViewportHeight(true);
		frmInicioApi.getContentPane().add(scrollPanePublicaciones);

	}
	
	private ArrayList<Publicacion> buscarPublicacion(String busqueda) {
		SistemaPublicaciones sp = SistemaPublicaciones.getInstance();
		resultado = (ArrayList<Publicacion>) sp.filtrarPublicaciones(busqueda);
		return resultado;
	}
	
	public void createTable() {
			
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		if (resultado != null) {
			for(Publicacion p : resultado){
				
				String categoria =(p.getArticulo() instanceof Producto) ? "Producto" : "Servicio";
				String tipoPublicacion = (p instanceof Subasta) ? "Subasta" : "Compra inmediata";
				String precio = (p instanceof Subasta) ? Float.toString(((Subasta) p).getPrecioActual()) : Float.toString(p.getPrecio());
				/**
				 * TODO checkear el tema de la subasta
				 */
				model.addRow(new Object[]{
						tipoPublicacion,
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
			        		Publicacion p = resultado.get(row);
			        		
			        		// TODO: change this for Articulo Detail Page
			        		VerPublicacion articuloSeleccionado = new VerPublicacion(p);	
			        		articuloSeleccionado.setVisible(true);
			        		frmInicioApi.dispose();
			            System.out.println("CLICKED "+ p.getArticulo().getNombre());
			        }
			    }
			});
		} else {
			JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
		}
		
		btnBuscar.setEnabled(true);
	}
	
	public void setVisible(boolean isVisible) {
		this.frmInicioApi.setVisible(isVisible);
	}
}
