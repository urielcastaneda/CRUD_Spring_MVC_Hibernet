package ucs.catedra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller para acceso a página principal
 */
@Controller
public class ErrorController {

    /**
     * Name for the 404 error view.
     */
    private static final String VIEW_404 = "404";

    /**
     * Default constructor.
     */
    public ErrorController() {
        super();
    }

    /**
     * Shows the 404 error view.
     * 
     * @return the 404 error view
     */
    @RequestMapping("/404")
    public String show404() {
        return VIEW_404;
    }

}
