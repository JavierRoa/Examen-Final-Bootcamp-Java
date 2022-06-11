package cl.aiep.practicafinal.security;

import cl.aiep.practicafinal.entities.Admin;
import cl.aiep.practicafinal.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserSec implements UserDetails {

    /* Se crean objetos de cada tipo de usuario que puede ingresar a la página.
     */
    private Admin admin;
    private Usuario usuario;

    /*
    Constructor del tipo UserSec
     */
    public UserSec (Admin admin, Usuario usuario) {
        this.admin = admin;
        this.usuario = usuario;
    }

    /* Métodos accesadores a dichos objetos
     */
    public Admin getAdmin() {
        return admin;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    // Override a los métodos obtenidos de "UserDetails".

    /* Este método detecta si el usuario que ingresa es un usuario común o un admin, y le pide el password
    correspondiente.
     */
    @Override
    public String getPassword() {
        if( admin != null ) return admin.getPassword();
        if( usuario != null ) return usuario.getPassword();
        return null;
    }

    /* Este método detecta si el usuario ingresa con cuenta de admin o usuario, y le pide los datos
    correspondientes (En este caso, lo usamos solo para el "bienvenido" que habrá en la vista "Home", ya que
    en el "MyUserDetailsService", haremos que la página pida otros datos para logear.)
     */
    @Override
    public String getUsername() {
        if( admin != null ) return admin.getNombre();
        if( usuario != null ) return usuario.getNombre1() + " " + usuario.getApellido1();
        return null;
    }

    /* Este método da el rol o autorizacion a cada usuario ("ADMIN" o "USUARIO") dependiendo de con qué tipo
    de cuenta se logee.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if( admin != null ) return List.of(new SimpleGrantedAuthority("ADMIN"));
        if( usuario != null ) return List.of(new SimpleGrantedAuthority("USUARIO"));
        return null;
    }

    // De aquí para abajo, todos los override deben retornar True, ya que son configuraciones que no usamos.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
