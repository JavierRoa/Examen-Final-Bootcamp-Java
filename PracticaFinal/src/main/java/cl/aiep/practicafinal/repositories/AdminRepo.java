package cl.aiep.practicafinal.repositories;

import cl.aiep.practicafinal.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/* Un repositorio es una interfaz que gestiona el acceso a los datos, a través del uso de distintos métodos
predefinidos, normalmente tomados del "JpaRepository", además de métodos creados por nosotros mismos
(como en este caso).
 */
public interface AdminRepo extends JpaRepository<Admin, Long> {

    /* Un Optional es un tipo de variable que puede almacenar dos valores. El primer valor es un Objeto
     que nosotros necesitamos utilizar
     */
    Optional<Admin> findByUsuario(String usuario);
}
