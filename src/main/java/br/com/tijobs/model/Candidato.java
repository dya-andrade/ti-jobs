package br.com.tijobs.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.io.IOUtils;
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
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;

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

	private Boolean semExperiencia;

	//---
	
	@OneToMany(mappedBy = "candidato", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Habilidade> habilidades;
	
	@OneToMany(mappedBy = "candidato", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Experiencia> experiencias;

	private String tamanhoEmpresa;

	private String tipoContrato;

	private String pretensaoSalarial;

	private String statusBusca;

	private String remoto;

	private String deficienteFisico;
	
	//-------
	
	
	
	public String fotoStr() throws IOException {
		byte[] foto = this.foto;

		if (foto != null) {
			return new String(Base64.getEncoder().encode(foto));
		} else {
			return new String(Base64.getEncoder()
					.encode(IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("profile.jpg"))));
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Boolean getSemExperiencia() {
		return semExperiencia;
	}

	public void setSemExperiencia(Boolean semExperiencia) {
		this.semExperiencia = semExperiencia;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
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