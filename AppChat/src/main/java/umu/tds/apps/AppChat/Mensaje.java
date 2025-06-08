package umu.tds.apps.AppChat;

import java.time.LocalDateTime;

public class Mensaje {
	
	private String texto;
	private LocalDateTime fecha;
    //private LocalDateTime hora;
	private int emoticono; 
	private Usuario emisor;
	private Usuario receptor;
	private int id; // Este campo puede ser utilizado para identificar el mensaje si es necesario
	
	//esto quitarlo para luego
	public Mensaje(String texto) {
		this.texto = texto;
		
	}
	public Mensaje(String texto, Usuario emisor, Usuario receptor) {
		this.texto = texto;
		this.fecha = LocalDateTime.now();
		this.receptor = receptor;
		this.emisor = emisor;
		this.emoticono = -1; 
	}
	
	//CONSTRUCTOR PERSISTENCIA
	public Mensaje(String texto, Usuario emisor, Usuario receptor, LocalDateTime fecha) {
		this.texto = texto;
		this.fecha = fecha;
		this.receptor = receptor;
		this.emisor = emisor;
		this.emoticono = -1;
	}
	
	public Mensaje(int emoticono, Usuario emisor, Usuario receptor) {
		this.texto = "emote";
		this.fecha = LocalDateTime.now();
		this.receptor = receptor;
		this.emisor = emisor;
		this.emoticono = emoticono;
	}
	//CONSTRUCTOR PERSISTENCIA
	public Mensaje(int emoticono, Usuario emisor, Usuario receptor, LocalDateTime fecha) {
		this.texto = "emote";
		this.fecha = fecha;
		this.receptor = receptor;
		this.emisor = emisor;
		this.emoticono = emoticono;
	}	
	
	
	public String getTexto() {
		return texto;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public Usuario getEmisor() {
		return emisor;
	}
	
	public Usuario getReceptor() {
		return receptor;
	}
	
	public int getEmoticono() {
		return emoticono;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}
	
	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}
	
	public void setEmoticono(int emoticono) {
		this.emoticono = emoticono;
	}
	
	
	
	
}
