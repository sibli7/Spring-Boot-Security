package spring_security.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import spring_security.security.config.CustomAuthSuccessHandler;
import spring_security.security.entity.User;
import spring_security.security.repository.UserRepo;
import spring_security.security.service.UserService;


@Controller
public class HomeController {

	@Autowired
	public CustomAuthSuccessHandler successHandler;


	
    
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	
	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByEmail(email);
			m.addAttribute("user", user);
		}

	}

	

	@GetMapping("/")
	public String index() {
		return "index";
	}
	

	



	



	@GetMapping("/product-details")
	public String productDetails() {
		return "pr_details";
	}

	@GetMapping("register")
	public String register() {
		return "register";
	}

	@GetMapping("signin")
	public String login(Principal principal, Mode model) {
		 
		if (principal != null) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			String role = user.getRole();

			System.out.println(role);	
			System.out.println("ROLE_ADMIN");

			 if(user.getRole()=="ROLE_ADMIN"){
			 	return "redirect:/admin/profile";
			 }
			 return "redirect:/";
		}else{
			return "login";
		}

	
	}

	/*
	 * @GetMapping("/user/profile") public String profile(Principal p, Model m) {
	 * String email = p.getName(); User user = userRepo.findByEmail(email);
	 * m.addAttribute("user", user); return "profile"; }
	 * 
	 * @GetMapping("/user/home") public String home() { return "home"; }
	 */

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m, HttpServletRequest request) {

		// System.out.println(user);
		String url = request.getRequestURL().toString();

		// System.out.println(url); http://localhost:8080/saveUser
		url = url.replace(request.getServletPath(), "");
		// System.out.println(url);
		// //http://localhost:8080/verify?code=3453sdfsdcsadcscd

		User u = userService.saveUser(user, url);

		if (u != null) { // System.out.println("save sucess");
			session.setAttribute("msg", "Register successfully");

		} else { // System.out.println("error in server");
			session.setAttribute("msg", "Something wrong server");
		}

		return "redirect:/register";
	}

	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code, Model m) {
		boolean f = userService.verifyAccount(code);

		if (f) {
			m.addAttribute("msg", "Sucessfully your account is verified");
		} else {
			m.addAttribute("msg", "may be your vefication code is incorrect or already veified ");
		}

		return "message";
	}
}
