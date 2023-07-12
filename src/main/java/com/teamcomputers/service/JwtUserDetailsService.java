package com.teamcomputers.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.NewUserFilterDto;
import com.teamcomputers.dto.UserFilterDto;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.ConflictException;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ApiResponseFormat;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.model.UserDto;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.repository.DepartmentRepository;
import com.teamcomputers.repository.RoleRepository;
import com.teamcomputers.repository.ServiceTitleRepository;
import com.teamcomputers.repository.SubCategoryRepository;
import com.teamcomputers.repository.UserRepository;


@Service
public class JwtUserDetailsService<T> implements UserDetailsService {
	
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private ServiceTitleRepository serviceTitleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserDao getUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		return user;
	}

//	public UserDao save(UserDto user) throws DuplicateUserException {
//
//		UserDao userDup = userDao.findByUsername(user.getUsername());
//		if (userDup != null) {
//			throw new DuplicateUserException("Username already exists");
//		}
//
//		// SubCategory subCategory = new SubCategory();
//		UserDao newUser = new UserDao();
//
//		newUser.setUsername(user.getUsername());
//		newUser.setFirstName(user.getFirstName());
//		newUser.setLastName(user.getLastName());
//		newUser.setEmail(user.getEmail());
//		newUser.setContact(user.getContact());
//		newUser.setAddress(user.getAddress());
//		newUser.setState(user.getState());
//		newUser.setCity(user.getCity());
//		newUser.setPostcode(user.getPostcode());
//		newUser.setCreatedBy(user.getCreatedBy());
//		newUser.setCreatedDate(user.getCreatedDate());
//		newUser.setUpdatedBy(user.getUpdatedBy());
//		newUser.setUpdatedDate(user.getUpdatedDate());
//		newUser.setStatus(user.isStatus());
//		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		newUser.setDepartment(departmentRepo.findById((int) user.getDepartmentId()).orElse(null));
//
//		DepartmentEntity cat = departmentRepo.findById((int) user.getDepartmentId()).orElse(null);
//		newUser.setDepartmentName(cat.getDepartmentName());
//		newUser.setDepartmentId(cat.getDepartmentId());
//		newUser.setRole(roleRepository.findById(user.getRoleId()).orElse(null));
//
//		RoleEntity cat1 = roleRepository.findById(user.getRoleId()).orElse(null);
//		newUser.setRoleName(cat1.getRoleName());
//		newUser.setRoleId(cat1.getRoleId());
//
//		return userDao.save(newUser);
//	}

	public UserDao save(UserDto user) throws DuplicateUserException {
		UserDao userDup = userDao.findByUsername(user.getUsername());
		if (userDup != null) {
			throw new DuplicateUserException("Username already exists");
		}

		UserDao newUser = new UserDao();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setContact(user.getContact());
		newUser.setAddress(user.getAddress());
		newUser.setState(user.getState());
		newUser.setPostcode(user.getPostcode());
		newUser.setCity(user.getCity());
		newUser.setCreatedBy(user.getCreatedBy());
		newUser.setCreatedDate(user.getCreatedDate());
		newUser.setUpdatedBy(user.getUpdatedBy());
		newUser.setUpdatedDate(user.getUpdatedDate());
		newUser.setStatus(user.isStatus());
		newUser.setDepartment(departmentRepo.findById((int) user.getDepartmentId()).orElse(null));
		newUser.setRole(roleRepository.findById((int) user.getRoleId()).orElse(null));
		newUser.setCategory(categoryRepository.findById((long) user.getCategoryId()).orElse(null));
		newUser.setSubCategory(subCategoryRepository.findById((int) user.getSubCategoryId()).orElse(null));
		newUser.setServiceTitle(serviceTitleRepository.findById((int) user.getServiceTitleId()).orElse(null));
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}

	public List<UserDao> getAll() {
		return userDao.findAll();
	}

	public List<UserFilterDto> getActiveUsers() {
		List<UserDao> user = userDao.findByStatusOrderByUsernameAsc(true);
		List<UserFilterDto> filteredCategories = new ArrayList<>();

		for (UserDao userDao : user) {
			UserFilterDto filtered = new UserFilterDto();

			filtered.setUserId(userDao.getUserId());
			filtered.setFirstName(userDao.getFirstName());
			filtered.setLastName(userDao.getLastName());
			filtered.setUsername(userDao.getUsername());
			filtered.setRole(userDao.getRole());
			filteredCategories.add(filtered);
		}

		return filteredCategories;
	}

