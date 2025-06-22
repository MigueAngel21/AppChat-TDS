package umu.tds.apps.Controlador;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import umu.tds.apps.AppChat.FactoriaDescuento;
import umu.tds.apps.AppChat.GestorDescuentos;
import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.AppChat.RepositorioUsuarios;
import umu.tds.apps.AppChat.Usuario;
import umu.tds.apps.AppChat.Grupo;
import umu.tds.apps.AppChat.ContactoIndividual;
import umu.tds.apps.AppChat.Contacto;

import umu.tds.apps.DAO.DAOException;
import umu.tds.apps.DAO.FactoriaDAO;
import umu.tds.apps.DAO.ContactoIndividualDAO;
import umu.tds.apps.DAO.GrupoDAO;
import umu.tds.apps.DAO.MensajeDAO;
import umu.tds.apps.DAO.UsuarioDAO;


public enum Controlador {
	
	INSTANCE;
	//Atributos de clase
	private RepositorioUsuarios repositorioUsuarios;
	private Usuario usuarioActual;
	private GestorDescuentos gestorDescuentos;
	private FactoriaDAO factoria;
	private ContactoIndividualDAO contactoIndividualDAO;
	private GrupoDAO grupoDAO;
	private MensajeDAO mensajeDAO;
	private UsuarioDAO usuarioDAO;
	
	
	// Constructor privado para el patrón Singleton
	private Controlador() {
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		gestorDescuentos = new GestorDescuentos();
		inicializarAdaptadores();
		inicializarRepositorio();
		inicializarDescuentos();
		usuarioActual = null;
	}
	
	
	//Inicia sesión dentro de la aplicación comprobando el usuario y la contraseña
	public boolean login(String telefono, String contraseña) {
		Usuario usuario = repositorioUsuarios.findUsuario(telefono);

		if (usuario != null && usuario.getPassword().equals(contraseña)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	//Consulta si existe un contacto para el usuario actual
	public String existeContacto(String telefono) {
		return usuarioActual.existeContacto(telefono);
	}

    //Obtiene un chat con un contacto específico
	public List<Mensaje> obtenerChat(String receptor) {
		// TODO Auto-generated method stub
		Usuario receptorUsuario = usuarioActual.existeContactoNombre(receptor);
		if (receptorUsuario == null) {
			receptorUsuario= RepositorioUsuarios.INSTANCE.findUsuario(receptor);
		}
		List<Mensaje> chat=this.usuarioActual.getChatMensajes(receptorUsuario);
		
		return chat;
	}
	
	
	//Obtiene una lista de todos los contactos del usuario actual
	public List<Contacto> recuperarTodosContactos(){
		return usuarioActual.getContactos();
	}
	
	
	//Calcula el precio con descuento
	public double obtenerPrecioConDescuento() {
		return gestorDescuentos.calcularMejorDescuento(usuarioActual);
	}
	
	
	//Inicializa los descuentos
	private void inicializarDescuentos() {
		gestorDescuentos.agregarDescuento(
				FactoriaDescuento.crearDescuentoFecha(15.0, LocalDate.now().minusYears(2), LocalDate.now().minusDays(1)));

		gestorDescuentos.agregarDescuento(FactoriaDescuento.crearDescuentoMensaje(20.0, 5));
	}


    //Inicializa el repositorio de usuarios
	private void inicializarRepositorio() {
		repositorioUsuarios = RepositorioUsuarios.INSTANCE;
	}


	//Inicializa los DAO
	private void inicializarAdaptadores() {
		usuarioDAO = factoria.getUsuarioDAO(); 
		contactoIndividualDAO = factoria.getContactoIndividualDAO();
		grupoDAO = factoria.getGrupoDAO();
		mensajeDAO = factoria.getMensajeDAO();
	}
	
	
	//Getter de usuario actual
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}


	//Comprueba si un usuario está registrado en la aplicación
	public boolean esUsuarioRegistrado(String login) {
		return repositorioUsuarios.findUsuario(login) != null;
	}
	
	
	//Registra un nuevo usuario en la aplicación
	public boolean registrarUsuario(String nombre, String password, String telefono, LocalDate fechaNacimiento,
			String saludo, String url) {
		if (esUsuarioRegistrado(telefono)) {
			return false;
		}
		Usuario usuario = new Usuario(nombre, password, telefono, fechaNacimiento, saludo, url, false, 0);

		usuarioDAO.registrarUsuario(usuario);
		repositorioUsuarios.addUsuario(usuario);
		return true;
	}
	
	
	//Borra un usuario de la aplicación
	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getTelefono()))
			return false;

		usuarioDAO.borrarUsuario(usuario);
		repositorioUsuarios.removeUsuario(usuario);
		return true;
	}
	
	
	//Cambia la imagen del perfil del usuario actual
	public boolean cambiarImagenUsuario(String url) {
		boolean res = this.usuarioActual.cambiarImagenPerfil(url);
		if (res)
			usuarioDAO.modificarUsuario(usuarioActual);
		return res;
	}
	
	
	//Comprueba si un contacto pertenece a un grupo
	public boolean esMiembroGrupo(String contacto, String grupo) {
		return usuarioActual.esMiembroGrupo(contacto, grupo);
	}
	
	
	//Obtiene el último mensaje intercambiado con otro usuario
	public Mensaje getUltimoMensaje(Usuario usuario) {
		return usuarioActual.getUltimoMensaje(usuario);
	}
	
	
	//Envía un mensaje de texto
	public void enviarMensaje(String receptor, String texto) {

		Optional<Grupo> grupo = usuarioActual.obtenerGrupo(receptor);

		if (grupo.isPresent()) {
			List<Contacto> contactos = grupo.get().getMiembros();
			for (Contacto contacto : contactos) {
				Usuario usuarioReceptor = usuarioActual.existeContactoNombre(contacto.getNombre());
				Mensaje m = new Mensaje(texto, usuarioActual, usuarioReceptor);
				usuarioActual.enviarMensaje(usuarioReceptor, m);
				mensajeDAO.registrarMensaje(m);
				usuarioDAO.modificarUsuario(usuarioActual);
				usuarioDAO.modificarUsuario(usuarioReceptor);
			}
		} else {

			Usuario usuarioReceptor = usuarioActual.existeContactoNombre(receptor);
			// Si no es contacto
			if (usuarioReceptor == null) {
				usuarioReceptor = RepositorioUsuarios.INSTANCE.findUsuario(receptor);
			}
			// Si no es ni contacto ni telefono
			if (usuarioReceptor == null) {
				throw new IllegalArgumentException("El teléfono no está registrado.");
			}

			if (usuarioReceptor.equals(usuarioActual)) {
				throw new IllegalArgumentException("No te puedes enviar un mensaje a tí mismo");
			}

			Mensaje m = new Mensaje(texto, usuarioActual, usuarioReceptor);
			mensajeDAO.registrarMensaje(m);
			usuarioActual.enviarMensaje(usuarioReceptor, m);
			usuarioDAO.modificarUsuario(usuarioActual);
			usuarioDAO.modificarUsuario(usuarioReceptor);
		}

	}
	
	
	//Envía un mensaje con emoticono
	public void enviarMensaje(String receptor, int emoticono) {
		
		Optional<Grupo> grupo = usuarioActual.obtenerGrupo(receptor);
		
		if (grupo.isPresent()) {
			List<Contacto> contactos = grupo.get().getMiembros();
			for (Contacto contacto : contactos) {
				Usuario usuarioReceptor = usuarioActual.existeContactoNombre(contacto.getNombre());
				Mensaje m = new Mensaje(emoticono, usuarioActual, usuarioReceptor);
				usuarioActual.enviarMensaje(usuarioReceptor, m);
				mensajeDAO.registrarMensaje(m);
				usuarioDAO.modificarUsuario(usuarioActual);
				usuarioDAO.modificarUsuario(usuarioReceptor);
			}
		} else {
			Usuario usuarioReceptor = usuarioActual.existeContactoNombre(receptor);

			// Si no es contacto
			if (usuarioReceptor == null) {
				usuarioReceptor = RepositorioUsuarios.INSTANCE.findUsuario(receptor);
			}
			// Si no es ni contacto ni telefono
			if (usuarioReceptor == null) {
				throw new IllegalArgumentException("El teléfono no está registrado.");
			}

			if (usuarioReceptor.equals(usuarioActual)) {
				throw new IllegalArgumentException("No te puedes enviar un emoji a tí mismo");
			}

			Mensaje m = new Mensaje(emoticono, usuarioActual, usuarioReceptor);
			mensajeDAO.registrarMensaje(m);
			usuarioActual.enviarMensaje(usuarioReceptor, m);
			usuarioDAO.modificarUsuario(usuarioActual);
			usuarioDAO.modificarUsuario(usuarioReceptor);
		}
	}
	
	
	//Añade un nuevo contacto individual
	public int addContactoIndividual(String nombre, String telefono) {
		Usuario usuario = repositorioUsuarios.findUsuario(telefono);

		if (usuario == null){
			return 1;
		}

		if (usuarioActual.existeContacto(telefono) != telefono){
			return 2;
		}

		if (usuarioActual.getTelefono().equals(telefono)){
			return 3;
		}

		if (usuarioActual.existeContactoNombre(nombre) != null){
			return 4;
		}
		
		ContactoIndividual contacto = new ContactoIndividual(nombre, usuario);
		contactoIndividualDAO.resistrarContactoIndividual(contacto);
		usuarioActual.añadirContacto(contacto);
		usuarioDAO.modificarUsuario(usuarioActual);
		return 0;
	}
	
	
	//Activa el modo premium
	public void activarPremium() {
		this.usuarioActual.setPremium(true);
		this.usuarioActual.setPrecioSuscripcion((double) Math.round(obtenerPrecioConDescuento() * 100) / 100);	// Para redondear 
		usuarioDAO.modificarUsuario(usuarioActual);
	}
	
	
	//Desactiva el modo premium
	public void anularPremium() {
		this.usuarioActual.setPremium(false);
		this.usuarioActual.setPrecioSuscripcion(0);
		usuarioDAO.modificarUsuario(usuarioActual);
	}
	
	
	//Comprueba si ya existe un grupo con ese nombre
	public boolean existeGrupo(String nombre) {
		return usuarioActual.obtenerGrupo(nombre)==null; //hacer al revés en la interfaz
	}
	
	//Crea un nuevo grupo
	public void crearGrupo(String nombre, List<Contacto> lista, String imagen) {
		if(imagen.isEmpty()) {
			imagen="/umu/tds/apps/resources/imagenPerfil2.png";
		}
		Grupo g=new Grupo(nombre, lista, imagen);
		grupoDAO.registrarGrupo(g);
		usuarioActual.addContactoGrupo(g);
		usuarioDAO.modificarUsuario(usuarioActual);
	}
	
	
	//Obtiene mensajes que cumplan ciertos filtros
	public List<Mensaje> obtenerMensajesFiltrados(String emisor, String receptor, String mensaje){
		return usuarioActual.obtenerMensajesFiltrados(emisor, receptor, mensaje);
	}
	
	
	//Obtiene todos los mensajes del usuario actual
	public List<Mensaje> obtenerTodosMensajes(){
		return usuarioActual.obtenerTodosMensajes();
	}
	
	
	//Modifica un grupo existente
	public boolean modificarGrupo(List<Contacto> listaContactos, String grupo) {
		boolean resultado=usuarioActual.modificarGrupo(listaContactos, grupo);
		if(resultado) {
			Optional<Grupo> g=usuarioActual.obtenerGrupo(grupo);
			if(g.isPresent()) {
				grupoDAO.modificarGrupo(g.get());
				usuarioDAO.modificarUsuario(usuarioActual);
			}
		}
		return resultado;
	}
	
	
}
