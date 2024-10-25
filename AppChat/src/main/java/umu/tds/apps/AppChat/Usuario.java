package umu.tds.apps.AppChat;

import java.time.LocalDate;
import java.util.*;

public class Usuario {
	private String usuario;
	private String contraseña;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String imagen;
	private String saludo;
	
	private List<Contacto> contactos;
	private List<Mensaje> mensajesEnviados;
	private List<Mensaje> mensajesRecibidos;
	private Descuento descuento;
	
	public Usuario(String usuario, String contraseña, String telefono, LocalDate fechaNacimiento, String imagen,
			String saludo) {
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen = imagen;
		this.saludo = saludo;
		mensajesEnviados = new LinkedList<Mensaje>();
		mensajesRecibidos = new LinkedList<Mensaje>();
		descuento = null;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public String getSaludo() {
		return saludo;
	}
	
	public List<Contacto> getContactos() {
		return contactos;
	}
	
	public Descuento getDescuento() {
		return descuento;
	}
	
	public List<Mensaje> getMensajesEnviados() {
		return mensajesEnviados;
	}
	
	public List<Mensaje> getMensajesRecibidos() {
		return mensajesRecibidos;
	}
	
	
	// mirar si hay que hacerlo asi o crear lista de contactos y añadir dentro de cada contacto los mensajes
	// mirar si puede ser un mapa asociando cada usuario de los contactos con sus mensajes intercambiados
	public void enviarMensaje(Mensaje mensaje) {
		mensajesEnviados.add(mensaje);
	}
	
	public void recibirMensaje(Mensaje mensaje) {
		mensajesRecibidos.add(mensaje);
	}		
	
	
	//Devuelve el contacto individual que es otroUsuario, si lo hubiera , si no, devuelve null
	public Contacto getContactoIndividual(String otroUsuario) {
		
		return null;
	}
	
	//Devuelve la lista de mensajes intercambiados con otroUsuario(tanto enviados como recibidos), orrdenados por fecha y hora
	public List<Mensaje> getChatMensajes(String otroUsuario) {
		
		return null;
	}
	
}
