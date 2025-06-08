package umu.tds.apps.AppChat;

import java.time.LocalDate;

public class FactoriaDescuento {
	
	public static Descuento crearDescuentoFecha(double porcentajeDescuento, LocalDate fechaInicio, LocalDate fechaFin) {
		return new DescuentoFecha(porcentajeDescuento, fechaInicio, fechaFin);
	}

	public static Descuento crearDescuentoMensaje(double porcentajeDescuento, int cantMensajes) {
		return new DescuentoMensaje(porcentajeDescuento, cantMensajes);
	}
	
	

}
