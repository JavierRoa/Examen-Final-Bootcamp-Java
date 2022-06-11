package cl.aiep.practicafinal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity // Anotación que define esta clase como entidad
@Getter // Anotación de Lombok que crea los Getters
@Setter // Anotación de Lombok que crea los Setters
@NoArgsConstructor // Anotación de Lombok que crea un constructor vacío
public class Admin {

    @Id // Anotación que define este atributo como PK (por defecto es único en base de datos).
    @Column(name = "id") // Anotación que crea una columna en base de datos para este atributo.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotación para el autoincremental.
    private Long id;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(unique = true, nullable = false) //"unique = true" hace que el atributo sea único en la base de datos y no se pueda repetir
    private String usuario;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String password;
}
