package umu.tds.apps.Controlador;

import java.util.Arrays;
import java.util.List;

import umu.tds.apps.AppChat.GeneradorPDF;
import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.AppChat.RepositorioUsuarios;
import umu.tds.apps.AppChat.Usuario;

public enum Controlador {
	
	INSTANCE;
	
	private static Controlador controlador;
	private RepositorioUsuarios repositorioUsuarios;
	private Usuario usuarioActual;
	private GeneradorPDF servicioPDF;
	
	
	private Controlador() {
		repositorioUsuarios = RepositorioUsuarios.getInstancia();
	}
	
	/* Aplicamos el patrón Singleton.
	 * Consiguiendo de esta forma que exista una única instancia de la clase Controlador,
	 * que es accesible globalmente.
	 */
	/*
	public static Controlador getInstancia() {
		if (controlador == null)
			controlador = new Controlador();
		return controlador;
	}	
	*/
	public boolean login(String usuario, String contraseña) {
		//usuarioActual = repositorioUsuarios.getUsuario(usuario, contraseña);
		//return usuarioActual != null;
		return true;
	}
	
	//metodo devolverListaMensajesRecientesPorUsuario
	public static List<Mensaje> devolverListaMensajesRecientesPorUsuario(String usuario) {
		Mensaje[] values = new Mensaje[] {
				new Mensaje("ana"), 
				new Mensaje("manuel"), 
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio")};
		
		
		return Arrays.asList(values);
	}

	public List<Mensaje> obtenerChat(String receptor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
