package umu.tds.apps.Controlador;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import umu.tds.apps.AppChat.FactoriaDescuento;
import umu.tds.apps.AppChat.GestorDescuentos;
import umu.tds.apps.AppChat.Mensaje;
import umu.tds.apps.AppChat.RepositorioUsuarios;
import umu.tds.apps.AppChat.Usuario;
import umu.tds.apps.DAO.DAOException;
import umu.tds.apps.DAO.FactoriaDAO;
import umu.tds.apps.DAO.ContactoIndividualDAO;
import umu.tds.apps.DAO.GrupoDAO;
import umu.tds.apps.DAO.MensajeDAO;
import umu.tds.apps.DAO.UsuarioDAO;

public enum Controlador {
	
	INSTANCE;
	
	private RepositorioUsuarios repositorioUsuarios;
	private Usuario usuarioActual;
	private GestorDescuentos gestorDescuentos;
	private FactoriaDAO factoria;
	private ContactoIndividualDAO adaptadorContactoIndividual;
	private GrupoDAO adaptadorGrupo;
	private MensajeDAO adaptadorMensaje;
	private UsuarioDAO adaptadorUsuario;
	
	
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
	
	
	public boolean login(String usuario, String contraseña) {
		//usuarioActual = repositorioUsuarios.getUsuario(usuario, contraseña);
		//return usuarioActual != null;
		return true;
	}
	
	//metodo devolverListaMensajesRecientesPorUsuario
	public static List<Mensaje> devolverListaMensajesRecientesPorUsuario(String usuario) {
		Mensaje[] values = new Mensaje[] {
				new Mensaje("ana"), 
				new Mensaje("manuel"), 
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio"),
				new Mensaje("antonio")};
		
		
		return Arrays.asList(values);
	}

	public List<Mensaje> obtenerChat(String receptor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double obtenerPrecioConDescuento() {
		return gestorDescuentos.calcularMejorDescuento(usuarioActual);
	}
	
	
	private void inicializarDescuentos() {
		gestorDescuentos.agregarDescuento(
				FactoriaDescuento.crearDescuentoFecha(15.0, LocalDate.now().minusYears(2), LocalDate.now().minusDays(1)));

		gestorDescuentos.agregarDescuento(FactoriaDescuento.crearDescuentoMensaje(20.0, 5));
	}

	private void inicializarRepositorio() {
		repositorioUsuarios = RepositorioUsuarios.INSTANCE;
	}

	private void inicializarAdaptadores() {
		adaptadorUsuario = factoria.getUsuarioDAO(); 
		adaptadorContactoIndividual = factoria.getContactoIndividualDAO();
		adaptadorGrupo = factoria.getGrupoDAO();
		adaptadorMensaje = factoria.getMensajeDAO();
	}
	
}
