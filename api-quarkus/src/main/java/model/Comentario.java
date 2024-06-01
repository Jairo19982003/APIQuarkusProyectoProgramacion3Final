package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "comentarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue
    private Integer comentario_id;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "fecha_comentario")
    private LocalDateTime fecha = LocalDateTime.now();
    @Column(name = "nombre_usuario")
    private String nombreUser;
    @Column(name = "email_usuario")
    private String emailUser;
    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;


}

