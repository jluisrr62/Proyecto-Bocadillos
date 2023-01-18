package com.example.demo.persistencia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Pedido;
import com.example.demo.modelo.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
}
