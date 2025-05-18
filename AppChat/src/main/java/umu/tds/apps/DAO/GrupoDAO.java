package umu.tds.apps.DAO;

import umu.tds.apps.AppChat.Grupo;

public interface GrupoDAO {
	
	
	public void registrarGrupo(Grupo grupo);
	public void borrarGrupo(Grupo grupo);
	public Grupo recuperarGrupo(int id);
	public void modificarGrupo(Grupo grupo);
}
