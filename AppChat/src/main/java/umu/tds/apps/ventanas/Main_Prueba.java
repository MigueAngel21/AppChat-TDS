package umu.tds.apps.ventanas;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;

public class Main_Prueba extends JFrame {


	private static final long serialVersionUID = 1L;

	private JTextField textMensaje;
	private boolean menuEmojiAbierto = false;
	// private Usuario usuarioActual = Controlador.INSTANCE.getUsuarioActual();
	private String receptor = null;
	private JComboBox<String> comboBox;

	private JPanel panelChatRecientes;
	private JPanel panelChatActual;
	private JPanel panelMensajes;
	private JPanel panelTxtMensaje;
	private JPanel panelMensajesInterno;
	private JScrollPane scroll;
	private DefaultListModel/*<Usuario>*/ modelo;
	private JList/*<Usuario>*/ list;
	private JLabel lblNombreusuario;
	private JLabel labelImagen;
	private Component horizontalGlue;

	public Main_Prueba() {
		try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		setTitle("UnicornChat");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEjemplo.class.getResource("/umu/tds/apps/resources/icono app.png")));
		setBounds(420, 160, 716, 553);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		initComponentes();
		eventos();
		this.setVisible(true);
	}

	private void initComponentes() {
		JPanel botonera = new JPanel();
		getContentPane().add(botonera, BorderLayout.NORTH);
		botonera.setLayout(new BoxLayout(botonera, BoxLayout.X_AXIS));

		panelChatRecientes = new JPanel(new BorderLayout());
		panelChatActual = new JPanel(new BorderLayout());
		panelMensajes = new JPanel();
		panelTxtMensaje = new JPanel();
		panelMensajesInterno = new JPanel();
		scroll = new JScrollPane(panelMensajesInterno);

		getContentPane().add(panelChatRecientes, BorderLayout.WEST);
		getContentPane().add(panelChatActual, BorderLayout.CENTER);

		comboBox = new JComboBox<>();
		// actualizarComboBox();
		botonera.add(comboBox);

		modelo = new DefaultListModel();
		list = new JList(modelo);
		// list.setCellRenderer(new RecientesCellRenderer());
		panelChatRecientes.add(new JScrollPane(list), BorderLayout.CENTER);

		panelMensajesInterno.setLayout(new BoxLayout(panelMensajesInterno, BoxLayout.Y_AXIS));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.X_AXIS));
		panelMensajes.add(scroll);

		panelChatActual.add(panelMensajes, BorderLayout.CENTER);
		panelChatActual.add(panelTxtMensaje, BorderLayout.SOUTH);
		panelChatActual.setPreferredSize(new Dimension(400, 700));
		panelChatActual.setBackground(Color.WHITE);

		panelTxtMensaje.setLayout(new BoxLayout(panelTxtMensaje, BoxLayout.X_AXIS));
		crearEmojis(panelTxtMensaje);

		textMensaje = new JTextField();
		panelTxtMensaje.add(textMensaje);
		textMensaje.setColumns(10);
		
		JButton btnEnviarMensaje = new JButton();
		btnEnviarMensaje.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/avion-enviar-whatsapp.png")));
		btnEnviarMensaje.setText("Enviar");
		panelTxtMensaje.add(btnEnviarMensaje);

		btnEnviarMensaje.addActionListener(e -> {
			/*
			if (receptor != null && !textMensaje.getText().isEmpty()) {
				Controlador.INSTANCE.enviarMensaje(receptor, textMensaje.getText());
				textMensaje.setText("");
				actualizarChatRecientes();
				actualizarPanelChat();	
			} else {
				JOptionPane.showMessageDialog(this, "Selecciona un receptor válido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			*/
		});

		JButton btnEnviarArriba = new JButton("");
		btnEnviarArriba.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/enviar-mensaje-avionPapel.png")));
		botonera.add(btnEnviarArriba);

		btnEnviarArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				if (receptor == null) return;

				if (receptor.equals("Nuevo teléfono") || usuarioActual.existeGrupo(receptor)) {
					VentanaMensaje ventana = new VentanaMensaje(receptor, Main_Prueba.this, panelMensajesInterno, scroll, modelo, panelChatRecientes, list);
					ventana.setVisible(true);
				} else {
					actualizarPanelChat(scroll, panelMensajesInterno, receptor);
				}
				*/
			
			}
		});

		JButton botonBuscar = new JButton("");
        botonBuscar.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/apps/resources/lupa-buscar.png")));
        botonBuscar.addActionListener(ev -> {
            //this.setVisible(false);
            VentanaBuscar2 ventanaBuscar2 = new VentanaBuscar2();
            ventanaBuscar2.setVisible(true);

        });
        botonera.add(botonBuscar);
		
		JButton btnContactos = new JButton("Contactos");
        btnContactos.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/apps/resources/imagen-contactos.png")));
        btnContactos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	VentanaGrupos ventanaGrupos = new VentanaGrupos();
            	ventanaGrupos.setVisible(true);
                /*
            	VentanaContactos ventanaContactos = new VentanaContactos();
                ventanaContactos.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        actualizarComboBox();
                    }
                });
                ventanaContactos.setVisible(true);
                */
            }
        });
        botonera.add(btnContactos);
		
		
		JButton btnPremium = new JButton("Premium");
		btnPremium.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/icono-premium.png")));
		botonera.add(btnPremium);

		btnPremium.addActionListener(e -> {
			/*
			Premium ventana = new Premium(this, usuarioActual.esPremium(), receptor);
			ventana.setVisible(true);
			*/
			Premium ventanaPremium = new Premium();
			ventanaPremium.setVisible(true);
		});
		
		horizontalGlue = Box.createHorizontalGlue();
		botonera.add(horizontalGlue);

		lblNombreusuario = new JLabel("Usuario"); // usuarioActual.getNombre()
		botonera.add(lblNombreusuario);

		labelImagen = new JLabel();
		// labelImagen.setIcon(new ImageIcon(getClass().getResource(usuarioActual.getImagen())));
		labelImagen.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/imagenperfil1.png")));
		botonera.add(labelImagen);

		/*
		Timer timer = new Timer(1800, e -> {
			lblNombreusuario.setForeground(usuarioActual.esPremium() ? new Color(238, 202, 36) : Color.BLACK);
		});
		timer.start();
		*/
	}

	private void eventos() {
		comboBox.addActionListener(e -> receptor = (String) comboBox.getSelectedItem());

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(e -> {
			/*
			if (!e.getValueIsAdjusting() && list.getSelectedValue() != null) {
				receptor = usuarioActual.existeContacto(list.getSelectedValue().getTelefono());
				actualizarPanelChat();
			}
			*/
		});

		labelImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				String nuevaURL = JOptionPane.showInputDialog(Main_Prueba.this, "Introduce la URL de la nueva imagen:", "Cambiar imagen de Usuario", JOptionPane.PLAIN_MESSAGE);
				if (nuevaURL != null && !nuevaURL.isEmpty()) {
					try {
						URL recurso = getClass().getResource(nuevaURL);
						if (recurso != null) {
							ImageIcon icono = new ImageIcon(recurso);
							if (Controlador.INSTANCE.cambiarImagenUsuario(nuevaURL)) {
								labelImagen.setIcon(icono);
							}
						} else {
							JOptionPane.showMessageDialog(Main_Prueba.this, "URL no válida.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(Main_Prueba.this, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				*/
			}
		});

		// actualizarChatRecientes();
	}

	private void actualizarComboBox() {
		/*
		List<String> contactos = new LinkedList<>();
		usuarioActual.getListaContactos().forEach(c -> contactos.add(c.getNombre()));
		contactos.add("Nuevo teléfono");
		comboBox.setModel(new DefaultComboBoxModel<>(contactos.toArray(new String[0])));
		*/
	}

	private void actualizarPanelChat() {
		/*
		List<Mensaje> mensajes = Controlador.INSTANCE.obtenerChat(receptor);
		panelMensajesInterno.removeAll();

		for (Mensaje m : mensajes) {
			Color color = m.getEmisor().equals(usuarioActual) ? Color.GREEN : Color.GRAY;
			String nombre = m.getEmisor().equals(usuarioActual) ? usuarioActual.getNombre() : receptor;
			if (m.getEmoji() != -1) {
				panelMensajesInterno.add(new BubbleText(panelMensajesInterno, m.getEmoji(), color, nombre, m.getEmisor().equals(usuarioActual) ? BubbleText.SENT : BubbleText.RECEIVED, 12));
			} else {
				panelMensajesInterno.add(new BubbleText(panelMensajesInterno, m.getTexto(), color, nombre, m.getEmisor().equals(usuarioActual) ? BubbleText.SENT : BubbleText.RECEIVED));
			}
		}

		panelMensajesInterno.revalidate();
		panelMensajesInterno.repaint();
		SwingUtilities.invokeLater(() -> scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum()));
		*/
	}

	private void actualizarChatRecientes() {
		/*
		modelo.clear();
		List<Mensaje> ultimos = usuarioActual.obtenerTodosUltimosMensajes();
		for (Mensaje m : ultimos) {
			Usuario u = m.getEmisor().equals(usuarioActual) ? m.getReceptor() : m.getEmisor();
			modelo.addElement(u);
		}
		*/
	}

	private void crearEmojis(JPanel panel) {
		JButton btnEmojis = new JButton("");
		btnEmojis.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/emoticonos-texto.png")));
		btnEmojis.setPreferredSize(new Dimension(40, 21));
		panel.add(btnEmojis);

		JPopupMenu menuEmojis = new JPopupMenu();
		JPanel panelEmojis = new JPanel(new GridLayout(2, 4, 3, 3));
		panelEmojis.setBackground(Color.WHITE);
		menuEmojis.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panelEmojis.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		for (int i = 0; i < 8; i++) {
			JButton emojiButton = new JButton("☺"); // BubbleText.getEmoji(i)
			//emojiButton.setIcon(new ImageIcon(Main_Prueba.class.getResource("/umu/tds/apps/resources/emoticonos-texto.png")));
			emojiButton.setBackground(Color.WHITE);
			emojiButton.setBorderPainted(false);
			emojiButton.setFocusPainted(false);
			int idx = i;
			emojiButton.addActionListener(e -> {
				/*
				Controlador.INSTANCE.enviarMensaje(receptor, idx);
				actualizarPanelChat();
				actualizarChatRecientes();
				menuEmojis.setVisible(false);
				*/
			});
			panelEmojis.add(emojiButton);
		}
		menuEmojis.add(panelEmojis);
		btnEmojis.addActionListener(e -> {
			if (menuEmojiAbierto) {
				menuEmojis.setVisible(false);
				menuEmojiAbierto = false;
			} else {
				menuEmojis.show(btnEmojis, 0, -menuEmojis.getPreferredSize().height);
				menuEmojiAbierto = true;
			}
		});
	}

}
