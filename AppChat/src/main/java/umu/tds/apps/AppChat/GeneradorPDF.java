package umu.tds.apps.AppChat;

import java.util.List;

public class GeneradorPDF {
	private static GeneradorPDF generadorPDF;

	private GeneradorPDF() {
		//No se sabe si tenemos que meter algo aquí
	}

	public static GeneradorPDF getInstancia() {
		if (generadorPDF == null)
			generadorPDF = new GeneradorPDF();
		return generadorPDF;
	}

	public void crearPDF() {
		// Genera un PDF
	}

	public static void exportarChat(String receptor, List<Mensaje> mensajes, String ruta) {
		// TODO Auto-generated method stub
		
	}
}
