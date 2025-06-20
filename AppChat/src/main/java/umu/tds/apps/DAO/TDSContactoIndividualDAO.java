package umu.tds.apps.DAO;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.apps.AppChat.ContactoIndividual;
import umu.tds.apps.AppChat.Usuario;

import java.util.ArrayList;
import java.util.Arrays;

import beans.Entidad;
import beans.Propiedad;



public class TDSContactoIndividualDAO implements ContactoIndividualDAO { // falta meter los implements y las funciones
	
	private ServicioPersistencia servPersistencia;
	private static TDSContactoIndividualDAO unicaInstancia = null;
	private UsuarioDAO usuarioDAO;
	private FactoriaDAO factoriaDAO;
	
	private static final String USUARIO = "Usuario";
	private static final String NOMBRE = "Nombre";
	private static final String CONTACTO = "ContactoIndividual";

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
		Entidad contacto=this.contactoToEntidad(contactoIndividual);
		contacto=servPersistencia.registrarEntidad(contacto);
		contactoIndividual.setIdContacto(Integer.valueOf("1"+contacto.getId()));
	}

	@Override
	public void borrarContactoIndividual(ContactoIndividual contactoIndividual) {
		// TODO Auto-generated method stub
		int codigoEntero=contactoIndividual.getIdContacto();
		String cadenaCodigo=String.valueOf(codigoEntero).substring(1);
		int codigo=Integer.valueOf(cadenaCodigo);
		Entidad contacto=servPersistencia.recuperarEntidad(codigo);
		servPersistencia.borrarEntidad(contacto);
	}

	@Override
	public ContactoIndividual recuperarContactoIndividual(int id) {
		// TODO Auto-generated method stub
		if(PoolDAO.getInstancia().containsObject(id)) {
			return (ContactoIndividual) PoolDAO.getInstancia().getObject(id);
		}
		String cadenaCodigo=String.valueOf(id);
		int codigo=Integer.parseInt(cadenaCodigo.substring(1));
		Entidad contacto=servPersistencia.recuperarEntidad(codigo);
		return entidadToContacto(contacto);
	}
	
	private ContactoIndividual entidadToContacto(Entidad entidad) {
		ContactoIndividual contacto;
		String usuarioId=null;
		for(Propiedad p: entidad.getPropiedades()) {
			if(p.getNombre().equals(USUARIO)){
				usuarioId = p.getValor();
				break;
			}
		}
		if(usuarioId==null) {
			throw new IllegalStateException("Id de usuario no encontrada en el contacto");
		}
		String nombre=servPersistencia.recuperarPropiedadEntidad(entidad, NOMBRE);
		Usuario usuario=usuarioDAO.recuperarUsuario(Integer.parseInt(usuarioId));
		contacto = new ContactoIndividual(nombre, usuario);
		contacto.setIdContacto(Integer.valueOf("1"+entidad.getId()));
		PoolDAO.getInstancia().addObject(contacto.getIdContacto(), contacto);
		return contacto;
	}
	
	private Entidad contactoToEntidad(ContactoIndividual contacto) {
		Entidad entidad = new Entidad();
		entidad.setNombre(CONTACTO);
		entidad.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, contacto.getNombre()), new Propiedad(USUARIO, String.valueOf(contacto.getUsuario().getId())))));
		return entidad;
	}
	
}
