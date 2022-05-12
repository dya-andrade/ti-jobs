package br.com.tijobs.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

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

		Element javaDeveloper = new Element("Java Developer in 2022", "37em", "2em");
		javaDeveloper.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		javaDeveloper.setStyleClass("titulo java");

		model.addElement(javaDeveloper);

		// ------------------------- JAVA

		Element java = new Element("Java", "20em", "15em");
		java.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		java.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		java.setStyleClass("subJava");

		model.addElement(java);

		model.connect(new Connection(javaDeveloper.getEndPoints().get(0), java.getEndPoints().get(0)));

		subElement(
				"Java CLI. Variables, Constants, Types, Function, Packages, etc. Array & ArrayList. "
						+ "Pointers, Class, Methods. Interface. Thread, Rountine. Exception handling.",
				"5em", "25em", EndPointAnchor.TOP, java, 1, 0);

		// ----------------------------- GRADLE E MAVEN

		Element gradleMaven = new Element("Gradle/Maven, Library", "65em", "15em");
		gradleMaven.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		gradleMaven.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		gradleMaven.setStyleClass("subJava");

		model.addElement(gradleMaven);

		model.connect(new Connection(javaDeveloper.getEndPoints().get(0), gradleMaven.getEndPoints().get(0)));

		subElement("Dependency management tool. Version, Scripts, Repository and other Properties", "70em", "25em",
				EndPointAnchor.TOP, gradleMaven, 1, 0);

		// --------------------------

		Element sql = new Element("SQL Fundamentals", "36em", "18em");
		sql.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		sql.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		sql.setStyleClass("subJava");

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

		skills.setStyleClass("titulo subJava");

		model.addElement(skills);

		model.connect(new Connection(javaDeveloper.getEndPoints().get(0), skills.getEndPoints().get(0)));

		subElement("GIT. HTTP/HTTPS. Data Structures and Algorithms. Scrum, Kanban or other project strategies", "22em",
				"35em", EndPointAnchor.RIGHT, skills, 1, 0);

		subElement("Basic Authentication, OAuth, JWT, etc. SOLID, YAGNI, KISS", "75em", "36em", EndPointAnchor.LEFT, skills, 2, 0);

		// -------------------- SKILLS

		Element databases = new Element("Databases", "30em", "38em");
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		databases.setStyleClass("subSkills");

		model.addElement(databases);

		model.connect(new Connection(skills.getEndPoints().get(3), databases.getEndPoints().get(0)));

		subElement("Relational: SQL Server, MySQL, MariaDB e PostgreSQL*", "5em", "42em", EndPointAnchor.RIGHT,
				databases, 2, 0);
		subElement("Cloud Databases: Azure CosmosDB* e Amazon DynamoDB.", "5em", "46em", EndPointAnchor.RIGHT,
				databases, 1, 0);
		subElement("NoSQL: MongoDB*, Redis*, LiteDB, Apache Cassandra, RavenDB e CouchDB.", "5em", "50em",
				EndPointAnchor.RIGHT, databases, 1, 0);
		subElement("Search Engines: ElasticSearch*, Soir e Sphinx.", "5em", "56em", EndPointAnchor.RIGHT, databases, 3,
				0);

		// ------------------

		Element cli = new Element("CLI", "50em", "38em");
		cli.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		cli.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		cli.setStyleClass("subSkills");

		model.addElement(cli);

		model.connect(new Connection(skills.getEndPoints().get(3), cli.getEndPoints().get(0)));

		subElement("JCommander* e airline.", "75em", "42em", EndPointAnchor.LEFT, cli, 1, 0);

		// -----------------

		Element web = new Element("Web Frameworks + Routers", "30em", "46em");
		web.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		web.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		web.setStyleClass("subSkills");

		model.addElement(web);

		model.connect(new Connection(skills.getEndPoints().get(3), web.getEndPoints().get(0)));

		subElement("Spring*, Play Framework, Spark, Jersey e nanohttpd.", "5em", "60em", EndPointAnchor.RIGHT, web, 1,
				0);

		// -----------------

		Element orm = new Element("ORMs", "50em", "45em");
		orm.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		orm.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		orm.setStyleClass("subSkills");

		model.addElement(orm);

		model.connect(new Connection(skills.getEndPoints().get(3), orm.getEndPoints().get(0)));

		subElement("Hibernate* e Ebean", "75em", "50em", EndPointAnchor.LEFT, orm, 1, 0);

		// -----------------

		Element log = new Element("Log Frameworks", "50em", "51em");
		log.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		log.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		log.setStyleClass("subSkills");

		model.addElement(log);

		model.connect(new Connection(skills.getEndPoints().get(3), log.getEndPoints().get(0)));

		subElement("log4j*, Zap e TinyLog", "77em", "58em", EndPointAnchor.LEFT, log, 1, 0);

		subElement("Log Management System: ELK Stack*, Sentry.io e Loggly.com", "77em", "63em", EndPointAnchor.LEFT,
				log, 1, 0);

		// -----------------

		Element caching = new Element("Caching", "30em", "51em");
		caching.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		caching.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		caching.setStyleClass("subSkills");

		model.addElement(caching);

		model.connect(new Connection(skills.getEndPoints().get(3), caching.getEndPoints().get(0)));

		subElement("Caffeine*, Distributed Cache*, Java-Redis* e Java-Memcached.", "5em", "63em", EndPointAnchor.RIGHT,
				caching, 1, 0);

		// -------------

		Element api = new Element("API Clients", "30em", "57em");
		api.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		api.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		api.setStyleClass("subSkills");

		model.addElement(api);

		model.connect(new Connection(skills.getEndPoints().get(3), api.getEndPoints().get(0)));

		subElement("REST*, okhttp*, GraphQL e retrofit.", "5em", "70em", EndPointAnchor.RIGHT, api, 1, 0);

		// -------------

		Element commun = new Element("Real Time Communication", "50em", "58em");
		commun.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		commun.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		commun.setStyleClass("subSkills");

		model.addElement(commun);

		model.connect(new Connection(skills.getEndPoints().get(3), commun.getEndPoints().get(0)));

		subElement("atmosphere* e webbit*.", "77em", "69em", EndPointAnchor.LEFT, commun, 1, 0);

		// -------------

		Element test = new Element("Testing", "30em", "63em");
		test.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		test.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		test.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		test.setStyleClass("subSkills");

		model.addElement(test);

		model.connect(new Connection(skills.getEndPoints().get(3), test.getEndPoints().get(0)));

		subElement("Behavior Testing: cucumber-jvm*, cukes e JBehave.", "5em", "75em", EndPointAnchor.RIGHT, test, 1,
				0);

		subElement("Integration Testing: rest-assured* e MockServer.", "5em", "78em", EndPointAnchor.RIGHT, test, 2, 0);

		subElement("E2E Testing: Selenium.", "5em", "81em", EndPointAnchor.RIGHT, test, 2, 0);

		// ------------

		Element lib = new Element("Good to Know Libraries", "50em", "63em");
		lib.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		lib.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		lib.setStyleClass("subSkills");

		model.addElement(lib);

		model.connect(new Connection(skills.getEndPoints().get(3), lib.getEndPoints().get(0)));

		subElement("beanvalidation, bouncycastle, gson, shiro, RxJava.", "77em", "73em", EndPointAnchor.LEFT, lib, 1,
				0);

		// ------------

		Element task = new Element("Task Scheduling", "50em", "69em");
		task.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		task.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		task.setStyleClass("subSkills");

		model.addElement(task);

		model.connect(new Connection(skills.getEndPoints().get(3), task.getEndPoints().get(0)));

		subElement("cron-utils* e Aurora.", "77em", "79em", EndPointAnchor.LEFT, task, 1, 0);

		// ------------

		Element patterns = new Element("Java Patterns", "50em", "75em");
		patterns.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		patterns.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		patterns.setStyleClass("subSkills");

		model.addElement(patterns);

		model.connect(new Connection(skills.getEndPoints().get(3), patterns.getEndPoints().get(0)));

		subElement("Creational, Structrul, Behavioral, synchronization, Concurrency, Messaging e Stability.", "77em",
				"85em", EndPointAnchor.LEFT, patterns, 1, 0);

		// ------------

		Element micro = new Element("MicroServices", "30em", "69em");
		micro.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		micro.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		micro.setStyleClass("subSkills");

		model.addElement(micro);

		model.connect(new Connection(skills.getEndPoints().get(3), micro.getEndPoints().get(0)));

		subElement("Message-Broker: RabbitMQ*, Apache Kafka, ActuveMQ e Azure Service Bus.", "10em", "87em",
				EndPointAnchor.RIGHT, micro, 1, 0);

		subElement("RPC: Protocol Buffers*, gRPC-Java* e thrift.", "15em", "95em", EndPointAnchor.TOP, micro, 1, 0);

		subElement("Frameworks: Apollo e micronaut.", "25em", "100em", EndPointAnchor.TOP, micro, 1, 0);

		subElement("Message-Bus: mbassador.", "42em", "95em", EndPointAnchor.TOP, micro, 1, 0);

	}

	private void subElement(String texto, String left, String top, EndPointAnchor endPointAnchor, Element paiElement,
			int ligacao1, int ligacao2) {
		Element subElement = new Element(texto, left, top);
		subElement.addEndPoint(new DotEndPoint(endPointAnchor));
		subElement.setStyleClass("sub");

		model.addElement(subElement);

		model.connect(new Connection(paiElement.getEndPoints().get(ligacao1), subElement.getEndPoints().get(ligacao2)));
	}

	public DiagramModel getModel() {
		return model;
	}
}
