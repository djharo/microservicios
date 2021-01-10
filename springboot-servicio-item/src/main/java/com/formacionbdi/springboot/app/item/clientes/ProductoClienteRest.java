package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.springboot.app.item.models.Producto;

@FeignClient(name = "servicio-productos") //servicio-productos nombre del microservicio al que quiero conectar el nombre se encuentra en el properties del microservicio.
public interface ProductoClienteRest { //, url = "localhost:8001/productos") esta parte si se hace uso de Ribbon ya desaparece de aqui.
										//La parte del @RequestMapping("/productos") se pone en el 	@GetMapping O @PostMapping del metodo al que se quiera acceder.
	@GetMapping("/productos/listar")    // /productos = @RequestMapping("/productos") de la clase ProductoController.
	public List<Producto> listar(); 
		
	@GetMapping("/productos/detalle/{id}")
	public Producto detalle(@PathVariable(value = "id", required = true) Long id);
	
	@PostMapping("/productos/crear")
	public Producto crear(@RequestBody Producto producto);
	
	@PutMapping("/productos/editar/{id}")
	public Producto update(@RequestBody Producto producto, @PathVariable Long id);
	
	@DeleteMapping("/productos/eliminar/{id}")
	public Producto eliminar(@PathVariable Long id);
}
