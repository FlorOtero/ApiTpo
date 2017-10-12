package edu.uade.api.tpo.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Servicio;
import edu.uade.api.tpo.model.SistemaPublicaciones;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MenuPrincipal {

	JLabel lblIngresarProducto;
	private JFrame frmMenuPrincipal;
	private JTextField buscarField;
	private ArrayList<Publicacion> resultado;
	private JTable tablaBusqueda;
	JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.frmMenuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setTitle("Menu");
		frmMenuPrincipal.getContentPane().setBackground(new Color(255, 255, 224));
		frmMenuPrincipal.getContentPane().setLayout(null);
		frmMenuPrincipal.setBounds(100, 100, 544, 565);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Content
		
		lblIngresarProducto = new JLabel("Buscar Publicaciones");
		lblIngresarProducto.setBounds(40, 40, 180, 16);
		frmMenuPrincipal.getContentPane().add(lblIngresarProducto);
		
		buscarField = new JTextField();
		buscarField.setBounds(40, 66, 273, 26);
		frmMenuPrincipal.getContentPane().add(buscarField);
		buscarField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(330, 66, 117, 29);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPublicacion(buscarField.getText());
				crearTabla();
			}
		});
		frmMenuPrincipal.getContentPane().add(btnBuscar);
		
		// Menu
		
		JMenuBar menuBar = new JMenuBar();
		frmMenuPrincipal.setJMenuBar(menuBar);
		
		JMenu mnMiCuenta = new JMenu("Mi cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmModificarCuenta = new JMenuItem("Modificar cuenta");
		mntmModificarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuario modificarUser = new ModificarUsuario();
				modificarUser.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnMiCuenta.add(mntmModificarCuenta);

		
		JMenuItem mntmEstadoCuentaCorriente = new JMenuItem("Estado cuenta corriente");
		mntmEstadoCuentaCorriente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EstadoCuentaCorriente estadoCuenta = new EstadoCuentaCorriente();
				estadoCuenta.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnMiCuenta.add(mntmEstadoCuentaCorriente);
		
		JMenuItem mntmReputacion = new JMenuItem("Reputacion");
		mntmReputacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReputacionCalificaciones reputacionUser = new ReputacionCalificaciones();
				reputacionUser.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnMiCuenta.add(mntmReputacion);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesion");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpcionIngreso opcion = new OpcionIngreso();
				opcion.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnMiCuenta.add(mntmCerrarSesion);
		
		JMenu mnPublicaciones = new JMenu("Publicaciones");
		menuBar.add(mnPublicaciones);
		
		JMenuItem mntmNuevaPublicacin = new JMenuItem("Nueva publicación");
		mntmNuevaPublicacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPublicacion nuevaPublicacion = new NuevaPublicacion();
				nuevaPublicacion.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnPublicaciones.add(mntmNuevaPublicacin);
		
		JMenuItem mntmModificarPublicacin = new JMenuItem("Modificar publicación");
		mntmModificarPublicacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificacionPublicacion modificarPublicacion = new ModificacionPublicacion();
				modificarPublicacion.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnPublicaciones.add(mntmModificarPublicacin);
		
		JMenuItem mntmEliminarPublicacion = new JMenuItem("Eliminar publicación");
		mntmEliminarPublicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaPublicacion baja = new BajaPublicacion();
				baja.setVisible(true);
				frmMenuPrincipal.dispose();
			}
		});
		mnPublicaciones.add(mntmEliminarPublicacion);
	}

	public void setVisible(boolean isVisible) {
		this.frmMenuPrincipal.setVisible(isVisible);
	}
	
	private ArrayList<Publicacion> buscarPublicacion(String busqueda) {
		SistemaPublicaciones sp = SistemaPublicaciones.getInstance();
		resultado = (ArrayList<Publicacion>) sp.filtrarPublicaciones(busqueda);
		return resultado;
	}
	
	public void crearTabla() {
		String[] columnNames = {"Nombre Articulo","Precio", "Tipo"};		
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		
		if (resultado != null) {
			for(Publicacion p : resultado){
				String tipo = "";
				if (p.getArticulo() instanceof Producto) {
					tipo = "Producto";
				} else if (p.getArticulo() instanceof Servicio) {
					tipo = "Servicio";
				}
				model.addRow(new Object[]{ 
					p.getArticulo().getNombre(), 
					"$"+p.getPrecio(),
					tipo
				});
			}				
			tablaBusqueda = new JTable(model);
			scrollPane = new JScrollPane(tablaBusqueda);
			tablaBusqueda.setBounds(50, 194, 400, 243);
			scrollPane.setBounds(50, 194, 400, 243);
			frmMenuPrincipal.getContentPane().add(scrollPane);
			tablaBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			        int row = tablaBusqueda.rowAtPoint(evt.getPoint());
			        int col = tablaBusqueda.columnAtPoint(evt.getPoint());
			        if (row >= 0 && col >= 0) {
			        		Publicacion p = resultado.get(row);
			        		
			        		// TODO: change this for Articulo Detail Page
			        		DetalleArticulo articuloSeleccionado= new DetalleArticulo(p);	
			        		articuloSeleccionado.setVisible(true);
			        		frmMenuPrincipal.dispose();
			            System.out.println("CLICKED "+ p.getArticulo().getNombre());
			        }
			    }
			});
		} else {
			JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
		}
	}
	
	
}
