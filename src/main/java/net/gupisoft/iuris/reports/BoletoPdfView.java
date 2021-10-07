package net.gupisoft.iuris.reports;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.Santander;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

public class BoletoPdfView {
	    public static void main(String[] args) {  
	        Datas datas = Datas.novasDatas()
	                .comDocumento(29, 04, 2013)
	                .comProcessamento(29, 04, 2013)
	                .comVencimento(8, 05, 2013);  

//	        Endereco enderecoBeneficiario = Endereco.novoEndereco()
//	        		.comLogradouro("Av das Empresas, 555")  
//	        		.comBairro("Bairro Grande")  
//	        		.comCep("01234-555")
//	        		.comCidade("São Paulo")  
//	        		.comUf("SP");  

	        //Quem emite o boleto
	        Beneficiario beneficiario = Beneficiario.novoBeneficiario().comNomeBeneficiario("PETROBRAS")
	                .comAgencia("6790").comDigitoAgencia("0").comCarteira("102")
	                .comNumeroConvenio("5260965").comNossoNumero("105613749501")
	                .comDigitoNossoNumero("4");  

//	        Endereco enderecoPagador = Endereco.novoEndereco()
//	        		.comLogradouro("Av dos testes, 111 apto 333")  
//	        		.comBairro("Bairro Teste")  
//	        		.comCep("01234-111")  
//	        		.comCidade("São Paulo")  
//	        		.comUf("SP");  
	        
	        //Quem paga o boleto
	        Pagador pagador = Pagador.novoPagador().comNome("PAULO SILVEIRA") ; 

	        Banco banco = new Santander();  

	        Boleto boleto = Boleto.novoBoleto().comEspecieDocumento("DM")
	        		.comBanco(banco).comDatas(datas).comBeneficiario(beneficiario)
	        		.comPagador(pagador).comValorBoleto(219.50).comAceite(Boolean.FALSE)
	        		.comInstrucoes("instrucao 1", "instrucao 2", "instrucao 3", "instrucao 4")
	        		.comLocaisDePagamento("local 1", "local 2")
	        		.comNumeroDoDocumento("105613749501");  

	        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);  

	        // Para gerar um boleto em PDF  
	        gerador.geraPDF("Boleto.pdf");  

	        // Para gerar um boleto em PNG  
	        gerador.geraPNG("BancoDoBrasil.png");  

	        // Para gerar um array de bytes a partir de um PDF  
	        //byte[] bPDF = gerador.geraPDF();  

	        // Para gerar um array de bytes a partir de um PNG  
	        //byte[] bPNG = gerador.geraPNG();
	    }  
	}  
