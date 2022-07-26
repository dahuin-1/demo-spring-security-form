package me.whiteship.demospringsecurityform.form;

import java.security.Principal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.whiteship.demospringsecurityform.accont.AccountContext;
import me.whiteship.demospringsecurityform.accont.AccountRepository;
import me.whiteship.demospringsecurityform.common.SecurityLogger;

@Controller
public class SampleController {

	@Autowired
	public SampleService sampleService;

	@Autowired
	AccountRepository accountRepository;

	@GetMapping("/")
	public String index(Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("message", "Hello Spring Security");
		} else {
			model.addAttribute("message", "Hello" + principal.getName());

		}
		return "index";
	}

	@GetMapping("/info")
	public String info(Model model) {
		model.addAttribute("message", "info");
		return "info";
	}

	@GetMapping("/admin")
	public String admin(Model model, Principal principal) {
		model.addAttribute("message", "Hello" + principal.getName());
		return "admin";
	}

	@GetMapping("/user")
	public String user(Model model, Principal principal) {
		System.out.println("????");
		model.addAttribute("message", "Hello" + principal.getName());
		return "user";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
		sampleService.dashboard();
		model.addAttribute("message", "Hello Admin" + principal.getName());
		return "dashboard";
	}

	@GetMapping("/aysnc-handler")
	@ResponseBody
	public Callable<String> aysncHandler() {
		SecurityLogger.log("MVC");
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				SecurityLogger.log("Callable");
				return "Asncs Handler";
			}
		};
	}

	@GetMapping("/async-service")
	@ResponseBody
	public String asyncService() {
		SecurityLogger.log("MVC, before async service");
		sampleService.asyncService();
		SecurityLogger.log("MVC, after async service");
		return "Async Service";
	}

}
