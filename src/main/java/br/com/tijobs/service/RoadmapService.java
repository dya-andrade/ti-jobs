package br.com.tijobs.service;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.springframework.stereotype.Service;

@Service
public class RoadmapService {

	private DefaultDiagramModel subElement(DefaultDiagramModel model, String texto, String left, String top,
			EndPointAnchor endPointAnchor, Element paiElement, int ligacao1, int ligacao2) {
		Element subElement = new Element(texto, left, top);
		subElement.addEndPoint(new DotEndPoint(endPointAnchor));
		subElement.setStyleClass("sub");

		model.addElement(subElement);

		model.connect(new Connection(paiElement.getEndPoints().get(ligacao1), subElement.getEndPoints().get(ligacao2)));

		return model;
	}

	public DefaultDiagramModel createRoadmapNET() {

		DefaultDiagramModel model = new DefaultDiagramModel();
		model.setMaxConnections(-1);
		model.setConnectionsDetachable(false);

		Element NETDeveloper = new Element("ASP .NET Core Developer in 2022", "37em", "2em");
		NETDeveloper.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		NETDeveloper.setStyleClass("titulo net tituloNet");

		model.addElement(NETDeveloper);

		// ------------ PRÉ REQUISITOS

		Element net = new Element("Pré Requisitos", "20em", "15em");
		net.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		net.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		net.setStyleClass("subNet");

		model.addElement(net);

		model.connect(new Connection(NETDeveloper.getEndPoints().get(0), net.getEndPoints().get(0)));

		model = subElement(model,
				"GIT, HTTP/HTTPS protocol, Learn to search for solution, Learn dotnet CLI, Data Structures and Algorithms.",
				"5em", "25em", EndPointAnchor.TOP, net, 1, 0);

		// -------- C#

		Element cSharp = new Element("C#", "60em", "15em");
		cSharp.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		cSharp.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		cSharp.setStyleClass("subNet");

		model.addElement(cSharp);

		model.connect(new Connection(NETDeveloper.getEndPoints().get(0), cSharp.getEndPoints().get(0)));

		model = subElement(model, "Learn the basics of C# 10 e Learn .NET 6", "68em", "25em", EndPointAnchor.TOP,
				cSharp, 1, 0);

		// ------------- SQL

		Element sql = new Element("SQL Fundamentals", "36em", "18em");
		sql.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		sql.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		sql.setStyleClass("subNet");

		model.addElement(sql);

		model.connect(new Connection(NETDeveloper.getEndPoints().get(0), sql.getEndPoints().get(0)));

		model = subElement(model,
				"Fundamentals about databse design and SQL Syntax. Stored Procedures, Constraints e Triggers.", "35em",
				"29em", EndPointAnchor.TOP, sql, 1, 0);

		// -------------------------- GENERAL

		Element skills = new Element("General Development Skills", "42em", "30em");
		skills.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		skills.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		skills.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		skills.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		skills.setStyleClass("titulo subNet");

		model.addElement(skills);

		model.connect(new Connection(NETDeveloper.getEndPoints().get(0), skills.getEndPoints().get(0)));

		model = subElement(model, "SOLID: SRP, OCP, LSP, ISP e DIP.", "22em", "35em", EndPointAnchor.RIGHT, skills, 1,
				0);

		model = subElement(model,
				"ASP.NET Core Basics: MVC, REST, Razor Pages, Razor, Components, Middlewares, Filters & Attributes, Application Settings & Configuration e Authentication & Authorization. ",
				"75em", "36em", EndPointAnchor.LEFT, skills, 2, 0);

		// -------------------- SKILLS

		// ----- INJECTION

		Element injection = new Element("Dependency Injection", "30em", "38em");
		injection.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		injection.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		injection.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));

		injection.setStyleClass("subSkills");

		model.addElement(injection);

		model.connect(new Connection(skills.getEndPoints().get(3), injection.getEndPoints().get(0)));

		model = subElement(model,
				"DI Containers*: Microsoft.Extensions.DependencyInjection*, AutoFac, Ninject, Castle Windsor e Simple Injector. ",
				"5em", "40em", EndPointAnchor.RIGHT, injection, 2, 0);
		model = subElement(model, "Life Cycles*: Scoped*, Transient* e Singleton.", "5em", "47em", EndPointAnchor.RIGHT,
				injection, 1, 0);
		model = subElement(model, "Scrutor", "5em", "50em", EndPointAnchor.RIGHT, injection, 1, 0);

		// ----- ORM

		Element orm = new Element("ORM", "50em", "38em");
		orm.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		orm.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		orm.setStyleClass("subSkills");

		model.addElement(orm);

		model.connect(new Connection(skills.getEndPoints().get(3), orm.getEndPoints().get(0)));

		model = subElement(model, "Entity Framework Core*, Dapper, RepoDB e NHibernate.", "77em", "46em",
				EndPointAnchor.LEFT, orm, 1, 0);

		// ----- DATABASE

		Element databases = new Element("Databases", "30em", "48em");
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		databases.setStyleClass("subSkills");

		model.addElement(databases);

		model.connect(new Connection(skills.getEndPoints().get(3), databases.getEndPoints().get(0)));

		model = subElement(model, "Relational: SQL Server*, MySQL, MariaDB e PostgreSQL", "5em", "53em",
				EndPointAnchor.RIGHT, databases, 2, 0);
		model = subElement(model, "Cloud Databases: Azure CosmosDB* e Amazon DynamoDB.", "5em", "57em",
				EndPointAnchor.RIGHT, databases, 1, 0);
		model = subElement(model, "NoSQL: MongoDB*, Redis*, LiteDB, Apache Cassandra, RavenDB e CouchDB.", "5em",
				"60em", EndPointAnchor.RIGHT, databases, 1, 0);
		model = subElement(model, "Search Engines: ElasticSearch*, Soir e Sphinx.", "5em", "66em", EndPointAnchor.RIGHT,
				databases, 3, 0);

		// ---------- CACHING

		Element caching = new Element("Caching", "30em", "55em");
		caching.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		caching.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		caching.setStyleClass("subSkills");

		model.addElement(caching);

		model.connect(new Connection(skills.getEndPoints().get(3), caching.getEndPoints().get(0)));

		model = subElement(model, "Memory Cache*, Distributed Cache* e Entity Framework 2nd Level Cache.", "5em",
				"70em", EndPointAnchor.RIGHT, caching, 1, 0);

		// ------- LOG

		Element log = new Element("Log Frameworks", "50em", "47em");
		log.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		log.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		log.setStyleClass("subSkills");

		model.addElement(log);

		model.connect(new Connection(skills.getEndPoints().get(3), log.getEndPoints().get(0)));

		model = subElement(model, "Serilog* e NLog", "77em", "53em", EndPointAnchor.LEFT, log, 1, 0);

		model = subElement(model, "Log Management System: ELK Stack*, Sentry.io, Loggly.com e elmah.io.", "77em",
				"58em", EndPointAnchor.LEFT, log, 1, 0);

		// ---- REAL TIME

		Element commun = new Element("Real Time Communication", "50em", "53em");
		commun.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		commun.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		commun.setStyleClass("subSkills");

		model.addElement(commun);

		model.connect(new Connection(skills.getEndPoints().get(3), commun.getEndPoints().get(0)));

		model = subElement(model, "SignalR Core e Web Sockets.", "77em", "65em", EndPointAnchor.LEFT, commun, 1, 0);

		// ------- OBJECT

		Element object = new Element("Object Mapping", "50em", "58em");
		object.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		object.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		object.setStyleClass("subSkills");

		model.addElement(object);

		model.connect(new Connection(skills.getEndPoints().get(3), object.getEndPoints().get(0)));

		model = subElement(model, "AutoMapper*, Mapster, ExpressMapper e ArgileMapper.", "77em", "70em",
				EndPointAnchor.LEFT, object, 1, 0);

		// ------- TASK

		Element task = new Element("Task Scheduling", "50em", "63em");
		task.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		task.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		task.setStyleClass("subSkills");

		model.addElement(task);

		model.connect(new Connection(skills.getEndPoints().get(3), task.getEndPoints().get(0)));

		model = subElement(model, "Native BackgroundService*, HangFire*, Quartz e Coravel.", "77em", "74em",
				EndPointAnchor.LEFT, task, 1, 0);

		// ------- Continuous

		Element continuous = new Element("Continuous Integration & Delivery.", "50em", "68em");
		continuous.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		continuous.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		continuous.setStyleClass("subSkills tituloNet");

		model.addElement(continuous);

		model.connect(new Connection(skills.getEndPoints().get(3), continuous.getEndPoints().get(0)));

		model = subElement(model, "Github Actions*, Axure Pipelines e Travis CI.", "77em", "78em", EndPointAnchor.LEFT,
				continuous, 1, 0);
		
		// ------- Good

		Element good = new Element("Good to Know Libraries", "50em", "74em");
		good.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		good.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		good.setStyleClass("subSkills");

		model.addElement(good);

		model.connect(new Connection(skills.getEndPoints().get(3), good.getEndPoints().get(0)));

		model = subElement(model, "MediatR*, FluentValidation*, Polly*, Benchmark.NET, NodaTime, GenFu e Swashbuckle. ", "77em", "83em", EndPointAnchor.LEFT,
				good, 1, 0);
		
		// ------- DESIGN

		Element design = new Element("Design Patterns", "50em", "79em");
		design.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		design.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		design.setStyleClass("subSkills");

		model.addElement(design);

		model.connect(new Connection(skills.getEndPoints().get(3), design.getEndPoints().get(0)));

		model = subElement(model, "CQRS*, Decorator*, Strategy, Builder, Singleton e Facade.", "77em", "93em", EndPointAnchor.LEFT,
				design, 1, 0);

		// ----- API

		Element api = new Element("API Clients", "30em", "60em");
		api.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		api.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		api.setStyleClass("subSkills");

		model.addElement(api);

		model.connect(new Connection(skills.getEndPoints().get(3), api.getEndPoints().get(0)));

		model = subElement(model, "REST*, gRPC* e GraphQL.", "5em", "77em", EndPointAnchor.RIGHT, api, 1, 0);

		// TESTE

		Element test = new Element("Testing", "30em", "66em");
		test.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		test.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		test.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		test.setStyleClass("subSkills");

		model.addElement(test);

		model.connect(new Connection(skills.getEndPoints().get(3), test.getEndPoints().get(0)));

		model = subElement(model, "Unit Testing*: Frameworks, Mocking e Assertion.", "5em", "81em",
				EndPointAnchor.RIGHT, test, 1, 0);

		model = subElement(model, "Integration Testing: WebApplicationFactory* e TestServer.", "5em", "84em",
				EndPointAnchor.RIGHT, test, 2, 0);

		model = subElement(model, "Behavior Testing: SpecFlow*, BDDfy e LightBDD.", "5em", "87em", EndPointAnchor.RIGHT,
				test, 2, 0);

		// ----- CLIENT

		Element client = new Element("Client-Side libraries", "30em", "75em");
		client.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		client.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		client.setStyleClass("subSkills");

		model.addElement(client);

		model.connect(new Connection(skills.getEndPoints().get(3), client.getEndPoints().get(0)));

		model = subElement(model, "Blazor*", "5em", "91em", EndPointAnchor.RIGHT, client, 1, 0);

		// ----- Engines

		Element engines = new Element("Template Engines", "30em", "80em");
		engines.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		engines.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		engines.setStyleClass("subSkills");

		model.addElement(engines);

		model.connect(new Connection(skills.getEndPoints().get(3), engines.getEndPoints().get(0)));

		model = subElement(model, "Razor*", "5em", "94em", EndPointAnchor.RIGHT, engines, 1, 0);		

		// ------------

		Element micro = new Element("MicroServices", "40em", "86em");
		micro.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		micro.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		micro.setStyleClass("subSkills");

		model.addElement(micro);

		model.connect(new Connection(skills.getEndPoints().get(3), micro.getEndPoints().get(0)));

		model = subElement(model, "Message-Broker: RabbitMQ*, Apache Kafka, ActiveMQ, Azure Service Bus e NetMQ.", "20em",
				"106em", EndPointAnchor.RIGHT, micro, 1, 0);

		model = subElement(model, "API Gateway: Ocelot*.", "30em", "115em", EndPointAnchor.TOP,
				micro, 1, 0);

		model = subElement(model, "Containerization: Docker*. Orchestration: Kubernetes*.", "58em", "115em", EndPointAnchor.TOP, micro, 1, 0);

		model = subElement(model, "Message-Bus: MassTransit*, NServiceBus e EasyNetQ.", "62em", "108em", EndPointAnchor.TOP, micro, 1, 0);
		
		return model;
	}

	public DefaultDiagramModel createRoadmapJava() {

		DefaultDiagramModel model = new DefaultDiagramModel();
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

		model = subElement(model,
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

		model = subElement(model, "Dependency management tool. Version, Scripts, Repository and other Properties",
				"70em", "25em", EndPointAnchor.TOP, gradleMaven, 1, 0);

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

		model = subElement(model,
				"GIT. HTTP/HTTPS. Data Structures and Algorithms. Scrum, Kanban or other project strategies", "22em",
				"35em", EndPointAnchor.RIGHT, skills, 1, 0);

		model = subElement(model, "Basic Authentication, OAuth, JWT, etc. SOLID, YAGNI, KISS", "75em", "36em",
				EndPointAnchor.LEFT, skills, 2, 0);

		// -------------------- SKILLS

		Element databases = new Element("Databases", "30em", "38em");
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
		databases.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		databases.setStyleClass("subSkills");

		model.addElement(databases);

		model.connect(new Connection(skills.getEndPoints().get(3), databases.getEndPoints().get(0)));

		model = subElement(model, "Relational: SQL Server, MySQL, MariaDB e PostgreSQL*", "5em", "42em",
				EndPointAnchor.RIGHT, databases, 2, 0);
		model = subElement(model, "Cloud Databases: Azure CosmosDB* e Amazon DynamoDB.", "5em", "46em",
				EndPointAnchor.RIGHT, databases, 1, 0);
		model = subElement(model, "NoSQL: MongoDB*, Redis*, LiteDB, Apache Cassandra, RavenDB e CouchDB.", "5em",
				"50em", EndPointAnchor.RIGHT, databases, 1, 0);
		model = subElement(model, "Search Engines: ElasticSearch*, Soir e Sphinx.", "5em", "56em", EndPointAnchor.RIGHT,
				databases, 3, 0);

		// ------------------

		Element cli = new Element("CLI", "50em", "38em");
		cli.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		cli.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		cli.setStyleClass("subSkills");

		model.addElement(cli);

		model.connect(new Connection(skills.getEndPoints().get(3), cli.getEndPoints().get(0)));

		model = subElement(model, "JCommander* e airline.", "75em", "42em", EndPointAnchor.LEFT, cli, 1, 0);

		// -----------------

		Element web = new Element("Web Frameworks + Routers", "30em", "46em");
		web.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		web.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		web.setStyleClass("subSkills");

		model.addElement(web);

		model.connect(new Connection(skills.getEndPoints().get(3), web.getEndPoints().get(0)));

		model = subElement(model, "Spring*, Play Framework, Spark, Jersey e nanohttpd.", "5em", "60em",
				EndPointAnchor.RIGHT, web, 1, 0);

		// -----------------

		Element orm = new Element("ORMs", "50em", "45em");
		orm.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		orm.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		orm.setStyleClass("subSkills");

		model.addElement(orm);

		model.connect(new Connection(skills.getEndPoints().get(3), orm.getEndPoints().get(0)));

		model = subElement(model, "Hibernate* e Ebean", "75em", "50em", EndPointAnchor.LEFT, orm, 1, 0);

		// -----------------

		Element log = new Element("Log Frameworks", "50em", "51em");
		log.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		log.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		log.setStyleClass("subSkills");

		model.addElement(log);

		model.connect(new Connection(skills.getEndPoints().get(3), log.getEndPoints().get(0)));

		model = subElement(model, "log4j*, Zap e TinyLog", "77em", "58em", EndPointAnchor.LEFT, log, 1, 0);

		model = subElement(model, "Log Management System: ELK Stack*, Sentry.io e Loggly.com", "77em", "63em",
				EndPointAnchor.LEFT, log, 1, 0);

		// -----------------

		Element caching = new Element("Caching", "30em", "51em");
		caching.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		caching.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		caching.setStyleClass("subSkills");

		model.addElement(caching);

		model.connect(new Connection(skills.getEndPoints().get(3), caching.getEndPoints().get(0)));

		model = subElement(model, "Caffeine*, Distributed Cache*, Java-Redis* e Java-Memcached.", "5em", "63em",
				EndPointAnchor.RIGHT, caching, 1, 0);

		// -------------

		Element api = new Element("API Clients", "30em", "57em");
		api.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		api.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));

		api.setStyleClass("subSkills");

		model.addElement(api);

		model.connect(new Connection(skills.getEndPoints().get(3), api.getEndPoints().get(0)));

		model = subElement(model, "REST*, okhttp*, GraphQL e retrofit.", "5em", "70em", EndPointAnchor.RIGHT, api, 1,
				0);

		// -------------

		Element commun = new Element("Real Time Communication", "50em", "58em");
		commun.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		commun.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		commun.setStyleClass("subSkills");

		model.addElement(commun);

		model.connect(new Connection(skills.getEndPoints().get(3), commun.getEndPoints().get(0)));

		model = subElement(model, "atmosphere* e webbit*.", "77em", "69em", EndPointAnchor.LEFT, commun, 1, 0);

		// -------------

		Element test = new Element("Testing", "30em", "63em");
		test.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		test.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		test.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		test.setStyleClass("subSkills");

		model.addElement(test);

		model.connect(new Connection(skills.getEndPoints().get(3), test.getEndPoints().get(0)));

		model = subElement(model, "Behavior Testing: cucumber-jvm*, cukes e JBehave.", "5em", "75em",
				EndPointAnchor.RIGHT, test, 1, 0);

		model = subElement(model, "Integration Testing: rest-assured* e MockServer.", "5em", "78em",
				EndPointAnchor.RIGHT, test, 2, 0);

		model = subElement(model, "E2E Testing: Selenium.", "5em", "81em", EndPointAnchor.RIGHT, test, 2, 0);

		// ------------

		Element lib = new Element("Good to Know Libraries", "50em", "63em");
		lib.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		lib.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		lib.setStyleClass("subSkills");

		model.addElement(lib);

		model.connect(new Connection(skills.getEndPoints().get(3), lib.getEndPoints().get(0)));

		model = subElement(model, "beanvalidation, bouncycastle, gson, shiro, RxJava.", "77em", "73em",
				EndPointAnchor.LEFT, lib, 1, 0);

		// ------------

		Element task = new Element("Task Scheduling", "50em", "69em");
		task.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		task.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		task.setStyleClass("subSkills");

		model.addElement(task);

		model.connect(new Connection(skills.getEndPoints().get(3), task.getEndPoints().get(0)));

		model = subElement(model, "cron-utils* e Aurora.", "77em", "79em", EndPointAnchor.LEFT, task, 1, 0);

		// ------------

		Element patterns = new Element("Java Patterns", "50em", "75em");
		patterns.addEndPoint(new DotEndPoint(EndPointAnchor.LEFT));
		patterns.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));

		patterns.setStyleClass("subSkills");

		model.addElement(patterns);

		model.connect(new Connection(skills.getEndPoints().get(3), patterns.getEndPoints().get(0)));

		model = subElement(model,
				"Creational, Structrul, Behavioral, synchronization, Concurrency, Messaging e Stability.", "77em",
				"85em", EndPointAnchor.LEFT, patterns, 1, 0);

		// ------------

		Element micro = new Element("MicroServices", "30em", "69em");
		micro.addEndPoint(new DotEndPoint(EndPointAnchor.RIGHT));
		micro.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));

		micro.setStyleClass("subSkills");

		model.addElement(micro);

		model.connect(new Connection(skills.getEndPoints().get(3), micro.getEndPoints().get(0)));

		model = subElement(model, "Message-Broker: RabbitMQ*, Apache Kafka, ActiveMQ e Azure Service Bus.", "10em",
				"87em", EndPointAnchor.RIGHT, micro, 1, 0);

		model = subElement(model, "RPC: Protocol Buffers*, gRPC-Java* e thrift.", "15em", "95em", EndPointAnchor.TOP,
				micro, 1, 0);

		model = subElement(model, "Frameworks: Apollo e micronaut.", "25em", "100em", EndPointAnchor.TOP, micro, 1, 0);

		model = subElement(model, "Message-Bus: mbassador.", "42em", "95em", EndPointAnchor.TOP, micro, 1, 0);

		// --------------

		return model;
	}

}
