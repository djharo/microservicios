package com.formacionbdi.springboot.app.productos.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.productos.models.vo.Producto;

public interface IProductoService {

	List<Producto> findAllProduct();
	
	Producto findById(Long id);
	
	Producto save(Producto producto);
	
	void delete(Long id);
	
	Producto editar(Producto producto, Long id);
}
	