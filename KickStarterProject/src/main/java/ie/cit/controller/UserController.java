package ie.cit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.cit.config.SecurityUser;
import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.entity.User;
import ie.cit.repository.PledgeRepository;
import ie.cit.repository.ProjectRepository;
import ie.cit.repository.UserRepository;
import ie.cit.service.CustomUserDetails;
import ie.cit.service.PledgeService;
import ie.cit.service.ProjectService;
import ie.cit.service.UserService;

@Controller
@SessionAttributes("username")
@RequestMapping("/user")
public class UserController extends WebMvcConfigurerAdapter{ 
	


	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	PledgeRepository pledgeRepository;
	
	@Autowired
	PledgeService pledgeService;
	
	private final String LOGIN_PAGE = "login";
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/login").setViewName("login");
    	registry.addViewController("/").setViewName("home");
    	//registry.addViewController("/login?error").setViewName("login?error");
    	//registry.addViewController("/register?error").setViewName("register?error");
    	registry.addViewController("/register").setViewName("register");
    	registry.addViewController("/profile").setViewName("profile");
    }
	

   @RequestMapping(value = "/login", method = RequestMethod.GET)
	private ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {
	   
	   	//request.getSession().setAttribute("username", username);
		ModelAndView model = new ModelAndView();

	

				
		if (error != null) {
			model.addObject("error", "error");
		}
		if (logout != null) {
			model.addObject("logout", "logout");
		}
		
		model.setViewName(LOGIN_PAGE);
		
		return model;
	}
   
   @GetMapping("/register")
   public String showProjForm(User user) {
       return "user/register";
   }

   @PostMapping("/register")
   public String checkUserInfo(@Valid User user, BindingResult bindingResult) {

       //if ((user.getUsername().equals(null)) ||(user.getPassword().equals(null))) {
	   //if ((user.getUsername() == null) ||(user.getPassword() == null)) {
	   if (bindingResult.hasErrors()) {
           return "redirect:/user/register?error";
       }
       else{
    	   userService.add(user);
    	   return "redirect:/login";
       }
   }
   
   @RequestMapping("/profile")
	public String list(Model model, HttpServletRequest request)  {
	   
	   	
		model.addAttribute("username", "Welcome " + getPrincipal() + "!");
		System.out.println("request.getSession()" +  getPrincipal());
		User user = userService.findUserByUsername( getPrincipal());
		

		Iterable<Project> a = user.getProjects();
		List<Project> projects = new ArrayList<Project>();
		a.forEach(projects::add);
		model.addAttribute("project", projects);
		//System.out.println("" + projects.get(0));
		
		
		Iterable<Pledge> pl = user.getPledges();
		List<Pledge> pledges = new ArrayList<Pledge>();
		pl.forEach(pledges::add);
		model.addAttribute("pledge", pledges);
		
		
		return "user/profileDetails";
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

   
   
   @GetMapping("/newProj")
   public String showProjForm(Project project) {
	   return "project/projForm";
   }
   
   
   @RequestMapping(value = "/cancelpledge/{id}", method=RequestMethod.GET)
	public String cancalPledge(@PathVariable("id") long id) {
		pledgeRepository.delete(id);
		System.out.println("Pledge Deleted");
		return "home";
	}
   

	   
/*
    //////WORKS BUT NOT PROPERLY

    @GetMapping("/login")
    public String loginForm(User user) {
        return "/login";
    }
    
    @PostMapping("/login")
    public String checkUserInfo(User user) {
    	
    	User user1 = userRepository.findByUsername(user.getUsername());
    	if (user1 == null){
    		//return "/login?error";
    		System.out.print("User not found");
    		 return "redirect:/login?error";
    	}
    	
    	
    	if(user1.getPassword().equals(user.getPassword())) {
    		System.out.print("password  matching  ");
    		return "redirect:/";
    		
    	}
    	
    	if(user1.getPassword() != user.getPassword()) {
    		System.out.print("password not matching user ");
    		return "/login?error";
    		
    	}

    	else{
    		System.out.print("Login fail, unknown reason ");
    		 return "redirect:/login?error";
    	}  
    }*/
}