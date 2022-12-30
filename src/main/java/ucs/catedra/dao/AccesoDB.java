package ucs.catedra.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ucs.catedra.model.*;
import ucs.catedra.util.*;

@Repository
public class AccesoDB implements EntidadDAO {	
	
	private Boolean ReportarLog = true; 
	private static final Logger logger = LoggerFactory.getLogger(AccesoDB.class);
	
	@Autowired
	// ---- articula métodos con HibernateTemplate del frameWork Hibernate.	
	private SessionFactory sessionFactory;

	public void setSession(SessionFactory sf){
		this.sessionFactory = sf;
	}

	protected Session getSession(){
		  return this.sessionFactory.getCurrentSession();		  
	}   	
    //   ---final---------------  articulación con HibernateTemplate
		


	// METODO: --->  selectGenericFromCriteria(Clase, criteioQuery)
	// Retorna el listado de elementos de una tabla, según el criterio de selección
	// Ejemplos de parámetros a utilizar:
	// Criterio: expresión del Query (tablas y condición) que se ejecutaría para obtener el listado
	//           por ejemplo: "from Contacto as alias where alias.nombre like 'uriel'"
	// Usar el NOMBRE DE LA CLASE en vez del nombre de la tabla de la DB.
	// -----------------
	@Transactional
//	@SuppressWarnings("unchecked")	
	public <T> List<T> selectGenericFromCriteria(Class<T> t, String criterioQuery) {
	
		Query<T> query = (Query<T>)(getSession().createQuery(criterioQuery,t));
		List<T> listadoResultante = query.getResultList();
		//---- reporte en Log ---
		if(ReportarLog) for(T elementoEntidad : listadoResultante){
			logger.info("Listado de " + t.getName() + " --> " + elementoEntidad);
		}
		return listadoResultante;
	}

	// METODO: --->  selectFirstGenericFromCriteria(Clase, criteioQuery)
	// Retorna el primer elemento resultante de una selección en una tabla
	// Ejemplos de uso parámetros a utilizar (igual que el anterior) :
	// -----------------
	@Transactional
	public <T> T selectFirstGenericFromCriteria(Class<T> t, String criteioQuery) {

		Query<T> query = (Query<T>)(getSession().createQuery(criteioQuery,t));
		T elementoEntidad = query.getResultList().get(0);
		if(ReportarLog) logger.info("Elemento Encontrado " + t.getName() + " --> " + elementoEntidad);
		return elementoEntidad;
	}
	
	// METODO: --->  Insert(objNuevo)
	// Inserta un elemento en una  tabla.
	// parámetros: 
	//             objNuevo = Objeto que contiene el nombre de la tabla y el registro
	// -----------------	
	@Transactional
	public void insert(Object objNuevo) {
		getSession().save(objNuevo);
		if(ReportarLog) logger.info("Elemento [" + objNuevo.getClass().getName() + 
				                    "] guardado Satisfactoriamente, --> " + objNuevo.toString());
	}

	// METODO: --->  update(objActualizado)
	// Inserta un elemento en una  tabla.
	// parámetros: 
	//             objActualizado = Objeto que contiene el nombre de la tabla y el registro
	// -----------------	
	@Transactional
	public  void update(Object objActualizado) {
		getSession().update(objActualizado);
		if(ReportarLog) logger.info("Elemento [" + objActualizado.getClass().getName() + 
				                    "] Actualizado Correctamente, --> " + objActualizado.toString());
	}
	
	// METODO: --->  remove(clase , ID )
	// Borra un elemento de una tabla,  primero asegura que el elemento exista.
	// parámetros: 
	//             Clase =  nombre de la clase que identifica la tabla
	//             ID    =  identificador del objeto
	// -----------------	
	@Transactional
	public <T extends IHasIntID> void remove(Class<T> t, int id) {
		Session session = this.sessionFactory.getCurrentSession();
		T elementoEntidad  = (T) getSession().load(t, new Integer(id));
		if(null != elementoEntidad){
			session.delete(elementoEntidad);
		}
		logger.info("Contacto borrado satisfactoriamente, información del contacto = " + elementoEntidad);
	}	
	
	// METODO: --->  update(clase , ID )
	// Actualiza un elemento de una tabla,  primero asegura que el elemento exista.
	// parámetros: 
	//             Clase =  nombre de la clase que identifica la tabla
	//             ID    =  identificador del objeto
	// -----------------	
	@Transactional
	public <T extends IHasIntID> void update(Class<T> t, int id, Object objActualizado) {
		Session session = this.sessionFactory.getCurrentSession();
		T objModelo = (T) objActualizado;
		T elementoEntidad  = (T) getSession().load(t, new Integer(id));
		if(null != elementoEntidad){
		
/*
 			//------   por ahora hacemos esta actualización  solo para la clase
			//         contacto..  Debe revisarse para hacelo en forma automática desde el controlador
			//         transfiriendo desde allá el objeto armado correctamente
			elementoEntidad.setId(id);
			elementoEntidad.setNombre(  objModelo.getNombre());
			getPerson.setDescripcion(person.getDescripcion());
*/
			session.update(objModelo);			
			
		}
		logger.info("Contacto borrado satisfactoriamente, información del contacto = " + elementoEntidad);
	}	

	
	
	
	
	// METODO: --->  selectGenericByID(Clase ,  ID)
	// Selecciona un elemento identificándolo por su campo llave (int ID).
	// parámetros: Clase  =  Nombre de la Clase que identifica la entidad que representa la tabla donde buscar
	//             ID =    VALOR del DATO tipo entero, identificador llave de la tabla
	// -----------------		
	@Transactional
	public <T extends IHasIntID> T selectGenericByID(Class<T> t, int ID) {
		
		T elementoEntidad = t.cast(getSession().get(t, ID));
    	//
		if(elementoEntidad != null)       
				logger.info("Eemento [" + elementoEntidad.getClass().getName() + 
						    "] encointrado, --> " + elementoEntidad.toString());
    	//		
    	return elementoEntidad;
	}
		

	// METODO: --->  QueryUpDateDeleteByID(HQLQuery)
	// Actualiza o borra registros en una tabla. Devuelve un valor ENTERO que indica la cantidad de registros intervenidos
	// parámetros: HQLQuery  =  Cadena de texto con la instrucción en formato HQLQuery a ejecutar 
	//                          utiliza executeUpdate()  por lo tanto sóla aplicao para actualización o borrado.
	// -----------------		
	@Transactional
	public void executeVoidHQLQuery(String HQLQuery) {
        int result = getSession().createQuery(HQLQuery).executeUpdate();
		if(ReportarLog) 
			if (result > 0) logger.info("OK   -->HQLQuery [ " + HQLQuery +" ]... Registros actualizados  =  " + result); 
			else logger.info("WARN --> [ " + HQLQuery +" ]... No actualizó registros.");
	}

	
	// METODO: --->  updateGenericByID(Clase,  ID, propertyName, Object value)
	// ****  (este método está  inconcluso) ******
	// el objetivo es Actualizar los datos leidos hacia todos los atributos que conforman el objeto
	// entidad especificado
	// falta incorporar un bucle que recorre e identifica todos los atributos  sin necesidad de informaerle uno por uno
	// -----------------	
	@Transactional
	public <T extends IHasIntID> void updateGenericByID(
		Class<T> t, int ID, String propertyName, Object value){
		Session session = sessionFactory.getCurrentSession();		
    	T result = t.cast(session.get(t, ID));        	
    	//Cambiamos el valor de la variable usando Reflection
    	UtilityMethods.invokeSetter(result, propertyName, value);  
	}	
}
