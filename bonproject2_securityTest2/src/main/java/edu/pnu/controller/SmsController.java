package edu.pnu.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.persistence.SmsCertification;
import edu.pnu.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class SmsController {
	
	private final MessageService messageService;
	
	@GetMapping("/checkphone")
	public ResponseEntity<Map<String, String>> checkphone(String phoneNumber) {
		Map<String, String> response = new HashMap();
		String msg;
		try {
			msg = messageService.sendSMS(phoneNumber);
			response.put("status", "success");
		}
		catch(Exception e){
			response.put("status", "failure");
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/checknum")
	public ResponseEntity<Map<String, String>> checknum(String num){
		
		System.out.println("true or false : " + messageService.verifySms(num));		
		Map<String, String> response = new HashMap<>();
		if(messageService.verifySms(num)) {
			response.put("status", "success");
		}
		else {
			response.put("status", "failure");
		}
		return ResponseEntity.ok(response);
		
	}
	
	
	
	
	
	
}
