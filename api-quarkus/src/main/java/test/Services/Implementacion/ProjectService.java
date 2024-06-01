package test.Services.Implementacion;

import jakarta.inject.Inject;
import model.Project;
import jakarta.enterprise.context.ApplicationScoped;
import test.Implementacion.IProjectRepo;
import test.Services.IProjectService;

import java.util.List;

@ApplicationScoped
public class ProjectService implements IProjectService {

    @Inject
    IProjectRepo projectRepository;


    public Project getProject(){
        return projectRepository.getProject();
    }

    public List<Project> getProjects(){
        return projectRepository.getProjects();
    }

}

