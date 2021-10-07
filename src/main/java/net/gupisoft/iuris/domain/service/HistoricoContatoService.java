package net.gupisoft.iuris.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.HistoricoContato;
import net.gupisoft.iuris.domain.repository.HistoricoContatoRepository;


@Service
public class HistoricoContatoService {

	@Autowired
	private HistoricoContatoRepository historicoContatoRepository;

	public String incluir(HistoricoContato historicoContato) {
		
		historicoContato.setDataHoraEvento(LocalDateTime.now());
		
		historicoContatoRepository.save(historicoContato);
		
		return "ok, incluído";
	}

	public HistoricoContato atualizaHistorico(HistoricoContato historicoContato) {
	
		HistoricoContato historicoBanco = historicoContatoRepository.getOne(historicoContato.getId());
		
		System.out.println("resp: " + historicoBanco.getResponsavelContato());
		
		if (historicoContato.getMensagemContato().trim() != "") {
			historicoBanco.setDataHoraContato(LocalDateTime.now());
			historicoBanco.setMensagemContato(historicoContato.getMensagemContato());
			historicoBanco.setTipoContato(historicoContato.getTipoContato());
			historicoBanco.setStatus(historicoContato.getStatus());
			historicoBanco.setResponsavelContato(historicoContato.getResponsavelContato());
			
			historicoContatoRepository.save(historicoBanco);			
		} 
		
		return historicoBanco;
	}
	
}
