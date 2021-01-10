package com.formacionbdi.springboot.app.item.models;

public class Item { //Generar el item a partir del prodcuto.
	
	private Producto producto;
	
	private Integer cantidad;

	public Item() {

	}

	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	//poniendo getTotal en el json de salida aparecera total con el resultado de la operacion.
	public Double getTotal() { //Precio total de uno o varios productos si se compran tres productos del id 3 pues el precio de ese producto por tres.
		return producto.getPrecio() * cantidad;
	}
}
//En el Json de salida lo que aparece son los getX es decir en este caso el getProducto, getCantidad y getTotal.