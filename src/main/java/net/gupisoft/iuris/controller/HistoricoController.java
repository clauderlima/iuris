package net.gupisoft.iuris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Captacao;
import net.gupisoft.iuris.domain.entity.HistoricoContato;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.service.HistoricoContatoService;
import net.gupisoft.iuris.domain.service.TituloService;
import net.gupisoft.iuris.domain.service.exception.ElementoJaCadastradoException;


@Controller
@RequestMapping("/historico")
public class HistoricoController {
	
	@Autowired
	private HistoricoContatoService historicoContatoService;
	
	@Autowired
	private TituloService tituloService;

	@GetMapping("/novo/{id}")
	@Secured("ROLE_HISTORICO_INCLUIR")
	public ModelAndView novo(@PathVariable("id") Pessoa cliente) {
		
		ModelAndView mv = formulario(cliente, new HistoricoContato());
		return mv;
	}
	
	@GetMapping("/captacao/novo/{id}")
	@Secured("ROLE_HISTORICO_INCLUIR")
	public ModelAndView novaCaptacao(@PathVariable("id") Captacao captacao) {
		
		ModelAndView mv = formularioCaptacao(captacao, new HistoricoContato());
		return mv;
	}
	
	@GetMapping("/{id}")
	@Secured("ROLE_HISTORICO_INCLUIR")
	public ModelAndView editar(@PathVariable HistoricoContato historicoContato) {
		ModelAndView mv = formulario(historicoContato.getCliente(), new HistoricoContato());
		return mv;
	}
	
	// Incluir
	@RequestMapping(value = {"", "{\\d+}"}, method = RequestMethod.POST)
	@Secured("ROLE_HISTORICO_INCLUIR")
	public ModelAndView salvar(@Valid HistoricoContato historicoContato, BindingResult result, RedirectAttributes attributes) {

		String msg = null;
		
		if (result.hasErrors()) {
			System.out.println("Brasil");
			return editar(historicoContato);
		}
		
		try {
			msg = historicoContatoService.incluir(historicoContato);			
		} catch (ElementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return editar(historicoContato);
		}
		
		attributes.addFlashAttribute("mensagem", msg);
		
		if (historicoContato.getCliente() == null) {
			return new ModelAndView("redirect:/captacao/ficha/" + historicoContato.getCaptacao().getId());
		}
		
		return new ModelAndView("redirect:/cliente/ficha/" + historicoContato.getCliente().getId());
	}
	
	
	// Incluir
	@PostMapping("/contato")
	public ResponseEntity<?> incluir(@RequestBody @Valid HistoricoContato historicoContato, BindingResult result) {

		System.out.println("lanca: " + historicoContato.toString());
		
		HistoricoContato historicoAtualizado = historicoContatoService.atualizaHistorico(historicoContato);

		System.out.println("Historico Controller: " + historicoAtualizado);
		
		return ResponseEntity.ok("OK");
	}

	// Form
	private ModelAndView formulario(Pessoa cliente, HistoricoContato historicoContato) {
		ModelAndView mv = new ModelAndView("sistema/HistoricoForm");
		
		mv.addObject("titulos", tituloService.listar());
        mv.addObject("historicoContato", historicoContato);
        mv.addObject("cliente", cliente);
		return mv;
	}
	
	private ModelAndView formularioCaptacao(Captacao captacao, HistoricoContato historicoContato) {
		ModelAndView mv = new ModelAndView("sistema/HistoricoCaptacaoForm");
		
		mv.addObject("titulos", tituloService.listar());
        mv.addObject("historicoContato", historicoContato);
        mv.addObject("captacao", captacao);
		return mv;
	}
}
