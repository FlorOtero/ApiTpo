package edu.uade.api.tpo.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.Garantia;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Servicio;
import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.model.TipoContratacion;
import edu.uade.api.tpo.model.TipoPeriodo;
import edu.uade.api.tpo.model.Usuario;
import edu.uade.api.tpo.ui.MenuPrincipal;

import javax.swing.JCheckBox;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AltaPublicacion implements ItemListener{

	final static String COMPRA_INMEDIATA = "Compra inmediata";
	final static String SUBASTA = "Subasta";
	final static String PRODUCTO = "Producto";
	final static String SERVICIO = "Servicio";
	
	private JFrame frmNuevaPublicacion;
	private JPanel panelTipoPublicacion;
	private JPanel panelCategoria;
	private JTextField txtTitulo;
	private JTextField txtPrecio;
	private JTextField txtPrecioInicial;
	private JTextField txtPrecioMinimo;
	private JTextField txtGarantia;
	private JTextField txtCertificados;
	private JTextArea textAreaDescripcion;
	private JCheckBox pagoEfectivoCheckbox;
	private JCheckBox pagoTarjetaCreditoCheckbox;
	private JCheckBox pagoTransferenciaCheckbox;
	private JComboBox<String> comboBoxCategoria;
	private JComboBox<String> comboBoxTipoPublicacion; 
	private JComboBox<String> comboBoxTipoContratacion;
	private JComboBox<String> comboBoxVigencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaPublicacion window = new AltaPublicacion();
					window.frmNuevaPublicacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AltaPublicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNuevaPublicacion = new JFrame();
		frmNuevaPublicacion.setTitle("Nueva Publicación | API");
		frmNuevaPublicacion.setBounds(100, 100, 780, 750);
		frmNuevaPublicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmNuevaPublicacion.setJMenuBar(menuBar);
		
		JMenu mnMiCuenta = new JMenu("Mi Cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta Corriente");
		mnMiCuenta.add(mntmCuentaCorriente);
		
		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		
		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		
		JMenu mnPublicaciones = new JMenu("Publicaciones");
		menuBar.add(mnPublicaciones);
		
		JMenuItem mntmNuevaPublicacion = new JMenuItem("Nueva Publicación");
		mnPublicaciones.add(mntmNuevaPublicacion);
		
		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmNuevaPublicacion.getContentPane().setLayout(null);
		
		/**
		 * TODO cambiar Nueva por Modificar, cuando se modifique la publicacion
		 */
		JLabel lblBreadcrumb = new JLabel("Inicio > Publicaciones > Nueva Publicación");
		lblBreadcrumb.setBounds(10, 20, 760, 16);
		frmNuevaPublicacion.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 780, 12);
		frmNuevaPublicacion.getContentPane().add(separator);
		
		JLabel lblTitulo = new JLabel("Título de la Publicación");
		lblTitulo.setBounds(10, 60, 480, 16);
		frmNuevaPublicacion.getContentPane().add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(10, 80, 760, 30);
		frmNuevaPublicacion.getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(10, 120, 760, 16);
		frmNuevaPublicacion.getContentPane().add(lblDescripcion);
	
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		
		JScrollPane scrollPaneDescripcion = new JScrollPane(textAreaDescripcion);
		scrollPaneDescripcion.setBounds(10, 145, 760, 150);
		scrollPaneDescripcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDescripcion.setPreferredSize(new Dimension(760, 150));
		frmNuevaPublicacion.getContentPane().add(scrollPaneDescripcion);
		
		
		JButton btnCargarImagenes = new JButton("Cargar imágenes");
		btnCargarImagenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
			
			}
		});
		btnCargarImagenes.setBounds(570, 305, 200, 30);
		frmNuevaPublicacion.getContentPane().add(btnCargarImagenes);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 340, 760, 12);
		frmNuevaPublicacion.getContentPane().add(separator_1);
		
		JLabel lblTipoDePublicacion = new JLabel("Tipo de Publicación");
		lblTipoDePublicacion.setBounds(10, 360, 360, 16);
		frmNuevaPublicacion.getContentPane().add(lblTipoDePublicacion);
		
		String[] tipoPublicacionStrings = { COMPRA_INMEDIATA, SUBASTA };
		comboBoxTipoPublicacion = new JComboBox(tipoPublicacionStrings);
		comboBoxTipoPublicacion.setBounds(10, 380, 360, 27);
		comboBoxTipoPublicacion.setSelectedIndex(0);
		comboBoxTipoPublicacion.addItemListener(this);
		frmNuevaPublicacion.getContentPane().add(comboBoxTipoPublicacion);
		
		panelTipoPublicacion = new JPanel();
		panelTipoPublicacion.setBounds(10, 410, 360, 150);
		frmNuevaPublicacion.getContentPane().add(panelTipoPublicacion);
		panelTipoPublicacion.setLayout(new CardLayout(0, 0));
		
		JPanel panelCompraInmediata = new JPanel();
		panelTipoPublicacion.add(panelCompraInmediata, COMPRA_INMEDIATA);
		panelCompraInmediata.setLayout(null);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(6, 6, 360, 16);
		panelCompraInmediata.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(6, 27, 180, 30);
		panelCompraInmediata.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblPesosArgentinos = new JLabel("ARS");
		lblPesosArgentinos.setBounds(198, 27, 154, 30);
		panelCompraInmediata.add(lblPesosArgentinos);
		
		JPanel panelSubasta = new JPanel();
		panelTipoPublicacion.add(panelSubasta, SUBASTA);
		panelSubasta.setLayout(null);
		
		JLabel lblPrecioInicial = new JLabel("Precio inicial:");
		lblPrecioInicial.setBounds(0, 10, 90, 30);
		panelSubasta.add(lblPrecioInicial);
		
		txtPrecioInicial = new JTextField();
		txtPrecioInicial.setBounds(100, 10, 120, 30);
		panelSubasta.add(txtPrecioInicial);
		txtPrecioInicial.setColumns(10);
		
		JLabel lblPesosArgentinos_1 = new JLabel("pesos argentinos");
		lblPesosArgentinos_1.setBounds(225, 10, 130, 30);
		panelSubasta.add(lblPesosArgentinos_1);
		
		JLabel lblPrecioMinimo = new JLabel("Precio mínimo:");
		lblPrecioMinimo.setBounds(0, 50, 100, 30);
		panelSubasta.add(lblPrecioMinimo);
		
		txtPrecioMinimo = new JTextField();
		txtPrecioMinimo.setBounds(100, 50, 120, 30);
		panelSubasta.add(txtPrecioMinimo);
		txtPrecioMinimo.setColumns(10);
		
		JLabel lblPesosArgentinos_2 = new JLabel("pesos argentinos");
		lblPesosArgentinos_2.setBounds(225, 50, 130, 30);
		panelSubasta.add(lblPesosArgentinos_2);
		
		JLabel lblVigencia = new JLabel("Vigencia:");
		lblVigencia.setBounds(0, 90, 90, 30);
		panelSubasta.add(lblVigencia);
		
		String[] vigenciaStrings = { "30", "60", "90"};
		comboBoxVigencia = new JComboBox(vigenciaStrings);
		comboBoxVigencia.setBounds(100, 90, 120, 30);
		panelSubasta.add(comboBoxVigencia);
		
		JLabel lblDias = new JLabel("días");
		lblDias.setBounds(225, 90, 130, 30);
		panelSubasta.add(lblDias);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(384, 360, 12, 200);
		frmNuevaPublicacion.getContentPane().add(separator_2);
		
		JLabel lblCategoria = new JLabel("Categoría");
		lblCategoria.setBounds(410, 360, 360, 16);
		frmNuevaPublicacion.getContentPane().add(lblCategoria);
		
		String[] categoriaStrings = { PRODUCTO, SERVICIO };
		comboBoxCategoria = new JComboBox(categoriaStrings);
		comboBoxCategoria.setBounds(410, 380, 360, 27);
		comboBoxCategoria.addItemListener(this);
		frmNuevaPublicacion.getContentPane().add(comboBoxCategoria);
		
		panelCategoria = new JPanel();
		panelCategoria.setBounds(410, 410, 360, 150);
		frmNuevaPublicacion.getContentPane().add(panelCategoria);
		panelCategoria.setLayout(new CardLayout(0, 0));
		
		JPanel panelProducto = new JPanel();
		panelCategoria.add(panelProducto, PRODUCTO);
		panelProducto.setLayout(null);
		
		JLabel lblGarantia = new JLabel("Garantía:");
		lblGarantia.setBounds(0, 6, 70, 30);
		panelProducto.add(lblGarantia);
		
		txtGarantia = new JTextField();
		txtGarantia.setBounds(0, 37, 110, 30);
		panelProducto.add(txtGarantia);
		txtGarantia.setColumns(10);
		
		String[] garantiaStrings = { "Meses", "Años"};
		JComboBox<String> comboBoxGarantia = new JComboBox(garantiaStrings);
		comboBoxGarantia.setBounds(122, 38, 170, 30);
		panelProducto.add(comboBoxGarantia);
		
		JPanel panelServicio = new JPanel();
		panelCategoria.add(panelServicio, SERVICIO);
		panelServicio.setLayout(null);
		
		JLabel lblTipoDeContratacion = new JLabel("Tipo de Contratación:");
		lblTipoDeContratacion.setBounds(0, 10, 140, 30);
		panelServicio.add(lblTipoDeContratacion);
		
		String[] contratacionStrings = { "Abono", "Única"};
		comboBoxTipoContratacion = new JComboBox(contratacionStrings);
		comboBoxTipoContratacion.setBounds(150, 10, 210, 30);
		panelServicio.add(comboBoxTipoContratacion);
		
