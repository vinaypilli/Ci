package com.teamcomputers.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class LogService {
	
	  private static final Logger logger = LoggerFactory.getLogger(LogService.class);

	    public void createLog(String logname) {
	        // Business logic to create a user
	        logger.info("User created: {}", logname);

	        // More business logic
	        logger.debug("Additional debug information");
	        
	        logger.error("Additional error msg");
	        
	        logger.warn("Additional warn msg");
	    }

	    public void deleteLog(String logname) {
	        // Business logic to delete a user
	        logger.info("User deleted: {}", logname);
	    }
       public void updateLog(String logname)
       {
    	   logger.info("User update:{}",logname);
       }

	public String getById(Long logId) {
		// TODO Auto-generated method stub
		return null;
	}
       
}
