package umu.tds.apps.DAO;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.AppChat.Grupo;

public class TDSGrupoDAO implements GrupoDAO { // falta meter los implements y las funciones 
    
    private ServicioPersistencia servPersistencia;
    private static TDSGrupoDAO unicaInstancia = null;
    private ContactoIndividualDAO contactoIndividualDAO;
    
	public static TDSGrupoDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			unicaInstancia = new TDSGrupoDAO();
		}
		return unicaInstancia;
	}
	
	private TDSGrupoDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Grupo recuperarGrupo(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		
	}
    
    
    
}
	

