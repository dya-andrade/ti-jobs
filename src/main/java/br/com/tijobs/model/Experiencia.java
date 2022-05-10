package br.com.tijobs.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "experiencia")
public class Experiencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM - yyyy", new Locale("pt", "BR"));

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String perfil;

	private String cargo;

	private LocalDate dataInicio;

	private LocalDate dataTermino;

	private Boolean atual;

	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_candidato", referencedColumnName = "id")
	private Candidato candidato;

	@OneToMany(mappedBy = "experiencia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Habilidade> habilidades;

	public String duracaoTrabalho() {

		LocalDate dataTermino;

		if (this.dataTermino == null) {
			dataTermino = LocalDate.now();
		} else {
			dataTermino = this.dataTermino;
		}

		Long meses = this.dataInicio.until(dataTermino, ChronoUnit.MONTHS);

		double anos = meses.doubleValue() / 12;

		BigDecimal bd = new BigDecimal(anos).setScale(1, RoundingMode.HALF_UP);

		double duracaoDouble = bd.doubleValue();

		String duracao = String.valueOf(duracaoDouble);

		if (duracaoDouble < 1) {
			duracao = duracao.replaceAll("[\\d].", " ");
			return duracao + " meses";
		} else if (duracaoDouble == 1) {
			duracao = duracao.replaceAll(".[\\d]", " ano");
			return duracao;
		} else {

			if (duracao.contains(".")) {
				duracao = duracao.replaceAll("[.]", " ano(s) e ");
				return duracao + " meses";
			} else {
				return duracao + " anos(s)";
			}
		}
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

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public String dataInicioFormatada() {
		if (dataInicio != null) {
			return dataInicio.format(formatter);
		}
		return null;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public String dataTerminoFormatada() {
		if (dataTermino != null) {
			return dataTermino.format(formatter);
		}
		return null;
	}

	public void setDataTermino(LocalDate dataTermino) {
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

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}

	public String habilidadesFormatada() {

		String habilidadesTexto = "";

		for (Habilidade habilidade : habilidades) {
			if(!habilidade.getHabilidade().isBlank()) {
				habilidadesTexto += habilidade.getHabilidade() + " | ";
			}
		}

		return habilidadesTexto;
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