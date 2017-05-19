DROP SCHEMA if exists test CASCADE;
CREATE SCHEMA test;
CREATE TABLE test.helloworld
(
    ID bigint NOT NULL,
    Language varchar(255) NOT NULL,
    HelloWorld varchar(255) NOT NULL
);

CREATE SEQUENCE test.serial START WITH 1000;