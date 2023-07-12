package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.IssueFilterDto;
import com.teamcomputers.dto.LabelDto;
import com.teamcomputers.dto.LabelFilterDto;
import com.teamcomputers.errorCode.ErrorCode;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.message.ApiResponseFormat;
import com.teamcomputers.message.ApiResponseGetAll;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.Issue;
import com.teamcomputers.model.Label;
import com.teamcomputers.repository.LabelRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class LabelService {
	@Autowired
	private LabelRepository labelRepository;
	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> addLabel(LabelDto labelDto) {

		Label userDup = labelRepository.findByLabelName(labelDto.getLabelName());
		if (userDup != null) {
			throw new DuplicateUserException("Label is name already exists");
		}

		Label label = new Label();
		label.setLabelName(labelDto.getLabelName());
		label.setCreatedBy(userRepository.findById((int) (labelDto.getCreatedBy())).orElse(null));
		label.setCreatedDate(labelDto.getCreatedDate());
		label.setUpdatedDate(labelDto.getUpdatedDate());
		label.setStatus(labelDto.isStatus());
		labelRepository.save(label);

		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "request saved successful",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<?> getById(long labelId) {

		Label label = labelRepository.findById(labelId)
				.orElseThrow(() -> new ResourceNotFoundException("Label Id : " + labelId + " Unavailable"));
		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(label);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

//	public ResponseEntity<ApiResponseGetAll<Label>> getAll(int page, int size) {
//		Pageable pageable = PageRequest.of(page, size);
//		Page<Label> labelPage = labelRepository.findAll(pageable);
//		List<Label> labels = labelPage.getContent();
//		
//		ApiResponseGetAll<Label> response = new ApiResponseGetAll<>();
//		response.setStatus("success");
//		response.setCode(HttpStatus.OK.value());
//		response.setMessage("Request successful");
//		response.setData(labels);
//		response.setTotalPages(labelPage.getTotalPages());
//		response.setCurrentPage(labelPage.getNumber() + 1);
//		response.setTotalRecords((int) labelPage.getTotalElements());
//		response.setPerPage(labelPage.getSize());
//		
//		if(labels.isEmpty()) {
//			response.setMessage("Data Not Found");
//			response.setCurrentPage(labelPage.getNumber());
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		}
//
//		if (page +1> labelPage.getTotalPages()) {
//			throw new IllegalArgumentException("Invalid page number");
//		}
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	public ResponseEntity<?> getActiveLabel() {
		List<Label> label = labelRepository.findByStatus(true);
		List<LabelFilterDto> filterDto = new ArrayList<>();

		for (Label labels : label) {
			LabelFilterDto filteredlabel = new LabelFilterDto();

			filteredlabel.setId(labels.getLabelId());
			filteredlabel.setName(labels.getLabelName());
			filterDto.add(filteredlabel);
		}
		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(filterDto);
		return new ResponseEntity<>(response, HttpStatus.OK);

		
	}
	public ResponseEntity<?> getAll() {
		List<Label>  labels= labelRepository.findAll();
		ApiResponseFormat response = new ApiResponseFormat();
		response.setStatus("success");
		response.setCode(HttpStatus.OK.value());
		response.setMessage("Request successful");
		response.setData(labels);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ErrorResponse> update(LabelDto labelDto) {

		Optional<Label> userDup2 = labelRepository.findById(labelDto.getLabelId());
		if (!userDup2.isPresent()) {
			throw new ResourceNotFoundException("Id does not exist");
		}
		Label userDup = labelRepository.findByLabelNameAndLabelIdNot(labelDto.getLabelName(), labelDto.getLabelId());
		if (userDup != null) {
			throw new DuplicateUserException("Label is name already exists");
		}

		Label label = new Label();
		label.setLabelId(labelDto.getLabelId());
		label.setLabelName(labelDto.getLabelName());
		label.setUpdatedBy(userRepository.findById((int) (labelDto.getUpdatedBy())).orElse(null));
		label.setUpdatedDate(labelDto.getUpdatedDate());
		label.setCreatedDate(labelDto.getCreatedDate());
		label.setStatus(labelDto.isStatus());
		labelRepository.save(label);
		ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()), "request successful",
				ErrorCode.Success.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	public ResponseEntity<ErrorResponse> deleteById(long labelId) throws NotFoundException {

		Label label = labelRepository.findById(labelId)
				.orElseThrow(() -> new ResourceNotFoundException("label Id : " + labelId + " Unavailable"));
		if (label.isStatus() == true) {
			label.setStatus(false); // Update status to false
			labelRepository.save(label);
			ErrorResponse response = new ErrorResponse(Integer.parseInt(ErrorCode.Success.getCode()),
					"request Deleted successful", ErrorCode.Success.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			throw new ResourceNotFoundException("Label Data not found from this Id");
		}

	}

}
