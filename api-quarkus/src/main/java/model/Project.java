package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "proyectos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private Integer proyecto_id;
    @Column(name = "url_repositorio")
    private String urlRepo;
    @Column(name = "url_imagen")
    private String urlImage;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "nombre")
    private String nameProject;
    @Column(name = "fecha_proyecto")
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin Admin;

}
