package com.teamcomputers.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcomputers.dto.AdditionalCommentsDto;
import com.teamcomputers.dto.TicketDto;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.model.AdditionalComments;
import com.teamcomputers.model.TicketCreation;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.repository.AdditionalCommentsRepository;
import com.teamcomputers.repository.TicketCreationRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class AdditionalCommentsService {

	@Autowired
	private AdditionalCommentsRepository additionalCommentsRepository;

	@Autowired
	private TicketCreationRepository ticketCreationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper mapper;

//	public String UPLOAD_DIR;
	public String UPLOAD_DIR="/home/ubuntu/CDC/ticketfile/";
	
	public AdditionalComments add(long ticketId, String comment, UserDao user, MultipartFile file)
			throws DuplicateUserException, IOException {

		TicketDto tickets = mapper.readValue(comment, TicketDto.class);
		AdditionalCommentsDto ticket = mapper.readValue(comment, AdditionalCommentsDto.class);
		AdditionalCommentsDto ac = new AdditionalCommentsDto(ticket.getCommentsId(), tickets.getComment(),
				tickets.getCreatedDate(), ticketId, user.getUserId(), file.getBytes(), ticket.getFileUrl(),
				ticket.getFilePath(), ticket.getFilename());
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateTimeString = currentDateTime.format(formatter);
		currentDateTimeString = currentDateTimeString.replace(":", "").replace(" ", "").replace("-", "");

		

		AdditionalComments additionalComments = new AdditionalComments();

//		String fileName = file.getOriginalFilename();
		String fileName = currentDateTimeString+file.getOriginalFilename();
		fileName = fileName.replaceAll(" ", "");

//		UPLOAD_DIR = new ClassPathResource("ticketfile/").getFile().getAbsolutePath();

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			Path destFilePath = Paths.get(UPLOAD_DIR, fileName);
			try {
				Files.copy(file.getInputStream(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (DirectoryNotEmptyException e) {

				throw new IOException("Cannot upload file to destination: directory is not empty.", e);
			} catch (IOException e) {

				throw new IOException("Failed to upload file to destination.", e);
			}
		}

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			additionalComments.setAttach_file(file.getBytes());
			additionalComments.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/ticketfile/")
					.path(fileName).toUriString());
			additionalComments.setFilePath(UPLOAD_DIR + File.separator + fileName);
			additionalComments.setFilename(fileName);

		} else {

			additionalComments.setAttach_file(null);
			additionalComments.setFileUrl(null);
			additionalComments.setFilePath(null);
			additionalComments.setFilename(null);
		}

		additionalComments.setCommentsId(ac.getCommentsId());
		additionalComments.setAdditionalMessage(tickets.getComment());
		// additionalComments.setCurrentTime(ac.getCurrentTime());
//		additionalComments.setCommentsId(ac.getCommentsId());

		additionalComments.setCreatedDate(ac.getCreatedDate());
		additionalComments.setTicketCreation(ticketCreationRepository.findById(ac.getTicketId()).orElse(null));
//		UserDao user =userRepository.findById(ac.getUserId()).orElseThrow(() -> new ResourceNotFoundException("CreatedforUser Id :" + ac.getUserId()+ " Unavailable"));
		additionalComments.setUserDao(userRepository.findById(ac.getUserId()).orElse(null));

		return additionalCommentsRepository.save(additionalComments);
	}

	public AdditionalComments add(long ticketId, String comment, UserDao user)
			throws DuplicateUserException, IOException {

		TicketDto tickets = mapper.readValue(comment, TicketDto.class);
		AdditionalCommentsDto ticket = mapper.readValue(comment, AdditionalCommentsDto.class);
		AdditionalCommentsDto ac = new AdditionalCommentsDto(ticket.getCommentsId(), tickets.getComment(),
				tickets.getCreatedDate(), ticketId, user.getUserId());

		AdditionalComments additionalComments = new AdditionalComments();
		additionalComments.setAttach_file(null);
		additionalComments.setFileUrl(null);
		additionalComments.setFilePath(null);
		additionalComments.setFilename(null);

		additionalComments.setCommentsId(ac.getCommentsId());
		additionalComments.setAdditionalMessage(tickets.getComment());
		// additionalComments.setCurrentTime(ac.getCurrentTime());
//		additionalComments.setCommentsId(ac.getCommentsId());

		additionalComments.setCreatedDate(ac.getCreatedDate());
		additionalComments.setTicketCreation(ticketCreationRepository.findById(ac.getTicketId()).orElse(null));
//		UserDao user =userRepository.findById(ac.getUserId()).orElseThrow(() -> new ResourceNotFoundException("CreatedforUser Id :" + ac.getUserId()+ " Unavailable"));
		additionalComments.setUserDao(userRepository.findById(ac.getUserId()).orElse(null));

		return additionalCommentsRepository.save(additionalComments);
	}

	public AdditionalComments update(long ticketId, String comment, UserDao user, MultipartFile file)
			throws DuplicateUserException, IOException {

		TicketDto tickets = mapper.readValue(comment, TicketDto.class);
		AdditionalCommentsDto ticket = mapper.readValue(comment, AdditionalCommentsDto.class);
		AdditionalCommentsDto ac = new AdditionalCommentsDto(ticket.getCommentsId(), tickets.getComment(),
				tickets.getCreatedDate(), ticketId, user.getUserId(), file.getBytes(), ticket.getFileUrl(),
				ticket.getFilePath(), ticket.getFilename());

		AdditionalComments additionalComments = new AdditionalComments();

		String fileName = file.getOriginalFilename();

//		UPLOAD_DIR = new ClassPathResource("uploads/").getFile().getAbsolutePath();

		if (file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
			Path destFilePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
			try {
				Files.copy(file.getInputStream(), destFilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (DirectoryNotEmptyException e) {

				throw new IOException("Cannot upload file to destination: directory is not empty.", e);
			} catch (IOException e) {

				throw new IOException("Failed to upload file to destination.", e);
			}

		}

		if (file.getOriginalFilename() != null && !(file.getOriginalFilename().isEmpty())) {
			additionalComments.setAttach_file(file.getBytes());
			additionalComments.setFileUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/")
					.path(fileName).toUriString());
			additionalComments.setFilePath(UPLOAD_DIR + File.separator + fileName);
			additionalComments.setFilename(fileName);
		} else {
			Optional<AdditionalComments> fileDetails = additionalCommentsRepository.findById(ticketId);

			AdditionalComments product = null;
			if (fileDetails.isPresent()) {
				product = fileDetails.get();
			}

			additionalComments.setAttach_file(product.getAttach_file());
			additionalComments.setFileUrl(product.getFileUrl());
			additionalComments.setFilePath(product.getFilePath());
			additionalComments.setFilename(product.getFilename());
		}

		additionalComments.setCommentsId(ac.getCommentsId());
		additionalComments.setAdditionalMessage(tickets.getComment());
		// additionalComments.setCurrentTime(ac.getCurrentTime());

		additionalComments.setCreatedDate(ac.getCreatedDate());
		additionalComments.setTicketCreation(ticketCreationRepository.findById(ac.getTicketId()).orElse(null));
//		UserDao user =userRepository.findById(ac.getUserId()).orElseThrow(() -> new ResourceNotFoundException("CreatedforUser Id :" + ac.getUserId()+ " Unavailable"));
		additionalComments.setUserDao(userRepository.findById(ac.getUserId()).orElse(null));

		return additionalCommentsRepository.save(additionalComments);
	}
	
	public AdditionalComments update(long ticketId, String comment, UserDao user)
			throws DuplicateUserException, IOException {

		TicketDto tickets = mapper.readValue(comment, TicketDto.class);
		AdditionalCommentsDto ticket = mapper.readValue(comment, AdditionalCommentsDto.class);
		AdditionalCommentsDto ac = new AdditionalCommentsDto(ticket.getCommentsId(), tickets.getComment(),
				tickets.getCreatedDate(), ticketId, user.getUserId());

		AdditionalComments additionalComments = new AdditionalComments();

		additionalComments.setCommentsId(ac.getCommentsId());
		additionalComments.setAdditionalMessage(tickets.getComment());
		// additionalComments.setCurrentTime(ac.getCurrentTime());

		additionalComments.setCreatedDate(ac.getCreatedDate());
		additionalComments.setTicketCreation(ticketCreationRepository.findById(ac.getTicketId()).orElse(null));
//		UserDao user =userRepository.findById(ac.getUserId()).orElseThrow(() -> new ResourceNotFoundException("CreatedforUser Id :" + ac.getUserId()+ " Unavailable"));
		additionalComments.setUserDao(userRepository.findById(ac.getUserId()).orElse(null));

		return additionalCommentsRepository.save(additionalComments);
	}

	public List<AdditionalCommentsDto> getByTicketId(long ticketId) {
		TicketCreation ticketCreation = new TicketCreation();
		ticketCreation.setTicketId(ticketId);
		List<AdditionalComments> comments = additionalCommentsRepository.findByTicketCreation(ticketCreation);
		List<AdditionalCommentsDto> filteredCategories = new ArrayList<>();

		for (AdditionalComments additionalComments : comments) {
			AdditionalCommentsDto filteredCategory = new AdditionalCommentsDto();
			UserDao user = additionalComments.getUserDao();
			filteredCategory.setCommentsId(additionalComments.getCommentsId());
			filteredCategory.setAdditionalMessage(additionalComments.getAdditionalMessage());
			filteredCategory.setCreatedDate(additionalComments.getCreatedDate());
			filteredCategory.setUserId(user.getUserId());
			filteredCategory.setTicketId(ticketId);
			filteredCategory.setFilename(additionalComments.getFilename());
			filteredCategory.setFilePath(additionalComments.getFilePath());
			filteredCategory.setFileUrl(additionalComments.getFileUrl());

			filteredCategories.add(filteredCategory);
		}
		return filteredCategories;
	}

	public List<AdditionalComments> getAll() {
		return additionalCommentsRepository.findAll();
	}
	
}
