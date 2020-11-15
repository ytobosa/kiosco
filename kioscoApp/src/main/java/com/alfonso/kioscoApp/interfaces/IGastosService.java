package com.alfonso.kioscoApp.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alfonso.kioscoApp.model.Gastos;

public interface IGastosService {
	
	void guardar(Gastos gasto);
	List<Gastos> buscarTodos();
	Gastos buscarPorId(int id);
	List<Gastos> buscarPorFecha(Date fechaGasto);
	List<Gastos> buscarPorUsuario(String Usuario);
	void borrarGasto(int id);
	List<Gastos> buscarPorClasificacion(String Usuario);
	Page<Gastos> getGastos(Pageable pagina);
}
