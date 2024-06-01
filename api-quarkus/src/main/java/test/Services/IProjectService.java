package test.Services;

import model.Project;

import java.util.List;

public interface IProjectService {
//    public void save(Project pro);
    public Project getProject();
    public List<Project> getProjects();
}
