package net.gupisoft.iuris.domain.entity.enumeration;

public enum TipoPoder {
	
	geral("Geral"),
	especifico("Específico"),;
	
	private String descricao;
	
	TipoPoder(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