// 		TODO: Decidir que son y como mostrar certificados
//
//		JLabel lblCertificados = new JLabel("Certificados:");
//		lblCertificados.setBounds(0, 50, 360, 16);
//		panelServicio.add(lblCertificados);
//		
//		txtCertificados = new JTextField();
//		txtCertificados.setBounds(0, 70, 200, 30);
//		panelServicio.add(txtCertificados);
//		txtCertificados.setColumns(10);
//		
//		JButton btnCargarCertificado = new JButton("Cargar certificado");
//		btnCargarCertificado.setBounds(210, 70, 150, 30);
//		panelServicio.add(btnCargarCertificado);
//		
//		JTextPane textPaneCertificados = new JTextPane();
//		textPaneCertificados.setText("Acá se tienen que ir mostrando los certificados cargados");
//		textPaneCertificados.setBackground(SystemColor.window);
//		textPaneCertificados.setEditable(false);
//		textPaneCertificados.setBounds(0, 110, 360, 40);
//		panelServicio.add(textPaneCertificados);
		
		JLabel lblMedioDePago = new JLabel("Medio de Pago aceptados:");
		lblMedioDePago.setBounds(10, 580, 760, 16);
		frmNuevaPublicacion.getContentPane().add(lblMedioDePago);
		
		pagoEfectivoCheckbox = new JCheckBox("Efectivo");
		pagoEfectivoCheckbox.setBounds(10, 600, 130, 23);
		frmNuevaPublicacion.getContentPane().add(pagoEfectivoCheckbox);
		
		pagoTarjetaCreditoCheckbox = new JCheckBox("Tarjeta de Crédito");
		pagoTarjetaCreditoCheckbox.setBounds(140, 600, 150, 23);
		frmNuevaPublicacion.getContentPane().add(pagoTarjetaCreditoCheckbox);
		
		pagoTransferenciaCheckbox = new JCheckBox("Transferencia Bancaria");
		pagoTransferenciaCheckbox.setBounds(330, 600, 180, 23);
		frmNuevaPublicacion.getContentPane().add(pagoTransferenciaCheckbox);
		
		JLabel lblFinPublicacion = new JLabel("Fin de la Publicación:");
		lblFinPublicacion.setBounds(10, 640, 140, 16);
		frmNuevaPublicacion.getContentPane().add(lblFinPublicacion);
		
		JLabel lblFechaFinPublicacion = new JLabel("20-10-2017");
		lblFechaFinPublicacion.setBounds(150, 640, 340, 16);
		frmNuevaPublicacion.getContentPane().add(lblFechaFinPublicacion);
		
		JButton btnConfirmar = new JButton("Publicar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicar();
			}
		});
		btnConfirmar.setBounds(650, 670, 120, 30);
		frmNuevaPublicacion.getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(530, 670, 120, 30);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goInicio();
			}
		});
		frmNuevaPublicacion.getContentPane().add(btnCancelar);
		
		/**
		 * Este boton solo debe aparecer en caso de modificar publicacion
		 */
		JButton btnEliminarPublicacion = new JButton("Eliminar Publicación");
		btnEliminarPublicacion.setBounds(10, 670, 180, 30);
		frmNuevaPublicacion.getContentPane().add(btnEliminarPublicacion);
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		CardLayout cl;
		String item = (String) e.getItem();
		
		if(item.equals(SUBASTA) || item.equals(COMPRA_INMEDIATA)) {
			cl = (CardLayout)(panelTipoPublicacion.getLayout());
	        cl.show(panelTipoPublicacion, item);
		} else if(item.equals(PRODUCTO) || item.equals(SERVICIO)) {
			cl = (CardLayout)(panelCategoria.getLayout());
	        cl.show(panelCategoria, item);
		}
	}
	
	private void publicar() {
		if (validateForm()) {
			Usuario user = SistemaUsuarios.getUsuarioLoggeado();
			List<MedioPago> mediosPagos = getMediosPago();
			Articulo articulo;
			
			if (comboBoxCategoria.getSelectedItem().toString() == PRODUCTO) {				
				articulo = crearProducto();
			} else {
				articulo = crearServicio();
			}
			
			try {
				if (comboBoxTipoPublicacion.getSelectedItem().toString() == SUBASTA) {	
					Subasta subasta = new Subasta();
					subasta.setPrecioInicial(Float.parseFloat(txtPrecioInicial.getText()));
					subasta.setPrecioInicial(Float.parseFloat(txtPrecioMinimo.getText()));
					subasta.setDiasVigencia(Integer.parseInt(comboBoxVigencia.getSelectedItem().toString()));
					SistemaPublicaciones.getInstance().altaSubasta(user.getId(), subasta.getArticulo(), subasta.getPrecioMin(), subasta.getDiasVigencia(),
							subasta.getPrecioInicial(), mediosPagos);
				} else {				
					Publicacion publicacion = new Publicacion();
					publicacion.setPrecio(Float.parseFloat(txtPrecio.getText()));
					publicacion.setArticulo(articulo);
					SistemaPublicaciones.getInstance().altaPublicacion(user.getId(), publicacion.getPrecio(), publicacion.getArticulo(), mediosPagos);
				}
				JOptionPane.showMessageDialog(null, "¡Tu publicacion ha sido creada!", "Operacion Exitosa", JOptionPane.PLAIN_MESSAGE);
				goInicio();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aun tienes campos que completar", "Formulario Incompleto", JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	private Producto crearProducto() {
		Producto producto = new Producto();
		producto.setNombre(txtTitulo.getText());
		producto.setDescripcion(textAreaDescripcion.getText());
		// producto.fromImagesTokenized(filepathField.toString());
		producto.fromImagesTokenized("");
		
		Garantia garantia = new Garantia();
		garantia.setCantidad(Integer.parseInt(txtGarantia.getText()));
        garantia.setTipo(TipoPeriodo.ANUAL);
        
		producto.setGarantia(garantia);
		return producto;
	}
	
	private Servicio crearServicio() {
		Servicio servicio = new Servicio();
		servicio.setNombre(txtTitulo.getText());
		servicio.setDescripcion(textAreaDescripcion.getText());
		// producto.fromImagesTokenized(filepathField.toString());
		servicio.fromImagesTokenized("");
		
		Garantia garantia = new Garantia();
		garantia.setCantidad(Integer.parseInt(txtGarantia.getText()));
        garantia.setTipo(TipoPeriodo.ANUAL);
        
        TipoContratacion tipoContratacion = null;
        if (comboBoxTipoContratacion.getSelectedItem().toString() == "Abono") {
        	tipoContratacion = TipoContratacion.ABONO;
        } else {
        	tipoContratacion = TipoContratacion.UNICA;
        }
		servicio.setContratacion(tipoContratacion);
		
		return servicio;
	}
	
	private void goInicio() {
		Inicio inicio = new Inicio();
		inicio.setVisible(true);
		frmNuevaPublicacion.dispose();
	}

	public void setVisible(boolean isVisible) {
		this.frmNuevaPublicacion.setVisible(isVisible);
	}
	
	private List<MedioPago> getMediosPago() {
		List<MedioPago> mediosPago = new ArrayList<MedioPago>();
		if (pagoEfectivoCheckbox.isSelected()) { mediosPago.add(MedioPago.EFECTIVO); }
		if (pagoTarjetaCreditoCheckbox.isSelected()) { mediosPago.add(MedioPago.TARJETA_CREDITO); }
		if (pagoTransferenciaCheckbox.isSelected()) { mediosPago.add(MedioPago.TRANSFERENCIA_BANCARIA); }
		return mediosPago;
	}
	
	private boolean validateForm() {
		// TODO: implement form validation
		return true;
	}
}
