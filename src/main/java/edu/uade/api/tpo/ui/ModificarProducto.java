package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;

import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.Garantia;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.SistemaPublicaciones;
import edu.uade.api.tpo.model.TipoPeriodo;

public class ModificarProducto {

	private JFrame frmModificarProducto;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;

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
		frmModificarProducto.setBounds(100, 100, 541, 490);
		frmModificarProducto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarProducto.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre Producto");
		lblNombre.setBounds(34, 25, 117, 16);
		frmModificarProducto.getContentPane().add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 121, 88, 16);
		frmModificarProducto.getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(34, 152, 61, 16);
		frmModificarProducto.getContentPane().add(lblPrecio);
		
		JLabel lblFechaDePublicacion = new JLabel("Garantia");
		lblFechaDePublicacion.setBounds(34, 180, 158, 16);
		frmModificarProducto.getContentPane().add(lblFechaDePublicacion);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(34, 211, 88, 16);
		frmModificarProducto.getContentPane().add(lblFechaHasta);
		
		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 276, 136, 16);
		frmModificarProducto.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(214, 19, 135, 23);
		frmModificarProducto.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(202, 116, 139, 23);
		frmModificarProducto.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(174, 273, 158, 23);
		frmModificarProducto.getContentPane().add(chckbxEfectivo);
		
		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(174, 305, 158, 23);
		frmModificarProducto.getContentPane().add(chckbxTarjetaDeCredito);
		
		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(174, 337, 158, 23);
		frmModificarProducto.getContentPane().add(chckbxTransferenciaBancaria);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Mensual", "Anual"}));
		comboBox.setBounds(378, 178, 95, 23);
		frmModificarProducto.getContentPane().add(comboBox);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showConfirmDialog(null, "Su producto se ha modificado con exito", "Aviso", JOptionPane.PLAIN_MESSAGE);
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarProducto.dispose();
			}
		});
		btnBuscar.setBounds(145, 54, 89, 23);
		frmModificarProducto.getContentPane().add(btnBuscar);
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Publicacion publicacion = new Publicacion();
				Producto producto = new Producto();
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
		btnModificar.setBounds(123, 377, 117, 29);
		frmModificarProducto.getContentPane().add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setVisible(true);
				frmModificarProducto.dispose();
			}
		});
		btnCancelar.setBounds(293, 377, 117, 29);
		frmModificarProducto.getContentPane().add(btnCancelar);
		
		textField_3 = new JTextField();
		textField_3.setBounds(202, 146, 139, 23);
		frmModificarProducto.getContentPane().add(textField_3);
		textField_3.setColumns(10);
	
		
		textField_4 = new JTextField();
		textField_4.setBounds(202, 177, 139, 23);
		frmModificarProducto.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setBounds(34, 93, 88, 16);
		frmModificarProducto.getContentPane().add(lblNombre_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(202, 88, 139, 23);
		frmModificarProducto.getContentPane().add(textField);
		

	}
	public void setVisible(boolean isVisible) {
		this.frmModificarProducto.setVisible(isVisible);
	}
}
