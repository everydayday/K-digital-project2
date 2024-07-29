package edu.pnu.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BonA;
import edu.pnu.service.BonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 메서드가 반환하는 값을 JSON 또는 XML 형식으로 변환하여 HTTP 응답 본문에 직접 작성
@RestController@RequiredArgsConstructor @Slf4j
@CrossOrigin(origins = "http://localhost:3000") // port가 달라서 발생하는 오류 해결책
public class BonController {
	
	private final BonService bonService;
	
	
	
//	@GetMapping("/flows")
//	public ResponseEntity<?> getFlows(){	// ResponseEntity로 설정하면 Object처럼 모든 data 타입 받는다.	
//		
//		return ResponseEntity.ok(bonService.getFlow());
//	}
	
	@GetMapping("/flowsbydate")
	public ResponseEntity<?> getFlowsByDate(String date, char jeosuji){	// ResponseEntity로 설정하면 Object처럼 모든 data 타입 받는다.	
//		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
//		SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
//		// String 타입을 Date 타입으로 변환
//		System.out.println("here");
//		Date formatDate;
//		try {
//			formatDate = dtFormat.parse(date);
//			// Date 타입의 변수를 새롭게 지정한 포맷으로 변환
//			String strNewDtFormat = newDtFormat.format(formatDate);			
//			return ResponseEntity.ok(bonService.getFlowByDateContaining(strNewDtFormat));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		System.out.println("here in flowsbydate");
		// 하루치 일 때는 -1 넣어주기
		if(jeosuji == 'A') {
			return ResponseEntity.ok(bonService.getAFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'B') {
			return ResponseEntity.ok(bonService.getBFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'C') {
//			return ResponseEntity.ok(bonService.getCFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'D') {
//			return ResponseEntity.ok(bonService.getDFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'E') {
			return ResponseEntity.ok(bonService.getEFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'F') {
			return ResponseEntity.ok(bonService.getFFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'G') {
			return ResponseEntity.ok(bonService.getGFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'H') {
			return ResponseEntity.ok(bonService.getHFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'I') {
			return ResponseEntity.ok(bonService.getIFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'J') {
			return ResponseEntity.ok(bonService.getJFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'K') {
//			return ResponseEntity.ok(bonService.getKFlowByDateContaining(date, jeosuji));
		}
		else if(jeosuji == 'L') {
			return ResponseEntity.ok(bonService.getLFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'M') {
			return ResponseEntity.ok(bonService.getMFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'N') {
			return ResponseEntity.ok(bonService.getNFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'O') {
			return ResponseEntity.ok(bonService.getOFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'P') {
			return ResponseEntity.ok(bonService.getPFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'Q') {
			return ResponseEntity.ok(bonService.getQFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'S') {
			return ResponseEntity.ok(bonService.getSFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'T') {
			return ResponseEntity.ok(bonService.getTFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'U') {
			return ResponseEntity.ok(bonService.getUFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'V') {
			return ResponseEntity.ok(bonService.getVFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'W') {
			return ResponseEntity.ok(bonService.getWFlowByDateContaining(date, jeosuji,-1));
		}
		return null;
	}
	
	@GetMapping("/flowssum")
	public ResponseEntity<?> getFlowsSumByDate(String date){	// ResponseEntity로 설정하면 Object처럼 모든 data 타입 받는다.	

		return ResponseEntity.ok(bonService.getSumFlowsByDateContaining(date));
		
	}
	
	public String plusDate(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        
        LocalDate plusOneDay = date.plusDays(1);
        String plusOneDayString = plusOneDay.format(formatter);
        
        LocalDate plusTwoDays = date.plusDays(2);
        String plusTwoDaysString = plusTwoDays.format(formatter);
        
        return plusOneDayString;
	}
	
	
	@GetMapping("/daybeforeflowsbydate")
	public ResponseEntity<?> getDayBeforeFlowsByDate(String date, char jeosuji, int day){	// ResponseEntity로 설정하면 Object처럼 모든 data 타입 받는다.	
		
		if(jeosuji == 'A') {		
			return ResponseEntity.ok(bonService.getAFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'B') {
			return ResponseEntity.ok(bonService.getBFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'C') {
//			return ResponseEntity.ok(bonService.getCFlowByDateContaining(date, jeosuji));
		}
		else if(jeosuji == 'D') {
//			return ResponseEntity.ok(bonService.getDFlowByDateContaining(date, jeosuji));
		}
		else if(jeosuji == 'E') {
			return ResponseEntity.ok(bonService.getEFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'F') {
			return ResponseEntity.ok(bonService.getFFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'G') {
			return ResponseEntity.ok(bonService.getGFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'H') {
			return ResponseEntity.ok(bonService.getHFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'I') {
			return ResponseEntity.ok(bonService.getIFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'J') {
			return ResponseEntity.ok(bonService.getJFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'K') {
//			return ResponseEntity.ok(bonService.getKFlowByDateContaining(date, jeosuji));
		}
		else if(jeosuji == 'L') {
			return ResponseEntity.ok(bonService.getLFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'M') {
			return ResponseEntity.ok(bonService.getMFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'N') {
			return ResponseEntity.ok(bonService.getNFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'O') {
			return ResponseEntity.ok(bonService.getOFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'P') {
			return ResponseEntity.ok(bonService.getPFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'Q') {
			return ResponseEntity.ok(bonService.getQFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'S') {
			return ResponseEntity.ok(bonService.getSFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'T') {
			return ResponseEntity.ok(bonService.getTFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'U') {
			return ResponseEntity.ok(bonService.getUFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'V') {
			return ResponseEntity.ok(bonService.getVFlowByDateContaining(date, jeosuji, day));
		}
		else if(jeosuji == 'W') {
			return ResponseEntity.ok(bonService.getWFlowByDateContaining(date, jeosuji, day));
		}
		return null;
	}
		
	
	

}
