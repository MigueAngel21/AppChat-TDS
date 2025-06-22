package umu.tds.apps.AppChat;

import java.time.LocalDate;

//Clase factoría para la creación de objetos de tipo Descuento.
public class FactoriaDescuento {
	
	//Crea y devuelve un descuento basado en fechas (DescuentoFecha).
	public static Descuento crearDescuentoFecha(double porcentajeDescuento, LocalDate fechaInicio, LocalDate fechaFin) {
		return new DescuentoFecha(porcentajeDescuento, fechaInicio, fechaFin);
	}

	//Crea y devuelve un descuento basado en mensajes enviados (DescuentoMensaje).
	public static Descuento crearDescuentoMensaje(double porcentajeDescuento, int cantMensajes) {
		return new DescuentoMensaje(porcentajeDescuento, cantMensajes);
	}
	
	

}
