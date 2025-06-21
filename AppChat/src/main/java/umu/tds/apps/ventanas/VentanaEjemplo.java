package umu.tds.apps.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.*;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class VentanaEjemplo {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public VentanaEjemplo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//aqui hay que poner el lokk and field que le queremos meter a las ventanas		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 849, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("UNICORNCHAT");
		//cambiar icono de la ventana
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEjemplo.class.getResource("/umu/tds/apps/resources/icono app.png")));
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setPopupSize(new Dimension(14, 14));
		addPopup(frame.getContentPane(), popupMenu);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_5 = new JLabel("imagen principal");
		lblNewLabel_5.setForeground(new Color(255, 20, 147));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridwidth = 3;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 0;
		frame.getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setForeground(new Color(255, 20, 147));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(20);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos");
		lblNewLabel_1.setForeground(new Color(255, 20, 147));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.gridwidth = 4;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 3;
		frame.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(20);
		
		JLabel lblNewLabel_2 = new JLabel();//meter dentro usuario
		lblNewLabel_2.setForeground(new Color(255, 20, 147));
		String path2 =  "https://cdn-icons-png.flaticon.com/512/6681/6681204.png"; 
		URL url2 = null;
		try {
			url2 = new URL(path2);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		BufferedImage image2= null;
		try {
			image2 = ImageIO.read(url2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image scaledImage2 = image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		lblNewLabel_2.setIcon(new ImageIcon(scaledImage2));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.anchor = GridBagConstraints.WEST;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 4;
		frame.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(15);
		
		JLabel lblNewLabel_3 = new JLabel("Clave");
		lblNewLabel_3.setForeground(new Color(255, 20, 147));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 4;
		frame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 5;
		gbc_passwordField.gridy = 4;
		frame.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("Descripcion");
		lblNewLabel_4.setForeground(new Color(255, 20, 147));
		
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		frame.getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha");
		lblNewLabel_6.setForeground(new Color(255, 20, 147));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 4;
		gbc_lblNewLabel_6.gridy = 5;
		frame.getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MMM-yy");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.anchor = GridBagConstraints.WEST;
		gbc_dateChooser.fill = GridBagConstraints.VERTICAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.gridx = 5;
		gbc_dateChooser.gridy = 5;
		frame.getContentPane().add(dateChooser, gbc_dateChooser);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 20, 147));
		//PONER UNA IMAGEN DESDE UNA CARPETA LOCAL
		URL url4 = getClass().getResource("/umu/tds/apps/resources/flecha login.png");
		if (url4 != null) {
			Image fotoImage = null;
			try {
				fotoImage = ImageIO.read(url4).getScaledInstance(20, 20, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			btnNewButton.setIcon(new ImageIcon(fotoImage));
		}
		
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		
		// CODIGO PARA VER COMO METER UNA IMAGEN DE INTERNET Y COMO REDIMENSIONARLA AL TAMAÑO QUE QUIERAS
		//para el icono del usuario, hay que meter para que lea por comandos la URL que le meta el usuario (cin)
		String path =  "https://static.wikia.nocookie.net/envyrp/images/6/65/VU_LOGO_OFFICIAL.png"; 
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		BufferedImage image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Image scaledImage = image.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
		
		JLabel lblNewLabel_7 = new JLabel("POPUP  DE EJEMPLO");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 8;
		frame.getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		
	
		JLabel label = new JLabel(new ImageIcon(scaledImage));	
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.SOUTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 8;
		frame.getContentPane().add(label, gbc_label);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 10;
		frame.getContentPane().add(panel, gbc_panel);
		
		JButton btnNewButton_1 = new JButton("OPCIONES");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("AISHD");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New ");
		panel.add(btnNewButton_3);
		
		JSeparator separator = new JSeparator();
		separator.setSize(new Dimension(9, 8));
		separator.setPreferredSize(new Dimension(10, 20));
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator);
		
		JButton btnNewButton_4 = new JButton(" button");
		panel.add(btnNewButton_4);
		
		JSlider slider = new JSlider();
		slider.setBackground(new Color(255, 255, 255));
		slider.setForeground(new Color(255, 0, 0));
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 4;
		gbc_slider.gridy = 11;
		frame.getContentPane().add(slider, gbc_slider);
		
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

