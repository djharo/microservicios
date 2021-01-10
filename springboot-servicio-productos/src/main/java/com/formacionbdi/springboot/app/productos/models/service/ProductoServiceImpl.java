package com.formacionbdi.springboot.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.productos.models.repository.ProductoRepository;
import com.formacionbdi.springboot.app.productos.models.vo.Producto;
import com.formacionbdi.springboot.app.productos.models.vo.utils.ProductoConverter;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ProductoConverter productoConverter;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAllProduct() {
		return productoConverter.productoEntityToVo(productoRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoConverter.productoEntityToVo(productoRepository.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {	
		return productoConverter.productoEntityToVo(productoRepository.save(productoConverter.productoVoToEntity(producto)));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public Producto editar(Producto producto, Long id) {
		
		Producto productoDb = this.findById(id);//Si producto no existe? y devuelve null?
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		
		return productoConverter.productoEntityToVo(productoRepository.save(productoConverter.productoVoToEntity(productoDb)));
	}
}
