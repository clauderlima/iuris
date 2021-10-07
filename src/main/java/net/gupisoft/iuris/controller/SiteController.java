package net.gupisoft.iuris.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site")
public class SiteController {

	@GetMapping
	public String inicio() {
		return "site/Index";
	}
}
