package umu.tds.apps.Controlador;

import umu.tds.apps.AppChat.GeneradorPDF;
import umu.tds.apps.AppChat.RepositorioUsuarios;
import umu.tds.apps.AppChat.Usuario;

public class Controlador {
	private static Controlador controlador;
	private RepositorioUsuarios repositorioUsuarios;
	private Usuario usuarioActual;
	private GeneradorPDF servicioPDF;
	
	private Controlador() {
		repositorioUsuarios = RepositorioUsuarios.getInstancia();
	}
	
	public static Controlador getInstancia() {
		if (controlador == null)
			controlador = new Controlador();
		return controlador;
	}	
	
	
	
}
