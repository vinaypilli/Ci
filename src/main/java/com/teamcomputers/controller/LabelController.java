package com.teamcomputers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.dto.IssueFilterDto;
import com.teamcomputers.dto.LabelDto;
import com.teamcomputers.dto.LabelFilterDto;
import com.teamcomputers.message.ApiResponseGetAll;
import com.teamcomputers.message.ErrorResponse;
import com.teamcomputers.model.Label;
import com.teamcomputers.service.LabelService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/label")
public class LabelController {

	@Autowired
	private LabelService labelService;

	@PostMapping
	public ResponseEntity<?> addLabel(@RequestBody LabelDto label) {
		return this.labelService.addLabel(label);
	}

	@GetMapping("/{labelId}")
	public ResponseEntity<?> getById(@PathVariable long labelId) {
		return labelService.getById(labelId);
	}

//	@GetMapping
//	private ResponseEntity<ApiResponseGetAll<Label>> getAll(@RequestParam(defaultValue = "1") int page,
//			@RequestParam(defaultValue = "10") int size) {
//		return labelService.getAll(page - 1, size);
//	}
	@GetMapping("/active")
	public ResponseEntity<?> getActiveUsers() {
		return labelService.getActiveLabel();
	}
	@GetMapping
	private ResponseEntity<?> getAll() {
		return labelService.getAll();
	}

	@PutMapping("/{labelId}")
	private ResponseEntity<ErrorResponse> update(@PathVariable long labelId, @RequestBody LabelDto labelDto) {
		labelDto.setLabelId(labelId);
		return labelService.update(labelDto);
	}

	@DeleteMapping("/{labelId}")
	private ResponseEntity<ErrorResponse> delete(@PathVariable long labelId) throws NotFoundException {

		return labelService.deleteById(labelId);

	}
}
