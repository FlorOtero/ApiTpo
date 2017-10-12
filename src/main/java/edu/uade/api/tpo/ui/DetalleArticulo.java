package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.SistemaUsuarios;
import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.model.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;

public class DetalleArticulo {
	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	private JFrame frmArticulo;
	private Articulo articulo;
	private Publicacion publicacion;
	private JButton btnOfertar;
	private Usuario user;
	/**
	 * Launch the application.
	 */
	public DetalleArticulo(Publicacion p){
		this.publicacion= p;
		this.articulo=p.getArticulo();
		String nombreUsuario = prefs.get("USERNAME", null);
		user= SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);
		initialize();
	}

	private void initialize() {
		frmArticulo = new JFrame();
		frmArticulo.setTitle("Articulo");
		frmArticulo.getContentPane().setBackground(new Color(255, 255, 224));
		frmArticulo.setBounds(100, 100, 418, 446);
		frmArticulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmArticulo.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(23, 33, 61, 16);
		
		frmArticulo.getContentPane().add(lblNombre);
		
		JLabel lblNewLabel = new JLabel(articulo.getNombre());
		lblNewLabel.setBounds(109, 33, 134, 16);
		frmArticulo.getContentPane().add(lblNewLabel);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(23, 84, 88, 16);
		frmArticulo.getContentPane().add(lblDescripcion);
		
		JLabel lblImagenes = new JLabel("Imagenes");
		lblImagenes.setBounds(23, 277, 61, 16);
		frmArticulo.getContentPane().add(lblImagenes);
		
		JLabel lblNewLabel_1 = new JLabel(articulo.getDescripcion());
		lblNewLabel_1.setBounds(109, 69, 168, 47);
		frmArticulo.getContentPane().add(lblNewLabel_1);
		
		btnOfertar = new JButton("Comprar");
		btnOfertar.setBounds(63, 389, 117, 29);
		frmArticulo.getContentPane().add(btnOfertar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCancelar.setBounds(242, 389, 117, 29);
		frmArticulo.getContentPane().add(btnCancelar);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(23, 127, 61, 16);
		frmArticulo.getContentPane().add(lblPrecio);
		
		JLabel lblMediosDePago = new JLabel("Medios de pago disponibles ");
		lblMediosDePago.setBounds(23, 179, 203, 16);
		frmArticulo.getContentPane().add(lblMediosDePago);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(109, 127, 61, 16);
		lblNewLabel_2.setText(String.valueOf(publicacion.getPrecio()));
		frmArticulo.getContentPane().add(lblNewLabel_2);
		ButtonGroup bg = new ButtonGroup();
		int mpHeight=180;
		for(MedioPago mp: publicacion.getMediosPago()) {
			JRadioButton b= new JRadioButton(mp.getId());
			b.setBounds(254, mpHeight, 61, 16);
			bg.add(b);
			frmArticulo.add(b);
			mpHeight+=30;			
		}
		
		if(publicacion instanceof Subasta) {
			articuloSubasta();
		}else{
			articuloPublicacion();
		} 
	}
	public void setVisible(boolean isVisible) {
		this.frmArticulo.setVisible(isVisible);
	}
	public void articuloSubasta(){
		//Precio de la ultima oferta. 
		btnOfertar.setName("Ofertar");
		JLabel lblOferta = new JLabel("Su oferta");
		lblOferta.setBounds(23, 327, 51, 16);
		frmArticulo.getContentPane().add(lblOferta);
		JTextField txtOferta= new JTextField();
		txtOferta.setBounds(166, 326, 61, 16);
		frmArticulo.getContentPane().add(txtOferta);
		btnOfertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				publicacion.setPrecio(Float.parseFloat(txtOferta.getText()));

			}
		});
	}
	public void articuloPublicacion(){
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(109, 127, 61, 16);
		lblNewLabel_2.setText(String.valueOf(publicacion.getPrecio()));
		frmArticulo.getContentPane().add(lblNewLabel_2);
		if(articulo instanceof Producto) {
			//Mostras los datos del producto
			} else {
			// Mostras los datos del servicio
			}
	}
}
