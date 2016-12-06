package ie.cit.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.repository.PledgeRepository;
import ie.cit.repository.ProjectRepository;
import ie.cit.service.ProjectService;


@Controller
@RequestMapping("/admin1")
public class AdminController   extends WebMvcConfigurerAdapter{ 
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/").setViewName("/");
    	System.out.println("ADMIN CONTROLLER");
    }
	 
	@Autowired
	ProjectService projectService;
	
   @RequestMapping("/")
	public String list(Model model)  {
	   //Open projects
	   long open = 1;
	   long closed =  0;
	   
	   
		Iterable<Project> a= projectService.findByStatus(open);
		List<Project> projectOpen = new ArrayList<Project>();
		System.out.println("projectOpen array size: " + projectOpen.size());
		a.forEach(projectOpen::add);
		model.addAttribute("projectOpen", projectOpen);
/*		System.out.println("projectOpen array size2: " + projectOpen.size());
		for(int i = 0;i <projectOpen.size(); i++ ){
			System.out.println("Project projectOpen: " + projectOpen.get(i).getName() + " - Status: " +   projectOpen.get(i).getStatus());
			}*/

		Iterable<Project> aa= projectService.findByStatus(closed);
		List<Project> projectClosed = new ArrayList<Project>();
		System.out.println("projectClosed array size: " + projectOpen.size());
		aa.forEach(projectClosed::add);
		model.addAttribute("projectClosed", projectClosed);
/*		System.out.println("projectOpen array size3: " + projectClosed.size());
		for(int i = 0;i <projectClosed.size(); i++ ){
			System.out.println("Project Closed: " + projectClosed.get(i).getName() + " - Status: " +   projectClosed.get(i).getStatus());
			}*/
		
		
		return "admin/admin";
   }
   
   @RequestMapping(value = "/close/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id") long id) {

		Project proj = projectService.findById(id);
		proj.setStatus(0);//Set the project to closed
		projectService.save(proj);

/*		model.addAttribute("project", proj);
		Calendar today = Calendar.getInstance();
		Long milis = proj.getDeadLine().getTime().getTime() - today.getTime().getTime();
		int days = (int)(milis/(1000*60*60*24));
		model.addAttribute("daysToGo", days);*/

		return "redirect:/admin1/?closed";
	}
   
   
   
}
