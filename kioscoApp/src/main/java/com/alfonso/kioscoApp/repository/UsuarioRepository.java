package com.alfonso.kioscoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alfonso.kioscoApp.model.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
	@Query("SELECT u FROM Usuarios u")
	List<Usuarios> findEmailByPerfil(@Param("perfil")String perfil);
}
