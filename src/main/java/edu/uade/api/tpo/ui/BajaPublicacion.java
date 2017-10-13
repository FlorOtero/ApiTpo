package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Servicio;
import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.Usuario;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;

public class BajaPublicacion {

	private JFrame frmEliminarPublicacion;
	private ArrayList<Publicacion> resultadoUser;
	private JTable tablaBusqueda;
	JScrollPane scrollPane;
	private JTextField buscarField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaPublicacion window = new BajaPublicacion();
					window.frmEliminarPublicacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BajaPublicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEliminarPublicacion = new JFrame();
		frmEliminarPublicacion.setTitle("Eliminar Publicacion");
		frmEliminarPublicacion.getContentPane().setBackground(new Color(255, 255, 224));
		frmEliminarPublicacion.getContentPane().setLayout(null);
		
		JLabel lblIngresarProducto = new JLabel("Buscar Publicaciones");
		lblIngresarProducto.setBounds(40, 40, 180, 16);
		frmEliminarPublicacion.getContentPane().add(lblIngresarProducto);
		
		buscarField = new JTextField();
		buscarField.setBounds(40, 66, 273, 26);
		frmEliminarPublicacion.getContentPane().add(buscarField);
		buscarField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(330, 66, 117, 29);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPublicacion(buscarField.getText());
				crearTabla();
			}
		});
		frmEliminarPublicacion.getContentPane().add(btnBuscar);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = 0;
				Publicacion p = resultadoUser.get(row);
				SistemaPublicaciones.getInstance().eliminarPublicacion(p);
				int rta= JOptionPane.showConfirmDialog(null,"Esta seguro de que quiere eliminar la publicación?", "Aviso", JOptionPane.OK_CANCEL_OPTION);
				if (JOptionPane.OK_OPTION == rta) {
					JOptionPane.showConfirmDialog(null,"Su publicacion se ha eliminado con exito","Confirmacion",JOptionPane.PLAIN_MESSAGE);
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					frmEliminarPublicacion.dispose();
				}
				
			}
		});
		btnNewButton.setBounds(59, 380, 117, 29);
		frmEliminarPublicacion.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta= JOptionPane.showConfirmDialog(null,"Seguro que quiere salir?", "Aviso", JOptionPane.OK_CANCEL_OPTION);
				if (JOptionPane.OK_OPTION == rta) {
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					frmEliminarPublicacion.dispose();
				}
			}
		});
		btnNewButton_1.setBounds(241, 380, 117, 29);
		frmEliminarPublicacion.getContentPane().add(btnNewButton_1);
		frmEliminarPublicacion.setBounds(100, 100, 450, 437);
		frmEliminarPublicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setVisible(boolean isVisible) {
		this.frmEliminarPublicacion.setVisible(isVisible);
	}
	
	private ArrayList<Publicacion> buscarPublicacion(String busqueda) {
		 Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
		 //String nombreUsuario = prefs.get("USERNAME", null);
         Usuario user = SistemaUsuarios.getInstance().buscarUsuario("flor");
		 resultadoUser =  (ArrayList<Publicacion>) user.getPublicaciones();
		return resultadoUser;
	}
		

	public void crearTabla() {
		String[] columnNames = {"Nombre Articulo","Precio", "Tipo"};		
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		
	
		if (resultadoUser != null) {
			for(Publicacion p : resultadoUser){
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
			frmEliminarPublicacion.getContentPane().add(scrollPane);
			tablaBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			        int row = tablaBusqueda.rowAtPoint(evt.getPoint());
			        int col = tablaBusqueda.columnAtPoint(evt.getPoint());
			        if (row >= 0 && col >= 0) {
			        		Publicacion p = resultadoUser.get(row);
			        		
			        		// TODO: change this for Articulo Detail Page
			            System.out.println("CLICKED "+ p.getArticulo().getNombre());
			        }
			    }
			});
		} else {
			JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
		}
	}
	
	
}

