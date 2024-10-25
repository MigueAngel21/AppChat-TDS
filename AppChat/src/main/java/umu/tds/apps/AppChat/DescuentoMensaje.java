package umu.tds.apps.AppChat;

public class DescuentoMensaje extends Descuento{

	private int mensajes;

	public DescuentoMensaje(int mensajes) {
		this.mensajes = mensajes;
	}

	public double calcularDescuento() {
		return 0.1;
	}

	public boolean esAplicable() {
		return true;
	}

	public String toString() {
		return "Descuento del 10% si el numero de mensajes es: " + mensajes;
	}
}
