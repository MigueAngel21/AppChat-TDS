package umu.tds.apps.AppChat;

public class DescuentoFecha extends Descuento{

	private int dia;
	private int mes;
	private int anio;

	public DescuentoFecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}

	public double calcularDescuento() {
		return 0.1;
	}

	public boolean esAplicable() {
		return true;
	}

	public String toString() {
		return "Descuento del 10% si la fecha es: " + dia + "/" + mes + "/" + anio;
	}
}
