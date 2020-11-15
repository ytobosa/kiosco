package com.alfonso.kioscoApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfonso.kioscoApp.interfaces.IProveedorService;
import com.alfonso.kioscoApp.model.Proveedor;
import com.alfonso.kioscoApp.repository.ProveedorRepository;

@Service
public class ProveedorServiceJPA implements IProveedorService {

	@Autowired
	ProveedorRepository repoProveedor;

	@Override
	public void guardar(Proveedor proveedor) {
		repoProveedor.save(proveedor);
		
	}

	@Override
	public List<Proveedor> buscarTodos() {
		return repoProveedor.findAll();
	}

	@Override
	public Proveedor buscarporId(int id) {
		Optional<Proveedor> prov =repoProveedor.findById(id);
		if (prov.isPresent() ) {
			return prov.get();
		}
		return null;
	}

	@Override
	public Proveedor buscarporNombre(String nombre) {
		repoProveedor.findByNombre(nombre);
		return null;
	}

	@Override
	public List<Proveedor> buscarporEstatus(String estatus) {
		repoProveedor.findByEstatus(estatus);
		return null;
	}

	@Override
	public void eliminar(int id) {
		repoProveedor.deleteById(id);		
	}

	@Override
	public Page<Proveedor> getProveedor(Pageable pagina) {
		return repoProveedor.findAll(pagina);
	}
}
