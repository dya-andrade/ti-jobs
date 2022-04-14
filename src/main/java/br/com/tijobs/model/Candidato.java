package br.com.tijobs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "candidato")
public class Candidato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6676353195254272882L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String telefone;

	private String cidade;

	@Lob //---
	private byte[] foto;

	private String titulo;

	private String ingles;

	private String descricao;

	private String github;

	private String linkedin;

	@Lob //---
	private byte[] curriculo;

	private String carreira;

	private String experiencia;

	//---
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "candidato_habilidade", joinColumns = @JoinColumn(name = "id_candidato"), inverseJoinColumns = @JoinColumn(name = "id_habilidade"))
	private List<Habilidade> habilidades = new ArrayList<Habilidade>();

	private Boolean semExperiencia;

	//---
	@OneToMany(mappedBy = "candidato", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Experiencia> experiencias = new ArrayList<Experiencia>();

	private String tamanhoEmpresa;

	private String tipoContrato;

	private String pretensaoSalarial;

	private String statusBusca;

	private String remoto;

	private String deficienteFisico;
	
	//-------
	@Transient
	private Habilidade primeiraHabilidade;
	
	@Transient
	private Habilidade segundaHabilidade;
	
	@Transient
	private Habilidade terceiraHabilidade;
	
	@Transient
	private Habilidade quartaHabilidade;
	
	@Transient
	private Habilidade quintaHabilidade;
	
	@Transient
	private Experiencia primeiraExperiencia = new Experiencia();

	@Transient
	private Experiencia segundaExperiencia = new Experiencia();

	@Transient
	private Experiencia terceiraExperiencia = new Experiencia();
	

	public Habilidade getPrimeiraHabilidade() {
		return primeiraHabilidade;
	}

	public void setPrimeiraHabilidade(Habilidade primeiraHabilidade) {
		this.primeiraHabilidade = primeiraHabilidade;
		habilidades.add(primeiraHabilidade);
	}

	public Habilidade getSegundaHabilidade() {
		return segundaHabilidade;
	}

	public void setSegundaHabilidade(Habilidade segundaHabilidade) {
		this.segundaHabilidade = segundaHabilidade;
		habilidades.add(segundaHabilidade);

	}

	public Habilidade getTerceiraHabilidade() {
		return terceiraHabilidade;
	}

	public void setTerceiraHabilidade(Habilidade terceiraHabilidade) {
		this.terceiraHabilidade = terceiraHabilidade;
		habilidades.add(terceiraHabilidade);
	}

	public Habilidade getQuartaHabilidade() {
		return quartaHabilidade;
	}

	public void setQuartaHabilidade(Habilidade quartaHabilidade) {
		this.quartaHabilidade = quartaHabilidade;
		habilidades.add(quartaHabilidade);
	}

	public Habilidade getQuintaHabilidade() {
		return quintaHabilidade;
	}

	public void setQuintaHabilidade(Habilidade quintaHabilidade) {
		this.quintaHabilidade = quintaHabilidade;
		habilidades.add(quintaHabilidade);
	}

	public Experiencia getPrimeiraExperiencia() {
		return primeiraExperiencia;
	}

	public void setPrimeiraExperiencia(Experiencia primeiraExperiencia) {
		this.primeiraExperiencia = primeiraExperiencia;
		primeiraExperiencia.setCandidato(this);
		experiencias.add(primeiraExperiencia);
	}

	public Experiencia getSegundaExperiencia() {
		return segundaExperiencia;
	}

	public void setSegundaExperiencia(Experiencia segundaExperiencia) {
		this.segundaExperiencia = segundaExperiencia;
		segundaExperiencia.setCandidato(this);
		experiencias.add(segundaExperiencia);
	}

	public Experiencia getTerceiraExperiencia() {
		return terceiraExperiencia;
	}

	public void setTerceiraExperiencia(Experiencia terceiraExperiencia) {
		this.terceiraExperiencia = terceiraExperiencia;
		terceiraExperiencia.setCandidato(this);
		experiencias.add(terceiraExperiencia);
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIngles() {
		return ingles;
	}

	public void setIngles(String ingles) {
		this.ingles = ingles;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public byte[] getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(byte[] curriculo) {
		this.curriculo = curriculo;
	}

	public String getCarreira() {
		return carreira;
	}

	public void setCarreira(String carreira) {
		this.carreira = carreira;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}

	public Boolean getSemExperiencia() {
		return semExperiencia;
	}

	public void setSemExperiencia(Boolean semExperiencia) {
		this.semExperiencia = semExperiencia;
	}

	public List<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

	public String getTamanhoEmpresa() {
		return tamanhoEmpresa;
	}

	public void setTamanhoEmpresa(String tamanhoEmpresa) {
		this.tamanhoEmpresa = tamanhoEmpresa;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getPretensaoSalarial() {
		return pretensaoSalarial;
	}

	public void setPretensaoSalarial(String pretensaoSalarial) {
		this.pretensaoSalarial = pretensaoSalarial;
	}

	public String getStatusBusca() {
		return statusBusca;
	}

	public void setStatusBusca(String statusBusca) {
		this.statusBusca = statusBusca;
	}

	public String getRemoto() {
		return remoto;
	}

	public void setRemoto(String remoto) {
		this.remoto = remoto;
	}

	public String getDeficienteFisico() {
		return deficienteFisico;
	}

	public void setDeficienteFisico(String deficienteFisico) {
		this.deficienteFisico = deficienteFisico;
	}
}