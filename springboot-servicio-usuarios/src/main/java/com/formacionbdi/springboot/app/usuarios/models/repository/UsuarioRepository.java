package com.formacionbdi.springboot.app.usuarios.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import commons.usuarios.models.entity.Usuario;

@RepositoryRestResource(path="usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@RestResource(path = "buscar-username")//Ruta end point para utilizar nuestros métodos personalizados -> localhost:8090/api/usuarios/usuarios/search/buscar-username?username=admin
	//si no personalizaramos el metodo con las anotaciones sería localhost:8090/api/usuarios/usuarios/search/findByUsername?username=admin //este username es el nombre de la variable no el del param.
	public Usuario findByUsername(@Param("username") String username);//Si hacemos la consulta usando las propiedades(atributos de la clase Usuario) spring generará la consulta
													//select * from usuarios where user_name = username.
	@Query("select u from Usuario u where u.username = ?1") //Ejemplo con jpa
	public Usuario obtenerPorUsername(String username);
	
	@Query(value = "select u.* from usuarios u where u.username = ?1", nativeQuery = true) //Ejemplo con query nativa.
	public Usuario obtenerPorUsernameNative(String username);
}
