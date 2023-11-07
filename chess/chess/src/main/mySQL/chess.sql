DROP DATABASE IF EXISTS ChessDatabase;

CREATE DATABASE ChessDatabase;

USE ChessDatabase;


CREATE TABLE  IF NOT EXISTS game (
    id INT NOT NULL,
    whiteUsername VARCHAR(255),
    blackUsername VARCHAR(255),
    gameName VARCHAR(255) NOT NULL,
    game VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE  IF NOT EXISTS users (
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE  IF NOT EXISTS authTokens (
    token VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (token)
);
