package net.gupisoft.iuris.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Escritorio;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.service.EscritorioService;
import net.gupisoft.iuris.domain.service.PessoaService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/gerencia/escritorio")
public class EscritorioController {
	
	@Autowired
	private EscritorioService escritorioService;
	
	@Autowired
	private PessoaService pessoaService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_ESCRITORIO_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/EscritorioListar");
		
		mv.addObject("escritorios", escritorioService.listar());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo")
	@Secured("ROLE_ESCRITORIO_INCLUIR")
	public ModelAndView novo(Escritorio escritorio) {
		ModelAndView mv = formulario(escritorio);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_ESCRITORIO_INCLUIR")
	public ModelAndView editar(@PathVariable Integer id) {
		ModelAndView mv = formulario(escritorioService.buscar(id));
		return mv;
	}
	
	
	// Incluir
	@PostMapping
	@Secured("ROLE_ESCRITORIO_INCLUIR")
	public ModelAndView incluir(@Valid Escritorio escritorio, BindingResult result, RedirectAttributes attributes) {

		System.out.println("Escritório: " + escritorio.toString());
		
		if (result.hasErrors()) {
			return novo(escritorio);
		}
		
		try {
			escritorioService.incluir(escritorio);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cnpj", e.getMessage(), e.getMessage());
			return novo(escritorio);
		}
		
		attributes.addFlashAttribute("mensagem"," Escritorio incluído com sucesso!");
		return new ModelAndView("redirect:/gerencia/escritorio");
	}
	
	// Formulário
	private ModelAndView formulario(Escritorio escritorio){
		ModelAndView mv = new ModelAndView("sistema/EscritorioForm");
		List<Pessoa> gerentes = pessoaService.listarFuncionarios();
		mv.addObject("gerentes", gerentes);
        mv.addObject("escritorio", escritorio);
		return mv; 
	}
}
