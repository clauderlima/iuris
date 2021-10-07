package net.gupisoft.iuris.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Escritorio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@NotBlank(message = "Informe o CNPJ")
	private String cnpj;
	
	private String email;
	
	@NotNull(message = "Informe o celular")
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Telefone telefoneCelular;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Telefone telefoneFixo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Endereco endereco;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Pessoa responsavel;

	public boolean isNovo() {
		return id == null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Telefone getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(Telefone telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public Telefone getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(Telefone telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escritorio other = (Escritorio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Escritorio [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", email=" + email + ", telefoneCelular="
				+ telefoneCelular + ", telefoneFixo=" + telefoneFixo + ", endereco=" + endereco + ", responsavel="
				+ responsavel + "]";
	}
}