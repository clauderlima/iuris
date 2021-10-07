package net.gupisoft.iuris.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import net.gupisoft.iuris.domain.entity.enumeration.TipoPoder;

@Entity
public class Poder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@Column(length = 1000)
	@NotBlank(message = "A descrição é obrigatória")
	private String descricao;
	
	@NotNull(message = "O tipo de poder é obrigatório")
	private TipoPoder tipoPoder;
	
	public boolean isNovo() {
		return id == null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoPoder getTipoPoder() {
		return tipoPoder;
	}

	public void setTipoPoder(TipoPoder tipoPoder) {
		this.tipoPoder = tipoPoder;
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
		Poder other = (Poder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Poder [id=" + id + ", descricao=" + descricao + ", tipoPoder=" + tipoPoder + "]";
	}
}
