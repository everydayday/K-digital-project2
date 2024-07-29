package edu.pnu.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.BonA;
import edu.pnu.domain.BonSum;

// 하이버네이트가 인터페이스 코드 만들면서 자동으로 어노테이션 올려줌
// 명시적으로 표시함
@Repository
public interface BonSumRepository extends JpaRepository<BonSum, LocalDateTime> {

	
	
	List<BonSum> findBonSumByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	// Inflow
	
	
	// 일별 유출유량 합
	@Query( "select sum(a) from BonSum bs where MONTH(bs.date) = :month and YEAR(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsAByMonth(String year, String month);	
	@Query( "select sum(b) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsBByMonth(String year, String month);
	@Query( "select sum(e) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsEByMonth(String year, String month);
	@Query( "select sum(f) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsFByMonth(String year, String month);
	@Query( "select sum(g) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsGByMonth(String year, String month);
	@Query( "select sum(h) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsHByMonth(String year, String month);
	@Query( "select sum(i) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsIByMonth(String year, String month);
	@Query( "select sum(j) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsJByMonth(String year, String month);
	@Query( "select sum(l) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsLByMonth(String year, String month);
	@Query( "select sum(m) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsMByMonth(String year, String month);
	@Query( "select sum(n) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsNByMonth(String year, String month);
	@Query( "select sum(o) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsOByMonth(String year, String month);
	@Query( "select sum(p) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsPByMonth(String year, String month);
	@Query( "select sum(q) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsQByMonth(String year, String month);
	@Query( "select sum(s) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsSByMonth(String year, String month);
	@Query( "select sum(t) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsTByMonth(String year, String month);
	@Query( "select sum(u) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsUByMonth(String year, String month);
	@Query( "select sum(v) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsVByMonth(String year, String month);
	@Query( "select sum(w) from BonSum bs where MONTH(bs.date) = :month and Year(bs.date) = :year group by DAY(bs.date)" )
	List<Double> findDailyOutSumsWByMonth(String year, String month);
	
	// 일별 유입유량 합
	@Query("select sum(a) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year  group by DAY(bis.date) ")
	List<Double> findDailyInSumsAByYear(String year, String month);
	@Query("select sum(b) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsBByYear(String year, String month);
	@Query("select sum(e) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsEByYear(String year, String month);
	@Query("select sum(f) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsFByYear(String year, String month);
	@Query("select sum(g) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsGByYear(String year, String month);
	@Query("select sum(h) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsHByYear(String year, String month);
	@Query("select sum(i) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsIByYear(String year, String month);
	@Query("select sum(j) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsJByYear(String year, String month);
	@Query("select sum(l) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsLByYear(String year, String month);
	@Query("select sum(m) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsMByYear(String year, String month);
	@Query("select sum(n) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsNByYear(String year, String month);
	@Query("select sum(o) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsOByYear(String year, String month);
	@Query("select sum(p) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsPByYear(String year, String month);
	@Query("select sum(q) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsQByYear(String year, String month);
	@Query("select sum(s) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsSByYear(String year, String month);
	@Query("select sum(t) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsTByYear(String year, String month);
	@Query("select sum(u) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsUByYear(String year, String month);
	@Query("select sum(v) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsVByYear(String year, String month);
	@Query("select sum(w) from BonInSum bis where MONTH(bis.date) = :month and Year(bis.date) = :year group by DAY(bis.date) ")
	List<Double> findDailyInSumsWByYear(String year, String month);
	
	// 일별 수위 평균
	@Query("select AVG(a) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightAByYear(String year, String month);
	@Query("select AVG(b) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightBByYear(String year, String month);
	@Query("select AVG(e) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightEByYear(String year, String month);
	@Query("select AVG(f) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightFByYear(String year, String month);
	@Query("select AVG(g) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightGByYear(String year, String month);
	@Query("select AVG(h) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightHByYear(String year, String month);
	@Query("select AVG(i) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightIByYear(String year, String month);
	@Query("select AVG(j) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightJByYear(String year, String month);
	@Query("select AVG(l) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightLByYear(String year, String month);
	@Query("select AVG(m) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightMByYear(String year, String month);
	@Query("select AVG(n) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightNByYear(String year, String month);
	@Query("select AVG(o) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightOByYear(String year, String month);
	@Query("select AVG(p) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightPByYear(String year, String month);
	@Query("select AVG(q) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightQByYear(String year, String month);
	@Query("select AVG(s) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightSByYear(String year, String month);
	@Query("select AVG(t) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightTByYear(String year, String month);
	@Query("select AVG(u) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightUByYear(String year, String month);
	@Query("select AVG(v) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightVByYear(String year, String month);
	@Query("select AVG(w) from BonHeightAvg bavg  where MONTH(bavg.date) = :month and YEAR(bavg.date) = :year group by DAY(bavg.date) ")
	List<Double> findDailyHeightWByYear(String year, String month);
	
	
	
	// 월별	
	// -height
	@Query("select AVG(a) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightAByYear(String year);
	@Query("select AVG(b) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightBByYear(String year);
	@Query("select AVG(e) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightEByYear(String year);
	@Query("select AVG(f) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightFByYear(String year);
	@Query("select AVG(g) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightGByYear(String year);
	@Query("select AVG(h) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightHByYear(String year);
	@Query("select AVG(i) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightIByYear(String year);
	@Query("select AVG(j) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightJByYear(String year);
	@Query("select AVG(l) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightLByYear(String year);
	@Query("select AVG(m) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightMByYear(String year);
	@Query("select AVG(n) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightNByYear(String year);
	@Query("select AVG(o) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightOByYear(String year);
	@Query("select AVG(p) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightPByYear(String year);
	@Query("select AVG(q) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightQByYear(String year);
	@Query("select AVG(s) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightSByYear(String year);
	@Query("select AVG(t) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightTByYear(String year);
	@Query("select AVG(u) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightUByYear(String year);
	@Query("select AVG(v) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightVByYear(String year);
	@Query("select AVG(w) from BonHeightAvg bavg  where YEAR(bavg.date) = :year group by MONTH(bavg.date) ")
	List<Double> findMonthlyHeightWByYear(String year);
	
	// -inflow
	@Query("select sum(a) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsAByYear(String year);
	@Query("select sum(b) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsBByYear(String year);
	@Query("select sum(e) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsEByYear(String year);
	@Query("select sum(f) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsFByYear(String year);
	@Query("select sum(g) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsGByYear(String year);
	@Query("select sum(h) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsHByYear(String year);
	@Query("select sum(i) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsIByYear(String year);
	@Query("select sum(j) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsJByYear(String year);
	@Query("select sum(l) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsLByYear(String year);
	@Query("select sum(m) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsMByYear(String year);
	@Query("select sum(n) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsNByYear(String year);
	@Query("select sum(o) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsOByYear(String year);
	@Query("select sum(p) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsPByYear(String year);
	@Query("select sum(q) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsQByYear(String year);
	@Query("select sum(s) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsSByYear(String year);
	@Query("select sum(t) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsTByYear(String year);
	@Query("select sum(u) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsUByYear(String year);
	@Query("select sum(v) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsVByYear(String year);
	@Query("select sum(w) from BonInSum bis where YEAR(bis.date) = :year group by MONTH(bis.date) ")
	List<Double> findMonthlyInSumsWByYear(String year);
	// -outflow
	@Query( "select sum(a) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsAByYear(String year);
	@Query( "select sum(b) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsBByYear(String year);
	@Query( "select sum(e) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsEByYear(String year);
	@Query( "select sum(f) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsFByYear(String year);
	@Query( "select sum(g) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsGByYear(String year);
	@Query( "select sum(h) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsHByYear(String year);
	@Query( "select sum(i) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsIByYear(String year);
	@Query( "select sum(j) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsJByYear(String year);
	@Query( "select sum(l) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsLByYear(String year);
	@Query( "select sum(m) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsMByYear(String year);
	@Query( "select sum(n) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsNByYear(String year);
	@Query( "select sum(o) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsOByYear(String year);
	@Query( "select sum(p) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsPByYear(String year);
	@Query( "select sum(q) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsQByYear(String year);
	@Query( "select sum(s) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsSByYear(String year);
	@Query( "select sum(t) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsTByYear(String year);
	@Query( "select sum(u) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsUByYear(String year);
	@Query( "select sum(v) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsVByYear(String year);
	@Query( "select sum(w) from BonSum bs where YEAR(bs.date) = :year group by MONTH(bs.date)" )
	List<Double> findMonthlySumsWByYear(String year);
	
	
	

}
