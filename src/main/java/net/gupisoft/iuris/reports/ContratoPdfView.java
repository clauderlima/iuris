package net.gupisoft.iuris.reports;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import net.gupisoft.iuris.domain.entity.Contrato;
import net.gupisoft.iuris.domain.entity.Parcela;
import net.gupisoft.iuris.domain.entity.Pessoa;

public class ContratoPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		writer.setPageEvent(new PageEventListener());
		
		String pattern = "dd/MM/yyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		NumberFormat valor = NumberFormat.getCurrencyInstance();
		
		Contrato contrato = (Contrato) model.get("contrato");
		Pessoa pessoa = contrato.getCliente();
		
		Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		Font textoFont = FontFactory.getFont(FontFactory.HELVETICA);
		Font textFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		
		
		Chunk tituloSublinhado = new Chunk("CONTRATO DE PRESTAÇÃO DE SERVIÇO", tituloFont);
		tituloSublinhado.setUnderline(01f, -1f);
		
		Paragraph titulo = new Paragraph();
		titulo.add(tituloSublinhado);
		titulo.setAlignment(Element.ALIGN_CENTER);
		titulo.setSpacingAfter(20);
		titulo.setSpacingBefore(100);
		
		Paragraph titulo_a = new Paragraph();
		titulo_a.add(new Phrase("PARTES / DEFINIÇÃO SERVIÇO", tituloFont));
		titulo_a.setAlignment(Element.ALIGN_LEFT);
		titulo_a.setSpacingAfter(10);
		
		Paragraph titulo_a1 = new Paragraph();
		titulo_a1.add(new Phrase("CONTRATADA", tituloFont));
		titulo_a1.setSpacingAfter(5);
		
		Paragraph texto_a1 = new Paragraph();
		Chunk empresa = new Chunk(contrato.getEscritorio().getNome().toString(), textFontBold);
		Chunk telefone = new Chunk(contrato.getEscritorio().getTelefoneFixo().getNumero(), textFontBold);
		Phrase conteudoTexto11a = new Phrase(", pessoa jurídica de direito privado, inscrita sob o CNPJ: "
				+ contrato.getEscritorio().getCnpj().toString() + ", sediada nesta capital federal, "
				+ "endereço  " + contrato.getEscritorio().getEndereco().getLogradouro() + " "
				+ contrato.getEscritorio().getEndereco().getNumero() + ", " 
				+ contrato.getEscritorio().getEndereco().getCidade() + "-"
				+ contrato.getEscritorio().getEndereco().getEstado() + " | "
				+ contrato.getEscritorio().getEndereco().getCep() + ", telefone: ", textoFont);
		Phrase conteudoTexto11b = new Phrase(", cliente pelo consultor(a): " + contrato.getConsultor().getNome().toString() 
				+ " - Brasília, DF.", textoFont);
		texto_a1.add(empresa);
		texto_a1.add(conteudoTexto11a);
		texto_a1.add(telefone);
		texto_a1.add(conteudoTexto11b);
		texto_a1.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph titulo_a2 = new Paragraph();
		titulo_a2.add(new Phrase("CONTRATANTE E/OU RESPONSÁVEL", tituloFont));
		titulo_a2.setSpacingAfter(10);
		titulo_a2.setSpacingBefore(10);
		
		Paragraph texto_a2 = new Paragraph();
		texto_a2.add(new Phrase("Nome: " + pessoa.getNome().toString() + "\r\n" + 
				"Data de nascimento: " + pessoa.getDataNascimento().format(formatter) + "\r\n" + 
				"Sexo: " + pessoa.getSexo().toString() + "\r\n" + 
				"Estado civil: " + pessoa.getEstadoCivil().toString() + "\r\n" + 
				"CPF: " + pessoa.getCpf().toString() + "\r\n" + 
				"RG: " + pessoa.getRg().toString() + " " + pessoa.getOrgaoExpedidor().toString() + "\r\n" + 
				"Telefone: " + pessoa.getTelefoneCelular().getNumero().toString() + "\r\n", textoFont));
		
		Paragraph titulo_a3 = new Paragraph();
		titulo_a3.add(new Phrase("TITULAR DO FINANCIAMENTO", tituloFont));
		titulo_a3.setSpacingAfter(10);
		titulo_a3.setSpacingBefore(10);
		
		Paragraph texto_a3 = new Paragraph();
		texto_a3.add(new Phrase("Nome: " + pessoa.getContrato().getTitularFinanciamento().getNome().toString() + "\r\n" + 
				"Data de nascimento: " + pessoa.getContrato().getTitularFinanciamento().getDataNascimento().format(formatter) + "\r\n" + 
				"Sexo: " + pessoa.getContrato().getTitularFinanciamento().getSexo().toString() + "\r\n" + 
				"CPF: " + pessoa.getContrato().getTitularFinanciamento().getCpf().toString() + "\r\n" + 
				"RG: " + pessoa.getContrato().getTitularFinanciamento().getRg().toString() + " " + pessoa.getOrgaoExpedidor().toString() + "\r\n" + 
				"Endereço: " + pessoa.getContrato().getTitularFinanciamento().getEndereco().getLogradouro().toString() + 
				", " + pessoa.getContrato().getTitularFinanciamento().getEndereco().getNumero() +
				", " + pessoa.getContrato().getTitularFinanciamento().getEndereco().getBairro() + 
				", " + pessoa.getContrato().getTitularFinanciamento().getEndereco().getCidade() + 
				"-" + pessoa.getContrato().getTitularFinanciamento().getEndereco().getEstado() + " \r\n", textoFont));
		
		Paragraph titulo_a4 = new Paragraph();
		titulo_a4.add(new Phrase("PLANO DE PAGAMENTO", tituloFont));
		titulo_a4.setSpacingAfter(10);
		titulo_a4.setSpacingBefore(10);
		
		Paragraph texto_a4 = new Paragraph();
		String textoFormaPagamento = "O valor de " + valor.format(pessoa.getContrato().getValorContrato()) + " no " 
				+ pessoa.getContrato().getTipoPagamento().getDescricao().toString().toLowerCase() + " em " + pessoa.getContrato().getQtdParcelasPagamento() 
				+ " parcelas de " + pessoa.getContrato().getValorParcela() + " - com vencimento para " 
				+ pessoa.getContrato().getDataAssinatura().format(formatter) + " e as demais para os meses subsquentes, podendo adiantar, sem direito à desconto.\r\n\n";
		
		for (Parcela parcela : pessoa.getContrato().getParcelas()) {
			textoFormaPagamento = textoFormaPagamento + parcela.getParcela() + "ª parcela no " + pessoa.getContrato().getTipoPagamento().getDescricao().toString().toLowerCase() + 
					", no valor de " + valor.format(parcela.getValor()) + ", com vencimento em " + parcela.getDataVencimento().format(formatter) + ". \r\n";
		}
		texto_a4.add(new Phrase(textoFormaPagamento, textoFont));
		texto_a4.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph titulo_a5 = new Paragraph();
		titulo_a5.add(new Phrase("DADOS DA DÍVIDA", tituloFont));
		titulo_a5.setSpacingAfter(10);
		titulo_a5.setSpacingBefore(10);
		
		Paragraph texto_a5 = new Paragraph();
		String dadosDivida = "Dívida contraída com o financiamento do ";
		
		
		
		
		texto_a5.add(new Phrase(dadosDivida, textoFont));
		
		Paragraph titulo_a6 = new Paragraph();
		titulo_a6.add(new Phrase("DADOS DO FINANCIAMENTO", tituloFont));
		titulo_a6.setSpacingAfter(10);
		titulo_a6.setSpacingBefore(10);
		
		Paragraph texto_a6 = new Paragraph();
		String dadosFinacimento = "Referente à dívida contraída com o "
				
						+ "\r\n";
		
		texto_a6.add(new Phrase(dadosFinacimento, textoFont));
		
		Paragraph titulo_b = new Paragraph();
		titulo_b.add(new Phrase("OBJETO", tituloFont));
		titulo_b.setSpacingAfter(10);
		titulo_b.setSpacingBefore(20);
		
		Paragraph texto_b1 = new Paragraph();
		texto_b1.add(new Phrase("O Objeto deste contrato é a prestação do serviço assessorial de consultoria financeira, recuperação de crédito e "
				+ "conciliação bancária, representando a parte CONTRATANTE, com objetivo de obter desconto para a quitação do saldo devedor objeto "
				+ "descrito neste contrato de prestação de serviço, junto ao banco financiado."));
		texto_b1.setSpacingAfter(10);
		texto_b1.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph texto_b2 = new Paragraph();
		texto_b2.add(new Phrase("Os procedimentos a seguir estarão vinculados ao tipo de serviço solicitado, este contrato contempla o serviço "
				+ "assessorial de conciliação bancária nos contratos de Financiamento de veículo (LEASING e/ou CDC), Empréstimos bancários, limites "
				+ "de conta corrente ou dívida de cartão de crédito, todas as ações descritas no corpo deste contrato serão vinculadas a seu "
				+ "respectivo produto conforme lista acima citada."));
		texto_b2.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph titulo1 = new Paragraph();
		titulo1.add(new Phrase("CLÁUSULA 1 - OBRIGAÇÕES DA CONTRATADA", tituloFont));
		titulo1.setSpacingAfter(10);
		titulo1.setSpacingBefore(20);
		
		Paragraph texto11 = new Paragraph();
		texto11.add(new Phrase("1.1. Realizar o atendimento ao cliente através dos canais de atendimento disponíveis, dentro do horário comercial."));
		texto11.setAlignment(Element.ALIGN_JUSTIFIED);
		texto11.setSpacingAfter(10);
		
		Paragraph texto12 = new Paragraph();
		texto12.add(new Phrase("1.2. Realizar o monitoramento do andamento processual da ação movida pelo banco em desfavor do cliente até o fim "
				+ "deste contrato, RESSALVADA A HIPÓTESE DE ERRO MATERIAL POR PARTE DO SISTEMA JUDICIÁRIO E/OU DO BANCO QUE INVIABILIZE A "
				+ "LOCALIZAÇÃO DE TAL AÇÃO JUDICIAL (INCLUSIVE SEGREDO DE JUSTIÇA), nesse caso a CONTRATADA não terá responsabilidade por se tratar "
				+ "de procedimento contraditório conforme diz o Art. 189 do CPC - Código de Processo Civil."));
		texto12.setAlignment(Element.ALIGN_JUSTIFIED);
		texto12.setSpacingAfter(10);
		
		Paragraph texto13 = new Paragraph();
		texto13.add(new Phrase("1.3. Ter contato com a cobrança por parte do banco financiado, conforme forem repassadas pelo CONTRATANTE até o "
				+ "final deste contrato."));
		texto13.setAlignment(Element.ALIGN_JUSTIFIED);
		texto13.setSpacingAfter(10);
		
		Paragraph texto14 = new Paragraph();
		texto14.add(new Phrase("1.4. Avisar ao cliente sobre o mandado de busca e apreensão, restrições ou decisão judicial relevante, referente "
				+ "ao contrato, conforme produto contratado, exceto se o monitoramento for impedido por algum acontecimento previsto no ítem 1.2 "
				+ "deste contrato."));
		texto14.setAlignment(Element.ALIGN_JUSTIFIED);
		texto14.setSpacingAfter(10);
		
		Paragraph texto15 = new Paragraph();
		texto15.add(new Phrase("1.5. Realizar a negociação especializada junto ao banco, para realizar a homologação da liquidação da dívida."));
		texto15.setAlignment(Element.ALIGN_JUSTIFIED);
		texto15.setSpacingAfter(10);
		
		Paragraph texto16 = new Paragraph();
		texto16.add(new Phrase("1.6. Repassar informações sobre o andamento do acordo com o banco ao CONTRATANTE em intervalos não superior à "
				+ "10 (dez) dias. TRABALHAR COM TODO O RIGOR PROFISSIONAL PARA OBTENÇÃO DO MAIS RAZOÁVEL DESCONTO EM FAVOR DO(A) CONTRATANTE, "
				+ "obrigando-se a CONTRATADA a repassar TODO O DESCONTO ao(à) CONTRATANTE"));
		texto16.setAlignment(Element.ALIGN_JUSTIFIED);
		texto16.setSpacingAfter(10);
		
		Paragraph texto17 = new Paragraph();
		texto17.add(new Phrase("1.7. A CONTRATADA é responsável pela negociação do contrato de financiamento, empréstimo, cartão de crédito ou "
				+ "limite e não pelo bem alienado (se houver), uma vez que a posse deste recai sobre o(a) CONTRATANTE."));
		texto17.setAlignment(Element.ALIGN_JUSTIFIED);
		texto17.setSpacingAfter(10);
		
		Paragraph texto18 = new Paragraph();
		texto18.add(new Phrase("1.8. A CONTRATADA É RESPONSÁVEL APENAS PELA NEGOCIAÇÃO E PELA OBTENÇÃO DO DESCONTO, não se responsabilizando, "
				+ "pelo pagamento da dívida perante o banco, uma vez que não tutela valores do(a) CONTRATANTE."));
		texto18.setAlignment(Element.ALIGN_JUSTIFIED);
		texto18.setSpacingAfter(10);
		
		Paragraph texto19 = new Paragraph();
		texto19.add(new Phrase("1.9. Em caso de não haver mais chances nas negociações e não cumprindo o desconto mínimo de  do saldo devedor do(a) CONTRATANTE, a CONTRATADA tem a obrigação "
						+ "de devolver o dinheiro pago pela prestação de serviço."));
		texto19.setAlignment(Element.ALIGN_JUSTIFIED);
		texto19.setSpacingAfter(10);
		
		Paragraph texto110 = new Paragraph();
		texto110.add(new Phrase("1.10. Realizar em favor do(a) CONTRATANTE, auxílio na demanda judicial de busca e apreensão ou reintegração de posse, "
				+ "no tempo e prazos corretos, durante todo o período de duração do contrato, buscando atingir o resultado necessário em favor do(a) "
				+ "CONTRATANTE, inclusive, se necessário for, progredir para 2ª instância até que se encerre a prestação de serviço deste contrato."));
		texto110.setAlignment(Element.ALIGN_JUSTIFIED);
		texto110.setSpacingAfter(10);
		
		Paragraph texto111 = new Paragraph();
		texto111.add(new Phrase("1.11. As eventuais despesas processuais e honorários advocatícios (se houverem), decorrentes da eventual demanda "
				+ "ou de qualquer outra demanda ou recurso judicial necessários à defesa do(a) CONTRATANTE no decorrer do período de negociação ficam"
				+ "por conta do CONTRATANTE."));
		texto111.setAlignment(Element.ALIGN_JUSTIFIED);
		texto111.setSpacingAfter(10);
		
		Paragraph titulo2 = new Paragraph();
		titulo2.add(new Phrase("CLÁUSULA 2 - OBRIGAÇÕES DO(A) CONTRATANTE", tituloFont));
		titulo2.setSpacingAfter(10);
		titulo2.setSpacingBefore(20);
		
		Paragraph texto21 = new Paragraph();
		texto21.add(new Phrase("2.1. Fornecer todos os meios de contato, caso não seja possível contactar o cliente será de inteira responsabilidade "
				+ "do mesmo, sob pena de cancelamento compulsório do serviço."));
		texto21.setAlignment(Element.ALIGN_JUSTIFIED);
		texto21.setSpacingAfter(10);
		
		Paragraph texto22 = new Paragraph();
		texto22.add(new Phrase("2.2. Seguir as orientações repassadas pela CONTRATADA, com o fim de obter êxito nas negociações."));
		texto22.setAlignment(Element.ALIGN_JUSTIFIED);
		texto22.setSpacingAfter(10);
		
		Paragraph texto23 = new Paragraph();
		texto23.add(new Phrase("2.3. Ressalta-se que o cliente pode ter contato com o banco, porém toda a negociação deverá ocorrer por parte da "
				+ "CONTRATADA, a interferência do(a) CONTRATANTE que resulte em falhas de qualquer natureza serão de inteira responsabilidade do(a) "
				+ "CONTRATANTE."));
		texto23.setAlignment(Element.ALIGN_JUSTIFIED);
		texto23.setSpacingAfter(10);
		
		Paragraph texto24 = new Paragraph();
		texto24.add(new Phrase("2.4. Garantir a posse do veículo durante todo o prazo deste contrato, seguindo o que lhe for orientado pela CONTRATADA."));
		texto24.setAlignment(Element.ALIGN_JUSTIFIED);
		texto24.setSpacingAfter(10);
		
		Paragraph texto25 = new Paragraph();
		texto25.add(new Phrase("2.5. Qualquer consequência advinda do não cumprimento de recomendação do corpo jurídico da CONTRATADA é de inteira "
				+ "responsabilidade do(a) CONTRATANTE, incluindo recomendação sobre busca e apreensão."));
		texto25.setAlignment(Element.ALIGN_JUSTIFIED);
		texto25.setSpacingAfter(10);
		
		Paragraph texto26 = new Paragraph();
		texto26.add(new Phrase("2.6. Fornecer todos os meios de contato com o banco e/ou qualquer assessoria, advocacia ou empresa que represente o "
				+ "banco, caso seja requerido pela CONTRATADA, inclusive se necessário for comparecer pessoalmente ao banco para adquirir informações "
				+ "necessárias ao andamento do acordo."));
		texto26.setAlignment(Element.ALIGN_JUSTIFIED);
		texto26.setSpacingAfter(10);
		
		Paragraph titulo3 = new Paragraph();
		titulo3.add(new Phrase("CLÁUSULA 3 - PROCEDIMENTOS DO CONTRATO", tituloFont));
		titulo3.setSpacingAfter(10);
		titulo3.setSpacingBefore(20);
		
		Paragraph texto31 = new Paragraph();
		texto31.add(new Phrase("3.1. A CONTRATADA É RESPONSÁVEL APENAS PELA NEGOCIAÇÃO E PELA OBTENÇÃO DO DESCONTO, não se responsabilizando, "
				+ "naturalmente, pelo pagamento da dívida perante o banco financiado"));
		texto31.setAlignment(Element.ALIGN_JUSTIFIED);
		texto31.setSpacingAfter(10);
		
		Paragraph texto32 = new Paragraph();
		texto32.add(new Phrase("3.2. A liquidação da dívida junto ao banco financiado, é de responsabilidade EXCLUSIVA do(a) CONTRATANTE, que deverá "
				+ "acumular valores durante o período de vigência deste contrato, a fim de quitar integralmente o saldo devedor perante o banco, ao "
				+ "tempo em que a CONTRATADA trabalhará para obter o melhor desconto possível, ressalvados os termos do item 7.1"));
		texto32.setAlignment(Element.ALIGN_JUSTIFIED);
		texto32.setSpacingAfter(10);
		
		Paragraph texto33 = new Paragraph();
		texto33.add(new Phrase("3.3. Dentro desse procedimento de negociação, o(a) CONTRATANTE fica ciente e está de acordo com os seguintes pontos:"));
		texto33.setAlignment(Element.ALIGN_JUSTIFIED);
		texto33.setSpacingAfter(10);
		
		Paragraph texto34 = new Paragraph();
		texto34.add(new Phrase("3.4. O banco incluirá o nome do titular do financiamento e/ou fiador/avalista (se houver) no SPC/SERASA e/ou protestar "
				+ "em cartório, até a liquidação integral da dívida pendente junto ao banco financiado."));
		texto34.setAlignment(Element.ALIGN_JUSTIFIED);
		texto34.setSpacingAfter(10);
		
		Paragraph texto35 = new Paragraph();
		texto35.add(new Phrase("3.5. O banco poderá a qualquer momento ingressar com ação de busca e apreensão ou reintegração de posse, execução da "
				+ "dívida em desfavor do(a) titular do financiamento, onde haverá o mandado de busca e apreensão do bem, e, eventualmente, solicitar o "
				+ "bloqueio judicial do documento juto ao DETRAN, na base RENAVAM, por via do sistema RENAJUD, caso seja deferido pelo juiz (se o "
				+ "contrato for de financiamento de veículo)."));
		texto35.setAlignment(Element.ALIGN_JUSTIFIED);
		texto35.setSpacingAfter(10);
		
		Paragraph texto36 = new Paragraph();
		texto36.add(new Phrase("3.6. O (a) CONTRATANTE fica ciente de que os infortúnios causados são decorrentes do período de inadimplência, "
				+ "não podendo ser a CONTRATADA, civilmente responsabilizada, eis que não é a causadora dos infortúnios. Haverá cobrança por parte "
				+ "do banco utilizando as mais diversas ferramentas, como cartas de cobrança, SMS no celular, ligações telefônicas, e-mails, e em "
				+ "alguns casos envio de pessoa à porta do cliente."));
		texto36.setAlignment(Element.ALIGN_JUSTIFIED);
		texto36.setSpacingAfter(10);
		
		Paragraph texto37 = new Paragraph();
		texto37.add(new Phrase("3.7. Ressalta-se que o boleto de quitação do saldo devedor perante à instituição financeira SOMENTE SERÁ LIBERADO "
				+ "APÓS A LIQUIDAÇÃO DO PAGAMENTO INTEGRAL DA PRESTAÇÃO DE SERVIÇO. Qualquer liberalidade da contratada não será considerada novação."));
		texto37.setAlignment(Element.ALIGN_JUSTIFIED);
		texto37.setSpacingAfter(10);
		
		Paragraph texto38 = new Paragraph();
		texto38.add(new Phrase("3.8. EM TODO O PERÍODO DO CONTRATO, A NEGOCIAÇÃO SERÁ FEITA EXCLUSIVAMENTE PELA CONTRATADA, A FIM DE GARANTIR A "
				+ "EFETIVAÇÃO NA NEGOCIAÇÃO."));
		texto38.setAlignment(Element.ALIGN_JUSTIFIED);
		texto38.setSpacingAfter(10);

		Paragraph texto39 = new Paragraph();
		texto39.add(new Phrase("3.9. A CONTRATADA DISPÕE DE LINHA DE NEGOCIAÇÃO EXCLUSIVA, A COBRANÇA DO BANCO IRÁ LIGAR E PASSAR PROPOSTAS PARA "
				+ "LIQUIDAÇÃO DA DÍVDA, CAMPANHAS JÁ ENVIADAS ANTECIPADAMENTE PELA CONTRATADA, SENDO ASSIM O(A) CONTRATANTE DEVERÁ INFORMAR À "
				+ "CONTRATADA TAL AÇÃO, PARA GARANTIR A  INTEGRIDADE DO ACORDO, NÃO TENDO A CONTRATADA RESPONSABILIDADE POR ACORDOS FRAUDADOS."));
		texto39.setAlignment(Element.ALIGN_JUSTIFIED);
		texto39.setSpacingAfter(10);
		
		Paragraph texto310 = new Paragraph();
		texto310.add(new Phrase("3.10. Após alcançado o valor prometido de no mínimo 50% de desconto, no prazo de até 12(doze) meses, e o cliente não "
				+ "tiver o dinheiro para liquidar a dívida, a CONTRATADA estará totalmente isenta de qualquer responsabilidade sobre o contrato em "
				+ "epígrafe, e se reserva no direito de cancelar o contrato de prestação de serviço afim de preservar a qualidade dos seus serviços, "
				+ "uma vez que o contrato prevê a conciliação e a entrega da dívida negociada ao cliente sendo ele mesmo o único responsável pelo "
				+ "pagamento da dívida."));
		texto310.setAlignment(Element.ALIGN_JUSTIFIED);
		texto310.setSpacingAfter(10);
		
		Paragraph titulo4 = new Paragraph();
		titulo4.add(new Phrase("CLÁUSULA 4 - VIGÊNCIA DO CONTRATO", tituloFont));
		titulo4.setSpacingAfter(10);
		titulo4.setSpacingBefore(20);
		
		Paragraph texto41 = new Paragraph();
		texto41.add(new Phrase("4.1. O contrato tem a vigência a partir de sua assinatura, com duração de até 12 (doze) meses, podendo exclusivamente a "
				+ "critério da CONTRATADA ser prorrogado por até 12 (doze) meses, em casos onde não houver aprovação do valor mínimo de 50% de "
				+ "desconto dentro do prazo, esta empresa se compromete a devolver o valor pago pelo serviço prestado, caso este não seja finalizado "
				+ "com êxito.", textoFont));
		texto41.setAlignment(Element.ALIGN_JUSTIFIED);
		texto41.setSpacingAfter(10);
		
		Paragraph texto42 = new Paragraph();
		texto42.add(new Phrase("4.2. O CANCELAMENTO do presente contrato poderá ser requerido pelo(a) CONTRATANTE a qualquer momento, desde que preste "
				+ "o pagamento de multa correspondente a 10% (dez por cento) do valor total do serviço, sem prejuízo do pagamento das parcelas"
				+ " referentes à prestação do serviço que por ventura estejam em atraso.", textoFont));
		texto42.setAlignment(Element.ALIGN_JUSTIFIED);
		texto42.setSpacingAfter(10);
		
		Paragraph texto43 = new Paragraph();
		texto43.add(new Phrase("4.3. Em caso de cancelamento após o pagamento do valor integral do serviço, fica isento o(a) CONTRATANTE da multa "
				+ "prevista no item 6.2, estando ciente que NÃO HAVERÁ DEVOLUÇÃO DE VALORES, SOB NENHUMA HIPÓTESE.", textoFont));
		texto43.setAlignment(Element.ALIGN_JUSTIFIED);
		texto43.setSpacingAfter(10);
		
		Paragraph texto44 = new Paragraph();
		texto44.add(new Phrase("4.4. Somente considera-se cancelado o contrato após o pagamento da multa contratual, se houver, caso não seja feito "
				+ "tal procedimento o contrato será encaminhado para o setor específico para cobrança.", textoFont));
		texto44.setAlignment(Element.ALIGN_JUSTIFIED);
		texto44.setSpacingAfter(10);
		
		Paragraph texto45 = new Paragraph();
		texto45.add(new Phrase("4.5. É de responsabilidade do CONTRATANTE o adimplemento do boleto de quitação da dívida, não se responsabilizando "
				+ "a CONTRATADA pelas consequências jurídicas do não pagamento do boleto de quitação.", textoFont));
		texto45.setAlignment(Element.ALIGN_JUSTIFIED);
		texto45.setSpacingAfter(10);
		
		Paragraph titulo5 = new Paragraph();
		titulo5.add(new Phrase("CLÁUSULA 5 - PAGAMENTO DO SERVIÇO", tituloFont));
		titulo5.setSpacingAfter(10);
		titulo5.setSpacingBefore(20);
		
		Paragraph texto51 = new Paragraph();
		texto51.add(new Phrase("5.1. O (a) CONTRATANTE pagará, a título de remuneração pelo serviço assessorial prestado, conforme PLANO DE "
				+ "PAGAMENTO, da seguinte forma: ", textoFont));
		texto51.setAlignment(Element.ALIGN_JUSTIFIED);
		texto51.setSpacingAfter(10);
		
		Paragraph texto52 = new Paragraph();
		String formaPagamento = "5.2. O valor de " + valor.format(pessoa.getContrato().getValorContrato()) + " no " 
				+ pessoa.getContrato().getTipoPagamento().getDescricao().toLowerCase() + " em " + pessoa.getContrato().getQtdParcelasPagamento() 
				+ " parcelas de " + pessoa.getContrato().getValorParcela() + " - com vencimento para " 
				+ pessoa.getContrato().getDataAssinatura().format(formatter) + " e as demais para os meses subsquentes, podendo adiantar, sem "
				+ "direito à desconto.\r\n";
		
		for (Parcela parcela : pessoa.getContrato().getParcelas()) {
			formaPagamento = formaPagamento + parcela.getParcela() + "ª parcela no " + pessoa.getContrato().getTipoPagamento().getDescricao().toLowerCase() + 
					", no valor de " + valor.format(parcela.getValor()) + ", com vencimento em " + parcela.getDataVencimento().format(formatter) + ". \r\n";
		}
		texto52.add(new Phrase(formaPagamento, textoFont));
		texto52.setAlignment(Element.ALIGN_JUSTIFIED);
		texto52.setSpacingAfter(10);
		
		Paragraph texto53 = new Paragraph();
		texto53.add(new Phrase("5.3. O (a) CONTRATANTE está ciente da emissão de boletos bancários referentes à prestação do serviço, e, ainda, "
				+ "declara ser o responsável pelo pagamento integral do contrato, confessando a dívida de acordo com a forma de pagamento escolhida, "
				+ "sendo a CONTRATADA credora da quantia líquida, certa e exigível.", textoFont));
		texto53.setAlignment(Element.ALIGN_JUSTIFIED);
		texto53.setSpacingAfter(10);
		
		Paragraph titulo6 = new Paragraph();
		titulo6.add(new Phrase("CLÁUSULA 6 - INADIMPLEMENTO", tituloFont));
		titulo6.setSpacingAfter(10);
		titulo6.setSpacingBefore(20);
		
		Paragraph texto61 = new Paragraph();
		texto61.add(new Phrase("6.1. O (a) CONTRATANTE está ciente que em caso de atraso no pagamento de qualquer das parcelas, acarretará na "
				+ "suspensão total do serviço prestado após 7 (sete) dias do vencimento. Será suspenso o monitoramento e avisos. O desbloqueio dos "
				+ "serviços ocorrerá após o pagamento da(s) parcela(s) vencida(s).", textoFont));
		texto61.setAlignment(Element.ALIGN_JUSTIFIED);
		texto61.setSpacingAfter(10);
		
		Paragraph texto62 = new Paragraph();
		texto62.add(new Phrase("6.2. Em caso de inadimplemento de 1 (um) boleto ou mais, incidirão juros e multa de acordo com o parágrafo único "
				+ "a seguir, estando ciente O (A) CONTRATANTE e concordando com propositura de ação de cobrança, inclusão do nome no SPC/SERASA, "
				+ "bem como demais órgãos de proteção ao crédito e protesto em cartório, e ainda honorários advocatícios no valor não inferior a "
				+ "10% (dez) por cento do valor principal da dívida. As despesas com tais providências correrão por conta do(a) CONTRATANTE.", textoFont));
		texto62.setAlignment(Element.ALIGN_JUSTIFIED);
		texto62.setSpacingAfter(10);
		
		Paragraph texto63 = new Paragraph();
		texto63.add(new Phrase("6.3. No caso do inadimplemento de qualquer boleto de pagamento, o(a) CONTRATANTE está ciente e concorda com a "
				+ "cobrança de multa na ordem de 10% (dez por cento) de multa por atraso em cima do valor de um único boleto, e de 1,0% (um) por "
				+ "cento de juros a.m..", textoFont));
		texto63.setAlignment(Element.ALIGN_JUSTIFIED);
		texto63.setSpacingAfter(10);
		
		Paragraph texto64 = new Paragraph();
		texto64.add(new Phrase("6.4. O(a) CONTRATANTE está de acordo que não haverá, sob hipótese alguma, devolução de qualquer valor para o(a) "
				+ "CONTRATANTE.", textoFont));
		texto64.setAlignment(Element.ALIGN_JUSTIFIED);
		texto64.setSpacingAfter(10);
		
		Paragraph texto65 = new Paragraph();
		texto65.add(new Phrase("6.5. O(a) CONTRATANTE fica ciente e autoriza o protesto de título em cartório, bem como, por confessar a dívida, "
				+ "concorda com propositura de ação de cobrança, na integralidade do valor aqui avençado, acrescido de juros e multa, nos termos "
				+ "do item 8.2, em caso de inadimplemento.", textoFont));
		texto65.setAlignment(Element.ALIGN_JUSTIFIED);
		texto65.setSpacingAfter(10);
		
		Paragraph titulo7 = new Paragraph();
		titulo7.add(new Phrase("CLÁUSULA 7 - CAMPANHA DE DESCONTOS", tituloFont));
		titulo7.setSpacingAfter(10);
		titulo7.setSpacingBefore(20);
		
		Paragraph texto71 = new Paragraph();
		texto71.add(new Phrase("7.1. Por esta cláusula o cliente fica ciente das condições de descontos a serem executados pela CONTRATADA, que "
				+ "buscará em primeiro lugar o melhor benefício em forma de desconto para o(a) CONTRATANTE, dependendo do banco e do transcurso "
				+ "de tempo em inadimplência. A CONTRATADA garante o mínimo de 50% de desconto do saldo devedor perante a financiadora referente "
				+ "à dívida, ao final do contrato, desde que respeitado o prazo mínimo de 12 (doze) meses, e poderá usar o prazo máximo de 24 "
				+ "(vinte e quatro) meses.", textoFont));
		texto71.setAlignment(Element.ALIGN_JUSTIFIED);
		texto71.setSpacingAfter(10);
		
		Paragraph texto72 = new Paragraph();
		texto72.add(new Phrase("7.2. A CONTRATADA possui acesso a algumas linhas de negociações especiais, onde os descontos poderão atingir "
				+ "maior escala, podendo chegar até a 80% (oitenta por cento), dependendo do banco financiado, não estando a CONTRATADA obrigada, "
				+ "naturalmente, à obtenção desses resultados. Ressalta-se que todo o desconto obtido será repassado ao(à) CONTRATANTE.", textoFont));
		texto72.setAlignment(Element.ALIGN_JUSTIFIED);
		texto72.setSpacingAfter(10);
		
		Paragraph titulo8 = new Paragraph();
		titulo8.add(new Phrase("CLÁUSULA 8 - DO DIREITO DE IMAGEM", tituloFont));
		titulo8.setSpacingAfter(10);
		titulo8.setSpacingBefore(20);
		
		Paragraph texto81 = new Paragraph();
		texto81.add(new Phrase("8.1. O(a) CONTRATANTE expressamente cede e autoriza o uso do direito de imagem respeitando a redação do art. 5º "
				+ "da Constituição Federal incisos X a seguir, através de vídeo gravado pelo(a) próprio(a) CONTRATANTE ou representante legal, "
				+ "que será usado na internet, no Facebook, Instagram, Whatsapp, Twitter, Linkedin, Youtube, Site da NFI ASSESSORIA, ou em outras "
				+ "plataformas que surgirem posteriormente, por tempo indeterminado, podendo ser solicitada há qualquer momento a retirada de todo "
				+ "material mediante solicitação prévia de no mínimo 30 (trinta) dias através de pedido específico por escrito enviado para matriz "
				+ "da CONTRATADA, podendo ser por carta enviada pelos correios, obrigatoriamente com AR (Aviso de Recebimento) ou por e-mail sendo "
				+ "enviado para o endereço eletrônico: nfiassessoria@gmail.com obrigatoriamente com confirmação de recebimento e leitura do "
				+ "e-mail.", textoFont));
		texto81.setAlignment(Element.ALIGN_JUSTIFIED);
		texto81.setSpacingAfter(10);

		Paragraph titulo9 = new Paragraph();
		titulo9.add(new Phrase("CLÁUSULA 9 - FORO DE ELEIÇÃO", tituloFont));
		titulo9.setSpacingAfter(10);
		titulo9.setSpacingBefore(20);
		
		Paragraph texto91 = new Paragraph();
		texto91.add(new Phrase("9.1. As partes elegem como foro o domicílio legal do cliente, ou em discordância de uma das partes, o foro do local "
				+ "onde o ato foi assinado, conforme Art. 100 inciso V, do Código de Processo Civil, para dirimir qualquer conflito referente ao "
				+ "presente instrumento.", textoFont));
		texto91.setAlignment(Element.ALIGN_JUSTIFIED);
		texto91.setSpacingAfter(10);
		
		Paragraph texto92 = new Paragraph();
		texto92.add(new Phrase("9.2. O(a) CONTRATANTE declara que NADA PODERÁ EXIGIR JUDICIAL OU EXTRAJUDICIALMENTE antes do fim do prazo de vigência "
				+ "do presente contrato, sob pena de declarar-se litigante de má-fé, incorrendo nas penas da lei.", textoFont));
		texto92.setAlignment(Element.ALIGN_JUSTIFIED);
		texto92.setSpacingAfter(10);

		Paragraph textoFinal = new Paragraph();
		textoFinal.add(new Phrase("E, por estarem JUSTAS E ACORDADAS, as partes assinam o presente CONTRATO DE PRESTAÇÃO DE SERVIÇOS ASSESSORIAIS em duas vias de igual teor e forma.", tituloFont));
		textoFinal.setAlignment(Element.ALIGN_JUSTIFIED);
		textoFinal.setSpacingAfter(30);
		
		Paragraph data = new Paragraph();
		data.add(new Phrase("Brasília-DF, " + pessoa.getContrato().getDataAssinatura().getDayOfMonth() + " de " + 
							pessoa.getContrato().getDataAssinatura().format(DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "PT"))) + " de " +
							pessoa.getContrato().getDataAssinatura().getYear() + 
							".", textoFont));
		data.setAlignment(Element.ALIGN_RIGHT);
		data.setSpacingAfter(10);
		
		Paragraph linha = new Paragraph();
		linha.add(new Phrase("--------------------------------------------------", textoFont));
		linha.setAlignment(Element.ALIGN_CENTER);
		linha.setSpacingBefore(40);
		
		Paragraph contratante = new Paragraph();
		contratante.add(new Phrase(pessoa.getNome().toString(), textoFont));
		contratante.setAlignment(Element.ALIGN_CENTER);
		contratante.setSpacingAfter(25);
		
		Paragraph contratado = new Paragraph();
		contratado.add(new Phrase(pessoa.getContrato().getEscritorio().getNome(), textoFont));
		contratado.setAlignment(Element.ALIGN_CENTER);
		contratado.setSpacingAfter(10);
		
