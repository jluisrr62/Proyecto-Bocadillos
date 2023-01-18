package com.example.demo.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Rol;
import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.dto.UsuarioDTO;
import com.example.demo.persistencia.UsuarioRepo;
import com.example.demo.servicio.interfaces.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepo usuarioRepo;
	
	private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<Usuario> usuario = usuarioRepo.findByUsername(username);
		
		if(usuario.isPresent()) {
			Usuario springUsermio = usuario.get();
			return springUsermio;
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

	@Override
	public Usuario insertarUsuario(Usuario user) {
		
		return usuarioRepo.save(user);
	}

	@Override
	public Usuario insertarUsuarioDTO(UsuarioDTO userDTO) {
	
		Usuario nuevoUsuario = new Usuario(
				userDTO.getNombre(), 
				userDTO.getUsername(), 
				passEncoder.encode(userDTO.getPassword())
				);
		
		nuevoUsuario.getRoles().add(new Rol("ROL_ADMIN"));
		
		return usuarioRepo.save(nuevoUsuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario obtenerUsuarioPorId(Integer id) {
		
		return usuarioRepo.findById(id).get();
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombre) {

		return usuarioRepo.findByUsername(nombre).get();
	}

	@Override
	public void eliminarUsuario(Usuario user) {

		usuarioRepo.delete(user);
	}

	@Override
	public void eliminarUsuarioPorId(Integer id) {
		
		usuarioRepo.delete(usuarioRepo.findById(id).get());
	}
	
	
	
}
