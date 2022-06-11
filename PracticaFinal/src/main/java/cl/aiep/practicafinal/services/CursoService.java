package cl.aiep.practicafinal.services;

import cl.aiep.practicafinal.dao.CursoDao;
import cl.aiep.practicafinal.entities.Curso;
import cl.aiep.practicafinal.repositories.CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements CursoDao {

    @Autowired
    private CursoRepo repo;


    @Override
    public Curso agregar(Curso curso) {
        return repo.save(curso);
    }

    @Override
    public Curso actualizar(Curso curso) {
        return repo.save(curso);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Curso buscar(Long id) {
        return repo.getById(id);
    }

    @Override
    public List<Curso> listar() {
        return repo.findAll();
    }
}
