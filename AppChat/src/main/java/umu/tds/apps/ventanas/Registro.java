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
import java.awt.Toolkit;
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
import javax.swing.JPasswordField;
import javax.swing.JTextPane;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JTextField textURL;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

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
		//cambiar icono de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEjemplo.class.getResource("/umu/tds/apps/resources/icono app.png")));
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
		gbl_panel_1.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 10, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		
		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.gridx = 3;
		gbc_textNombre.gridy = 1;
		panel_1.add(textNombre, gbc_textNombre);
		textNombre.setColumns(15);
		
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
		
		textApellido = new JTextField();
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textApellido.gridx = 3;
		gbc_textApellido.gridy = 3;
		panel_1.add(textApellido, gbc_textApellido);
		textApellido.setColumns(15);
		
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
		
		textTelefono = new JTextField();
		GridBagConstraints gbc_textTelefono = new GridBagConstraints();
		gbc_textTelefono.anchor = GridBagConstraints.WEST;
		gbc_textTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_textTelefono.gridx = 3;
		gbc_textTelefono.gridy = 5;
		panel_1.add(textTelefono, gbc_textTelefono);
		textTelefono.setColumns(10);
		
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
		
		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 7;
		panel_1.add(passwordField, gbc_passwordField);
		
		JLabel etq2Contraseña = new JLabel("Contraseña");
		etq2Contraseña.setForeground(new Color(255, 20, 147));
		etq2Contraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etq2Contraseña = new GridBagConstraints();
		gbc_etq2Contraseña.anchor = GridBagConstraints.EAST;
		gbc_etq2Contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_etq2Contraseña.gridx = 5;
		gbc_etq2Contraseña.gridy = 7;
		panel_1.add(etq2Contraseña, gbc_etq2Contraseña);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(15);
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.anchor = GridBagConstraints.WEST;
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.gridx = 6;
		gbc_passwordField_1.gridy = 7;
		panel_1.add(passwordField_1, gbc_passwordField_1);
		
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
		
		JLabel etqImagen2 = new JLabel("Imagen : ");
		etqImagen2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		etqImagen2.setForeground(new Color(255, 20, 147));
		GridBagConstraints gbc_etqImagen2 = new GridBagConstraints();
		gbc_etqImagen2.anchor = GridBagConstraints.EAST;
		gbc_etqImagen2.insets = new Insets(0, 0, 5, 5);
		gbc_etqImagen2.gridx = 5;
		gbc_etqImagen2.gridy = 11;
		panel_1.add(etqImagen2, gbc_etqImagen2);
		
		textURL = new JTextField();
		GridBagConstraints gbc_textURL = new GridBagConstraints();
		gbc_textURL.fill = GridBagConstraints.HORIZONTAL;
		gbc_textURL.insets = new Insets(0, 0, 5, 0);
		gbc_textURL.gridx = 6;
		gbc_textURL.gridy = 11;
		panel_1.add(textURL, gbc_textURL);
		textURL.setColumns(10);
		
		JLabel etqSaludo = new JLabel("Saludo");
		etqSaludo.setForeground(new Color(255, 20, 147));
		etqSaludo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_etqSaludo = new GridBagConstraints();
		gbc_etqSaludo.anchor = GridBagConstraints.NORTHEAST;
		gbc_etqSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_etqSaludo.gridx = 2;
		gbc_etqSaludo.gridy = 12;
		panel_1.add(etqSaludo, gbc_etqSaludo);
		
		JTextPane textPaneSaludo = new JTextPane();
		GridBagConstraints gbc_textPaneSaludo = new GridBagConstraints();
		gbc_textPaneSaludo.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneSaludo.fill = GridBagConstraints.BOTH;
		gbc_textPaneSaludo.gridx = 3;
		gbc_textPaneSaludo.gridy = 12;
		panel_1.add(textPaneSaludo, gbc_textPaneSaludo);
		
		JLabel imagen = new JLabel("");
		GridBagConstraints gbc_imagen = new GridBagConstraints();
		gbc_imagen.insets = new Insets(0, 0, 5, 0);
		gbc_imagen.gridx = 6;
		gbc_imagen.gridy = 12;
		panel_1.add(imagen, gbc_imagen);
		//hacer que el usuario intriduzca una url de una imagen de interner y que se muestre en el label imagen
		textURL.addActionListener(e -> {
			String urlImagen = textURL.getText();
			URL url = getClass().getResource(urlImagen);
			if (url != null) {
				Image fotoImage = null;
				try {
					fotoImage = ImageIO.read(url).getScaledInstance(130, 130, Image.SCALE_DEFAULT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				imagen.setIcon(new ImageIcon(Registro.class.getResource("/umu/tds/apps/resources/anadir-imagen.png")));
			}
		});
		
		this.setTitle("UNICORNCHAT");
		this.setVisible(true);
		
	}

}
