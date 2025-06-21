package umu.tds.apps.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import umu.tds.apps.Controlador.Controlador;
import umu.tds.apps.AppChat.Contacto;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

public class VentanaAddContacto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textNombre;
	private JTextField textTelefono;

	public VentanaAddContacto(DefaultListModel<String> modelo) { //cambiar el defaultlist por Contacto
		initialize(modelo);
	}

	private void initialize(DefaultListModel<String> modelo) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//cambiar icono de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEjemplo.class.getResource("/umu/tds/apps/resources/icono app.png")));
		setBounds(420, 160, 716, 553);
		this.setTitle("UNICORNCHAT");
		this.setVisible(true);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelCampos = new JPanel();
		getContentPane().add(panelCampos);
		GridBagLayout gbl_panelCampos = new GridBagLayout();
		gbl_panelCampos.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panelCampos.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		panelCampos.setLayout(gbl_panelCampos);

		JLabel lblAviso = new JLabel("");
		lblAviso.setIcon(new ImageIcon(VentanaAddContacto.class.getResource("/umu/tds/apps/resources/icono-usuario-ventanaMain.png")));
		GridBagConstraints gbc_lblAviso = new GridBagConstraints();
		gbc_lblAviso.insets = new Insets(0, 0, 5, 5);
		gbc_lblAviso.gridx = 1;
		gbc_lblAviso.gridy = 0;
		panelCampos.add(lblAviso, gbc_lblAviso);

		JLabel lblTextoIntroducir = new JLabel("Introduzca el nombre del contacto y su teléfono:");
		GridBagConstraints gbc_lblTextoIntroducir = new GridBagConstraints();
		gbc_lblTextoIntroducir.insets = new Insets(0, 0, 5, 0);
		gbc_lblTextoIntroducir.gridx = 2;
		gbc_lblTextoIntroducir.gridy = 0;
		panelCampos.add(lblTextoIntroducir, gbc_lblTextoIntroducir);

		JLabel lblNewLabel = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panelCampos.add(lblNewLabel, gbc_lblNewLabel);

		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 1;
		panelCampos.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Teléfono:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panelCampos.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textTelefono = new JTextField();
		GridBagConstraints gbc_textTelefono = new GridBagConstraints();
		gbc_textTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelefono.gridx = 2;
		gbc_textTelefono.gridy = 2;
		panelCampos.add(textTelefono, gbc_textTelefono);
		textTelefono.setColumns(10);

		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		getContentPane().add(panelBotones);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ---- INICIO BLOQUE DE PERSISTENCIA (comentado para pruebas de UI) ----
				/*
				int añadido = Controlador.INSTANCE.addContactoIndividual(textNombre.getText(), textTelefono.getText());

				if (añadido == 1) {
					JOptionPane.showMessageDialog(VentanaAddContacto.this,
							"No existe un usuario asociado al teléfono introducido", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (añadido == 2) {
					JOptionPane.showMessageDialog(VentanaAddContacto.this,
							"El teléfono introducido ya lo tienes agregado como contacto", "Error",
							JOptionPane.ERROR_MESSAGE);

				} else if (añadido == 3) {
					JOptionPane.showMessageDialog(VentanaAddContacto.this, "El teléfono introducido es tu teléfono",
							"Error", JOptionPane.ERROR_MESSAGE);

				} else if (añadido == 4) {
					JOptionPane.showMessageDialog(VentanaAddContacto.this, "El nombre introducido ya existe en tu lista de contactos",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					modelo.clear();
					List<Contacto> lista = Controlador.INSTANCE.recuperarTodosContactos();
					lista.forEach(modelo::addElement);
					dispose();
				}
				*/
				// ---- FIN BLOQUE DE PERSISTENCIA ----

				// Mensaje simulado para pruebas sin persistencia
				JOptionPane.showMessageDialog(VentanaAddContacto.this,
						"Simulación: Contacto añadido correctamente (sin persistencia)", "Información",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		panelBotones.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelBotones.add(btnCancelar);
	}
}

