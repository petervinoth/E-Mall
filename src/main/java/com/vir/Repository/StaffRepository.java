package com.vir.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.vir.Model.*;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {

	Staff findByUsername(String username);

	//Optional<Staff> findById(int id);
	
	
}
