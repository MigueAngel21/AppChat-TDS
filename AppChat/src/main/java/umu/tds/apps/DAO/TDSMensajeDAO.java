package umu.tds.apps.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.AppChat.Usuario;
import beans.Entidad;
import beans.Propiedad;



public class TDSMensajeDAO implements MensajeDAO { // falta meter los implements y las funciones
	
	
	private ServicioPersistencia servPersistencia;
	private static TDSMensajeDAO unicaInstancia = null;
	private FactoriaDAO factoriaDAO;
	private UsuarioDAO usuarioDAO;
	
	// Constantes para nombres de propiedades
	private static final String MENSAJE = "Mensaje";
	private static final String TEXTO = "Texto";
	private static final String EMISOR = "Emisor";
	private static final String RECEPTOR = "Receptor";
	private static final String FECHA = "Fecha";
	private static final String EMOJI = "Emoticono";
	
    //Obtiene la única instancia del DAO (patrón Singleton).
	public static TDSMensajeDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			unicaInstancia = new TDSMensajeDAO();
		}
		return unicaInstancia;
	}

	//Constructor privado para el patrón Singleton.
	private TDSMensajeDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		try {
			factoriaDAO = FactoriaDAO.getInstancia();
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuarioDAO = factoriaDAO.getUsuarioDAO();

	}

	
	//Registra un nuevo mensaje en la persistencia.
	@Override
	public void registrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		Entidad entidad=this.mensajeToEntidad(mensaje);
		entidad=servPersistencia.registrarEntidad(entidad);
		mensaje.setId(entidad.getId());
	}

	//Elimina un mensaje de la persistencia.
	@Override
	public void borrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		Entidad entidad=servPersistencia.recuperarEntidad(mensaje.getId());
		servPersistencia.borrarEntidad(entidad);
	}

	//Recupera un mensaje desde la persistencia.
	@Override
	public Mensaje recuperarMensaje(int id) {
		// TODO Auto-generated method stub
		if(PoolDAO.getInstancia().containsObject(id)) {
			return (Mensaje) PoolDAO.getInstancia().getObject(id);
		}
		Entidad entidad = servPersistencia.recuperarEntidad(id);
		return entidadToMensaje(entidad);
	}

	//Recupera todos los mensajes almacenados en la persistencia.
	@Override
	public List<Mensaje> recuperarTodosMensajes() {
		// TODO Auto-generated method stub
		List<Entidad> entidades=servPersistencia.recuperarEntidades(MENSAJE);
		List<Mensaje> mensajes=new LinkedList<Mensaje>();
		for(Entidad e: entidades) {
			mensajes.add(recuperarMensaje(e.getId()));
		}
		return mensajes;
	}
	
	//Convierte un objeto Mensaje a una Entidad para persistencia.
	private Entidad mensajeToEntidad(Mensaje mensaje) {
		Entidad entidad = new Entidad();
		entidad.setNombre(MENSAJE);
		entidad.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(TEXTO, mensaje.getTexto()),
				new Propiedad(EMISOR, String.valueOf(mensaje.getEmisor().getId())),
				new Propiedad(RECEPTOR, String.valueOf(mensaje.getReceptor().getId())),
				new Propiedad(FECHA, mensaje.getFecha().toString()),
				new Propiedad(EMOJI, String.valueOf(mensaje.getEmoticono())))));
		return entidad;
	}
	
	//Convierte una Entidad recuperada de la persistencia a un objeto Mensaje.
	private Mensaje entidadToMensaje(Entidad eMensaje) {

		String texto = servPersistencia.recuperarPropiedadEntidad(eMensaje, TEXTO);
		String fecha = servPersistencia.recuperarPropiedadEntidad(eMensaje, FECHA);
		String emoji = servPersistencia.recuperarPropiedadEntidad(eMensaje, EMOJI);

		Mensaje mensaje;
		if (Integer.valueOf(emoji) == -1)
			mensaje = new Mensaje(texto, null, null, LocalDateTime.parse(fecha));
		else
			mensaje = new Mensaje(Integer.valueOf(emoji), null, null, LocalDateTime.parse(fecha));
		PoolDAO.getInstancia().addObject(mensaje.getId(), mensaje);
		mensaje.setId(eMensaje.getId());

		String emisor = servPersistencia.recuperarPropiedadEntidad(eMensaje, EMISOR);
		if (emisor == null) {
			System.err.println("No se encontró el ID de usuarioE en el contacto");
		}
		String receptor = servPersistencia.recuperarPropiedadEntidad(eMensaje, RECEPTOR);
		if (receptor == null) {
			System.err.println("No se encontró el ID de usuarioR en el contacto");
		}
		Usuario usuarioEmisor = usuarioDAO.recuperarUsuario(Integer.valueOf(emisor));
		Usuario usuarioReceptor = usuarioDAO.recuperarUsuario(Integer.valueOf(receptor));

		mensaje.setEmisor(usuarioEmisor);
		mensaje.setReceptor(usuarioReceptor);

		return mensaje;
	}
	
	
	
	
	
	
	
	
	
	
	
}	
