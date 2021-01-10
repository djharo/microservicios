package com.formacionbdi.springboot.app.oauth.services;

import commons.usuarios.models.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public Usuario update(Usuario usuario, Long id);
}
