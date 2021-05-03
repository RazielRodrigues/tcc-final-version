-- CRIANDO BANCO DE DADOS E TABELA 
-- (BASEADO NA TABELA DO VOLTDB MESMAS COLUNAS E MESMO TIPO DE DADOS)
CREATE DATABASE benchmark_mysql;
USE benchmark_mysql;

CREATE TABLE app_session(
    appid INTEGER primary key auto_increment,
    deviceid BIGINT NOT NULL,
    ts timestamp
);

-- CRIANDO INDICES DA TABELA
CREATE INDEX app_session_idx ON app_session (deviceid);
-- CRIANDO UMA VIEW DA TABELA
CREATE VIEW app_usage AS
SELECT appid, deviceid, count(*) as ct
FROM app_session
GROUP BY appid, deviceid;
delimiter //
-- PROCEDURE DE INSERT, A IDEIA É CHAMAR ESSSA PROCEDURE
-- 500 MIL VEZES PELA APLICAÇÃO JAVA
-- ASSIM PODEMOS MENSURAR O TEMPO DE EXECUÇÃO DO MYSQL/VOLTDB
CREATE PROCEDURE insert_app()
BEGIN
    INSERT INTO app_session (appid, deviceid, ts) 
    VALUES (DEFAULT, FLOOR(RAND() * 401) + 99999999,  now());
    COMMIT;
END//