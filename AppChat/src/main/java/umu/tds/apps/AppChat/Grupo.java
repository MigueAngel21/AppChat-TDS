package umu.tds.apps.AppChat;

import java.util.*;

public class Grupo extends Contacto{
	private String imagen;
	private List<ContactoIndividual> miembros;
	
	public Grupo(String nombre, String imagen) {
		super(nombre);
		this.imagen = imagen;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public List<ContactoIndividual> getMiembros() {
		return miembros;
	}
}
