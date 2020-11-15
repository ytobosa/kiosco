package com.alfonso.kioscoApp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alfonso.kioscoApp.interfaces.IGastosService;
import com.alfonso.kioscoApp.interfaces.IUsuariosService;
import com.alfonso.kioscoApp.model.Gastos;
import com.alfonso.kioscoApp.model.Perfil;
import com.alfonso.kioscoApp.model.Usuarios;
import com.alfonso.kioscoApp.service.EnvioMail;
import com.alfonso.kioscoApp.utileria.Utileria;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/gastos")
public class GastosController {


	@Autowired
	private IGastosService serviceGastos;
	
	@Autowired
	private IUsuariosService serviceUsuario;
	
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	// mapeamos la solicitud
	@RequestMapping(value="/create", method = RequestMethod.GET )
	public String crear(@ModelAttribute Gastos gasto, Model modelo) {
		modelo.addAttribute("gasto", new Gastos());
        return "gastos/formGastos";
	}
	
	@GetMapping(value ="indexPagina")
	public String indicePaginado(Model modelo, Pageable page) {
		// se llama al metodo de la implementacion
		Page<Gastos> lista = serviceGastos.getGastos(page);
		modelo.addAttribute("gastos", lista);
		return "gastos/listGastos";
	}
	
	// mapeamos la solicitud
	@PostMapping("/guardar")
	public String guardar(Gastos gasto, BindingResult resultado, RedirectAttributes redirect,
							@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			gasto.setImagen(nombreImagen);
		}
		
		// se recorre la lista de errores g
		for (ObjectError error : resultado.getAllErrors()) {
			System.out.println("Error: " + error.getDefaultMessage());
			return "gastos/formGastos";
		}		
		
		//Mandamos a la vista un objeto flash que muestra un mensaje 
		serviceGastos.guardar(gasto);
		redirect.addFlashAttribute("mensaje", "El gasto fue registrado con exito.");
		EnvioMail enviomail = new EnvioMail();
		String body ="Se ha registrado un gasto con los siguientes detalles: <br> "
				+ "Clasificación: "+gasto.getClasificacion()+"<br>"
				+ "Id: "+gasto.getId()+"<br>"
				+ "Fecha de la factura: "+gasto.getFechaFact()+"<br>"
				+ "Fecha de Carga: "+gasto.getFechaCarga()+"<br>"
				+ "Usuario: "+gasto.getUsuario()+"<br>"
				+ "Proveedor: "+gasto.getProveedor()+"<br>"
				+ "Nombre unico de imagen: "+gasto.getImagen()+"<br>"
				+ "Descripcion: "+gasto.getDescripcion()+"<br>";
		String subject="Un gasto se ha registrado con exito";
		List<Usuarios> emails= serviceUsuario.buscarEmailByPerfil("GERENTE");
		for (Usuarios usuario : emails) {
			List<Perfil> perfiles = usuario.getPerfiles();
			for (Perfil perfil : perfiles) {
				if(perfil.getPerfil().equals("GERENTE")) {
					enviomail.sendEmail(usuario.getEmail(), subject, body);
				}
			}
		}
		
		System.out.println("Gasto: " + gasto);
		return "redirect:/gastos/indexPagina";
	}
	
	//Mapeamos por el get 
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id")int idGasto, Model modelo) {
		Gastos gasto = serviceGastos.buscarPorId(idGasto);
		//Se a�ade a� model que necesita el formulario para renderizar los datos 
		modelo.addAttribute("gasto",gasto);		
		return "gastos/formGastos";
	}
	
	//mapeamos la solicitud 
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id")int idEliminar, RedirectAttributes redirect) {
		//Declaramos un objeto de tipo pelicula
		Gastos gasto = serviceGastos.buscarPorId(idEliminar);
		//se elimina por id una pelicula
		serviceGastos.borrarGasto(idEliminar);
		//se a�ade un objeto de tipo RedirectAttributes para enviar mensaje en un redirect
		redirect.addFlashAttribute("mensaje", "El gasto fue eliminado.");
		EnvioMail enviomail = new EnvioMail();
		String body ="Se ha eliminado un registro de gasto con los siguientes detalles: <br> "
				+ "Clasificación: "+gasto.getClasificacion()+"<br>"
				+ "Id: "+gasto.getId()+"<br>"
				+ "Fecha de la factura: "+gasto.getFechaFact()+"<br>"
				+ "Fecha de Carga: "+gasto.getFechaCarga()+"<br>"
				+ "Usuario: "+gasto.getUsuario()+"<br>"
				+ "Proveedor: "+gasto.getProveedor()+"<br>"
				+ "Nombre unico de imagen: "+gasto.getImagen()+"<br>"
				+ "Descripcion: "+gasto.getDescripcion()+"<br>";
		String subject="Un registro de gasto se ha eliminado";
		List<Usuarios> emails= serviceUsuario.buscarEmailByPerfil("GERENTE");
		for (Usuarios usuario : emails) {
			List<Perfil> perfiles = usuario.getPerfiles();
			for (Perfil perfil : perfiles) {
				if(perfil.getPerfil().equals("GERENTE")) {
					enviomail.sendEmail(usuario.getEmail(), subject, body);
				}
			}
		}
		return "redirect:/gastos/indexPagina";		
	}
	
	public void pruebaApi() {
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> call= restTemplate.getForEntity("https://www.dolarsi.com/api/api.php?type=valoresprincipales",String.class);
	    ObjectMapper mapper = new ObjectMapper();
	    TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
	    Map<String, String> map = null;
	    try {
			 map = mapper.readValue(call.getBody(), typeRef);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    System.out.println(call.getBody());
	    System.out.println(map.toString());
	}	
	
	// declarar un conversor de tipos fcha para que sea manejado por el
	// controlador antes de almacenarlo en el bean
	// Aplica para todos los tipos de variable, tipo fecha
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
