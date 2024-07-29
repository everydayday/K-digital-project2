package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.persistence.UserRepository;



@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired private UserRepository memRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		edu.pnu.domain.User2 member = memRepo.findById(username)
								.orElseThrow(()->new UsernameNotFoundException("Not Found!"));
		// User 형식으로 주기
		return new User(member.getUserLoginId(), member.getUserPw(),
						AuthorityUtils.createAuthorityList(member.getUserRole().toString()));
		
	}
	
}
