--liquibase formatted sql
--changeset avivannikov:1
CREATE TABLE IF NOT EXISTS ford(
	model VARCHAR(255) PRIMARY KEY,
	year_of_release INT,
	engine_power FLOAT
);

CREATE TABLE IF NOT EXISTS honda(
	model VARCHAR(255) PRIMARY KEY,
	year_of_release INT,
	engine_power FLOAT
);

CREATE TABLE IF NOT EXISTS bmw(
	model VARCHAR(255) PRIMARY KEY,
	year_of_release INT,
	engine_power FLOAT,
	is_there_a_turn_signal BOOLEAN
);

