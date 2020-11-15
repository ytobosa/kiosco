package com.alfonso.kioscoApp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfonso.kioscoApp.model.Noticia;

@Repository //Anotacion que indica que es una interface repositorio
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

		//select * from noticia where estatus = ?
		List<Noticia> findByEstatus(String estatus);
		
		//select * from noticia
		List<Noticia> findBy();
		
		//select * from noticia where fecha = ?
		List<Noticia> findByFecha(Date fecha);
		
		//where estatus = ? and fecha = ? and id = ?
		List<Noticia> findByEstatusAndId(String estatus, int id);
		
		//where estatus= ? or fecha = ?
		List<Noticia> findByEstatusOrFecha(String estatus, Date fecha);
		
		//where fecha between ? and ?
		List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);
}