//	public List<NewUserFilterDto> getAllActiveUsersByDepartmentId(Integer id) {
//		List<UserDao> user = userDao.findByDepartmentAndStatusTrue(id);
//		List<NewUserFilterDto> filteredUsers = new ArrayList<>();
//
//		for (UserDao userDao : user) {
//			NewUserFilterDto filtered = new NewUserFilterDto();
//
//			filtered.setUserId(userDao.getUserId());
//			filtered.setUsername(userDao.getUsername());
//			filtered.setFirstName(userDao.getFirstName());
//			filtered.setLastName(userDao.getLastName());
//			filteredUsers.add(filtered);
//		}
//
//		return filteredUsers;
//	}
	public List<UserFilterDto> getAllActiveUsersByDepartmentId(int departmentId) {

		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setDepartmentId(departmentId);
		List<UserDao> userdao = userDao.findByDepartmentAndStatusTrueOrderByUsernameAsc(departmentEntity);
		List<UserFilterDto> filteredCategories = new ArrayList<>();

		for (UserDao userdao1 : userdao) {
			UserFilterDto filteredCategory = new UserFilterDto();

			filteredCategory.setUserId(userdao1.getUserId());
			filteredCategory.setUsername(userdao1.getUsername());
			filteredCategory.setFirstName(userdao1.getFirstName());
			filteredCategory.setLastName(userdao1.getLastName());
			filteredCategory.setRole(userdao1.getRole());
			// filteredCategory.setRoleName(userdao1.getRole().getRoleName());

			filteredCategories.add(filteredCategory);
		}

		return filteredCategories;

	}

	public ResponseEntity<?> getById(int userId) {
		List<UserDao> userdao= userDao.findByUserId(userId);
		if(userdao==null ||userdao.isEmpty()) {
			ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.NOT_FOUND.getCode()), "Unavailable Id",
					ErrorCode.NOT_FOUND.getMessage());
			throw new ResourceNotFoundException("User Not Found");
		}
				
		List<NewUserFilterDto> filteredUsers = new ArrayList<>();

		for (UserDao userDao : userdao) {
			NewUserFilterDto filtered = new NewUserFilterDto();

			filtered.setUserId(userDao.getUserId());
			filtered.setUsername(userDao.getUsername());
			filtered.setFirstName(userDao.getFirstName());
			filtered.setLastName(userDao.getLastName());
			filteredUsers.add(filtered);
		}
		 ApiResponseFormat response = new ApiResponseFormat();
			response.setStatus("success");
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Request successful");
			response.setData(filteredUsers);
			return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public UserDao updateUser(UserDto user) throws ResourceNotFoundException {
		UserDao newUser = userDao.findById(user.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setContact(user.getContact());
		newUser.setAddress(user.getAddress());
		newUser.setState(user.getState());
		newUser.setCity(user.getCity());
		newUser.setCreatedBy(user.getCreatedBy());
		newUser.setPostcode(user.getPostcode());
		newUser.setCreatedDate(user.getCreatedDate());
		newUser.setUpdatedBy(user.getUpdatedBy());
		newUser.setUpdatedDate(user.getUpdatedDate());
		newUser.setStatus(user.isStatus());
		newUser.setDepartment(departmentRepo.findById((int) user.getDepartmentId()).orElse(null));
		newUser.setRole(roleRepository.findById((int) user.getRoleId()).orElse(null));
		newUser.setCategory(categoryRepository.findById((long) user.getCategoryId()).orElse(null));
		newUser.setSubCategory(subCategoryRepository.findById((int) user.getSubCategoryId()).orElse(null));
		newUser.setServiceTitle(serviceTitleRepository.findById((int) user.getServiceTitleId()).orElse(null));

		return userDao.save(newUser);
	}

	public UserDao findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public boolean checkOldPassword(UserDao user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

	public void changePassword(UserDao user, String newPassword) {
//		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(newPassword);
		userRepository.save(user);
	}

	public boolean deleteById(int userId) throws NotFoundException {
		UserDao user = userDao.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("user Id : " + userId + " is Not Present in DataBase"));
		user.setStatus(false); // Update status to false
		userDao.save(user);
		return true;
	}
	
	
    public void sendForgotPasswordEmail(UserDao user, String resetLink) {  	

    	
    	SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Testing Mail API");
		mail.setText("Dear Sir/madam ,\r\n" + "\r\n"
				+ "Ticket has been created and please find the below ticket id :\r\n");
		javaMailSender.send(mail);
     }
    
    public void sendForgotPasswordEmail1(UserDao user, String resetLink) {
        String recipientEmail = user.getEmail();
        String subject = "Forgot Password";
        String content = "Dear Sir/Madam,\n\n"
                + "Please click on the following link to reset your password:\n"
                + resetLink + "\n\n"
                + "If you didn't request a password reset, please ignore this email.\n\n"
                + "Regards,\n"
                + "Your Application Team";

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            helper.setText(content, false);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it or throw a custom exception)
        }
    }
        // Implement your email sending logic here
        // Use the user's email and the resetLink to send the email
    

    public String forgotPassword(String email) {
        UserDao userOptional = userRepository.findByEmail(email);
        if (userOptional ==null) {
            return "Invalid email id.";
        }

        UserDao user = userRepository.findByEmail(userOptional.getEmail());
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepository.save(userOptional);

//        String resetLink = "http://localhost:8080/reset-password?token=" + userOptional.getToken();
        String resetLink = "http://13.127.147.41/reset-password/" + userOptional.getToken();
        sendForgotPasswordEmail1(user, resetLink);

        return "Forgot password email sent.";
    }


    public ResponseEntity<?> resetPassword(String token, String password) {
    	UserDao userOptional = userRepository.findByToken(token);
    	if (userOptional == null) {
			throw new UsernameNotFoundException("Invalid Token!!!" );
		}

        LocalDateTime tokenCreationDate = userOptional.getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            
        	throw new UsernameNotFoundException("Token Expired!!! ");
        }

        UserDao user =userRepository.findByEmail(userOptional.getEmail()) ;

        user.setPassword(password);
        user.setToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);
        ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "Successfully password changed ",
                ErrorCode.Success.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();
        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);
        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }
	

}