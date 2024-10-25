package umu.tds.apps.AppChat;

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
}
