package net.gupisoft.iuris.domain.entity.enumeration;

public enum TipoPessoa {
	
	cliente("Cliente"),
	funcionario("Funcionário"),;
	
	private String descricao;
	
	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
