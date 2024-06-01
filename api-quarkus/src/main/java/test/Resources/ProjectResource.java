package test.Resources;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import model.Project;
import test.Services.Implementacion.ProjectService;

import java.util.List;

@Path("/Project")
public class ProjectResource {
    @Inject
    ProjectService project;

    @GET
    @Path("/project")
    //sirve para obtener un proyecto
    public Project getProject() {
        System.out.println("Project: " + project.getProject());
        return project.getProject();
    }

    @GET
    public List<Project> getProjects(){
        return project.getProjects();
    }
}

