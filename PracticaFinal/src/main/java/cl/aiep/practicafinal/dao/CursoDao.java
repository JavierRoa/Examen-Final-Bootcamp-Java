package cl.aiep.practicafinal.dao;

import cl.aiep.practicafinal.entities.Curso;

import java.util.List;

public interface CursoDao {

    Curso agregar(Curso curso);

    Curso actualizar(Curso curso);
    void eliminar(Long id);
    Curso buscar (Long id);
    List<Curso> listar();
}
