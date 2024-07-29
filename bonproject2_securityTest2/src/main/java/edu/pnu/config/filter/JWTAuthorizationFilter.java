package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


import edu.pnu.persistence.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
	private final UserRepository memberRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain) throws IOException, ServletException{
		System.out.println("here in AuthorizationFilter");
		String srcToken = request.getHeader("Authorization");	// 요청 헤더에서 Authorization을 얻어온다.
		if(srcToken == null || !srcToken.startsWith("Bearer ")) {	// 없거나 "Bearer"로 시작하지 않는다면
			System.out.println("here in authorizationfilter if statement ");
			filterchain.doFilter(request, response);				// 필터를 그냥 통과
			return;
		}
		String jwtToken = srcToken.replace("Bearer ", "");		// 토큰에서 "Bearer"를 제거
		
		// 토큰에서 username 추출
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwt")).build()
								.verify(jwtToken).getClaim("username").asString();
		
		System.out.println("username: in AuthorizationFilter" + username);
		Optional<edu.pnu.domain.User2> opt = memberRepository.findById(username);	// 토큰에서 얻은 username으로 DB를 검색해서 사용자를 검색
		if(!opt.isPresent()) {
			filterchain.doFilter(request, response);
			return;
			
		}
		
		edu.pnu.domain.User2 findmember = opt.get();
		
		System.out.println("findmember.getUserLoginId : " + findmember.getUserLoginId());
		// DB에서 읽은 사용자 정보를 이용해서 UserDetails 타입의 객체를 생성
		User user = new User(findmember.getUserLoginId(), findmember.getUserPw(),
							AuthorityUtils.createAuthorityList(findmember.getUserRole().toString()));
		
		// Authentication 객체를 생성 : 사용자명과 권한 관리를 위한 정보를 입력(암호는 필요 없음) 
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// 시큐리티 세션에 등록한다.
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterchain.doFilter(request, response);
		
		System.out.println("End in AuthorizationFilter");
		
		
	}
	
	
}
