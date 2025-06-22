package umu.tds.apps.AppChat;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import umu.tds.apps.Controlador.Controlador;

public class Usuario {
	private int id; //codigo
	private String usuario;
	private String password;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String imagen;
	private String saludo;
	private boolean premium;
	private LocalDate fechaRegistro;
	private double precioSuscripcion;
	
	private List<Contacto> contactos;
	private List<Mensaje> mensajesEnviados;
	private List<Mensaje> mensajesRecibidos;
	
	public final static String IMG = "/umu/tds/apps/resources/imagenPerfil1.png"; //imagen por defecto

	
	// constructor para cuando un usuario tenga algun descuento
	public Usuario(String usuario, String password, String telefono, LocalDate fechaNacimiento, String imagen,
			String saludo, boolean premium, double precioSuscripcion) {
		this.usuario = usuario;
		this.password = password;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen = imagen;
		this.saludo = saludo;
		this.premium = premium;
		this.fechaRegistro = LocalDate.now();
		this.precioSuscripcion = precioSuscripcion;
		this.contactos = new LinkedList<Contacto>();
		this.mensajesEnviados = new LinkedList<Mensaje>();
		this.mensajesRecibidos = new LinkedList<Mensaje>();
		
	}	
	
	//constructor para cuando descuento sea null
	public Usuario(String usuario, String contraseña, String telefono, LocalDate fechaNacimiento, String saludo,
			String imagen) {
		this.usuario = usuario;
		this.password = contraseña;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen = imagen;
		this.saludo = saludo;
		this.mensajesEnviados = new LinkedList<Mensaje>();
		this.mensajesRecibidos = new LinkedList<Mensaje>();

	}
	
	// getters y setters
	public String getUsuario() {
		return usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String contraseña) {
		this.password = contraseña;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getSaludo() {
		return saludo;
	}
	
	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}
	
	public List<Contacto> getContactos() {
		return contactos;
	}
	
	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	public List<Mensaje> getMensajesEnviados() {
		return mensajesEnviados;
	}
	
	public void setMensajesEnviados(List<Mensaje> mensajesEnviados) {
		this.mensajesEnviados = mensajesEnviados;
	}
	
	public List<Mensaje> getMensajesRecibidos() {
		return mensajesRecibidos;
	}
	
