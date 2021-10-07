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

import net.gupisoft.iuris.domain.entity.Escritorio;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.Poder;
import net.gupisoft.iuris.domain.entity.Procuracao;
import net.gupisoft.iuris.domain.service.EscritorioService;
import net.gupisoft.iuris.domain.service.FuncionarioService;
import net.gupisoft.iuris.domain.service.PessoaService;
import net.gupisoft.iuris.domain.service.PoderService;
import net.gupisoft.iuris.domain.service.ProcuracaoService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;

@Controller
@RequestMapping("/procuracao")
public class ProcuracaoController {
	
	@Autowired
	private ProcuracaoService procuracaoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EscritorioService escritorioService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PoderService poderService;

	// Listar registros
	@GetMapping
	@Secured("ROLE_CONTRATO_VER")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("sistema/ProcuracaoListar");
		
		mv.addObject("procuracoes", procuracaoService.listar());
		return mv;
	}
	
	// Novo
	@GetMapping("/novo/{id}")
	@Secured("ROLE_CONTRATO_INCLUIR")
	public ModelAndView novo(@PathVariable("id") Integer id) {
		
		Pessoa cliente = pessoaService.buscarPessoa(id);
		
		Procuracao procuracao = new Procuracao();
		ModelAndView mv = formulario(procuracao, cliente);
		return mv;
	}
	
	// Edit
	@GetMapping("/editar")
	@Secured("ROLE_CONTRATO_INCLUIR")
	public ModelAndView editar(Pessoa cliente, Procuracao procuracao) {
		ModelAndView mv = formulario(procuracao, cliente);
		return mv;
	}
	
	// Novo
	@GetMapping("/{id}")
	@Secured("ROLE_CONTRATO_VER")
	public ModelAndView editar(@PathVariable("id") Procuracao procuracao) {
		ModelAndView mv = formulario(procuracao, procuracao.getCliente());
		return mv;
	}
	
	
	// Incluir
	@RequestMapping(value = "", method = RequestMethod.POST)
	@Secured("ROLE_CONTRATO_INCLUIR")
	public ModelAndView salvar(@Valid Procuracao procuracao, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		
		if (result.hasErrors()) {
			return editar(procuracao.getCliente(), procuracao);
		}
		
		try {
			msg = procuracaoService.incluir(procuracao);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return editar(procuracao.getCliente(), procuracao);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/procuracao");
	}
	
	// Editar
	@RequestMapping(value = "{\\d+}", method = RequestMethod.POST)
	@Secured("ROLE_CONTRATO_INCLUIR")
	public ModelAndView editar(@Valid Procuracao procuracao, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		
		if (result.hasErrors()) {
			return editar(procuracao.getCliente(), procuracao);
		}
		
		try {
			msg = procuracaoService.editar(procuracao);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return editar(procuracao.getCliente(), procuracao);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		return new ModelAndView("redirect:/procuracao");
	}
	
	// Formulário
	private ModelAndView formulario(Procuracao procuracao, Pessoa cliente){
		ModelAndView mv = new ModelAndView("sistema/ProcuracaoForm");
		List<Poder> poderGeral = poderService.buscarGeral();
		List<Poder> poderesEspeciais= poderService.buscaEspeciais();
		
		List<Pessoa> advogados = funcionarioService.listarAdvogados();
		List<Escritorio> escritorios = escritorioService.listar();
		
		mv.addObject("escritorios", escritorios);
		mv.addObject("advogados", advogados);
		mv.addObject("poderGeral", poderGeral);
		mv.addObject("poderesEspeciais", poderesEspeciais);
        mv.addObject("procuracao", procuracao);
        mv.addObject("cliente", cliente);
		return mv; 
	}
}
