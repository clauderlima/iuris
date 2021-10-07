package net.gupisoft.iuris.domain.entity.enumeration;

public enum TipoPagamento {
	
	CARTAO("Cartão"),
	CHEQUE("Cheque"),
	DINHEIRO("Dinheiro"),
	BOLETO("Boleto");
	
	private String descricao;
	
	TipoPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
