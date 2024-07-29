package edu.pnu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import edu.pnu.domain.User;
import edu.pnu.persistence.BonARepository;
import edu.pnu.persistence.BonBRepository;
import edu.pnu.persistence.BonCRepository;
import edu.pnu.persistence.BonDRepository;
import edu.pnu.persistence.BonERepository;
import edu.pnu.persistence.BonFRepository;
import edu.pnu.persistence.BonGRepository;
import edu.pnu.persistence.BonHRepository;
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
//	@Override
//	public List<BonA> getFlow() {
//		List<BonA> BonFlows = new ArrayList<>();
//		
//		BonFlows = (List<BonA>) bonRepo.findAll(); 
////		BonFlows = bonRepo.flow();
//				
//		return BonFlows;
//	}
	
	
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
	

	
	@Override
	public User getUser(String userLogInId) {
		User findUser = userRepo.findByUserLoginId(userLogInId);
		return findUser;
	}
	
	@Override
	public User registerUser(User user) {
		String userloginid = user.getUserLoginId();
		if(userRepo.findByUserLoginId(userloginid) == null) {	// 찾으니깐 없을 때 값을 저장
			User encodedUser =  User.builder()
					.userLoginId(user.getUserLoginId())
					.userName(user.getUserName())
					.userPw(passwordEncoder.encode(user.getUserPw())) // 암호화해서 저장
					.build();	
			return userRepo.save(encodedUser);
		}
		
		return null;
	}

}
