package me.whiteship.demospringsecurityform.form;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import me.whiteship.demospringsecurityform.accont.Account;
import me.whiteship.demospringsecurityform.accont.AccountContext;
import me.whiteship.demospringsecurityform.common.SecurityLogger;

@Service
public class SampleService {
	public void dashboard() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		// 인증을 한 사용자를 나타낸다
//		// 사용자의 권한은 authentication이
//		Object principal = authentication.getPrincipal();
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		Object credentials = authentication.getCredentials();
//		boolean authenticated = authentication.isAuthenticated();

		// Thread에서 가져와서 쓴다.
		Account account = AccountContext.getAccount();

		// 아니면 그냥 꺼내쓴다. 스레드 로컬을 쓸필요가없
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		
		System.out.println("==========");
		// 두개가 같은 효과임 스레드 로컬 안만들고 authetication 에서 받아서 쓴다. 
		// 한번 인증된 사용자는 똑같은 authetication 해시맵이 똑같음..
		System.out.println(account.getUsername());
		System.out.println(userDetails.getUsername());
	}
	
	@Async
	public void asyncService() {
		// TODO Auto-generated method stub
		SecurityLogger.log("Async Service");
		System.out.println("Async service is called");
		
	}
}
