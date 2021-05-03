package com.yachay.services.impl;

import com.yachay.dtos.CreateUsuarioDto;
import com.yachay.dtos.LoginUsuarioDto;
import com.yachay.dtos.UsuarioDto;
import com.yachay.entities.Usuario;
import com.yachay.repositories.UsuarioRepository;
import com.yachay.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UsuarioDto findUsuarioById(Long usuarioId) {
        return modelMapper.map(getUsuarioEntityById(usuarioId), UsuarioDto.class);
    }

    @Override
    public UsuarioDto findContrasenia(Long usuarioId){
        return modelMapper.map(getContraseniaByCorreo(usuarioId), UsuarioDto.class);
    }

    @Transactional
    @Override
    public UsuarioDto registerUsuario(CreateUsuarioDto createUsuarioDto) {
        Usuario new_usuario = new Usuario();
        new_usuario.setNombre(createUsuarioDto.getNombre());
        new_usuario.setCorreo(createUsuarioDto.getCorreo());
        new_usuario.setContraseña(createUsuarioDto.getContraseña());
        new_usuario.setUniversidad(createUsuarioDto.getUniversidad());
        new_usuario.setGenero(createUsuarioDto.getGenero());
        new_usuario.setRol(createUsuarioDto.getRol());
        new_usuario.setFecha_de_nacimiento(toLocalDate(createUsuarioDto.getFecha_de_nacimiento()));

        try {
            new_usuario = usuarioRepository.save(new_usuario);
        } catch (Exception exception) {
            throw new Error("No se pudo crear usuario");// Todo handle error better
        }

        return modelMapper.map(getUsuarioEntityById(new_usuario.getId()), UsuarioDto.class);
    }

    @Override
    public UsuarioDto loginUsuario(LoginUsuarioDto loginUsuarioDto) {
        return modelMapper.map(getUsuarioEntity(loginUsuarioDto.getCorreo(), loginUsuarioDto.getContraseña()), UsuarioDto.class);
    }


    private LocalDate toLocalDate(String strFecha) {
       return LocalDate.parse(strFecha);
    }

    private Usuario getUsuarioEntityById(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElse(null);// Todo Add exception
    }

    private Usuario getUsuarioEntity(String correo, String contraseña) {
        return usuarioRepository.findByCorreoAndContraseña(correo, contraseña).orElse(null);// Todo Add exception
    }


    private Usuario getContraseniaByCorreo(Long usuarioId){
        return  usuarioRepository.findUserbyContrasenia(usuarioId).orElse(null);
    }
}
