package umu.tds.apps.DAO;

import java.util.List;

import umu.tds.apps.AppChat.Mensaje;

public interface MensajeDAO {

	public void registrarMensaje(Mensaje mensaje);
	public void borrarMensaje(Mensaje mensaje);
	public Mensaje recuperarMensaje(int id);
	public List<Mensaje> recuperarTodosMensajes();
}
