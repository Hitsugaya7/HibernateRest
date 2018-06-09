package com.example.services;


import com.example.dao.ProjectDao;
import com.example.model.Employee;
import com.example.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;


    public Project getProjectById(int id) {
        Project providerById = projectDao.getProviderById(id);
        return providerById;
    }


    public List<Project> allProjects() {
        List<Project> allProjects = projectDao.getAllProjects();
        return allProjects;
    }

    public List<Project> addProjects(String name) {
        Project project = new Project(name);
        projectDao.addProject(project);
        List<Project> allProjects = projectDao.getAllProjects();
        return allProjects;
    }

    public List<Employee> in(int id) {
        List<Employee> in = projectDao.in(id);
        return in;
    }

    public List<Project> delete(String id) {
        List<Project> delete = projectDao.deleteProject(id);
        return delete;
    }

    public Project put(String id) {
        Project delete = projectDao.putProject(id);
        return delete;
    }
}
