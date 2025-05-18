package umu.tds.apps.DAO;

import umu.tds.apps.AppChat.ContactoIndividual;

public interface ContactoIndividualDAO {
	
	public void resistrarContactoIndividual(ContactoIndividual contactoIndividual);
	public void borrarContactoIndividual(ContactoIndividual contactoIndividual);
	public ContactoIndividual recuperarContactoIndividual(int id);
}
