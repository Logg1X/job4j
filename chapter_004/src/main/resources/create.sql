Create database tracker;

CREATE TABLE role(
ID serial PRIMARY KEY,
NAME VARCHAR(50)
);

CREATE TABLE users(
ID serial PRIMARY KEY,
NAME VARCHAR(50) NOT NULL,
ID_ROLE INT NOT NULL REFERENCES ROLE(ID)
);

CREATE TABLE rules(
ID serial PRIMARY KEY,
NAME VARCHAR(50) NOT NULL
);

CREATE TABLE role_rules(
ID serial PRIMARY KEY,
ID_ROLE INT REFERENCES role(ID),
ID_RULES INT REFERENCES rules(ID)
);


CREATE TABLE category(
ID serial PRIMARY KEY,
Name  VARCHAR(200) NOT NULL
);

CREATE TABLE state(
ID serial PRIMARY KEY,
Name  VARCHAR(200) NOT NULL
);



CREATE TABLE item(
ID serial PRIMARY KEY,
ID_AUTHOR INT REFERENCES users(id),
TITLE VARCHAR(2000) NOT NULL,
DESCRIPTION TEXT,
ID_STATE INT REFERENCES state(id),
ID_CATEGORY INT REFERENCES category(id)
);

CREATE TABLE comments(
ID serial PRIMARY KEY,
ID_USER INT REFERENCES users(ID),
ID_ITEM int REFERENCES item(id),
DESCRIPTION TEXT
);

CREATE TABLE attachs(
ID serial PRIMARY KEY,
Name  VARCHAR(200) NOT NULL,
ID_ITEM INT REFERENCES item(ID)
);

INSERT INTO role(NAME) VALUES('ADMIN'),('USER');
INSERT INTO rules(NAME) VALUES('READ'),('WRITE');
INSERT INTO role_rules(id_role,id_rules) VALUES(1,1),(1,2),(2,1);

INSERT INTO users(NAME,ID_ROLE) VALUES('PAVEL TOPOROV',1),
('YULIYA LUCHNIKOVA',2),('ALEXANDR KRETOV',1),('DMITRIY EREMEEV',2),
('SERGEY STEPKIN',2),('ANTON LISITCIN',2);

INSERT INTO category(NAME) VALUES('LOW'),('MIDLE'),('HIGH'),('BLOCKER');
INSERT INTO state(NAME) VALUES('QUEUE'),('IN_PROGRES'),('CLOSE'),('REOPEN_QUEUE');

INSERT INTO item(TITLE,ID_AUTHOR,DESCRIPTION,ID_STATE,ID_CATEGORY)
VALUES ('Доступ к DB',2,'Прошу предоставить доступ к базе',1,3);

INSERT INTO item(TITLE,ID_AUTHOR,DESCRIPTION,ID_STATE,ID_CATEGORY)
VALUES ('Доступ к папке на сетьевом диске',2,'Прошу предоставить доступ к папке на диске Х',1,2);

INSERT INTO item(TITLE,ID_AUTHOR,ID_STATE,ID_CATEGORY)
VALUES ('Срочно нужен доступ к JIRA',6,1,4);

INSERT INTO comments(id_USER,ID_ITEM, description)
VALUES(6,3,'Коллеги, сориентируйте по срокам пожалуста.'),
(1,1,'Прошу указать обоснование'),
(1,1,'К каким таблицам нужен доступ?'),
(3,2,'укажите точный путь к папке');

--UPDATE users  u SET id_role = 2 where id = 6;
--SELECT u.name, r.name  FROM users u JOIN role r ON u.id_role = r.id;
--SELECT r.name, ru.name FROM role_rules k Join role r ON k.id_role = r.id JOIN rules ru ON k.id_rules = ru.id;

/*SELECT u.name, r.name, ru.name
FROM role_rules k
JOIN role r ON k.id_role = r.id
JOIN users u ON u.id_role = r.id
JOIN rules ru ON ru.id = k.id_rules
WHERE u.name like '%LUCH%'; */

--SELECT * FROM role;
--SELECT * FROM rules;
--SELECT * FROM users;
--SELECT * FROM role_rules;
--SELECT * FROM category;
--SELECT * FROM item;
--SELECT * FROM state;
--SELECT * FROM comments;
