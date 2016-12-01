package ie.cit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.cit.entity.Project;
import ie.cit.entity.User;
import ie.cit.repository.UserRepository;
import ie.cit.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends WebMvcConfigurerAdapter{ 
	

	@Autowired
	UserRepository userRepository;
	

	@Autowired
	UserService userService;
	
	
	private final String LOGIN_PAGE = "login";
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/login").setViewName("login");
    	registry.addViewController("/").setViewName("home");
    	//registry.addViewController("/login?error").setViewName("login?error");
    	//registry.addViewController("/register?error").setViewName("register?error");
    	registry.addViewController("/register").setViewName("register");
    }
	

   @RequestMapping(value = "/login", method = RequestMethod.GET)
	private ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
	   
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
    	   return "redirect:login";
       }
   }


/*	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerForm() {
		System.out.println("GET");
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		return model;
	}
*/
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