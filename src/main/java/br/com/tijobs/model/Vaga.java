package br.com.tijobs.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "vaga")
public class Vaga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5058507194333298243L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String anonima;
	
	private String aceitaCandidatoFora;
	
	@Lob
	private String apresentacao;
	
	private String beneficios;
	
	private String tipoTrabalho;
	
	private String remoto;
	
	private String profissao;
	
	private String nivelExperiencia;
	
	private String salario;
	
	private String tipoContrato;
	
	private String area;
	
	private String principaisTecnologias;
	
	@Lob
	private String responsabilidades;
	
	@Lob
	private String skillsDesejaveis;
	
	@Lob
	private String skillsObrigatorias;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "vaga_candidato", joinColumns = @JoinColumn(name = "id_vaga"), 
	inverseJoinColumns = @JoinColumn(name = "id_candidato"))
	private List<Candidato> candidados;

	private LocalDateTime dataCriacao;
	
	@Column(columnDefinition = "0")
	private Boolean desativada;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnonima() {
		return anonima;
	}

	public void setAnonima(String anonima) {
		this.anonima = anonima;
	}

	public String getAceitaCandidatoFora() {
		return aceitaCandidatoFora;
	}

	public void setAceitaCandidatoFora(String aceitaCandidatoFora) {
		this.aceitaCandidatoFora = aceitaCandidatoFora;
	}

	public String getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public String getTipoTrabalho() {
		return tipoTrabalho;
	}

	public void setTipoTrabalho(String tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}

	public String getRemoto() {
		return remoto;
	}

	public void setRemoto(String remoto) {
		this.remoto = remoto;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getNivelExperiencia() {
		return nivelExperiencia;
	}

	public void setNivelExperiencia(String nivelExperiencia) {
		this.nivelExperiencia = nivelExperiencia;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPrincipaisTecnologias() {
		return principaisTecnologias;
	}

	public void setPrincipaisTecnologias(String principaisTecnologias) {
		this.principaisTecnologias = principaisTecnologias;
	}

	public String getResponsabilidades() {
		return responsabilidades;
	}

	public void setResponsabilidades(String responsabilidades) {
		this.responsabilidades = responsabilidades;
	}

	public String getSkillsDesejaveis() {
		return skillsDesejaveis;
	}

	public void setSkillsDesejaveis(String skillsDesejaveis) {
		this.skillsDesejaveis = skillsDesejaveis;
	}

	public String getSkillsObrigatorias() {
		return skillsObrigatorias;
	}

	public void setSkillsObrigatorias(String skillsObrigatorias) {
		this.skillsObrigatorias = skillsObrigatorias;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Candidato> getCandidados() {
		return candidados;
	}

	public void setCandidados(List<Candidato> candidados) {
		this.candidados = candidados;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Boolean getDesativada() {
		return desativada;
	}

	public void setDesativada(Boolean desativada) {
		this.desativada = desativada;
	}
}
