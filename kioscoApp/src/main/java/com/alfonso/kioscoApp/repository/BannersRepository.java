package com.alfonso.kioscoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfonso.kioscoApp.model.Banner;

@Repository
public interface BannersRepository extends JpaRepository<Banner, Integer> {
	//Se realiza query para buscar listaa por estatus
	List<Banner> findByEstatus(String estatus);
}
