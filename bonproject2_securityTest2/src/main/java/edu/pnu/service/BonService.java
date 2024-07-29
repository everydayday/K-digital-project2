package edu.pnu.service;

import java.time.LocalDateTime;
import java.util.List;

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



public interface BonService {
//	
//	User getUser(String username);
//	User registerUser(User user);
	
//	List<BonA> getFlow();

	BonHeightAvg findMostRecentHeight(LocalDateTime startDate, LocalDateTime endDate);
	
	BonJ findBonJHeight(LocalDateTime date);
	
	
	List<BonA> getAFlowByDateContaining(String date, char jeosuji, int day);
	List<BonB> getBFlowByDateContaining(String date, char jeosuji, int day);
	List<BonC> getCFlowByDateContaining(String date, char jeosuji, int day);
	List<BonD> getDFlowByDateContaining(String date, char jeosuji, int day);
	List<BonE> getEFlowByDateContaining(String date, char jeosuji, int day);
	List<BonF> getFFlowByDateContaining(String date, char jeosuji, int day);
	List<BonG> getGFlowByDateContaining(String date, char jeosuji, int day);
	List<BonH> getHFlowByDateContaining(String date, char jeosuji, int day);
	List<BonI> getIFlowByDateContaining(String date, char jeosuji, int day);
	List<BonJ> getJFlowByDateContaining(String date, char jeosuji, int day);
	List<BonK> getKFlowByDateContaining(String date, char jeosuji, int day);
	List<BonL> getLFlowByDateContaining(String date, char jeosuji, int day);
	List<BonM> getMFlowByDateContaining(String date, char jeosuji, int day);
	List<BonN> getNFlowByDateContaining(String date, char jeosuji, int day);
	List<BonO> getOFlowByDateContaining(String date, char jeosuji, int day);
	List<BonP> getPFlowByDateContaining(String date, char jeosuji, int day);
	List<BonQ> getQFlowByDateContaining(String date, char jeosuji, int day);
	List<BonS> getSFlowByDateContaining(String date, char jeosuji, int day);
	List<BonT> getTFlowByDateContaining(String date, char jeosuji, int day);
	List<BonU> getUFlowByDateContaining(String date, char jeosuji, int day);
	List<BonV> getVFlowByDateContaining(String date, char jeosuji, int day);
	List<BonW> getWFlowByDateContaining(String date, char jeosuji, int day);
	
	List<BonSum> getSumFlowsByDateContaining(String date);
	
	List<LocalDateTime> getdaybeforeDateTime(String datetime, int beforeDay);
	User2 getUser(String username);
	User2 registerUser(User2 user);
	
	// 일별 유출량 합
	List<Double> getDailyOutflowSumByMonth(String year,String month, String jeosuji);
	// 일별 유입량 합
	List<Double> getDailyInflowSumByMonth(String year,String month, String jeosuji);
	// 일별 수위 평균
	List<Double> getDailyHeightAvgByMonth(String year, String month, String jeosuji);
	
	
	// 월별
	List<Double> findMonthlyInSumsByYear(String year,String jeosuji);
	List<Double> findMonthlyHeightByYear(String year,String jeosuji);
	List<Double> findMonthlyOutSumsByYear(String year, String jeosuji);
//	List<Double> getDayInflowSumPerMonth(String year);
	
}
