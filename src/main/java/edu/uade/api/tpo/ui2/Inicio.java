package edu.uade.api.tpo.ui2;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Subasta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class Inicio {

	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	private JFrame frmInicioApi;
	private JTextField txtBuscador;
	private JTable table;
	private List<Publicacion> resultado;
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
		table.setModel(new DefaultTableModel(new Object[][] {}, columnNames) {
			 @Override
			    public Class<?> getColumnClass(int column) {
			        switch(column) {
			            case 0: return ImageIcon.class;
			            default: return Object.class;
			        }
			    }
		});
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(20);
		table.getColumnModel().getColumn(0).setMaxWidth(32);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);

		JScrollPane scrollPanePublicaciones = new JScrollPane(table);
		scrollPanePublicaciones.setBounds(10, 120, 480, 300);
		table.setFillsViewportHeight(true);
		frmInicioApi.getContentPane().add(scrollPanePublicaciones);

	}
	
	private List<Publicacion> buscarPublicacion(String busqueda) {
		SistemaPublicaciones sp = SistemaPublicaciones.getInstance();
		this.resultado = new ArrayList<>(sp.filtrarPublicaciones(busqueda));
		return resultado;
	}
	
	public void createTable() {
			
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		if (resultado != null) {
			//JLabel tipoPub = new JLabel("");
			for(Publicacion p : resultado){
	
				String categoria =(p.getArticulo() instanceof Producto) ? "Producto" : "Servicio";
				String tipoPublicacion = (p instanceof Subasta) ? "subasta-16.png" : "compra-inmediata-16.png";
				String precio = Float.toString(p.getPrecio());
				ImageIcon tipoPub = new ImageIcon("src/main/resources/"+tipoPublicacion);
				/**
				 * TODO checkear el tema de la subasta
				 */
				model.addRow(new Object[]{
						tipoPub,
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
