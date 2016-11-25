package ie.cit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.cit.entity.Project;
import ie.cit.repository.ProjectRepository;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepository;

	@RequestMapping("/")
	public String list(Model model) {
		
		Iterable<Project> a= projectRepository.findAll();
		List<Project> projects = new ArrayList<Project>();
		a.forEach(projects::add);
		model.addAttribute("project", projects);
		System.out.println("" + projects.get(0));
		
		return "project/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id") long id) {
		
		Project proj = projectRepository.findById(id);
		
		model.addAttribute("project", proj);
		
		return "project/view";
	}
	
}
