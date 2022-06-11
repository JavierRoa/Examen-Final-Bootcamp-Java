package cl.aiep.practicafinal.dao;

import cl.aiep.practicafinal.entities.Region;

import java.util.List;

public interface RegionDao {

    Region buscar(Long id);
    List<Region> listar();
}
