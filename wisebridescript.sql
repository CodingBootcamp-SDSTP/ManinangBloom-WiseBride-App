CREATE DATABASE IF NOT EXISTS wisebridedb;

USE wisebridedb;
DROP TABLE IF EXISTS officers;
CREATE TABLE officers (
	id VARCHAR(64) NOT NULL PRIMARY KEY, 
	firstname VARCHAR(64) NOT NULL, 
	lastname VARCHAR(64) NOT NULL, 
	contactnumber VARCHAR(64) NOT NULL,
	department VARCHAR(64) NOT NULL
	);

INSERT INTO officers (id, firstname, lastname, contactnumber, department) VALUES ('2018-001', 'Levijoy', 'Gatchalian', '0945-1130953', 'Marketing');
INSERT INTO officers (id, firstname, lastname, contactnumber, department) VALUES ('2018-002', 'Ayelle', 'Rejante', '0916-4325140', 'Marketing');
INSERT INTO officers (id, firstname, lastname, contactnumber, department) VALUES ('2018-003', 'Meryll', 'Calejesan', '0927-7142833', 'Marketing');
INSERT INTO officers (id, firstname, lastname, contactnumber, department) VALUES ('2018-004', 'Karen Anne', 'Delos Reyes', '0936-4714412', 'Marketing');
INSERT INTO officers (id, firstname, lastname, contactnumber, department) VALUES ('2018-005', 'Claudette', 'Agras', '0936-7448521', 'Marketing');
INSERT INTO officers (id, firstname, lastname, contactnumber, department) VALUES ('2018-006', 'Ichey', 'Balasbas', '0915-8256935', 'Marketing');

DROP TABLE IF EXISTS locations;
CREATE TABLE locations (
	id INT NOT NULL PRIMARY KEY, 
	name VARCHAR(64) NOT NULL, 
	city VARCHAR(64) NOT NULL, 
	country VARCHAR(64) NOT NULL, 
	address VARCHAR(128) NOT NULL
	);

INSERT INTO locations (id, name, city, country, address) VALUES (1229, 'First Midland', 'Makati City', 'Philippines', 'First Midland Building, Gamboa Street, Legazpi Village');
INSERT INTO locations (id, name, city, country, address) VALUES (1550, 'Megamall', 'Mandaluyong City', 'Philippines', '5th Floor, Mega Atrium, SM Megamall');
INSERT INTO locations (id, name, city, country, address) VALUES (1552, 'Shaw Boulevard', 'Pasig City', 'Philippines', '604 Shaw Boulevard');
INSERT INTO locations (id, name, city, country, address) VALUES (1503, 'Wilson Square Bldg', 'San Juan City', 'Philippines', '2nd fl. Wilson Square Bldg. 199 Wilson Street');
INSERT INTO locations (id, name, city, country, address) VALUES (1128, 'Sanville Subdivision', 'Quezon City', 'Philippines', 'Sanville Subdivision, Cenacle Drive');
INSERT INTO locations (id, name, city, country, address) VALUES (1605, 'Evangelista', 'Pasig City', 'Philippines', '257 Evangelista Avenue');

DROP TABLE IF EXISTS suppliers;
CREATE TABLE IF NOT EXISTS suppliers (
	id INT NOT NULL AUTO_INCREMENT, 
	name VARCHAR(64) NOT NULL, 
	location INT, 
	assignedto VARCHAR(64), 
	type VARCHAR(64) NOT NULL, 
	PRIMARY KEY (id), 
	FOREIGN KEY(location) REFERENCES locations(id), 
	FOREIGN KEY(assignedto) REFERENCES officers(id)
	);

INSERT INTO suppliers (name, location, assignedto, type) VALUES ('Bizu Catering Studio', (SELECT id FROM locations WHERE id=1229), (SELECT id FROM officers WHERE id='2018-001'), 'caterer');
INSERT INTO suppliers (name, location, assignedto, type) VALUES ('Josiahs Catering', (SELECT id FROM locations WHERE id=1550), (SELECT id FROM officers WHERE id='2018-002'), 'caterer');
INSERT INTO suppliers (name, location, assignedto, type) VALUES ('Nice Print Photography', (SELECT id FROM locations WHERE id=1552), (SELECT id FROM officers WHERE id='2018-003'), 'photographyservices');
INSERT INTO suppliers (name, location, assignedto, type) VALUES ('Pat Dy Photography', (SELECT id FROM locations WHERE id=1503), (SELECT id FROM officers WHERE id='2018-004'), 'photographyservices');
INSERT INTO suppliers (name, location, assignedto, type) VALUES ('Fernwood Gardens', (SELECT id FROM locations WHERE id=1128), (SELECT id FROM officers WHERE id='2018-005'), 'weddingvenue');
INSERT INTO suppliers (name, location, assignedto, type) VALUES ('Glass Garden', (SELECT id FROM locations WHERE id=1605), (SELECT id FROM officers WHERE id='2018-006'), 'weddingvenue');