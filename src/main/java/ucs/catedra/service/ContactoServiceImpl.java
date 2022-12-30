package ucs.catedra.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ucs.catedra.dao.EntidadDAO;
import ucs.catedra.model.Contacto;


@Service
@Transactional                             
public class ContactoServiceImpl implements ContactoService {
		
	private EntidadDAO entidadDAO;

	@Autowired
	public void setContactoDAO(EntidadDAO entidadDAO) {
		this.entidadDAO = entidadDAO;
	}


	@Transactional
	public List<Contacto> listContactos() {
//	return this.contactoDAO.listContactos();
	return	entidadDAO.selectGenericFromCriteria(Contacto.class, "from Contacto");
	
		
	}

	/*

	@Transactional
	public void addContacto(Contacto contact) {
		this.contactoDAO.addContacto(contact);
	}

	@Transactional
	public void updateContacto(Contacto contact) {
		this.contactoDAO.updateContacto(contact);
	}
	
	@Transactional
	public Contacto getContactoById(int id) {
		return this.contactoDAO.getContactoById(id);
	}

	@Transactional
	public void removeContacto(int id) {
		this.contactoDAO.removeContacto(id);
	}

	 */
	
}   