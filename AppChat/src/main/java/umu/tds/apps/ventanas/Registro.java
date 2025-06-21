package umu.tds.apps.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import umu.tds.apps.Controlador.Controlador;


public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JDateChooser dateChooser;
	private String rutaImagen;
	private JLabel imagenSeleccionada;
	private JPanel panel_1;
	
	
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
		
		
		panel_1 = new JPanel();
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
		
	    dateChooser = new JDateChooser();
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
		
		JButton btnañadirImagen = new JButton("Añadir Imagen");
		btnañadirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 11;
		panel_1.add(btnañadirImagen, gbc_btnNewButton);
		btnañadirImagen.addActionListener(e -> seleccionarImagen(rutaImagen)); //llama al metodo seleccionarImagen para seleccionar la imagen del usuario
		
		
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
		
		
		botonAceptar.addActionListener(e -> {
			//crear aqui el metodo para registrar al usuario completo
			this.setVisible(false);
			//hacer visible de nuevo la ventana de login
			Login window = new Login();
			window.frame.setVisible(true);
		});
		
		botonAceptar.addActionListener(e -> {
			if (validarCampos()) { //funcion para comprobar que todos los campos son correctos
				//Aqui es donde se llama al metodo del controlador para registrar a un usuarios con todos sus campos
				 Controlador.INSTANCE.registrarUsuario(textNombre.getText() + " " + textApellido.getText(),
							new String(passwordField.getPassword()), textTelefono.getText().trim(),
							dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
							textPaneSaludo.getText(), rutaImagen);
				dispose();
			}
		});
		
		this.setTitle("UNICORNCHAT");
		this.setVisible(true);
		
	}
	
	
	private void seleccionarImagen(String rutaImagen) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Seleccionar imagen");
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes (.jpg, .png, .jpeg)", "jpg", "jpeg", "png"));

	    int resultado = fileChooser.showOpenDialog(this);

	    if (resultado == JFileChooser.APPROVE_OPTION) {
	        File archivoImagen = fileChooser.getSelectedFile();

	        try {
	            // Guardar la ruta para la imagen
	        	rutaImagen = archivoImagen.getAbsolutePath();
	           // Usuario usuario = Controlador.INSTANCE.getUsuarioActual();
	           // usuario.setRutaAvatar(archivoImagen.getAbsolutePath());

	            // Mostrar la imagen en la interfaz
	            ImageIcon imagenIcon = new ImageIcon(archivoImagen.getAbsolutePath());
	            Image imagenEscalada = imagenIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	            imagenIcon = new ImageIcon(imagenEscalada);

	            if (imagenSeleccionada == null) {
		        	imagenSeleccionada = new JLabel();
		        	GridBagConstraints gbc_imagenSeleccionada = new GridBagConstraints();
		    		gbc_imagenSeleccionada.insets = new Insets(0, 0, 5, 0);
		    		gbc_imagenSeleccionada.gridx = 6;
		    		gbc_imagenSeleccionada.gridy = 12;
		    		panel_1.add(imagenSeleccionada, gbc_imagenSeleccionada);
		        }
	            imagenSeleccionada.setIcon(imagenIcon);

	            JOptionPane.showMessageDialog(this,
	                "Avatar actualizado correctamente.",
	                "Éxito",
	                JOptionPane.INFORMATION_MESSAGE);

	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this,
	                "Error al procesar la imagen: " + e.getMessage(),
	                "Error",
	                JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	
	private boolean validarCampos() {
	    boolean salida = true;
	    ocultarErrores();
	
	    if (textNombre.getText().trim().isEmpty()) {
	        textNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
	        salida = false;
	    }
	
	    if (textApellido.getText().trim().isEmpty()) {
	        textApellido.setBorder(BorderFactory.createLineBorder(Color.RED));
	        salida = false;
	    }
	
	    String pass1 = new String(passwordField.getPassword());
	    String pass2 = new String(passwordField_1.getPassword());
	
	    if (pass1.isEmpty()) {
	        passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
	        salida = false;
	    }
	
	    if (pass2.isEmpty()) {
	        passwordField_1.setBorder(BorderFactory.createLineBorder(Color.RED));
	        salida = false;
	    }
	
	    if (!pass1.equals(pass2)) {
	        passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
	        passwordField_1.setBorder(BorderFactory.createLineBorder(Color.RED));
	        salida = false;
	    }
	
	    if (textTelefono.getText().trim().isEmpty()) {
	        textTelefono.setBorder(BorderFactory.createLineBorder(Color.RED));
	        salida = false;
	        
	    } else if (Controlador.INSTANCE.esUsuarioRegistrado(textTelefono.getText().trim())) {
	        textTelefono.setBorder(BorderFactory.createLineBorder(Color.RED));
	        JOptionPane.showMessageDialog(this,
	                "El teléfono introducido ya está registrado.",
	                "Error",
	                JOptionPane.ERROR_MESSAGE);
	        salida = false;
	    }
	
	    if (dateChooser.getDate() == null) {
	        dateChooser.setBorder(BorderFactory.createLineBorder(Color.RED));
	        salida = false;
	    }
	
	    return salida;
	}
	
	private void ocultarErrores() {
	    Border defaultBorder = new JTextField().getBorder();
	
	    textNombre.setBorder(defaultBorder);
	    textApellido.setBorder(defaultBorder);
	    textTelefono.setBorder(defaultBorder);
	    passwordField.setBorder(defaultBorder);
	    passwordField_1.setBorder(defaultBorder);
	    dateChooser.setBorder(null);
	}

	


	
}
