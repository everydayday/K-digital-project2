package edu.pnu.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BonHeightAvg;
import edu.pnu.domain.BonJ;
import edu.pnu.service.BonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 메서드가 반환하는 값을 JSON 또는 XML 형식으로 변환하여 HTTP 응답 본문에 직접 작성
@RestController@RequiredArgsConstructor @Slf4j
@CrossOrigin(origins = "http://localhost:3000") // port가 달라서 발생하는 오류 해결책
public class BonController {
	
	private final BonService bonService;
	
	@GetMapping("/getWaterLastHeight")
	public List<Double> findBonAvg() {
		List<Double> arr = new ArrayList<>();
		
		String dateString = "20221231";

     
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		LocalDateTime startDate = LocalDateTime.parse(dateString + "230000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		LocalDateTime endDate = LocalDateTime.parse(dateString + "230000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		BonHeightAvg bh = bonService.findMostRecentHeight(startDate, endDate);
		
		arr.add(bh.getA());
		arr.add(bh.getB());
		arr.add(bh.getE());
		arr.add(bh.getF());
		arr.add(bh.getG());
		arr.add(bh.getH());
		arr.add(bh.getI());
		arr.add(bh.getJ());
		arr.add(bh.getL());
		arr.add(bh.getM());
		arr.add(bh.getN());
		arr.add(bh.getO());
		arr.add(bh.getP());
		arr.add(bh.getQ());
		arr.add(bh.getS());
		arr.add(bh.getT());
		arr.add(bh.getU());
		arr.add(bh.getV());
//		arr.add(bh.getW()); // W 빼고
		
		return arr;
		
	}
	
	@GetMapping("/bonjdata")
	public Double bonjdata(String yearmonth){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		LocalDateTime date = LocalDateTime.parse(yearmonth+"000000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		
		BonJ bon = bonService.findBonJHeight(date);		
		
		Double height = bon.getHeight();
		return height; 
		
	}
	
	
	// 일별 데이터
	@GetMapping("/flowdailysumbymonth")
	public Map<String, List<Double>> flowdailysumbymonth(String yearmonth, String jeosuji){
		Map<String, List<Double>> hm = new HashMap<String, List<Double>>();
		
		List<List<Double>> arr = new ArrayList<>();
		
		String year = yearmonth.substring(0, 4);
		String month = yearmonth.substring(4);
		
		
		hm.put("in_flow", bonService.getDailyInflowSumByMonth(year,month,jeosuji));
		hm.put("out_flow",bonService.getDailyOutflowSumByMonth(year,month,jeosuji));
		hm.put("height", bonService.getDailyHeightAvgByMonth(year,month, jeosuji));
		
		
		
		return hm;
	}
	
	
	// 월별 데이터
	@GetMapping("/flowmonthlysumbyyear")
	public Map<String, List<Double>> getmontlysumbyyear(String year, String jeosuji){
		Map<String, List<Double>> hm = new HashMap<String, List<Double>>();
		
		List<List<Double>> arr = new ArrayList<>();
		
		List<Double> darr = new ArrayList<>();
		arr.add(bonService.findMonthlyInSumsByYear(year, jeosuji));
		arr.add(bonService.findMonthlyOutSumsByYear(year,jeosuji));
		arr.add(bonService.findMonthlyHeightByYear(year,jeosuji));
		
		hm.put("in_flow", bonService.findMonthlyInSumsByYear(year,jeosuji));
		hm.put("out_flow",bonService.findMonthlyOutSumsByYear(year,jeosuji));
		hm.put("height", bonService.findMonthlyHeightByYear(year,jeosuji));
		
		
		return hm;
	}
	
//	@GetMapping("/flows")
//	public ResponseEntity<?> getFlows(){	// ResponseEntity로 설정하면 Object처럼 모든 data 타입 받는다.	
//		
//		return ResponseEntity.ok(bonService.getFlow());
//	}
	
	@GetMapping("/flowsbydate")
	public ResponseEntity<?> getFlowsByDate(String date, char jeosuji){	// ResponseEntity로 설정하면 Object처럼 모든 data 타입 받는다.	

		System.out.println("here in flowsbydate");
		// 하루치 일 때는 -1 넣어주기
		if(jeosuji == 'a') {
			return ResponseEntity.ok(bonService.getAFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'b') {
			return ResponseEntity.ok(bonService.getBFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'c') {
//			return ResponseEntity.ok(bonService.getCFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'd') {
//			return ResponseEntity.ok(bonService.getDFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'e') {
			return ResponseEntity.ok(bonService.getEFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'f') {
			return ResponseEntity.ok(bonService.getFFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'g') {
			return ResponseEntity.ok(bonService.getGFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'h') {
			return ResponseEntity.ok(bonService.getHFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'i') {
			return ResponseEntity.ok(bonService.getIFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'j') {
			return ResponseEntity.ok(bonService.getJFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'k') {
//			return ResponseEntity.ok(bonService.getKFlowByDateContaining(date, jeosuji));
		}
		else if(jeosuji == 'l') {
			return ResponseEntity.ok(bonService.getLFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'm') {
			return ResponseEntity.ok(bonService.getMFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'n') {
			return ResponseEntity.ok(bonService.getNFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'o') {
			return ResponseEntity.ok(bonService.getOFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'p') {
			return ResponseEntity.ok(bonService.getPFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'q') {
			return ResponseEntity.ok(bonService.getQFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 's') {
			return ResponseEntity.ok(bonService.getSFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 't') {
			return ResponseEntity.ok(bonService.getTFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'u') {
			return ResponseEntity.ok(bonService.getUFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'v') {
			return ResponseEntity.ok(bonService.getVFlowByDateContaining(date, jeosuji,-1));
		}
		else if(jeosuji == 'w') {
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
