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

import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.enumeration.CargoFuncionario;
import net.gupisoft.iuris.domain.service.EscritorioService;
import net.gupisoft.iuris.domain.service.PessoaService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/gerencia/funcionario")
public class FuncionarioController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private EscritorioService escritorioService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_FUNCIONARIO_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/FuncionarioListar");
		
		mv.addObject("funcionarios", pessoaService.listarFuncionarios());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo")
	@Secured("ROLE_FUNCIONARIO_INCLUIR")
	public ModelAndView novo(Pessoa funcionario) {
		ModelAndView mv = formulario(funcionario);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_FUNCIONARIO_INCLUIR")
	public ModelAndView editar(@PathVariable("id") Pessoa funcionario) {
		System.out.println("funcionario controller: " + funcionario.getNome());
		ModelAndView mv = formulario(funcionario);
		return mv;
	}
	
	
	// Incluir
	@RequestMapping(value = {"", "{\\d+}"}, method = RequestMethod.POST)
	@Secured("ROLE_FUNCIONARIO_INCLUIR")
	public ModelAndView salvar(@Valid Pessoa funcionario, BindingResult result, RedirectAttributes attributes) {
		
		String msg = null;

		if (result.hasErrors()) {
			System.out.println("Tem erro no formulário: " + result.toString());
			result.rejectValue("nacionalidade", "404", "informe a nacionalidade");
			return formulario(funcionario);
		}
		
		try {
			msg = pessoaService.incluir(funcionario);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return novo(funcionario);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/gerencia/funcionario");
	}
	
	// Remover
	@GetMapping("/remove/{id}")
	@Secured("ROLE_FUNCIONARIO_INCLUIR")
	public ModelAndView remover(@PathVariable("id") Integer id) {
		
		Pessoa funcionario = pessoaService.buscarPessoa(id);
		
		String msg = pessoaService.removeFuncionario(funcionario);
		System.out.println(msg);
		
		return new ModelAndView("redirect:/gerencia/funcionario/tipo");
	}
	
	// Formulário
	private ModelAndView formulario(Pessoa funcionario){
		ModelAndView mv = new ModelAndView("sistema/FuncionarioForm");
		
		mv.addObject("escritorios", escritorioService.listar());
		mv.addObject("cargos", CargoFuncionario.values());
        mv.addObject("funcionario", funcionario);
		return mv; 
	}
}
