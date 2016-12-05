package ie.cit.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ie.cit.entity.Pledge;
import ie.cit.entity.Project;
import ie.cit.entity.User;
import ie.cit.repository.ProjectRepository;
import ie.cit.repository.UserRepository;
import ie.cit.service.ProjectService;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends WebMvcConfigurerAdapter{
	
	@Autowired
    ServletContext context;

	@Autowired
	ProjectService projectService;

	@Autowired
	UserRepository userRepository;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/projCreated").setViewName("projCreated");
	}

	@RequestMapping("/")
	public String list(Model model) {

		Iterable<Project> a= projectService.findAll();
		List<Project> projects = new ArrayList<Project>();
		a.forEach(projects::add);
		model.addAttribute("project", projects);

		return "project/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id") long id) {

		Project proj = projectService.findById(id);

		model.addAttribute("project", proj);
		Calendar today = Calendar.getInstance();
		Long milis = proj.getDeadLine().getTime().getTime() - today.getTime().getTime();
		int days = (int)(milis/(1000*60*60*24));
		model.addAttribute("daysToGo", days);

		return "project/view";
	}



	//(id , creation_date, dead_line, description, goal_amount, image_path, name, owner_id)

	@GetMapping("/newProj")
	public String showProjForm(Project project) {
		return "project/projForm";
	}

	@PostMapping("/newProj")
	public String checkProjectInfo(@RequestParam("projectImage") MultipartFile image, @Valid Project project, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "project/projForm";
		}
		else{
			//timestamp'2016-09-09 09:30:25 GMT', '2016-12-15','Description 1', 1200.00, '../images/money_tree.jpg
			
			InputStream inputStream = null;
	        OutputStream outputStream = null;
	        String fileName = image.getOriginalFilename();
	        
	        String relativeWebPath = "/resources/static/images";
	        String absoluteFilePath = context.getRealPath(relativeWebPath);
	        //File newFile = new File(absoluteFilePath + "/" + fileName);
	        String myPath = "C:/Users/Colmos/Documents/Cork Institute Of Technology/GitRepos/KickStarterProject/KickStarterProject/src/main/resources/static/images";
	        File newFile = new File(myPath + "/" + fileName);
	        
	        
	        System.out.println("Absolute path " + absoluteFilePath);

	        try {
	            inputStream = image.getInputStream();

	            if (!newFile.exists()) {
	                newFile.createNewFile();
	            }
	            outputStream = new FileOutputStream(newFile);
	            int read = 0;
	            byte[] bytes = new byte[1024];

	            while ((read = inputStream.read(bytes)) != -1) {
	                outputStream.write(bytes, 0, read);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			
			project.setOwner(userRepository.findByUsername(getPrincipal()));
			project.setImagePath("../images/" + image.getOriginalFilename());
			System.out.println("Image location:: " + project.getImagePath());

			Calendar cal = Calendar.getInstance();
			project.setCreationDate(cal);

			projectService.save(project);
			return "redirect:/project/projCreated";
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

	@RequestMapping("/projCreated")
	public String logout(Model model){
		return "project/projCreated";
	}
}
