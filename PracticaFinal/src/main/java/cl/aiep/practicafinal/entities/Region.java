package cl.aiep.practicafinal.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity // Anotación que define esta clase como entidad
@Getter // Anotación de Lombok que crea los Getters
@Setter // Anotación de Lombok que crea los Setters
@NoArgsConstructor // Anotación de Lombok que crea un constructor vacío
public class Region {

    @Id // Anotación que define este atributo como PK (por defecto es único en base de datos).
    @Column(name = "id") // Anotación que crea una columna en base de datos para este atributo.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotación para el autoincremental.
    private Long id;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(unique=true, name="descripcion", nullable=false, length=70)
    private String descripcion;

    @OneToMany(mappedBy="region") // Anotación para la FK (Una región puede tener muchos usuarios)
    private List<Usuario> usuarios;
}