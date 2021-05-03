package com.yachay.repositories;

import java.util.List;

import com.yachay.entities.Curso;
import com.yachay.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   //Todo add exception
   Optional<Usuario> findById(Long usuarioId);
   Optional<Usuario> findByCorreoAndContraseña(String correo, String contraseña);
   Optional<Usuario> findUserbyContrasenia(String correo);
   List<Usuario> findAllByCurso(String var1);
}
