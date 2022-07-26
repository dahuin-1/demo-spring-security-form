package me.whiteship.demospringsecurityform.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import me.whiteship.demospringsecurityform.accont.AccountService;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AccountService accountService;
	
//	public AccessDecisionManager accessDecisionManager() {
//		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
//
//		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
//		handler.setRoleHierarchy(roleHierarchy);
//
//		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
//		webExpressionVoter.setExpressionHandler(handler);
//		List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList();
//
//		return new AffirmativeBased(voters);
//
//	}

	public SecurityExpressionHandler expressionHandler() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		handler.setRoleHierarchy(roleHierarchy);

		return handler;
	}

//	// favicon.ico 요청을 무시하겠다.
//	@Override
//	public void configure(WebSecurity web) throws Exception {
////		web.ignoring().mvcMatchers("/favicon.ico"); //매번이렇 적어주는게 귀찮다..
//		//이렇게하면 더빠르다.
//		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.mvcMatchers("/","/info","/account/**","/signup").permitAll()
			.mvcMatchers("admin").hasRole("ADMIN")
			.mvcMatchers("user").hasRole("USER")
			.anyRequest().authenticated()
//			.accessDecisionManager(accessDecisionManager())
			.expressionHandler(expressionHandler());
		http.formLogin().loginPage("/login").permitAll()
			.and()
		.httpBasic();
		
//		token 기반 쿠키를 생성한다. username이들어간다. 언제까지 유효한지 remember-me-sample 이란 쿠키로 들어간다.
//		http.rememberMe().userDetailsService(accountService).key("remember-me-sample");
		
//		예외처리 하는 핸들러
//		http.exceptionHandling()
//			.accessDeniedPage("/access-denied");
		
//		예외처리 커스텀 마이
//		http.exceptionHandling()
//			.accessDeniedHandler(new AccessDeniedHandler() {
//				
//				@Override
//				public void handle(HttpServletRequest request, HttpServletResponse response,
//						AccessDeniedException accessDeniedException) throws IOException, ServletException {
//					UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//					String username = principal.getUsername();
//					System.out.println(username + " is denied to access "+request.getRequestURI());
//					response.sendRedirect("access-denied");
//				}
//			} );
		
		
//		 mvcMathcer , antMatcher , reqxMatcher등을 쓸수 있다.
//		permitAll , hasRole("USER"), hasAuthority("ROLE_USER")
//	fullyAuthenticated , anyRequest().not().익명 사용자만 허락
//		
		
//		RESTAPI 전략..세션을 쓰지 않겠다.
//		http.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
//		csrf를 꺼두겠다 옵션 기본적으로 만들어저 있다.form기반에서는 반드시 사용하는게 좋다 REST API에서는 번거로우니 꺼주자.
//		http.csrf().disable();
		
//	http.logout()
//		.logoutUrl("logout")
//		.logoutSuccessUrl(""/) // logou 성공시 어디로 갈지
//		;
		
//		하위 스레드에서 principal를 공유하게 해주는 spring의 기능.
//		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		
		// and 없이 http.formLogin() 가능
		
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("jeongmin").password("{noop}123").roles("USER").and()
//		.withUser("admin").password("{noop}!@#").roles("ADMIN");
//		//이값들이 DB에들어가고 이값들이 암호화해서 들어간다.
//		//{noop} <- 암호화를 하지않았다. 보통은 암호화를 한다.
//	}
	
	


}
