package com.alfonso.kioscoApp.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alfonso.kioscoApp.model.Contactos;
import com.alfonso.kioscoApp.utileria.Utileria;

@Controller
@RequestMapping("/contact")
public class ContactoController {
		
	//@Autowired
	//IPeliculasService servicePeliculas;
	
	@RequestMapping("/contactos")
	public String mostrarContactos(@ModelAttribute("instanciaContactos") Contactos contacto, Model modelo) {
		//modelo.addAttribute("generos", servicePeliculas.buscarGeneros());
		modelo.addAttribute("notificaciones", tipoNotificaciones());
		modelo.addAttribute("listaRating", rating());
		
		return "contactos/formContactos";
	}
	
	@RequestMapping("/save")
	public String guardar(Contactos contacto, Model modelo) {
		//modelo.addAttribute("generos",servicePeliculas.buscarGeneros());
		return "redirect:/contact/contactos";
	}
	
	public List<String> tipoNotificaciones(){
		List<String> notificaciones = new LinkedList<>();
		notificaciones.add("Estrenos");
		notificaciones.add("Promociones");
		notificaciones.add("Noticias");
		notificaciones.add("Preventas");
		return notificaciones;
	}
	
	public List<String> rating(){
		List<String> lista= new LinkedList<>();
		lista.add("Muy mala");
		lista.add("Mala");
		lista.add("Regular");
		lista.add("Buena");
		lista.add("Muy buena");
		
		return lista;
	}
	
	
	
	
	
}
