package umu.tds.apps.ventanas;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import tds.BubbleText;
import umu.tds.apps.Controlador.Controlador;
import umu.tds.apps.AppChat.Usuario;


public class VentanaMensaje extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTelefono;
	private JTextField textMensaje;
	private boolean menuEmojiAbierto = false;

	public VentanaMensaje(String tipo, Main_Prueba ventanaMain, JPanel panelMensajesInterno, JScrollPane scroll,
			DefaultListModel<Usuario> modelo, JPanel panelChatRecientes, JList<Usuario> list) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(420, 160, 716, 553);
		this.setTitle("UNICORNCHAT");
		//cambiar icono de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaMensaje.class.getResource("/umu/tds/apps/resources/icono app.png")));
				
		setLocationRelativeTo(null);
		this.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel(
				tipo.equals("Nuevo teléfono") ? "Enviar mensaje a nuevo teléfono" : "Enviar mensaje a " + tipo);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitulo.setBounds(20, 20, 400, 20);
		contentPane.add(lblTitulo);

		txtTelefono = new JTextField();
		// Campo de teléfono (solo si es nuevo teléfono)
		if (tipo.equals("Nuevo teléfono")) {
			JLabel lblTelefono = new JLabel("Introduce el teléfono:");
			lblTelefono.setBounds(20, 60, 120, 20);
			contentPane.add(lblTelefono);

			txtTelefono.setBounds(150, 60, 150, 25);
			contentPane.add(txtTelefono);
		} else {
			txtTelefono.setText(tipo);
		}

		JButton btnEmojis = new JButton("");
		btnEmojis.setIcon(BubbleText.getEmoji(6));
		btnEmojis.setPreferredSize(new Dimension(40, 21));
		btnEmojis.setMinimumSize(new Dimension(40, 21));
		btnEmojis.setMaximumSize(new Dimension(40, 21));
		btnEmojis.setBounds(20, 200, 30, 30);
		contentPane.add(btnEmojis);

		JPopupMenu menuEmojis = new JPopupMenu();
		JPanel panelEmojis = new JPanel(new GridLayout(2, 4, 3, 3)); //2 filas, 4 columnas, gap de 2px
		panelEmojis.setBackground(Color.WHITE);
		menuEmojis.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panelEmojis.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Creamos los 8 botones de emojis
		VentanaMensaje aux = this;
		for (int i = 0; i < 8; i++) {
			JButton emojiButton = new JButton(BubbleText.getEmoji(i));
			emojiButton.setBackground(Color.WHITE);
			emojiButton.setBorderPainted(false);
			emojiButton.setFocusPainted(false);

			final int emojiIndex = i;
			emojiButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (hayTelefono()) {
						Controlador.INSTANCE.enviarMensaje(txtTelefono.getText(), emojiIndex);
						ventanaMain.actualizarPanelChat(scroll, panelMensajesInterno, txtTelefono.getText());
						ventanaMain.actualizarChatRecientes(modelo, panelChatRecientes, list);
						menuEmojis.setVisible(false);
						menuEmojiAbierto = false;
						dispose();
					} else {
						JOptionPane.showMessageDialog(aux, "Introduce un teléfono para enviar un emoji", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});

			panelEmojis.add(emojiButton);
		}

		menuEmojis.add(panelEmojis);

		btnEmojis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (menuEmojiAbierto) {
					menuEmojis.setVisible(false);
					menuEmojiAbierto = false;
				} else {
					menuEmojis.show(btnEmojis, 0, -menuEmojis.getPreferredSize().height);
					menuEmojiAbierto = true;
				}
			}
		});

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
		textMensaje.setBounds(60, 200, 280, 30);
		contentPane.add(textMensaje);

		JButton btnEnviar = new JButton("");
		btnEnviar.setBounds(350, 200, 41, 30);
		btnEnviar.setIcon(new ImageIcon(VentanaMensaje.class.getResource("/umu/tds/apps/resources/avion-enviar-whatsapp.png")));
		btnEnviar.setFocusPainted(false);
		contentPane.add(btnEnviar);
		btnEnviar.addActionListener(e -> {
			
			if (hayTexto()) {
				if (hayTelefono()) {
					try {
						String msg = textMensaje.getText();
						Controlador.INSTANCE.enviarMensaje(txtTelefono.getText(), msg);
						ventanaMain.actualizarPanelChat(scroll, panelMensajesInterno, txtTelefono.getText());
						ventanaMain.actualizarChatRecientes(modelo, panelChatRecientes, list);
						textMensaje.setText("");
						dispose();
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(aux, ex.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(aux, "Introduce un teléfono para enviar un mensaje", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(aux, "Introduce un texto para enviar un mensaje", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
		});
	}

	
	private boolean hayTexto() {
		return textMensaje.getText().length() > 0;
	}

	private boolean hayTelefono() {
		return txtTelefono.getText().length() > 0;
	}
	

}
