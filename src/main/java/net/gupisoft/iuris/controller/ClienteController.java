package net.gupisoft.iuris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.service.PessoaService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private PessoaService pessoaService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_CLIENTE_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/ClienteListar");
		
		mv.addObject("clientes", pessoaService.listarClientes());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo")
	@Secured("ROLE_CLIENTE_INCLUIR")
	public ModelAndView novo(Pessoa cliente) {
		ModelAndView mv = formulario(cliente);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_CLIENTE_VER")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		Pessoa cliente = pessoaService.buscarPessoa(id);
		
		ModelAndView mv = formulario(cliente);
		return mv;
	}
	
	// Ficha
	@GetMapping("/ficha/{id}")
	@Secured("ROLE_CLIENTE_VER")
	public ModelAndView mostrar(@PathVariable("id") Integer id) {
		
		Pessoa cliente = pessoaService.buscarPessoa(id);
		
		ModelAndView mv = new ModelAndView("sistema/ClienteFicha");
		mv.addObject("cliente", pessoaService.buscarPessoa(cliente.getId()));
		mv.addObject("historicos", pessoaService.buscarHistoricos(cliente));
		System.out.println("hists: " + pessoaService.buscarHistoricos(cliente));
		return mv;
	}
	
	// Incluir
	@RequestMapping(value = {"", "{\\d+}"}, method = RequestMethod.POST)
	@Secured("ROLE_CLIENTE_INCLUIR")
	public ModelAndView salvar(@Valid Pessoa cliente, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		if (result.hasErrors()) {
			System.out.println("Brasil");
			return novo(cliente);
		}
		
		try {
			msg = pessoaService.incluir(cliente);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/cliente");
	}
	
	@PostMapping("/salvar")
	@Secured("ROLE_CLIENTE_INCLUIR")
	public ModelAndView gravar(@Valid Pessoa cliente, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		if (result.hasErrors()) {
			System.out.println("Tem erro no formulário: " + result.toString());
			return novo(cliente);
		}
		
		try {
			msg = pessoaService.incluir(cliente);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/cliente");
	}
	
	
	// Formulário
	private ModelAndView formulario(Pessoa cliente){
		ModelAndView mv = new ModelAndView("sistema/ClienteForm");
        mv.addObject("cliente", cliente);
		return mv; 
	}
}
