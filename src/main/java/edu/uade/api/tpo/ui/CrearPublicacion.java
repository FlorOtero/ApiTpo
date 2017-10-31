package edu.uade.api.tpo.ui;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CrearPublicacion {

	private JFrame crearPublicacionFrame;
	private JTextField nombreField;
	private JTextField descripcionField;
	private JTextField precioField;
	private JTextField cantidadField;
	private JTextField filepathField;
	private JCheckBox pagoEfectivoCheckbox;
	private JCheckBox pagoTarjetaCreditoCheckbox;
	private JCheckBox pagoTransferenciaCheckbox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPublicacion window = new CrearPublicacion();
					window.crearPublicacionFrame.setVisible(true);
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
		crearPublicacionFrame = new JFrame();
		crearPublicacionFrame.getContentPane().setBackground(new Color(255, 255, 224));
		crearPublicacionFrame.setTitle("Nueva Publicacion");
		crearPublicacionFrame.setBounds(100, 100, 529, 434);
		crearPublicacionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crearPublicacionFrame.getContentPane().setLayout(null);

		
		////////////
		// Labels //
		////////////
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(34, 21, 61, 16);
		crearPublicacionFrame.getContentPane().add(labelNombre);

		JLabel labelDescripcion = new JLabel("Descripcion");
		labelDescripcion.setBounds(34, 49, 88, 16);
		crearPublicacionFrame.getContentPane().add(labelDescripcion);

		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(34, 77, 61, 16);
		crearPublicacionFrame.getContentPane().add(labelPrecio);

		JLabel labelformasDePago = new JLabel("Formas de pago");
		labelformasDePago.setBounds(34, 261, 136, 16);
		crearPublicacionFrame.getContentPane().add(labelformasDePago);
		
		JLabel labelGarantia = new JLabel("Garantia");
		labelGarantia.setBounds(34, 105, 61, 16);
		crearPublicacionFrame.getContentPane().add(labelGarantia);
		
		
		////////////
		// Fields //
		////////////
		
		nombreField = new JTextField();
		nombreField.setBounds(221, 16, 130, 26);
		crearPublicacionFrame.getContentPane().add(nombreField);
		nombreField.setColumns(10);

		descripcionField = new JTextField();
		descripcionField.setBounds(221, 44, 130, 26);
		crearPublicacionFrame.getContentPane().add(descripcionField);
		descripcionField.setColumns(10);

		precioField = new JTextField();
		precioField.setBounds(221, 72, 130, 26);
		crearPublicacionFrame.getContentPane().add(precioField);
		precioField.setColumns(10);
		
		cantidadField = new JTextField();
		cantidadField.setBounds(221, 100, 130, 26);
		crearPublicacionFrame.getContentPane().add(cantidadField);
		cantidadField.setColumns(10);

		filepathField = new JTextField();
		filepathField.setBounds(149, 147, 130, 105);
		filepathField.setColumns(10);
		crearPublicacionFrame.getContentPane().add(filepathField);

		pagoEfectivoCheckbox = new JCheckBox("Efectivo");
		pagoEfectivoCheckbox.setBounds(34, 289, 128, 23);
		crearPublicacionFrame.getContentPane().add(pagoEfectivoCheckbox);

		pagoTarjetaCreditoCheckbox = new JCheckBox("Tarjeta de credito");
		pagoTarjetaCreditoCheckbox.setBounds(34, 314, 142, 23);
		crearPublicacionFrame.getContentPane().add(pagoTarjetaCreditoCheckbox);

		pagoTransferenciaCheckbox = new JCheckBox("Transferencia bancaria");
		pagoTransferenciaCheckbox.setBounds(34, 342, 197, 23);
		crearPublicacionFrame.getContentPane().add(pagoTransferenciaCheckbox);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"ANUAL", "MENSUAL"}));
		comboBox.setBounds(380, 101, 127, 27);
		crearPublicacionFrame.getContentPane().add(comboBox);

		
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
		crearPublicacionFrame.getContentPane().add(subirImagenButton);

		JButton publicarButton = new JButton("Publicar");
		publicarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicar();
			}
		});
		publicarButton.setBounds(396, 380, 89, 23);
		crearPublicacionFrame.getContentPane().add(publicarButton);

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goMenuPrincipal();
			}
		});
		cancelarButton.setBounds(267, 377, 117, 29);
		crearPublicacionFrame.getContentPane().add(cancelarButton);
	}
	
	private void publicar() {
		if (validateForm()) {
			List<MedioPago> mediosPagos = getMediosPago();
			Articulo articulo = crearProducto();
			Publicacion publicacion = new Publicacion();
			publicacion.setPrecio(Float.parseFloat(precioField.getText()));
			publicacion.setArticulo(articulo);
			
			Usuario user = SistemaUsuarios.getInstance().getUsuarioActivo();

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
		crearPublicacionFrame.dispose();
	}

	public void setVisible(boolean isVisible) {
		this.crearPublicacionFrame.setVisible(isVisible);
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
