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
	private JTextField textUsuario;
	private JPasswordField textContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
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
			String usuario = textUsuario.getText();
			String contraseña = new String(textContraseña.getPassword());
			
			//comprobar si el usuario y la contraseña son correctos
			boolean logueado = Controlador.getInstancia().login(usuario, contraseña);
			
			if(!logueado) {
				// mostrar mensaje de error
				JOptionPane.showMessageDialog(botonLogin, "Usuario" + usuario + " o contraseña incorrectos","Error Login", JOptionPane.ERROR_MESSAGE, null);
			} else {
				// abrir la ventana principal
				 VentanaMain ventanaMain = new VentanaMain();
				 ventanaMain.setVisible(true);
				// ocultar la ventana de login
				frame.setVisible(false);
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(botonLogin);
		
		JLabel lblNewLabel_1 = new JLabel("       ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);
		
		JLabel pregunta = new JLabel("¿No tienes cuenta?");
		pregunta.setForeground(new Color(255, 255, 255));
		panel.add(pregunta);
		
		
		JButton botonRegistro = new JButton("Registrarse");
		botonRegistro.setIcon(new ImageIcon(Login.class.getResource("/umu/tds/apps/resources/usuario.png")));
		botonRegistro.setBackground(new Color(255, 20, 147));
		botonRegistro.setForeground(new Color(255, 255, 255));
		botonRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(botonRegistro);
		//creame un evento para el boton de registro, que cuando lo pulses se abra la ventana de registro haciendose visible y se oculte la de login implementadolo con una expresion lambda
		botonRegistro.addActionListener(e -> {
			Registro registro = new Registro();
			registro.setVisible(true);
			frame.setVisible(false);
		});
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Login", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{20, 0, 32, 20, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel contraseña = new JLabel("Contraseña");
		contraseña.setForeground(new Color(255, 20, 147));
		contraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_contraseña = new GridBagConstraints();
		gbc_contraseña.anchor = GridBagConstraints.EAST;
		gbc_contraseña.fill = GridBagConstraints.VERTICAL;
		gbc_contraseña.insets = new Insets(0, 0, 5, 5);
		gbc_contraseña.gridx = 3;
		gbc_contraseña.gridy = 5;
		panel_1.add(contraseña, gbc_contraseña);
		
		textContraseña = new JPasswordField();
		textContraseña.setColumns(20);
		GridBagConstraints gbc_textContraseña = new GridBagConstraints();
		gbc_textContraseña.anchor = GridBagConstraints.WEST;
		gbc_textContraseña.insets = new Insets(0, 0, 5, 0);
		gbc_textContraseña.gridx = 4;
		gbc_textContraseña.gridy = 5;
		panel_1.add(textContraseña, gbc_textContraseña);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 6;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel telefono = new JLabel("Telefono");
		telefono.setForeground(new Color(255, 20, 147));
		telefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.anchor = GridBagConstraints.EAST;
		gbc_telefono.fill = GridBagConstraints.VERTICAL;
		gbc_telefono.insets = new Insets(0, 0, 5, 5);
		gbc_telefono.gridx = 3;
		gbc_telefono.gridy = 7;
		panel_1.add(telefono, gbc_telefono);
		
		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.anchor = GridBagConstraints.SOUTHWEST;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_textUsuario.gridx = 4;
		gbc_textUsuario.gridy = 7;
		panel_1.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(15);
	}

}
