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

	
	/*
	 * Constructor privado para inicializar el repositorio de usuarios. Carga los
	 * usuarios desde la base de datos y los almacena en las estructuras de datos.
	 */
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

	//Obtiene una lista con todos los usuarios registrados en el sistema
	public List<Usuario> findUsuarios() throws DAOException {
		return new LinkedList<Usuario>(usuariosPorTlf.values());
	}
	
	// Busca un usuario por su número de teléfono
	public Usuario findUsuario(String telefono) {
		return usuariosPorTlf.get(telefono);
	}

	// Busca un usuario por su código
	public Usuario findUsuario(int id) {
		return usuariosPorCodigo.get(id);
	}
	
	// Busca un usuario por su nombre de usuario
	public Usuario findUsuarioNombre(String nombre) {
		return usuariosPorNombre.get(nombre);
	}
	
	// Añade un nuevo usuario al repositorio y lo registra en la base de datos
	public void addUsuario(Usuario usuario) {
		usuariosPorCodigo.put(usuario.getId(), usuario);
		usuariosPorTlf.put(usuario.getTelefono(), usuario);
		System.out.println("Usuario registrado con código: " + usuario.getId() + " y tlf: " + usuario.getTelefono()
				+ " en la base de datos");
	}
	
	// Actualiza un usuario existente en el repositorio y en la base de datos
	public void removeUsuario(Usuario usuario) {
		usuariosPorCodigo.remove(usuario.getId());
		usuariosPorTlf.remove(usuario.getTelefono());
		System.out.println("Usuario con código: " + usuario.getId() + " eliminado");
	}
	
	
	
}
