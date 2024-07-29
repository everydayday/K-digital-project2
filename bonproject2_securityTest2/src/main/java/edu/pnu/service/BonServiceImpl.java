package edu.pnu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.BonA;
import edu.pnu.domain.BonB;
import edu.pnu.domain.BonC;
import edu.pnu.domain.BonD;
import edu.pnu.domain.BonE;
import edu.pnu.domain.BonF;
import edu.pnu.domain.BonG;
import edu.pnu.domain.BonH;
import edu.pnu.domain.BonHeightAvg;
import edu.pnu.domain.BonI;
import edu.pnu.domain.BonJ;
import edu.pnu.domain.BonK;
import edu.pnu.domain.BonL;
import edu.pnu.domain.BonM;
import edu.pnu.domain.BonN;
import edu.pnu.domain.BonO;
import edu.pnu.domain.BonP;
import edu.pnu.domain.BonQ;
import edu.pnu.domain.BonS;
import edu.pnu.domain.BonSum;
import edu.pnu.domain.BonT;
import edu.pnu.domain.BonU;
import edu.pnu.domain.BonV;
import edu.pnu.domain.BonW;
import edu.pnu.domain.User2;
import edu.pnu.persistence.BonARepository;
import edu.pnu.persistence.BonBRepository;
import edu.pnu.persistence.BonCRepository;
import edu.pnu.persistence.BonDRepository;
import edu.pnu.persistence.BonERepository;
import edu.pnu.persistence.BonFRepository;
import edu.pnu.persistence.BonGRepository;
import edu.pnu.persistence.BonHRepository;
import edu.pnu.persistence.BonHeightAvgRepository;
import edu.pnu.persistence.BonIRepository;
import edu.pnu.persistence.BonJRepository;
import edu.pnu.persistence.BonKRepository;
import edu.pnu.persistence.BonLRepository;
import edu.pnu.persistence.BonMRepository;
import edu.pnu.persistence.BonNRepository;
import edu.pnu.persistence.BonORepository;
import edu.pnu.persistence.BonPRepository;
import edu.pnu.persistence.BonQRepository;
import edu.pnu.persistence.BonSRepository;
import edu.pnu.persistence.BonSumRepository;
import edu.pnu.persistence.BonTRepository;
import edu.pnu.persistence.BonURepository;
import edu.pnu.persistence.BonVRepository;
import edu.pnu.persistence.BonWRepository;
import edu.pnu.persistence.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BonServiceImpl implements BonService {

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	private final BonARepository bonARepo;
	private final BonBRepository bonBRepo;
	private final BonCRepository bonCRepo;
	private final BonDRepository bonDRepo;
	private final BonERepository bonERepo;
	private final BonFRepository bonFRepo;
	private final BonGRepository bonGRepo;
	private final BonHRepository bonHRepo;
	private final BonIRepository bonIRepo;
	private final BonJRepository bonJRepo;
	private final BonKRepository bonKRepo;
	private final BonLRepository bonLRepo;
	private final BonMRepository bonMRepo;
	private final BonNRepository bonNRepo;
	private final BonORepository bonORepo;
	private final BonPRepository bonPRepo;
	private final BonQRepository bonQRepo;
	private final BonSRepository bonSRepo;
	private final BonTRepository bonTRepo;
	private final BonURepository bonURepo;
	private final BonVRepository bonVRepo;
	private final BonWRepository bonWRepo;
	
	
	private final BonSumRepository bonSumRepo;
	private final BonHeightAvgRepository bonHeightRepo;

	
	// 가장 최근 날짜의 수위값 반환
	public BonHeightAvg findMostRecentHeight(LocalDateTime startDate, LocalDateTime endDate) {
		return bonHeightRepo.findByDateBetween(startDate, endDate);
		
	}
	
	// J 값 반환
	public BonJ findBonJHeight(LocalDateTime date) {
		return bonJRepo.findByDateIs(date);
	}
	
	// 날짜에 해당하는 (시작일시간, 시작일끝시간) 반환
	public List<LocalDateTime> getDateTime(String date){
		// format 형식 변환
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate start = LocalDate.parse(date, formatter);
		LocalDate end = LocalDate.parse(date, formatter);
		

		LocalDateTime startDateTime = start.atStartOfDay();
		LocalDateTime endDateTime = end.atTime(23, 59, 59);
		
		System.out.println("startDateTime : " + startDateTime.toString());		
		return Arrays.asList(startDateTime, endDateTime);
	}
	
	@Override
	public List<BonSum> getSumFlowsByDateContaining(String date){
		// format 형식 변환
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate start = LocalDate.parse(date, formatter);
        LocalDate end = LocalDate.parse(date, formatter);

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);
        System.out.println("startDateTime in serviceImple"+ startDateTime);
		return bonSumRepo.findBonSumByDateBetween(startDateTime, endDateTime);
	}
	
	
	
	// beforeDay에 해당 전일 ~ datetime 까지 시간 반환
	public List<LocalDateTime> getdaybeforeDateTime(String datetime, int beforeDay){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		
		System.out.println("datetime:"+ datetime);
//		System.out.println("now : " + LocalDateTime.now().format(formatter));
		LocalDateTime start;
        LocalDateTime end;
		try {
			
			start = LocalDateTime.parse(datetime, formatter);
			end = LocalDateTime.parse(datetime, formatter);
			
			}
		catch(DateTimeParseException e) {
            System.out.println("입력 문자열을 구문 분석하는 동안 오류가 발생했습니다: " + e.getMessage());
            return null; // 또는 예외를 던지거나 빈 리스트 반환
        }
		
		LocalDateTime startDateTime = start.minusDays(beforeDay).plusHours(1);	// + 1시간 
		LocalDateTime endDateTime = end;
		
		String formattedStartDateTime = startDateTime.format(formatter);
        String formattedEndDateTime = endDateTime.format(formatter);
		
		System.out.println("formattedStartDateTime : " + formattedStartDateTime);
		
		
		
		return Arrays.asList(startDateTime, endDateTime);
	}
	
	// ======= 일별 유출유량 합 ========
	public List<Double> getDailyOutflowSumByMonth(String year,String month, String jeosuji){
		
		List<Double> arr = new ArrayList<>(); 
		if(jeosuji.equals("a") ) {
			arr = bonSumRepo.findDailyOutSumsAByMonth(year, month);
		}else if(jeosuji.equals("b") ) {
			System.out.println("here");
			arr = bonSumRepo.findDailyOutSumsBByMonth(year, month);
		}else if(jeosuji.equals("e")) {
			arr = bonSumRepo.findDailyOutSumsEByMonth(year, month);
		}else if(jeosuji.equals("f")) {
			arr = bonSumRepo.findDailyOutSumsFByMonth(year, month);
		}else if(jeosuji.equals("g")) {
			arr = bonSumRepo.findDailyOutSumsGByMonth(year, month);
		}else if(jeosuji.equals("h")) {
			arr = bonSumRepo.findDailyOutSumsHByMonth(year, month);
		}else if(jeosuji.equals("i")) {
			arr = bonSumRepo.findDailyOutSumsIByMonth(year, month);
		}else if(jeosuji.equals("j")) {
			arr = bonSumRepo.findDailyOutSumsJByMonth(year, month);
		}else if(jeosuji.equals("l")) {
			arr = bonSumRepo.findDailyOutSumsLByMonth(year, month);
		}else if(jeosuji.equals("m")) {
			arr = bonSumRepo.findDailyOutSumsMByMonth(year, month);
		}else if(jeosuji.equals("n")) {
			arr = bonSumRepo.findDailyOutSumsNByMonth(year, month);
		}else if(jeosuji.equals("o")) {
			arr = bonSumRepo.findDailyOutSumsOByMonth(year, month);
		}else if(jeosuji.equals("p")) {
			arr = bonSumRepo.findDailyOutSumsPByMonth(year, month);
		}else if(jeosuji.equals("q")) {
			arr = bonSumRepo.findDailyOutSumsQByMonth(year, month);
		}else if(jeosuji.equals("s")) {
			arr = bonSumRepo.findDailyOutSumsSByMonth(year, month);
		}else if(jeosuji.equals("t")) {
			arr = bonSumRepo.findDailyOutSumsTByMonth(year, month);
		}else if(jeosuji.equals("u")) {
			arr = bonSumRepo.findDailyOutSumsUByMonth(year, month);
		}else if(jeosuji.equals("v")) {
			arr = bonSumRepo.findDailyOutSumsVByMonth(year, month);
		}else if(jeosuji.equals("w")) {
			arr = bonSumRepo.findDailyOutSumsWByMonth(year, month);
		}
		
		return arr ;
	}
	
	// ======= 일별 유입유량 합 ========
	public List<Double> getDailyInflowSumByMonth(String year,String month, String jeosuji){
		List<Double> arr = new ArrayList<>(); 
		if(jeosuji.equals("a") ) {
			arr = bonSumRepo.findDailyInSumsAByYear(year, month);
		}else if(jeosuji.equals("b") ) {
			System.out.println("here");
			arr = bonSumRepo.findDailyInSumsBByYear(year, month);
		}else if(jeosuji.equals("e")) {
			arr = bonSumRepo.findDailyInSumsEByYear(year, month);
		}else if(jeosuji.equals("f")) {
			arr = bonSumRepo.findDailyInSumsFByYear(year, month);
		}else if(jeosuji.equals("g")) {
			arr = bonSumRepo.findDailyInSumsGByYear(year, month);
		}else if(jeosuji.equals("h")) {
			arr = bonSumRepo.findDailyInSumsHByYear(year, month);
		}else if(jeosuji.equals("i")) {
			arr = bonSumRepo.findDailyInSumsIByYear(year, month);
		}else if(jeosuji.equals("j")) {
			arr = bonSumRepo.findDailyInSumsJByYear(year, month);
		}else if(jeosuji.equals("l")) {
			arr = bonSumRepo.findDailyInSumsLByYear(year, month);
		}else if(jeosuji.equals("m")) {
			arr = bonSumRepo.findDailyInSumsMByYear(year, month);
		}else if(jeosuji.equals("n")) {
			arr = bonSumRepo.findDailyInSumsNByYear(year, month);
		}else if(jeosuji.equals("o")) {
			arr = bonSumRepo.findDailyInSumsOByYear(year, month);
		}else if(jeosuji.equals("p")) {
			arr = bonSumRepo.findDailyInSumsPByYear(year, month);
		}else if(jeosuji.equals("q")) {
			arr = bonSumRepo.findDailyInSumsQByYear(year, month);
		}else if(jeosuji.equals("s")) {
			arr = bonSumRepo.findDailyInSumsSByYear(year, month);
		}else if(jeosuji.equals("t")) {
			arr = bonSumRepo.findDailyInSumsTByYear(year, month);
		}else if(jeosuji.equals("u")) {
			arr = bonSumRepo.findDailyInSumsUByYear(year, month);
		}else if(jeosuji.equals("v")) {
			arr = bonSumRepo.findDailyInSumsVByYear(year, month);
		}else if(jeosuji.equals("w")) {
			arr = bonSumRepo.findDailyInSumsWByYear(year, month);
		}
		
		return arr ;
	}
	
	
	// ====== 일별 수위 평균 ===========
	public List<Double> getDailyHeightAvgByMonth(String year, String month, String jeosuji){
		List<Double> arr = new ArrayList<>(); 
		if(jeosuji.equals("a") ) {
			arr = bonSumRepo.findDailyHeightAByYear(year, month);
		}else if(jeosuji.equals("b") ) {
			System.out.println("here");
			arr = bonSumRepo.findDailyHeightBByYear(year, month);
		}else if(jeosuji.equals("e")) {
			arr = bonSumRepo.findDailyHeightEByYear(year, month);
		}else if(jeosuji.equals("f")) {
			arr = bonSumRepo.findDailyHeightFByYear(year, month);
		}else if(jeosuji.equals("g")) {
			arr = bonSumRepo.findDailyHeightGByYear(year, month);
		}else if(jeosuji.equals("h")) {
			arr = bonSumRepo.findDailyHeightHByYear(year, month);
		}else if(jeosuji.equals("i")) {
			arr = bonSumRepo.findDailyHeightIByYear(year, month);
		}else if(jeosuji.equals("j")) {
			arr = bonSumRepo.findDailyHeightJByYear(year, month);
		}else if(jeosuji.equals("l")) {
			arr = bonSumRepo.findDailyHeightLByYear(year, month);
		}else if(jeosuji.equals("m")) {
			arr = bonSumRepo.findDailyHeightMByYear(year, month);
		}else if(jeosuji.equals("n")) {
			arr = bonSumRepo.findDailyHeightNByYear(year, month);
		}else if(jeosuji.equals("o")) {
			arr = bonSumRepo.findDailyHeightOByYear(year, month);
		}else if(jeosuji.equals("p")) {
			arr = bonSumRepo.findDailyHeightPByYear(year, month);
		}else if(jeosuji.equals("q")) {
			arr = bonSumRepo.findDailyHeightQByYear(year, month);
		}else if(jeosuji.equals("s")) {
			arr = bonSumRepo.findDailyHeightSByYear(year, month);
		}else if(jeosuji.equals("t")) {
			arr = bonSumRepo.findDailyHeightTByYear(year, month);
		}else if(jeosuji.equals("u")) {
			arr = bonSumRepo.findDailyHeightUByYear(year, month);
		}else if(jeosuji.equals("v")) {
			arr = bonSumRepo.findDailyHeightVByYear(year, month);
		}else if(jeosuji.equals("w")) {
			arr = bonSumRepo.findDailyHeightWByYear(year, month);
		}
		
		return arr ;
	}
	
	
	// ====== 월별 ======
	// 월별 유입유량 합
	public List<Double> findMonthlyInSumsByYear(String year,String jeosuji){
		List<Double> arr = new ArrayList<>(); 
		if(jeosuji.equals("a") ) {
			arr = bonSumRepo.findMonthlyInSumsAByYear(year);
		}else if(jeosuji.equals("b") ) {
			System.out.println("here");
			arr = bonSumRepo.findMonthlyInSumsBByYear(year);
		}else if(jeosuji.equals("e")) {
			arr = bonSumRepo.findMonthlyInSumsEByYear(year);
		}else if(jeosuji.equals("f")) {
			arr = bonSumRepo.findMonthlyInSumsFByYear(year);
		}else if(jeosuji.equals("g")) {
			arr = bonSumRepo.findMonthlyInSumsGByYear(year);
		}else if(jeosuji.equals("h")) {
			arr = bonSumRepo.findMonthlyInSumsHByYear(year);
		}else if(jeosuji.equals("i")) {
			arr = bonSumRepo.findMonthlyInSumsIByYear(year);
		}else if(jeosuji.equals("j")) {
			arr = bonSumRepo.findMonthlyInSumsJByYear(year);
		}else if(jeosuji.equals("l")) {
			arr = bonSumRepo.findMonthlyInSumsLByYear(year);
		}else if(jeosuji.equals("m")) {
			arr = bonSumRepo.findMonthlyInSumsMByYear(year);
		}else if(jeosuji.equals("n")) {
			arr = bonSumRepo.findMonthlyInSumsNByYear(year);
		}else if(jeosuji.equals("o")) {
			arr = bonSumRepo.findMonthlyInSumsOByYear(year);
		}else if(jeosuji.equals("p")) {
			arr = bonSumRepo.findMonthlyInSumsPByYear(year);
		}else if(jeosuji.equals("q")) {
			arr = bonSumRepo.findMonthlyInSumsQByYear(year);
		}else if(jeosuji.equals("s")) {
			arr = bonSumRepo.findMonthlyInSumsSByYear(year);
		}else if(jeosuji.equals("t")) {
			arr = bonSumRepo.findMonthlyInSumsTByYear(year);
		}else if(jeosuji.equals("u")) {
			arr = bonSumRepo.findMonthlyInSumsUByYear(year);
		}else if(jeosuji.equals("v")) {
			arr = bonSumRepo.findMonthlyInSumsVByYear(year);
		}else if(jeosuji.equals("w")) {
			arr = bonSumRepo.findMonthlyInSumsWByYear(year);
		}
		
		return arr;
	}
	
	public List<Double> findMonthlyHeightByYear(String year,String jeosuji){
		List<Double> arr = new ArrayList<>(); 
		if(jeosuji.equals("a") ) {
			arr = bonSumRepo.findMonthlyHeightAByYear(year);
		}else if(jeosuji.equals("b") ) {
			arr = bonSumRepo.findMonthlyHeightBByYear(year);
		}else if(jeosuji.equals("e")) {
			arr = bonSumRepo.findMonthlyHeightEByYear(year);
		}else if(jeosuji.equals("f")) {
			arr = bonSumRepo.findMonthlyHeightFByYear(year);
		}else if(jeosuji.equals("g")) {
			arr = bonSumRepo.findMonthlyHeightGByYear(year);
		}else if(jeosuji.equals("h")) {
			arr = bonSumRepo.findMonthlyHeightHByYear(year);
		}else if(jeosuji.equals("i")) {
			arr = bonSumRepo.findMonthlyHeightIByYear(year);
		}else if(jeosuji.equals("j")) {
			arr = bonSumRepo.findMonthlyHeightJByYear(year);
		}else if(jeosuji.equals("l")) {
			arr = bonSumRepo.findMonthlyHeightLByYear(year);
		}else if(jeosuji.equals("m")) {
			arr = bonSumRepo.findMonthlyHeightMByYear(year);
		}else if(jeosuji.equals("n")) {
			arr = bonSumRepo.findMonthlyHeightNByYear(year);
		}else if(jeosuji.equals("o")) {
			arr = bonSumRepo.findMonthlyHeightOByYear(year);
		}else if(jeosuji.equals("p")) {
			arr = bonSumRepo.findMonthlyHeightPByYear(year);
		}else if(jeosuji.equals("q")) {
			arr = bonSumRepo.findMonthlyHeightQByYear(year);
		}else if(jeosuji.equals("s")) {
			arr = bonSumRepo.findMonthlyHeightSByYear(year);
		}else if(jeosuji.equals("t")) {
			arr = bonSumRepo.findMonthlyHeightTByYear(year);
		}else if(jeosuji.equals("u")) {
			arr = bonSumRepo.findMonthlyHeightUByYear(year);
		}else if(jeosuji.equals("v")) {
			arr = bonSumRepo.findMonthlyHeightVByYear(year);
		}else if(jeosuji.equals("w")) {
			arr = bonSumRepo.findMonthlyHeightWByYear(year);
		}
		
		return arr;
	}
	
	public List<Double> findMonthlyOutSumsByYear(String year, String jeosuji){
		List<Double> arr = new ArrayList<>(); 
		if(jeosuji.equals("a") ) {
			arr = bonSumRepo.findMonthlySumsAByYear(year);
		}else if(jeosuji.equals("b") ) {
			System.out.println("here");
			arr = bonSumRepo.findMonthlySumsBByYear(year);
		}else if(jeosuji.equals("e")) {
			arr = bonSumRepo.findMonthlySumsEByYear(year);
		}else if(jeosuji.equals("f")) {
			arr = bonSumRepo.findMonthlySumsFByYear(year);
		}else if(jeosuji.equals("g")) {
			arr = bonSumRepo.findMonthlySumsGByYear(year);
		}else if(jeosuji.equals("h")) {
			arr = bonSumRepo.findMonthlySumsHByYear(year);
		}else if(jeosuji.equals("i")) {
			arr = bonSumRepo.findMonthlySumsIByYear(year);
		}else if(jeosuji.equals("j")) {
			arr = bonSumRepo.findMonthlySumsJByYear(year);
		}else if(jeosuji.equals("l")) {
			arr = bonSumRepo.findMonthlySumsLByYear(year);
		}else if(jeosuji.equals("m")) {
			arr = bonSumRepo.findMonthlySumsMByYear(year);
		}else if(jeosuji.equals("n")) {
			arr = bonSumRepo.findMonthlySumsNByYear(year);
		}else if(jeosuji.equals("o")) {
			arr = bonSumRepo.findMonthlySumsOByYear(year);
		}else if(jeosuji.equals("p")) {
			arr = bonSumRepo.findMonthlySumsPByYear(year);
		}else if(jeosuji.equals("q")) {
			arr = bonSumRepo.findMonthlySumsQByYear(year);
		}else if(jeosuji.equals("s")) {
			arr = bonSumRepo.findMonthlySumsSByYear(year);
		}else if(jeosuji.equals("t")) {
			arr = bonSumRepo.findMonthlySumsTByYear(year);
		}else if(jeosuji.equals("u")) {
			arr = bonSumRepo.findMonthlySumsUByYear(year);
		}else if(jeosuji.equals("v")) {
			arr = bonSumRepo.findMonthlySumsVByYear(year);
		}else if(jeosuji.equals("w")) {
			arr = bonSumRepo.findMonthlySumsWByYear(year);
		}
		
		return arr;
	}
	
	
	
	
	@Override
	public User2 getUser(String userLogInId) {
		User2 findUser = userRepo.findByUserLoginId(userLogInId);
		return findUser;
	}
	
	@Override
	public User2 registerUser(User2 user) {
		String userloginid = user.getUserLoginId();
		if(userRepo.findByUserLoginId(userloginid) == null) {	// 찾으니깐 없을 때 값을 저장
			User2 encodedUser =  User2.builder()
					.userLoginId(user.getUserLoginId())
					.userName(user.getUserName())
					.userPw(passwordEncoder.encode(user.getUserPw())) // 암호화해서 저장
					.build();	
			return userRepo.save(encodedUser);
		}
		
		return null;
	}
	
	// -1 일시 그날 값 주기
	@Override
	public List<BonA> getAFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		

