package com.example.demo.servicio.interfaces;

import java.util.List;

import com.example.demo.modelo.Pedido;

public interface PedidoService {
	public Pedido insertarPedido (Pedido pedido);
	public List<Pedido> listarPedidos();
	public Pedido obtenerPedidoPorId (Integer id);
	public void eliminarPedido(Pedido pedido);
	public void eliminarPedidoPorId(Integer id);
}
