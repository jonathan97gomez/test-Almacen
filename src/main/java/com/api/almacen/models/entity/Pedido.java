package com.api.almacen.models.entity;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

	private long idPedido;
	private int cedula;
	private ArrayList<Producto> productos;
	private boolean domicilio;
	private Date fechaCreacion;
	private double valorPedido;
	private String direccion;

	public Pedido(long idPedido, int cedula, ArrayList<Producto> productos, boolean domicilio,
			Date fechaCreacion, double valorPedido, String direccion) {

		this.idPedido = idPedido;
		this.cedula = cedula;
		this.productos = productos;
		this.domicilio = domicilio;
		this.fechaCreacion = fechaCreacion;
		this.valorPedido = valorPedido;
		this.direccion = direccion;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	
	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public boolean getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(boolean domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", cedula=" + cedula + ", productos=" + productos + ", domicilio="
				+ domicilio + ", fechaCreacion=" + fechaCreacion + ", valorPedido=" + valorPedido + ", direccion="
				+ direccion + "]";
	}

	

}
