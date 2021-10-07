package net.gupisoft.iuris.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
public class Captacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O email é obrigatório")
	private String email;
	
	@NotBlank(message = "O telefone é obrigatório")
	private String telefone;
	
	private BigDecimal valorFinanciado;
	
	private Integer qtdTotalParcelas;
	
	private Integer qtdParcelasPagas;
	
	private Integer qtdParcelasEmAtraso;
	
	private BigDecimal valorAtualParcela;
	
	private Boolean contato;
	
	private Boolean ativo;
	
	@OneToMany(mappedBy = "captacao", cascade=CascadeType.ALL)
	private List<HistoricoContato> historicos;
	
	@Transient
	private Integer qtdParcelasRestante;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getValorFinanciado() {
		return valorFinanciado;
	}

	public void setValorFinanciado(BigDecimal valorFinanciado) {
		this.valorFinanciado = valorFinanciado;
	}

	public Integer getQtdTotalParcelas() {
		return qtdTotalParcelas;
	}

	public void setQtdTotalParcelas(Integer qtdTotalParcelas) {
		this.qtdTotalParcelas = qtdTotalParcelas;
	}

	public Integer getQtdParcelasPagas() {
		return qtdParcelasPagas;
	}

	public void setQtdParcelasPagas(Integer qtdParcelasPagas) {
		this.qtdParcelasPagas = qtdParcelasPagas;
	}

	public Integer getQtdParcelasEmAtraso() {
		return qtdParcelasEmAtraso;
	}

	public void setQtdParcelasEmAtraso(Integer qtdParcelasEmAtraso) {
		this.qtdParcelasEmAtraso = qtdParcelasEmAtraso;
	}

	public BigDecimal getValorAtualParcela() {
		return valorAtualParcela;
	}

	public void setValorAtualParcela(BigDecimal valorAtualParcela) {
		this.valorAtualParcela = valorAtualParcela;
	}

	public Boolean getContato() {
		return contato;
	}

	public void setContato(Boolean contato) {
		this.contato = contato;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getQtdParcelasRestante() {
		if (qtdTotalParcelas == null || qtdParcelasEmAtraso == null || qtdParcelasPagas == null) {
			return 0;
		}
		return qtdTotalParcelas - qtdParcelasEmAtraso - qtdParcelasPagas;
	}
	
	public BigDecimal getMetaQuitacao() {
		if (qtdParcelasEmAtraso == null) {
			return null;
		}
		return valorAtualParcela.multiply(new BigDecimal(this.getQtdParcelasRestante()+qtdParcelasEmAtraso)).divide(new BigDecimal(2));
	}
	
	public BigDecimal getDivida() {
		if (qtdParcelasEmAtraso == null) {
			return null;
		}
		return valorAtualParcela.multiply(new BigDecimal(this.getQtdParcelasRestante()+qtdParcelasEmAtraso));
	}

	public List<HistoricoContato> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<HistoricoContato> historicos) {
		this.historicos = historicos;
	}


	public boolean isNovo() {
		return id == null;
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
		Captacao other = (Captacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
