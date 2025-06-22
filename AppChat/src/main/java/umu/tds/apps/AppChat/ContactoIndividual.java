package umu.tds.apps.AppChat;

public class ContactoIndividual extends Contacto{
	
	private Usuario usuario;
	
	// Constructor de ContactoIndividual con nombre y usuario
	public ContactoIndividual(String nombre, Usuario usuario) {
		super(nombre);
		this.usuario = usuario;
	}
	
	// getters y setters
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getImagen() {
		return usuario.getImagen();
	}
	
	@Override
	public String toString() {
		return usuario.toString();
	}
}
