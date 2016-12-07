package ie.cit.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


	double totalPledged = 0;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("pledge");
	}


	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String view(Model model, @PathVariable("id") long id) {

		Project proj = projectRepository.findById(id);

		if(!getPrincipal().equals("anonymousUser")){//If the user is logged in
			User user = userRepository.findByUsername( getPrincipal());
			totalPledged = pledgeService.getTotalAmountOfPledges(user);
			model.addAttribute("creditRemaining", (user.getCreditLimit() - totalPledged));
			//return "redirect:/project/"+ id + "/pledge/?error";
		}



		model.addAttribute("project", proj);
		/*		Calendar today = Calendar.getInstance();
		Long milis = proj.getDeadLine().getTime().getTime() - today.getTime().getTime();
		int days = (int)(milis/(1000*60*60*24));
		model.addAttribute("daysToGo", days);*/

		return "pledge/pledgeView";
	}
	
	@GetMapping("/submit")
	public String showProjForm(Project project) {
		return "pledge/pledgeView";
	}

	@PostMapping("/submit")
	public String checkUserInfo(@Valid Pledge pledge, BindingResult bindingResult, RedirectAttributes attr, @PathVariable("id") long id) {

		User user = userRepository.findByUsername( getPrincipal());
		Project project = projectRepository.findById(id);

		if (bindingResult.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.register", bindingResult);
		    attr.addFlashAttribute("pledge", pledge);
			return "redirect:/project/"+ id + "/pledge/";
		}
		else if(getPrincipal().equals("anonymousUser")){
			System.out.println("pledge amount: " + pledge.getAmount());
			return "redirect:/project/"+ id + "/pledge/?error";
		}
		else if (user.getId() == project.getOwner().getId()){//if the pledge user is the owner of the project
			return "redirect:/project/?pledgeUnsuccessful";
		}

		else if (pledge.getAmount() > user.getCreditLimit() - totalPledged ){
			return "redirect:/project/"+ id + "/pledge/?errorCreditLimit";			   
		}

		else{
			pledgeService.add(pledge, user, project);
			//return "redirect:/project/"+ id + "/";
			return "redirect:/project/?pledgeSuccessful";
		}
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
