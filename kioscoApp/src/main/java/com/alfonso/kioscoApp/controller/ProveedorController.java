package com.alfonso.kioscoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alfonso.kioscoApp.interfaces.IProveedorService;
import com.alfonso.kioscoApp.model.Gastos;
import com.alfonso.kioscoApp.model.Proveedor;

@Controller
@RequestMapping("/proveedor")

public class ProveedorController {

	@Autowired
	private IProveedorService serviceProv;

	@RequestMapping(value = "/save")
	public String guardar(Proveedor prov) {
		serviceProv.guardar(prov);
		return null;//*********************
	}

	@GetMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable("id") int idEliminar) {
		Proveedor Prov = serviceProv.buscarporId(idEliminar);
		// se elimina por id
		serviceProv.eliminar(idEliminar);
		return null;//*********************
	}
}
