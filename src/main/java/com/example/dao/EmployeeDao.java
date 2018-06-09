package com.example.dao;

import com.example.model.Employee;
import com.example.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Employee addEmployee(Employee employee) {
        getSession().save(employee);
        return employee;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> deleteEmployee(String id) {
        Employee employee = (Employee) getSession().createQuery("from Employee where id =" + id).uniqueResult();
        getSession().delete(employee);
        return getSession().createQuery("from Employee").list();
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployees() {
        return getSession().createQuery("from Employee").list();
    }

    public Employee getEmployeeById(int id) {
        return (Employee) sessionFactory.getCurrentSession().createQuery("from Employee where id = " + id).uniqueResult();
    }
}
