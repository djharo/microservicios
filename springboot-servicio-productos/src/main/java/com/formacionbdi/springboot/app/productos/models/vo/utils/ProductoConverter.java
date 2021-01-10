package com.formacionbdi.springboot.app.productos.models.vo.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.formacionbdi.springboot.app.commons.models.entity.ProductoEntity;
import com.formacionbdi.springboot.app.productos.models.vo.Producto;

@Component
public class ProductoConverter {
	
	public Producto productoEntityToVo(ProductoEntity entity) {
		
		if(entity == null)
			return null;
		
		Producto producto = new Producto();
		producto.setId(entity.getId());
		producto.setNombre(entity.getNombre());
		producto.setPrecio(entity.getPrecio());
		producto.setFechaCreacion(entity.getFechaCreacion());
		
		return producto;
	}
	
	public List<Producto> productoEntityToVo(List<ProductoEntity> entities) {
		
		if(entities == null)
			return new ArrayList<>();
		
		List<Producto> productos = new ArrayList<>();
		
		for(ProductoEntity p : entities) {
			productos.add(productoEntityToVo(p));
		}
		
		return productos;
	}

	public ProductoEntity productoVoToEntity(Producto producto) {
		
		if(producto == null)
			return null;
		
		ProductoEntity productoEntity = new ProductoEntity();
		productoEntity.setId(producto.getId());//Aqui si ponemos el id para cuando queramos modificar un registro.
		productoEntity.setNombre(producto.getNombre());
		productoEntity.setPrecio(producto.getPrecio());
		productoEntity.setFechaCreacion(producto.getFechaCreacion());
		
		return productoEntity;
	}
}
