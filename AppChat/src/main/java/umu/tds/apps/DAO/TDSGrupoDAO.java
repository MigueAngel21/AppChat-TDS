package umu.tds.apps.DAO;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.AppChat.Contacto;
import umu.tds.apps.AppChat.Grupo;
import java.util.*;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;

public class TDSGrupoDAO implements GrupoDAO {
    
    private ServicioPersistencia servPersistencia;
    private static TDSGrupoDAO unicaInstancia = null;
    private ContactoIndividualDAO contactoIndividualDAO;
    
    // Constantes para nombres de propiedades
	private static final String NOMBRE = "Nombre";
	private static final String CONTACTO = "Grupo";
	private static final String MIEMBROS = "Miembros";
	private static final String IMAGEN = "Imagen";
    
    
    //Obtiene la única instancia del DAO (patrón Singleton).
	public static TDSGrupoDAO getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			unicaInstancia = new TDSGrupoDAO();
		}
		return unicaInstancia;
	}
	
	//Constructor privado para el patrón Singleton.
	private TDSGrupoDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	
	//Registra un nuevo grupo en la persistencia.
	@Override
	public void registrarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		Entidad entidad = this.grupoToEntidad(grupo);
		entidad=servPersistencia.registrarEntidad(entidad);
		grupo.setIdContacto(Integer.valueOf("2" + entidad.getId()));
	}

	//Elimina un grupo de la persistencia.
	@Override
	public void borrarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		int codigo=grupo.getIdContacto();
		String id=String.valueOf(codigo).substring(1);
		int idGrupo=Integer.valueOf(id);
		Entidad entidad = servPersistencia.recuperarEntidad(idGrupo);
		servPersistencia.borrarEntidad(entidad);
	}

	//Recupera un grupo de la persistencia por su ID.
	@Override
	public Grupo recuperarGrupo(int id) {
		// TODO Auto-generated method stub
		if (PoolDAO.getInstancia().containsObject(id)) {
			return (Grupo) PoolDAO.getInstancia().getObject(id);
		}
		String idGrupo=String.valueOf(id);
		int codigo=Integer.parseInt(idGrupo.substring(1));
		Entidad entidad= servPersistencia.recuperarEntidad(codigo);
		if (entidad == null) {
			System.out.println("Entidad no encontrada para el código: " + id);
			return null;
		}
		return entidadToGrupo(entidad);
	}

	//Modifica un grupo existente en la persistencia.
	@Override
	public void modificarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		System.out.println(grupo.getNombre()+" Código: "+grupo.getIdContacto());
		Entidad entidad=servPersistencia.recuperarEntidad(Integer.parseInt(String.valueOf(grupo.getIdContacto()).substring(1)));
		for(Propiedad p: entidad.getPropiedades()) {
			if(p.getNombre().equals(MIEMBROS)) {
				p.setValor(obtenerCodigoContacto(grupo.getMiembros()));
			}
			else if (p.getNombre().equals(NOMBRE)) {
				p.setValor(grupo.getNombre());
			} else if (p.getNombre().equals(IMAGEN)) {
				p.setValor(grupo.getImagen());
			}
		}
	}
    
	//Convierte una lista de contactos a una cadena de IDs separados por espacios.
    private String obtenerCodigoContacto(List<Contacto> contactos) {
    	if (contactos==null || contactos.isEmpty()) {
    		return "";
    	}
    	return contactos.stream()
    			.map(Contacto::getIdContacto).map(c->c.toString()).collect(Collectors.joining(" "));
    }
    
    //Obtiene el DAO para ContactoIndividual (lazy initialization).
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
    
    
    //Convierte una cadena de IDs de contactos a una lista de objetos Contacto.
    private List<Contacto> obtenerContactosCodigo(String codigos){
    	List<Contacto> contactos = new LinkedList<Contacto>();
    	StringTokenizer str=new StringTokenizer(codigos, " ");
    	while(str.hasMoreTokens()) {
    		String id=(String) str.nextElement();
    		contactos.add(getContactoIndividualDAO().recuperarContactoIndividual(Integer.valueOf(id)));
    	}
    	return contactos;
    }
    
    //Convierte un objeto Grupo a una Entidad para persistencia.
    private Entidad grupoToEntidad(Grupo g) {
    	List<Contacto> contactos = g.getMiembros();
    	String codigos = contactos.stream()
    					.map(c->c.getIdContacto()).map(s->s.toString())
    					.collect(Collectors.joining(" "));
    	Entidad entidad = new Entidad();
    	entidad.setNombre("CONTACTO");
    	entidad.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, g.getNombre()), new Propiedad(CONTACTO, codigos), new Propiedad(IMAGEN, g.getImagen()))));
    	return entidad;
    }
    
    //Convierte una Entidad de persistencia a un objeto Grupo.
    private Grupo entidadToGrupo(Entidad entidad) {
    	String nombre = servPersistencia.recuperarPropiedadEntidad(entidad, NOMBRE);
    	Grupo grupo = new Grupo(nombre);
    	grupo.setIdContacto(Integer.valueOf("2" + entidad.getId()));
    	PoolDAO.getInstancia().addObject(grupo.getIdContacto(), grupo);
    	
    	String codigos=servPersistencia.recuperarPropiedadEntidad(entidad, CONTACTO);
    	if(!codigos.equals("") && codigos != null) {
    		List<Contacto> contactos = obtenerContactosCodigo(codigos);
    		grupo.setMiembros(contactos);
    	}
    	String imagen = servPersistencia.recuperarPropiedadEntidad(entidad, IMAGEN);
		if (imagen != null) {
			grupo.setImagen(imagen);
	    }
		return grupo;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
	

