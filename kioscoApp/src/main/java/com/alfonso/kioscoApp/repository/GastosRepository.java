package com.alfonso.kioscoApp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfonso.kioscoApp.model.Gastos;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Integer> {

	
	public List<Gastos> findByFechaFact(Date fecha);

	public List<Gastos> findByUsuario(String usuario);
	
	public List<Gastos> findByClasificacion(String clasificacion);
	
}
