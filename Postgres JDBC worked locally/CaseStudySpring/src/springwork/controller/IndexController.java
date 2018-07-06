package springwork.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.UserDAO;
import models.User;

@Controller
public class IndexController {

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	// testdata - TamD@yahoo.com  adminp, lee@gmail.com  leep
	@PostMapping("/validateLogin")
	public ModelAndView user_info(
			@RequestParam("email") String email,
			@RequestParam("password") String password) throws IOException, SQLException {
		
		String returnPage = "login";
		ModelAndView mav = null;
		
		User u = null; u = new User();
		UserDAO uDAO = null; uDAO = new UserDAO();
		u = uDAO.isValidUser(email, password);
		
		if(u==null) returnPage = "login";
		else {
			if(u.getUser_type().equals("Admin")) returnPage = "adminDetailList";
			else returnPage = "userDetailList";
		}
		
		mav = new ModelAndView(returnPage);  
		mav.addObject("user", u);           // the returnPage will make the user a session variable
		mav.addObject("city", "all");           
		mav.addObject("state", "all");           
		mav.addObject("order", "date");           
		return mav;
	}
		
	@GetMapping("/userDetailList")
	public ModelAndView userDetailList(
			@RequestParam("city") String city,
			@RequestParam("state") String state,
			@RequestParam("order") String order) {
		ModelAndView mav = new ModelAndView("userDetailList");
		mav.addObject("city", city);           
		mav.addObject("state", state);           
		mav.addObject("order", order);  
		return mav;
	}
}
