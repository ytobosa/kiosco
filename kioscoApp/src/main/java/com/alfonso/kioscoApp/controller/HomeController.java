package com.alfonso.kioscoApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alfonso.kioscoApp.interfaces.IBannerService;
import com.alfonso.kioscoApp.interfaces.INoticiasService;
import com.alfonso.kioscoApp.model.Banner;
import com.alfonso.kioscoApp.model.Noticia;

@Controller
public class HomeController {
	
	@Autowired
	private IBannerService serviceBanner;
	
	@Autowired
	private INoticiasService serviceNoticia;
	
	//Se instancia un formato de fechas de manera global
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		List<Banner> listaBanners = serviceBanner.buscarTodasXEst("Activo");
		
		List<Noticia> noticias = serviceNoticia.buscarTodas();
		
		//Se añaden los atributos de la pelicula al modelo para ser enviados al context
		model.addAttribute("noticias", noticias);
		//model.addAttribute("fechaBusqueda", sdf.format(new Date()));
		model.addAttribute("listaBanners", listaBanners);
		return "home";
	}
	
    @GetMapping("/login")
    public String mostrarLogin() {
    	return"formLogin";
    }
	
	@RequestMapping(value="/acerca", method = RequestMethod.GET)
	public String acerca() {
		return "acerca";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
	
	
}
