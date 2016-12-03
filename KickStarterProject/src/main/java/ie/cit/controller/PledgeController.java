package ie.cit.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.entity.User;
import ie.cit.repository.PledgeRepository;
import ie.cit.repository.ProjectRepository;
import ie.cit.repository.UserRepository;
import ie.cit.service.PledgeService;

@Controller
@RequestMapping("/project/{id}/pledge")
public class PledgeController  extends WebMvcConfigurerAdapter{ 

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PledgeService pledgeService;
	
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("pledge");
    }

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id") long id) {
		
		Project proj = projectRepository.findById(id);
		
		System.out.println("PledgeController ID: " + proj.getId());
		System.out.println("PledgeController getPrincipal() : " + getPrincipal());
		
		model.addAttribute("project", proj);
		Calendar today = Calendar.getInstance();
		Long milis = proj.getDeadLine().getTime().getTime() - today.getTime().getTime();
		int days = (int)(milis/(1000*60*60*24));
		model.addAttribute("daysToGo", days);
		
		return "pledge/pledgeView";
	}
	
	   @PostMapping("/submit")
	   public String checkUserInfo(@Valid Pledge pledge, BindingResult bindingResult, @PathVariable("id") long id) {
		   
		   if(getPrincipal().equals("anonymousUser")){
			   System.out.println("pledge amount: " + pledge.getAmount());
			   return "redirect:/project/"+ id + "/pledge/?error";
		   }
		   else{
/*			   Project project = projectRepository.findById(id);
			   User user = userRepository.findByUsername( getPrincipal());*/
			   return "redirect:/project/"+ id + "/";
		   }
/*		   User user = userRepository.findByUsername( getPrincipal());
		   pledgeService.add(pledge, user);
		   		return "redirect:/project/"+ id + "/pledge/";*/
		   		///project/__${project.getId()}__/pledge/
/*
	       //if ((user.getUsername().equals(null)) ||(user.getPassword().equals(null))) {
		   //if ((user.getUsername() == null) ||(user.getPassword() == null)) {
		   pledgeRepository.save(pledge);
		   
		   if (bindingResult.hasErrors()) {
	           return "redirect:/user/register?error";
	       }
	       else{
	    	   userService.add(user);*/
	    	   //return "redirect:/login";
	       }
	
	
	private String getPrincipal(){
	       String userName = null;
	       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	       if (principal instanceof UserDetails) {
	           userName = ((UserDetails)principal).getUsername();
	       } else {
	           userName = principal.toString();
	       }
	       return userName;
	   }
}
