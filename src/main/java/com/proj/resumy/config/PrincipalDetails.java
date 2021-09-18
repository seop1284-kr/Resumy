package com.proj.resumy.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.proj.resumy.domain.MemberDTO;
import com.proj.resumy.service.MemberService;


//시큐리티가 /loginOk 주소요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인 진행이 완료되면 '시큐리티 session' 에 넣어주게 된다. 
//우리가 익히 알고 있는 같은 session 공간이긴 한데..
//시큐리티가 자신이 사용하기 위한 공간을 가집니다. 
//=> Security ContextHolder 라는 키값에다가 session 정보를 저장합니다.
//여기에 들어갈수 있는 객체는 Authentication 객체이어야 한다.
//Authentication 안에 User 정보가 있어야 됨. 
//User 정보 객체는 ==> UserDetails 타입 객체이어야 한다.

//따라서 로그인한 User 정보를 꺼내려면
//Security Session 에서 
//   => Authentication 객체를 꺼내고, 그 안에서
//        => UserDetails 정보를 꺼내면 된다.


//이건 @ 안해도 되나? ==> 나중에 강제로 띄우게 될겁니다. new 를 통해서!
public class PrincipalDetails implements UserDetails{

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	private MemberDTO user;
	
	public PrincipalDetails(MemberDTO user) {
		System.out.println("PrincipalDetails(user) 생성: " + user);
		this.user = user;
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("getAuthorities() 호출");
		
		Collection<GrantedAuthority> collect = new ArrayList<>();
		
		List<String> list = memberService.selectAuthoritiesById(user.getUserid());
		
		for(String auth : list) {
			collect.add(new GrantedAuthority() {
				
				@Override
				public String getAuthority() {
					return auth;
				}
			});
		}		
		
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPw();
	}

	@Override
	public String getUsername() {
		return user.getUserid();
	}

	// 계정이 만료된건 아닌지?
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠긴건 아닌지?
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정 credential 이 만료된건 아닌지?
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화 되었는지?
	@Override
	public boolean isEnabled() {
		return true;
		// ex)
		// 사이트에서 1년동안 회원이 로그인을 안하면 휴면계정으로 하기로 했다면?
		// 현재시간 - 로그인시간 => 1년을 초과하면 false  
	}

}










