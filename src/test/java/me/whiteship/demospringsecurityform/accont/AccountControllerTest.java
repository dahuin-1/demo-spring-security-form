package me.whiteship.demospringsecurityform.accont;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AccountService accountService;

	@Test
	@WithAnonymousUser
	public void index_anonymous() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}
	
	// Custorm 어노테이션
	@Test
	@WithUser
	public void index_user() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="keesun",roles="ADMIN")
	public void index_admin() throws Exception {
		mockMvc.perform(get("/admin"))
				.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void login() throws Exception{
		Account account = new Account();
		account.setUsername("keesun");
		account.setPassword("123");
		account.setRole("USER");
		accountService.createNew(account);
		
		mockMvc.perform(formLogin().user("keesun").password("123"))
		.andExpect(authenticated());
	}
	@Test
	@Transactional
	public void login2() throws Exception{
		Account account = new Account();
		account.setUsername("keesun");
		account.setPassword("123");
		account.setRole("USER");
		accountService.createNew(account);
		
		mockMvc.perform(formLogin().user("keesun").password("123"))
		.andExpect(authenticated());
	}
}
