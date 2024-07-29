package edu.pnu.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.BonHeightAvg;

@Repository
public interface BonHeightAvgRepository extends JpaRepository<BonHeightAvg, LocalDateTime> {
	
//	@Query("SELECT * FROM BonHeightAvg E WHERE E.date = (SELECT MAX(E2.date) FROM BonHeightAvg E2)")
//	List<Double> findMostRecentHeight();
	
//	@Query("SELECT a FROM water_height_view E WHERE E.height_date = (SELECT MAX(E2.height_date) FROM water_height_view E2)")
//	BonHeightAvg anything();
	
	// 이 방법 안 됨..
//	@Query("SELECT bh FROM BonHeightAvg bh")
//	BonHeightAvg anything();	

	// ok
//	BonHeightAvg findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	BonHeightAvg findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
