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
import com.toedter.calendar.JCalendar;

import edu.uade.api.tpo.model.Garantia;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.model.TipoPeriodo;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;

public class NuevoProducto {

	private JFrame frmNuevoProducto;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JCalendar calendar;
	private JTextField textField_3;
	private JTextField JTextField_path;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoProducto window = new NuevoProducto();
					window.frmNuevoProducto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevoProducto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevoProducto = new JFrame();
		frmNuevoProducto.getContentPane().setBackground(new Color(255, 255, 224));
		frmNuevoProducto.setTitle("Nuevo producto");
		frmNuevoProducto.setBounds(100, 100, 529, 434);
		frmNuevoProducto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNuevoProducto.getContentPane().setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 21, 61, 16);
		frmNuevoProducto.getContentPane().add(lblNombre);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(34, 49, 88, 16);
		frmNuevoProducto.getContentPane().add(lblDescripcion);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(34, 77, 61, 16);
		frmNuevoProducto.getContentPane().add(lblPrecio);

		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setBounds(396, 147, 88, 16);
		frmNuevoProducto.getContentPane().add(lblFechaHasta);

		JLabel lblNewLabel = new JLabel("Formas de pago");
		lblNewLabel.setBounds(34, 261, 136, 16);
		frmNuevoProducto.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(221, 16, 130, 26);
		frmNuevoProducto.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(221, 44, 130, 26);
		frmNuevoProducto.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(221, 72, 130, 26);
		frmNuevoProducto.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setBounds(34, 289, 128, 23);
		frmNuevoProducto.getContentPane().add(chckbxEfectivo);

		JCheckBox chckbxTarjetaDeCredito = new JCheckBox("Tarjeta de credito");
		chckbxTarjetaDeCredito.setBounds(34, 314, 142, 23);
		frmNuevoProducto.getContentPane().add(chckbxTarjetaDeCredito);

		JCheckBox chckbxTransferenciaBancaria = new JCheckBox("Transferencia bancaria");
		chckbxTransferenciaBancaria.setBounds(34, 342, 197, 23);
		frmNuevoProducto.getContentPane().add(chckbxTransferenciaBancaria);

		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			private List<String> imagenes;

			public void actionPerformed(ActionEvent e) {
				Publicacion publi = new Publicacion();
				Producto prod = new Producto();
				prod.setNombre(textField.getText());
				prod.setDescripcion(textField_1.getText());
				prod.fromImagesTokenized(JTextField_path.toString());
				publi.setPrecio(Float.parseFloat(textField_2.getText()));

				publi.setFechaHasta(calendar.getDate());

				Garantia garantia = new Garantia();
				garantia.setCantidad(Integer.parseInt(textField_3.getText()));
		        garantia.setTipo(TipoPeriodo.ANUAL);
				prod.setGarantia(garantia);
				
				prod.fromImagesTokenized("images");
				publi.setArticulo(prod);
				List<MedioPago> mediosPagos = new ArrayList();

				if (chckbxEfectivo.isSelected()) {
					mediosPagos.add(MedioPago.EFECTIVO);
				}
				if (chckbxTarjetaDeCredito.isSelected()) {

					mediosPagos.add(MedioPago.TARJETA_CREDITO);
				}
				if (chckbxTransferenciaBancaria.isSelected()) {
					mediosPagos.add(MedioPago.TRANSFERENCIA_BANCARIA);
				}

				SistemaPublicaciones.getInstance().altaPublicacion("9cd3b14b-6737-45cb-9436-02e78892f9c7",
						publi.getFechaHasta(), publi.getPrecio(), publi.getArticulo(), mediosPagos);
				JOptionPane.showMessageDialog(null, "Se ha creado su Producto con exito", "Aviso",
						JOptionPane.PLAIN_MESSAGE);

			}
		});
		btnPublicar.setBounds(114, 377, 117, 29);
		frmNuevoProducto.getContentPane().add(btnPublicar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaPublicacion nuevaP = new NuevaPublicacion();
				nuevaP.setVisible(true);
				frmNuevoProducto.dispose();
			}
		});
		btnCancelar.setBounds(267, 377, 117, 29);
		frmNuevoProducto.getContentPane().add(btnCancelar);

		calendar = new JCalendar();

		calendar.setBounds(231, 166, 198, 153);

		calendar.setBounds(325, 175, 198, 153);
		calendar.setWeekOfYearVisible(false);
		calendar.setTodayButtonVisible(true);

		frmNuevoProducto.getContentPane().add(calendar);

		JButton btnAgregar = new JButton("Agregar ");
		btnAgregar.setBounds(396, 380, 89, 23);
		frmNuevoProducto.getContentPane().add(btnAgregar);
		
		JLabel lblGarantia = new JLabel("Garantia");
		lblGarantia.setBounds(34, 105, 61, 16);
		frmNuevoProducto.getContentPane().add(lblGarantia);
		
		textField_3 = new JTextField();
		textField_3.setBounds(221, 100, 130, 26);
		frmNuevoProducto.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ANUAL", "MENSUAL"}));
		comboBox.setBounds(380, 101, 127, 27);
		frmNuevoProducto.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Subir imagen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				JTextField_path.setText(filename);
					
			}
		});
		btnNewButton.setBounds(26, 186, 117, 29);
		frmNuevoProducto.getContentPane().add(btnNewButton);
		
		JTextField_path = new JTextField();
		JTextField_path.setBounds(149, 147, 130, 105);
		frmNuevoProducto.getContentPane().add(JTextField_path);
		JTextField_path.setColumns(10);
	}

	public void setVisible(boolean isVisible) {
		this.frmNuevoProducto.setVisible(isVisible);
	}
}
