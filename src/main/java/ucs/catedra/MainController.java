package ucs.catedra;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// ${pageContext.request.contextPath}
// Para evitar conflictos con otras rutas, 
// las rutas de los métodos son relativas a ésta.
@RequestMapping()
public class MainController {
	
	@RequestMapping()
	public String index(){
		return "index";
	}	
	
	@RequestMapping("/")
	public String viewHome()
	{
		return "index";
	}	

	@RequestMapping("/index")
	public String goToIndex(){
		return "index";
	}	
}
