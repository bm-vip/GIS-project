ALTER USER postgres PASSWORD 'password';
CREATE SCHEMA IF NOT EXISTS evcsm_db;
grant all privileges on database evcsm_db to postgres;
CREATE EXTENSION IF NOT EXISTS postgis;