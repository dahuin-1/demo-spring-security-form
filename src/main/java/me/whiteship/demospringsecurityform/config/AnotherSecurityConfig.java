package me.whiteship.demospringsecurityform.config;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configurable
//@Order(Ordered.LOWEST_PRECEDENCE -100)  // order로 필터에 먼저들어갈 순서를 정해 줄 수 있
//public class AnotherSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.mvcMatchers("/","/info","/account/**").permitAll()
//			.mvcMatchers("admin").hasRole("ADMIN")
//			.anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.and()
//		.httpBasic();
//		
//		// and 없이 http.formLogin() 가능
//		
//	}
//
//
//}
