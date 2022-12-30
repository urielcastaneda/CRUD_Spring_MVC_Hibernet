package ucs.catedra.dao;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ucs.catedra.model.*;
import ucs.catedra.util.*;

@Repository
public class ContactoDAOImpl implements ContactoDAO {

	
	private static final Logger logger = LoggerFactory.getLogger(ContactoDAOImpl.class);

	@Autowired  
	// ---- articula métodos con HibernateTemplate del frameWork Hibernate.
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	protected Session getSession(){
		  return sessionFactory.getCurrentSession();
	}   	
    //   ---final---------------  articulación con HibernateTemplate
	
	// ---- Obtiene el listado de TODOS LOS CAMPOS de la tabla
//	@SuppressWarnings("unchecked")
	public List<Contacto> listContactos() {
//		Session session = this.sessionFactory.getCurrentSession();
//		List<Contacto> listContactos = session.createQuery("from Contacto").list();

		List<Contacto> listContactos = getSession().createQuery("from Contacto").list();
		for(Contacto contact : listContactos){
			logger.info("Listado de contacto::" + contact);
		}
		return listContactos;
	}
	
	
	
	
	public void addContacto(Contacto contact) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.persist(contact);
		getSession().persist(contact);
		logger.info("Contacto guardado Satisfactoriamente, Detalles de Contacto =" + contact);
	}
	
	public void updateContacto(Contacto contact) {
//		Session session = this.sessionFactory.getCurrentSession();
//		session.update(contact);
		getSession().update(contact);		
		logger.info("Contacto actualizado correctamente, Detalles de Contacto=" + contact);
	}

	
	public Contacto getContactoById(int id) {
//		Session session = this.sessionFactory.getCurrentSession();		
//		Contacto contact = (Contacto) session.load(Contacto.class, new Integer(id));

		Contacto contact = (Contacto) getSession().load(Contacto.class, new Integer(id));
		logger.info("Contactos leidos satisfactoriamente, informaci�n de Contactos=" + contact);
		return contact;
	}

	
	public void removeContacto(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Contacto contact = (Contacto) session.load(Contacto.class, new Integer(id));
		if(null != contact){
			session.delete(contact);
		}
		logger.info("Contacto borrado satisfactoriamente, informaci�n del contacto = " + contact);
	}

	
}