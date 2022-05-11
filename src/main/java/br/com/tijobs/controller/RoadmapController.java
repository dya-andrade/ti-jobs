package br.com.tijobs.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.mindmap.DefaultMindmapNode;

@Named
@ViewScoped
public class RoadmapController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultDiagramModel model;

	@PostConstruct
	public void init() {

		model = new DefaultDiagramModel();
		model.setMaxConnections(-1);
		model.setConnectionsDetachable(false);

		Element javaDeveloper = new Element("Java Developer in 2022", "35em", "2em");
		javaDeveloper.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		javaDeveloper.setStyleClass("titulo");

		model.addElement(javaDeveloper);

		// ------------------------- JAVA

		Element java = new Element("Java", "20em", "15em");
		java.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		java.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		model.addElement(java);

		model.connect(new Connection(javaDeveloper.getEndPoints().get(0), java.getEndPoints().get(0)));

		Element javaSub = new Element(
				"Java CLI. Variables, Constants, Types, Function, Packages, etc. Array & ArrayList. "
						+ "Pointers, Class, Methods. Interface. Thread, Rountine. Exception handling.",
				"5em", "25em");
		javaSub.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		javaSub.setStyleClass("sub");

		model.addElement(javaSub);

		model.connect(new Connection(java.getEndPoints().get(1), javaSub.getEndPoints().get(0)));

		// ----------------------------- GRADLE E MAVEN

		Element gradleMaven = new Element("Gradle/Maven, Library", "65em", "15em");
		gradleMaven.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		gradleMaven.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		model.addElement(gradleMaven);

		model.connect(new Connection(javaDeveloper.getEndPoints().get(0), gradleMaven.getEndPoints().get(0)));

		Element version = new Element("Dependency management tool. Version, Scripts, Repository and other Properties",
				"70em", "25em");
		version.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		version.setStyleClass("sub");

		model.addElement(version);

		model.connect(new Connection(gradleMaven.getEndPoints().get(1), version.getEndPoints().get(0)));

		// --------------------------

		Element sql = new Element("SQL Fundamentals", "36em", "18em");
		sql.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		sql.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		model.addElement(sql);

		model.connect(new Connection(javaDeveloper.getEndPoints().get(0), sql.getEndPoints().get(0)));

		Element basic = new Element("Basic SQL Syntax", "34em", "27em");
		basic.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		basic.setStyleClass("fontSub");

		model.addElement(basic);

		model.connect(new Connection(sql.getEndPoints().get(1), basic.getEndPoints().get(0)));

		// -------------------------- GENERAL

		Element skills = new Element("General Development Skills", "42em", "25em");
		skills.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		skills.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		skills.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		skills.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		skills.setStyleClass("titulo");

		model.addElement(skills);

		model.connect(new Connection(javaDeveloper.getEndPoints().get(0), skills.getEndPoints().get(0)));

		Element skillsSub1 = new Element(
				"GIT. HTTP/HTTPS. Data Structures and Algorithms. Scrum, Kanban or other project strategies", "22em",
				"35em");
		skillsSub1.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		skillsSub1.setStyleClass("sub");

		model.addElement(skillsSub1);

		model.connect(new Connection(skills.getEndPoints().get(1), skillsSub1.getEndPoints().get(0)));

		Element skillsSub2 = new Element("Basic Authentication, OAuth, JWT, etc. SOLID, YAGNI, KISS", "75em", "36em");
		skillsSub2.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		skillsSub2.setStyleClass("sub");

		model.addElement(skillsSub2);

		model.connect(new Connection(skills.getEndPoints().get(2), skillsSub2.getEndPoints().get(0)));

		// --------------------

		Element databases = new Element("Databases", "30em", "38em");
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		model.addElement(databases);

		model.connect(new Connection(skills.getEndPoints().get(3), databases.getEndPoints().get(0)));

		Element cli = new Element("CLI", "50em", "38em");
		cli.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		model.addElement(cli);

		model.connect(new Connection(skills.getEndPoints().get(3), cli.getEndPoints().get(0)));

		Element web = new Element("Web Frameworks + Routers", "30em", "45em");
		web.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		model.addElement(web);

		model.connect(new Connection(skills.getEndPoints().get(3), web.getEndPoints().get(0)));

		Element orm = new Element("ORMs", "50em", "45em");
		orm.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		model.addElement(orm);

		model.connect(new Connection(skills.getEndPoints().get(3), orm.getEndPoints().get(0)));

		Element log = new Element("Log Frameworks", "30em", "51em");
		log.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		model.addElement(log);

		model.connect(new Connection(skills.getEndPoints().get(3), log.getEndPoints().get(0)));

		Element caching = new Element("Caching", "50em", "51em");
		caching.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		model.addElement(caching);

		model.connect(new Connection(skills.getEndPoints().get(3), caching.getEndPoints().get(0)));

		Element api = new Element("API Clients", "30em", "57em");
		api.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		model.addElement(api);

		model.connect(new Connection(skills.getEndPoints().get(3), api.getEndPoints().get(0)));

		Element commun = new Element("Real Time Communication", "50em", "57em");
		commun.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		model.addElement(commun);

		model.connect(new Connection(skills.getEndPoints().get(3), commun.getEndPoints().get(0)));

		Element test = new Element("Testing", "30em", "63em");
		test.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		model.addElement(test);

		model.connect(new Connection(skills.getEndPoints().get(3), test.getEndPoints().get(0)));

		Element lib = new Element("Good to Know Libraries", "50em", "63em");
		lib.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		model.addElement(lib);

		model.connect(new Connection(skills.getEndPoints().get(3), lib.getEndPoints().get(0)));

		Element micro = new Element("MicroServices", "30em", "69em");
		micro.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		model.addElement(micro);

		model.connect(new Connection(skills.getEndPoints().get(3), micro.getEndPoints().get(0)));

		Element task = new Element("Task Scheduling", "50em", "69em");
		task.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		model.addElement(task);

		model.connect(new Connection(skills.getEndPoints().get(3), task.getEndPoints().get(0)));

		Element patterns = new Element("Java Patterns", "40em", "75em");
		patterns.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));

		model.addElement(patterns);

		model.connect(new Connection(skills.getEndPoints().get(3), patterns.getEndPoints().get(0)));

	}

	public DiagramModel getModel() {
		return model;
	}
}
