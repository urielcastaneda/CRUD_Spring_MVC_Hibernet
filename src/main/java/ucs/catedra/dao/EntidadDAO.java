package ucs.catedra.dao;

import java.util.List;

import ucs.catedra.model.Contacto;
import ucs.catedra.model.IHasIntID;

public interface EntidadDAO {


	public <T> List<T> selectGenericFromCriteria(Class<T> t, String criteria);
	public void insert(Object o);
//	public void update(Object o);	
	public <T extends IHasIntID> void update(Class<T> t, int id, Object objActualizado);

	public <T extends IHasIntID> void remove(Class<T> t, int ID);	
	public <T> T selectFirstGenericFromCriteria(Class<T> t, String criteria);	
	public <T extends IHasIntID> T selectGenericByID(Class<T> t, int ID);
	public <T extends IHasIntID> void updateGenericByID(Class<T> t, int ID, String propertyName, Object value);
	public void executeVoidHQLQuery(String query);	
	
}