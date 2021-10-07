package net.gupisoft.iuris.controller;

import java.util.List;

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

import net.gupisoft.iuris.domain.entity.Contrato;
import net.gupisoft.iuris.domain.entity.Escritorio;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPagamento;
import net.gupisoft.iuris.domain.service.ContratoService;
import net.gupisoft.iuris.domain.service.EscritorioService;
import net.gupisoft.iuris.domain.service.FuncionarioService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;

@Controller
@RequestMapping("/contrato")
public class ContratoController {
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EscritorioService escritorioService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_CONTRATO_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/ContratoListar");
		
		mv.addObject("contratos", contratoService.listar());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo/{id}")
	@Secured("ROLE_CONTRATO_INCLUIR")
	public ModelAndView novo(@PathVariable("id") Pessoa cliente) {
		Contrato contrato = new Contrato();
		ModelAndView mv = formulario(contrato, cliente);
		return mv;
	}
	
	// Edit
	@GetMapping("/editar")
	@Secured("ROLE_CONTRATO_INCLUIR")
	public ModelAndView editar(Pessoa cliente, Contrato contrato) {
		ModelAndView mv = formulario(contrato, cliente);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_CONTRATO_VER")
	public ModelAndView editar(@PathVariable("id") Contrato contrato) {
		ModelAndView mv = formulario(contrato, contrato.getCliente());
		return mv;
	}
	
	
	// Incluir
	@RequestMapping(value = "", method = RequestMethod.POST)
	@Secured("ROLE_CONTRATO_INCLUIR")
	public ModelAndView salvar(@Valid Contrato contrato, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		
		if (result.hasErrors()) {
			return editar(contrato.getCliente(), contrato);
		}
		
		try {
			msg = contratoService.incluir(contrato);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return editar(contrato.getCliente(), contrato);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/cliente");
	}
	
	// Editar
	@RequestMapping(value = "{\\d+}", method = RequestMethod.POST)
	@Secured("ROLE_CONTRATO_INCLUIR")
	public ModelAndView editar(@Valid Contrato contrato, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		
		if (result.hasErrors()) {
			return editar(contrato.getCliente(), contrato);
		}
		
		try {
			msg = contratoService.editar(contrato);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return editar(contrato.getCliente(), contrato);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/cliente");
	}
	
	// Formulário
	private ModelAndView formulario(Contrato contrato, Pessoa cliente){
		ModelAndView mv = new ModelAndView("sistema/ContratoForm");
		TipoPagamento[] pagamentos = TipoPagamento.values();
		
		List<Pessoa> consultores = funcionarioService.listarConsultores();
		List<Escritorio> escritorios = escritorioService.listar();
		
		mv.addObject("escritorios", escritorios);
		mv.addObject("consultores", consultores);
		mv.addObject("pagamentos", pagamentos);
        mv.addObject("contrato", contrato);
        mv.addObject("cliente", cliente);
		return mv; 
	}
}
