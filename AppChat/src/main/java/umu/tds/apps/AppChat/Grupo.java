package umu.tds.apps.AppChat;

import java.util.*;

public class Grupo extends Contacto{
	private String imagen;
	private List<Contacto> miembros; //mirar a ver si se usa ContactoIndividual o Contacto
	
	public Grupo(String nombre, String imagen) {
		super(nombre);
		miembros = new LinkedList<Contacto>();
		
	}
	
	public Grupo(String nombre, List<Contacto> miembros, String imagen) {
		super(nombre);
		this.imagen = imagen;
		this.miembros = miembros;
	}
	
	public Grupo(String nombre) {
		super(nombre);
		miembros = new LinkedList<Contacto>();
	}
	
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
