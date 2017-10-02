package edu.uade.api.tpo.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.List;
import java.awt.Choice;
import java.awt.Color;
import java.awt.TextArea;
import java.awt.Label;
import javax.swing.JTextField;
import java.awt.ScrollPane;
import java.awt.Button;
import javax.swing.JMenuItem;

public class ModificarProducto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarProducto frame = new ModificarProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModificarProducto() {
		setTitle("Modificar Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccioneElProducto = new JLabel("Seleccione el producto que desea modificar");
		lblSeleccioneElProducto.setBounds(0, 6, 348, 16);
		contentPane.add(lblSeleccioneElProducto);
		
		JLabel ModificarDescripcion = new JLabel("Modificar Descripcion");
		ModificarDescripcion.setBounds(0, 109, 284, 16);
		contentPane.add(ModificarDescripcion);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 128, 293, 47);
		contentPane.add(textArea);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(6, 206, 61, 16);
		contentPane.add(lblPrecio);
		
		textField = new JTextField();
		textField.setToolTipText("Nuevo Precio\n");
		textField.setBounds(79, 201, 176, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		Button btnAccept = new Button("Aceptar");
		btnAccept.setBounds(167, 249, 117, 29);
		contentPane.add(btnAccept);
		
		Button btnCancel = new Button("Cancelar");
		btnCancel.setBounds(290, 249, 117, 29);
		contentPane.add(btnCancel);
		
		List list = new List();
		list.setBounds(20, 28, 235, 76);
		contentPane.add(list);
	}
		
}
