package com.vir.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vir.Model.*;

@Repository
public interface MarketRepository extends JpaRepository<MarketStaff,Long> {

	MarketStaff findBySpace(String space);

}
