package br.com.cwi.racha.factories;


import br.com.cwi.racha.domain.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsuarioSetListFactory {

    public static Set<Usuario> listar(){
        Set<Usuario> lista = new HashSet<>();

        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());

        return lista;
    }

    public static List<Usuario> listarList(){
        List<Usuario> lista = new ArrayList<>();

        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());
        lista.add(UsuarioFactory.getUsuario());

        return lista;
    }

}