//		return bonARepo.findBonAByDateBetween(dateArray.get(0), dateArray.get(1));
		
		System.out.println("date in getA : " + date );
		return bonARepo.findBonAByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonB> getBFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonBRepo.findBonBByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonC> getCFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonCRepo.findBonCByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	@Override
	public List<BonD> getDFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonDRepo.findBonDByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonE> getEFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonERepo.findBonEByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonF> getFFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonFRepo.findBonFByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonG> getGFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonGRepo.findBonGByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	
	@Override
	public List<BonH> getHFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonHRepo.findBonHByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonI> getIFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonIRepo.findBonIByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonJ> getJFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonJRepo.findBonJByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonK> getKFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonKRepo.findBonKByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonL> getLFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonLRepo.findBonLByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonM> getMFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonMRepo.findBonMByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonN> getNFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonNRepo.findBonNByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonO> getOFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonORepo.findBonOByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonP> getPFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonPRepo.findBonPByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonQ> getQFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonQRepo.findBonQByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonS> getSFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonSRepo.findBonSByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonT> getTFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonTRepo.findBonTByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonU> getUFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonURepo.findBonUByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonV> getVFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonVRepo.findBonVByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	@Override
	public List<BonW> getWFlowByDateContaining(String date, char jeosuji, int day){
		List<LocalDateTime> dateArray;
		dateArray = day == -1 ?  getDateTime(date) : getdaybeforeDateTime(date, day);
		return bonWRepo.findBonWByDateBetween(dateArray.get(0), dateArray.get(1));
	}
	
	
	
	

}
