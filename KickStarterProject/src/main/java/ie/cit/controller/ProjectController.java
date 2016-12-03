package ie.cit.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.cit.entity.Project;
import ie.cit.repository.ProjectRepository;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends WebMvcConfigurerAdapter{

	@Autowired
	ProjectRepository projectRepository;
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/projCreated").setViewName("projCreated");
    }

	@RequestMapping("/")
	public String list(Model model) {
		
		Iterable<Project> a= projectRepository.findAll();
		List<Project> projects = new ArrayList<Project>();
		a.forEach(projects::add);
		model.addAttribute("project", projects);
		
		return "project/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id") long id) {
		
		Project proj = projectRepository.findById(id);
		
		model.addAttribute("project", proj);
		Calendar today = Calendar.getInstance();
		Long milis = proj.getDeadLine().getTime().getTime() - today.getTime().getTime();
		int days = (int)(milis/(1000*60*60*24));
		model.addAttribute("daysToGo", days);
		
		return "project/view";
	}
	
	@GetMapping("/newProj")
    public String showProjForm(Project project) {
        return "project/projForm";
    }

    @PostMapping("/newProj")
    public String checkProjectInfo(@Valid Project project, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "project/projForm";
        }

        return "redirect:projCreated";
    }
	
}
