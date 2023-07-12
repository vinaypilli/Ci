package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.CustomerDto;
import com.teamcomputers.dto.CustomerFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.logg.Logg;
import com.teamcomputers.model.Customer;
import com.teamcomputers.repository.CustomerRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class CustomerService {
	private Logger logger = Logg.getLogger(CustomerService.class);
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LatestMessageListShowService latestMessageListShowService;

	@Autowired
	private IncomingMessageShowService incomingMessageShowService;
	@Autowired
	private OutgoingMessageShowService outgoingMessageShowService;
	
	@Autowired
	private MessageNotesService messageNotesService;
	

	public Customer add(CustomerDto customerDto) {
		try {

			logger.debug("Adding customer in service class...");

			Customer customer = new Customer();

			customer.setCustomerId(customerDto.getCustomerId());
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			customer.setEmail(customerDto.getEmail());
			customer.setContact(customerDto.getContact());
			customer.setAddress(customerDto.getAddress());
			customer.setState(customerDto.getState());
			customer.setCity(customerDto.getCity());
			customer.setPostcode(customerDto.getPostcode());
			customer.setCreatedBy(userRepository.findById((int) (customerDto.getCreatedBy())).orElse(null));
			customer.setCreatedDate(customerDto.getCreatedDate());
			customer.setUpdatedBy(userRepository.findById((int) (customerDto.getUpdatedBy())).orElse(null));
			customer.setUpdatedDate(customerDto.getUpdatedDate());
			customer.setStatus(customerDto.isStatus());

			Customer savedCustomer = customerRepository.save(customer);

			// Log an info message
			logger.info("Customer added successfully in service class: {}", savedCustomer);

			return customerRepository.save(customer);
		} catch (Exception e) {
			// Log an error message
			logger.error("Failed to add customer in service class", e);
			throw new RuntimeException(e);
		}
	}

	public Customer getById(int customerId) {
		try {
			// Log a debug message
			logger.debug("Fetching customer by ID service class : {}", customerId);

			logger.info("Fetched customer by ID from service class {}: {}", customerId);

			return customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer Id :" + customerId + "Unavailable"));
		} catch (Exception e) {
			// Log an error message
			logger.error("Failed to fetch customer by ID service class : {}", customerId, e);
			throw new RuntimeException(e);
		}
	}

	public Customer update(CustomerDto customerDto) {
		try {
			// Log a debug message
			logger.debug("Updating customer in service class...");

			Customer customer = new Customer();

			customer.setCustomerId(customerDto.getCustomerId());
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			customer.setEmail(customerDto.getEmail());
			customer.setContact(customerDto.getContact());
			customer.setAddress(customerDto.getAddress());
			customer.setState(customerDto.getState());
			customer.setCity(customerDto.getCity());
			customer.setPostcode(customerDto.getPostcode());
			customer.setCreatedBy(userRepository.findById((int) (customerDto.getCreatedBy())).orElse(null));
			customer.setCreatedDate(customerDto.getCreatedDate());
			customer.setUpdatedBy(userRepository.findById((int) (customerDto.getUpdatedBy())).orElse(null));
			customer.setUpdatedDate(customerDto.getUpdatedDate());
			customer.setStatus(customerDto.isStatus());
			// Log an info message
			logger.info("Customer updated successfully in service class: {}");

			String fullname = null;

			if (customerDto.getFirstName() != null) {
				if (customerDto.getFirstName() != null && customerDto.getLastName() != null) {
					fullname = customerDto.getFirstName() + " " + customerDto.getLastName();
				} else {
					fullname = customerDto.getFirstName();
				}

			}

			latestMessageListShowService.updatename(customerDto.getContact(), fullname);
			incomingMessageShowService.updatename(customerDto.getContact(), fullname);
			messageNotesService.updateName(customerDto.getContact(), fullname);
			outgoingMessageShowService.updateName(customerDto.getContact(), fullname);

			return customerRepository.save(customer);
		} catch (Exception e) {
			// Log an error message
			logger.error("Failed to update customer in service class", e);
			throw new RuntimeException(e);
		}
	}

	public List<Customer> getAll() {
		try {

			logger.debug("Fetching all customers in service class...");

			logger.info("Fetched get all{} customers from service class ");
			return customerRepository.findAll();

		} catch (Exception e) {
			// Log an error message
			logger.error("Failed to fetch all customers from service class", e);
			throw new RuntimeException(e);
		}
	}

//check active user with ascending order	
	public List<CustomerFilterDto> getActiveUsers() {
		try {
			// Log a debug message
			logger.debug("Fetching active users in service class...");

			List<Customer> user = customerRepository.findByStatusOrderByFirstNameAsc(true);
			List<CustomerFilterDto> filteredCategories = new ArrayList<>();

			for (Customer customer : user) {
				CustomerFilterDto filtered = new CustomerFilterDto();

				filtered.setCustomerId(customer.getCustomerId());
				filtered.setFirstName(customer.getFirstName());
				filteredCategories.add(filtered);
			}
			// Log an info message
			logger.info("Fetched {} active users from service class", filteredCategories.size());
			return filteredCategories;
		} catch (Exception e) {
			// Log an error message
			logger.error("Failed to fetch active users from service class", e);
			throw new RuntimeException(e);
		}
	}

	public boolean deleteById(int customerId) throws NotFoundException {
		try {
			// Log a debug message
			logger.debug("Deleting customer by ID from service class: {}", customerId);

			Customer customer = customerRepository.findById(customerId).orElseThrow(
					() -> new ResourceNotFoundException("customer Id : " + customerId + " is Not Present in DataBase"));
			customer.setStatus(false); // Update status to false
			customerRepository.save(customer);
			logger.info("Customer deleted successfully from service class: {}", customer);
			return true;
		} catch (Exception e) {
			// Log an error message
			logger.error("Failed to delete customer by ID service class: {}", customerId, e);
			throw new RuntimeException(e);
		}

	}

	public Customer findbycontact(String contact) {

		return customerRepository.findByContact(contact);
	}

//	public boolean deleteById(int id) {
//		customerRepository.deleteById(id);
//		return true;
//	}

}
