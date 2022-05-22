CREATE TABLE ers_users(
--The ID is SERIAL type to increment with every new row added
--The id is denoted as the primary key 
id SERIAL PRIMARY KEY,
--Username,password, and roles are VARCHAR type wtih a max of 250 characters to store them as strings
--Username, must be unique because we will query for single results of a given username
--None of these columns will be null when a new entry is created
username VARCHAR (250) UNIQUE NOT NULL,
password VARCHAR(250) NOT NULL,
role VARCHAR(250) NOT NULL
);
INSERT INTO ers_users (id,username,PASSWORD,role)
VALUES(1,'genericEmployee1','genericPassword1','Employee'),
(2,'genericEmployee2','genericPassword2','Employee'),
(3,'genericEmployee3','genericPassword3','Employee'),
(4,'genericManager1','genericPassword1','Manager'),
(5,'genericManager2','genericPassword2','Manager'),
(6,'genericManager3','genericPassword3','Manager');
--This will create a table for the reimbursement data 
CREATE TABLE ers_reimbursements(
id SERIAL PRIMARY KEY,
author INT NOT NULL,
resolver INT NOT NULL,
description VARCHAR(250) NOT NULL,
type VARCHAR(250) NOT NULL,
status VARCHAR (250) NOT NULL,
amount FLOAT NOT NULL
--CONSTRAINT fk_author
--	FOREIGN KEY (author)
--		REFERENCES ers_users(id),
--CONSTRAINT fk_resolver
--	FOREIGN KEY (resolver)
--		REFERENCES ers_users(id)
);
INSERT INTO ers_reimbursements (id, author, resolver, description, type, status, amount)
VALUES(1, 1, 1, 'Oracle Java Certification', 'Other', 'Pending',250.00),
(2, 2, 1, 'Travel to Reston HQ', 'Travel', 'Pending',600.00),
(3, 1, 3, 'Free Lunch offer from Sean', 'Food', 'Approved',25.00),
(4, 2, 4, 'Two night hotel stay near Orlando office for visit.', 'Lodging', 'Approved',300.00),
(5, 1, 3, 'Rental car to drive from Reston to Orlando.', 'Travel', 'Denied',2500.00);
--Remove the tables and all contents if they exists in the current schems
--WARNING this is only meant to intitalize the database structure, do not use otherwize
--It WILL delete all fo your data 
-- We use CASCADE to ensure that all references get deleted as well.
DROP TABLE IF EXISTS ers_users CASCADE;
DROP TABLE iF EXISTS ers_reimbursements CASCADE;
--Creating the necessary enum types for data storage 
create type role AS enum ('Employee','Manager');
create type type as enum('Lodging','Travel','Food','Other');
create type status as enum('Pending','Approved','Denied');
--This is meant to create two default users in the ers_users table 
--THe only way to create a Manager, currently, is to put it directly in the database.
--You will use the manager credentials to test your manager's functionality and reimbursement processing.
INSERT INTO ers_users(username,password,role)
VALUES('default','guest','Employee'),('admin','admin','Manager');

	