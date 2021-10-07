package net.gupisoft.iuris.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.gupisoft.iuris.domain.entity.enumeration.StatusPagamento;

@Entity
public class Parcela {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	private Integer parcela;
	
	private LocalDate dataVencimento;
	
	private BigDecimal valor;
	
	private LocalDate dataPagamento;
	
	private BigDecimal valorPagamento;
	
	private StatusPagamento status;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "contrato_id")
	private Contrato contrato;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}

	public LocalDate getDataVencimento() {
		LocalDate data = dataVencimento;
		if (data != null) {
			data.plusDays(1);
		}
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
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
		Parcela other = (Parcela) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parcela [id=" + id + ", parcela=" + parcela + ", dataVencimento=" + dataVencimento + ", valor=" + valor
				+ ", dataPagamento=" + dataPagamento + ", valorPagamento=" + valorPagamento + ", status=" + status
				+ ", observacao=" + observacao + "]";
	}
}
