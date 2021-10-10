package com.proj.resumy.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    RequestCache requestCache = new HttpSessionRequestCache();


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		
		String targetUrl = "/";
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if (savedRequest != null) {
			targetUrl = savedRequest.getRedirectUrl();
		}
		
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
		if (roles.contains("ROLE_ADMIN")) {
			request.getSession(false).setMaxInactiveInterval(3600);
		} else {
			request.getSession(false).setMaxInactiveInterval(7200);
		}
		
		// Your login success url goes here, currently login success url="/"
		//response.sendRedirect(request.getContextPath());
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
}