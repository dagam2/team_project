package kr.com.controller;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RequestMapping("/mail/*")
public class MailController {
	
	@Autowired
	JavaMailSenderImpl mailSender;
	
	@Test 
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public void sendMail() throws Exception {
		
	       String subject = "test 메일";
	        String content = "메일 테스트 내용"+ "<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
	        String from = "legotravel@naver.com";
	        String to = "parkbyungtae91@naver.com";
	        
	        try {
	        	final MimeMessagePreparator preparator = new MimeMessagePreparator() {
	                
	                public void prepare(MimeMessage mimeMessage) throws Exception{
	                    final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	                    
	                    mailHelper.setFrom(from);
	                    mailHelper.setTo(to);
	                    mailHelper.setSubject(subject);
	                    mailHelper.setText(content, true);
	                }
	        };
	        mailSender.send(preparator);
	        
	        }catch(Exception e) {
	            e.printStackTrace();
	        }

}
}

//tls 1.0+1.1 java security에서 삭제하기
