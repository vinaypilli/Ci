package com.teamcomputers.logg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logg {
	
	public static Logger getLogger(Class classname) {
		return LoggerFactory.getLogger(classname);
		
	}

		
	}


