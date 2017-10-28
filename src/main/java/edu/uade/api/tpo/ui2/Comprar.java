package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;

import edu.uade.api.tpo.model.Subasta;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTable;

public class Comprar implements ActionListener{

	private JFrame frmComprarApi;
	JPanel panelDatosDelPago;
	private JTextField txtNumeroDeTarjeta;
	private JTextField txtCodigoDeSeguridad;
	private JTextField txtFechaDeVencimiento;
	private JTextField txtNombreDelTitular;
	private JTextField txtNumeroDeCuenta;
	private JTextField txtCuil;
	private String opcion;
	private JTextField txtOferta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comprar window = new Comprar();
					window.frmComprarApi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Comprar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmComprarApi = new JFrame();
		frmComprarApi.setTitle("Comprar | API");
		frmComprarApi.setBounds(100, 100, 500, 570);
		frmComprarApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmComprarApi.setJMenuBar(menuBar);
		
		JButton btnInicio = new JButton("");
		String homePath = new File("src/main/resources/house.png").getAbsolutePath();
		btnInicio.setIcon(new ImageIcon(homePath));
		menuBar.add(btnInicio);
		
		btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmComprarApi.dispose();
			}
		});
		
		JMenu mnMiCuenta = new JMenu("Mi Cuenta");
		menuBar.add(mnMiCuenta);
		
		JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta Corriente");
		mnMiCuenta.add(mntmCuentaCorriente);
		mntmCuentaCorriente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CuentaCorriente cc = new CuentaCorriente();
				cc.setVisible(true);
				frmComprarApi.dispose();
			}
		});
		
		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		mntmMiReputacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reputacion reputacion = new Reputacion();
				reputacion.setVisible(true);
				frmComprarApi.dispose();
			}
		});
		
		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		mntmMiUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiUsuario miUsuario = new MiUsuario();
				miUsuario.setVisible(true);
				frmComprarApi.dispose();
			}
		});
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar ingreso = new Ingresar();
				ingreso.setVisible(true);
				frmComprarApi.dispose();
			}
		});
		
		JMenu mnPublicaciones = new JMenu("Publicaciones");
		menuBar.add(mnPublicaciones);
		
		JMenuItem mntmNuevaPublicacion = new JMenuItem("Nueva Publicación");
		mnPublicaciones.add(mntmNuevaPublicacion);
		mntmNuevaPublicacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaPublicacion altaPublicacion = new AltaPublicacion();
				altaPublicacion.setVisible(true);
				frmComprarApi.dispose();
			}
		});
		
		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmComprarApi.getContentPane().setLayout(null);
		mntmMisPublicaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MisPublicaciones misPublicaciones = new MisPublicaciones();
				misPublicaciones.setVisible(true);
				frmComprarApi.dispose();
			}
		});
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Publicación > Comprar > Medio de Pago");
		lblBreadcrumb.setBounds(10, 20, 480, 16);
		frmComprarApi.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 500, 12);
		frmComprarApi.getContentPane().add(separator);
		
		/**
		 * IMPORTANTE: el panelSubasta solo debe ser visible si la publicacion
		 * es una SUBASTA. De lo contrario hay que ocultarlo.
		 * 
		 * panelSubasta.setVisible(publicacion es subasta);
		 */	
	    JPanel panelSubasta = new JPanel();
	    panelSubasta.setBounds(10, 60, 480, 80);
	    frmComprarApi.getContentPane().add(panelSubasta);
	    
	    JLabel lblPrecioInicial = new JLabel("Precio inicial:");
	    lblPrecioInicial.setLocation(0, 0);
	    lblPrecioInicial.setSize(100, 30);
	    panelSubasta.add(lblPrecioInicial);
	    panelSubasta.setLayout(null);
	    lblPrecioInicial.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		/**
		 * Indicar el precio inicial de la publicacion
		 */
	    JLabel lblPrecioInicialPublicacion = new JLabel("$1000");
	    lblPrecioInicialPublicacion.setLocation(100, 0);
	    lblPrecioInicialPublicacion.setSize(380, 30);
	    panelSubasta.add(lblPrecioInicialPublicacion);
	    lblPrecioInicialPublicacion.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	    
	    JLabel lblOferta = new JLabel("Oferta:");
	    lblOferta.setLocation(0, 40);
	    lblOferta.setSize(50, 30);
	    panelSubasta.add(lblOferta);
	    
	    txtOferta = new JTextField();
	    txtOferta.setLocation(50, 40);
	    txtOferta.setSize(280, 30);
	    panelSubasta.add(txtOferta);
	    txtOferta.setColumns(10);
	    
	    JLabel lblPesosArgentinos = new JLabel("pesos argentinos");
	    lblPesosArgentinos.setLocation(340, 40);
	    lblPesosArgentinos.setSize(140, 30);
	    panelSubasta.add(lblPesosArgentinos);		
		
		JPanel panelSeleccionarMP = new JPanel();
	    panelSeleccionarMP.setBounds(10, 140, 480, 130);
	    frmComprarApi.getContentPane().add(panelSeleccionarMP);
	    panelSeleccionarMP.setLayout(null);
	    
	    JLabel lblSeleccionarMP = new JLabel("Seleccione el medio de pago:");
	    lblSeleccionarMP.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	    lblSeleccionarMP.setBounds(0, 0, 480, 22);
	    panelSeleccionarMP.add(lblSeleccionarMP); 
	    
	    /**
	     * Mostrar los radio button que se correspondan con los medios
	     * de pago disponibles para esta publicacion
	     * 
	     * IMPORTANTE: no olvida que al menos un radio button debe estar siempre
	     * seleccionado. Seleccionar siempre el primero de la lista, para que
	     * sea mas sencillo.
	     * 
	     * NO OLVIDAR: panelDatosDelPago.show(panelDatosDelPago, "nombre_del_primer_medio_disponible"); => efectivo, tarjeta_credito, transferencia_bancaria.
	     */
	    ButtonGroup group = new ButtonGroup();
	    
	    JRadioButton rdbtnEfectivo = new JRadioButton("Efectivo");
	    rdbtnEfectivo.setSelected(true);
	    rdbtnEfectivo.setBounds(0, 30, 480, 25);
	    rdbtnEfectivo.setName("efectivo");
	    panelSeleccionarMP.add(rdbtnEfectivo);
	    group.add(rdbtnEfectivo);
	    rdbtnEfectivo.addActionListener(this);
	    
	    JRadioButton rdbtnTarjetaDeCredito = new JRadioButton("Tarjeta de Crédito (MercadoPago)");
	    rdbtnTarjetaDeCredito.setBounds(0, 60, 480, 25);
	    rdbtnTarjetaDeCredito.setName("tarjeta_credito");
	    panelSeleccionarMP.add(rdbtnTarjetaDeCredito);
	    group.add(rdbtnTarjetaDeCredito);
	    rdbtnTarjetaDeCredito.addActionListener(this);
	    
	    JRadioButton rdbtnTransferenciaBancaria = new JRadioButton("Transferencia Bancaria");
	    rdbtnTransferenciaBancaria.setBounds(0, 90, 480, 25);
	    rdbtnTransferenciaBancaria.setName("transferencia_bancaria");
	    panelSeleccionarMP.add(rdbtnTransferenciaBancaria);
	    group.add(rdbtnTransferenciaBancaria);
	    rdbtnTransferenciaBancaria.addActionListener(this);
		
		panelDatosDelPago = new JPanel();
		panelDatosDelPago.setBounds(10, 270, 480, 200);
		frmComprarApi.getContentPane().add(panelDatosDelPago);
		panelDatosDelPago.setLayout(new CardLayout(0, 0));
		
		JPanel panelEfectivo = new JPanel();
		panelDatosDelPago.add(panelEfectivo, "efectivo");
		panelEfectivo.setLayout(null);
		
		JPanel panelTarjetaCredito = new JPanel();
		panelDatosDelPago.add(panelTarjetaCredito, "tarjeta_credito");
		panelTarjetaCredito.setLayout(null);
		
		JLabel lblDatosDelPago = new JLabel("Datos del Pago:");
		lblDatosDelPago.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblDatosDelPago.setBounds(0, 0, 480, 22);
		panelTarjetaCredito.add(lblDatosDelPago);
		
		JLabel lblNumeroDeTarjeta = new JLabel("Número de tarjeta");
		lblNumeroDeTarjeta.setBounds(0, 30, 480, 16);
		panelTarjetaCredito.add(lblNumeroDeTarjeta);
		
		txtNumeroDeTarjeta = new JTextField();
		txtNumeroDeTarjeta.setBounds(0, 50, 480, 30);
		panelTarjetaCredito.add(txtNumeroDeTarjeta);
		txtNumeroDeTarjeta.setColumns(10);
		
		JLabel lblCodigoDeSeguridad = new JLabel("Código de Seguridad");
		lblCodigoDeSeguridad.setBounds(0, 90, 235, 16);
		panelTarjetaCredito.add(lblCodigoDeSeguridad);
		
		txtCodigoDeSeguridad = new JTextField();
		txtCodigoDeSeguridad.setBounds(0, 110, 235, 30);
		panelTarjetaCredito.add(txtCodigoDeSeguridad);
		txtCodigoDeSeguridad.setColumns(10);
		
		JLabel lblFechaDeVencimiento = new JLabel("Fecha de Vencimiento");
		lblFechaDeVencimiento.setBounds(245, 90, 235, 16);
		panelTarjetaCredito.add(lblFechaDeVencimiento);
		
		txtFechaDeVencimiento = new JTextField();
		txtFechaDeVencimiento.setBounds(245, 110, 235, 30);
		panelTarjetaCredito.add(txtFechaDeVencimiento);
		txtFechaDeVencimiento.setColumns(10);
		
		JLabel lblNombreDelTitular = new JLabel("Nombre del titular (como figura en la tarjeta)");
		lblNombreDelTitular.setBounds(0, 150, 480, 16);
		panelTarjetaCredito.add(lblNombreDelTitular);
		
		txtNombreDelTitular = new JTextField();
		txtNombreDelTitular.setBounds(0, 170, 480, 30);
		panelTarjetaCredito.add(txtNombreDelTitular);
		txtNombreDelTitular.setColumns(10);
		
		JPanel panelTransferenciaBancaria = new JPanel();
		panelDatosDelPago.add(panelTransferenciaBancaria, "transferencia_bancaria");
		panelTransferenciaBancaria.setLayout(null);
		
		JLabel lblDatosDelPago_1 = new JLabel("Datos del Pago:");
		lblDatosDelPago_1.setBounds(0, 0, 480, 22);
		lblDatosDelPago_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		panelTransferenciaBancaria.add(lblDatosDelPago_1);
		
		JLabel lblNumeroDeCuenta = new JLabel("Número de cuenta (CBU)");
		lblNumeroDeCuenta.setBounds(0, 30, 480, 16);
		panelTransferenciaBancaria.add(lblNumeroDeCuenta);
		
		txtNumeroDeCuenta = new JTextField();
		txtNumeroDeCuenta.setBounds(0, 50, 480, 30);
		panelTransferenciaBancaria.add(txtNumeroDeCuenta);
		txtNumeroDeCuenta.setColumns(10);
		
		JLabel lblCuil = new JLabel("CUIL/CUIT");
		lblCuil.setBounds(0, 90, 480, 16);
		panelTransferenciaBancaria.add(lblCuil);
		
		txtCuil = new JTextField();
		txtCuil.setBounds(0, 110, 480, 30);
		panelTransferenciaBancaria.add(txtCuil);
		txtCuil.setColumns(10);
		
		/**
		 * EJEMPLO DE LA LOGICA Y FUNCIONALIDAD ESPERADA
		 * REEMPLAZAR LA VARIABLE isSubasta POR LO QUE CORRESPONDA
		 */
	    boolean isSubasta = false;
	    
	    if(isSubasta) {
	    		panelSubasta.setVisible(true);
	    	    panelSeleccionarMP.setBounds(10, 140, 480, 130);
	    	    panelDatosDelPago.setBounds(10, 270, 480, 200);
	    } else {
	    		panelSubasta.setVisible(false);
	    		panelSeleccionarMP.setBounds(10, 60, 480, 130);
	    	    panelDatosDelPago.setBounds(10, 190, 480, 200);
	    }

		JButton btnConfirmar = new JButton("Confirmar");
	    btnConfirmar.setBounds(370, 490, 120, 30);
	    frmComprarApi.getContentPane().add(btnConfirmar);
	    
	    JButton btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBounds(250, 490, 120, 30);
	    frmComprarApi.getContentPane().add(btnCancelar);
	    

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JRadioButton){
            JRadioButton btn = (JRadioButton) e.getSource();
            System.out.println(btn.getName());

            CardLayout cl = (CardLayout)(panelDatosDelPago.getLayout());
            cl.show(panelDatosDelPago, btn.getName());
        }		
	}
}
