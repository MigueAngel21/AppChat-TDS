package umu.tds.apps.ventanas;


import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;

import umu.tds.apps.Controlador.Controlador;
import umu.tds.apps.AppChat.Usuario;
import umu.tds.apps.AppChat.Contacto;
import umu.tds.apps.AppChat.Grupo;
import umu.tds.apps.AppChat.ContactoIndividual;


public class VentanaGrupos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String grupo = "Añadir grupo";

	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VentanaGrupos frame = new VentanaGrupos();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public VentanaGrupos() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//cambiar icono de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEjemplo.class.getResource("/umu/tds/apps/resources/icono app.png")));
		setBounds(420, 160, 716, 553);
		this.setTitle("UNICORNCHAT");
		this.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "lista de contactos",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		contentPane.add(panelMain);
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.X_AXIS));

		JPanel panelContactos = new JPanel();
		panelContactos.setSize(new Dimension(150, 0));
		panelContactos.setLayout(new BoxLayout(panelContactos, BoxLayout.X_AXIS));
		
		DefaultListModel<String> modelo = new DefaultListModel<>();
		DefaultListModel<String> modelo2 = new DefaultListModel<>();
		
		JList<String> lista2 = new JList<>(modelo2);
		// lista2.setCellRenderer(new ContactoCellRenderer()); // Comentado: requiere clase externa
		lista2.setModel(modelo2);

		// Simulación de lista de contactos sin persistencia
		modelo.addElement("Contacto 1");
		modelo.addElement("Contacto 2");
		modelo.addElement("Grupo de amigos");

		JPanel panelGrupo = new JPanel();

		JList<String> lista = new JList<>(modelo);
		// lista.setCellRenderer(new ContactoCellRenderer()); // Comentado: requiere clase externa

		lista.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String seleccionado = lista.getSelectedValue();
				if (seleccionado != null && seleccionado.contains("Grupo")) {
					modelo2.clear();
					modelo2.addElement("Miembro A");
					modelo2.addElement("Miembro B");

					grupo = seleccionado;
					TitledBorder titulo = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), grupo);
					panelGrupo.setBorder(titulo);
				}
			}
		});
		
		panelContactos.add(new JScrollPane(lista));
		panelMain.add(panelContactos);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.RED);
		panelMain.add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

		JButton btnDerecha = new JButton(">>>");
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccionado = lista.getSelectedValue();
				if (seleccionado != null && !seleccionado.contains("Grupo")) {
					modelo.removeElement(seleccionado);
					modelo2.addElement(seleccionado);
				}
			}
		});
		panelBotones.add(btnDerecha);

		JButton btnIzquierda = new JButton("<<<");
		panelBotones.add(btnIzquierda);

		panelGrupo.setBorder(new TitledBorder(new LineBorder(Color.BLACK), grupo, TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelGrupo.setLayout(new BoxLayout(panelGrupo, BoxLayout.X_AXIS));
		panelGrupo.add(new JScrollPane(lista2));
		panelMain.add(panelGrupo);

		JPanel panelAddContacto = new JPanel();
		contentPane.add(panelAddContacto);

		JButton btnAddContactoInd = new JButton("Añadir Contacto");
		btnAddContactoInd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAddContacto v = new VentanaAddContacto(modelo);
				v.setVisible(true);
			}
		});
		panelAddContacto.add(btnAddContactoInd);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setPreferredSize(new Dimension(230, 0));
		panelAddContacto.add(horizontalGlue);

		JButton btnAddContactoGrp = new JButton("Añadir o Modificar Grupo");
		btnAddContactoGrp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (grupo.equals("Añadir grupo")) {
					List<String> listaGrupo = new LinkedList<>();
					for (int i = 0; i < modelo2.size(); i++) {
						listaGrupo.add(modelo2.get(i));
					}

					if (listaGrupo.isEmpty()) {
						JOptionPane.showMessageDialog(VentanaGrupos.this, "El grupo está vacío", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					String nombreGrupo = JOptionPane.showInputDialog(VentanaGrupos.this, "Introduce el nombre del grupo:",
							"Crear grupo", JOptionPane.PLAIN_MESSAGE);

					if (nombreGrupo == null || nombreGrupo.isEmpty()) {
						JOptionPane.showMessageDialog(VentanaGrupos.this, "Introduce un nombre para el grupo", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Comentado: validaciones con Controlador
					// if (Controlador.INSTANCE.existeGrupo(nombreGrupo)) {
					// 	JOptionPane.showMessageDialog(...);
					// 	return;
					// }

					JOptionPane.showMessageDialog(VentanaGrupos.this,
							"¡Has creado el grupo " + nombreGrupo + " correctamente!",
							"Grupo creado", JOptionPane.INFORMATION_MESSAGE);
					grupo = nombreGrupo;
					modelo2.clear();
				} else {
					JOptionPane.showMessageDialog(VentanaGrupos.this,
							"Grupo '" + grupo + "' modificado correctamente.",
							"Grupo modificado", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panelAddContacto.add(btnAddContactoGrp);
	}

		
	

}
