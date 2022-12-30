package ucs.catedra.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import ucs.catedra.model.Contacto;
import ucs.catedra.model.IHasIntID;
import ucs.catedra.model.AppConfig;
//ucs.catedra.model..service.ContactoService;
import ucs.catedra.dao.EntidadDAO;
import ucs.catedra.dao.SearchParameters;

@Controller
@RequestMapping("/contacto2")
public class ContactoController2 {
	
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
	// FORM  Tabla consulta general 	
	@RequestMapping("/ReporteContactos")
	public String ReporteContactos(Model model) {
		//hace el Get a través de la interface DAO (Get from DAO)
		List<Contacto> contactos = 
			dao.selectGenericFromCriteria(Contacto.class, "from Contacto");
		
		//mostramos reporte de seguimiento por consola
		System.out.println(contactos.toString());
		
		//Añade el LISTADO al objeto modelo para transferirlo a la vista
		model.addAttribute("listadoContactos", contactos);	
		model.addAttribute("titulo", "Listado de Contactos");		

		return "vistasContacto2/vistaListadoContactos2";
	}	
	
	
	//------------ Nuevo CONTACTO ------------//
	@RequestMapping("/nuevoContacto2")
	public String nuevoContacto(Model model) {
		
		//Testing Spring prototype
		//Se obtiene el registro en Blanco para realizar la captura de datos del nuevo registro
		Contacto contacto =
			AppConfig.factory.getBean(Contacto.class);
		
		// Transferimos el registro en blanco al modelo de datos que se utilizará en la vista
		model.addAttribute("contacto", contacto);
	
		// Retorna la cadena de texto con el nombre de la vista a proyectar
		// este nombre es tomado por el LINSTENER  para luego mostrar en pantalla el formulario correspondiente.
		return "vistasContacto2/vistaNuevoContacto2";
	}

        
	@PostMapping("/procesarNuevoContacto2")
	//@Valid para especificar que tenemos validación
	public String procesarNuevoContacto(@Valid @ModelAttribute("contacto") Contacto contactoToken, 
			BindingResult resValidacion) {
		//Aplicamos la validación. BindingResult sin errores
		if(!resValidacion.hasErrors()) {			
			//Guardamos en DB
			dao.insert(contactoToken);
			return "vistasContacto2/vistaDatosContacto2"; 
		}				
		//En caso de errores volver a la página de formulario
		return "vistasContacto2/vistaNuevoContacto2";
	}
	
	
	//------------ACTUALIZACIONES:  Borrado ------------//	
	@RequestMapping("/borrarContacto/{id}")
    public String remove(@PathVariable("id") int id){
		
        this.dao.remove(Contacto.class, id);
        return "redirect:/contacto2/ReporteContactos";
    }
 	

	//------------ACTUALIZACIONES:  Edición (UpDate) ------------//	
    @GetMapping("/editarContacto/{id}")
    public String editContacto(@PathVariable("id") int id, Model model){
        model.addAttribute("contacto", this.dao.selectGenericByID(Contacto.class, id));
       
        return "vistasContacto2/vistaEditorContacto2";
    }
   
	@PostMapping("/editarContacto/procesarEdicionContacto2")
	public String procesarResultadoEdicionContacto(@Valid  @ModelAttribute("contacto") Contacto contactoToken,
				Model modelo, BindingResult resValidacion) {
	//Aplicamos la validación. BindingResult sin errores

   	if(!resValidacion.hasErrors()) {			
			//Guardamos en DB

		dao.update(Contacto.class, contactoToken.getId(), contactoToken);			
			return "vistasContacto2/vistaDatosContacto2";
 		}				
		//En caso de errores volver a la página de formulario
		return "vistasContacto2/vistaEditorContacto2";
	}
	
	
	//-------INVOCA LA VISTA PARA CONFIRMACIÓN DE ALTAS Y ACTUALIZACIONES ----------------
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
		return "vistasContacto/vistaEditorDatosContacto";
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
	
	/*
    JOptionPane.showMessageDialog(null,
            "hasta aquí voy bien",
            "PopUp Dialog",
            JOptionPane.INFORMATION_MESSAGE);		
*/
	
}