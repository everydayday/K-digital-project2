package edu.pnu.persistence;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class SmsCertification {
	private final String PREFIX = "sms:"; // key값이 중복되지 않도록 상수 선언
    private final int LIMIT_TIME = 100000 * 60; // 인증번호 유효 시간

    private final StringRedisTemplate stringRedisTemplate;

    // Redis에 저장
    public void createSmsCertification(String phone, String certificationNumber) {
        stringRedisTemplate.opsForValue()
                .set(PREFIX + phone, certificationNumber, Duration.ofSeconds(LIMIT_TIME));
        
    }

    // 휴대전화 번호에 해당하는 인증번호 불러오기
    public String getSmsCertification(String phone) {
        return stringRedisTemplate.opsForValue().get(PREFIX + phone);
    }

    // 인증 완료 시, 인증번호 Redis에서 삭제
    public void deleteSmsCertification(String phone) {
        stringRedisTemplate.delete(PREFIX + phone);
    }

    // Redis에 해당 휴대번호로 저장된 인증번호가 존재하는지 확인
    public boolean hasKey(String phone) {
        return stringRedisTemplate.hasKey(PREFIX + phone);
    }
    
 // Redis에 발송한 전화번호 저장하기
    public void saveValue(String key, String value) {
        ValueOperations<String, String> values = stringRedisTemplate.opsForValue();
        values.set(key , value);
    }
    
    public String getPhone() {
    	ValueOperations<String, String> values = stringRedisTemplate.opsForValue();
    	String phone;
    	try {
    		phone = values.get("phone");
    		return phone;
    	}
    	catch(Exception e) {
    		return null;
    	}
    	
    }
    
    public String getRandomNum() {
    	ValueOperations<String, String> values = stringRedisTemplate.opsForValue();
    	String randomNum;
    	try {
    		randomNum = values.get("randomNum");
    		return randomNum;
    	}
    	catch(Exception e) {
    		
    		return null;
    	}
    	
    }
    
    
    
}
