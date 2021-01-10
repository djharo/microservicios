package com.formacionbdi.springboot.app.productos.models.vo;

import java.util.Date;

public class Producto {
	
	//La informacion del id del producto suele ser irrelevante para el usuario en este caso practico lo dejaremos para ver que
	//estamos haciendo bien el obtener todos los productos o uno solo.
	
	private Long id;
	
	private String nombre;
	
	private double precio;

	private Date fechaCreacion;
	
	private Long port; //Solo se utilizara para visualizar que puerto se esta seleccionando cuando se haga la llamada desde el cliente servicio-item
	
	public Producto() {

	}

	public Producto(Long id, String nombre, double precio, Date fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion;
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getPort() {
		return port;
	}

	public void setPort(Long port) {
		this.port = port;
	}
	
}