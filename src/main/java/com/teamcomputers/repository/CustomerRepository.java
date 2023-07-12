package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByStatusOrderByFirstNameAsc(boolean b);
	// List<Customer> findAllByOrderByFirstNameAsc();
	
	Customer findByContact(String contact);

}
