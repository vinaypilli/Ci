package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.IssueDto;
import com.teamcomputers.dto.IssueFilterDto;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Issue;
import com.teamcomputers.repository.IssueRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class IssueService {

	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private UserRepository userRepository;

	public Issue add(IssueDto issueDto) throws DuplicateUserException {

		Issue userDup = issueRepository.findByIssueName(issueDto.getIssueName());
		if (userDup != null) {
			throw new DuplicateUserException("IssueName already exists");
		}
		
		Issue issue = new Issue();
		
		issue.setIssueId(issueDto.getIssueId());
		issue.setIssueName(issueDto.getIssueName());
		issue.setCalculationSLA(issueDto.getCalculationSLA());
		issue.setIssueId(issueDto.getIssueId());
		issue.setCreatedBy(userRepository.findById((int) (issueDto.getCreatedBy())).orElse(null));		
		issue.setUpdatedBy(userRepository.findById((int) (issueDto.getUpdatedBy())).orElse(null));
		issue.setUpdatedDate(issueDto.getUpdatedDate());
		issue.setCreatedDate(issueDto.getCreatedDate());
		issue.setStatus(issueDto.isStatus());

		return issueRepository.save(issue);
	}

	public Issue update(IssueDto issueDto) throws DuplicateUserException {

		Issue issue = new Issue();

		issue.setIssueId(issueDto.getIssueId());
		issue.setIssueName(issueDto.getIssueName());
		issue.setCalculationSLA(issueDto.getCalculationSLA());
		issue.setIssueId(issueDto.getIssueId());
		issue.setCreatedBy(userRepository.findById((int) (issueDto.getCreatedBy())).orElse(null));		issue.setCreatedDate(issueDto.getCreatedDate());
		issue.setUpdatedBy(userRepository.findById((int) (issueDto.getUpdatedBy())).orElse(null));
		issue.setCreatedDate(issueDto.getCreatedDate());
		issue.setUpdatedDate(issueDto.getUpdatedDate());
		issue.setStatus(issueDto.isStatus());

		return issueRepository.save(issue);
	}

	public Issue getById(long issueId) {
		return issueRepository.findById(issueId)
				.orElseThrow(() -> new ResourceNotFoundException("Issue Id :" + issueId + "Unavailable"));
	}

	public List<IssueFilterDto> getActiveIssue() {
		List<Issue> issueTitleEntity = issueRepository.findByStatus(true);
		List<IssueFilterDto> issueFilterDto = new ArrayList<>();

		for (Issue issueTitle : issueTitleEntity) {
			IssueFilterDto filteredissue = new IssueFilterDto();

			filteredissue.setIssueId(issueTitle.getIssueId());
			filteredissue.setIssueName(issueTitle.getIssueName());
			issueFilterDto.add(filteredissue);
		}

		return issueFilterDto;
	}

	public List<Issue> getAll() {
		return issueRepository.findAll();
	}

	public boolean deleteById(long issueId) throws NotFoundException {
		Issue issue = issueRepository.findById( issueId).orElseThrow(
				() -> new ResourceNotFoundException("issueId: " + issueId + " is Not Present in DataBase"));
		issue.setStatus(false); // Update status to false
		issueRepository.save(issue);
		return true;
	}

}
