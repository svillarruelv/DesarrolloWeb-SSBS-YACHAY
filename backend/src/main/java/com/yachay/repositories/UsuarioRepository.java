package com.yachay.repositories;

import java.util.List;

import com.yachay.entities.Curso;
import com.yachay.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   //Todo add exception
   @Override
   Optional<Usuario> findById(Long usuarioId);
   Optional<Usuario> findByCorreoAndContraseña(String correo, String contraseña);
   //Optional<Usuario> findPasswordbyEmail(Long usuarioId);
   List<Usuario> findAllByCurso(String var1);

   //@Query("SELECT u FROM Usuario u WHERE u.rol =:Rol")
   //List<Usuario> findByRol(@Param("Rol") String Rol);
   List<Usuario> findByRolIs(String Rol);
}
