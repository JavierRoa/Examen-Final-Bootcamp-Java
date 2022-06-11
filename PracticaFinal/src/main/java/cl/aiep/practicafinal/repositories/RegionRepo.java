package cl.aiep.practicafinal.repositories;

import cl.aiep.practicafinal.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

/* Un repositorio es una interfaz que gestiona el acceso a los datos, a través del uso de distintos métodos
predefinidos, normalmente tomados del "JpaRepository", además de métodos creados por nosotros mismos.
 */
public interface RegionRepo extends JpaRepository<Region, Long> {
}
