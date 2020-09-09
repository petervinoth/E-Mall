package com.vir.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vir.Model.*;

@Repository
public interface MarketRepository extends JpaRepository<MarketStaff,Integer> {

	MarketStaff findBySpace(String space);

	//Optional<Staff> findById(int id);

}
