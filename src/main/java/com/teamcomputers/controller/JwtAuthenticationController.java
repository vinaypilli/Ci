package com.teamcomputers.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.config.JwtTokenUtil;
import com.teamcomputers.dto.ChangePasswordDto;
import com.teamcomputers.dto.UserFilterDto;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.JwtRequest;
import com.teamcomputers.model.JwtResponse;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.model.UserDto;
import com.teamcomputers.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController<T> {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	private String message;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println(userDetails + "sdddddddddddddddddddddd");
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

//	@PatchMapping("/change-password")
//	public ResponseEntity<String> changePassword(@RequestBo email,
//			@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
//			@RequestParam("confirmPassword") String confirmPassword) {
	
	
	
//	@PostMapping("/forgot-password")
//	public String forgotPassword(@RequestParam String email) {
//		String response = userDetailsService.forgotPassword(email);
//		if (!response.startsWith("Invalid")) {
//			response = "http://localhost:8080/reset-password?token=" + response;
//			ResponseMessage responseBody = new ResponseMessage(message);
//			message="forgot password is successful";
//		}
//		//return response;
//		return message;
//	
//	}else {
//		message = "Invalid email address";
//		ResponseMessage responseBody = new ResponseMessage(message);
//	}
	
//	@PostMapping("/forgot-password")
//	public ResponseEntity<?> forgotPassword(@RequestParam String email) {
//	    String response = userDetailsService.forgotPassword(email);
//	    String message;
//	    if (!response.startsWith("Invalid")) {
//	        response = "http://localhost:8080/reset-password?token=" + response;
//	        
// ErrorResponse response1 = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
//         "Forgot password request is successful", ErrorCode.Success.getMessage());
// return ResponseEntity.status(HttpStatus.OK).body(response1);        
//	
//	    } else {
//	    
//	        ErrorResponse response1 = new ErrorResponse(Integer.parseInt(ErrorCode.NOT_FOUND.getCode()),
//	                "Data not Found", ErrorCode.NOT_FOUND.getMessage());
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response1);
//	    }
//		
//	}
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestParam(required = false) String email) {
	    if (email == null || email.trim().isEmpty()) {
	        ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Bad_Request.getCode()),
	                "Email address is required", ErrorCode.Bad_Request.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }

	    String response = userDetailsService.forgotPassword(email);
	    if (!response.startsWith("Invalid")) {
	        response = "http://13.127.147.41/reset-password/" + response;
//	        response = "http://localhost:8080/reset-password?token=" + response;
	        ErrorResponse response1 = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
	                "Forgot password request is successful", ErrorCode.Success.getMessage());
	        return ResponseEntity.status(HttpStatus.OK).body(response1);
	    } else {
	        ErrorResponse response1 = new ErrorResponse(Integer.parseInt(ErrorCode.NOT_FOUND.getCode()),
	                "Invalid email address", ErrorCode.NOT_FOUND.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response1);
	    }
	}


    
    @PutMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody Map<String, String> passwordMap) {
    	String password=passwordMap.get("password");
    	if (password == null || password.isBlank()) {
            ErrorResponse response = new ErrorResponse(
                Integer.parseInt(ErrorCode.Bad_Request.getCode()),
                "Invalid password",
                "Password cannot be null or blank"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    	
    	String newPassword = passwordEncoder.encode(passwordMap.get("password"));
//		String newPassword = passwordMap.get("password");
		return userDetailsService.resetPassword(token, newPassword);
//		 ErrorResponse response1 = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
//	                "Password successfully changed", ErrorCode.Success.getMessage());
//		 return ResponseEntity.status(HttpStatus.OK).body(response1);
	}
	
	
	@PatchMapping("/change-password")
	public ResponseEntity<String> changePassword( @RequestBody ChangePasswordDto changePasswordDto){
		UserDao user = userDetailsService.findByEmail(changePasswordDto.getEmail());
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		// Check if the old password matches the stored password
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        }

        // Check if the new password and confirm password match
        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("New password and confirm password do not match");
        }

        // Encode the new password
        String encodedPassword = passwordEncoder.encode(changePasswordDto.getNewPassword());

		
		userDetailsService.changePassword(user, encodedPassword);

		return ResponseEntity.ok("Password changed successfully");
	}

	@GetMapping("/userinfo/{username}")
	private UserDao getByUsername(@PathVariable String username) {
		return userDetailsService.getUserByUsername(username);
	}

	@GetMapping("users/active/{department}")
	public List<UserFilterDto> getAllActiveSubcategoriesByCategoryId(@PathVariable int department) {
		return userDetailsService.getAllActiveUsersByDepartmentId(department);
	}

	@GetMapping("/userid/{userId}")
	private ResponseEntity<?> getById(@PathVariable int userId) {
		return userDetailsService.getById(userId);
	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<ErrorMessage> handleLocation1(ResourceNotFoundException rx) {
//		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//	}

	@GetMapping("/users")
	private List<UserDao> getAll() {
		return userDetailsService.getAll();
	}

	@GetMapping("users/active")
	public List<UserFilterDto> getActiveUsers() {
		return userDetailsService.getActiveUsers();
	}

	@DeleteMapping("/users/{userId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable int userId) {
		try {
			userDetailsService.deleteById(userId);
			message = "User Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "User details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> saveUser(@RequestBody UserDto user) throws Exception {

		try {

			this.userDetailsService.save(user);
			message = "User  successfully registerd !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}

//		catch (DuplicateKeyException e) {
//			//System.out.println(e + "duplivcate....");
//			message = "data is duplicate"+e.getCause().getMessage();
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//			} 
		catch (Exception e) {
			message = "User  not registered";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseMessage> updateUser(@PathVariable("userId") int userId,
			@Valid @RequestBody UserDto userDto) {
		userDto.setUserId(userId);
		try {
			userDetailsService.updateUser(userDto);
			return ResponseEntity.ok(new ResponseMessage("User details successfully updated"));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("User not found"));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage("Failed to update user details"));
		}
	}

}
