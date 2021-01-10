package com.formacionbdi.springboot.app.item.models;

import java.util.Date;

public class Producto {
	
	private Long id;
	
	private String nombre;
	
	private double precio;

	private Date fechaCreacion;
	
	private Long port;//Aqui tambien declaramos el puerto para que cuando llame al servicio productos y este devuelva el json con Producto que contendrá
	//la variable port se mape en este vo Prducto.
	
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