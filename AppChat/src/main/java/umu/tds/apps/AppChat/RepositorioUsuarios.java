package umu.tds.apps.AppChat;

import java.util.*;

import umu.tds.apps.DAO.DAOException;
import umu.tds.apps.DAO.FactoriaDAO;

public enum RepositorioUsuarios {
	INSTANCE;
	
	private FactoriaDAO factoria;

	private HashMap<Integer, Usuario> usuariosPorCodigo;
	private HashMap<String, Usuario> usuariosPorTlf;
	private HashMap<String, Usuario> usuariosPorNombre;

	
	private RepositorioUsuarios() {
		usuariosPorCodigo = new HashMap<Integer, Usuario>();
		usuariosPorTlf = new HashMap<String, Usuario>();
		usuariosPorNombre = new HashMap<String, Usuario>();

		try {
			factoria = FactoriaDAO.getInstancia();

			List<Usuario> listausuarios = factoria.getUsuarioDAO().recuperarTodosUsuarios();
			for (Usuario usuario : listausuarios) {
				usuariosPorCodigo.put(usuario.getId(), usuario);
				usuariosPorTlf.put(usuario.getTelefono(), usuario);
				usuariosPorNombre.put(usuario.getUsuario(), usuario);
			}
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}


	public List<Usuario> findUsuarios() throws DAOException {
		return new LinkedList<Usuario>(usuariosPorTlf.values());
	}
	
	
	public Usuario findUsuario(String telefono) {
		return usuariosPorTlf.get(telefono);
	}

	public Usuario findUsuario(int id) {
		return usuariosPorCodigo.get(id);
	}
	
	
	public Usuario findUsuarioNombre(String nombre) {
		return usuariosPorNombre.get(nombre);
	}
	
	
	public void addUsuario(Usuario usuario) {
		usuariosPorCodigo.put(usuario.getId(), usuario);
		usuariosPorTlf.put(usuario.getTelefono(), usuario);
		System.out.println("Usuario registrado con código: " + usuario.getId() + " y tlf: " + usuario.getTelefono()
				+ " en la base de datos");
	}
	
	public void removeUsuario(Usuario usuario) {
		usuariosPorCodigo.remove(usuario.getId());
		usuariosPorTlf.remove(usuario.getTelefono());
		System.out.println("Usuario con código: " + usuario.getId() + " eliminado");
	}
	
	
	
}
