CREATE DATABASE IF NOT EXISTS wisebridedb;

DROP TABLE IF EXISTS suppliers;

USE wisebridedb;
DROP TABLE IF EXISTS contactpersons;
CREATE TABLE contactpersons (
	id VARCHAR(64) NOT NULL PRIMARY KEY, 
	firstname VARCHAR(64) NOT NULL, 
	lastname VARCHAR(64) NOT NULL, 
	contactnumber INT NOT NULL,
	department VARCHAR(64) NOT NULL
	);

INSERT INTO contactpersons (id, firstname, lastname, contactnumber, department) VALUES ('2018-001', 'Levijoy', 'Santiago', 9451130953, 'Marketing');
INSERT INTO contactpersons (id, firstname, lastname, contactnumber, department) VALUES ('2018-002', 'Ayelle', 'Santiago', 9164325140, 'Marketing');
INSERT INTO contactpersons (id, firstname, lastname, contactnumber, department) VALUES ('2018-003', 'Meryll', 'Santiago', 9277142833, 'Marketing');
INSERT INTO contactpersons (id, firstname, lastname, contactnumber, department) VALUES ('2018-004', 'Karen', 'Santiago', 9364714412, 'Marketing');
INSERT INTO contactpersons (id, firstname, lastname, contactnumber, department) VALUES ('2018-005', 'Claud', 'Santiago', 9367448521, 'Marketing');
INSERT INTO contactpersons (id, firstname, lastname, contactnumber, department) VALUES ('2018-006', 'Ichey', 'Santiago', 9158256935, 'Marketing');

DROP TABLE IF EXISTS locations;
CREATE TABLE locations (
	id INT NOT NULL PRIMARY KEY, 
	name VARCHAR(64) NOT NULL, 
	city VARCHAR(64) NOT NULL, 
	country VARCHAR(64) NOT NULL, 
	address VARCHAR(64) NOT NULL
	);

INSERT INTO locations (id, name, city, country, address) VALUES (1229, 'First Midland', 'Makati', 'Philippines', 'First Midland Building Gamboa, Legazpi Village');
INSERT INTO locations (id, name, city, country, address) VALUES (1550, 'Megamall', 'Mandaluyong', 'Philippines', '5th Floor Mega Atrium SM Megamall');
INSERT INTO locations (id, name, city, country, address) VALUES (1552, 'Shaw Boulevard', 'Pasig', 'Philippines', '604 Shaw Boulevard');
INSERT INTO locations (id, name, city, country, address) VALUES (1503, 'Wilson Square Bldg', 'San Juan', 'Philippines', '2nd fl. Wilson Square Bldg. 199 Wilson Street, San Juan cor P.Guevarra Street');
INSERT INTO locations (id, name, city, country, address) VALUES (1128, 'Sanville Subdivision', 'Quezon City', 'Philippines', 'Sanville Subdivision Cenacle Drive');
INSERT INTO locations (id, name, city, country, address) VALUES (1605, 'Evangelista', 'Pasig', 'Philippines', '257 Evangelista Avenue');

DROP TABLE IF EXISTS suppliers;
CREATE TABLE IF NOT EXISTS suppliers (
	id INT NOT NULL AUTO_INCREMENT, 
	name VARCHAR(64) NOT NULL, 
	price INT NOT NULL, 
	location INT, 
	assignedto VARCHAR(64), 
	type VARCHAR(64) NOT NULL, 
	PRIMARY KEY (id), 
	FOREIGN KEY(location) REFERENCES locations(id), 
	FOREIGN KEY(assignedto) REFERENCES contactpersons(id)
	);

INSERT INTO suppliers (name, price, location, assignedto, type) VALUES ('Bizu Catering Studio', 250000, (SELECT id FROM locations WHERE id=1229), (SELECT id FROM contactpersons WHERE id='2018-001'), 'caterer');
INSERT INTO suppliers (name, price, location, assignedto, type) VALUES ('Josiahs Catering', 200000, (SELECT id FROM locations WHERE id=1550), (SELECT id FROM contactpersons WHERE id='2018-002'), 'caterer');
INSERT INTO suppliers (name, price, location, assignedto, type) VALUES ('Nice Print Photography', 95000, (SELECT id FROM locations WHERE id=1552), (SELECT id FROM contactpersons WHERE id='2018-003'), 'photographyservices');
INSERT INTO suppliers (name, price, location, assignedto, type) VALUES ('Pat Dy Photography', 85000, (SELECT id FROM locations WHERE id=1503), (SELECT id FROM contactpersons WHERE id='2018-004'), 'photographyservices');
INSERT INTO suppliers (name, price, location, assignedto, type) VALUES ('Fernwood Gardens', 120000, (SELECT id FROM locations WHERE id=1128), (SELECT id FROM contactpersons WHERE id='2018-005'), 'weddingvenue');
INSERT INTO suppliers (name, price, location, assignedto, type) VALUES ('Glass Garden', 90000, (SELECT id FROM locations WHERE id=1605), (SELECT id FROM contactpersons WHERE id='2018-006'), 'weddingvenue');