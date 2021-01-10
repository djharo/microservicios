package com.formacionbdi.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;

@Service
//@Primary //Comento el Primary de ItemServiceFeign para utilizar este. 
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Item> findAll() {
		//Ahora como se hace uso de ribon en la url para la llamada al servicio "externo"(Llamada entre microservicios) en lugar de poner localhost:8001 ponemos servicio-productos que esta definido en el aplication.properties con el valor de localhost:8001 y localhost:9001
																			//Ojo hay que poner la ruta completa con http
		//List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/productos/listar", Producto[].class));
		
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/productos/listar", Producto[].class));
		
		return productos.stream().map(p-> new Item(p, 1)).collect(Collectors.toList());

	}

	@Override
	public Item findByIdAndCantidad(Long id, Integer cantidad) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		//Lo mismo hacemos aqui para la url que se utiliza para hacer la llamada al servicio "externo"(Llamada entre microservicios)
		//Producto producto = clienteRest.getForObject("http://localhost:8001/productos/detalle/{id}", Producto.class, pathVariable);
		Producto producto = clienteRest.getForObject("http://servicio-productos/productos/detalle/{id}", Producto.class, pathVariable);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/productos/crear", HttpMethod.POST, body, Producto.class);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/productos/editar/{id}", 
				HttpMethod.PUT, body, Producto.class, pathVariable);
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		
		clienteRest.delete("http://servicio-productos/productos/eliminar/{id}", pathVariable);	
	}
}
