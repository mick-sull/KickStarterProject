package ie.cit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.repository.PledgeRepository;


/**
 * Handles requests for the application home page.
 * 
 * This can be viewed by anyone to list all projects. 
 * But if you want to create a project or fund one then 
 * you need to login ??
 */
@Controller
@RequestMapping("/")
public class HomeController extends WebMvcConfigurerAdapter {
	
	
	
	@Autowired
	PledgeRepository pledgedRepository;
	

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/logout").setViewName("logout");
    }
	

/*	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {*/
	@RequestMapping("/")
	public String list(Model model) {	
		Iterable<Pledge> a= pledgedRepository.findFirst5ByOrderByPledgeDateDesc();
		List<Pledge> pledges = new ArrayList<Pledge>();
		a.forEach(pledges::add);
		model.addAttribute("pledge", pledges);
		for(int i = 0; i < pledges.size(); i++ ){
			System.out.println("Pledges ID " + pledges.get(i).getId() + " Username " + pledges.get(i).getUser().getUsername() + " Username " + pledges.get(i).getProject().getName() );
		}
		
		return "/list";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model){
		return "/logout";
	}
	
}
