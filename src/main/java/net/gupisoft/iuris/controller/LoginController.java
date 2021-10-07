package net.gupisoft.iuris.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@AuthenticationPrincipal User usuario) {
		
		if (usuario != null) {
			System.out.println("Autenticação: " + usuario.toString());
			System.out.println("Autenticado");
			return "redirect:/";
		}
		
		return "Login";
	}
}
