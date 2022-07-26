package me.whiteship.demospringsecurityform.accont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController{
	
	
//	TODO {noop}123 를 원한다
//	@Autowired
//	AccountRepository accountRepository;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/account/{role}/{username}/{password}")
	public Account createAccount(@ModelAttribute Account account) {
		return accountService.createNew(account);
	
	}

}
