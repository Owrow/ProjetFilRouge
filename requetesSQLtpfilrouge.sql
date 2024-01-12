DROP TABLE IF EXISTS plats_commandes, plats_cartes,  factures,commandes, reservations,tables, equipes, clients, restaurants, cartes,  roles,plats, categories;


 
CREATE TABLE roles (
 
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
 
	id          INT       NOT NULL PRIMARY KEY IDENTITY,
 
	libelle   CHAR(6)   NOT NULL,
 
);
 
CREATE TABLE clients (
 
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
 
	id					INT		           NOT NULL
 
	                                       PRIMARY KEY IDENTITY,
	id_role				INT NOT NULL,
 
	nom					VARCHAR(20)			NOT NULL,
 
	prenom				VARCHAR(20)			NOT NULL,
 
	mail			    VARCHAR(30)			NOT NULL,
 
	telephone           VARCHAR(12)         NOT NULL,
 
	mdp                 VARCHAR(30)         NOT NULL,
 
);
CREATE TABLE equipes (
 
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
 
	id            INT       NOT NULL  PRIMARY KEY IDENTITY,
	id_role		 INT       NOT NULL,	
 
	identifiant   VARCHAR(255)   NOT NULL,
 
	mdp           VARCHAR(255)   NOT NULL,
);



CREATE TABLE categories (
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id INT PRIMARY KEY IDENTITY,
	nom VARCHAR(255)
); 
CREATE TABLE plats (
createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id INT PRIMARY KEY IDENTITY	,
	id_categorie INT,
	nom VARCHAR(50),
	description_plat VARCHAR(100),
	prix NUMERIC(5,2)
	);

	
CREATE TABLE cartes (
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id INT PRIMARY KEY IDENTITY	,
	nom varchar(30) ,

);

CREATE TABLE plats_cartes (
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id INT PRIMARY KEY IDENTITY	,
	id_carte INT,
	id_plat INT,
	);


CREATE TABLE tables (
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id					INT					NOT NULL
											PRIMARY KEY
											IDENTITY,
	id_restaurant		INT					NOT NULL,
	numero				INT					NOT NULL,
	nombre_places		INT					NULL,
	
);
CREATE TABLE reservations (
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id					INT					NOT NULL
											PRIMARY KEY
											IDENTITY,
	id_client			INT					NOT NULL,
	id_restaurant		INT					NOT NULL,
	id_table			INT					NOT NULL,
	date_reservation	DATE				NOT NULL,
	heure_reservation	DATE				NOT NULL,
	etat				CHAR(10)			NOT NULL
											UNIQUE,
); 

CREATE TABLE commandes (
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id					INT					NOT NULL
											PRIMARY KEY
											IDENTITY,
	id_client			INT					NOT NULL,
	id_table			INT					NOT NULL,
	
	etat				CHAR(10)			NOT NULL
											UNIQUE
);

CREATE TABLE plats_commandes (
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id INT PRIMARY KEY IDENTITY	,
	id_commande  INT,
	id_plat INT,
	);


CREATE TABLE factures (
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
	id INT PRIMARY KEY IDENTITY,
	id_commande INT 	,
	
	etat CHAR(2) NOT NULL,
);

 
CREATE TABLE restaurants (
 
	createdAt          DATETIME DEFAULT CURRENT_TIMESTAMP,     
 
	updatedAt          DATETIME DEFAULT CURRENT_TIMESTAMP,
 
	deletedAT          DATETIME NULL,
 
	id           INT         NOT NULL  PRIMARY KEY IDENTITY,
	id_carte	INT      NULL,
	nom          VARCHAR(30) NOT NULL,
 
	adresse      VARCHAR(80) NOT NULL,
 
	ouverture    DATETIME    NOT NULL,
 
	fermeture    DATETIME    NOT NULL,

);

ALTER TABLE clients
WITH CHECK ADD
FOREIGN KEY (id_role) REFERENCES roles(id);
 

 
ALTER TABLE factures
WITH CHECK ADD
FOREIGN KEY (id_commande) REFERENCES commandes(id); 

 
ALTER TABLE tables
WITH CHECK ADD
FOREIGN KEY (id_restaurant) REFERENCES restaurants(id);
 
ALTER TABLE commandes
WITH CHECK ADD
FOREIGN KEY (id_client) REFERENCES clients(id),
FOREIGN KEY (id_table) REFERENCES tables(id);



ALTER TABLE equipes
WITH CHECK ADD
FOREIGN KEY (id_role) REFERENCES roles(id);

ALTER TABLE restaurants
WITH CHECK ADD
FOREIGN KEY (id_carte) REFERENCES cartes(id);


ALTER TABLE reservations
WITH CHECK ADD
FOREIGN KEY (id_client) REFERENCES clients(id),
FOREIGN KEY (id_restaurant) REFERENCES restaurants(id),
FOREIGN KEY (id_table) REFERENCES tables(id);

ALTER TABLE plats
WITH CHECK ADD
FOREIGN KEY (id_categorie) REFERENCES categories(id);

ALTER TABLE plats_cartes
WITH CHECK ADD
FOREIGN KEY (id_carte) REFERENCES cartes(id),
FOREIGN KEY (id_plat) REFERENCES plats(id);

ALTER TABLE plats_commandes
WITH CHECK ADD
FOREIGN KEY (id_commande) REFERENCES commandes(id),
FOREIGN KEY (id_plat) REFERENCES plats(id);
