package test.Implementacion;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import model.Project;

import java.util.List;

public interface IProjectRepo extends PanacheRepository<Project> {

//    public void saveProject(Project pro);
    public Project getProject();
    List<Project> getProjects();
}
