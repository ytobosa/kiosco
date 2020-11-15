package com.alfonso.kioscoApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfonso.kioscoApp.interfaces.IUsuariosService;
import com.alfonso.kioscoApp.model.Usuarios;
import com.alfonso.kioscoApp.repository.UsuarioRepository;

@Service
public class UsuariosServiceJPA implements IUsuariosService{

	//Inyeccion de dependencia del repository
	@Autowired
	UsuarioRepository usuariosRepo;
	
	@Override
	public void guardar(Usuarios usuario) {
		usuariosRepo.save(usuario);
	}

	@Override
	public List<Usuarios> buscarUsuarios() {
		return usuariosRepo.findAll();
	}

	@Override
	public Usuarios getUsuario(int id) {
		Optional<Usuarios> opt = usuariosRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public void eliminarUsu(int id) {
		usuariosRepo.deleteById(id);		
	}

	@Override
	public Page<Usuarios> getUsuarios(Pageable pagina) {
		return usuariosRepo.findAll(pagina);
	}

	@Override
	public List<Usuarios> buscarEmailByPerfil(String perfil) {
		return usuariosRepo.findEmailByPerfil(perfil);
	}
	
}
