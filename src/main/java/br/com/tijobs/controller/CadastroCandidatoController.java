package br.com.tijobs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Named
@ViewScoped
public class CadastroCandidatoController {
	
	private List<String> distritos;
	
	private int numero;
	
	private List<String> habilidades;

	@PostConstruct
	public void init() {
		buscaDistritosSP();
		
		habilidades = new ArrayList<String>();
		habilidades.add("PHP");
		habilidades.add("PHP - Zend");
		habilidades.add("PHP - Laravel");
		habilidades.add("PHP - CodeIgniter");
		habilidades.add("PHP - Symfony");
		habilidades.add("PHP - CakePHP");
		habilidades.add("Ajax");
		habilidades.add("Android");
		habilidades.add("Android Studio");
		habilidades.add("AngularJS");
		habilidades.add("Bootstrap");
		habilidades.add("Vue.js");
		habilidades.add("Express.js");
		habilidades.add("JQuery");
		habilidades.add("React.js");
		habilidades.add("API");
		habilidades.add("Arduino");
		habilidades.add("APS.NET");
		habilidades.add("APS.NET Core");
		habilidades.add("APS.NET MVC");
		habilidades.add("Assembly");
		habilidades.add("AWS EC2");
		habilidades.add("AWS RDS");
		habilidades.add("AWS S3");
		habilidades.add("Big Data");
		habilidades.add("Blockchain");
		habilidades.add("C");
		habilidades.add("C#");
		habilidades.add("C++");
		habilidades.add("Clean Code");
		habilidades.add("Clojure");
		habilidades.add("COBOL");
		habilidades.add("Cordova");
		habilidades.add("CSS");
		habilidades.add("Data Science");
		habilidades.add("Delphi");
		habilidades.add("Design Pattern");
		habilidades.add("DevOps");
		habilidades.add("Django");
		habilidades.add("Docker");
		habilidades.add("ES6");
		habilidades.add("Figma");
		habilidades.add("Firebase");
		habilidades.add("Flask");
		habilidades.add("Flutter");
		habilidades.add("Flutter");
		habilidades.add("Git");
		habilidades.add("Go");
		habilidades.add("Google Analytics");
		habilidades.add("Google Cloud");
		habilidades.add("GraphQL");
		habilidades.add("HTML");
		habilidades.add("HTTP");
		habilidades.add("Ionic");
		habilidades.add("iOS");
		habilidades.add("Ireport");
		habilidades.add("Jasper");
		habilidades.add("Java");
		habilidades.add("JavaEE");
		habilidades.add("JavaScript");
		habilidades.add("Java Server Pages");
		habilidades.add("Jenkins");
		habilidades.add("JSF");
		habilidades.add("JSON");
		habilidades.add("JUnit");
		habilidades.add("Kotlin");
		habilidades.add("Linux");
		habilidades.add("Lua");
		habilidades.add("Lua");
		habilidades.add("Machine Learning");
		habilidades.add("Magento");
		habilidades.add("MariaDB");
		habilidades.add("Maven");
		habilidades.add("MySQL");
		habilidades.add(".NET");
		habilidades.add(".NET Core");
		habilidades.add(".NET MVC");
		habilidades.add("Node.js");
		habilidades.add("NoSQL");
		habilidades.add("Oracle");
		habilidades.add("Pandas");
		habilidades.add("Pascal");
		habilidades.add("Photoshop");
		habilidades.add("PostgreSQL");
		habilidades.add("Python");
		habilidades.add("R");
		habilidades.add("React Native");
		habilidades.add("Redux");
		habilidades.add("RESTful");
		habilidades.add("RabbitMQ");
		habilidades.add("Ruby");
		habilidades.add("Ruby on Rails");
		habilidades.add("Salesforce");
		habilidades.add("SAP");
		habilidades.add("SAS");
		habilidades.add("Sass");
		habilidades.add("SCRUM");
		habilidades.add("SCSS");
		habilidades.add("Selenium");
		habilidades.add("SEO");
		habilidades.add("S.O.L.I.D");
		habilidades.add("Spring Boot");
		habilidades.add("Spring MVC");
		habilidades.add("Spring Cloud");
		habilidades.add("SQL");
		habilidades.add("Swift");
		habilidades.add("TDD");
		habilidades.add("TypeScript");
		habilidades.add("UML");
		habilidades.add("Unity");
		habilidades.add("VBA");
		habilidades.add("VB.NET");
		habilidades.add("Web Services");
		habilidades.add("WordPress");
		habilidades.add("Xamarin");
		habilidades.add("XML");
	}

	public void buscaDistritosSP() {

		String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/SP/distritos";
		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		JSONArray array = new JSONArray(response.getBody());

		distritos = new ArrayList<>();

		distritos.add("São Paulo - Central");

		for (Object objeto : array) {
			JSONObject json = (JSONObject) objeto;
			distritos.add(json.getString("nome"));
		}
	}

	public List<String> getDistritos() {
		return distritos;
	}

	public List<String> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
