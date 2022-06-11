package cl.aiep.practicafinal.services;

import cl.aiep.practicafinal.entities.Admin;
import cl.aiep.practicafinal.entities.Usuario;
import cl.aiep.practicafinal.repositories.AdminRepo;
import cl.aiep.practicafinal.repositories.UsuarioRepo;
import cl.aiep.practicafinal.security.UserSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
/* En el service sobreescribimos los métodos establecidos en los Daos. En este caso en particular, sobreescribo
un método tomado de UserDetailsServices (se toma de spring security), para sobreescribir los datos con los que
los usuarios deben acceder en el login, para ingresar con credenciales a la página.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    // Autowired para acceder a los métodos creados por nosotros en los repositorios correspondientes
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    /* En este Override, creo objetos de tipo "optional", y uso los métodos "optional" que creé de los repositorios,
    para definir si la persona que entró es admin o usuario normal, y ya que el "optional" permite enviar 2
    atributos, dejando uno como nulo y nosotros usando el otro, creo un objeto de tipo "UserSec" en el cual,
    dependiendo de quien logeó, se le solicita el dato correspondiente como usuario (Al admin se le pide el
    atributo "usuario", y al usuario común se le solicita el "rut").
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Creacion del objeto optional, al cual asignamos el método creado en el repo
        Optional<Admin> adminOptional = adminRepo.findByUsuario(username);
        // Si el que logea es admin
        if(adminOptional.isPresent()) {
            // Retorna un objeto de tipo UserSec, que busca el usuario del admin para el logeo.
            return new UserSec(adminOptional.get(), null);
        }
        else {
            // Creacion del objeto optional, al cual asignamos el método creado en el repo
            Optional<Usuario> usuarioOptional = usuarioRepo.findByRut(username);
            // Si el que logea es usuario común
            if(usuarioOptional.isPresent()) {
                // Retorna un objeto de tipo UserSec, que busca el rut de usuario común para el logeo.
                return new UserSec(null, usuarioOptional.get());
            }
        }
        // Excepción que ocurre si no se ingresa con datos correctos.
        throw new UsernameNotFoundException("Usuario no encontrado.");
    }
}
