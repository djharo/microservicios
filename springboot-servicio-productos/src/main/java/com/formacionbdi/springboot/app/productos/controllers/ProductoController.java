package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.models.service.IProductoService;
import com.formacionbdi.springboot.app.productos.models.vo.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired//Con esta propiedad podemos obtener las propiedades que tenemos definidas en el application.properties como el valor del puerto en el que se levanta el servicio.
	private Environment env;//org.springframework.core.env.Environment;
	
	@Autowired
	private IProductoService productoService;
	
	@Value("${server.port}") //import org.springframework.beans.factory.annotation.Value;
	private Long port;//Se obtiene el valor de la propiedad server.port definida en el application.properties de esta manera spring tambien hace el casting de forma automatica de string a long.
	
	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAllProduct().stream().map(producto -> {
			//producto.setPort(Long.valueOf(env.getProperty("local.server.port", "8001")));
			producto.setPort(port);	
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/detalle/{id}")
	public Producto detalle(@PathVariable(value = "id", required = true) Long id) /*throws Exception*/ {
		Producto producto = productoService.findById(id);
		//producto.setPort(Long.valueOf(env.getProperty("local.server.port", "8001"))); //local.server.port local indida que haga uso de nuestro application.properties y que tome el valor que se encuentra en server.port
		producto.setPort(port);																			//Si no se resolviera la clave no hubiera puerto por defecto mostraria el 8001 que es donde se levanta el servicio.
		
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		boolean ok = true;
//							**Provacando error para comprobar que Hystrix funciona correctamente
//		if(ok) {
//			throw new Exception("No se pudo cargar el producto.");
//		}
		
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		return productoService.editar(producto, id); //Podria haber mandado todo en el body.
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.delete(id);
	}
}
