package com.teamcomputers.controller;

import java.io.IOException;
import org.springframework.http.ContentDisposition;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamcomputers.service.AdditionalCommentsService;
import com.teamcomputers.service.ProductCategoryService;
import com.teamcomputers.service.ProductService;

@RestController
@RequestMapping()
public class FilesController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private AdditionalCommentsService additionalCommentsService;
	
	@Autowired
	private ProductService productService;
	
//	@GetMapping("{fileName}")
//	public ResponseEntity<?> downloadImage(@PathVariable String filename) throws IOException {
//		byte[] imageData = productCategoryService.downloadImageFromFileSystem(filename);
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
//	}

	
	
	
	@GetMapping("/uploads/{filename}")
    public ResponseEntity<byte[]> downloadImagePc(@PathVariable String filename) {
        try {
            byte[] imageData = productCategoryService.downloadImageFromFileSystem(filename);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate content type
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Handle the exception and return an appropriate response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/productUploads/{filename}")
    public ResponseEntity<byte[]> downloadImageProduct(@PathVariable String filename) {
        try {
            byte[] imageData = productService.downloadImageFromFileSystem(filename);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate content type
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Handle the exception and return an appropriate response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @GetMapping("/ticketfile/{filename}")
//    public ResponseEntity<byte[]> downloadImageTickets(@PathVariable String filename) {
//        try {
//            byte[] fileData = additionalCommentsService.downloadFileFromFileSystem(filename);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(filename).build());
//            return ResponseEntity.ok().headers(headers).body(fileData);
//        } catch (IOException e) {
//            e.printStackTrace(); // Print or log the exception for debugging purposes
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

	
}
