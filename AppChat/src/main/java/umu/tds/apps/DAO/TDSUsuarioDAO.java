package umu.tds.apps.DAO;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.AppChat.Usuario;
import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.AppChat.Contacto;
import beans.Entidad;
import beans.Propiedad;



public class TDSUsuarioDAO implements UsuarioDAO {  //falta meter los implements y las funcione
	
	
	private ServicioPersistencia servPersistencia;
	private static TDSUsuarioDAO unicaInstancia = null;
	private ContactoIndividualDAO contactoIndividualDAO;
	private GrupoDAO grupoDAO;
	private MensajeDAO mensajeDAO;
	
	// Constantes para las propiedades de la entidad Usuario
	private static final String USUARIO = "Usuario";
	private static final String NOMBRE = "Usuario";
	private static final String PASSWORD = "Password";
	private static final String TELEFONO = "Telefono";
	private static final String FECHA_NACIMIENTO = "FechaNacimiento";
	private static final String SALUDO = "Saludo";
	private static final String CONTACTOS = "Contactos";
	private static final String ENVIADOS = "MensajesEnviados";
	private static final String RECIBIDOS = "MensajesRecibidos";
	private static final String PREMIUM = "Premium";
	private static final String IMAGEN = "Imagen";
	private static final String PRECIO = "PrecioSuscripcion";
	
	
	// Método para obtener la única instancia del DAO (patrón Singleton)
	public static TDSUsuarioDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			unicaInstancia = new TDSUsuarioDAO();
		}
		return unicaInstancia;
	}

	// Constructor privado para evitar instanciación externa
	private TDSUsuarioDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	// Métodos para obtener la instancia del DAO de contactoIndividual
	public ContactoIndividualDAO getContactoIndividualDAO() {
		if (contactoIndividualDAO == null) {
			try {
				FactoriaDAO factoria = FactoriaDAO.getInstancia();
				contactoIndividualDAO = factoria.getContactoIndividualDAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contactoIndividualDAO;
	}
	
	// Método para obtener la instancia del DAO de grupos
	public GrupoDAO getGrupoDAO() {
		if (grupoDAO == null) {
			try {
				FactoriaDAO factoria = FactoriaDAO.getInstancia();
				grupoDAO = factoria.getGrupoDAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return grupoDAO;
	}
	
	// Método para obtener la instancia del DAO de mensajes
	public MensajeDAO getMensajeDAO() {
		if (mensajeDAO == null) {
			try {
				FactoriaDAO factoria = FactoriaDAO.getInstancia();
				mensajeDAO = factoria.getMensajeDAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mensajeDAO;
	}

	
	//Registra un nuevo usuario en la persistencia.
	@Override
	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
		
	}

	//Elimina un usuario de la persistencia.
	@Override
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		servPersistencia.borrarEntidad(eUsuario);
	}


	//Recupera un usuario desde la persistencia.
	@Override
	public Usuario recuperarUsuario(int id) {
		// TODO Auto-generated method stub
		if (PoolDAO.getInstancia().containsObject(id))
			return (Usuario) PoolDAO.getInstancia().getObject(id);

		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		return entidadToUsuario(eUsuario);
	}

	//Recupera todos los usuarios almacenados en la persistencia.
	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);
		// TODO: Se podría hacer con streams?
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : entidades)
			usuarios.add(recuperarUsuario(eUsuario.getId()));

		return usuarios;
	}


	//Modifica un usuario existente en la persistencia.
	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD))
				prop.setValor(usuario.getPassword());
			else if (prop.getNombre().equals(TELEFONO))
				prop.setValor(usuario.getTelefono());
			else if (prop.getNombre().equals(NOMBRE))
				prop.setValor(usuario.getUsuario());
			else if (prop.getNombre().equals(FECHA_NACIMIENTO))
				prop.setValor(usuario.getFechaNacimiento().toString());
			else if (prop.getNombre().equals(PREMIUM))
				if (usuario.isPremium())
					prop.setValor("SI");
				else
					prop.setValor("NO");
			else if (prop.getNombre().equals(PRECIO))
				prop.setValor(String.valueOf(usuario.getPrecioSuscripcion()));
			else if (prop.getNombre().equals(CONTACTOS))
				prop.setValor(obtenerCodigosContactos(usuario.getContactos()));
			else if (prop.getNombre().equals(IMAGEN))
				prop.setValor(usuario.getImagen());
			else if (prop.getNombre().equals(ENVIADOS))
				prop.setValor(obtenerCodigosListaMensajes(usuario.getMensajesEnviados()));
			else if (prop.getNombre().equals(RECIBIDOS))
				prop.setValor(obtenerCodigosListaMensajes(usuario.getMensajesRecibidos()));

			servPersistencia.modificarPropiedad(prop);
		}
	}
	
	//Convierte una Entidad de persistencia a un objeto Usuario.
	private Usuario entidadToUsuario(Entidad eUsuario){
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String telefono = servPersistencia.recuperarPropiedadEntidad(eUsuario, TELEFONO);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String saludo = servPersistencia.recuperarPropiedadEntidad(eUsuario, SALUDO);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		String imagen = servPersistencia.recuperarPropiedadEntidad(eUsuario, IMAGEN);
		String precio = servPersistencia.recuperarPropiedadEntidad(eUsuario, PRECIO);

		boolean pr = false;
		if (premium.equals("SI")){
			pr = true;
		}
		
		Usuario usuario = new Usuario(nombre, password, telefono, LocalDate.parse(fechaNacimiento), imagen, saludo, pr, Double.valueOf(precio));
		usuario.setId(eUsuario.getId());
		
		PoolDAO.getInstancia().addObject(usuario.getId(), usuario);
		
		String contactosCodigos = servPersistencia.recuperarPropiedadEntidad(eUsuario, CONTACTOS);
		if (!contactosCodigos.equals("") && contactosCodigos != null) {
			List<Contacto> contactos = obtenerContactosCodigos(contactosCodigos);
			usuario.setContactos(contactos);
		}
		
		String enviadosCodigos = servPersistencia.recuperarPropiedadEntidad(eUsuario, ENVIADOS);
		if (!enviadosCodigos.equals("") && enviadosCodigos != null) {
			List<Mensaje> enviados = obtenerEnviadosCodigos(enviadosCodigos);
			usuario.setMensajesEnviados(enviados);
		}
		
		String recibidosCodigos = servPersistencia.recuperarPropiedadEntidad(eUsuario, RECIBIDOS);
		if (!recibidosCodigos.equals("") && recibidosCodigos != null) {
			List<Mensaje> recibidos = obtenerRecibidosCodigos(recibidosCodigos);
			usuario.setMensajesRecibidos(recibidos);
		}
		
		return usuario;
		
	}
	
	//Convierte un objeto Usuario a una Entidad de persistencia.
	private Entidad usuarioToEntidad(Usuario usuario) {
		List<Contacto> listaContactos = usuario.getContactos();
		String ids = listaContactos.stream().map(c -> c.getIdContacto()).map(s -> s.toString())
				.collect(Collectors.joining(" "));
		List<Mensaje> listaMensajes = usuario.getMensajesEnviados();
		String idsMensajes = obtenerCodigosListaMensajes(listaMensajes);
		List<Mensaje> listaMensajesR = usuario.getMensajesRecibidos();
		String idsMensajesR = obtenerCodigosListaMensajes(listaMensajesR);
		String premium = "NO";
		if (usuario.isPremium())
			premium = "SI";
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getUsuario()),
				new Propiedad(PASSWORD, usuario.getPassword()), new Propiedad(TELEFONO, usuario.getTelefono()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento().toString()), new Propiedad(CONTACTOS, ids),
				new Propiedad(ENVIADOS, idsMensajes), new Propiedad(RECIBIDOS, idsMensajesR),
				new Propiedad(IMAGEN, usuario.getImagen()), new Propiedad(PREMIUM, premium), new Propiedad(PRECIO, String.valueOf(usuario.getPrecioSuscripcion())))));
		return eUsuario;
	}
	
	//Convierte una cadena de IDs de mensajes recibidos a una lista de Mensajes.
	private List<Mensaje> obtenerRecibidosCodigos(String recibidosCodigos) {
		List<Mensaje> recibidos = new LinkedList<Mensaje>();
		StringTokenizer strTok = new StringTokenizer(recibidosCodigos, " ");
		while (strTok.hasMoreTokens()) {
			String id = (String) strTok.nextElement();

			recibidos.add(getMensajeDAO().recuperarMensaje(Integer.valueOf(id)));
		}
		return recibidos;
	}
	
	//Convierte una cadena de IDs de contactos a una lista de Contactos.
	private List<Contacto> obtenerContactosCodigos(String contactosCodigos) {
		List<Contacto> contactos = new LinkedList<Contacto>();
		StringTokenizer strTok = new StringTokenizer(contactosCodigos, " ");
		while (strTok.hasMoreTokens()) {
			String id = (String) strTok.nextElement();

			if (id.startsWith("1")) {
				contactos.add(getContactoIndividualDAO().recuperarContactoIndividual(Integer.valueOf(id)));
			} else {
				contactos.add(getGrupoDAO().recuperarGrupo(Integer.valueOf(id)));
			}
		}
		return contactos;
	}
	
	
	//Convierte una cadena de IDs de mensajes enviados a una lista de Mensajes.
	private List<Mensaje> obtenerEnviadosCodigos(String enviadosCodigos) {
		List<Mensaje> enviados = new LinkedList<Mensaje>();
		StringTokenizer strTok = new StringTokenizer(enviadosCodigos, " ");
		while (strTok.hasMoreTokens()) {
			String id = (String) strTok.nextElement();

			enviados.add(getMensajeDAO().recuperarMensaje(Integer.valueOf(id)));
		}
		return enviados;
	}
	
	
	//Convierte una lista de contactos a una cadena de IDs separados por espacios.
	private String obtenerCodigosContactos(List<Contacto> contactos) {

		if (contactos == null || contactos.isEmpty()) {
			return "";
		}

		return contactos.stream().map(Contacto::getIdContacto).map(c -> c.toString()).collect(Collectors.joining(" "));
	}
	
	//Convierte una lista de mensajes a una cadena de IDs separados por espacios.
	private String obtenerCodigosListaMensajes(List<Mensaje> lista) {

		if (lista == null || lista.isEmpty()) {
			return "";
		}

		return lista.stream().map(Mensaje::getId).map(c -> c.toString()).collect(Collectors.joining(" "));
	}
	
	
	
	
	
	
}
