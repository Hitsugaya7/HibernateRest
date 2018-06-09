package com.example.dao;

import com.example.model.Employee;
import com.example.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class ProjectDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Project addProject(Project project) {
        getSession().save(project);
        return project;
    }
    @SuppressWarnings("unchecked")
    public List<Project> deleteProject(String id) {
        Project project = (Project) getSession().createQuery("from Project where id =" + id).uniqueResult();
        getSession().delete(project);
        return getSession().createQuery("from Project").list();
    }

    @SuppressWarnings("unchecked")
    public List<Project> getAllProjects() {
        return getSession().createQuery("from Project").list();
    }

    public Project getProviderById(int id) {
        return (Project) sessionFactory.getCurrentSession().createQuery("from Project where id = " + id).uniqueResult();
    }

        @SuppressWarnings("unchecked")
    public List<Employee> in(int id) {
//            List list = sessionFactory.getCurrentSession().createQuery("select e from Employee e join fetch e.projects p where p.id != " + id).list();
            List<Project> projects = getSession().createQuery("from Project p where p.id = "+id).list();
            List<Employee> employees = getSession().createQuery("from Employee").list();
            for (int i = 0; i < projects.size(); i++) {
                employees.removeAll(projects.get(i).getEmployees());
            }
            return employees;
    }
    @SuppressWarnings("unchecked")
    public Project putProject(String id) {
        Project project = (Project) getSession().createQuery("from Project where id =" + id.charAt(0)).uniqueResult();
        Employee employee = (Employee) getSession().createQuery("from Employee where id =" + id.charAt(1)).uniqueResult();
        employee.add(project);
        getSession().saveOrUpdate(employee);
        System.out.println("hello");
        return (Project) sessionFactory.getCurrentSession().createQuery("from Project where id = " + id.charAt(0)).uniqueResult();
    }
}
