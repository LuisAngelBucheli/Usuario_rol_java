Proyecto java web
Se usaron las siguientes tecnologías
PostgreSQL
Java
javascript
jQuery
css (bootstrap)
ajax


Este es el sql que se usó para genera la base de datos
CREATE DATABASE prueba;
CREATE TABLE rol (id_rol int PRIMARY KEY, nombre VARCHAR(50) NOT NULL);
CREATE TABLE usuario (id_usuario serial PRIMARY KEY, id_rol int NOT NULL, nombre VARCHAR(100) NOT NULL, activo CHAR NOT NULL, FOREIGN KEY (id_rol) REFERENCES rol (id_rol));
CREATE FUNCTION create_rol(id int, nombre VARCHAR(50)) RETURNS void AS $$
    BEGIN
        INSERT INTO rol VALUES (id, nombre);
    END;
$$ LANGUAGE plpgsql;
SELECT create_rol(1, 'ADMINISTRADOR');
SELECT create_rol(2, 'AUDITOR');
SELECT create_rol(3, 'AUXILIAR');
