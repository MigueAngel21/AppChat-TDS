package umu.tds.apps.AppChat;

import java.time.LocalDate;

public class DescuentoFecha extends Descuento{

	
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
    //constructor con porcentaje de descuento, fecha de inicio y fecha de fin
	public DescuentoFecha(double porcentajeDescuento, LocalDate fechaInicio, LocalDate fechaFin) {
		super(porcentajeDescuento); 
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	// te dice si el descuento es aplicable o no
	@Override
	public boolean esAplicable(Usuario usuario) {
		LocalDate fechaR = usuario.getFechaRegistro();
		return !fechaR.isBefore(fechaInicio) && !fechaR.isAfter(fechaFin);
	}
	
	
}
