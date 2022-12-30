package ucs.catedra.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import ucs.catedra.dao.SearchParameters;

// Mediante esta clase se obtienen los REGISTROS VAC�OS (en blanco)  
// de cada uno de los modelos de ENTIDAD que se requieran para la aplicación... 

public class AppConfig {
	
	public static ApplicationContext factory =
    		new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Bean
	@Scope("prototype")
	public Contacto getContacto() {
		return new Contacto();
	}
	
	@Bean
	@Scope("prototype")
	public SearchParameters getSearchParameters() {
		return new SearchParameters();
	}	
}
