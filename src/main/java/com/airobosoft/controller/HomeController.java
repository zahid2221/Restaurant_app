package com.airobosoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.airobosoft.model.Details;
import com.airobosoft.service.ServiceImp;
import com.airobosoft.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService service;
   
	@GetMapping("/")   
	public String index()
	{
		return "index";
	}
	
    @GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
    @GetMapping("/register")
	public String register()
	{
		return "register";
	 }
    @GetMapping("/addnew")
    public String addnew()
    {
    	return "addNew";
    }
 	
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute Details user, HttpSession session)
    {
    	System.out.println(user);
    	{
	        boolean f = service.checkEmail(user.getEmail()); 
	    	if(f)
	    	{ 
	    		session.setAttribute("msg","Email Already Exists");
	    		
	    		return "redirect:/login";
	    	}
	    	else
	    	{    	
		    	Details det=service.createUser(user);
		    	if(det!=null)
		    	{
		    		session.setAttribute("msg"," Registered Successfully");
		    		return "redirect:/login";
		    		
		    		
		    	}else
		    	{
		    		session.setAttribute("msg","Server Error");
		    			    	}
	    	 }
    	}
    	return "redirect:/register";
    }
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute Details user, HttpSession session)
    {
    	
        boolean f = service.checkEmail(user.getEmail()); 
    	if(f)
    	{
    		session.setAttribute("msg","Email Already Exists");
    	}
    	else
    	{    	
	    	Details det=service.createUser(user);
	    	if(det!=null)
	    	{
	    		session.setAttribute("msg"," Added Successfully");
	    	}else
	    	{
	    		session.setAttribute("msg","No Data Error");
	    		return "redirect:/addnew";
	    	}
    	}
    	return "redirect:/addnew";
    }
    
    @PostMapping("/UserLogin")
    public String UserLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
       String email=request.getParameter("email");
       String password= request.getParameter("password");
      
	if(email!=null && !email.isEmpty() && service.checkEmail(email) && service.checKpassword(email,password))
		{
			return "redirect:/Details";
		} else {
			session.setAttribute("msg","Invalid Credentials");
			return "redirect:/login";
	    }
	}
    
    @GetMapping("/Details")
    public String viewAll(Model model)
    {
    	model.addAttribute("Details", service.displayall());
    	return "index";
    }
}