package com.example.demo.Service;

import com.example.demo.Entity.Usuario;

public interface UsuarioService {
    //public abstract boolean autenticarUsuario(String correo, String password);
    public abstract Usuario registrarUsuario(Usuario usuario);
    // public abstract Usuario obtenerDetallesUsuario(String correo, String password);
    public abstract boolean buscarExisteUsuario(String correo, String password);
    public abstract boolean esAdmin(String correo);

}
