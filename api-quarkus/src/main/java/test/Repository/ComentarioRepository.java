package test.Repository;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Comentario;
import test.Implementacion.IComentarioRepo;

import java.util.List;

@ApplicationScoped
public class ComentarioRepository implements IComentarioRepo {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    //hacer que se guarde en formato txt el comentario
    public void saveComment(Comentario com) {
        persist(com);
    }



    public Comentario getComentario(){
        return find("id", 1).firstResult();
    }

    @Override
    public List<Comentario> getComentarios() {
        return listAll();
    }


}
