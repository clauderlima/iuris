package net.gupisoft.iuris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Captacao;
import net.gupisoft.iuris.domain.service.CaptacaoService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;

@Controller
@RequestMapping("/captacao")
public class CaptacaoControler {
	
	@Autowired
	private CaptacaoService captacaoService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_CLIENTE_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/CaptacaoListar");
		
		return mv;
	}
	
	// Novo
	@GetMapping("/novo")
	@Secured("ROLE_CLIENTE_INCLUIR")
	public ModelAndView novo(Captacao captacao) {
		ModelAndView mv = formulario(captacao);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_CLIENTE_VER")
	public ModelAndView editar(@PathVariable("id") Captacao captacao) {
		ModelAndView mv = formulario(captacao);
		return mv;
	}
	
	// Ficha
	@GetMapping("/ficha/{id}")
	@Secured("ROLE_CLIENTE_VER")
	public ModelAndView mostrar(@PathVariable("id") Captacao captacao) {

		ModelAndView mv = new ModelAndView("sistema/CaptacaoFicha");
		mv.addObject("captacao", captacaoService.buscar(captacao.getId()));
		mv.addObject("historicos", captacaoService.buscarHistoricos(captacao));
		return mv;
	}
	
	// Incluir
	@RequestMapping(value = {"", "{\\d+}"}, method = RequestMethod.POST)
	@Secured("ROLE_CLIENTE_INCLUIR")
	public ModelAndView salvar(@Valid Captacao captacao, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		
		if (result.hasErrors()) {
			return novo(captacao);
		}
		
		try {
			msg = captacaoService.incluir(captacao);			
		} catch (ElementoJaCadastradoException e) {
			result.reject(e.getMessage(), e.getMessage());
			return novo(captacao);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/captacao");
	}
	
	// Formulário
	private ModelAndView formulario(Captacao captacao){
		ModelAndView mv = new ModelAndView("sistema/CaptacaoForm");
        mv.addObject("captacao", captacao);
		return mv; 
	}
}
