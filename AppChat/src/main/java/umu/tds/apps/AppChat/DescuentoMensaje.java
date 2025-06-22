package umu.tds.apps.AppChat;

import java.time.LocalDateTime;

public class DescuentoMensaje extends Descuento{

	private int cantidadMensajes;

	//Constructor para crear un descuento basado en mensajes enviados.
	public DescuentoMensaje(double porcentajeDescuento, int cantidadMensajes) {
		super(porcentajeDescuento);
		this.cantidadMensajes = cantidadMensajes;
	}
	
	//Determina si este descuento es aplicable a un usuario específico, verificando 
	//si ha enviado suficientes mensajes en el último mes.
	@Override
	public boolean esAplicable(Usuario usuario) {
		
		LocalDateTime mesAnterior = LocalDateTime.now().minusMonths(1);
		// Filtrar los mensajes enviados por el usuario en el último mes y contar cuántos mensajes ha enviado
		long mensajesEnviadosUltimoMes = usuario.getMensajesEnviados().stream()
				.filter(mensaje -> !mensaje.getFecha().isBefore(mesAnterior))
				.count();
		
		return mensajesEnviadosUltimoMes >= cantidadMensajes;
	}
	
}
