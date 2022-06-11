package cl.aiep.practicafinal.services;

import cl.aiep.practicafinal.dao.UsuarioDao;
import cl.aiep.practicafinal.entities.Usuario;
import cl.aiep.practicafinal.repositories.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UsuarioDao {

    @Autowired
    private PasswordEncoder codificador;
    @Autowired
    private UsuarioRepo repo;

    @Override
    public Usuario agregar(Usuario usuario) {
        // Se crea objeto de tipo String, para almacenar la codificacion de la contraseña
        String codificada = codificador.encode(usuario.getPassword());
        // Se establece el password como la versión codificada del mismo, para que se guarde en la DB
        usuario.setPassword(codificada);
        return repo.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Usuario buscar(Long id) {
        return repo.getById(id);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return repo.findAll();
    }
}
