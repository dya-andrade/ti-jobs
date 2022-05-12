package br.com.tijobs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "habilidade")
public class Habilidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String habilidade;
	
	private Integer ano;
	
	private Integer anoPorcetagem;
	
	@ManyToOne
	@JoinColumn(name = "id_candidato", referencedColumnName = "id")
	private Candidato candidato;
	
	@ManyToOne
	@JoinColumn(name = "id_experiencia", referencedColumnName = "id")
	private Experiencia experiencia;


	public Integer getAnoPorcetagem() {
		return anoPorcetagem;
	}

	public void setAnoPorcetagem(Integer anoPorcetagem) {
		this.anoPorcetagem = anoPorcetagem;
	}

	public String textoHabilidade() {
		if(ano == null) {
			return "";
		} else {
			if(ano > 1) {
				return this.habilidade + ": " + this.ano + " anos";
			}else {
				return this.habilidade + ": " + this.ano + " ano";
			}
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}
	
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.anoPorcetagem = ano * 10;
		this.ano = ano;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Experiencia getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Experiencia experiencia) {
		this.experiencia = experiencia;
	}
	
}
