package net.gupisoft.iuris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.gupisoft.iuris.domain.service.ContratoService;
import net.gupisoft.iuris.domain.service.EscritorioService;
import net.gupisoft.iuris.domain.service.PessoaService;

@Controller
@ControllerAdvice
@RequestMapping("/")
public class InicioController {

	@Autowired
	private PessoaService clienteService;
	
	@Autowired
	private EscritorioService escritorioService;
	
	@Autowired
	private ContratoService contratoService;
	
	@GetMapping
	public ModelAndView Inicio() {
		ModelAndView mv = new ModelAndView("Inicio");
		
		mv.addObject("qtdClientesSemContrato", clienteService.qtdClientesSemContrato());
		mv.addObject("qtdClientesComContrato", clienteService.qtdClientesComContrato());
		mv.addObject("qtdEscritorios", escritorioService.qtdEscritorios());
		mv.addObject("qtdContratos", contratoService.qtdContratos());
		mv.addObject("escritorios", escritorioService.listar());
		
		return mv;	
	}
}
