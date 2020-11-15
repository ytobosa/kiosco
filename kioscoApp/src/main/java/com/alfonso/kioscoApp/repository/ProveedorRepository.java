package com.alfonso.kioscoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alfonso.kioscoApp.model.Proveedor;

public interface ProveedorRepository  extends JpaRepository<Proveedor, Integer> {

	public List<Proveedor> findByEstatus(String estatus);
	public Proveedor findByNombre(String nombre);
}
