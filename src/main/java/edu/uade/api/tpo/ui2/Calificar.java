package edu.uade.api.tpo.ui2;

import java.awt.EventQueue;
import java.io.File;

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
import javax.swing.JTextArea;

public class Calificar {

	private JFrame frmCalificarApi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calificar window = new Calificar();
					window.frmCalificarApi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calificar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalificarApi = new JFrame();
		frmCalificarApi.setTitle("Calificar | API");
		frmCalificarApi.setBounds(100, 100, 500, 500);
		frmCalificarApi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmCalificarApi.setJMenuBar(menuBar);
		
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
		frmCalificarApi.getContentPane().setLayout(null);
		
		JLabel lblBreadcrumb = new JLabel("Inicio > Mi Cuenta > Calificar");
		lblBreadcrumb.setBounds(10, 20, 480, 16);
		frmCalificarApi.getContentPane().add(lblBreadcrumb);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 500, 12);
		frmCalificarApi.getContentPane().add(separator);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 60, 60, 16);
		frmCalificarApi.getContentPane().add(lblUsuario);
		
		/**
		 * Indicar el nombre del usuario vendedor
		 */
		JLabel lblUsuarioVendedor = new JLabel("cosmefulanito");
		lblUsuarioVendedor.setBounds(70, 60, 420, 16);
		frmCalificarApi.getContentPane().add(lblUsuarioVendedor);
		
		JLabel lblPublicacion = new JLabel("Publicación:");
		lblPublicacion.setBounds(10, 90, 85, 16);
		frmCalificarApi.getContentPane().add(lblPublicacion);
		
		JButton btnVerPublicacion = new JButton("Ver Publicación");
		btnVerPublicacion.setBounds(95, 84, 140, 30);
		frmCalificarApi.getContentPane().add(btnVerPublicacion);
		
		JLabel lblCalificacion = new JLabel("Calificación:");
		lblCalificacion.setBounds(10, 120, 480, 16);
		frmCalificarApi.getContentPane().add(lblCalificacion);
		
		ButtonGroup group = new ButtonGroup();
		JRadioButton rate_1 = new JRadioButton("1");
		rate_1.setBounds(10, 150, 48, 25);
		group.add(rate_1);
		frmCalificarApi.getContentPane().add(rate_1);
		
		JRadioButton rate_2 = new JRadioButton("2");
		rate_2.setBounds(58, 150, 48, 23);
		group.add(rate_2);
		frmCalificarApi.getContentPane().add(rate_2);
		
		JRadioButton rate_3 = new JRadioButton("3");
		rate_3.setBounds(106, 150, 48, 23);
		group.add(rate_3);
		frmCalificarApi.getContentPane().add(rate_3);
		
		JRadioButton rate_4 = new JRadioButton("4");
		rate_4.setBounds(154, 150, 48, 23);
		group.add(rate_4);
		frmCalificarApi.getContentPane().add(rate_4);
		
		JRadioButton rate_5 = new JRadioButton("5");
		rate_5.setBounds(202, 150, 48, 23);
		rate_5.setSelected(true);
		group.add(rate_5);
		frmCalificarApi.getContentPane().add(rate_5);
		
		JRadioButton rate_6 = new JRadioButton("6");
		rate_6.setBounds(250, 150, 48, 23);
		group.add(rate_6);
		frmCalificarApi.getContentPane().add(rate_6);
		
		JRadioButton rate_7 = new JRadioButton("7");
		rate_7.setBounds(298, 150, 48, 23);
		group.add(rate_7);
		frmCalificarApi.getContentPane().add(rate_7);
		
		JRadioButton rate_8 = new JRadioButton("8");
		rate_8.setBounds(346, 150, 48, 23);
		group.add(rate_8);
		frmCalificarApi.getContentPane().add(rate_8);
		
		JRadioButton rate_9 = new JRadioButton("9");
		rate_9.setBounds(394, 150, 48, 23);
		group.add(rate_9);
		frmCalificarApi.getContentPane().add(rate_9);
		
		JRadioButton rate_10 = new JRadioButton("10");
		rate_10.setBounds(442, 150, 48, 23);
		group.add(rate_10);
		frmCalificarApi.getContentPane().add(rate_10);
		
		String negativoPath = new File("src/main/resources/negativo.png").getAbsolutePath();
		JLabel lblNegativo = new JLabel(new ImageIcon(negativoPath));
		lblNegativo.setBounds(10, 180, 32, 32);
		frmCalificarApi.getContentPane().add(lblNegativo);
		
		String neutralPath = new File("src/main/resources/neutral.png").getAbsolutePath();
		JLabel lblNeutral = new JLabel(new ImageIcon(neutralPath));
		lblNeutral.setBounds(200, 180, 32, 32);
		frmCalificarApi.getContentPane().add(lblNeutral);
		
		String positivoPath = new File("src/main/resources/positivo.png").getAbsolutePath();
		JLabel lblPositivo = new JLabel(new ImageIcon(positivoPath));
		lblPositivo.setBounds(440, 180, 32, 32);
		frmCalificarApi.getContentPane().add(lblPositivo);
		
		JLabel lblObservaciones = new JLabel("Observaciones:");
		lblObservaciones.setBounds(10, 230, 480, 16);
		frmCalificarApi.getContentPane().add(lblObservaciones);
		
		JTextArea textAreaObservaciones = new JTextArea();
		textAreaObservaciones.setBounds(10, 260, 480, 150);
		frmCalificarApi.getContentPane().add(textAreaObservaciones);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(370, 420, 120, 30);
		frmCalificarApi.getContentPane().add(btnEnviar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(250, 420, 120, 30);
		frmCalificarApi.getContentPane().add(btnCancelar);
	}
}
