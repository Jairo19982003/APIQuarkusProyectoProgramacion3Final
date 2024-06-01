package test.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.Project;
import test.Implementacion.IProjectRepo;

import java.util.List;

@ApplicationScoped
public class ProjectRepository implements IProjectRepo {

     @Inject
     EntityManager em;

//     @Override
//     public void saveProject(Project pro) {
//          persist(pro);}

     public Project getProject(){
          return find("id", 1).firstResult();
     }

     @Override
     public List<Project> getProjects(){
          return listAll();
        }
}
