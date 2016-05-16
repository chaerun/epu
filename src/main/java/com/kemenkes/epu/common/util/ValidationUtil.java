package com.kemenkes.epu.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ValidationUtil {

	public static boolean validateImage(MultipartFile multipartFile) {
		
		// check file extension
    	String extension = StringUtils.reverse(multipartFile.getOriginalFilename());
    	extension = StringUtils.substring(extension, 0, StringUtils.indexOf(extension, "."));
    	extension = StringUtils.reverse(extension);
    	
    	if (!(StringUtils.equalsIgnoreCase(extension, "jpeg") || 
    			StringUtils.equalsIgnoreCase(extension, "jpg") ||
    			StringUtils.equalsIgnoreCase(extension, "gif") ||
    			StringUtils.equalsIgnoreCase(extension, "png"))) {
    		return false;
    	}
    	
		return true;
	}
}
