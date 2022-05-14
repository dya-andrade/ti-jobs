package br.com.tijobs.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Candidato;
import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.model.habilidade.Habilidade;
import br.com.tijobs.repository.habilidade.HabilidadeRepository;
import br.com.tijobs.service.VagaService;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class ChartViewController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PieChartModel pieModelEmpresa;

	private PieChartModel pieModelCandidato;

	@Autowired
	private VagaService vagaService;

	@Autowired
	private HabilidadeRepository habilidadeRepository;

	@Autowired
	private UtilService utilService;

	@PostConstruct
	public void init() {
		createPieModelEmpresa();
		createPieModelCandidato();
	}

	private void createPieModelCandidato() {

		Candidato perfilCandidato = utilService.perfilCandidato();

		List<Habilidade> habilidadesCandidato = new ArrayList<Habilidade>();

		if (perfilCandidato != null) {

			habilidadesCandidato = habilidadeRepository.findByCandidato(perfilCandidato);

			habilidadesCandidato = habilidadesCandidato.stream()
					.sorted(Comparator.comparing(Habilidade::getAno).reversed()).collect(Collectors.toList());
		}
		
		pieModelCandidato = new PieChartModel();

		if (!habilidadesCandidato.isEmpty()) {

			for (int i = 0; i < habilidadesCandidato.size(); i++) {
				Habilidade habilidade = habilidadesCandidato.get(i);
				pieModelCandidato.set(habilidade.getHabilidade(), habilidade.getAno());
			}

		} else {
			pieModelCandidato.set("Tecnologia 1", 0);
			pieModelCandidato.set("Tecnologia 2", 0);
			pieModelCandidato.set("Tecnologia 3", 0);
			pieModelCandidato.set("Tecnologia 4", 0);
		}
		
		pieModelCandidato.setTitle("Principais Tecnologias");
		pieModelCandidato.setLegendPosition("e");
		pieModelCandidato.setFill(false);
		pieModelCandidato.setShowDataLabels(true);
		pieModelCandidato.setDiameter(150);
		pieModelCandidato.setShadow(false);

		pieModelCandidato.setSeriesColors("9384df,33d8ff,84f560,558ac3");

	}

	private void createPieModelEmpresa() {

		List<Entry<String, Integer>> principaisTecnologiasOrdernada = new ArrayList<Map.Entry<String, Integer>>();

		Empresa perfilEmpresa = utilService.perfilEmpresa();

		if (perfilEmpresa != null) {
			List<Vaga> vagas = vagaService.buscaVagasPeloIdEmpresa(perfilEmpresa);

			Set<String> principaisTecnologias = new HashSet<String>();

			for (Vaga vaga : vagas) {
				principaisTecnologias.addAll(vaga.listaPrincipaisTecnologias());
			}

			Map<String, Integer> quantidadePrincipaisTecnologias = new HashMap<String, Integer>();

			for (String tecnologia : principaisTecnologias) {
				List<Vaga> vagasPorPrincipaisTecnologias = vagaService
						.buscaVagasPelaEmpresaEPrincipaisTecnologias(perfilEmpresa, "%" + tecnologia + "%");
				quantidadePrincipaisTecnologias.put(tecnologia, vagasPorPrincipaisTecnologias.size());
			}

			principaisTecnologiasOrdernada = new ArrayList<>(quantidadePrincipaisTecnologias.entrySet());
			principaisTecnologiasOrdernada.sort(Entry.comparingByValue(Comparator.reverseOrder()));
		}

		pieModelEmpresa = new PieChartModel();

		if (!principaisTecnologiasOrdernada.isEmpty()) {

			for (int i = 0; i < principaisTecnologiasOrdernada.size(); i++) {
				Entry<String, Integer> base = principaisTecnologiasOrdernada.get(i);
				pieModelEmpresa.set(base.getKey(), base.getValue());
			}

		} else {

			pieModelEmpresa.set("Tecnologia 1", 0);
			pieModelEmpresa.set("Tecnologia 2", 0);
			pieModelEmpresa.set("Tecnologia 3", 0);
			pieModelEmpresa.set("Tecnologia 4", 0);
		}

		pieModelEmpresa.setTitle("Principais Tecnologias");
		pieModelEmpresa.setLegendPosition("e");
		pieModelEmpresa.setFill(false);
		pieModelEmpresa.setShowDataLabels(true);
		pieModelEmpresa.setDiameter(150);
		pieModelEmpresa.setShadow(false);

		pieModelEmpresa.setSeriesColors("9384df,33d8ff,84f560,558ac3");
	}

	public PieChartModel getPieModelEmpresa() {
		return pieModelEmpresa;
	}

	public void setPieModelEmpresa(PieChartModel pieModelEmpresa) {
		this.pieModelEmpresa = pieModelEmpresa;
	}

	public PieChartModel getPieModelCandidato() {
		return pieModelCandidato;
	}

	public void setPieModelCandidato(PieChartModel pieModelCandidato) {
		this.pieModelCandidato = pieModelCandidato;
	}
}