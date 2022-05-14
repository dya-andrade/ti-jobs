package br.com.tijobs.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tijobs.service.RoadmapService;

@Named
@ViewScoped
public class RoadmapController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultDiagramModel model;
	
	@Autowired
	private RoadmapService service;
	
	private String roadmapSelecionado;

	@PostConstruct
	public void init() {
		if(model == null) {
			model = service.createRoadmapJava();
		}
	}
	
	public void geraRoadmap() {
		if(roadmapSelecionado != null) {
			if(roadmapSelecionado.equals("Java")) {
				model = service.createRoadmapJava();
			}else {
				model = service.createRoadmapNET();
			}
		}
	}

	public DiagramModel getModel() {
		return model;
	}

	public String getRoadmapSelecionado() {
		return roadmapSelecionado;
	}

	public void setRoadmapSelecionado(String roadmapSelecionado) {
		this.roadmapSelecionado = roadmapSelecionado;
	}
}
