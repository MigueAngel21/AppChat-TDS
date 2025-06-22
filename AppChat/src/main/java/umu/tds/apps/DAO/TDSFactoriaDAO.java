package umu.tds.apps.DAO;



public class TDSFactoriaDAO extends FactoriaDAO {

	//constructor por defecto
	public TDSFactoriaDAO() {
	}
	
	//Obtiene el DAO para la entidad Usuario
	@Override
	public UsuarioDAO getUsuarioDAO() {
		return TDSUsuarioDAO.getUnicaInstancia();
	}
	
	//Obtiene el DAO para la entidad Grupo
	@Override
	public GrupoDAO getGrupoDAO() {
		return TDSGrupoDAO.getUnicaInstancia();
	}
	
	//Obtiene el DAO para la entidad Mensaje
	@Override
	public MensajeDAO getMensajeDAO() {
		return TDSMensajeDAO.getUnicaInstancia();
	}
	
	//Obtiene el DAO para la entidad ContactoIndividual
	@Override
	public ContactoIndividualDAO getContactoIndividualDAO() {
		return TDSContactoIndividualDAO.getUnicaInstancia();
	}
	
		
	
	
}
