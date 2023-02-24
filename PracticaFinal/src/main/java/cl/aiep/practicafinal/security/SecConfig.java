package cl.aiep.practicafinal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/* El Security Config permite (como dice su nombre), configurar la seguridad de la página. A través de este
se asignan los permisos requeridos para accesar a cada URL, el encriptado de contraseñas, y la forma de
entrar y salir de sesión.
 */
@Configuration
@EnableWebSecurity
public class SecConfig {

    /* BCrypt es lo que permite usar passwords encriptados. La mayoría del código relacionado se maneja
    en otros archivos.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/css/**", "/js/**").permitAll()
                        .antMatchers("/", "/courses/**", "/courses2/**").permitAll()
                        .antMatchers("/course/**").hasAuthority("ADMIN")
                        .antMatchers("/user/register", "/login").anonymous()
                        .antMatchers("/cpannel", "/apply/**").hasAuthority("USUARIO")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")));
        return http.build();
    }
}
