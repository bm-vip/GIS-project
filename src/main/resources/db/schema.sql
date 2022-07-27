ALTER USER postgres PASSWORD 'postgres';
CREATE SCHEMA IF NOT EXISTS evcsm_db;
grant all privileges on database evcsm_db to postgres;