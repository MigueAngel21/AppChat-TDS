package umu.tds.apps.AppChat;

import java.util.*;

public class RepositorioUsuarios {
	private static RepositorioUsuarios repositorioUsuarios;
	private List<Usuario> usuarios;

	private RepositorioUsuarios() {
		usuarios = new LinkedList<Usuario>();
	}

	public static RepositorioUsuarios getInstancia() {
		if (repositorioUsuarios == null)
			repositorioUsuarios = new RepositorioUsuarios();
		return repositorioUsuarios;
	}

	public void registrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public void eliminarUsuario(Usuario usuario) {
		usuarios.remove(usuario);
	}

	public Usuario getUsuario(String nombre) {
		for (Usuario usuario : usuarios) {
			if (usuario.getUsuario().equals(nombre))
				return usuario;
		}
		return null;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}
