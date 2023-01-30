package com.example.demo.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modelo.Pedido;
import com.example.demo.persistencia.PedidoRepository;
import com.example.demo.servicio.interfaces.PedidoService;

public class PedidoServiceImpl implements PedidoService{

	@Autowired
	public PedidoRepository pedidoRepo;
	
	@Override
	public Pedido insertarPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		return pedidoRepo.save(pedido);
	}

	@Override
	public List<Pedido> listarPedidos() {
		// TODO Auto-generated method stub
		return pedidoRepo.findAll();
	}

	@Override
	public Pedido obtenerPedidoPorId(Integer id) {
		// TODO Auto-generated method stub
		return pedidoRepo.findById(id).get();
	}

	@Override
	public void eliminarPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		pedidoRepo.delete(pedido);
	}

	@Override
	public void eliminarPedidoPorId(Integer id) {
		// TODO Auto-generated method stub
		pedidoRepo.delete(pedidoRepo.findById(id).get());
	}

}
