package net.gupisoft.iuris.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.gupisoft.iuris.domain.entity.Contrato;
import net.gupisoft.iuris.domain.entity.Procuracao;
import net.gupisoft.iuris.domain.service.ProcuracaoService;
import net.gupisoft.iuris.reports.ContratoPdfView;
import net.gupisoft.iuris.reports.ProcuracaoPdfView;

@Controller
@RequestMapping("/imprimir")
public class RelatorioController {
	
	@Autowired
	private ProcuracaoService procuracaoService;
	
	@RequestMapping("/contrato/{id}")
	public ModelAndView contrato(@PathVariable("id") Contrato contrato) {
		Map<String, Object> model = new HashMap<>();
		
		model.put("contrato", contrato);
		
		return new ModelAndView(new ContratoPdfView(), model);
	}
	
	@GetMapping("/procuracao/{id}")
	public ModelAndView procuracao(@PathVariable("id") Integer id) {
		
		Procuracao procuracao = procuracaoService.buscar(id);
		
		System.out.println("Proc: " + procuracao.toString());
		
		Map<String, Object> model = new HashMap<>();
		
		model.put("procuracao", procuracao);
		
		return new ModelAndView(new ProcuracaoPdfView(), model);
	}
}
