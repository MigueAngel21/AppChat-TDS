package umu.tds.apps.DAO;

import java.util.Hashtable;

public class PoolDAO {

	private static PoolDAO unicaInstancia;
	private Hashtable<Integer, Object> pool;
	
	// Constructor privado para evitar la instanciación externa
	private PoolDAO() {
		pool = new Hashtable<Integer, Object>();
	}
	
	// Método para obtener la instancia única de PoolDAO
	public static PoolDAO getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new PoolDAO();
		}
		return unicaInstancia;
	}
	
	
	// Método para obtener un objeto del pool
	public Object getObject(int id) {
		return pool.get(id);
	} //devuelve null si no existe el objeto
	
	
	// Método para añadir un objeto al pool
	public void addObject(int id, Object obj) {
		pool.put(id, obj);
	}
	
	
	// Método para eliminar un objeto del pool
	public void removeObject(int id) {
		pool.remove(id);
	}
	
	// Método para verificar si un objeto existe en el pool
	public boolean containsObject(int id) {
		return pool.containsKey(id);
	}
}
