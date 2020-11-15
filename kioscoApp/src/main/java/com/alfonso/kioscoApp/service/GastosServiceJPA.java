package com.alfonso.kioscoApp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfonso.kioscoApp.interfaces.IGastosService;
import com.alfonso.kioscoApp.model.Gastos;
import com.alfonso.kioscoApp.repository.GastosRepository;

@Service
public class GastosServiceJPA implements IGastosService{


	@Autowired
	GastosRepository repoGastos;

	public List<Gastos> buscarTodos() {
		return repoGastos.findAll();
	}

	public Gastos buscarPorId(int id) {
		Optional<Gastos> gasto = repoGastos.findById(id);
		if (gasto.isPresent()) {
			return gasto.get();
		}
		return null;
	}

	public List<Gastos> buscarPorFecha(Date fechaGasto) {
		return repoGastos.findByFechaFact(fechaGasto);
	}

	public List<Gastos> buscarPorUsuario(String Usuario) {
		return repoGastos.findByUsuario(Usuario);
	}

	public void guardar(Gastos gasto) {
		repoGastos.save(gasto);
	}

	public void borrarGasto(int id) {
		repoGastos.deleteById(id);
	}

	public List<Gastos> buscarPorClasificacion(String Usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Gastos> getGastos(Pageable pagina) {
		return repoGastos.findAll(pagina);
	}

}
