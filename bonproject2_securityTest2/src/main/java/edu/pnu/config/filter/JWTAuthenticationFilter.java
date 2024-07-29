package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.User2;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	// 인증 객체
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,	// HttpServletRequest vs HttpSecurity
										HttpServletResponse response) throws AuthenticationException{
		
		System.out.println("here in Authentication");
		ObjectMapper mapper = new ObjectMapper();
		
		User2 member = null;
		
		try {
			// null 값인지 판단하는 듯
			member = mapper.readValue(request.getInputStream(), User2.class);
			Authentication authToken =new UsernamePasswordAuthenticationToken(member.getUserLoginId(), member.getUserPw());
			System.out.println("authToken " + authToken);
			System.out.println("member.getUserLoginId " + member.getUserLoginId());
			System.out.println("member.getUserPw" + member.getUserPw());
			Authentication auth = authenticationManager.authenticate(authToken);
			System.out.println("auth:" + auth);
			return auth;
		}catch(Exception e) {
			e.printStackTrace();
			// 원본에서 추가함
		}
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 자격 증명에 실패하면 응답코드 리턴
		
		return null;
		
		
//		return super.attemptAuthentication(request, response);
	}
	
	// 응답 성공 시, 실행되는 듯
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse
			response, FilterChain chain, Authentication authResult) throws IOException, ServletException{
		
		// 자격 증명이 성공하면 loadUserByUsername에서 만든 객체가 authResult에 담겨져 있다.
		User user = (User)authResult.getPrincipal();
		System.out.println("successfulAuthentication here");
		
		// User의 권한(roles)을 가져오기
	    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	    List<String> role = authorities.stream()
	                                    .map(GrantedAuthority::getAuthority)
	                                    .collect(Collectors.toList());
		
		String token = JWT.create()
						.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*100000))
						.withClaim("username", user.getUsername())
						.withClaim("role", role)
						.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		response.addHeader("Authorization", "Bearer " + token);
		response.setStatus(HttpStatus.OK.value());
		
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		
		System.out.println("this is token" + "Bearer " + token);
		Map<String, String> responseBody = new HashMap<>();
//	    responseBody.put("token", token);
	    responseBody.put("status", "success");
		
		
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonResponse = mapper.writeValueAsString(responseBody);
	    response.getWriter().write(jsonResponse);
	    response.getWriter().flush();
//		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	
	
}
