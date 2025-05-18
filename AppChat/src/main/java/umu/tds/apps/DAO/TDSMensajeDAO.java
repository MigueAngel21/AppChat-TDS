package umu.tds.apps.DAO;

import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.AppChat.Mensaje;


public class TDSMensajeDAO implements MensajeDAO { // falta meter los implements y las funciones
	
	
	private ServicioPersistencia servPersistencia;
	private static TDSMensajeDAO unicaInstancia = null;
	private FactoriaDAO factoriaDAO;
	private UsuarioDAO usuarioDAO;

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
		
	}

	@Override
	public void borrarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mensaje recuperarMensaje(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mensaje> recuperarTodosMensajes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}	
