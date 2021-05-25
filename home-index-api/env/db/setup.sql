DROP TABLE IF EXISTS user_account;
DROP TABLE IF EXISTS property;
DROP TABLE IF EXISTS user_property;
DROP TABLE IF EXISTS space;
DROP TABLE IF EXISTS container;
DROP TABLE IF EXISTS item;

CREATE TABLE user_account (
	username varchar(25) NOT NULL UNIQUE,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	PRIMARY KEY (username)
);

CREATE TABLE property (
	id int NOT NULL AUTO_INCREMENT,
	nickname varchar(100) NOT NULL,
	address text,
	PRIMARY KEY (id)
);

CREATE TABLE user_property (
	username varchar(25) NOT NULL,
	property_id int NOT NULL,
	PRIMARY KEY (username, property_id),
	FOREIGN KEY (username) REFERENCES user_account(username)
);

CREATE TABLE space (
	id int NOT NULL AUTO_INCREMENT,
	nickname varchar(100) NOT NULL,
	description text,
	property_id int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (property_id) REFERENCES property(id)
);

CREATE TABLE container (
	id int NOT NULL AUTO_INCREMENT,
	nickname varchar(100) NOT NULL,
	description text,
	space_id int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (space_id) REFERENCES space(id)
);

CREATE TABLE item (
	id int NOT NULL AUTO_INCREMENT,
	nickname varchar(100) NOT NULL,
	description text,
	photo TEXT,
	quantity int NOT NULL,
	monetary_value decimal(15,2),
	container_id int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (container_id) REFERENCES container(id)
);