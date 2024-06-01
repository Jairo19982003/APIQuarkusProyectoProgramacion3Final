package model;

import io.quarkus.arc.runtime.AdditionalBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Request {

    private String comentario;
    private Date fecha;
    private String nameUser;
    private String emailUser;
    private Project project;
    private String nameProject;
    private String urlRepo;
    private String admin;

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class UsuarioId {
//        private Integer usuario_id;
//        // Getter y setter
//    }
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class ProjectId {
//        private Integer proyecto_id;
//        // Getter y setter
//    }
}
