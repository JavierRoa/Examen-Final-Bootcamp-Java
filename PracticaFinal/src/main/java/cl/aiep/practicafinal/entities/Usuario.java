package cl.aiep.practicafinal.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity // Anotación que define esta clase como entidad
@Getter // Anotación de Lombok que crea los Getters
@Setter // Anotación de Lombok que crea los Setters
@NoArgsConstructor // Anotación de Lombok que crea un constructor vacío
public class Usuario {

    @Id // Anotación que define este atributo como PK (por defecto es único en base de datos).
    @Column(name = "id") // Anotación que crea una columna en base de datos para este atributo.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotación para el autoincremental.
    private Long id;

    @Pattern(message = "RUT sin punto y con guión", regexp = "^(\\d{7}-(\\d|[K-k])|\\d{8}-(\\d|[K-k]))$")
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(unique = true, nullable = false)
    private String rut;

    @Pattern(message = "Ingrese solo letras, sin tildes o caracteres especiales", regexp = "^([A-Z][a-z]+)$")
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(name="primer_nombre", nullable = false)
    private String nombre1;

    @Pattern(message = "Ingrese solo letras, sin tildes o caracteres especiales", regexp = "^([A-Z][a-z]+)$")
    @Column(name="segundo_nombre")
    private String nombre2;

    @Pattern(message = "Ingrese solo letras, sin tildes o caracteres especiales", regexp = "^([A-Z][a-z]+)$")
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(name="primer_apellido", nullable = false)
    private String apellido1;

    @Pattern(message = "Ingrese solo letras, sin tildes o caracteres especiales", regexp = "^([A-Z][a-z]+)$")
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(name="segundo_apellido", nullable = false)
    private String apellido2;

    @Range(message = "Ingrese un número de 9 dígitos, sin caracteres especiales", min = 100000000, max = 999999999)
    @NotNull(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private Integer telefono;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String direccion;

    @Email(message = "Ingrese una dirección de correo válida", regexp = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b")
    @NotEmpty(message = "Este campo es obligatorio")
    @Column(unique = true, nullable = false)
    private String email;

    @Range(message = "Debe tener entre 18 y 100 años.", min = 18, max = 100)
    @NotNull(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private Integer edad;

    @NotEmpty(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY) // Anotación para FK (Muchos usuarios, pueden tener una región)
    @JoinColumn(nullable = false) // Anotación que define una columna conectada con otra entidad para el FK.
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Curso curso;
}