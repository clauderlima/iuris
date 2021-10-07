package net.gupisoft.iuris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.gupisoft.iuris.domain.entity.Parcela;
import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.enumeration.StatusPagamento;
import net.gupisoft.iuris.domain.service.ParcelaService;

@Controller
@RequestMapping("/parcela")
public class ParcelaController {
	
	@Autowired
	private ParcelaService parcelaService;

	@GetMapping("/{id}")
	public ModelAndView mostrar(@PathVariable("id") Pessoa cliente) {
		ModelAndView mv = new ModelAndView("sistema/PagamentoMostrar");
		
		
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Parcela parcela,  RedirectAttributes redirect) {
		ModelAndView mv = new ModelAndView("sistema/PagamentoForm");
		
		Pessoa cliente = parcela.getContrato().getCliente();
		
		mv.addObject("cliente", cliente);
		mv.addObject("parcela", parcela);
		mv.addObject("statusPagamentos", StatusPagamento.values());
		return mv;
	}
	
	@PostMapping("/editar")
	public ModelAndView gravar(@Valid Parcela parcela, BindingResult result, RedirectAttributes attributes) {
//		if (result.hasErrors()) {
//			return editar(parcela);
//		}
		System.out.println("Parcela: " + parcela.toString());

		parcelaService.gravar(parcela);			
		
		return new ModelAndView("redirect:/parcela/" + parcela.getContrato().getCliente().getId());
	}
	
	@GetMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Parcela parcela, RedirectAttributes redirect) {
		
		String msg = "";
		
		try {
			parcelaService.remover(parcela);
			msg = "Parcela removida com sucesso";
		} catch (Exception e) {
			msg = e.getMessage().toString();
		}
		redirect.addFlashAttribute(msg, msg);
	
		return new ModelAndView("redirect:/parcela/" + parcela.getContrato().getCliente().getId());
	}
	
	@GetMapping("/gerar/{id}")
	public ModelAndView gerar(@PathVariable("id") Pessoa cliente) {
		parcelaService.criaParcelas(cliente.getContrato());
		return new ModelAndView("redirect:/parcela/" + cliente.getId());
	}
}
