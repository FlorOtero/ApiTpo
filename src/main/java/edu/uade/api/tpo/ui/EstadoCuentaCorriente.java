package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import edu.uade.api.tpo.controller.SistemaCuentaCorriente;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.model.ItemCtaCte;
import edu.uade.api.tpo.model.Usuario;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;

public class EstadoCuentaCorriente {

	private JFrame frmEstadoDeCuenta;
	private JTable table;
	private Usuario user;
	private JLabel notification;
	Preferences prefs = Preferences.userNodeForPackage(edu.uade.api.tpo.util.Prefs.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstadoCuentaCorriente window = new EstadoCuentaCorriente();
					window.frmEstadoDeCuenta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EstadoCuentaCorriente() {
		cargarUsuario();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		notification = new JLabel("");
		notification.setBounds(92, 220, 300, 14);

		frmEstadoDeCuenta = new JFrame();
		frmEstadoDeCuenta.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmEstadoDeCuenta.setTitle("Estado de Cuenta Corriente");
		frmEstadoDeCuenta.setBounds(100, 100, 450, 334);
		frmEstadoDeCuenta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEstadoDeCuenta.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(0, 0, 450, 312);
		panel.add(notification);
		
		frmEstadoDeCuenta.getContentPane().add(panel);

		JLabel lblTransaccionesAConsultar = new JLabel("Transacciones a consultar:");

		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Todas");
		comboBox.addItem("Sólo Confirmadas");
		comboBox.addItem(" Sólo Canceladas");
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        
		    }
		});

		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Numero Transaccion", "Monto", "Estado", "Tipo" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(137);
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		try {

			List<ItemCtaCte> movimientos = SistemaCuentaCorriente.getInstance()
					.consultarMovimientos(user.getNombreUsuario());
			for (ItemCtaCte item : movimientos) {
				model.addRow(new Object[] { item.getIdOperacion(), item.getMonto(), item.getEstado(), item.getTipo() });
			}
			
		} catch (BusinessException e) {
			notification.setText(e.getMessage());
		}

		scrollPane.setViewportView(table);

		JLabel lblSaldoCuentaCorriente = new JLabel("Saldo Cuenta Corriente:");
		JLabel lblmonto = new JLabel(Float.toString(user.getCuentaCorriente().getSaldo()));

		JButton btnVolverAMenu = new JButton("Volver a menu principal");
		btnVolverAMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				frmEstadoDeCuenta.dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGap(54)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(10)
										.addComponent(lblSaldoCuentaCorriente).addGap(49).addComponent(lblmonto))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblTransaccionesAConsultar)
										.addGap(30).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 156,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup().addGap(137).addComponent(btnVolverAMenu)))
				.addContainerGap(44, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(22)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(3).addComponent(lblTransaccionesAConsultar))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSaldoCuentaCorriente).addComponent(lblmonto))
				.addGap(23).addComponent(btnVolverAMenu).addContainerGap()));
		panel.setLayout(gl_panel);

	}

	public void setVisible(boolean isVisible) {
		this.frmEstadoDeCuenta.setVisible(isVisible);
	}

	public void cargarUsuario() {
	//	String nombreUsuario = prefs.get("USERNAME", null);
	//	user = SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);
		user = SistemaUsuarios.getInstance().buscarUsuarioById("041b9ec5-d99d-4f80-8db8-d3a69fa5b137");
	}
}
