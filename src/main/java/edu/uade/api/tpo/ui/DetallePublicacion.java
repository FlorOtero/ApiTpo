package edu.uade.api.tpo.ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;

import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.SistemaUsuarios;
import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.model.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;

public class DetallePublicacion {
	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);
	private JFrame frmDetallePublicacion;
	private MenuPrincipal frmMenuPrincipal;
	private Articulo articulo;
	private Publicacion publicacion;
	private JButton btnOfertar;
	private Usuario user;
	private ButtonGroup bg;
	
	/**
	 * Launch the application.
	 */
	public DetallePublicacion(Publicacion p){
		this.publicacion= p;
		this.articulo=p.getArticulo();
		String nombreUsuario = prefs.get("USERNAME", null);
		user= SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);
		initialize();
	}

	private void initialize() {
		frmDetallePublicacion = new JFrame();
		frmDetallePublicacion.setTitle("Articulo");
		frmDetallePublicacion.getContentPane().setBackground(new Color(255, 255, 224));
		frmDetallePublicacion.setBounds(100, 100, 418, 446);
		frmDetallePublicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDetallePublicacion.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(articulo.getNombre());
		lblNewLabel.setBounds(23, 33, 400, 30);
		lblNewLabel.setFont(new Font("", Font.PLAIN, 18));
		frmDetallePublicacion.getContentPane().add(lblNewLabel);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(23, 84, 88, 16);
		frmDetallePublicacion.getContentPane().add(lblDescripcion);
		
		JLabel lblImagenes = new JLabel("Imagenes");
		lblImagenes.setBounds(23, 277, 61, 16);
		frmDetallePublicacion.getContentPane().add(lblImagenes);
		
		JLabel lblNewLabel_1 = new JLabel(articulo.getDescripcion());
		lblNewLabel_1.setBounds(109, 69, 168, 47);
		frmDetallePublicacion.getContentPane().add(lblNewLabel_1);
		
		btnOfertar = new JButton("Comprar");
		btnOfertar.setBounds(63, 389, 117, 29);
		frmDetallePublicacion.getContentPane().add(btnOfertar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenuPrincipal = new MenuPrincipal();	
				frmMenuPrincipal.setVisible(true);
				frmDetallePublicacion.dispose();
	        		
			}
		});
		btnCancelar.setBounds(242, 389, 117, 29);
		frmDetallePublicacion.getContentPane().add(btnCancelar);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(23, 127, 61, 16);
		frmDetallePublicacion.getContentPane().add(lblPrecio);
		
		JLabel lblMediosDePago = new JLabel("Medios de pago disponibles ");
		lblMediosDePago.setBounds(23, 179, 203, 16);
		frmDetallePublicacion.getContentPane().add(lblMediosDePago);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(109, 127, 61, 16);
		lblNewLabel_2.setText(String.valueOf(publicacion.getPrecio()));
		frmDetallePublicacion.getContentPane().add(lblNewLabel_2);
		bg = new ButtonGroup();
		int mpHeight=180;
		for(MedioPago mp: publicacion.getMediosPago()) {
			String mpLabel = null;
			
			// TODO: get from MedioPago directly, without strings switch
			switch(mp.toString()) {
				case "EFECTIVO": 				mpLabel = "Efectivo"; break;
				case "TRANSFERENCIA_BANCARIA": 	mpLabel = "Transferencia Bancaria"; break;
				case "TARJETA_CREDITO": 			mpLabel = "Tarjeta de Credito"; break;
			}
			JRadioButton b= new JRadioButton(mpLabel);
			b.setBounds(254, mpHeight, 200, 16);
			bg.add(b);
			frmDetallePublicacion.add(b);
			mpHeight+=30;			
		}
		
		if(publicacion instanceof Subasta) {
			manageSubasta();
		}else{
			managePublicacion();
		} 
	}
	public void setVisible(boolean isVisible) {
		this.frmDetallePublicacion.setVisible(isVisible);
	}
	public void manageSubasta(){
		//Precio de la ultima oferta. 
		btnOfertar.setName("Ofertar");
		JLabel lblOferta = new JLabel("Su oferta");
		lblOferta.setBounds(23, 327, 51, 16);
		frmDetallePublicacion.getContentPane().add(lblOferta);
		JTextField txtOferta= new JTextField();
		txtOferta.setBounds(166, 326, 61, 16);
		frmDetallePublicacion.getContentPane().add(txtOferta);
		btnOfertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedioPago mp = null;
				
				// TODO: get from MedioPago directly, without strings switch
				switch(getSelectedButtonText(bg)) {
					case "EFECTIVO": mp = MedioPago.EFECTIVO; break;
					case "TRANSFERENCIA_BANCARIA": mp = MedioPago.TRANSFERENCIA_BANCARIA; break;
					case "TARJETA_CREDITO": mp = MedioPago.TARJETA_CREDITO; break;
				}
				
				try {
					publicacion.ofertar(Float.parseFloat(txtOferta.getText()), user, mp);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				publicacion.setPrecio(Float.parseFloat(txtOferta.getText()));

			}
		});
	}
	public void managePublicacion(){
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(109, 127, 61, 16);
		lblNewLabel_2.setText(String.valueOf(publicacion.getPrecio()));
		frmDetallePublicacion.getContentPane().add(lblNewLabel_2);
		if(articulo instanceof Producto) {
			// Mostras los datos del producto
		} else {
			// Mostras los datos del servicio
		}
	}
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
