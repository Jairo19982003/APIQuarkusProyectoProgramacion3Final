package test.Implementacion;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import model.Comentario;

import java.util.List;

public interface IComentarioRepo extends PanacheRepository<Comentario> {
    public void saveComment(Comentario com);
    public Comentario getComentario();
    List<Comentario> getComentarios();
}
