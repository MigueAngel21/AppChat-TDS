package umu.tds.apps.ventanas;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import umu.tds.apps.AppChat.GeneradorPDF;
import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.Controlador.Controlador;

import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Premium extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel mainPanel;
    private JPanel buttonPanel;

    private JLabel lblEstadoPremium;
    private JLabel lblPrecioActual;
    private JButton btnSuscribirse;
    private JButton btnAnular;
    private JButton btnExportarPDF;
    private JTextArea txtVentajasPremium;

    private boolean esPremium = false; // Puedes ajustar esto según usuario actual
    private double precio = 9.99; // Simula Controlador.obtenerPrecioConDescuento()
    private String receptor = "contactoEjemplo";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Premium frame = new Premium();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Premium() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(420, 160, 716, 553);
        setTitle("UNICORNCHAT");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Premium.class.getResource("/umu/tds/apps/resources/icono app.png")));

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(mainPanel, BorderLayout.CENTER);

        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        mainPanel.removeAll();

        JLabel lblTitulo = new JLabel("GESTIÓN DE SUSCRIPCIÓN");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        lblEstadoPremium = new JLabel(esPremium ?
            "Actualmente eres Premium" :
            "Actualmente no eres Premium");
        lblEstadoPremium.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblEstadoPremium.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblEstadoPremium);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        lblPrecioActual = new JLabel("Cuota actual: " + (esPremium ? precio + "€" : "0€"));
        lblPrecioActual.setFont(new Font("Dialog", Font.PLAIN, 15));
        lblPrecioActual.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblPrecioActual);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (esPremium) {
            btnExportarPDF = new JButton("Exportar PDF");
            btnAnular = new JButton("Anular suscripción");

            buttonPanel.add(btnExportarPDF);
            buttonPanel.add(btnAnular);

            // TODO: implementar exportarPDF()
            btnExportarPDF.addActionListener(e -> exportarPDF());

            btnAnular.addActionListener(e -> anularSuscripcion());

        } else {
            btnSuscribirse = new JButton("Suscribirse por " + precio + "€");
            buttonPanel.add(btnSuscribirse);

            btnSuscribirse.addActionListener(this::suscribirse);

            txtVentajasPremium = new JTextArea(
                "Ventajas de ser Premium:\n" +
                "- Exportar chats a PDF\n" +
                "- Acceso prioritario\n" +
                "- Soporte 24/7"
            );
            txtVentajasPremium.setEditable(false);
            txtVentajasPremium.setBackground(null);
            txtVentajasPremium.setFont(new Font("Dialog", Font.PLAIN, 14));
            txtVentajasPremium.setAlignmentX(Component.CENTER_ALIGNMENT);

            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            mainPanel.add(txtVentajasPremium);
        }

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(buttonPanel);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void suscribirse(ActionEvent e) {
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Deseas suscribirte al plan Premium por " + precio + "€?",
            "Confirmar suscripción",
            JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            // Lógica real de suscripción
            //Controlador.INSTANCE.activarPremium(); // Suponiendo que existe
            esPremium = true;

            JOptionPane.showMessageDialog(this,
                "¡Bienvenido a Premium!",
                "Suscripción completada",
                JOptionPane.INFORMATION_MESSAGE);

            actualizarUIaPremium();
        }
    }

    private void anularSuscripcion() {
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que deseas anular tu suscripción Premium?",
            "Confirmar anulación",
            JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            // Lógica real de anulación
            //Controlador.INSTANCE.anularPremium(); // Suponiendo que existe
            esPremium = false;

            JOptionPane.showMessageDialog(this,
                "Suscripción Premium anulada.",
                "Anulación completada",
                JOptionPane.INFORMATION_MESSAGE);

            actualizarUIaNoPremium();
        }
    }
    
    private void exportarPDF() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar PDF");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF files (*.pdf)", "pdf"));

            int seleccion = fileChooser.showSaveDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                if (!ruta.toLowerCase().endsWith(".pdf")) {
                    ruta += ".pdf";
                }

                // Obtener el chat del receptor
                java.util.List<Mensaje> mensajes = Controlador.INSTANCE.obtenerChat(receptor);

                // Exportar usando el exportador de PDF
                GeneradorPDF.exportarChat(receptor, mensajes, ruta);

                JOptionPane.showMessageDialog(this,
                    "PDF exportado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al exportar el PDF: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    
    private void actualizarUIaPremium() {
        inicializarInterfaz(); // reconstruye con esPremium = true
    }

    private void actualizarUIaNoPremium() {
        inicializarInterfaz(); // reconstruye con esPremium = false
    }
}

