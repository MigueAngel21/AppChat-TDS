package umu.tds.apps.ventanas;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;


import umu.tds.apps.AppChat.Descuento;
import umu.tds.apps.AppChat.GeneradorPDF;
import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.Controlador.Controlador;

public class Premium extends JDialog {
    
	private static final long serialVersionUID = 1L;
	
	private JLabel lblEstadoPremium;
    private JLabel lblPrecioActual;
    private JButton btnSuscribirse;
    private JButton btnAnular;
    private JButton btnExportarPDF;
    private JTextArea txtVentajasPremium;
    private String receptor;
    private double precio = Descuento.PRECIO_BASE;

    public Premium(JFrame parent, boolean esPremium, String receptor) {
        super(parent, "Gestión de Suscripción", true); // true para hacerla modal
        this.receptor = receptor;
        inicializarComponentes(esPremium);
    }

    private void inicializarComponentes(boolean esPremium) {
        // Configuración básica de la ventana
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Panel principal con padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel lblTitulo = new JLabel("GESTIÓN DE SUSCRIPCIÓN");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
        mainPanel.add(lblTitulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Estado Premium
        lblEstadoPremium = new JLabel(esPremium ? 
            "Actualmente eres Premium" : 
            "Actualmente no eres Premium");
        lblEstadoPremium.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblEstadoPremium);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Precio actual
        lblPrecioActual = new JLabel("Cuota actual: " + 
            (esPremium ? Controlador.INSTANCE.getUsuarioActual().getPrecioSuscripcion() + "€" : "0€"));
        lblPrecioActual.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblPrecioActual);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (esPremium) {
            // Botones para usuarios Premium
            btnExportarPDF = new JButton("Exportar PDF");
            btnAnular = new JButton("Anular suscripción");
            
            buttonPanel.add(btnExportarPDF);
            buttonPanel.add(btnAnular);

            // Añadir listeners
            btnExportarPDF.addActionListener(e -> exportarPDF());
            btnAnular.addActionListener(e -> anularSuscripcion());
        } else {
            // Botón para usuarios no Premium
        	precio = Controlador.INSTANCE.obtenerPrecioConDescuento();
        	precio = Math.round(precio * 100.0) / 100.0;
            btnSuscribirse = new JButton("Suscribirse por " + precio + "€");
            buttonPanel.add(btnSuscribirse);
            
            // Añadir listener
            btnSuscribirse.addActionListener(e -> suscribirse());

            // Texto de ventajas Premium
            txtVentajasPremium = new JTextArea(
                "Ventajas de ser Premium:\n" +
                "- Exportar chats a PDF\n" +
                "- Acceso prioritario\n" +
                "- Soporte 24/7"
            );
            txtVentajasPremium.setEditable(false);
            txtVentajasPremium.setBackground(null);
            txtVentajasPremium.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            mainPanel.add(txtVentajasPremium);
        }

        mainPanel.add(buttonPanel);
        getContentPane().add(mainPanel);
    }

    private void exportarPDF() {
        try {
            // Crear un FileChooser para que el usuario elija dónde guardar el PDF
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar PDF");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF files (*.pdf)", "pdf"));
            
            int userSelection = fileChooser.showSaveDialog(this);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
                if (!rutaArchivo.toLowerCase().endsWith(".pdf")) {
                    rutaArchivo += ".pdf";
                }
                
                //TODO DEBERIA FUNCIONAR
                List<Mensaje> mensajes = Controlador.INSTANCE.obtenerChat(receptor);
                
                GeneradorPDF.exportarChat(receptor, mensajes, rutaArchivo);
                
                JOptionPane.showMessageDialog(this,
                    "PDF exportado correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al exportar el PDF: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void anularSuscripcion() {
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que deseas anular tu suscripción Premium?",
            "Confirmar anulación",
            JOptionPane.YES_NO_OPTION);
            
        if (respuesta == JOptionPane.YES_OPTION) {
            // Implementar lógica de anulación
        	Controlador.INSTANCE.anularPremium();
            JOptionPane.showMessageDialog(this,
                "Suscripción Premium anulada",
                "Anulación completada",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private void suscribirse() {
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Deseas suscribirte al plan Premium por " + precio + "€?",
            "Confirmar suscripción",
            JOptionPane.YES_NO_OPTION);
            
        if (respuesta == JOptionPane.YES_OPTION) {
            Controlador.INSTANCE.activarPremium();
            JOptionPane.showMessageDialog(this,
                "¡Bienvenido a Premium!",
                "Suscripción completada",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
}
