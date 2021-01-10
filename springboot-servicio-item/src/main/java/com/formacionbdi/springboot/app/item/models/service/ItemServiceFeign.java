package com.formacionbdi.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;

@Service
@Primary //Como tengo dos clases que implementan la misma interface con primary indico que esta sera la que spring tome por defecto.
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p-> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findByIdAndCantidad(Long id, Integer cantidad) {
		return new Item(clienteFeign.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return clienteFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);
	}
}

//Como tengo dos clases que implementan la misma interface con primary indico que esta sera la que spring tome por defecto.
//Otra opcion seria darle un nombre al bean (@Service("serviceFeing")) si no le ponemos un nombre spring por defecto toma como nombre del bean
//el nombre de la clase en minuscula.Una vez hecho esto en el controller ItemController hacemos uso de @Qualifier("serviceFeing") de esta manera tambien 
//se indica que el bean clase por defecto a tomar es ItemServiceFeign