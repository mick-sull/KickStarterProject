package ie.cit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.repository.PledgeRepository;
import ie.cit.repository.ProjectRepository;

@Controller
@RequestMapping("/admin1")
public class AdminController   extends WebMvcConfigurerAdapter{ 
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/").setViewName("/");
    	System.out.println("ADMIN CONTROLLER");
    }
	 
	@Autowired
	ProjectRepository projectRepository;
	
   @RequestMapping("/")
	public String list(Model model)  {
	   //Open projects
	   long open = 1;
	   long closed =  0;
		Iterable<Project> a= projectRepository.findBystatus(open);
		List<Project> projectOpen = new ArrayList<Project>();
		System.out.println("projectOpen array size: " + projectOpen.size());
		a.forEach(projectOpen::add);
		model.addAttribute("projectOpen", projectOpen);
		
	   //Closed projects
		Iterable<Project> aa = projectRepository.findBystatus(closed);
		List<Project> projectClosed = new ArrayList<Project>();
		a.forEach(projectClosed::add);
		model.addAttribute("projectClosed", projectClosed);
		/*for(int i = 0; i < pledges.size(); i++ ){
			System.out.println("Pledges ID " + pledges.get(i).getId() + " Username " + pledges.get(i).getUser().getUsername() + " Username " + pledges.get(i).getProject().getName() );
		}*/
		
		Iterable<Project> aaa = projectRepository.findAll();
		List<Project> projectAll = new ArrayList<Project>();
		a.forEach(projectAll::add);
		model.addAttribute("projectAll", projectAll);
		for(int i = 0; i < projectAll.size(); i++ ){
			System.out.println("projectAll " + projectAll.get(i).getId() + " : " + projectAll.get(i).getStatus());
		}
		
		return "admin/admin";
   }
   
}
