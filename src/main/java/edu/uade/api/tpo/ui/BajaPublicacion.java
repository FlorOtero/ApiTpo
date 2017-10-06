package edu.uade.api.tpo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BajaPublicacion {

	private JFrame frmEliminarPublicacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaPublicacion window = new BajaPublicacion();
					window.frmEliminarPublicacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BajaPublicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEliminarPublicacion = new JFrame();
		frmEliminarPublicacion.setTitle("Eliminar Publicacion");
		frmEliminarPublicacion.getContentPane().setBackground(new Color(255, 255, 224));
		frmEliminarPublicacion.getContentPane().setLayout(null);
		
		JLabel lblSeleccionePublicacionA = new JLabel("Seleccione publicacion a eliminar");
		lblSeleccionePublicacionA.setBounds(16, 70, 218, 16);
		frmEliminarPublicacion.getContentPane().add(lblSeleccionePublicacionA);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(266, 66, 135, 27);
		frmEliminarPublicacion.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta= JOptionPane.showConfirmDialog(null,"Esta seguro de que quiere eliminar la publicación?", "Aviso", JOptionPane.OK_CANCEL_OPTION);
				if (JOptionPane.OK_OPTION == rta) {
					JOptionPane.showConfirmDialog(null,"Su publicacion se ha eliminado con exito","Confirmacion",JOptionPane.PLAIN_MESSAGE);
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					frmEliminarPublicacion.dispose();
				}
				
			}
		});
		btnNewButton.setBounds(58, 208, 117, 29);
		frmEliminarPublicacion.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rta= JOptionPane.showConfirmDialog(null,"Seguro que quiere salir?", "Aviso", JOptionPane.OK_CANCEL_OPTION);
				if (JOptionPane.OK_OPTION == rta) {
					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					frmEliminarPublicacion.dispose();
				}
			}
		});
		btnNewButton_1.setBounds(239, 208, 117, 29);
		frmEliminarPublicacion.getContentPane().add(btnNewButton_1);
		frmEliminarPublicacion.setBounds(100, 100, 450, 300);
		frmEliminarPublicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setVisible(boolean isVisible) {
		this.frmEliminarPublicacion.setVisible(isVisible);
	}
}
