package br.com.tijobs.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.model.Empresa;
import br.com.tijobs.model.Vaga;
import br.com.tijobs.util.UtilService;

@Named
@ViewScoped
public class ChartView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel;

	@Autowired
	private VagaService vagaService;

	@Autowired
	private UtilService utilService;

	@PostConstruct
	public void init() {
		createPieModel();
	}

	private void createPieModel() {
		
		List<Entry<String, Integer>> principaisTecnologiasOrdernada = null;

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
						.buscaVagasPelaEmpresaEPrincipaisTecnologias(perfilEmpresa, tecnologia);
				quantidadePrincipaisTecnologias.put(tecnologia, vagasPorPrincipaisTecnologias.size());
			}

			principaisTecnologiasOrdernada = new ArrayList<>(
					quantidadePrincipaisTecnologias.entrySet());
			principaisTecnologiasOrdernada.sort(Entry.comparingByValue(Comparator.reverseOrder()));
		}
		
		pieModel = new PieChartModel();


		if(principaisTecnologiasOrdernada != null && !principaisTecnologiasOrdernada.isEmpty()) {
			
			Entry<String, Integer> base = principaisTecnologiasOrdernada.get(0);
			pieModel.set(base.getKey(), base.getValue());
			
			base = principaisTecnologiasOrdernada.get(1);
			pieModel.set(base.getKey(), base.getValue());
			
			base = principaisTecnologiasOrdernada.get(2);
			pieModel.set(base.getKey(), base.getValue());
			
			base = principaisTecnologiasOrdernada.get(3);
			pieModel.set(base.getKey(), base.getValue());
			
		}else {
			
			pieModel.set("Tecnologia 1", 0);
			pieModel.set("Tecnologia 2", 0);
			pieModel.set("Tecnologia 3", 0);
			pieModel.set("Tecnologia 4", 0);
		}

		pieModel.setTitle("Principais Tecnologias");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
		pieModel.setShadow(false);

	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

}