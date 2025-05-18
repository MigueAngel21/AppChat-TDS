package umu.tds.apps.AppChat;

public class Mensaje {
	private String texto;
	private String fecha;
	private String hora;
	private String emoticonono; 
	private Usuario emisor;
	private Usuario receptor;
	
	public Mensaje(String texto, String fecha, String hora, String emoticonono, Usuario emisor, Usuario receptor) {
		this.texto = texto;
		this.fecha = fecha;
		this.hora = hora;
		this.emoticonono = emoticonono;
		this.emisor = emisor;
		this.receptor = receptor;
	}	
	
	public Mensaje(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getHora() {
		return hora;
	}
	
	public String getEmoticonono() {
		return emoticonono;
	}
	
	public Usuario getEmisor() {
		return emisor;
	}
	
	public Usuario getReceptor() {
		return receptor;
	}
	

	
	
}
