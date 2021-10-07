package net.gupisoft.iuris.reports;

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

import net.gupisoft.iuris.domain.entity.Pessoa;
import net.gupisoft.iuris.domain.entity.Poder;
import net.gupisoft.iuris.domain.entity.Procuracao;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPoder;

public class ProcuracaoPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		writer.setPageEvent(new PageEventListener());
		
//		String pattern = "dd/MM/yyy";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//		NumberFormat valor = NumberFormat.getCurrencyInstance();
		
		Procuracao procuracao = (Procuracao) model.get("procuracao");
		Pessoa outorgante = procuracao.getOutorgante();
		
		Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		Font textoFont = FontFactory.getFont(FontFactory.HELVETICA);
		Font textFontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		
		
		Chunk tituloSublinhado = new Chunk("PROCURAÇÃO", tituloFont);
		tituloSublinhado.setUnderline(01f, -1f);
		
		Paragraph titulo = new Paragraph();
		titulo.add(tituloSublinhado);
		titulo.setAlignment(Element.ALIGN_CENTER);
		titulo.setSpacingAfter(30);
		titulo.setSpacingBefore(50);
		
		
		Paragraph paragrafoOutorgante = new Paragraph();
		Chunk inicioOutorgante = new Chunk("OUTORGANTE: ", textFontBold);
		Chunk nomeCompletoOutorgante = new Chunk(outorgante.getNome(), textFontBold);
		Phrase fraseOutorgante = new Phrase(", "
				+ outorgante.getNacionalidade().toLowerCase() + ", "
				+ outorgante.getProfissao().toLowerCase() + ", "
				+ "CPF: " + outorgante.getCpf() + ", "
				+ "e-mail: " + outorgante.getEmail() + ", "
				+ "endereço: " + outorgante.getEndereco().getLogradouro() + ", nº "
				+ outorgante.getEndereco().getNumero() + ", bairro: "
				+ outorgante.getEndereco().getBairro() + ", "
				+ outorgante.getEndereco().getCidade() + "-"
				+ outorgante.getEndereco().getEstado() + ", "
				+ outorgante.getEndereco().getCep() + "."
				, textoFont);
		paragrafoOutorgante.add(inicioOutorgante);
		paragrafoOutorgante.add(nomeCompletoOutorgante);
		paragrafoOutorgante.add(fraseOutorgante);
		paragrafoOutorgante.setAlignment(Element.ALIGN_JUSTIFIED);
		paragrafoOutorgante.setSpacingAfter(20);
		paragrafoOutorgante.setSpacingBefore(10);
	
		
		Paragraph paragrafoOutorgado = new Paragraph();
		Chunk inicioOutorgado = new Chunk("OUTORGADO: ", textFontBold);
		String textoOutorgado ="";
		paragrafoOutorgado.add(inicioOutorgado);
		for (Pessoa outorgado : procuracao.getOutorgados()) {
			
			Chunk nomeCompletoOutorgado = new Chunk(outorgado.getNome(), textFontBold);
			
			textoOutorgado = ", "
					+ outorgado.getNacionalidade().toLowerCase()  + ", "
					+ outorgado.getCargoFuncionario().getDescricao().toLowerCase() + "(a), "
					+ outorgado.getOrgaoExpedidor() + " "
					+ outorgado.getRg() + ", com escritório no "
					+ outorgado.getEndereco().getLogradouro() + ", nº "
					+ outorgado.getEndereco().getNumero() + ", "
					+ outorgado.getEndereco().getCidade() + "-"
					+ outorgado.getEndereco().getEstado() + ", CEP: "
					+ outorgado.getEndereco().getCep() + ", e-mail: "
					+ outorgado.getEmail() + ", telefone: "
					+ outorgado.getTelefoneCelular().getNumero() + ". \r\n";
			Phrase fraseOutorgado = new Phrase(textoOutorgado, textoFont);
			paragrafoOutorgado.add(nomeCompletoOutorgado);
			paragrafoOutorgado.add(fraseOutorgado);
		}
		paragrafoOutorgado.setAlignment(Element.ALIGN_JUSTIFIED);
		paragrafoOutorgado.setSpacingAfter(20);
		paragrafoOutorgado.setSpacingBefore(10);

		Paragraph paragrafoPoder = new Paragraph();
		Chunk inicioPoderes = new Chunk("PODERES: ", textFontBold);
		String textoPoderes = "confere amplos poderes para o foro em geral à defesa de seus direitos, interesses, "
				+ "com as cláusula ad judicia e et extra, em quaisquer esferas e instâncias -  administrativa ou jurisdicional -, podendo acompanhar e "
				+ "propor contra quem de direito procedimentos administrativos e /ou ações competentes e defendê-los nas contrárias, seguindo umas e "
				+ "outras até final decisão, usando os recursos legais e acompanhando-os. Conferindo-lhes, ainda, poderes especiais para";
		paragrafoPoder.add(inicioPoderes);
		paragrafoPoder.add(textoPoderes);

		for (Poder poder : procuracao.getPoderes()) {
			if (poder.getTipoPoder() == TipoPoder.especifico) {
				Phrase frasePoder = new Phrase(", " + poder.getDescricao(), textoFont);
				paragrafoPoder.add(frasePoder);
			}
		}
		paragrafoPoder.add(new Phrase("."));
		paragrafoPoder.setAlignment(Element.ALIGN_JUSTIFIED);
		paragrafoPoder.setSpacingAfter(30);
		paragrafoPoder.setSpacingBefore(10);

//
//		Paragraph textoFinal = new Paragraph();
//		textoFinal.add(new Phrase("E, por estarem JUSTAS E ACORDADAS, as partes assinam o presente CONTRATO DE PRESTAÇÃO DE SERVIÇOS ASSESSORIAIS em duas vias de igual teor e forma.", tituloFont));
//		textoFinal.setAlignment(Element.ALIGN_JUSTIFIED);
//		textoFinal.setSpacingAfter(30);
//		
		Paragraph data = new Paragraph();
		data.add(new Phrase("Brasília-DF, " + procuracao.getData().getDayOfMonth() + " de " + 
				procuracao.getData().format(DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "PT"))) + " de " +
				procuracao.getData().getYear() + 
							".", textoFont));
		data.setAlignment(Element.ALIGN_RIGHT);
		data.setSpacingAfter(20);
//		
		Paragraph linha = new Paragraph();
		linha.add(new Phrase("--------------------------------------------------", textoFont));
		linha.setAlignment(Element.ALIGN_CENTER);
		linha.setSpacingBefore(30);
		
		Paragraph contratante = new Paragraph();
		contratante.add(new Phrase(outorgante.getNome().toString(), textoFont));
		contratante.setAlignment(Element.ALIGN_CENTER);
		contratante.setSpacingAfter(25);
		
		
		Paragraph contratado = new Paragraph();
		for (Pessoa outorgado2 : procuracao.getOutorgados()) {
			
			contratado.add(new Phrase("--------------------------------------------------\r\n", textoFont));
			
			contratado.add(new Phrase(outorgado2.getNome() + "\r\n\n", textoFont));
			contratado.setAlignment(Element.ALIGN_CENTER);
			contratado.setSpacingAfter(10);
		}
		contratado.setAlignment(Element.ALIGN_CENTER);
		
		document.add(titulo);
		document.add(paragrafoOutorgante);
		document.add(paragrafoOutorgado);
		document.add(paragrafoPoder);
    
        document.add(data);
        
        document.add(linha);
        document.add(contratante);
        document.add(contratado);
        
	}

}
