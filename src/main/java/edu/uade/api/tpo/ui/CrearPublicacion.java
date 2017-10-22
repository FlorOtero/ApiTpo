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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.Garantia;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.TipoPeriodo;
import edu.uade.api.tpo.model.Usuario;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;

public class CrearPublicacion {

	private JFrame frmCrearPublicacion;
	private JTextField nombreField;
	private JTextField descripcionField;
	private JTextField precioField;
	private JTextField cantidadField;
	private JTextField filepathField;
	JCheckBox pagoEfectivoCheckbox;
	JCheckBox pagoTarjetaCreditoCheckbox;
	JCheckBox pagoTransferenciaCheckbox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPublicacion window = new CrearPublicacion();
					window.frmCrearPublicacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearPublicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrearPublicacion = new JFrame();
		frmCrearPublicacion.getContentPane().setBackground(new Color(255, 255, 224));
		frmCrearPublicacion.setTitle("Nueva Publicacion");
		frmCrearPublicacion.setBounds(100, 100, 529, 434);
		frmCrearPublicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrearPublicacion.getContentPane().setLayout(null);

		
		////////////
		// Labels //
		////////////
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(34, 21, 61, 16);
		frmCrearPublicacion.getContentPane().add(labelNombre);

		JLabel labelDescripcion = new JLabel("Descripcion");
		labelDescripcion.setBounds(34, 49, 88, 16);
		frmCrearPublicacion.getContentPane().add(labelDescripcion);

		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(34, 77, 61, 16);
		frmCrearPublicacion.getContentPane().add(labelPrecio);

		JLabel labelformasDePago = new JLabel("Formas de pago");
		labelformasDePago.setBounds(34, 261, 136, 16);
		frmCrearPublicacion.getContentPane().add(labelformasDePago);
		
		JLabel labelGarantia = new JLabel("Garantia");
		labelGarantia.setBounds(34, 105, 61, 16);
		frmCrearPublicacion.getContentPane().add(labelGarantia);
		
		
		////////////
		// Fields //
		////////////
		
		nombreField = new JTextField();
		nombreField.setBounds(221, 16, 130, 26);
		frmCrearPublicacion.getContentPane().add(nombreField);
		nombreField.setColumns(10);

		descripcionField = new JTextField();
		descripcionField.setBounds(221, 44, 130, 26);
		frmCrearPublicacion.getContentPane().add(descripcionField);
		descripcionField.setColumns(10);

		precioField = new JTextField();
		precioField.setBounds(221, 72, 130, 26);
		frmCrearPublicacion.getContentPane().add(precioField);
		precioField.setColumns(10);
		
		cantidadField = new JTextField();
		cantidadField.setBounds(221, 100, 130, 26);
		frmCrearPublicacion.getContentPane().add(cantidadField);
		cantidadField.setColumns(10);

		filepathField = new JTextField();
		filepathField.setBounds(149, 147, 130, 105);
		filepathField.setColumns(10);
		frmCrearPublicacion.getContentPane().add(filepathField);

		pagoEfectivoCheckbox = new JCheckBox("Efectivo");
		pagoEfectivoCheckbox.setBounds(34, 289, 128, 23);
		frmCrearPublicacion.getContentPane().add(pagoEfectivoCheckbox);

		pagoTarjetaCreditoCheckbox = new JCheckBox("Tarjeta de credito");
		pagoTarjetaCreditoCheckbox.setBounds(34, 314, 142, 23);
		frmCrearPublicacion.getContentPane().add(pagoTarjetaCreditoCheckbox);

		pagoTransferenciaCheckbox = new JCheckBox("Transferencia bancaria");
		pagoTransferenciaCheckbox.setBounds(34, 342, 197, 23);
		frmCrearPublicacion.getContentPane().add(pagoTransferenciaCheckbox);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"ANUAL", "MENSUAL"}));
		comboBox.setBounds(380, 101, 127, 27);
		frmCrearPublicacion.getContentPane().add(comboBox);

		
		/////////////
		// Buttons //
		/////////////
		
		JButton subirImagenButton = new JButton("Subir imagen");
		subirImagenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				File f = fileChooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				filepathField.setText(filename);
			}
		});
		subirImagenButton.setBounds(26, 186, 117, 29);
		frmCrearPublicacion.getContentPane().add(subirImagenButton);

		JButton publicarButton = new JButton("Publicar");
		publicarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicar();
			}
		});
		publicarButton.setBounds(396, 380, 89, 23);
		frmCrearPublicacion.getContentPane().add(publicarButton);

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goMenuPrincipal();
			}
		});
		cancelarButton.setBounds(267, 377, 117, 29);
		frmCrearPublicacion.getContentPane().add(cancelarButton);
	}
	
	private void publicar() {
		if (validateForm()) {
			List<MedioPago> mediosPagos = getMediosPago();
			Articulo articulo = crearProducto();
			Publicacion publicacion = new Publicacion();
			publicacion.setPrecio(Float.parseFloat(precioField.getText()));
			publicacion.setArticulo(articulo);
			
			Usuario user = SistemaUsuarios.getUsuarioLoggeado();

			SistemaPublicaciones.getInstance().altaPublicacion(user.getId(), publicacion.getPrecio(), publicacion.getArticulo(), mediosPagos);
			JOptionPane.showMessageDialog(null, "¡Tu publicacion ha sido creada!", "Operacion Exitosa", JOptionPane.PLAIN_MESSAGE);
			goMenuPrincipal();
		} else {
			JOptionPane.showMessageDialog(null, "Aun tienes campos que completar", "Formulario Incompleto", JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	private Producto crearProducto() {
		Producto producto = new Producto();
		producto.setNombre(nombreField.getText());
		producto.setDescripcion(descripcionField.getText());
		producto.fromImagesTokenized(filepathField.toString());
		
		Garantia garantia = new Garantia();
		garantia.setCantidad(Integer.parseInt(cantidadField.getText()));
        garantia.setTipo(TipoPeriodo.ANUAL);
        
		producto.setGarantia(garantia);
		return producto;
	}
	
	private void goMenuPrincipal() {
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.setVisible(true);
		frmCrearPublicacion.dispose();
	}

	public void setVisible(boolean isVisible) {
		this.frmCrearPublicacion.setVisible(isVisible);
	}
	
	private List<MedioPago> getMediosPago() {
		List<MedioPago> mediosPago = new ArrayList<MedioPago>();

		if (pagoEfectivoCheckbox.isSelected()) {
			mediosPago.add(MedioPago.EFECTIVO);
		}
		if (pagoTarjetaCreditoCheckbox.isSelected()) {
			mediosPago.add(MedioPago.TARJETA_CREDITO);
		}
		if (pagoTransferenciaCheckbox.isSelected()) {
			mediosPago.add(MedioPago.TRANSFERENCIA_BANCARIA);
		}
		
		return mediosPago;
	}
	
	private boolean validateForm() {
		// TODO: implement form validation
		return true;
	}
}
