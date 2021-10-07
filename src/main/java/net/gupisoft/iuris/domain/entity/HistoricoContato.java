package net.gupisoft.iuris.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class HistoricoContato {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Pessoa cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "captacao_id")
	private Captacao captacao;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Pessoa responsavelContato;
	
	private String tipoContato;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Pessoa responsavelEvento;
	
	private LocalDateTime dataHoraContato;
	
	private LocalDateTime dataHoraEvento;
	
	@ManyToOne
	private Titulo titulo;
	
	@Column(length = 2500)
	private String mensagemEvento;
	
	@Column(length = 2500)
	private String mensagemContato;

	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public Pessoa getResponsavelContato() {
		return responsavelContato;
	}

	public void setResponsavelContato(Pessoa responsavelContato) {
		this.responsavelContato = responsavelContato;
	}

	public String getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}

	public Pessoa getResponsavelEvento() {
		return responsavelEvento;
	}

	public void setResponsavelEvento(Pessoa responsavelEvento) {
		this.responsavelEvento = responsavelEvento;
	}

	public LocalDateTime getDataHoraContato() {
		return dataHoraContato;
	}

	public void setDataHoraContato(LocalDateTime dataHoraContato) {
		this.dataHoraContato = dataHoraContato;
	}

	public LocalDateTime getDataHoraEvento() {
		return dataHoraEvento;
	}

	public void setDataHoraEvento(LocalDateTime dataHoraEvento) {
		this.dataHoraEvento = dataHoraEvento;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public String getMensagemEvento() {
		return mensagemEvento;
	}

	public void setMensagemEvento(String mensagemEvento) {
		this.mensagemEvento = mensagemEvento;
	}

	public String getMensagemContato() {
		return mensagemContato;
	}

	public void setMensagemContato(String mensagemContato) {
		this.mensagemContato = mensagemContato;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Captacao getCaptacao() {
		return captacao;
	}

	public void setCaptacao(Captacao captacao) {
		this.captacao = captacao;
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
		HistoricoContato other = (HistoricoContato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoricoContato [id=" + id + ", cliente=" + cliente + ", responsavelContato=" + responsavelContato
				+ ", tipoContato=" + tipoContato + ", responsavelEvento=" + responsavelEvento + ", dataHoraContato="
				+ dataHoraContato + ", dataHoraEvento=" + dataHoraEvento + ", titulo=" + titulo + ", mensagemEvento="
				+ mensagemEvento + ", mensagemContato=" + mensagemContato + ", status=" + status + "]";
	}
}
