package com.example.controller;

import com.example.model.Employee;
import com.example.model.Project;
import com.example.services.EmployeeService;
import com.example.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final String ID="/{id}";

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = ID,method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable final int id, Model model) {
        Employee projectById = employeeService.getEmployeeById(id);
        model.addAttribute("employee",projectById);
        return "employee";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String allEmployees(Model model) {
        List<Employee> employees = employeeService.allEmployees();
        model.addAttribute("employees",employees);
        return "employees";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addEmployees(@RequestParam(required = false) String text, Model model) {
        if(text!=null&&!text.equals("")){
            List<Employee> employees = employeeService.addEmployees(text);
            model.addAttribute("employees",employees);
        }
        else {
            List<Employee> employees = employeeService.allEmployees();
            model.addAttribute("employees",employees);
        }
        return "employees";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestBody String id, Model model) {
        List<Employee> employees = employeeService.delete(id);
        model.addAttribute("employees",employees);
        return "employees";
    }
}
