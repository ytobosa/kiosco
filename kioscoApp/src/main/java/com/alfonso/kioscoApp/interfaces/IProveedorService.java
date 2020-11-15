package com.alfonso.kioscoApp.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alfonso.kioscoApp.model.Proveedor;

public interface IProveedorService {

	void guardar(Proveedor proveedor);
	List<Proveedor>buscarTodos();
	Proveedor buscarporId (int id);
	Proveedor buscarporNombre (String nombre);
	List<Proveedor>buscarporEstatus(String estatus);
	void eliminar(int id);
	Page<Proveedor> getProveedor(Pageable pagina);
	
}
