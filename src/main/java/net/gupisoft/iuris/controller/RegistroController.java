package net.gupisoft.iuris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Captacao;
import net.gupisoft.iuris.domain.service.CaptacaoService;


@Controller
@RequestMapping("/registro")
public class RegistroController {
	
	@Autowired
	private CaptacaoService captacaoService;
	
	@GetMapping
	public ModelAndView registro() {
		return formulario(new Captacao());
	}
	
	public ModelAndView formulario(Captacao captacao) {
		ModelAndView mv = new ModelAndView("sistema/Registro");
		mv.addObject("captacao", captacao);
		return mv;
	}
	
	@PostMapping
	public ModelAndView cadastrar(@Valid Captacao captacao, BindingResult result, RedirectAttributes attributes) {
		String msg = null;
		System.out.println("Erro: " + result.getErrorCount());
		if (result.hasErrors()) {
			System.out.println("Erro: " + result.getErrorCount());
			return formulario(captacao);
		}
		
		try {
			msg = captacaoService.gravar(captacao);			
		} catch (RuntimeException e) {
			result.reject(e.getMessage());
			return formulario(captacao);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		return new ModelAndView("redirect:/registro/sucesso");
	}
	
	@GetMapping("/sucesso")
	public String sucesso() {
		return "CadastroSucesso";
	}
	
}
