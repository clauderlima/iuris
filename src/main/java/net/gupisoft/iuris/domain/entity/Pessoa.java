package net.gupisoft.iuris.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import net.gupisoft.iuris.domain.entity.enumeration.CargoFuncionario;
import net.gupisoft.iuris.domain.entity.enumeration.TipoPessoa;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotNull(message = "Informe a Data de Nascimento")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "Informe o Sexo")
	private String sexo;
	
	@NotBlank(message = "Informe o Estado Civil")
	private String estadoCivil;
	
	@NotBlank(message = "Informe o CPF")
	private String cpf;
	
	@NotBlank(message = "Informe o RG")
	private String rg;
	
	@NotBlank(message = "Informe o Órgão Expedidor")
	private String orgaoExpedidor;
	
	@NotBlank(message = "Informe a Nacionalidade")
	private String nacionalidade;
	
	private String profissao;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Endereco endereco;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Contrato contrato;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Procuracao procuracao;
	
	//@NotNull(message = "Informe o celular")
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Telefone telefoneCelular;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Telefone telefoneFixo;
	
	//@NotBlank(message = "Informe o email")
	private String email;
	
	@OneToMany(mappedBy = "cliente", cascade=CascadeType.ALL)
	private List<HistoricoContato> historicos;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Escritorio escritorio;
	
	private TipoPessoa tipoPessoa;
	
	private CargoFuncionario cargoFuncionario;
	
	@OneToOne(mappedBy = "pessoa")
	private Usuario usuario;
	
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

	public LocalDate getDataNascimento() {
		LocalDate data = dataNascimento;
		if (data != null) {
			data.plusDays(1);
		}
		return data;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Procuracao getProcuracao() {
		return procuracao;
	}

	public void setProcuracao(Procuracao procuracao) {
		this.procuracao = procuracao;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<HistoricoContato> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<HistoricoContato> historicos) {
		this.historicos = historicos;
	}

	public Escritorio getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public CargoFuncionario getCargoFuncionario() {
		return cargoFuncionario;
	}

	public void setCargoFuncionario(CargoFuncionario cargoFuncionario) {
		this.cargoFuncionario = cargoFuncionario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
