package online.jadg13.rifa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "participantes")
@Getter @Setter @ToString
public class Participante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name ="apellido", nullable = false)
    private String apellido;

    @Column(name = "cedula", nullable = false)
    private String cedula;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name ="celular", nullable = false)
    private String celular;

    public Participante() {
    }

    public Participante(String nombre, String apellido, String cedula, String ciudad, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.ciudad = ciudad;
        this.celular = celular;
    }



}
