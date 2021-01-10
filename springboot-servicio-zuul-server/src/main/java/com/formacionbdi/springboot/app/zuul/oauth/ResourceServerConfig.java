package com.formacionbdi.springboot.app.zuul.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception { //antMatchers configuración multiple de seguridad en Spring se vana añadieno las rutas con sus tipos de vebos a los que queremos dar algun tipo de permisos para todos o algun determinado rol
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll() //Cualquier usuario puede logearse. Se aplica a cualquier verbo post, put get delete ...
		.antMatchers(HttpMethod.GET, "/api/productos/productos/listar", "/api/item/items/listar", "/api/usuarios/usuarios").permitAll()//Para los listados de nuestros servicios cualquier usuario podrá acceder
		.antMatchers(HttpMethod.GET, "/api/productos/productos/detalle/{id}",
				"/api/item/items/detalle/{id}/cantidad/{cantidad}", 
				"/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")//Para obtener el detalle de nuestros servicios solo tendrán acceso los usuarios con role ADMIN Y USER ** NOTA EN BBDD SI ES NECESARIO AÑADIR EL PREFIJO ROLE_ aqui spring lo "añade" cuando hace la validacion de los roles
		.antMatchers("/api/productos/**", "/api/item/**", "/api/usuarios/**").hasRole("ADMIN") //Las rutas mas especificas iran arriba y luego todas las reglas y rutas que no haymos configurado se van a configurar de forma automática en las reglas que son mas genéricas como el crud (post, put, delete)
		.anyRequest().authenticated();//cualquier otra ruta(regla mas generica requiere autenticacion)
//		.antMatchers(HttpMethod.POST, "/api/productos/productos/crear", "/api/item/items/crear", "/api/usuarios/usuarios").hasRole("ADMIN")//Sólo los usuarios con role ADMIN pueden crear en nuestros microservicios.
//		.antMatchers(HttpMethod.PUT, "/api/productos/productos/editar/{id}", "/api/item/items/editar/{id}", "/api/usuarios/usuarios/{id}").hasRole("ADMIN")//Sólo los usuarios con role ADMIN pueden editar/modificar en nuestros microservicios.
//		.antMatchers(HttpMethod.DELETE, "/api/productos/productos/eliminar/{id}", "/api/item/items/eliminar/{id}", "/api/usuarios/usuarios/{id}").hasRole("ADMIN") //Sólo los usuarios con role ADMIN pueden eliminar en nuestros microservicios.
//		.anyRequest().authenticated();//cualquier otra ruta(regla mas generica requiere autenticacion)
	}	
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey); //"algun_codigo_secreto_aeiou" Ahora se toma desde el archivo application.properties de config-server 
 		return tokenConverter;//Para este caso como solo es una propiedad hacemos usu de @Value en vez de Enviroment como se hace en AuthorizationServerConfig del servicio oauth
	}
}
