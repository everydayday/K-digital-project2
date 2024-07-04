package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(security-> security
				.requestMatchers("/**").permitAll());
				//.anyRequest().permitAll());
		
		http.csrf(cf->cf.disable());
		http.cors(c->c.configurationSource(corsSource()));
		
		
		http.formLogin(form->form
				.loginPage("/")	//  loginPage() : 스프링부트가 제공하는 로그인 화면이 아닌 사용자가 작성한 로그인 화면으로 가겠다.
				.loginProcessingUrl("/main") // 로그인 처리 경로 (로그인 성공 시 어디로 갈 것인가.)  // 해당 Url로 요청이 될 시 SpringSecurity가 직접 알아서 로그인 과정을 진행해준다.
				.defaultSuccessUrl("/loginSuccess", true)
		);
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout->logout
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/login"));	
		
		http.headers(hr->hr.frameOptions(fo->fo.disable()));
		return http.build();
		
	
	}
	
	private CorsConfigurationSource corsSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOriginPattern(CorsConfiguration.ALL) ; // 요청을 허용할 서버
		config.addAllowedMethod(CorsConfiguration.ALL) ; // 요청을 허용할 Method
		config.addAllowedHeader(CorsConfiguration.ALL); // 요청을 허용할 Header
		config.setAllowCredentials(true); // 요청/응답에 자격증명정보 포함을 허용
		// true인 경우 addAllowedOrigin(“*”)는 사용 불가 ➔ Pattern으로 변경
		config.addExposedHeader(CorsConfiguration.ALL);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config); // 위 설정을 적용할 Rest 서버의 URL 패턴 설정
		return source;
	}
	
}
