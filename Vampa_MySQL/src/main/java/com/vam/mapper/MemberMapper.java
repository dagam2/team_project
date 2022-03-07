package com.vam.mapper;

import java.util.HashMap;

import com.vam.model.MemberVO;

public interface MemberMapper {

	/* 회원가입 */
	public void memberJoin(MemberVO member);

	/* 아이디 중복 검사 */
	public int idCheck(String memberId);
	
    /* 로그인 */
    public MemberVO memberLogin(MemberVO member);
	
	/* 회원 주소 정보 */
	public MemberVO getMemberInfo(String memberId);		
	
    /* 비밀번호찾기 */
    public MemberVO memberfindpw(MemberVO member);
    public void updatepw(HashMap<String, String> updateMap);
    
    public String selectMemberID(HashMap<String, String> selectMap);
	
}
