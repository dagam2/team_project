package com.vam.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.mapper.MemberMapper;
import com.vam.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper membermapper;

	@Override
	public void memberJoin(MemberVO member) throws Exception {
		
		membermapper.memberJoin(member);
		
	}
	@Override
	public int idCheck(String memberId) throws Exception {
		
		return membermapper.idCheck(memberId);
	}
    /* 로그인 */
    @Override
    public MemberVO memberLogin(MemberVO member) throws Exception {
        
        return membermapper.memberLogin(member);
    }

    public void memberPw(HashMap<String,String> updateMap) throws Exception {}
	@Override
	public MemberVO memberfindpw(MemberVO member) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updatepw(HashMap<String, String> updateMap) {
		// TODO Auto-generated method stub
		membermapper.updatepw(updateMap);
	}
	@Override
	public String selectMemberID(HashMap<String, String> selectMap) {
		// TODO Auto-generated method stub
		return membermapper.selectMemberID(selectMap);
	}
	
	}