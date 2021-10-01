package com.proj.resumy.mng.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.resumy.domain.MemberDAO;
import com.proj.resumy.domain.MemberDTO;
import com.proj.resumy.mng.member.domain.AjaxMngMemberDAO;

@Service
public class AjaxMngMemberService {
	AjaxMngMemberDAO ajaxMngMemberDao;
	MemberDAO memberDao;
	
	@Autowired
	public void setAjaxMngMemberDao(AjaxMngMemberDAO ajaxMngMemberDao) {
		this.ajaxMngMemberDao = ajaxMngMemberDao;
	}
	
	@Autowired
	public void setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}
	
	public List<MemberDTO> list(int from, int pageRows) {
		return ajaxMngMemberDao.selectMemberFromRow(from, pageRows);
	}
	
	public int count() {
		return ajaxMngMemberDao.countAllMember();
	}
	
	public int deleteByUid(int [] uids) {
		return ajaxMngMemberDao.deleteByUid(uids);
	}
}
