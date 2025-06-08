package umu.tds.apps.AppChat;

import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class GeneradorPDF {
	/**
	 * Exporta un chat a un archivo PDF.
	 *
	 * @param receptor El nombre del contacto con el que se ha mantenido la
	 *                 conversación.
	 * @param mensajes Lista de mensajes que componen el chat.
	 * @param ruta     Ruta donde se guardará el archivo PDF.
	 */
	public static void exportarChat(String receptor, List<Mensaje> mensajes, String ruta) {
		try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
            document.open();

            // Añadir título
            Font fontTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Paragraph titulo = new Paragraph("Chat con " + receptor, fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph("\n")); // Espacio después del título

            // Añadir mensajes
            Font fontMensaje = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font fontFecha = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            for (Mensaje mensaje : mensajes) {
                // Añadir emisor y mensaje
                Paragraph p = new Paragraph();
                p.add(new Chunk(mensaje.getEmisor().getUsuario() + ": ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
                p.add(new Chunk(mensaje.getTexto(), fontMensaje));
                document.add(p);

                // Añadir fecha
                Paragraph fecha = new Paragraph(mensaje.getFecha().format(formatter), fontFecha);
                fecha.setAlignment(Element.ALIGN_RIGHT);
                document.add(fecha);
                
                document.add(new Paragraph("\n")); // Espacio entre mensajes
            }

            document.close();
            
        } catch (Exception e) {
            throw new RuntimeException("Error al exportar el chat a PDF", e);
        }
		
	}
}
