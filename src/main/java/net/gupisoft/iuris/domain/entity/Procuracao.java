package net.gupisoft.iuris.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Procuracao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;

	@OneToOne
	@JoinColumn(name = "procuracao_id")
	private Pessoa outorgante;
	
	@ManyToMany
	@JoinTable(name = "procuracao_outorgado", 
			joinColumns = @JoinColumn(name = "procuracao_id"), 
			inverseJoinColumns = @JoinColumn(name = "outorgado_id"))
	private List<Pessoa> outorgados;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@NotNull(message = "Informe o escritório em que foi assinado o contrato")
	private Escritorio escritorio;
	
	@ManyToMany
	@JoinTable(name = "procuracao_poder",
			joinColumns = @JoinColumn(name = "procuracao_id"),
			inverseJoinColumns = @JoinColumn(name = "poder_id"))
	private List<Poder> poderes;
	
	@OneToOne
	private Pessoa cliente;
	
	private LocalDate data;
	
	public boolean isNovo() {
		return id == null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getOutorgante() {
		return outorgante;
	}

	public void setOutorgante(Pessoa outorgante) {
		this.outorgante = outorgante;
	}

	public List<Pessoa> getOutorgados() {
		return outorgados;
	}

	public void setOutorgados(List<Pessoa> outorgados) {
		this.outorgados = outorgados;
	}

	public List<Poder> getPoderes() {
		return poderes;
	}

	public void setPoderes(List<Poder> poderes) {
		this.poderes = poderes;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Escritorio getEscritorio() {
		return escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
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
		Procuracao other = (Procuracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Procuracao [id=" + id + ", outorgante=" + outorgante + ", outorgados=" + outorgados + ", poderes="
				+ poderes + "]";
	}
}