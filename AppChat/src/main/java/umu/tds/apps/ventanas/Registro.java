package umu.tds.apps.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.io.IOException;
import java.net.URL;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain frame = new VentanaMain();
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
	public Registro() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(420, 160, 716, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setForeground(new Color(255, 255, 255));
		botonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonCancelar.setBackground(new Color(255, 20, 147));
		panel.add(botonCancelar);
		//creame un evento para el boton de cancelar, que cuando lo pulses se oculte la ventana de registro la ventana de registro y vuelva a hacerse visible la ventana de login implementadolo con una expresion lambda
		botonCancelar.addActionListener(e -> {
			this.setVisible(false);
			//hacer visible de nuevo la ventana de login
			Login window = new Login();
			window.frame.setVisible(true);
		});
		
		JLabel lblNewLabel_1 = new JLabel("       ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setToolTipText("         ");
		panel.add(lblNewLabel_1);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonAceptar.setBackground(new Color(255, 20, 147));
		panel.add(botonAceptar);
		botonAceptar.addActionListener(e -> {
			//crear aqui el metodo para registrar al usuario completo
			this.setVisible(false);
			//hacer visible de nuevo la ventana de login
			Login window = new Login();
			window.frame.setVisible(true);
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registro", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{5, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel etqNombre = new JLabel("Nombre");
		etqNombre.setForeground(new Color(255, 20, 147));
		etqNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etqNombre = new GridBagConstraints();
		gbc_etqNombre.anchor = GridBagConstraints.EAST;
		gbc_etqNombre.insets = new Insets(0, 0, 5, 5);
		gbc_etqNombre.gridx = 2;
		gbc_etqNombre.gridy = 1;
		panel_1.add(etqNombre, gbc_etqNombre);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(15);
		
		JLabel lblNewLabel = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel etqApellidos = new JLabel("Apellidos");
		etqApellidos.setForeground(new Color(255, 20, 147));
		etqApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etqApellidos = new GridBagConstraints();
		gbc_etqApellidos.anchor = GridBagConstraints.EAST;
		gbc_etqApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_etqApellidos.gridx = 2;
		gbc_etqApellidos.gridy = 3;
		panel_1.add(etqApellidos, gbc_etqApellidos);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 3;
		panel_1.add(textField_1, gbc_textField_1);
		textField_1.setColumns(15);
		
		JLabel lblNewLabel_2 = new JLabel("  ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 4;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel etqTelefono = new JLabel("Telefono");
		etqTelefono.setForeground(new Color(255, 20, 147));
		etqTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etqTelefono = new GridBagConstraints();
		gbc_etqTelefono.anchor = GridBagConstraints.EAST;
		gbc_etqTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_etqTelefono.gridx = 2;
		gbc_etqTelefono.gridy = 5;
		panel_1.add(etqTelefono, gbc_etqTelefono);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 5;
		panel_1.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 6;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel etqContraseña = new JLabel("Contraseña");
		etqContraseña.setForeground(new Color(255, 20, 147));
		etqContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etqContraseña = new GridBagConstraints();
		gbc_etqContraseña.anchor = GridBagConstraints.EAST;
		gbc_etqContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_etqContraseña.gridx = 2;
		gbc_etqContraseña.gridy = 7;
		panel_1.add(etqContraseña, gbc_etqContraseña);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.WEST;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 7;
		panel_1.add(textField_3, gbc_textField_3);
		textField_3.setColumns(15);
		
		JLabel etq2Contraseña = new JLabel("Contraseña");
		etq2Contraseña.setForeground(new Color(255, 20, 147));
		etq2Contraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etq2Contraseña = new GridBagConstraints();
		gbc_etq2Contraseña.anchor = GridBagConstraints.EAST;
		gbc_etq2Contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_etq2Contraseña.gridx = 5;
		gbc_etq2Contraseña.gridy = 7;
		panel_1.add(etq2Contraseña, gbc_etq2Contraseña);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.anchor = GridBagConstraints.WEST;
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.gridx = 6;
		gbc_textField_5.gridy = 7;
		panel_1.add(textField_5, gbc_textField_5);
		textField_5.setColumns(15);
		
		JLabel lblNewLabel_4 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 8;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel etqFecha = new JLabel("Fecha");
		etqFecha.setForeground(new Color(255, 20, 147));
		etqFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etqFecha = new GridBagConstraints();
		gbc_etqFecha.anchor = GridBagConstraints.EAST;
		gbc_etqFecha.insets = new Insets(0, 0, 5, 5);
		gbc_etqFecha.gridx = 2;
		gbc_etqFecha.gridy = 9;
		panel_1.add(etqFecha, gbc_etqFecha);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd MMM yy");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.anchor = GridBagConstraints.WEST;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.VERTICAL;
		gbc_dateChooser.gridx = 3;
		gbc_dateChooser.gridy = 9;
		panel_1.add(dateChooser, gbc_dateChooser);
		
		JLabel etqSaludo = new JLabel("Saludo");
		etqSaludo.setForeground(new Color(255, 20, 147));
		etqSaludo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etqSaludo = new GridBagConstraints();
		gbc_etqSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_etqSaludo.anchor = GridBagConstraints.EAST;
		gbc_etqSaludo.gridx = 2;
		gbc_etqSaludo.gridy = 11;
		panel_1.add(etqSaludo, gbc_etqSaludo);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.anchor = GridBagConstraints.WEST;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 11;
		panel_1.add(textField_4, gbc_textField_4);
		textField_4.setColumns(20);
		
		JLabel etqImagen2 = new JLabel("Imagen : ");
		etqImagen2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		etqImagen2.setForeground(new Color(255, 20, 147));
		GridBagConstraints gbc_etqImagen2 = new GridBagConstraints();
		gbc_etqImagen2.anchor = GridBagConstraints.EAST;
		gbc_etqImagen2.insets = new Insets(0, 0, 5, 5);
		gbc_etqImagen2.gridx = 5;
		gbc_etqImagen2.gridy = 11;
		panel_1.add(etqImagen2, gbc_etqImagen2);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.gridx = 6;
		gbc_textField_6.gridy = 11;
		panel_1.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JLabel imagen = new JLabel("");
		GridBagConstraints gbc_imagen = new GridBagConstraints();
		gbc_imagen.gridx = 6;
		gbc_imagen.gridy = 12;
		panel_1.add(imagen, gbc_imagen);
		//hacer que el usuario intriduzca una url de una imagen de interner y que se muestre en el label imagen
		textField_6.addActionListener(e -> {
			String urlImagen = textField_6.getText();
			URL url = getClass().getResource(urlImagen);
			if (url != null) {
				Image fotoImage = null;
				try {
					fotoImage = ImageIO.read(url).getScaledInstance(130, 130, Image.SCALE_DEFAULT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				imagen.setIcon(new ImageIcon(fotoImage));
			}
		});
		
		this.setTitle("UNICORNCHAT");
		this.setVisible(true);
		
	}

}
