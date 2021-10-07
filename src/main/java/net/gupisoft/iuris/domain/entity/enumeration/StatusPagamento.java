package net.gupisoft.iuris.domain.entity.enumeration;


public enum StatusPagamento {

	ABERTA("Aberta"),
	ATRASADA("Atrasada"),
	PAGA("Paga");
	
	private String descricao;
	
	StatusPagamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
