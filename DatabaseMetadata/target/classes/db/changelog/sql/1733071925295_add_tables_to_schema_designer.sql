--liquibase formatted sql
--changeset avivannikov:2
CREATE TABLE IF NOT EXISTS designer.ford(
	first_name VARCHAR(255),
    second_name VARCHAR(255),
    year_of_birth INT
);

CREATE TABLE IF NOT EXISTS designer.bmw(
	first_name VARCHAR(255),
	second_name VARCHAR(255),
	year_of_birth INT
);
