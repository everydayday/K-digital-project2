package edu.pnu.service;

import java.time.LocalDateTime;
import java.util.List;

import edu.pnu.domain.Bon1;



public interface BonService {
//	
//	User getUser(String username);
//	User registerUser(User user);
	
	List<Bon1> getFlow();
	
	List<Bon1> getFlowByDateContaining(String date, char jeosuji);

}
