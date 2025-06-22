package umu.tds.apps.AppChat;

public abstract class Descuento {
	
	//porcentaje de descuento aplicado al precio base
	protected Double porcentajeDescuento;
	// precio base del producto al que se le aplica el descuento
	public static final double PRECIO_BASE = 19.99;
	
	//Constructor con porcentaje de descuento
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
