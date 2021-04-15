package com.api.almacen.models.entity;

public class Producto {

	private String idProducto;
	private String nombreProducto;
	private double valorProducto;

	public Producto(String idProducto, String nombreProducto, double valorProducto) {

		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.valorProducto = valorProducto;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getValorProducto() {
		return valorProducto;
	}

	public void setValorProducto(double valorProducto) {
		this.valorProducto = valorProducto;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", valorProducto="
				+ valorProducto + "]";
	}

}
