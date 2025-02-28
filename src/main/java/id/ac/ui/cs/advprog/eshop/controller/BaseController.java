package id.ac.ui.cs.advprog.eshop.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.ui.cs.advprog.eshop.service.GeneralService;

/**
 * Abstract base controller with common functionality
 * @param <T> Entity type
 * @param <ID> ID type
 * @param <S> Service type
 */
public abstract class BaseController<T, ID, S extends GeneralService<T, ID>> {
    
    protected final S service;
    
    protected BaseController(S service) {
        this.service = service;
    }
    
    /**
     * Handle entity not found cases
     */
    protected String handleNotFound(ID id, RedirectAttributes redirectAttributes, String redirectUrl) {
        redirectAttributes.addFlashAttribute("errorMessage", 
                String.format("Entity with ID %s not found", id));
        return redirectUrl;
    }
    
    /**
     * Add entities to model
     */
    protected void addEntitiesToModel(Model model, List<T> entities, String attributeName) {
        model.addAttribute(attributeName, entities);
    }
}