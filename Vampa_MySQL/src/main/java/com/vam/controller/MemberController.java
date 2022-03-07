package com.vam.controller;

import java.util.HashMap;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.MemberVO;
import com.vam.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberservice;
    @Autowired
    private JavaMailSender mailSender;

	//회원가입 페이지 이동
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void joinGET() {
		
		logger.info("회원가입 페이지 진입");
				
	}
	//회원가입
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPOST(MemberVO member) throws Exception{
		
		logger.info("join 진입");
		
		// 회원가입 서비스 실행
		memberservice.memberJoin(member);
		
		logger.info("join Service 성공");
        
        /* 회원가입 쿼리 실행 */
		return "redirect:/main";
	}
	
	//로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void loginGET() {
		
		logger.info("로그인 페이지 진입");
		
	}
	//비밀번호 찾기 페이지 이동
	@RequestMapping(value = "findpw", method = RequestMethod.GET)
	public void findpwGET() {
		
		logger.info("비밀번호찾기 페이지 진입");
		
	}
	// 아이디 중복 검사
	@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception{
		
		logger.info("memberIdChk() 진입");
		
		int result = memberservice.idCheck(memberId);
		
		logger.info("결과값 = " + result);
		
		if(result != 0) {
			
			return "fail";	// 중복 아이디가 존재
			
		} else {
			
			return "success";	// 중복 아이디 x
			
		}	
		
	} // memberIdChkPOST() 종료
    /* 이메일 인증 */
    @RequestMapping(value="/mailCheck", method=RequestMethod.GET)
    @ResponseBody
    public String mailCheckGET(String email) throws Exception{
        
        /* 뷰(View)로부터 넘어온 데이터 확인 */
        logger.info("이메일 데이터 전송 확인");
        logger.info("인증번호 : " + email);
        /* 인증번호(난수) 생성 */
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        logger.info("인증번호 " + checkNum);
        /* 이메일 보내기 */
        String setFrom = "ksm5654f2@naver.com";
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content = 
                "홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" + 
                "인증 번호는 " + checkNum + "입니다." + 
                "<br>" + 
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        String num = Integer.toString(checkNum);
        return num;
    }
    /* 로그인 */
    @RequestMapping(value="login", method=RequestMethod.POST)
    public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
        
        //System.out.println("login 메서드 진입");
        //System.out.println("전달된 데이터 : " + member);
        
        HttpSession session = request.getSession();
        MemberVO lvo = memberservice.memberLogin(member);
        
        if(lvo == null) {                                // 일치하지 않는 아이디, 비밀번호 입력 경우
            
            int result = 0;
            rttr.addFlashAttribute("result", result);
            return "redirect:/member/login";
            
        }
        
        session.setAttribute("member", lvo);             // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
        
        return "redirect:/main";
    }    
    
    /* 메인페이지 로그아웃 */
    @RequestMapping(value="logout.do", method=RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{
        
        logger.info("logoutMainGET메서드 진입");
        
        HttpSession session = request.getSession();
        session.invalidate();
        
        return "redirect:/main";  
    }
    
    /* 임시비밀번호 이메일전송 */
    @RequestMapping(value="/mailCheck2", method=RequestMethod.GET)
    @ResponseBody
    public String mailCheck2GET(String email, String memberId, String result) throws Exception{

        HashMap<String, String> updateMap = new HashMap<>();
        HashMap<String, String> selectMap = new HashMap<>();

        /* 뷰(View)로부터 넘어온 데이터 확인 */
        logger.info("이메일 데이터 전송 확인");
        logger.info("이메일 : " + email);
        logger.info("id : " + memberId);
         
        selectMap.put("memberId", memberId);
        selectMap.put("email", email);
        String getId = memberservice.selectMemberID(selectMap);
        
        if(getId == null){ // id email 불일치
        	logger.info("아이디 이메일 불일치");
        	return "false";
        }else{ // 일치
        	logger.info("mailCheck2GET getId : "+getId);

            /* 비밀번호(난수) 생성 */
    		String checkNum2 = "";
    		for (int i = 0; i < 12; i++) {
    			checkNum2 += (char) ((Math.random() * 26) + 97);
    		}
            logger.info("임시 비밀번호 " + checkNum2);
            updateMap.put("memberPw", checkNum2);
            updateMap.put("memberId", memberId);

            
            /* 이메일 보내기 */
            String setFrom = "ksm5654f2@naver.com";
            String toMail = email;
            String title = "임시비밀번호 발급 입니다.";
            String content = 
                    "임시비밀번호 입니다." +
                    "<br><br>" + 
                    "비밀번호는 " + checkNum2 + "입니다." + 
                    "<br>" + 
                    "임시비밀번호로 로그인하여주세요.";
            memberservice.updatepw(updateMap);
            logger.info("확인 ");
            
            try {
                
                MimeMessage message2 = mailSender.createMimeMessage();
                MimeMessageHelper helper2 = new MimeMessageHelper(message2, true, "utf-8");
                helper2.setFrom(setFrom);
                helper2.setTo(toMail);
                helper2.setSubject(title);
                helper2.setText(content,true);
                mailSender.send(message2);
                
            }catch(Exception e) {
                e.printStackTrace();
            }
        	return "true";
        }		

        

    
        
    }
    
    
    
}