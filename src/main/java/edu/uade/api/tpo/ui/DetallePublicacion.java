package edu.uade.api.tpo.ui;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class DetallePublicacion {
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
		user = SistemaUsuarios.getInstance().getUsuarioActivo();
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
		
		JLabel lblTipo = new JLabel();
		lblTipo.setBounds(290, 33, 400, 30);
		frmDetallePublicacion.getContentPane().add(lblTipo);

		JLabel lblDescripcion = new JLabel("Descripcion: ");
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
				backToMenuPrincipal();
			}
		});
		btnCancelar.setBounds(242, 389, 117, 29);
		frmDetallePublicacion.getContentPane().add(btnCancelar);
		
		JLabel lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(23, 127, 61, 16);
		frmDetallePublicacion.getContentPane().add(lblPrecio);
		
		JLabel lblMediosDePago = new JLabel("Medios de pago disponibles: ");
		lblMediosDePago.setBounds(23, 179, 203, 16);
		frmDetallePublicacion.getContentPane().add(lblMediosDePago);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(109, 127, 61, 16);
		lblNewLabel_2.setText(String.valueOf("$" + publicacion.getPrecio()));
		frmDetallePublicacion.getContentPane().add(lblNewLabel_2);
		bg = new ButtonGroup();
		int mp_y=180;
		for(MedioPago mp: publicacion.getMediosPago()) {
			String mpLabel = null;
			
			// TODO: get from MedioPago directly, without strings switch
			switch(mp.toString()) {
				case "EFECTIVO": 				mpLabel = "Efectivo"; break;
				case "TRANSFERENCIA_BANCARIA": 	mpLabel = "Transferencia Bancaria"; break;
				case "TARJETA_CREDITO": 			mpLabel = "Tarjeta de Credito"; break;
			}
			JRadioButton b= new JRadioButton(mpLabel);
			b.setBounds(230, mp_y, 200, 16);
			bg.add(b);
			frmDetallePublicacion.add(b);
			mp_y += 30;			
		}
		
		if (publicacion instanceof Subasta) {
			manageSubasta();
			lblTipo.setText("Subasta");
		} else {
			managePublicacion();
			lblTipo.setText("Compra Inmediata");
		}
		
		backToMenuPrincipal();
	}
	
	public void setVisible(boolean isVisible) {
		this.frmDetallePublicacion.setVisible(isVisible);
	}
	
	public void manageSubasta() {
		Subasta subasta = (Subasta) publicacion;
		//Precio de la ultima oferta. 
		JLabel lblOferta = new JLabel("Su oferta");
		lblOferta.setBounds(23, 327, 51, 16);
		frmDetallePublicacion.getContentPane().add(lblOferta);
		
		JTextField txtOferta= new JTextField();
		txtOferta.setBounds(166, 326, 61, 16);
		frmDetallePublicacion.getContentPane().add(txtOferta);
		
		btnOfertar.setName("Ofertar");
		btnOfertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedioPago mp = null;
				
				// TODO: get from MedioPago directly, without strings switch
				System.out.println(getSelectedButtonText(bg));
				switch(getSelectedButtonText(bg)) {
					case "Efectivo": mp = MedioPago.EFECTIVO; break;
					case "Transferencia Bancaria": mp = MedioPago.TRANSFERENCIA_BANCARIA; break;
					case "Tarjeta de Credito": mp = MedioPago.TARJETA_CREDITO; break;
				}
				
				// TODO: populate datos paog
				DatosPago datosPago = null;				
				try {
					subasta.ofertar(Float.parseFloat(txtOferta.getText()), user, datosPago);
					JOptionPane.showConfirmDialog(null, "Has ofertado exitosamente!", "Aviso", JOptionPane.PLAIN_MESSAGE);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
	}
	
	public void managePublicacion(){
		btnOfertar.setName("comprar");
		if(articulo instanceof Producto) {
			// Mostras los datos del producto
			JLabel lblGarantia = new JLabel();			
		} else {
			// Mostras los datos del servicio

		}
		btnOfertar.setName("Comprar");
		btnOfertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosPago datosPago = new DatosPago();	
				MedioPago mp= null;
				
				switch(getSelectedButtonText(bg)) {
					case "Efectivo": mp = MedioPago.EFECTIVO; 
						datosPago.setMedioPago(mp);
						break;
					case "Transferencia Bancaria": mp = MedioPago.TRANSFERENCIA_BANCARIA;
						datosPago.setMedioPago(mp);
						PagoTransferencia pagoTB = new PagoTransferencia(datosPago, publicacion.getPrecio());
						pagoTB.setVisible(true);
						break;
					case "Tarjeta de Credito": mp = MedioPago.TARJETA_CREDITO; 
						datosPago.setMedioPago(mp);
						PagoTarjeta pagoTC = new PagoTarjeta(datosPago, publicacion.getPrecio());	
						pagoTC.setVisible(true);
						break;
				}
				realizarOferta(datosPago);			
			}
		});
		
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
	
	public void backToMenuPrincipal() {
		frmMenuPrincipal = new MenuPrincipal();	
		frmMenuPrincipal.setVisible(true);
		frmDetallePublicacion.dispose();
	}
	private void realizarOferta(DatosPago datosPago) {
		try {
			SistemaPublicaciones.getInstance().ofertar(publicacion, publicacion.getPrecio(), user, datosPago);
			JOptionPane.showConfirmDialog(null, "Compra exitosa", "Aviso", JOptionPane.PLAIN_MESSAGE);
		}catch(BusinessException e2){
			
		}
	}
}
