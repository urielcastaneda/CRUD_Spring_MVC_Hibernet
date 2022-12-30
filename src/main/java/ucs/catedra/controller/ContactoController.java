package ucs.catedra.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import ucs.catedra.model.Contacto;
import ucs.catedra.model.AppConfig;
//ucs.catedra.model..service.ContactoService;
import ucs.catedra.dao.EntidadDAO;
import ucs.catedra.dao.SearchParameters;

@Controller
@RequestMapping("/contacto")
public class ContactoController {
	
	@Autowired
	private EntidadDAO dao;

	//Método privado para evitar espacios en blanco	
	private void stringBinder(WebDataBinder binder) {
		StringTrimmerEditor blankTrimmer = 
			new StringTrimmerEditor(true);
		binder.registerCustomEditor(
			String.class, blankTrimmer);
	}	
	

	//------------ Mostrar todos Contactos ------------//
	@RequestMapping("/listadoContactos")
	public String listadoContactos(Model model) {
		//hace el Get a través de la interface DAO (Get from DAO)
		List<Contacto> contactos = 
			dao.selectGenericFromCriteria(Contacto.class, "from Contacto");
		
		//mostramos reporte de seguimiento por consola
		System.out.println(contactos.toString());
		
		//Añade el LISTADO al objeto modelo para transferirlo a la vista
		model.addAttribute("listadoContactos", contactos);		

		return "vistasContacto/vistaListadoContactos";
	}	
	
	
	//------------ Nuevo CONTACTO ------------//
	@RequestMapping("/nuevoContacto")
	public String nuevoContacto(Model model) {
		
		//Testing Spring prototype
		//Se obtiene el registro en Blanco para realizar la captura de datos del nuevo registro
		Contacto contacto =
			AppConfig.factory.getBean(Contacto.class);
		
		// Transferimos el registro en blanco al modelo de datos que se utilizará en la vista
		model.addAttribute("contacto", contacto);
	
		// Retorna la cadena de texto con el nombre de la vista a proyectar
		// este nombre es tomado por el LINSTENER  para luego mostrar en pantalla el formulario correspondiente.
		return "vistasContacto/vistaFormNuevoContacto";
	}
	
	
	@PostMapping("/procesarNuevoContacto")
	//@Valid para especificar que tenemos validación
	public String procesarNuevoContacto(@Valid @ModelAttribute("contacto") Contacto contactoToken, 
			BindingResult resValidacion) {
		//Aunque no usemos el user UserToken, hay que rescatarlo.
		//Aplicamos la validación. BindingResult sin errores
		if(!resValidacion.hasErrors()) {			
			//Guardamos en DB
			dao.insert(contactoToken);
			return "vistasContacto/vistaConfirNuevoContacto";
		}				
		//En caso de errores volver a la página de formulario
		return "vistasContacto/vistaFormNuevoContacto";
	}
	
	
	//------------ Búsquedas ------------//
	@RequestMapping("/consultarPorID")
	public String consultarPorID(Model model) {
		//Testing Spring prototype
		SearchParameters searchParams =
				AppConfig.factory.getBean(SearchParameters.class);
		model.addAttribute("searchParams", searchParams);
		
		return "vistasContacto/vistaConsultaPorID";
	}
	
	@RequestMapping("/consultarPorNombre")
	public String consultarPorNombre(Model model) {
		//Testing Spring prototype
		SearchParameters searchParams =
				AppConfig.factory.getBean(SearchParameters.class);
		model.addAttribute("searchParams", searchParams);
		
		return "vistasContacto/vistaConsultaPorNombre";
	}
	
	@RequestMapping("/verDatosContacto")
	public String viewUserData(
		@ModelAttribute("searchParams") SearchParameters searchParams, Model model) {
		Contacto contacto = null;
		if(searchParams.getId() != -1) {
			contacto = 
				dao.selectGenericByID(
					Contacto.class, searchParams.getId());
		}		
		else if(searchParams.getNombre() != null) {
			contacto = 
				dao.selectFirstGenericFromCriteria(
					Contacto.class, "from Contacto u where u.nombre='"+searchParams.getNombre()+"'");
		}	

		model.addAttribute("contacto", contacto);		
		return "vistasContacto/vistaDatosContacto";
	}	
	
	/*	
	
	//--------------  ACTUALIZACIONES ----------------------
    //   pendiente corregir y generar front ....
	//--------------------------------------	
	
	
//	@Autowired(required=true)
//	@Qualifier(value="contactoService")
//	public void setContactoService(ContactoService cs){
//		this.contactoService = cs;
//	}
	
	//----  Este m�todo controla las dos acciones
	//     cuando se desea ADICIONAR (add)  y cuando es ACTUALIZAR  (update)
	@RequestMapping(value= "/contacto/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("contacto") Contacto contact){
		
		if(contact.getId() == 0){
			//si el identificador es CERO, se considera que es acci�n ADD,
			this.dao.Insert(contact);
		}else{
			// en caso contrario, es porque existe un dato, entoces es UPDATE
			this.dao.updateContacto(contact);
		}
		
		return "redirect:/contactos";
		
	}
	
	@RequestMapping("/borrar/{id}")
    public String remove(@PathVariable("id") int id){
		
        this.dao.removeContacto(id);
        return "redirect:/contactos";
    }
 
    @RequestMapping("/editar/{id}")
    public String editContacto(@PathVariable("id") int id, Model model){
        model.addAttribute("contacto", this.contactoService.getContactoById(id));
        model.addAttribute("listContactos", this.contactoService.listContactos());
        return "contacto";
    }
*/
	
}