	public void setMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
		this.mensajesRecibidos = mensajesRecibidos;
	}
	
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public double getPrecioSuscripcion() {
		return precioSuscripcion;
	}
	
	public void setPrecioSuscripcion(double precioSuscripcion) {
		this.precioSuscripcion = precioSuscripcion;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	// Método para obtener un contacto individual por su nombre
	public Optional<Contacto> getContactoIndividual(String otroUsuario) {
		return contactos.stream().filter(c -> c.getClass().equals(ContactoIndividual.class))
				.filter(c -> c.getNombre().equals(otroUsuario)).findFirst(); 
	}
	
	//Obtiene el historial de mensajes con otro usuario específico
	public List<Mensaje> getChatMensajes (Usuario receptor){
							// Obtenemos los mensajes enviados donde el receptor es el usuario especificado
		return Stream.concat(this.getMensajesEnviados().stream().filter(m -> m.getReceptor().equals(receptor)),
							// Obtenemos los mensajes recibidos donde el emisor es el usuario especificado
						     this.getMensajesRecibidos().stream().filter(m -> m.getEmisor().equals(receptor)))
				// Ordenamos por fecha 
				.sorted(Comparator.comparing(Mensaje::getFecha))
				.collect(Collectors.toList());
	}
	
	// Añade un contacto individual a la lista de contactos del usuario.
	public void añadirContacto(ContactoIndividual contacto) {
		contactos.add(contacto);
	}
	
	//Envía un mensaje a otro usuario.
	public void enviarMensaje(Usuario receptor, Mensaje mensaje) {
		mensajesEnviados.add(mensaje);
		receptor.recibirMensaje(mensaje);
	}
	
	//Método privado para registrar un mensaje recibido.
	private void recibirMensaje(Mensaje mensaje) {
		mensajesRecibidos.add(mensaje);
	}
	
	//Activa la suscripción premium para el usuario.
	public void activarPremium(GestorDescuentos gestorDescuentos, double precioBase) {
		if (!this.premium) {
			this.premium = true;
			this.precioSuscripcion = Controlador.INSTANCE.obtenerPrecioConDescuento();
		}
	}
	
	//Envía un emoji a otro usuario.
	public void enviarEmoji(String id, int emoji) {

		Usuario usuarioReceptor = Optional.ofNullable(getContactoIndividual(id))
				.flatMap(contacto -> Optional.ofNullable(contacto.get())).map(Contacto::getNombre)
				.map(nombre -> RepositorioUsuarios.INSTANCE.findUsuarioNombre(nombre))
				.orElseGet(() -> RepositorioUsuarios.INSTANCE.findUsuario(id));

		if (usuarioReceptor == null) {
			throw new IllegalArgumentException("No se encontró el usuario receptor");
		}

		Mensaje mensaje = new Mensaje(emoji, this, usuarioReceptor);
		mensajesEnviados.add(mensaje);
		usuarioReceptor.recibirMensaje(mensaje);
	}
	
	
	@Override
	public String toString() {
		return "[" + usuario + ", " + telefono + ", " + fechaNacimiento + "]";
	}
	
	//Obtiene el último mensaje intercambiado con un usuario específico.
	public Mensaje getUltimoMensaje(Usuario usuario) {
		List<Mensaje> listaMensajes = getChatMensajes(usuario);
		return listaMensajes.get(listaMensajes.size() - 1);
	}
	
	//Cambia la imagen de perfil del usuario.
	public boolean cambiarImagenPerfil(String url) {
		if (!url.isEmpty()) {
			setImagen(url);
			return true;
		}
		return false;
	}
	
	
	//Obtiene los últimos mensajes de todas las conversaciones.
	public List<Mensaje> obtenerTodosUltimosMensajes() {
		return Stream.concat(mensajesEnviados.stream(), mensajesRecibidos.stream())
				// Creamos una clave única para cada conversación, ordenando los teléfonos
				// para que (A->B) y (B->A) se consideren la misma conversación
				.collect(Collectors.groupingBy(mensaje -> {
					String tel1 = mensaje.getEmisor().getTelefono();
					String tel2 = mensaje.getReceptor().getTelefono();
					// Ordenamos los teléfonos para que siempre tengamos la misma clave
					// independientemente de quién envió el mensaje
					return tel1.compareTo(tel2) < 0 ? tel1 + "_" + tel2 : tel2 + "_" + tel1;
				}))
				// Para cada grupo (conversación), obtenemos el mensaje más reciente
				.values().stream()
				.map(mensajes -> mensajes.stream().max(Comparator.comparing(Mensaje::getFecha)).orElse(null))
				.filter(Objects::nonNull)
				// Ordenamos por fecha descendente
				.sorted(Comparator.comparing(Mensaje::getFecha).reversed()).collect(Collectors.toList());
	}
	
	// Filtra los mensajes según el emisor, receptor y texto del mensaje.
	public List<Mensaje> obtenerMensajesFiltrados(String emisor, String receptor, String mensaje) {
		List<Mensaje> listaMensajes = obtenerTodosMensajes();
		List<Mensaje> mensajesFiltrados = listaMensajes.stream()
				// Filtro de texto de mensaje
				.filter(m -> mensaje.isEmpty() || m.getTexto().contains(mensaje))
				// Filtro de emisor
				//.filter(m -> emisor.isEmpty() || m.getEmisor().getUsuario().contains(emisor))
				.filter(m -> emisor.isEmpty() || m.getReceptor().getTelefono().equals(emisor))
				// Filtro de receptor
				.filter(m -> receptor.isEmpty() || m.getReceptor().getUsuario().contains(receptor))
				.collect(Collectors.toList());
		return mensajesFiltrados;
	}
	
	// Obtiene todos los mensajes enviados y recibidos por el usuario.
	public List<Mensaje> obtenerTodosMensajes() {
		return Stream.concat(mensajesEnviados.stream(), mensajesRecibidos.stream()).collect(Collectors.toList());
	}
	
	// Obtiene los contactos individuales del usuario.
	public List<ContactoIndividual> getListaContactosIndividuales() {
		List<ContactoIndividual> lContactos = contactos.stream().filter(c -> c instanceof ContactoIndividual)
				.map(c -> (ContactoIndividual) c).collect(Collectors.toList());
		return lContactos;
	}
	
	// Verifica si un grupo con el nombre dado ya existe.
	public boolean existeGrupo(String nombreGrupo) {
		return contactos.stream().filter(g -> g instanceof Grupo).map(g -> (Grupo) g).anyMatch(g -> g.getNombre().equals(nombreGrupo));
	}
	
	// Obtiene un grupo por su nombre, si existe.
	public Optional<Grupo> obtenerGrupo(String nombreGrupo) {
		return contactos.stream().filter(g -> g instanceof Grupo).map(g -> (Grupo) g)
				.filter(c -> c.getNombre().equals(nombreGrupo)).findFirst();
	}
	
	
	// Verifica si un contacto con el nombre dado ya existe y devuelve el usuario asociado.
	public Usuario existeContactoNombre(String nombre) {
		List<ContactoIndividual> contactos = getListaContactosIndividuales();
		Optional<ContactoIndividual> res = contactos.stream().filter(c -> c.getNombre().equals(nombre)).findAny();
		if (res.isPresent())
			return res.get().getUsuario();
		else
			return null;
	}
	
	// Verifica si un contacto con el número de teléfono dado ya existe
	public String existeContacto(String telefono) {
		List<ContactoIndividual> contactos = getListaContactosIndividuales();
		Optional<ContactoIndividual> res = contactos.stream().filter(c -> c.getUsuario().getTelefono().equals(telefono))
				.findAny();
		if (res.isPresent())
			return res.get().getNombre();
		else
			return telefono;
	}
	
	//Modifica la lista de miembros de un grupo.
	public boolean modificarGrupo(List<Contacto> listaGrupo, String grupo) {
		Optional<Grupo> contactoGrupo = obtenerGrupo(grupo);
		
		if (contactoGrupo.isPresent()) {
			Grupo g = contactoGrupo.get();
			g.setMiembros(listaGrupo);
			return true;
		} else
			return false;
	}
	
	// Añade un grupo a la lista de contactos del usuario.
	public void addContactoGrupo(Grupo g) {
		contactos.add(g);
	}
	
	// Verifica si un contacto es miembro de un grupo específico.
	public boolean esMiembroGrupo(String contacto, String grupo) {		
		Optional<Grupo> grupoSeleccionado = obtenerGrupo(grupo);
		
		if (grupoSeleccionado.isPresent()) {
			return grupoSeleccionado.get().getMiembros().stream().anyMatch(c -> c.getNombre().equals(contacto));
		} else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
}
