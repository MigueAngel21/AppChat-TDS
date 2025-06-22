package umu.tds.apps.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.Controlador.Controlador;

public class VentanaBuscar2 extends JFrame {

	private static final long serialVersionUID = 1L;
	/** Panel principal de contenido */
	private JPanel contentPane;
	/** Campo de texto para el número de teléfono del remitente */
	private JTextField telefono;
	/** Campo de texto para el nombre del contacto */
	private JTextField contacto;
	/** Campo de texto para el texto del mensaje */
	private JTextField texto;

	//Crea una nueva ventana de búsqueda de mensajes.
	public VentanaBuscar2() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaBuscar2.class.getResource("/umu/tds/apps/resources/icono app.png")));
		setBounds(400, 80, 708, 616);
		setTitle("UNICORNCHAT");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Panel superior con icono
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);

		JLabel imagenLupa = new JLabel("");
		imagenLupa.setIcon(
				new ImageIcon(VentanaBuscar2.class.getResource("/umu/tds/apps/resources/search-engine.png")));
		panelNorte.add(imagenLupa);

		// Panel central donde se encuentra el buscador y la lista
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));

		// Subpanel con campos de búsqueda
		JPanel panel_Norte = new JPanel();
		panel_Norte.setBorder(new TitledBorder(null, "buscar", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 255, 255)));
		panelCentro.add(panel_Norte, BorderLayout.NORTH);
		GridBagLayout gbl_panel_Norte = new GridBagLayout();
		gbl_panel_Norte.columnWidths = new int[] { 0, 0, 40, 96, 63, 0 };
		gbl_panel_Norte.rowHeights = new int[] { 21, 0, 0 };
		gbl_panel_Norte.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Norte.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_Norte.setLayout(gbl_panel_Norte);

		// Campo texto
		texto = new JTextField("texto");
		texto.setForeground(Color.GRAY);
		texto.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (texto.getText().equals("texto")) {
					texto.setText("");
					texto.setForeground(Color.WHITE);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (texto.getText().isEmpty()) {
					texto.setText("texto");
					texto.setForeground(Color.GRAY);
				}
			}
		});
		GridBagConstraints gbc_texto = new GridBagConstraints();
		gbc_texto.gridwidth = 5;
		gbc_texto.insets = new Insets(0, 0, 5, 5);
		gbc_texto.fill = GridBagConstraints.HORIZONTAL;
		gbc_texto.gridx = 0;
		gbc_texto.gridy = 0;
		panel_Norte.add(texto, gbc_texto);

		// Campo teléfono
		telefono = new JTextField("telefono");
		telefono.setForeground(Color.GRAY);
		telefono.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (telefono.getText().equals("telefono")) {
					telefono.setText("");
					telefono.setForeground(Color.WHITE);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (telefono.getText().isEmpty()) {
					telefono.setText("telefono");
					telefono.setForeground(Color.GRAY);
				}
			}
		});
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefono.insets = new Insets(0, 0, 0, 5);
		gbc_telefono.gridx = 0;
		gbc_telefono.gridy = 1;
		panel_Norte.add(telefono, gbc_telefono);

		// Campo contacto
		contacto = new JTextField("contacto");
		contacto.setForeground(Color.GRAY);
		contacto.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (contacto.getText().equals("contacto")) {
					contacto.setText("");
					contacto.setForeground(Color.WHITE);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (contacto.getText().isEmpty()) {
					contacto.setText("contacto");
					contacto.setForeground(Color.GRAY);
				}
			}
		});
		GridBagConstraints gbc_contacto = new GridBagConstraints();
		gbc_contacto.insets = new Insets(0, 0, 0, 5);
		gbc_contacto.gridx = 1;
		gbc_contacto.gridy = 1;
		panel_Norte.add(contacto, gbc_contacto);

		
		// Modelo y lista de mensajes
		DefaultListModel<Mensaje> modelo = new DefaultListModel<>();
		List<Mensaje> listaMensajes = Controlador.INSTANCE.obtenerTodosMensajes();
		listaMensajes.forEach(modelo::addElement);
		
		JList<Mensaje> lista = new JList<>(modelo);
		lista.setFocusable(false);
		lista.setCellRenderer(new BuscarCellRenderer());
		
		// Botón buscar 
		JButton botonbuscar = new JButton("Buscar");
		botonbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<Mensaje> mensajesFiltrados = Controlador.INSTANCE.obtenerMensajesFiltrados(
						telefono.getText().equals("telefono") ? "" : telefono.getText(),
						contacto.getText().equals("contacto") ? "" : contacto.getText(),
						texto.getText().equals("texto") ? "" : texto.getText());
				
				
				modelo.clear();
				mensajesFiltrados.forEach(modelo::addElement);
			}
		});
		
		GridBagConstraints gbc_botonbuscar = new GridBagConstraints();
		gbc_botonbuscar.insets = new Insets(0, 0, 0, 5);
		gbc_botonbuscar.gridx = 2;
		gbc_botonbuscar.gridy = 1;
		panel_Norte.add(botonbuscar, gbc_botonbuscar);

		
		// Agregar la lista en el panel central
		panelCentro.add(new JScrollPane(lista), BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new VentanaBuscar2();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}