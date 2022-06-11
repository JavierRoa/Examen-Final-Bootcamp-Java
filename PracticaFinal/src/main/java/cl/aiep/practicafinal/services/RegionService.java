package cl.aiep.practicafinal.services;

import cl.aiep.practicafinal.dao.RegionDao;
import cl.aiep.practicafinal.entities.Region;
import cl.aiep.practicafinal.repositories.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService implements RegionDao {

    @Autowired
    private RegionRepo repo;

    @Override
    public Region buscar(Long id) {
        return repo.getById(id);
    }

    @Override
    public List<Region> listar() {
        return repo.findAll();
    }
}
