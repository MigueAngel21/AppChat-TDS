package umu.tds.apps.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import tds.BubbleText;
import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.Controlador.Controlador;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class VentanaMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField chatdeWhatsapp;

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
	*/

	/**
	 * Create the frame.
	 */
	public VentanaMain() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//cambiar icono de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEjemplo.class.getResource("/umu/tds/apps/resources/icono app.png")));
		setBounds(350, 8, 833, 771);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.X_AXIS));
		
		JComboBox ComboBoxContactos = new JComboBox();
		ComboBoxContactos.setModel(new DefaultComboBoxModel(new String[] {"Contacto 1", "Contacto 2", "Contacto 2", "COntacto 4", "Contacto 5"}));
		panelNorte.add(ComboBoxContactos);
		
		JButton botonEnviar = new JButton("");
		botonEnviar.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/apps/resources/enviar-mensaje-avionPapel.png")));
		panelNorte.add(botonEnviar);
		
		JButton botonBuscar = new JButton("");
		botonBuscar.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/apps/resources/lupa-buscar.png")));
		panelNorte.add(botonBuscar);
		botonBuscar.addActionListener(ev -> {
			//this.setVisible(false);
			VentanaBuscar ventanaBuscar = new VentanaBuscar();
			ventanaBuscar.setVisible(true);
			
		});
		
		JButton botonContactos = new JButton("Contactos");
		botonContactos.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/apps/resources/imagen-contactos.png")));	
		panelNorte.add(botonContactos);
		botonContactos.addActionListener(ev -> {
			//this.setVisible(false);
			VentanaGrupos ventanaGrupos = new VentanaGrupos();
			ventanaGrupos.setVisible(true);
		});
		
		JButton botonPremium = new JButton("premium");
		botonPremium.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/apps/resources/icono-premium.png")));
		botonPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// this.setVisible(false);
				Premium ventanaPremium = new Premium();
				ventanaPremium.setVisible(true);
				
			}
		});
		panelNorte.add(botonPremium);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panelNorte.add(horizontalGlue);
		
		JLabel EtqUsuario = new JLabel("Nombre Usuario");
		EtqUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelNorte.add(EtqUsuario);
		
		JLabel FotoUsuario = new JLabel("");
		FotoUsuario.setIcon(new ImageIcon(VentanaMain.class.getResource("/umu/tds/apps/resources/icono-usuario-ventanaMain.png")));
		panelNorte.add(FotoUsuario);
		
		JPanel panelContactos = new JPanel();
		panelContactos.setBorder(new TitledBorder(null, "Contactos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		contentPane.add(panelContactos, BorderLayout.WEST);
		panelContactos.setLayout(new BorderLayout(0, 0));
		
		
		JList<String> list = new JList();
		list.setModel(new AbstractListModel() {
			//aqui tiene que haber una llamada al controlador para que te muestre el ultimo mensaje mas reciente para el usuario
			List<Mensaje> mensajes = Controlador.devolverListaMensajesRecientesPorUsuario("usuario");
			public int getSize() {
				return mensajes.size();
			}
			public Object getElementAt(int index) {
				return mensajes.get(index);
			}
		});
		//panelContactos.add(list);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list); //por defecto la barra es vertical
		panelContactos.add(scrollPane);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelCentro.add(panel, BorderLayout.SOUTH);
		
		JButton botonEmojis = new JButton("");
		URL url = getClass().getResource("/umu/tds/apps/resources/emoticonos-texto.png");
		if (url != null) {
			Image fotoImage = null;
			try {
				fotoImage = ImageIO.read(url).getScaledInstance(19, 19, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			botonEmojis.setIcon(new ImageIcon(fotoImage));
		}
		panel.add(botonEmojis);
		
		chatdeWhatsapp = new JTextField();
		panel.add(chatdeWhatsapp);
		chatdeWhatsapp.setColumns(40);
		
		JButton botonEnviarMsg = new JButton("");
		URL url1 = getClass().getResource("/umu/tds/apps/resources/avion-enviar-whatsapp.png");
		if (url1 != null) {
			Image fotoImage = null;
			try {
				fotoImage = ImageIO.read(url1).getScaledInstance(19, 19, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			botonEnviarMsg.setIcon(new ImageIcon(fotoImage));
		}
		panel.add(botonEnviarMsg);
		
		JPanel chat = new JPanel();
		chat.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		chat.setLayout(new BoxLayout(chat,BoxLayout.Y_AXIS)); 
		chat.setSize(400,700); 
		chat.setMinimumSize(new Dimension(400,700)); 
		chat.setMaximumSize(new Dimension(400,700)); 
		chat.setPreferredSize(new Dimension(400,700));
		
		BubbleText burbuja; 
		burbuja=new BubbleText(chat,"Alumno, cual era tu duda ?", Color.GREEN, "J.Ramón", BubbleText.SENT); 
		chat.add(burbuja);
		
		BubbleText burbuja2; 
		burbuja2=new BubbleText(chat, 
		"Pues verá, no se porque me pongo tan horny cuando pienso en tds", 
		Color.LIGHT_GRAY, "Alumno", BubbleText.RECEIVED); 
		chat.add(burbuja2);
		
		BubbleText burbuja3; 
		burbuja3=new BubbleText(chat,"No estoy seguro, pero será porque estoy yo",  
		Color.GREEN, "J.Ramón", BubbleText.SENT, 24); 
		chat.add(burbuja3); 
		
		BubbleText burbuja4; 
		burbuja4=new BubbleText(chat,"Venta para mi despacho y lo hablamos ;)",  
		Color.GREEN, "J.Ramón", BubbleText.SENT, 24); 
		chat.add(burbuja4);
		
		BubbleText burbujaEmo =new BubbleText(chat, 4, Color.GREEN, "J.Ramón", BubbleText.SENT,18); 
		chat.add(burbujaEmo);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setViewportView(chat); //por defecto la barra es vertical
		panelCentro.add(scrollPane2);
		
		//panelCentro.add(chat, BorderLayout.CENTER);
		
		this.setTitle("UNICORNCHAT");
		this.setVisible(true);
	}

}
