package umu.tds.apps.DAO;

import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.AppChat.Usuario;

public class TDSUsuarioDAO implements UsuarioDAO {  //falta meter los implements y las funcione
	
	
	private ServicioPersistencia servPersistencia;
	private static TDSUsuarioDAO unicaInstancia = null;
	private ContactoIndividualDAO contactoIndividualDAO;
	private GrupoDAO grupoDAO;
	private MensajeDAO mensajeDAO;

	public static TDSUsuarioDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			unicaInstancia = new TDSUsuarioDAO();
		}
		return unicaInstancia;
	}

	private TDSUsuarioDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
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

	
	
	@Override
	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario recuperarUsuario(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
