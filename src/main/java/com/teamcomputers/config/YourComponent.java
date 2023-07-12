//package com.teamcomputers.config;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@Component
//public class YourComponent {
//	
//	 public void processFile() {
//	        Resource resource = new ClassPathResource("uploads/test.txt");
//	        try (InputStream inputStream = resource.getInputStream()) {
//	            // Process the file using the input stream
//
//	            // Example: Reading the file content
//	            byte[] fileContent = inputStream.readAllBytes();
//	            String content = new String(fileContent);
//	            System.out.println("File Content: " + content);
//	        } catch (IOException e) {
//	            // Handle any IOException that might occur during resource loading or file processing
//	            e.printStackTrace();
//	        }
//	    }
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
////    private final ResourceLoader resourceLoader;
////
////    public YourComponent(ResourceLoader resourceLoader) {
////        this.resourceLoader = resourceLoader;
////    }
////
////    public void processFile() {
////        Resource resource = resourceLoader.getResource("classpath:uploads/test.text");
////                                                   //   ("classpath:upload/test.text");
////        try (InputStream inputStream = resource.getInputStream()) {
////            // Process the file using the input stream
////
////            // Example: Reading the file content
////            byte[] fileContent = inputStream.readAllBytes();
////            String content = new String(fileContent);
////            System.out.println("File Content: " + content);
////        } catch (IOException e) {
////            // Handle any IOException that might occur during resource loading or file processing
////            e.printStackTrace();
////        }
////    }
//	
//	
//
//
//
//
//	
////	   String strJson = null;
////       ClassPathResource classPathResource = new ClassPathResource("uploads/test.txt");
////       {
////       try {
////           byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
////           strJson = new String(binaryData, StandardCharsets.UTF_8);
////       } catch (IOException e) {
////           e.printStackTrace();
////       }
////
////    }
//  
//    
//    
//    
//    
//    
//    
//    
////    @Value("${uploads.folder}")
////    private String uploadFolderPath;
////
////    public void processUpload(MultipartFile file) {
////        try {
////            // Create the target directory if it doesn't exist
////            File targetDirectory = new File(uploadFolderPath);
////            if (!targetDirectory.exists()) {
////                targetDirectory.mkdirs();
////            }
////
////            // Generate a unique file name
////            String fileName = generateUniqueFileName(file.getOriginalFilename());
////
////            // Create the target file path
////            Path targetFilePath = Path.of(uploadFolderPath, fileName);
////
////            // Copy the uploaded file to the target directory
////            Files.copy(file.getInputStream(), targetFilePath, StandardCopyOption.REPLACE_EXISTING);
////
////            // Process the uploaded image as needed
////            // You can also return the file path or any other response as required
////        } catch (IOException e) {
////            // Handle any IOException that might occur during file processing
////            e.printStackTrace();
////        }
////    }
////
////    private String generateUniqueFileName(String originalFileName) {
////        // Generate a unique file name using a UUID or any other logic you prefer
////        // For simplicity, appending a timestamp to the original file name here
////        String timestamp = String.valueOf(System.currentTimeMillis());
////        return timestamp + "_" + originalFileName;
////    }
////}
//    
//    
//    
////======================================================   
//     //   APPLICATION.PROPERTIES file.
////file.upload.path=/path/to/upload/directory
//
//    
//    
//    
////    @Component
////    public class YourComponent {
////        private final ResourceLoader resourceLoader;
////        private final String uploadPath; // Specify the path where you want to save the file on the server
////
////        public YourComponent(ResourceLoader resourceLoader, @Value("${file.upload.path}") String uploadPath) {
////            this.resourceLoader = resourceLoader;
////            this.uploadPath = uploadPath;
////        }
////
////        public void processFile() {
////            Resource resource = resourceLoader.getResource("classpath:uploads/test.text");
////
////            try (InputStream inputStream = resource.getInputStream()) {
////                // Generate a unique filename for the uploaded file
////                String fileName = UUID.randomUUID().toString() + ".jpg";
////                
////                // Create the output file path using the uploadPath and the generated filename
////                String outputPath = uploadPath + File.separator + fileName;
////                
////                // Save the file to the server file system
////                try (OutputStream outputStream = new FileOutputStream(outputPath)) {
////                    byte[] buffer = new byte[4096];
////                    int bytesRead;
////                    while ((bytesRead = inputStream.read(buffer)) != -1) {
////                        outputStream.write(buffer, 0, bytesRead);
////                    }
////                }
////                
////                System.out.println("File saved successfully: " + outputPath);
////            } catch (IOException e) {
////                // Handle any IOException that might occur during resource loading or file processing
////                e.printStackTrace();
////            }
////        }
////    }
//
//    
//}
