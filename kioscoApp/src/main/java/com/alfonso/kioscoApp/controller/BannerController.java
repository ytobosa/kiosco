package com.alfonso.kioscoApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alfonso.kioscoApp.interfaces.IBannerService;
import com.alfonso.kioscoApp.model.Banner;
import com.alfonso.kioscoApp.utileria.Utileria;

@Controller
@RequestMapping("/banners")
public class BannerController {
	
	@Autowired
	IBannerService bannerService;
	
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	@RequestMapping("/create")
	public String crear(@ModelAttribute Banner banner) {		
		return "banners/formBanner";		
	}
	
	@GetMapping(value ="/indexPagina")
	public String indicePaginado(Model modelo, Pageable pagina) {
		Page<Banner> lista = bannerService.buscarTodas(pagina);
		modelo.addAttribute("banners", lista);
		return "banners/listBanner";
	} 
	
	
	@RequestMapping(value="/edit/{id}")
	public String editarBanners(@PathVariable("id") int idBanner, Model modelo) {
		Banner banner = bannerService.buscarPorId(idBanner);
		modelo.addAttribute("banner", banner);
		return "banners/formBanner";
	}
	
	@PostMapping("/save")
	public String guardar(Banner banner, BindingResult result, RedirectAttributes attr, @RequestParam("archivoImagen") MultipartFile multiPart) {
		if(result.hasErrors()){
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}
			return "banners/formBanner";
		}
		
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
				// Procesamos la variable nombreImagen
				banner.setArchivo(nombreImagen);
			}
		}
		
		bannerService.insertar(banner);
		attr.addFlashAttribute("msg", "Registro guardado.");
		System.out.println("banner: "+banner);
		return "redirect:/banners/indexPagina";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idBanner, Model modelo, RedirectAttributes attr) {
		bannerService.eliminar(idBanner);
		System.out.println("Vacante eliminada con el id "+idBanner);
		attr.addFlashAttribute("msg", "Registro eliminado.");
		modelo.addAttribute("id", idBanner);
		return "redirect:/banners/indexPagina";
	}
}
