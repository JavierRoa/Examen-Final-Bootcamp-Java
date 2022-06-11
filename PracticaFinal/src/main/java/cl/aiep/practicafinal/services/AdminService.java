package cl.aiep.practicafinal.services;

import cl.aiep.practicafinal.dao.AdminDao;
import cl.aiep.practicafinal.entities.Admin;
import cl.aiep.practicafinal.repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminDao {

    @Autowired
    private AdminRepo repo;

    @Override
    public Admin agregar(Admin admin) {
        return repo.save(admin);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Admin buscar(Long id) {
        return repo.getById(id);
    }
}
