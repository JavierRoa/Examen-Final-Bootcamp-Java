package cl.aiep.practicafinal.dao;

import cl.aiep.practicafinal.entities.Usuario;

import java.util.List;

public interface UsuarioDao {

    Usuario agregar(Usuario usuario);
    void eliminar(Long id);
    Usuario buscar (Long id);
    Usuario actualizar (Usuario usuario);
    List<Usuario> listar();
}
