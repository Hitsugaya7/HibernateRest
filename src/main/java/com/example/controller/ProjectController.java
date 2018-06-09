package com.example.controller;

import java.util.List;

import com.example.model.Employee;
import com.example.model.Project;
import com.example.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

	private final String ID="/{id}";


	@Autowired
    ProjectService projectService;

    @RequestMapping(value = ID,method = RequestMethod.GET)
	public String getProjectById(@PathVariable final int id,Model model) {
        Project projectById = projectService.getProjectById(id);
        model.addAttribute("project",projectById);
        model.addAttribute("ins",projectService.in(id));
        return "project";
	}

    @RequestMapping(method = RequestMethod.GET)
	public String allProjects(Model model) {
        List<Project> projects = projectService.allProjects();
        model.addAttribute("projects",projects);
		return "main";
	}

    @RequestMapping(method = RequestMethod.POST)
    public String addProjects(@RequestParam(required = false) String text, Model model) {
        if(text!=null&&!text.equals("")){
            List<Project> projects = projectService.addProjects(text);
            model.addAttribute("projects",projects);
        }else {
            List<Project> projects = projectService.allProjects();
            model.addAttribute("projects",projects);
        }
        return "main";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteProjects(@RequestBody String id, Model model) {
        List<Project> projects = projectService.delete(id);
        model.addAttribute("projects",projects);
        return "main";
    }


    @RequestMapping(method = RequestMethod.PUT)
    public String putProjects(@RequestBody String str, Model model) {
        Project projectById = projectService.put(str);
        model.addAttribute("project",projectById);
        model.addAttribute("ins",projectService.in(str.charAt(1)));
        return "project";
    }

}
