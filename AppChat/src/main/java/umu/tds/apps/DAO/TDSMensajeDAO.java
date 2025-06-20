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
	
	private static final String MENSAJE = "Mensaje";
	private static final String TEXTO = "Texto";
	private static final String EMISOR = "Emisor";
	private static final String RECEPTOR = "Receptor";
	private static final String FECHA = "Fecha";
	private static final String EMOJI = "Emoticono";
	

	public static TDSMensajeDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			unicaInstancia = new TDSMensajeDAO();
		}
		return unicaInstancia;
	}

	private TDSMensajeDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		try {
			factoriaDAO = FactoriaDAO.getInstancia();
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuarioDAO = factoriaDAO.getUsuarioDAO();

	}

	@Override
	public void registrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		Entidad entidad=this.mensajeToEntidad(mensaje);
		entidad=servPersistencia.registrarEntidad(entidad);
		mensaje.setId(entidad.getId());
	}

	@Override
	public void borrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		Entidad entidad=servPersistencia.recuperarEntidad(mensaje.getId());
		servPersistencia.borrarEntidad(entidad);
	}

	@Override
	public Mensaje recuperarMensaje(int id) {
		// TODO Auto-generated method stub
		if(PoolDAO.getInstancia().containsObject(id)) {
			return (Mensaje) PoolDAO.getInstancia().getObject(id);
		}
		Entidad entidad = servPersistencia.recuperarEntidad(id);
		return entidadToMensaje(entidad);
	}

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
