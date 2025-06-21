package umu.tds.apps.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import umu.tds.apps.Controlador.Controlador;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class Login {

	JFrame frame;
	private JLabel telefono;
	private JPasswordField textContraseña;
	private JTextField textTelefono;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(420, 160, 681, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("UNICORNCHAT");
		frame.setResizable(false);
		//cambiar icono de la ventana
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEjemplo.class.getResource("/umu/tds/apps/resources/icono app.png")));
		
		JLabel titulo = new JLabel("AppChat");
		URL url1 = getClass().getResource("/umu/tds/apps/resources/logo app.png");
		if (url1 != null) {
			Image fotoImage1 = null;
			try {
				fotoImage1 = ImageIO.read(url1).getScaledInstance(180, 180, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			titulo.setIcon(new ImageIcon(fotoImage1));
		}
		titulo.setForeground(new Color(255, 20, 147));
		titulo.setFont(new Font("Tahoma", Font.BOLD, 42));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(titulo, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		frame.getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		JButton botonLogin = new JButton("Iniciar Sesion");
		botonLogin.setBackground(new Color(255, 20, 147));
		URL url = getClass().getResource("/umu/tds/apps/resources/flecha login.png");
		if (url != null) {
			Image fotoImage = null;
			try {
				fotoImage = ImageIO.read(url).getScaledInstance(18, 18, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			botonLogin.setIcon(new ImageIcon(fotoImage));
		}
		botonLogin.setForeground(new Color(255, 255, 255));
		botonLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonLogin.addActionListener(e -> {
			// implementar la accion de login
			//leer datos de la pantalla
			String telefono = textTelefono.getText();
			String contraseña = new String(textContraseña.getPassword());
			
			//comprobar si el usuario y la contraseña son correctos
			boolean logueado = Controlador.INSTANCE.login(telefono, contraseña);
			if(logueado) {
				 //abrir la ventana principal
				 //VentanaMain ventanaMain = new VentanaMain();
				 //ventanaMain.setVisible(true);
				Main_Prueba ventanaMain = new Main_Prueba();
				ventanaMain.setVisible(true);
				// ocultar la ventana de login
				frame.setVisible(false);
				
			} else {
				// mostrar mensaje de error
				JOptionPane.showMessageDialog(botonLogin, "Telefono" + telefono + " o contraseña incorrectos","Error Login", JOptionPane.ERROR_MESSAGE, null);
			
			}
		});
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelSur.add(botonLogin);
		
		JLabel lblNewLabel_1 = new JLabel("       ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSur.add(lblNewLabel_1);
		
		JLabel pregunta = new JLabel("¿No tienes cuenta?");
		pregunta.setForeground(new Color(255, 255, 255));
		panelSur.add(pregunta);
		
		
		JButton botonRegistro = new JButton("Registrarse");
		botonRegistro.setIcon(new ImageIcon(Login.class.getResource("/umu/tds/apps/resources/usuario.png")));
		botonRegistro.setBackground(new Color(255, 20, 147));
		botonRegistro.setForeground(new Color(255, 255, 255));
		botonRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSur.add(botonRegistro);
		botonRegistro.addActionListener(e -> {
			Registro registro = new Registro();
			registro.setVisible(true);
			frame.setVisible(false);
		});
		
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Login", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		frame.getContentPane().add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{10, 0, 0, 0, 0, 0};
		gbl_panelCentro.rowHeights = new int[]{20, 0, 32, 20, 0, 0, 0, 0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);
		
		
		telefono = new JLabel("Telefono");
		telefono.setForeground(new Color(255, 20, 147));
		telefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.anchor = GridBagConstraints.EAST;
		gbc_telefono.fill = GridBagConstraints.VERTICAL;
		gbc_telefono.insets = new Insets(0, 0, 5, 5);
		gbc_telefono.gridx = 3;
		gbc_telefono.gridy = 5;
		panelCentro.add(telefono, gbc_telefono);
		
		textTelefono = new JTextField();
		GridBagConstraints gbc_textTelefono = new GridBagConstraints();
		gbc_textTelefono.anchor = GridBagConstraints.WEST;
		gbc_textTelefono.insets = new Insets(0, 0, 5, 0);
		gbc_textTelefono.gridx = 4;
		gbc_textTelefono.gridy = 5;
		panelCentro.add(textTelefono, gbc_textTelefono);
		textTelefono.setColumns(15);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 6;
		panelCentro.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel contraseña = new JLabel("Contraseña");
		contraseña.setForeground(new Color(255, 20, 147));
		contraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_contraseña = new GridBagConstraints();
		gbc_contraseña.anchor = GridBagConstraints.EAST;
		gbc_contraseña.fill = GridBagConstraints.VERTICAL;
		gbc_contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_contraseña.gridx = 3;
		gbc_contraseña.gridy = 7;
		panelCentro.add(contraseña, gbc_contraseña);
		
		textContraseña = new JPasswordField();
		textContraseña.setColumns(20);
		GridBagConstraints gbc_textContraseña = new GridBagConstraints();
		gbc_textContraseña.anchor = GridBagConstraints.WEST;
		gbc_textContraseña.insets = new Insets(0, 0, 5, 0);
		gbc_textContraseña.gridx = 4;
		gbc_textContraseña.gridy = 7;
		panelCentro.add(textContraseña, gbc_textContraseña);
	}

}
