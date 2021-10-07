package net.gupisoft.iuris.domain.entity.enumeration;


public enum CargoFuncionario {

	TELEFONISTA("Telefonista"),
	ADMINISTRATIVO("Administrativo"),
	CONSULTOR("Consultor"),
	ADVOGADO("Advogado"),
	GERENTE("Gerente");
	
	private String descricao;
	
	CargoFuncionario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
