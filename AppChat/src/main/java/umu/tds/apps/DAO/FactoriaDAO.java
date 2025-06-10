package umu.tds.apps.DAO;

public abstract class FactoriaDAO {
	
	private static FactoriaDAO unicaInstancia;
	
	public static final String DAO_TDS= "DAO.TDSFactoriaDAO"; //mirar esto
	
	/**
	 * Crea un tipo de factoria DAO. Solo existe el tipo TDSFactoriaDAO
	 */
	
	public static FactoriaDAO getInstancia(String tipo) throws DAOException {
		if (unicaInstancia == null) {
			try {
				unicaInstancia = (FactoriaDAO) Class.forName(tipo).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		}
		return unicaInstancia;
	}
	
	/**
	 * Constructor privado para evitar instanciacion
	 */
	public static FactoriaDAO getInstancia() throws DAOException {
		if (unicaInstancia == null) {
			return getInstancia(FactoriaDAO.DAO_TDS);
		} else {
            return unicaInstancia;
        }
	}
	
	/**
	 * Constructor 
	 */
	protected FactoriaDAO() {
		
	}
	
	
	//metodos factoria que devuelven objetos DAO
	public abstract UsuarioDAO getUsuarioDAO();
	public abstract GrupoDAO getGrupoDAO();
	public abstract MensajeDAO getMensajeDAO();
	public abstract ContactoIndividualDAO getContactoIndividualDAO();

	
}
