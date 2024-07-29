package edu.pnu.service;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import edu.pnu.persistence.SmsCertification;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final SmsCertification smsCertification;

    @Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String apiSecret;

    @Value("${coolsms.fromnumber}")
    private String fromNumber;

    private String createRandomNumber() {
        Random rand = new Random();
        String randomNum = "";
        for (int i = 0; i < 4; i++) {
            String random = Integer.toString(rand.nextInt(10));
            randomNum += random;
        }

        return randomNum;
    }

    private HashMap<String, String> makeParams(String to, String randomNum) {
        HashMap<String, String> params = new HashMap<>();
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("app_version", "test app 1.2");
        params.put("to", to);
        params.put("text", "(사람들)인증번호는 [" + randomNum + "]입니다.");
        return params;
    }

    // 인증번호 전송하기
    public String sendSMS(String phonNumber) {
        Message coolsms = new Message(apiKey, apiSecret);

        // 랜덤한 인증 번호 생성
        String randomNum = createRandomNumber();
        System.out.println(randomNum);
        smsCertification.saveValue("randomNum", randomNum);

        // 발신 정보 설정
        HashMap<String, String> params = makeParams(phonNumber, randomNum);

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            smsCertification.saveValue("phone", phonNumber); // 발송번호 저장하기
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        return "문자 전송이 완료되었습니다.";
    }
  //사용자가 입력한 인증번호가 Redis에 저장된 인증번호와 동일한지 확인
    public boolean verifySms(String num)  {
    	System.out.println("getPhone : " + smsCertification.getPhone());
    	System.out.println("getRandomNum : " + smsCertification.getRandomNum());
    	System.out.println("getCertification : " + smsCertification.getSmsCertification(smsCertification.getPhone()));
    	String sentNum = smsCertification.getRandomNum();
    	if(sentNum.equals(num)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    	/*
    	if (isVerify(num)) {
//            throw new AuthenticationNumberMismatchException("인증번호가 일치하지 않습니다.");
        	System.out.println("인증번호가 일치하지 않습니다.");
        	return false; 
        }
//        smsCertification.removeSmsCertification(requestDto.getPhone());
        // /추후 인증번호 삭제용 코드 살리기
        return true;
        */
    }
    
//    https://green-bin.tistory.com/69
    private boolean isVerify(String num) {
    	
        return !smsCertification.getSmsCertification(smsCertification.getPhone())
                .equals(num);
    }
    
    
}
