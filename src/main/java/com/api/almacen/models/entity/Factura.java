package com.api.almacen.models.entity;

public class Factura {

	private Pedido pedido;
	private double totalIva;
	private double totalDomicilio;
	private String statusPedido;
		
	public Factura(Pedido pedido, double totalIva, double totalDomicilio, String statusPedido) {
		this.pedido = pedido;
		this.totalIva = totalIva;
		this.totalDomicilio = totalDomicilio;
		this.statusPedido = statusPedido;
	}
	
	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public double getTotalIva() {
		return totalIva;
	}
	public void setTotalIva(double totalIva) {
		this.totalIva = totalIva;
	}
	public double getTotalDomicilio() {
		return totalDomicilio;
	}
	public void setTotalDomicilio(double totalDomicilio) {
		this.totalDomicilio = totalDomicilio;
	}

	@Override
	public String toString() {
		return "Factura [pedido=" + pedido + ", totalIva=" + totalIva + ", totalDomicilio=" + totalDomicilio
				+ ", statusPedido=" + statusPedido + "]";
	}
	
}
