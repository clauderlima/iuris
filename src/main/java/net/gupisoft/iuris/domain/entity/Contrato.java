package net.gupisoft.iuris.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import net.gupisoft.iuris.domain.entity.enumeration.TipoPagamento;

@Entity
public class Contrato {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@NotNull(message = "Informe o número do contrato")
	private String numero;
	
	@NotNull(message = "Informe a data de assinatura do contrato")
	private LocalDate dataAssinatura;
	
	@OneToOne
	private Pessoa cliente;
	
	private BigDecimal valorParcela;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@NotNull(message = "Informe o consultor do contrato")
	private Pessoa consultor;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@NotNull(message = "Informe o escritório em que foi assinado o contrato")
	private Escritorio escritorio;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Pessoa titularFinanciamento;

	
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
	private Integer qtdParcelasPagamento;
	
	@OneToMany(mappedBy = "contrato")
	private List<Parcela> parcelas;
	
	@Transient
	private BigDecimal valorContrato;
	
	public boolean isNovo() {
		return id == null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(LocalDate dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Pessoa getConsultor() {
		return consultor;
	}

	public void setConsultor(Pessoa consultor) {
		this.consultor = consultor;
	}

	public Escritorio getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}

	public Pessoa getTitularFinanciamento() {
		return titularFinanciamento;
	}

	public void setTitularFinanciamento(Pessoa titularFinanciamento) {
		this.titularFinanciamento = titularFinanciamento;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getQtdParcelasPagamento() {
		return qtdParcelasPagamento;
	}

	public void setQtdParcelasPagamento(Integer qtdParcelasPagamento) {
		this.qtdParcelasPagamento = qtdParcelasPagamento;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	public BigDecimal getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}

	@Override
	public String toString() {
		return "Contrato [id=" + id + ", numero=" + numero + ", dataAssinatura=" + dataAssinatura + ", cliente="
				+ cliente + ", valorParcela=" + valorParcela + ", consultor=" + consultor + ", escritorio=" + escritorio
				+ ", titularFinanciamento=" + titularFinanciamento + ", tipoPagamento=" + tipoPagamento
				+ ", qtdParcelasPagamento=" + qtdParcelasPagamento + ", parcelas=" + parcelas + ", valorContrato="
				+ valorContrato + "]";
	}

	
}
