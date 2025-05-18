package umu.tds.apps.DAO;



public class TDSFactoriaDAO extends FactoriaDAO {

	public TDSFactoriaDAO() {
	}
	
	@Override
	public UsuarioDAO getUsuarioDAO() {
		return TDSUsuarioDAO.getUnicaInstancia();
	}
	
	@Override
	public GrupoDAO getGrupoDAO() {
		return TDSGrupoDAO.getUnicaInstancia();
	}
	
	@Override
	public MensajeDAO getMensajeDAO() {
		return TDSMensajeDAO.getUnicaInstancia();
	}
	
	@Override
	public ContactoIndividualDAO getContactoIndividualDAO() {
		return TDSContactoIndividualDAO.getUnicaInstancia();
	}
	
		
	
	
}
