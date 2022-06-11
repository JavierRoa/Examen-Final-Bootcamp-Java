package cl.aiep.practicafinal.dao;

import cl.aiep.practicafinal.entities.Admin;

public interface AdminDao {

    Admin agregar(Admin admin);
    void eliminar(Long id);
    Admin buscar (Long id);
}
