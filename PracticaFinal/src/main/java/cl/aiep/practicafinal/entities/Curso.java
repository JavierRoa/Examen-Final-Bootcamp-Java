package cl.aiep.practicafinal.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity // Anotación que define esta clase como entidad
@Getter // Anotación de Lombok que crea los Getters
@Setter // Anotación de Lombok que crea los Setters
@NoArgsConstructor // Anotación de Lombok que crea un constructor vacío
public class Curso {

    @Id // Anotación que define este atributo como PK (por defecto es único en base de datos).
    @Column(name = "id") // Anotación que crea una columna en base de datos para este atributo.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotación para el autoincremental.
    private Long id;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(name="descripción", nullable = false)
    private String descripcion;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(name="contenidos", nullable = false)
    private String contenidos;

    @NotNull(message = "Este campo es obligatorio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha_inicio", nullable = false)
    private Date fechaInicio;

    @NotNull(message = "Este campo es obligatorio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha_termino", nullable = false)
    private Date fechaTermino;

    @Min(message = "Debe ingresar un mínimo de 1 cupo", value = 1)
    @NotNull(message = "Este campo es obligatorio")
    @Column(name="cupos_disponibles", nullable = false)
    private Integer cuposDisponibles;

    @Min(message = "No puede asignar una cantidad negativa de cupos", value = 0)
    @NotNull(message = "Este campo es obligatorio")
    @Column(name="cupos_restantes", nullable = false)
    private Integer cuposRestantes;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(name="imagenRef", nullable = false)
    private String imagenRef;

    @OneToMany(mappedBy = "curso")
    private List<Usuario> usuario;
}
