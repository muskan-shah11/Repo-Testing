package com.src.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.src.dto.EmailDetailsDto;
import com.src.service.SendMailService;

@RestController
public class EmailController {

@Autowired private SendMailService emailService;

private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

 
     @PostMapping("/sendMail")
     public void sendMail(@RequestBody EmailDetailsDto details) {
	   LOGGER.info("Sending a simple mail");
	  try {
		emailService.sendSimpleMail(details);
	      } catch (Exception e) {
		LOGGER.error("Error while sending the mail: {}", e.getMessage());
	     }
      }
}