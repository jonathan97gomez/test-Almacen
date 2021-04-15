package com.api.almacen.models.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.almacen.models.entity.Pedido;
import com.api.almacen.models.services.IPedidoService;

@RestController
@RequestMapping("/api")
public class PagosAlmacenController {
	
	@Autowired
	private IPedidoService pedido;		
	
	@PostMapping("/crearPedido")
	public ResponseEntity<?> creaPedido(@RequestBody Pedido nuevoPedido){
		
		return pedido.registrarPedido(nuevoPedido);
	}
	
	@PatchMapping("/editarPedido")
	public ResponseEntity<?> editaPedido(@RequestBody Pedido nuevoPedido){
						
		return pedido.editarPedido(nuevoPedido);
	}
	
	
	@DeleteMapping("/eliminarPedido")
	public ResponseEntity<?> eliminaPedido(@RequestParam(value="idPedido") Long idPedido){
		
		return pedido.eliminarPedido(idPedido); 
	}
}
