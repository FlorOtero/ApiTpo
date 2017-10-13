package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.Garantia;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Servicio;
import edu.uade.api.tpo.model.TipoPeriodo;
import edu.uade.api.tpo.model.Usuario;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ModificarProducto {
	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	private String nombreUsuario = prefs.get("USERNAME", null);
	private JFrame frmModificarProducto;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private ArrayList<Publicacion> resultado;
	private JTable tablaBusqueda;
	private JScrollPane scrollPane;
	private JComboBox comboBox;
	private JCheckBox chckbxEfectivo;
	private JCheckBox chckbxTarjetaDeCredito;
	private JCheckBox chckbxTransferenciaBancaria;
	private Publicacion p;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarProducto window = new ModificarProducto();
					window.frmModificarProducto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificarProducto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmModificarProducto = new JFrame();
		frmModificarProducto.getContentPane().setBackground(new Color(255, 255, 224));
		frmModificarProducto.setTitle("Modificar producto");
		frmModificarProducto.setBounds(100, 100, 581, 568);
		frmModificarProducto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarProducto.getContentPane().setLayout(null);
		buscarPublicacion();
		crearTabla();

		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setBounds(34, 173, 88, 16);
		frmModificarProducto.getContentPane().add(lblNombre_1);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 201, 88, 16);
		frmModificarProducto.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(34, 241, 61, 16);
		frmModificarProducto.getContentPane().add(lblPrecio);
		
		JLabel lblFechaDePublicacion = new JLabel("Garantia");
		lblFechaDePublicacion.setBounds(34, 288, 158, 16);
		frmModificarProducto.getContentPane().add(lblFechaDePublicacion);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(34, 332, 88, 16);
		frmModificarProducto.getContentPane().add(lblFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 379, 136, 16);
		frmModificarProducto.getContentPane().add(lblNewLabel);
		
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//producto.setNombre(textField.getText());
			}
		});
		textField.setColumns(10);
		textField.setBounds(202, 162, 139, 23);
		frmModificarProducto.getContentPane().add(textField);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(206, 328, 135, 23);
		frmModificarProducto.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//producto.setDescripcion(textField_2.getText());
			}
		});
		textField_2.setBounds(202, 197, 139, 23);
		frmModificarProducto.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			//	p.setPrecio(Float.parseFloat(textField_4.getText()));
			}
		});
		textField_3.setBounds(202, 237, 139, 23);
		frmModificarProducto.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {


			}
		});
		textField_4.setBounds(202, 284, 139, 23);
		frmModificarProducto.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(202, 375, 158, 23);
		frmModificarProducto.getContentPane().add(chckbxEfectivo);
		
		chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(202, 410, 158, 23);
		frmModificarProducto.getContentPane().add(chckbxTarjetaDeCredito);
		
		chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(202, 446, 158, 23);
		frmModificarProducto.getContentPane().add(chckbxTransferenciaBancaria);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mensual", "Anual"}));
		comboBox.setBounds(376, 286, 95, 23);
		frmModificarProducto.getContentPane().add(comboBox);
		
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: mosificar el producto con los datos ingresados por parametros
				// TODO: se hardcodeo el is del producto a modificar para probar
				Publicacion publicacion = new Publicacion();
				Producto producto = new Producto();
				producto.setId("c4244652-e6e6-47a0-8fc5-e19a221bafd9");
				producto.setNombre(textField.toString());
				producto.setDescripcion(textField_2.toString());
				Garantia garantia = new Garantia();
				TipoPeriodo tipo = (TipoPeriodo) comboBox.getSelectedItem();
				garantia.setTipo(tipo);
				garantia.setCantidad(Integer.parseInt(textField_3.toString()));
				producto.setGarantia(garantia);
				publicacion.setArticulo(producto);
			//	publicacion.setFechaHasta();
				ArrayList<MedioPago> mediosPago = new ArrayList<MedioPago>();
				if (chckbxEfectivo.isSelected()){
					mediosPago.add(MedioPago.EFECTIVO);
				}if (chckbxTarjetaDeCredito.isSelected()){
					mediosPago.add(MedioPago.TARJETA_CREDITO);
				}if (chckbxTransferenciaBancaria.isSelected()){
					mediosPago.add(MedioPago.TRANSFERENCIA_BANCARIA);
				}
				publicacion.setMediosPago(mediosPago);
				publicacion.setPrecio(Float.parseFloat(textField_3.toString()));
				
				
				SistemaPublicaciones.getInstance().modificarPublicacion(publicacion);
				
				JOptionPane.showConfirmDialog(null, "Su producto se ha modificado con exito", "Aviso", JOptionPane.PLAIN_MESSAGE);
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarProducto.dispose();
			}
		});
		btnModificar.setBounds(121, 499, 117, 29);
		frmModificarProducto.getContentPane().add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarProducto.dispose();
			}
		});
		btnCancelar.setBounds(356, 499, 117, 29);
		frmModificarProducto.getContentPane().add(btnCancelar);

	}
	public void setVisible(boolean isVisible) {
		this.frmModificarProducto.setVisible(isVisible);
	}
	
	private ArrayList<Publicacion> buscarPublicacion() {
		SistemaPublicaciones sp = SistemaPublicaciones.getInstance();
		Usuario user= SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);
		resultado = (ArrayList<Publicacion>) user.getPublicaciones();
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
			tablaBusqueda.setBounds(34,40,400, 100);
			scrollPane.setBounds(34,40, 400, 100);
			frmModificarProducto.getContentPane().add(scrollPane);
			tablaBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			        int row = tablaBusqueda.rowAtPoint(evt.getPoint());
			        int col = tablaBusqueda.columnAtPoint(evt.getPoint());
			        if (row >= 0 && col >= 0) {
			        		p = resultado.get(row);		
			        		CargarDatos();
			        		// TODO: change this for Articulo Detail Page
			            System.out.println("CLICKED "+ p.getArticulo().getNombre());
			        }
			    }
			});
		} else {
			JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
		}
	}
	private void CargarDatos() {
		Producto producto = (Producto) p.getArticulo();
		textField.setText(producto.getNombre());
		textField_2.setText(producto.getDescripcion());
		textField_3.setText(String.valueOf(p.getPrecio()));
		textField_4.setText(String.valueOf(producto.getGarantia().getCantidad()));
		textField_1.setText(p.getFechaHasta().toString());
		System.out.println(comboBox.getSelectedItem());
		String tipo = producto.getGarantia().getTipo().toString();
		String selected = comboBox.getSelectedItem().toString();
		int index = comboBox.getSelectedIndex();
		if(tipo == selected){
			if(index == 0) {
				index = 1;
			} else {
				index = 0;
			}
		}
		comboBox.setSelectedIndex(index);
	//	comboBox.setSelectedItem(producto.getGarantia().getTipo());
		for(MedioPago mp: p.getMediosPago()) {		
			// TODO: get from MedioPago directly, without strings switch
			switch(mp.toString()) {
				case "EFECTIVO": 				
					chckbxEfectivo.setSelected(true);
					break;
				case "TRANSFERENCIA_BANCARIA":
					chckbxTarjetaDeCredito.setSelected(true);
					break;
				case "TARJETA_CREDITO": 	
					chckbxTransferenciaBancaria.setSelected(true);
			}
		}
		
		
		
	}
}
