-- 1
CREATE USER map PASSWORD 'map';
-- 2
CREATE DATABASE map OWNER map;
-- 3
CREATE EXTENSION pgcrypto;