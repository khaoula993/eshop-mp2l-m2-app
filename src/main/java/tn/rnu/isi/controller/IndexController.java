package tn.rnu.isi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tn.rnu.isi.model.Produit;
import tn.rnu.isi.service.ProduitService;


 


 
 
@Controller 
@RequestMapping("/") //make all URL's through this controller relative to /index
public class IndexController {
	
	private final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	ProduitService produitService;
	
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String index(Map<String, Object> model) throws Exception {
	 
    return "index";
	}   
	
 
	/**************************************
	 * Gestion produit
	 * **Nouvelle : /produit/new
     * **Rechercher : /produit/search
     * **Liser Tous : /produit/listAll
	 ***************************************/	
	 
	// show new Produit form
		@RequestMapping(value = "/produit/new", method = RequestMethod.GET)
		public String showNewProduit(Model model) {

			logger.debug(":::showNewProduit:::");

			Produit produit = new Produit();
			
			model.addAttribute("produitForm", produit);

	 
			 return "/produit/addUpdateProduit";// C'est le nom de la page JSP à rediriger (newProduit.jsp)

		}
		
	
				
	 // show list of All Produit
		@RequestMapping({"/produit/listAll","produitList"})
		protected ModelAndView lisAllProduits(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			/*
			 * Lancement du Service et récupération données en base
			 */
			List<Produit> listeProduits = produitService.getAll();

			/*
			 * Envoi Vue + Modèle MVC pour Affichage données vue
			 */
			return new ModelAndView("/produit/showAllProduits", "produits", listeProduits);
		} 
	
}
