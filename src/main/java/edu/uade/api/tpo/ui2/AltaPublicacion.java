package edu.uade.api.tpo.ui2;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AltaPublicacion implements ItemListener {

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
    private JComboBox<String> comboBoxGarantia;
    private JLabel lblFechaFinPublicacion;
    private Publicacion publicacion;

    public AltaPublicacion() {
        initialize();
    }

    public AltaPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmNuevaPublicacion = new JFrame();
        if (this.publicacion != null) {
            frmNuevaPublicacion.setTitle("Modificar Publicación | API");
        } else {
            frmNuevaPublicacion.setTitle("Nueva Publicación | API");
        }
        frmNuevaPublicacion.setBounds(100, 100, 780, 750);
        frmNuevaPublicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frmNuevaPublicacion.setJMenuBar(menuBar);

        JButton btnInicio = new JButton("");
        String homePath = new File("src/main/resources/house.png").getAbsolutePath();
        btnInicio.setIcon(new ImageIcon(homePath));
        menuBar.add(btnInicio);

        btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
                frmNuevaPublicacion.dispose();
            }
        });

        JMenu mnMiCuenta = new JMenu("Mi Cuenta");
        menuBar.add(mnMiCuenta);

        JMenuItem mntmCuentaCorriente = new JMenuItem("Cuenta Corriente");
        mnMiCuenta.add(mntmCuentaCorriente);
        mntmCuentaCorriente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MiCuentaCorriente cc = new MiCuentaCorriente();
                cc.setVisible(true);
                frmNuevaPublicacion.dispose();
            }
        });

        JMenuItem mntmMiReputacion = new JMenuItem("Mi Reputación");
        mnMiCuenta.add(mntmMiReputacion);
        mntmMiReputacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reputacion reputacion = new Reputacion();
                reputacion.setVisible(true);
                frmNuevaPublicacion.dispose();
            }
        });

        JMenuItem mntmMiUsuario = new JMenuItem("Mi Usuario");
        mnMiCuenta.add(mntmMiUsuario);
        mntmMiUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MiUsuario miUsuario = new MiUsuario();
                miUsuario.setVisible(true);
                frmNuevaPublicacion.dispose();
            }
        });

        JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesión");
        mnMiCuenta.add(mntmCerrarSesion);
        mntmCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ingresar ingreso = new Ingresar();
                ingreso.setVisible(true);
                frmNuevaPublicacion.dispose();
            }
        });

        JMenu mnPublicaciones = new JMenu("Publicaciones");
        menuBar.add(mnPublicaciones);

        JMenuItem mntmNuevaPublicacion = new JMenuItem("Nueva Publicación");
        mnPublicaciones.add(mntmNuevaPublicacion);
        mntmNuevaPublicacion.setEnabled(false);

        JMenuItem mntmMisPublicaciones = new JMenuItem("Mis Publicaciones");
        mnPublicaciones.add(mntmMisPublicaciones);
        frmNuevaPublicacion.getContentPane().setLayout(null);
        mntmMisPublicaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MisPublicaciones misPublicaciones = new MisPublicaciones();
                misPublicaciones.setVisible(true);
                frmNuevaPublicacion.dispose();
            }
        });

        JLabel lblBreadcrumb = null;
        if (this.publicacion == null) {
            lblBreadcrumb = new JLabel("Inicio > Publicaciones > Nueva Publicación");
        } else {
            lblBreadcrumb = new JLabel("Inicio > Publicaciones > Modificar Publicación");
        }

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
        if (publicacion != null) {
            txtTitulo.setText(publicacion.getArticulo().getNombre());
        }
        frmNuevaPublicacion.getContentPane().add(txtTitulo);
        txtTitulo.setColumns(10);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(10, 120, 760, 16);
        frmNuevaPublicacion.getContentPane().add(lblDescripcion);

        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        if (publicacion != null) {
            textAreaDescripcion.setText(publicacion.getArticulo().getDescripcion());
        }
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

        String[] tipoPublicacionStrings = {COMPRA_INMEDIATA, SUBASTA};
        comboBoxTipoPublicacion = new JComboBox(tipoPublicacionStrings);
        comboBoxTipoPublicacion.setBounds(10, 380, 360, 27);
        frmNuevaPublicacion.getContentPane().add(comboBoxTipoPublicacion);

        panelTipoPublicacion = new JPanel();
        panelTipoPublicacion.setBounds(10, 410, 360, 150);
        frmNuevaPublicacion.getContentPane().add(panelTipoPublicacion);
        panelTipoPublicacion.setLayout(new CardLayout(0, 0));

        if (this.publicacion != null) {
            if (this.publicacion instanceof Subasta) {
                comboBoxTipoPublicacion.setSelectedIndex(1);
            } else {
                comboBoxTipoPublicacion.setSelectedIndex(0);
            }
            comboBoxTipoPublicacion.setEnabled(false);
        }
        comboBoxTipoPublicacion.addItemListener(this);

        JPanel panelCompraInmediata = new JPanel();
        panelTipoPublicacion.add(panelCompraInmediata, COMPRA_INMEDIATA);
        panelCompraInmediata.setLayout(null);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(6, 6, 360, 16);
        panelCompraInmediata.add(lblPrecio);

        lblFechaFinPublicacion = new JLabel();
        lblFechaFinPublicacion.setBounds(150, 640, 340, 16);
        frmNuevaPublicacion.getContentPane().add(lblFechaFinPublicacion);
        setFechaHastaPublicacion();

        txtPrecio = new JTextField();
        txtPrecio.setBounds(6, 27, 180, 30);
        if (this.publicacion != null && publicacion instanceof Publicacion) {
            txtPrecio.setText(new Float(publicacion.getPrecio()).toString());
        }
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
        if (publicacion != null && publicacion instanceof Subasta) {
            txtPrecioInicial.setText(new Float(((Subasta) publicacion).getPrecioInicial()).toString());
        }
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
        if (publicacion != null && publicacion instanceof Subasta) {
            txtPrecioMinimo.setText(new Float(((Subasta) publicacion).getPrecioMin()).toString());
        }
        panelSubasta.add(txtPrecioMinimo);
        txtPrecioMinimo.setColumns(10);

        JLabel lblPesosArgentinos_2 = new JLabel("pesos argentinos");
        lblPesosArgentinos_2.setBounds(225, 50, 130, 30);
        panelSubasta.add(lblPesosArgentinos_2);

        JLabel lblVigencia = new JLabel("Vigencia:");
        lblVigencia.setBounds(0, 90, 90, 30);
        panelSubasta.add(lblVigencia);

        String[] vigenciaStrings = {"30", "60", "90"};
        comboBoxVigencia = new JComboBox(vigenciaStrings);
        comboBoxVigencia.setBounds(100, 90, 120, 30);
        comboBoxVigencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setFechaHastaPublicacion();
            }
        });
        if (publicacion != null && publicacion instanceof Subasta) {
            comboBoxVigencia.setSelectedIndex((((Subasta) publicacion).getDiasVigencia() / 30) - 1);
        }
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

        String[] categoriaStrings = {PRODUCTO, SERVICIO};
        comboBoxCategoria = new JComboBox(categoriaStrings);
        comboBoxCategoria.setBounds(410, 380, 360, 27);
        frmNuevaPublicacion.getContentPane().add(comboBoxCategoria);

        panelCategoria = new JPanel();
        panelCategoria.setBounds(410, 410, 360, 150);
        frmNuevaPublicacion.getContentPane().add(panelCategoria);
        panelCategoria.setLayout(new CardLayout(0, 0));

        comboBoxCategoria.addItemListener(this);
        if (publicacion != null) {
            comboBoxCategoria.setSelectedIndex(publicacion.getArticulo() instanceof Servicio ? 1 : 0);
            comboBoxCategoria.setEnabled(false);
        }

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
        comboBoxGarantia = new JComboBox(Arrays.stream(TipoPeriodo.values()).map(t -> t.getVal()).toArray());
        comboBoxGarantia.setBounds(122, 38, 170, 30);
        if (publicacion != null && publicacion.getArticulo() != null && publicacion.getArticulo() instanceof Producto) {
            comboBoxGarantia.setSelectedIndex(((Producto) publicacion.getArticulo()).getGarantia().getTipo() == TipoPeriodo.MENSUAL ? 0 : 1);
        }
        panelProducto.add(comboBoxGarantia);

        JPanel panelServicio = new JPanel();
        panelCategoria.add(panelServicio, SERVICIO);
        panelServicio.setLayout(null);

        JLabel lblTipoDeContratacion = new JLabel("Tipo de Contratación:");
        lblTipoDeContratacion.setBounds(0, 10, 140, 30);
        panelServicio.add(lblTipoDeContratacion);

        comboBoxTipoContratacion = new JComboBox(Arrays.stream(TipoContratacion.values()).map(t -> t.getVal()).toArray());
        comboBoxTipoContratacion.setBounds(150, 10, 210, 30);
        if (publicacion != null && publicacion.getArticulo() != null && publicacion.getArticulo() instanceof Servicio) {
            comboBoxTipoContratacion.setSelectedIndex(((Servicio) publicacion.getArticulo()).getContratacion() == TipoContratacion.ABONO ? 0 : 1);
        }
        panelServicio.add(comboBoxTipoContratacion);

        JLabel lblCertificados = new JLabel("Certificados:");
        lblCertificados.setBounds(0, 50, 360, 16);
        panelServicio.add(lblCertificados);

        txtCertificados = new JTextField();
        txtCertificados.setBounds(0, 70, 200, 30);
        panelServicio.add(txtCertificados);
        txtCertificados.setColumns(10);

        JButton btnCargarCertificado = new JButton("Cargar certificado");
        btnCargarCertificado.setBounds(210, 70, 150, 30);
        panelServicio.add(btnCargarCertificado);

        JTextPane textPaneCertificados = new JTextPane();
        //textPaneCertificados.setText("Acá se tienen que ir mostrando los certificados cargados");
        textPaneCertificados.setBackground(SystemColor.window);
        textPaneCertificados.setEditable(false);
        textPaneCertificados.setBounds(0, 110, 360, 40);
        panelServicio.add(textPaneCertificados);

        JLabel lblMedioDePago = new JLabel("Medio de Pago aceptados:");
        lblMedioDePago.setBounds(10, 580, 760, 16);
        frmNuevaPublicacion.getContentPane().add(lblMedioDePago);

        pagoEfectivoCheckbox = new JCheckBox("Efectivo");
        pagoEfectivoCheckbox.setBounds(10, 600, 130, 23);
        pagoEfectivoCheckbox.setSelected(publicacion != null && publicacion.getMediosPago().contains(MedioPago.EFECTIVO));

        frmNuevaPublicacion.getContentPane().add(pagoEfectivoCheckbox);

        pagoTarjetaCreditoCheckbox = new JCheckBox("Tarjeta de Crédito");
        pagoTarjetaCreditoCheckbox.setBounds(140, 600, 150, 23);
        pagoTarjetaCreditoCheckbox.setSelected(publicacion != null && publicacion.getMediosPago().contains(MedioPago.TARJETA_CREDITO));
        frmNuevaPublicacion.getContentPane().add(pagoTarjetaCreditoCheckbox);

        pagoTransferenciaCheckbox = new JCheckBox("Transferencia Bancaria");
        pagoTransferenciaCheckbox.setBounds(330, 600, 180, 23);
        pagoTransferenciaCheckbox.setSelected(publicacion != null && publicacion.getMediosPago().contains(MedioPago.TRANSFERENCIA_BANCARIA));
        frmNuevaPublicacion.getContentPane().add(pagoTransferenciaCheckbox);

        JLabel lblFinPublicacion = new JLabel("Fin de la Publicación:");
        lblFinPublicacion.setBounds(10, 640, 140, 16);
        frmNuevaPublicacion.getContentPane().add(lblFinPublicacion);

        JButton btnConfirmar = null;
        if(publicacion != null) {
            btnConfirmar = new JButton("Guardar");
            btnConfirmar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    modificar();
                }
            });
        } else {
            btnConfirmar = new JButton("Publicar");
            btnConfirmar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    publicar();
                }
            });
        }
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

        if (publicacion != null) {
            JButton btnEliminarPublicacion = new JButton("Eliminar Publicación");
            btnEliminarPublicacion.setBounds(10, 670, 180, 30);
            btnEliminarPublicacion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar su publicación?\n¡Esta acción no se puede deshacer!", "Alerta", JOptionPane.WARNING_MESSAGE);
                    if (opcion == JOptionPane.YES_OPTION) {
                        if (publicacion instanceof Subasta) {
                            SistemaPublicaciones.getInstance().eliminarSubasta((Subasta) publicacion);
                        } else {
                            SistemaPublicaciones.getInstance().eliminarPublicacion(publicacion);
                        }
                        JOptionPane.showMessageDialog(null, "Su usuario se ha eliminado exitosamente!", "Aviso", JOptionPane.PLAIN_MESSAGE);
                        goInicio();
                    }
                }
            });
            frmNuevaPublicacion.getContentPane().add(btnEliminarPublicacion);
        }
    }

    private void setFechaHastaPublicacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        if (publicacion != null) {
            cal.setTime(publicacion.getFechaDesde());
        }
        if (this.comboBoxTipoPublicacion.getSelectedItem().toString().equals(COMPRA_INMEDIATA)) {
            cal.add(Calendar.DAY_OF_MONTH, 90);
        } else {
            cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(comboBoxVigencia.getSelectedItem().toString()));
        }
        lblFechaFinPublicacion.setText(sdf.format(cal.getTime()));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        CardLayout cl;
        String item = (String) e.getItem();

        if (item.equals(SUBASTA) || item.equals(COMPRA_INMEDIATA)) {
            cl = (CardLayout) (panelTipoPublicacion.getLayout());
            cl.show(panelTipoPublicacion, item);
        } else if (item.equals(PRODUCTO) || item.equals(SERVICIO)) {
            cl = (CardLayout) (panelCategoria.getLayout());
            cl.show(panelCategoria, item);
        }
        setFechaHastaPublicacion();
    }

    private void publicar() {
        if (validateForm()) {
            Usuario user = SistemaUsuarios.getInstance().getUsuarioActivo();
            List<MedioPago> mediosPagos = getMediosPago();
            Articulo articulo = null;
            if (comboBoxCategoria.getSelectedItem().toString() == PRODUCTO) {
                articulo = crearProducto();
            } else {
                articulo = crearServicio();
            }

            try {
                if (comboBoxTipoPublicacion.getSelectedItem().toString() == SUBASTA) {
                    Subasta subasta = new Subasta();
                    subasta.setPrecioInicial(Float.parseFloat(txtPrecioInicial.getText()));
                    subasta.setPrecioMin(Float.parseFloat(txtPrecioMinimo.getText()));
                    subasta.setDiasVigencia(Integer.parseInt(comboBoxVigencia.getSelectedItem().toString()));
                    subasta.setArticulo(articulo);
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

    private void modificar() {
        if(validateForm()) {
            this.publicacion.setMediosPago(getMediosPago());
            //Campos genéricos
            if (comboBoxCategoria.getSelectedItem().toString() == PRODUCTO) {
                Producto prod = (Producto) publicacion.getArticulo();
                prod.setNombre(txtTitulo.getText());
                prod.setDescripcion(textAreaDescripcion.getText());
                prod.fromImagesTokenized("");
                Garantia garantia = prod.getGarantia();
                garantia.setCantidad(Integer.parseInt(txtGarantia.getText()));
                garantia.setTipo(TipoPeriodo.findByValue(comboBoxGarantia.getSelectedItem().toString()));
            } else {
                Servicio servicio = (Servicio) publicacion.getArticulo();
                servicio.setNombre(txtTitulo.getText());
                servicio.setDescripcion(textAreaDescripcion.getText());
                servicio.fromImagesTokenized("");
                servicio.setCertificados(new ArrayList<>());
                servicio.setContratacion(TipoContratacion.findByValue(comboBoxTipoContratacion.getSelectedItem().toString()));
            }

            //campos específicos
            if (comboBoxTipoPublicacion.getSelectedItem().toString() == SUBASTA) {
                Subasta subasta = (Subasta) publicacion;
                subasta.setPrecioInicial(Float.parseFloat(txtPrecioInicial.getText()));
                subasta.setPrecioMin(Float.parseFloat(txtPrecioMinimo.getText()));
                subasta.setDiasVigencia(Integer.parseInt(comboBoxVigencia.getSelectedItem().toString()));
                SistemaPublicaciones.getInstance().modificarSubasta(subasta);
            } else {
                publicacion.setPrecio(Float.parseFloat(txtPrecio.getText()));
                SistemaPublicaciones.getInstance().modificarPublicacion(publicacion);
            }
            JOptionPane.showMessageDialog(null, "¡Los datos fueron modificados!", "Operacion Exitosa", JOptionPane.PLAIN_MESSAGE);
            goInicio();
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
        garantia.setTipo(TipoPeriodo.findByValue(comboBoxGarantia.getSelectedItem().toString()));

        producto.setGarantia(garantia);
        return producto;
    }

    private Servicio crearServicio() {
        Servicio servicio = new Servicio();
        servicio.setNombre(txtTitulo.getText());
        servicio.setDescripcion(textAreaDescripcion.getText());
        // producto.fromImagesTokenized(filepathField.toString());
        servicio.fromImagesTokenized("");
        servicio.setCertificados(new ArrayList<>());
        servicio.setContratacion(TipoContratacion.findByValue(comboBoxTipoContratacion.getSelectedItem().toString()));
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
