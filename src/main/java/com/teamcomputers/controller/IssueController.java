package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.IssueDto;
import com.teamcomputers.dto.IssueFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ErrorMessage;
import com.teamcomputers.message.ResponseMessage;
import com.teamcomputers.model.Issue;
import com.teamcomputers.service.IssueService;

@RestController
@RequestMapping("/issue")
public class IssueController {

	@Autowired
	private IssueService issueService;

	String message = "";

	@PostMapping
	public ResponseEntity<?> add(@RequestBody IssueDto issueDto) {

		try {

			this.issueService.add(issueDto);
			message = "Issue Details successfully saved !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Issue details not saved";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

//	@GetMapping("/active/{category}")
//	public List<IssueFilterDto> getAllActiveSubcategoriesByCategoryId(@PathVariable Long category) {
//		return issueService.getAllActiveSubcategoriesByCategoryId(category);
//	}

	@GetMapping("/{issueId}")
	private Issue getById(@PathVariable long issueId) {
		return issueService.getById(issueId);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> handleLocation(ResourceNotFoundException rx) {
		ErrorMessage errorMessage = new ErrorMessage("USER NOT FOUND", rx.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@GetMapping
	private List<Issue> getAll() {
		return issueService.getAll();
	}

	@GetMapping("/active")
	public List<IssueFilterDto> getActiveUsers() {
		return issueService.getActiveIssue();
	}

	@PutMapping("/{issueId}")
	private ResponseEntity<ResponseMessage> update(@PathVariable long issueId, @RequestBody IssueDto issueDto) {

		String message = "";

		try {
			issueDto.setIssueId(issueId);
			this.issueService.update(issueDto);
			message = "Issue Details successfully Updated !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Issue details are not updated";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@DeleteMapping("/{issueId}")
	private ResponseEntity<ResponseMessage> delete(@PathVariable long issueId) {
		try {
			issueService.deleteById(issueId);
			message = "Issue Details successfully deleted !!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Issue details are not deleted" + e.getCause().getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

}
