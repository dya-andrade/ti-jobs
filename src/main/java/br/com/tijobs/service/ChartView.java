package br.com.tijobs.service;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;



@Named
@ViewScoped
public class ChartView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel;


    @PostConstruct
    public void init() {
        createPieModel();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
          
        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);
         
        //pieModel.setTitle("Custom Pie");
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