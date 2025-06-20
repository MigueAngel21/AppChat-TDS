package umu.tds.apps.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

public class VentanaBuscar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField telefono;
	private JTextField contacto;
	private JTextField texto;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBuscar frame = new VentanaBuscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * Create the frame.
	 */
	public VentanaBuscar() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//cambiar icono de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEjemplo.class.getResource("/umu/tds/apps/resources/icono app.png")));
		setBounds(400, 80, 708, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel imagenLupa = new JLabel("");
		imagenLupa.setIcon(new ImageIcon(VentanaBuscar.class.getResource("/umu/tds/apps/resources/search-engine.png")));
		panelNorte.add(imagenLupa);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Norte = new JPanel();
		panel_Norte.setBorder(new TitledBorder(null, "buscar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelCentro.add(panel_Norte, BorderLayout.NORTH);
		GridBagLayout gbl_panel_Norte = new GridBagLayout();
		gbl_panel_Norte.columnWidths = new int[]{0, 0, 40, 96, 63, 0};
		gbl_panel_Norte.rowHeights = new int[]{21, 0, 0};
		gbl_panel_Norte.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Norte.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_Norte.setLayout(gbl_panel_Norte);
		
		texto = new JTextField();
		GridBagConstraints gbc_texto = new GridBagConstraints();
		gbc_texto.gridwidth = 5;
		gbc_texto.insets = new Insets(0, 0, 5, 5);
		gbc_texto.fill = GridBagConstraints.HORIZONTAL;
		gbc_texto.gridx = 0;
		gbc_texto.gridy = 0;
		// dento del textfield texto aparezca un texto de fondo que diga "texto", pero que se puede ascribir encima de el
		texto.setText("texto");
		texto.setForeground(Color.GRAY);
		texto.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				texto.setText("");
				texto.setForeground(Color.WHITE);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (texto.getText().equals("")) {
					texto.setText("texto");
					texto.setForeground(Color.GRAY);
				}
			}
		});
		panel_Norte.add(texto, gbc_texto);
		texto.setColumns(10);
		
		telefono = new JTextField();
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefono.insets = new Insets(0, 0, 0, 5);
		gbc_telefono.gridx = 0;
		gbc_telefono.gridy = 1;
		// dento del textfield telefono aparezca un texto de fondo que diga "telefono", pero que se puede ascribir encima de el
		telefono.setText("telefono");
		telefono.setForeground(Color.GRAY);
		telefono.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				telefono.setText("");
				telefono.setForeground(Color.WHITE);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (telefono.getText().equals("")) {
					telefono.setText("telefono");
					telefono.setForeground(Color.GRAY);
				}
			}
		});
		
		panel_Norte.add(telefono, gbc_telefono);
		telefono.setColumns(10);
		
		contacto = new JTextField();
		GridBagConstraints gbc_contacto = new GridBagConstraints();
		gbc_contacto.anchor = GridBagConstraints.WEST;
		gbc_contacto.insets = new Insets(0, 0, 0, 5);
		gbc_contacto.gridx = 1;
		gbc_contacto.gridy = 1;
		//dento del textfield contacto aparezca un texto de fondo que diga "contacto", pero que se puede ascribir encima de el
		contacto.setText("contacto");
		contacto.setForeground(Color.GRAY);
		contacto.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				contacto.setText("");
				contacto.setForeground(Color.WHITE);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (contacto.getText().equals("")) {
					contacto.setText("contacto");
					contacto.setForeground(Color.GRAY);
				}
			}
		});
		panel_Norte.add(contacto, gbc_contacto);
		contacto.setColumns(10);
		
		JButton botonbuscar = new JButton("Buscar");
		GridBagConstraints gbc_botonbuscar = new GridBagConstraints();
		gbc_botonbuscar.insets = new Insets(0, 0, 0, 5);
		gbc_botonbuscar.gridx = 2;
		gbc_botonbuscar.gridy = 1;
		panel_Norte.add(botonbuscar, gbc_botonbuscar);
		
		
		JPanel panel_centro = new JPanel();
	
		DefaultListModel<String> modelo = new DefaultListModel<>();
		modelo.addElement("Mensaje 1 de prueba");
		modelo.addElement("Mensaje 2 de prueba");
		modelo.addElement("Mensaje 3 de prueba");
		
		JList<String> lista = new JList<>(modelo);
		lista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lista.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		/*
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"jfhsiodjfs+ç", "sodfhsdfsç", "sdfsdfsdf", "sdfsdfsdf", "sdfsdfsdfsçdfsd", "fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs",
					"fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs",
					"fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs",
					"fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs",
					"fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs",
					"fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","fsfdsdfsdfs","dfsfsdf"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		*/
		
		panel_centro.add(new JScrollPane(lista), BorderLayout.CENTER);
		
		
		
		this.setTitle("UNICORNCHAT");
		this.setVisible(true);
	}

}
