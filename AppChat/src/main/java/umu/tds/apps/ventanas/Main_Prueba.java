package umu.tds.apps.ventanas;


import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tds.BubbleText;
import umu.tds.apps.AppChat.*;
import umu.tds.apps.Controlador.Controlador;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

//Clase principal de la interfaz de usuario que representa la ventana del chat
public class Main_Prueba extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JTextField textMensaje;
	private boolean menuEmojiAbierto = false;// Controla si el menú de emojis está visible
	private Usuario usuarioActual = Controlador.INSTANCE.getUsuarioActual();// Usuario que ha iniciado sesión
	private String receptor = null; // Receptor actual del mensaj
	private JComboBox<String> comboBox = new JComboBox<String>(); // Combo box para seleccionar contacto o grupo

	 // Método que actualiza el combo box con los contactos del usuario
	private void actualizarComboBox() {
		List<String> array = new LinkedList<String>();
		List<Contacto> listaContactos = usuarioActual.getContactos();
		listaContactos.stream().map(c -> c.getNombre()).forEach(n -> array.add(n));
		array.add("Nuevo teléfono");
		comboBox.setModel(new DefaultComboBoxModel<String>(array.toArray(new String[0])));
	}

	// Método que carga los mensajes de un chat al panel visual
	protected void actualizarPanelChat(JScrollPane scroll, JPanel panelChatActual, String usuarioSeleccionado) {
		List<Mensaje> listaMensajes = Controlador.INSTANCE.obtenerChat(usuarioSeleccionado);
		panelChatActual.removeAll();
		 // Añadir mensajes al panel
		for (Mensaje mensaje : listaMensajes) {
			if (mensaje.getEmoticono() != -1) {// Si es un emoji
				if (mensaje.getEmisor().equals(usuarioActual))
					panelChatActual.add(new BubbleText(panelChatActual, mensaje.getEmoticono(), Color.GREEN,
							usuarioActual.getUsuario(), BubbleText.SENT, 12));
				else
					panelChatActual.add(new BubbleText(panelChatActual, mensaje.getEmoticono(), Color.GRAY, receptor,
							BubbleText.RECEIVED, 12));
			}

			else {// Si es texto
				if (mensaje.getEmisor().equals(usuarioActual))
					panelChatActual.add(new BubbleText(panelChatActual, mensaje.getTexto(), Color.GREEN,
							usuarioActual.getUsuario(), BubbleText.SENT));
				else
					panelChatActual.add(new BubbleText(panelChatActual, mensaje.getTexto(), Color.GRAY, receptor,
							BubbleText.RECEIVED));
			}
		}

        // Refrescar panel
		panelChatActual.revalidate();
		panelChatActual.repaint();
		SwingUtilities.invokeLater(() -> {
			JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
			verticalScrollBar.setValue(verticalScrollBar.getMaximum());
		});
	};

	 // Actualiza la lista de chats recientes en el panel izquierdo
	protected void actualizarChatRecientes(DefaultListModel<Usuario> modelo, JPanel panelChatRecientes,
			JList<Usuario> list) {
		List<Mensaje> mensajes = usuarioActual.obtenerTodosUltimosMensajes();
		modelo.clear();
		// Añade usuarios involucrados en los últimos mensajes
		for (Mensaje m : mensajes) {
			Usuario emisor = m.getEmisor();
			Usuario receptor = m.getReceptor();
			if (emisor.equals(usuarioActual))
				modelo.addElement(receptor);
			else
				modelo.addElement(emisor);
		}
		
		// Refrescar lista
		list.setModel(modelo);
		panelChatRecientes.revalidate();
		panelChatRecientes.repaint();
	}


    // Constructor principal de la ventana
	public Main_Prueba() {
		try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		
		// Configuración inicial de ventana
		setTitle("UNICORNCHAT");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Main_Prueba.class.getResource("/umu/tds/apps/resources/icono app.png")));
		setBounds(420, 160, 716, 553);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		
		 // Panel superior (barra de botones y combo box)
		JPanel botonera = new JPanel();
		getContentPane().add(botonera, BorderLayout.NORTH);

		botonera.setLayout(new BoxLayout(botonera, BoxLayout.X_AXIS));

		// Paneles principales
		JPanel panelChatRecientes = new JPanel();
		JPanel panelChatActual = new JPanel();
		JPanel panelMensajes = new JPanel();
		JPanel panelTxtMensaje = new JPanel();
		JPanel panelMensajesInterno = new JPanel();
		JScrollPane scroll = new JScrollPane(panelMensajesInterno);
		getContentPane().add(panelChatRecientes, BorderLayout.WEST);
		panelChatRecientes.setLayout(new BorderLayout(0, 0)); 
		
		 // Actualizar combo box con contactos
		actualizarComboBox();
		
		// Combo box de destinatarios
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				receptor = (String) comboBox.getSelectedItem();
			}
		});
		comboBox.setEditable(false);
		botonera.add(comboBox);

		  // Lista de chats recientes
		DefaultListModel<Usuario> modelo = new DefaultListModel<>();
		JList<Usuario> list = new JList<Usuario>();
		list.setCellRenderer(new RecientesCellRenderer());
		
		 // Botón enviar (arriba)
		JButton btnEnviarArriba = new JButton();
		btnEnviarArriba.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/enviar-mensaje-avionPapel.png")));
		Main_Prueba aux = this;
		btnEnviarArriba.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (receptor == "Nuevo teléfono" || usuarioActual.existeGrupo(receptor)) {
					VentanaMensaje ventana = new VentanaMensaje(receptor, aux, panelMensajesInterno, scroll, modelo,
							panelChatRecientes, list);
					ventana.setVisible(true);
				} else {
					actualizarPanelChat(scroll, panelMensajesInterno, receptor);
				}
			}
		});

		Component horizontalGlue_3_1 = Box.createHorizontalGlue();
		botonera.add(horizontalGlue_3_1);
		botonera.add(btnEnviarArriba);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		botonera.add(horizontalGlue_3);
		
		 // Botón buscar mensaje (lupa)
		JButton btnBuscarMensaje = new JButton("");
		btnBuscarMensaje.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/lupa-buscar.png")));
		btnBuscarMensaje.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaBuscar2 ventana2 = new VentanaBuscar2();
				ventana2.setLocationRelativeTo(null);
				ventana2.setVisible(true);
			}
		});
		
		// Botón para agregar nuevo teléfono a contactos
		JButton btnAddTelefono = new JButton("");
		btnAddTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddTelefono.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/mas.png")));
		btnAddTelefono.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() != null) {
					// Si hay un contacto seleccionado, se pide el nombre para agregarlo
					if (comprobarExisteContacto(list)) {
						String telefono = list.getSelectedValue().getTelefono();
						String nombre = JOptionPane.showInputDialog(aux, "Introduce el nombre de tu nuevo contacto " + telefono, null, JOptionPane.WARNING_MESSAGE);
						if (nombre != null) {
							int añadido = Controlador.INSTANCE.addContactoIndividual(nombre, telefono);
							if (añadido == 1) {
								JOptionPane.showMessageDialog(Main_Prueba.this,
										"No existe un usuario asociado al teléfono introducido", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else if (añadido == 2) {
								JOptionPane.showMessageDialog(Main_Prueba.this,
										"El teléfono introducido ya lo tienes agregado como contacto", "Error",
										JOptionPane.ERROR_MESSAGE);
	
							} else if (añadido == 3) {
								JOptionPane.showMessageDialog(Main_Prueba.this, "El teléfono introducido es tu teléfono",
										"Error", JOptionPane.ERROR_MESSAGE);
	
							} else if (añadido == 4) {
								JOptionPane.showMessageDialog(Main_Prueba.this, "El nombre introducido ya existe en tu lista de contactos",
										"Error", JOptionPane.ERROR_MESSAGE);
							} else {
								actualizarChatRecientes(modelo, panelChatRecientes, list);
								actualizarComboBox();
								actualizarPanelChat(scroll, panelMensajesInterno, nombre);
							}
						}
					} else {
						JOptionPane.showMessageDialog(aux, "Selecciona un chat no agregado a Contactos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			// Comprueba si el contacto seleccionado ya existe en la agenda del usuario
			private boolean comprobarExisteContacto(JList<Usuario> list) {
				return Controlador.INSTANCE.existeContacto(list.getSelectedValue().getTelefono()).equals(list.getSelectedValue().getTelefono());
			}
		});
		botonera.add(btnAddTelefono);
		
		Component horizontalGlue_2_1 = Box.createHorizontalGlue();
		botonera.add(horizontalGlue_2_1);
		botonera.add(btnBuscarMensaje);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		botonera.add(horizontalGlue_2);

		// Botón de contactos (grupos)
		JButton btnContactos = new JButton("Contactos");
		btnContactos.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/imagen-contactos.png")));
		btnContactos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaGrupos ventanaGrupos = new VentanaGrupos();
				ventanaGrupos.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						actualizarComboBox();
					}
				});
				ventanaGrupos.setVisible(true);
			}
		});
		botonera.add(btnContactos);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		botonera.add(horizontalGlue_1);

		 // Botón premium
		JButton btnPremium = new JButton("Premium");
		btnPremium.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/icono-premium.png")));
		botonera.add(btnPremium);

		// GESTION VENTANA PREMIUM
		btnPremium.addActionListener(e -> {
			Premium ventanaPremium = new Premium(Main_Prueba.this,
					Controlador.INSTANCE.getUsuarioActual().isPremium(), receptor);
			ventanaPremium.setVisible(true);

		});

		Component horizontalGlue = Box.createHorizontalGlue();
		botonera.add(horizontalGlue);
		
		// Animar color si es premium
		JLabel lblNombreusuario = new JLabel(Controlador.INSTANCE.getUsuarioActual().getUsuario());
		Color miColor = new Color(238, 202, 36);

		botonera.add(lblNombreusuario);

		Timer timer = new Timer(1800, e -> {
			if (Controlador.INSTANCE.getUsuarioActual().isPremium())
				lblNombreusuario.setForeground(miColor);
			else
				lblNombreusuario.setForeground(Color.BLACK);

			lblNombreusuario.revalidate();
			lblNombreusuario.repaint();
		});
		timer.start();

		 // Imagen de usuario
		JLabel labelImagen = new JLabel("");
		Usuario usuarioActual = Controlador.INSTANCE.getUsuarioActual();
		labelImagen.setIcon(new ImageIcon(Main_Prueba.class.getResource(Usuario.IMG)));//Usuario.IMG
		botonera.add(labelImagen);
		labelImagen.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent e) {

				String nuevaURL = JOptionPane.showInputDialog(Main_Prueba.this, "Introduce la URL de la nueva imagen:",
						"Cambiar imagen de Usuario", JOptionPane.PLAIN_MESSAGE);

				if (nuevaURL == null)
					return;
				
				if (nuevaURL != null && nuevaURL.isEmpty()) {
					JOptionPane.showMessageDialog(Main_Prueba.this, "Introduce una URL no vacía.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				else {
					try {
						URL recurso = getClass().getResource(nuevaURL);
						if (recurso != null) {
							ImageIcon icono = new ImageIcon(recurso);
							if (icono.getIconWidth() > 0 && icono.getIconHeight() > 0
									&& Controlador.INSTANCE.cambiarImagenUsuario(nuevaURL)) {
								labelImagen.setIcon(icono);
							} else {
								JOptionPane.showMessageDialog(Main_Prueba.this,
										"Error al cargar la imagen. URL no válida.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(Main_Prueba.this,
									"El recurso no se encontró. Verifica que la ruta sea correcta.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(Main_Prueba.this, "Error al cargar la imagen. URL no válida.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// Cargar chats recientes
		actualizarChatRecientes(modelo, panelChatRecientes, list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {
					receptor = usuarioActual.existeContacto(list.getSelectedValue().getTelefono());
				}
				actualizarPanelChat(scroll, panelMensajesInterno, receptor);
			}
		});
		
		// Agrega lista al panel izquierdo
		panelChatRecientes.add(new JScrollPane(list));
		
		  // Configurar panel de mensajes
		panelTxtMensaje.setBackground(Color.GREEN);
		panelChatActual.setLayout(new BorderLayout(0, 0));
		panelChatActual.add(panelMensajes, BorderLayout.CENTER);
		panelChatActual.add(panelTxtMensaje, BorderLayout.SOUTH);
		getContentPane().add(panelChatActual, BorderLayout.CENTER);
		panelChatActual.setSize(400, 700);
		panelChatActual.setMinimumSize(new Dimension(400, 700));
		panelChatActual.setMaximumSize(new Dimension(400, 700));
		panelChatActual.setPreferredSize(new Dimension(400, 700));
		panelChatActual.setBackground(Color.WHITE);

		 // Contenedor de mensajes del chat
		panelMensajesInterno.setLayout(new BoxLayout(panelMensajesInterno, BoxLayout.Y_AXIS));

		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SwingUtilities.invokeLater(() -> {
			JScrollBar verticalScrollBar = scroll.getVerticalScrollBar();
			verticalScrollBar.setValue(verticalScrollBar.getMaximum());
		});
		panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.X_AXIS));
		panelMensajes.add(scroll);
		panelTxtMensaje.setLayout(new BoxLayout(panelTxtMensaje, BoxLayout.X_AXIS));

		// EMOJIS
		JButton btnEmojis = new JButton("");
		btnEmojis.setIcon(BubbleText.getEmoji(6));
		btnEmojis.setPreferredSize(new Dimension(40, 21));
		btnEmojis.setMinimumSize(new Dimension(40, 21));
		btnEmojis.setMaximumSize(new Dimension(40, 21));
		panelTxtMensaje.add(btnEmojis);

		  // Menú de emojis emergente
		JPopupMenu menuEmojis = new JPopupMenu();
		JPanel panelEmojis = new JPanel(new GridLayout(2, 4, 3, 3)); // 2 filas, 4 columnas, gap de 2px
		panelEmojis.setBackground(Color.WHITE);

		menuEmojis.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panelEmojis.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Creamos los 8 botones de emojis
		for (int i = 0; i < 8; i++) {
			JButton emojiButton = new JButton(BubbleText.getEmoji(i));
			emojiButton.setBackground(Color.WHITE);
			emojiButton.setBorderPainted(false);
			emojiButton.setFocusPainted(false);

			final int emojiIndex = i;
			emojiButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					Controlador.INSTANCE.enviarMensaje(receptor, emojiIndex);
					actualizarPanelChat(scroll, panelMensajesInterno, receptor);
					actualizarChatRecientes(modelo, panelChatRecientes, list);
					menuEmojis.setVisible(false);
					menuEmojiAbierto = false;
				}
			});

			panelEmojis.add(emojiButton);
		}

		menuEmojis.add(panelEmojis);

		// Manejo del Menu
		btnEmojis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (menuEmojiAbierto) {
					menuEmojis.setVisible(false);
					menuEmojiAbierto = false;
				} else {
					// Posicionamos el popup justo encima del botón de emojis
					menuEmojis.show(btnEmojis, 0, -menuEmojis.getPreferredSize().height);
					menuEmojiAbierto = true;
				}
			}
		});

		// Ajustar el tamaño de los botones de emojis
		Dimension emojiButtonSize = new Dimension(40, 40);
		for (Component c : panelEmojis.getComponents()) {
			if (c instanceof JButton) {
				JButton btn = (JButton) c;
				btn.setPreferredSize(emojiButtonSize);
				btn.setMinimumSize(emojiButtonSize);
				btn.setMaximumSize(emojiButtonSize);
			}
		}

		
		textMensaje = new JTextField();
		textMensaje.setToolTipText("Escribe un mensaje...");
		panelTxtMensaje.add(textMensaje);
		textMensaje.setColumns(10);

		// Botón enviar mensaje (abajo)
		JButton btnEnviarMensaje = new JButton();
		btnEnviarMensaje.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/avion-enviar-whatsapp.png")));
		panelTxtMensaje.add(btnEnviarMensaje);
		btnEnviarMensaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (receptor != null) {
					if (hayTexto()) {
						String msg = textMensaje.getText();
						Controlador.INSTANCE.enviarMensaje(receptor, msg);
						actualizarChatRecientes(modelo, panelChatRecientes, list);
						actualizarPanelChat(scroll, panelMensajesInterno, receptor);
						textMensaje.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(btnEnviarMensaje.getParent().getParent(), "Selecciona un contacto o teléfono para enviar el mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			private boolean hayTexto() {
				return textMensaje.getText().length() > 0;
			}
		});
	}
}