//		document.add(logo);
		document.add(titulo);

		document.add(titulo_a);
		document.add(titulo_a1);
		document.add(texto_a1);
		document.add(titulo_a2);
		document.add(texto_a2);
		document.add(titulo_a3);
		document.add(texto_a3);
		document.add(titulo_a4);
		document.add(texto_a4);
		document.add(titulo_a5);
		document.add(texto_a5);
		document.add(titulo_a6);
		document.add(texto_a6);
		document.add(titulo_b);
		document.add(texto_b1);
		document.add(texto_b2);

		document.add(titulo1);
		document.add(texto11);
		document.add(texto12);
		document.add(texto13);
		document.add(texto14);
		document.add(texto15);
		document.add(texto16);
		document.add(texto17);
		document.add(texto18);
		document.add(texto19);
		document.add(texto110);
		document.add(texto111);
		document.add(titulo2);
		document.add(texto21);
		document.add(texto22);
		document.add(texto23);
		document.add(texto24);
		document.add(texto25);
		document.add(texto26);
		document.add(titulo3);
		document.add(texto31);
		document.add(texto32);
		document.add(texto33);
		document.add(texto34);
		document.add(texto35);
		document.add(texto36);
		document.add(texto37);
		document.add(texto38);
		document.add(texto39);
		document.add(texto310);
		document.add(titulo4);
		document.add(texto41);
		document.add(texto42);
		document.add(texto43);
		document.add(texto44);
		document.add(texto45);
		document.add(titulo5);
		document.add(texto51);
		document.add(texto52);
		document.add(texto53);
		document.add(titulo6);
		document.add(texto61);
		document.add(texto62);
		document.add(texto63);
		document.add(texto64);
		document.add(texto65);
		document.add(titulo7);
		document.add(texto71);
		document.add(texto72);
		document.add(titulo8);
		document.add(texto81);
		document.add(titulo9);
		document.add(texto91);
		document.add(texto92);
        
        document.add(textoFinal);
        
        document.add(data);
        
        document.add(linha);
        document.add(contratante);
        document.add(linha);
        document.add(contratado);
        
	}

}
