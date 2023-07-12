package com.teamcomputers.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.teamcomputers.dto.TempleteAddDTO;
import com.teamcomputers.model.TempleteAdd;
import com.teamcomputers.service.TempleteAddService;


import javassist.NotFoundException;

@RestController
@RequestMapping("/templetes")
public class TempleteAddController {
	
	@Autowired
    private  TempleteAddService templeteAddService;

    

    @PostMapping
    public ResponseEntity<TempleteAdd> createTempleteAdd(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("userData") String templeteAddDTO) throws IOException {
        TempleteAdd createdTempleteAdd = templeteAddService.saveTemplate(file,templeteAddDTO);
        return ResponseEntity.ok(createdTempleteAdd);
    }

    
    @GetMapping
    public List<TempleteAdd> getAllTempleteAdds() {
        return templeteAddService.getAllTempleteAdds();
    }
    
//    @GetMapping("/{id}")
//    public ResponseEntity<TempleteAddDTO> getTempleteAddById(@PathVariable Long id) throws NotFoundException {
//        TempleteAddDTO templeteAddDTO = templeteAddService.getTempleteAddById(id);
//        return ResponseEntity.ok(templeteAddDTO);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<TempleteAddDTO>> getAllTempleteAdds() {
//        List<TempleteAddDTO> templeteAddDTOs = templeteAddService.getAllTempleteAdds();
//        return ResponseEntity.ok(templeteAddDTOs);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TempleteAddDTO> updateTempleteAdd(@PathVariable Long id, @RequestBody TempleteAddDTO templeteAddDTO) throws NotFoundException {
//        TempleteAddDTO updatedTempleteAdd = templeteAddService.updateTempleteAdd(id, templeteAddDTO);
//        return ResponseEntity.ok(updatedTempleteAdd);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTempleteAdd(@PathVariable Long id) throws NotFoundException {
//        templeteAddService.deleteTempleteAdd(id);
//        return ResponseEntity.noContent().build();
//    }
}

