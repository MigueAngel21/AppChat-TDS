package umu.tds.apps.AppChat;

import java.util.*;

public class Grupo extends Contacto{
	private String imagen;
	private List<Contacto> miembros; 
	
	// Constructor para crear un grupo con nombre e imagen, inicializando la lista de miembros.
	public Grupo(String nombre, String imagen) {
		super(nombre);
		miembros = new LinkedList<Contacto>();
		
	}
	
	// Constructor para crear un grupo con nombre, miembros e imagen.
	public Grupo(String nombre, List<Contacto> miembros, String imagen) {
		super(nombre);
		this.imagen = imagen;
		this.miembros = miembros;
	}
	
	// Constructor para crear un grupo con nombre, inicializando la lista de miembros.
	public Grupo(String nombre) {
		super(nombre);
		miembros = new LinkedList<Contacto>();
	}
	
	//getters y setters para los atributos del grupo.
	public String getImagen() {
		return imagen;
	}
	
	public List<Contacto> getMiembros() {
		return miembros;
	}
	
	public void setMiembros(List<Contacto> miembros) {
		this.miembros = miembros;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
