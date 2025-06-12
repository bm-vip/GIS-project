ALTER USER postgres PASSWORD 'password';
CREATE SCHEMA IF NOT EXISTS evcsm_db;
grant all privileges on database evcsm_db to postgres;
CREATE EXTENSION IF NOT EXISTS postgis;
--select version();
--docker exec -it your-postgres-container bash
--apt-get update && apt-get install -y postgresql-17-postgis-3