package com.api.almacen.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.almacen.models.entity.Pedido;
import com.api.almacen.models.entity.Producto;
import com.api.almacen.models.entity.Factura;


@Service
public class PedidoServiceImpl implements IPedidoService{

	private final AtomicLong counter= new AtomicLong();
	private ArrayList<Pedido> listPedidos = new ArrayList(); 	
	
	@Override
	public double valorPedido(Pedido pedido) {	
		
		if(valorProductos(pedido)>100000) {
			pedido.setDomicilio(false);
			return (valorProductos(pedido)*1.19);			
			
		}else if(valorProductos(pedido)<=100000 && valorProductos(pedido)>= 70000) {
			pedido.setDomicilio(true);
			return ((valorProductos(pedido)*1.19)+valorDomicilio());
		}
		return 0;
	}
	
	
	@Override
	public double valorProductos(Pedido pedido) {
		
		double total=0;
		
		for (Producto producto : pedido.getProductos()) {
			total+= producto.getValorProducto();
		}
		return total;
	}

	@Override
	public double valorDomicilio() {
		
		return 8000;
	}

	@Override
	public int compararFecha(Date fechaOld) {
		//  responde 18000 si la diferencia es de 5 horas
		// responde  43200 si la diferencia es de 12 horas
		
        Date fechaPedido= fechaOld;
        Date fechaActual= new Date();
 
        int diferencia=(int) ((fechaActual.getTime()-fechaPedido.getTime())/1000);
		
		return diferencia;
	}
	
	@Override
	public ResponseEntity<?> generarFactura(Pedido pedido, String status) {
		
		Map response = new HashMap();
		try {
			double iva= valorProductos(pedido)*0.19;
			if(pedido.getDomicilio()) {
				
				 Factura factura = new Factura(pedido,iva,valorDomicilio(),status);				 
				 return new ResponseEntity<Factura>(factura,HttpStatus.OK);
				 
			}else {
				Factura factura = new Factura(pedido,iva,0,status);
				return new ResponseEntity<Factura>(factura,HttpStatus.OK);
			}
			
		}catch (Exception e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> registrarPedido(Pedido nuevoPedido) {
		
		Map response = new HashMap();
		boolean ejecutarServicio=false;
		
		if(valorProductos(nuevoPedido)>=70000) {
			ejecutarServicio=true;
		}
		
		try {
			
			if(ejecutarServicio) {			
					
					long contador =counter.incrementAndGet();
										
					nuevoPedido.setIdPedido(contador);
					nuevoPedido.setFechaCreacion(new Date());
					nuevoPedido.setDomicilio(true);					
					nuevoPedido.setValorPedido(valorPedido(nuevoPedido));
					listPedidos.add(nuevoPedido);
					
				}
				
				return generarFactura(nuevoPedido,"Activo");
			
		}catch (Exception e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> editarPedido(Pedido nuevoPedido) {

		Map response = new HashMap();		
		boolean ejecutarServicio= false;
		Pedido respuesta = null;
		try {
			for (Pedido pedidoOld : listPedidos) {
				
				if(pedidoOld.getIdPedido()== nuevoPedido.getIdPedido()
						&& compararFecha(pedidoOld.getFechaCreacion())<= 18000 
						&& valorProductos(pedidoOld)<= valorProductos(nuevoPedido)) {										
					
					pedidoOld.setProductos(nuevoPedido.getProductos());
					pedidoOld.setDireccion(nuevoPedido.getDireccion());
					pedidoOld.setValorPedido(valorProductos(nuevoPedido));
					respuesta = pedidoOld;
					
				}
			}
			return generarFactura(respuesta,"Activo");
			
		}catch (Exception e) {
			
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@Override
	public ResponseEntity<?> eliminarPedido(long idPedido) {
		
		Map response = new HashMap();
		
		try {
			for (Pedido pedidoOld : listPedidos) {
				
				if(pedidoOld.getIdPedido()== idPedido) {							
						
					if(compararFecha(pedidoOld.getFechaCreacion())<43200) {
						
						listPedidos.remove(pedidoOld);
												
					}else if(compararFecha(pedidoOld.getFechaCreacion())>43200) {
						
						double cobro= pedidoOld.getValorPedido()*0.1;	
						return generarFactura(pedidoOld,"Cancelado");
					}					
				}					
			}
			response.put("Mensaje","Pedio eliminado");
			response.put("CodigoPedido",idPedido);
			return new ResponseEntity<Map>(response,HttpStatus.OK);
		}catch (Exception e) {
			response.put("Mensaje",e.getMessage());
			return new ResponseEntity<Map>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}		
}