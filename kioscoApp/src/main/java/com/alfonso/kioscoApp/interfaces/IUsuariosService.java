package com.alfonso.kioscoApp.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alfonso.kioscoApp.model.Usuarios;

public interface IUsuariosService {
	void guardar(Usuarios usuario);
	List<Usuarios> buscarUsuarios();
	List<Usuarios> buscarEmailByPerfil(String perfil);
	Usuarios getUsuario(int id);
	void eliminarUsu(int id);
	Page<Usuarios> getUsuarios(Pageable pagina);
	
} 
