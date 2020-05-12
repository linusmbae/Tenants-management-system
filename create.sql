--SET MODE PostgreSQL;

CREATE DATABASE tenants_manager;
\c tenants_manager;

CREATE TABLE users
(
id serial PRIMARY KEY,
name VARCHAR,
email VARCHAR,
username VARCHAR,
password VARCHAR
);

CREATE TABLE tenants
(
id serial PRIMARY KEY,
name VARCHAR,
phone VARCHAR,
roomnumber int,
floor int,
apartmentid int
);

CREATE TABLE apartments
(
id serial PRIMARY KEY,
name VARCHAR,
location VARCHAR,
type VARCHAR,
numberofrooms int,
numberoffloors int
);

CREATE TABLE issues
(
id serial PRIMARY KEY,
type VARCHAR,
content VARCHAR,
apartmentid VARCHAR,
roomid VARCHAR
);

CREATE DATABASE tenants_manager_test WITH TEMPLATE tenants_manager;