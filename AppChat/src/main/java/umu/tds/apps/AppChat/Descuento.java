package umu.tds.apps.AppChat;

public abstract class Descuento {
	
	protected Double porcentajeDescuento;
	public static final double PRECIO_BASE = 19.99;
	
	public Descuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	// Calcula el precio final aplicando el porcentaje de descuento
	public double calcularDescuentoFinal() {
		return PRECIO_BASE * (1 - (porcentajeDescuento / 100));
	}
	
	// Método abstracto para determinar si el descuento es aplicable
	public abstract boolean esAplicable(Usuario usuario);
}
