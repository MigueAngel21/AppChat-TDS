package umu.tds.apps.DAO;

import java.util.List;

import umu.tds.apps.AppChat.Usuario;

public interface UsuarioDAO {
	
	public void registrarUsuario(Usuario usuario);
	public void borrarUsuario(Usuario usuario);
	public Usuario recuperarUsuario(int id);
	public List<Usuario> recuperarTodosUsuarios();
	public void modificarUsuario(Usuario usuario);

}
