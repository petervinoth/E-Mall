package com.vir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vir.Model.MarketStaff;
import com.vir.Model.Staff;
import com.vir.Repository.MarketRepository;

@Controller
public class MarketController {
	
	@Autowired
	MarketRepository repo;
	
	BCryptPasswordEncoder Encoder=new BCryptPasswordEncoder(12);
	
	
	
	 @RequestMapping(value= {"/space"}, method=RequestMethod.GET)
	 public ModelAndView space() {
	  ModelAndView model = new ModelAndView();
	 
	 // System.out.print("save success1212");
	  model.setViewName("spaceowner/BookSpace");
	  return model;
	 }
	 
		@RequestMapping(value= {"/space"},method=RequestMethod.POST)
		public ModelAndView  user(MarketStaff mark,BindingResult bindingResult) {
			ModelAndView model=new ModelAndView();
			MarketStaff exit=repo.findBySpace(mark.getSpace());
			if(exit != null) {
				model.setViewName("marketstaff/error");
				}
			else {
				
				repo.save(mark);
				System.out.print("save success");
				model.addObject("msg","add successfully");
				model.setViewName("spaceowner/BookSpace");
				
			}
			return model;
		}
		
		@RequestMapping(value= {"/space1"}, method=RequestMethod.GET)
		 public ModelAndView space1() {
		  ModelAndView model = new ModelAndView();
		 
		 // System.out.print("save success1212");
		  model.setViewName("marketstaff/BookSpace");
		  return model;
		 }
		 
		
			@RequestMapping(value= {"/space1"},method=RequestMethod.POST)
			public ModelAndView  space(MarketStaff mark) {
				ModelAndView model=new ModelAndView();
				MarketStaff exit=repo.findBySpace(mark.getSpace());
				if(exit != null) {
					//model.addObject("msg","Space already taken");
					model.setViewName("marketstaff/error");
					}
				else {
					
					repo.save(mark);
					System.out.print("save success");
					model.addObject("msg","add successfully");
					model.setViewName("marketstaff/BookSpace");
					
				}
				return model;
			}
}
