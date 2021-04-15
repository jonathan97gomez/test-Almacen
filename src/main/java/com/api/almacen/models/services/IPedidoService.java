package com.api.almacen.models.services;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.api.almacen.models.entity.Pedido;


public interface IPedidoService {
	
	public double valorPedido(Pedido pedido);
	
	public double valorProductos(Pedido pedido);
		
	public double valorDomicilio();
	
	public int compararFecha(Date fechaOld);
	
	public ResponseEntity<?> generarFactura(Pedido pedido, String status);
	
	public ResponseEntity<?> registrarPedido(Pedido nuevoPedido);
	
	public ResponseEntity<?> editarPedido(Pedido nuevoPedido);
	
	public ResponseEntity<?> eliminarPedido(long idPedido);
}
