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

public class VentanaBuscar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		FlowLayout fl_panel_Norte = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panel_Norte.setAlignOnBaseline(true);
		panel_Norte.setLayout(fl_panel_Norte);
		
		textField = new JTextField();
		panel_Norte.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel_Norte.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_centro = new JPanel();
	
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 12));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"jfhsiodjfs+ç", "sodfhsdfsç", "sdfsdfsdf", "sdfsdfsdf", "sdfsdfsdfsçdfsd", "fsfdsdfsdfs", "dfsfsdf"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		
		panelCentro.add(new JScrollPane(list), BorderLayout.CENTER);
		
		
		
		this.setTitle("UNICORNCHAT");
		this.setVisible(true);
	}

}
