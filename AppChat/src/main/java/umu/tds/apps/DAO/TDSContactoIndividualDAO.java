package umu.tds.apps.DAO;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.AppChat.ContactoIndividual;

public class TDSContactoIndividualDAO implements ContactoIndividualDAO { // falta meter los implements y las funciones
	
	private ServicioPersistencia servPersistencia;
	private static TDSContactoIndividualDAO unicaInstancia = null;
	private UsuarioDAO usuarioDAO;
	private FactoriaDAO factoriaDAO;

	public static TDSContactoIndividualDAO getUnicaInstancia() { //patron singleton
		if (unicaInstancia == null) {
			unicaInstancia = new TDSContactoIndividualDAO();
		}
		return unicaInstancia;
	}

	private TDSContactoIndividualDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		try {
			factoriaDAO = FactoriaDAO.getInstancia();
		} catch (Exception e) {
			e.printStackTrace();
		}
		usuarioDAO = factoriaDAO.getUsuarioDAO();	
    }

	@Override
	public void resistrarContactoIndividual(ContactoIndividual contactoIndividual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarContactoIndividual(ContactoIndividual contactoIndividual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ContactoIndividual recuperarContactoIndividual(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
