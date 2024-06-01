package test.Services.Implementacion;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import model.Comentario;
import model.Request;
import test.Repository.ComentarioRepository;
import test.Resources.PaginatedReponse;
import test.Services.IComentarioService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ComentarioService implements IComentarioService {

        @Inject
        ComentarioRepository comentarioRepository;

        @Override
        public void save(Comentario com){
            comentarioRepository.saveComment(com);
        }


        public Comentario getComentario(){
            return comentarioRepository.getComentario();
        }

        public List<Comentario> getComentarios(){
            return comentarioRepository.getComentarios();
        }


        public PaginatedReponse<Comentario> paginated (int page){
            Page p = new Page(page -1, 10);
            var query = comentarioRepository.findAll().page(p);
            return new PaginatedReponse<>(query);
        }

//necesito cambio
        public void saveFile (Comentario comentario){
            // Nombre del archivo con la fecha actual
            String filename = LocalDate.now() + "_comentarios.csv";

            // Verificar si el archivo ya existe
            File file = new File(filename);
            boolean fileExists = file.exists();

            try (FileWriter writer = new FileWriter(filename, true)) { // "true" indica modo de append (añadir)
                if (!fileExists) { // Si el archivo no existe, agregar encabezados
                    writer.write("Comentario,Fecha, nombre usuario, email usuario, Proyecto\n");
                }
                // Escribir el nuevo comentario
                writer.write(comentario.getComentario() + "," + comentario.getFecha() + "," + comentario.getNombreUser() + "," + comentario.getEmailUser() + "," + comentario.getProject().getProyecto_id() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
