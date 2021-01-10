package com.formacionbdi.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean("clienteRest") //Si no el nombre del Bean seria el nombre del metodo.
	@LoadBalanced //Con esta anotacion cuando se invoque al servicio productos se utilizara ribon para el balanceo de cargas restTemplat buscara la mejor instancia disponible
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();//El objeto que se retorna se guarda en el contenedor de beans gracias a la etiqueta @Beans con 
								//el identificador de nombre el nombre del metodo pero al ponerle ("clienteRest") el identificador pasa
								//a ser clienteRest. 
	}//RestTemplate es un cliente para trabajar con API Rest, un cliente HTTP para poder acceder a recursos que estan en otros microservicios.
}
