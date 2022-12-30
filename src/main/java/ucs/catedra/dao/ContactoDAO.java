package ucs.catedra.dao;

import java.util.List;

import ucs.catedra.model.Contacto;

public interface ContactoDAO {

	public void addContacto(Contacto contact);
	public void updateContacto(Contacto contact);
	public List<Contacto> listContactos();
	public Contacto getContactoById(int id);
	public void removeContacto(int id);
}