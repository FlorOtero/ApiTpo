package edu.uade.api.tpo.ui;

import edu.uade.api.tpo.controller.SistemaCuentaCorriente;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.model.ItemCtaCte;
import edu.uade.api.tpo.model.Usuario;
import edu.uade.api.tpo.ui.custom.ButtonColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MiCuentaCorriente {

	private static final Logger logger = LoggerFactory.getLogger(MiCuentaCorriente.class);
	private Usuario user;
	private JFrame frmCuentaCorriente;
	private JTable table;
	private TableRowSorter<DefaultTableModel> sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiCuentaCorriente window = new MiCuentaCorriente();
					window.frmCuentaCorriente.setVisible(true);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiCuentaCorriente() {
		loadUser();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCuentaCorriente = new JFrame();
		frmCuentaCorriente.setTitle("Cuenta Corriente | API");
		frmCuentaCorriente.setBounds(100, 100, 780, 620);
		frmCuentaCorriente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmCuentaCorriente.setJMenuBar(menuBar);

		JButton btnInicio = new JButton("");
		String homePath = new File("src/main/resources/house.png").getAbsolutePath();
		btnInicio.setIcon(new ImageIcon(homePath));
		menuBar.add(btnInicio);

		btnInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmCuentaCorriente.dispose();
			}
		});

		JMenu mnMiCuenta = new JMenu("Mi Cuenta");
		menuBar.add(mnMiCuenta);

		JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta Corriente");
		mnMiCuenta.add(mntmCuentaCorriente);
		mntmCuentaCorriente.setEnabled(false);

		JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
		mnMiCuenta.add(mntmMiReputacion);
		mntmMiReputacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reputacion reputacion = new Reputacion();
				reputacion.setVisible(true);
				frmCuentaCorriente.dispose();
			}
		});

		JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
		mnMiCuenta.add(mntmMiUsuario);
		mntmMiUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MiUsuario miUsuario = new MiUsuario();
				miUsuario.setVisible(true);
				frmCuentaCorriente.dispose();
			}
		});

		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
		mnMiCuenta.add(mntmCerrarSesion);
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingresar ingreso = new Ingresar();
				ingreso.setVisible(true);
				frmCuentaCorriente.dispose();
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
				frmCuentaCorriente.dispose();
			}
		});

		JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
		mnPublicaciones.add(mntmMisPublicaciones);
		frmCuentaCorriente.getContentPane().setLayout(null);
		mntmMisPublicaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MisPublicaciones misPublicaciones = new MisPublicaciones();
				misPublicaciones.setVisible(true);
				frmCuentaCorriente.dispose();
			}
		});

		JLabel lblBreadcrumb = new JLabel("Inicio > Mi Cuenta > Cuenta Corriente");
		lblBreadcrumb.setBounds(10, 20, 760, 16);
		frmCuentaCorriente.getContentPane().add(lblBreadcrumb);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 40, 780, 12);
		frmCuentaCorriente.getContentPane().add(separator);

		JLabel lblFiltrarResultados = new JLabel("Filtrar resultados:");
		lblFiltrarResultados.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblFiltrarResultados.setBounds(10, 60, 760, 20);
		frmCuentaCorriente.getContentPane().add(lblFiltrarResultados);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 90, 375, 16);
		frmCuentaCorriente.getContentPane().add(lblTipo);

		String[] tipoStrings = { "Todos", "Compras", "Ventas", "Comisiones" };
		JComboBox comboTipo = new JComboBox(tipoStrings);
		comboTipo.setBounds(10, 110, 375, 30);
		comboTipo.setSelectedIndex(0);
		frmCuentaCorriente.getContentPane().add(comboTipo);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(395, 92, 375, 16);
		frmCuentaCorriente.getContentPane().add(lblEstado);

		String[] estadoStrings = { "Todos", "Aprobadas", "Canceladas" };
		JComboBox comboEstado = new JComboBox(estadoStrings);
		comboEstado.setBounds(395, 110, 375, 30);
		comboEstado.setSelectedIndex(0);
		frmCuentaCorriente.getContentPane().add(comboEstado);

		String[] columnNames = { "Fecha", "Título", "Estado", "Tipo", "Monto", "Calificación", "id" };
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, columnNames));
		table.setAutoCreateRowSorter(true);
		table.getColumnModel().getColumn(0).setMinWidth(90);
		table.getColumnModel().getColumn(0).setMaxWidth(90);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setMaxWidth(80);
		table.getColumnModel().getColumn(4).setMaxWidth(120);
		table.getColumnModel().getColumn(5).setMaxWidth(80);
		// Ocultamos la columna con el id de operacion
		table.removeColumn(table.getColumnModel().getColumn(6));

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);
		//Por default ordenamos la tabla por fecha
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();

		comboTipo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object item = e.getItem();
					String filtro;

					switch (item.toString()) {
					case "Compras":
						filtro = "compra";
						break;
					case "Ventas":
						filtro = "venta";
						break;
					case "Comisiones":
						filtro = "comisión";
						break;
					default:
						filtro = "";
						break;
					}
					System.out.println(item);
					RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(filtro, 3);
					sorter.setRowFilter(rf);
				}
			}
		});

		comboEstado.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object item = e.getItem();
					String filtro;

					switch (item.toString()) {
					case "Aprobadas":
						filtro = "A";
						break;
					case "Canceladas":
						filtro = "C";
						break;
					default:
						filtro = "";
						break;
					}
					System.out.println(item);
					RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(filtro, 2);
					sorter.setRowFilter(rf);
				}
			}
		});

		Action showCalificacion = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				// int modelRow = Integer.valueOf(e.getActionCommand());
				String trid = (String) table.getModel().getValueAt(table.getSelectedRow(), 6);
				String action = (String) table.getModel().getValueAt(table.getSelectedRow(), 5);

				switch (action) {
				case "ver":
					VerCalificacion verCalificacion = new VerCalificacion(trid, "cuenta-corriente");
					verCalificacion.setVisible(true);
					frmCuentaCorriente.dispose();
					break;
				case "calificar":
					Calificar calificar = new Calificar(trid);
					calificar.setVisible(true);
					frmCuentaCorriente.dispose();
					break;
				}
			}
		};

		// Hacemos que la columna de calificación tenga un botón
		ButtonColumn buttonColumn = new ButtonColumn(table, showCalificacion, 5);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 150, 760, 300);
		table.setFillsViewportHeight(true);
		frmCuentaCorriente.getContentPane().add(scrollPane);

		JLabel lblSaldo = new JLabel("Saldo de la Cuenta Corriente:");
		lblSaldo.setBounds(10, 460, 190, 16);
		frmCuentaCorriente.getContentPane().add(lblSaldo);

		JLabel lblSaldoCtaCte = new JLabel("$" + Float.toString(user.getCuentaCorriente().getSaldo()));
		lblSaldoCtaCte.setBounds(200, 460, 570, 16);
		frmCuentaCorriente.getContentPane().add(lblSaldoCtaCte);

		JTextPane txtMensajes = new JTextPane();
		txtMensajes.setText("");
		txtMensajes.setBackground(SystemColor.window);
		txtMensajes.setBounds(10, 490, 760, 40);
		frmCuentaCorriente.getContentPane().add(txtMensajes);

		try {
			List<ItemCtaCte> movimientos = SistemaCuentaCorriente.getInstance()
					.consultarMovimientos(user.getNombreUsuario());
			for (ItemCtaCte item : movimientos) {

				String calificacion = "-";

				if (!item.isComision()) {
					if (item.isCalificada()) {
						calificacion = "ver";
					} else {
						if (item.getTipo().equals("compra")) {
							calificacion = "calificar";
						}
					}
				}

				model.addRow(new Object[] { item.getFecha(), // fecha
						item.getTitulo(), // titulo
						item.getEstado(), // estado
						item.getTipo(), // tipo
						item.getMonto(), // monto
						calificacion, item.getIdOperacion() // hidden
				});
			}

		} catch (BusinessException e) {
			txtMensajes.setText(e.getMessage());

		}

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(650, 540, 120, 30);
		frmCuentaCorriente.getContentPane().add(btnVolver);

		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				frmCuentaCorriente.dispose();
			}
		});
	}

	public void setVisible(boolean isVisible) {
		this.frmCuentaCorriente.setVisible(isVisible);
	}

	private void loadUser() {
		user = SistemaUsuarios.getInstance().getUsuarioActivo();
	}
}
