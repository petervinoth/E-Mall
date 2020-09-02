package com.vir.Controller;

import javax.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.vir.Model.Staff;
import com.vir.Repository.MarketRepository;
import com.vir.Repository.StaffRepository;

@Controller
public class StaffController {
	
	@Autowired
	StaffRepository repository;
	
	@Autowired
	MarketRepository repo;
	
	BCryptPasswordEncoder Encoder=new BCryptPasswordEncoder(12);
	
	

	 @RequestMapping(value= {"/"}, method=RequestMethod.GET)
	 public ModelAndView home3() {
	  ModelAndView model = new ModelAndView();
	 
	  
	  model.setViewName("Main");
	  return model;
	 }
	
	 @RequestMapping(value= {"/useradd"}, method=RequestMethod.GET)
	 public ModelAndView home() {
	  ModelAndView model = new ModelAndView();
	 
	  System.out.print("save success1212");
	  model.setViewName("spaceowner/AddStaff");
	  return model;
	 }
	
	
	@RequestMapping(value= {"/useradd"},method=RequestMethod.POST)
	public ModelAndView  user(Staff staff,BindingResult bindingResult) {
		ModelAndView model=new ModelAndView();
		Staff exit=repository.findByUsername(staff.getUsername());
		if(exit != null) {
			   bindingResult.rejectValue("username", "error.user", "This username already exists!");
			  }
		else {
			staff.setPassword(Encoder.encode(staff.getPassword()));
			repository.save(staff);
			System.out.print("save success");
			model.addObject("msg","add successfully");
			model.setViewName("spaceowner/AddStaff");
			
		}
		return model;
	}
	
	@RequestMapping(value="/revenuReport",method=RequestMethod.GET)
	public ModelAndView view(ModelAndView model1,Model model) {
		 model.addAttribute("infos", repo.findAll()); 
		model1.setViewName("spaceowner/revenuReport");
		return model1;
		
	}
	
	@RequestMapping(value="/revenuReport1",method=RequestMethod.GET)
	public ModelAndView views(ModelAndView model1,Model model) {
		 model.addAttribute("infos", repo.findAll()); 
		model1.setViewName("marketstaff/revenuReport");
		return model1;
		
	}
	
	@RequestMapping(value="/utilityReport",method=RequestMethod.GET)
	public ModelAndView view1(ModelAndView model1,Model model) {
		 model.addAttribute("info", repo.findAll()); 
		model1.setViewName("spaceowner/utilityReport");
		return model1;
		
	}
	
	@RequestMapping(value="/utilityReport1",method=RequestMethod.GET)
	public ModelAndView viewal(ModelAndView model1,Model model) {
		 model.addAttribute("info", repo.findAll()); 
		model1.setViewName("marketstaff/utilityReport");
		return model1;
		
	}
	
	 @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
	 public ModelAndView main() {
	  ModelAndView model = new ModelAndView();
	  System.out.print("home work");
	  
	  model.setViewName("home");
	  return model;
	 }

	 @RequestMapping(value= {"/login"}, method=RequestMethod.GET)
	 public ModelAndView log() {
	  ModelAndView model = new ModelAndView();
	 
	  System.out.print("login work");
	  model.setViewName("login");
	  return model;
	 }
	 @RequestMapping(value= {"/admin"}, method=RequestMethod.GET)
	 public ModelAndView main1() {
	  ModelAndView model = new ModelAndView();
	  //System.out.print("home work");
	  
	  model.setViewName("spaceowner/dashboard");
	  return model;
	 }
	 
	 @RequestMapping(value= {"/staff"}, method=RequestMethod.GET)
	 public ModelAndView staff() {
	  ModelAndView model = new ModelAndView();
	  //System.out.print("home work");
	  
	  model.setViewName("marketstaff/dashboard");
	  return model;
	 }
	 
	 
	 @RequestMapping(value="/deletee/{id}",method=RequestMethod.GET)
	 public ModelAndView doDelete(@PathVariable("id") int id ,Model model) {
		ModelAndView model2=new ModelAndView();
		Staff book=repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		
			 	repository.delete(book);
	      // model.addAttribute("bloods", repo.findAll());
	         model2.setViewName("spaceowner/dismiss");
	         return model2;
	 
	 }
	 
	 @RequestMapping(value="/liststaff",method=RequestMethod.GET)
		public ModelAndView viewall(ModelAndView model1,Model model) {
			 model.addAttribute("staffs", repository.findAll()); 
			model1.setViewName("spaceowner/liststaff");
			return model1;
			
		}
	 
	 @RequestMapping(value= {"/complaint"}, method=RequestMethod.GET)
	 public ModelAndView complaint() {
	  ModelAndView model = new ModelAndView();
	 
	  
	  model.setViewName("spaceowner/complaint");
	  return model;
	 }
	 
	 @RequestMapping(value= {"/complaint1"}, method=RequestMethod.GET)
	 public ModelAndView complaint1() {
	  ModelAndView model = new ModelAndView();
	 
	  
	  model.setViewName("marketstaff/complaint");
	  return model;
	 }
	 
	
}
