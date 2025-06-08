package umu.tds.apps.AppChat;

public class ContactoIndividual extends Contacto{
	
	private Usuario usuario;
	
	public ContactoIndividual(String nombre, Usuario usuario) {
		super(nombre);
		this.usuario = usuario;
	}
	
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
