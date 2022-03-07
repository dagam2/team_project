package com.vam.service;

import java.util.HashMap;

import com.vam.model.MemberVO;

public interface MemberService {

	//회원가입
	public void memberJoin(MemberVO member) throws Exception;
	// 아이디 중복 검사
	public int idCheck(String memberId) throws Exception;
    /* 로그인 */
    public MemberVO memberLogin(MemberVO member) throws Exception;
    /* 임시비밀번호 */
    public MemberVO memberfindpw(MemberVO member) throws Exception;
    public void updatepw(HashMap<String, String> updateMap);
    
    public String selectMemberID(HashMap<String, String> selectMap);
}