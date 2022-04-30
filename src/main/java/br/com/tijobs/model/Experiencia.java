package br.com.tijobs.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "experiencia")
public class Experiencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String perfil;

	private String cargo;

	private LocalDateTime dataInicio;

	private LocalDateTime dataTermino;

	private Boolean atual;

	private String habilidades;
	
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Candidato candidato;

	//-------
	@Transient
	private Habilidade primeiraHabilidade;
	
	@Transient
	private Habilidade segundaHabilidade;
	
	@Transient
	private Habilidade terceiraHabilidade;
	
	@Transient
	private Habilidade quartaHabilidade;
	

	public Habilidade getPrimeiraHabilidade() {
		return primeiraHabilidade;
	}

	public void setPrimeiraHabilidade(Habilidade primeiraHabilidade) {
		this.primeiraHabilidade = primeiraHabilidade;
		habilidades = habilidades + "," + primeiraHabilidade.getNome();
	}

	public Habilidade getSegundaHabilidade() {
		return segundaHabilidade;
	}

	public void setSegundaHabilidade(Habilidade segundaHabilidade) {
		this.segundaHabilidade = segundaHabilidade;
		habilidades = habilidades + "," + segundaHabilidade.getNome();
	}

	public Habilidade getTerceiraHabilidade() {
		return terceiraHabilidade;
	}

	public void setTerceiraHabilidade(Habilidade terceiraHabilidade) {
		this.terceiraHabilidade = terceiraHabilidade;
		habilidades = habilidades + "," + terceiraHabilidade.getNome();
	}

	public Habilidade getQuartaHabilidade() {
		return quartaHabilidade;
	}

	public void setQuartaHabilidade(Habilidade quartaHabilidade) {
		this.quartaHabilidade = quartaHabilidade;
		habilidades = habilidades + "," + quartaHabilidade.getNome();
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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Boolean getAtual() {
		return atual;
	}

	public void setAtual(Boolean atual) {
		this.atual = atual;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
}