package net.gupisoft.iuris.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gupisoft.iuris.domain.entity.Contrato;
import net.gupisoft.iuris.domain.entity.Parcela;
import net.gupisoft.iuris.domain.entity.enumeration.StatusPagamento;
import net.gupisoft.iuris.domain.repository.ParcelaRepository;


@Service
public class ParcelaService {

	@Autowired
	private ParcelaRepository parcelaRepository;

	public void criaParcelas(Contrato contrato) {
		
		for (int i = 0; i < contrato.getQtdParcelasPagamento(); i++) {				
			Parcela parcela = new Parcela();
			
			parcela.setParcela(i+1);
			parcela.setDataVencimento(contrato.getDataAssinatura().plusMonths(i));
			parcela.setValor(contrato.getValorParcela());
			parcela.setStatus(StatusPagamento.ABERTA);
			parcela.setContrato(contrato);
			
			parcelaRepository.save(parcela);
			
		}
	}

	public void remover(Parcela parcela) {
		parcelaRepository.delete(parcela);
		
	}

	public void gravar(Parcela parcela) {
		System.out.println("Salvando parcela: " + parcela.toString());
		parcelaRepository.saveAndFlush(parcela);
		
	}
}
