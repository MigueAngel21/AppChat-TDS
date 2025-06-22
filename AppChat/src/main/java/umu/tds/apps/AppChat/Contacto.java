package umu.tds.apps.AppChat;

public abstract class Contacto {
	private String nombre;
	private int idContacto;
	
	// Constructor por defecto
	public Contacto() {	}
	
	// Constructor con nombre
	public Contacto(String nombre) {
		this.nombre = nombre;
		
	}
	
	// getters y setters
	public String getNombre() {
		return nombre;
	}
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    
    public int getIdContacto() {
        return idContacto;
        	    
    }
    
	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}
	
	
	public abstract String getImagen();
}
