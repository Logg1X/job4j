CREATE TABLE if not exists carcase (id   serial PRIMARY KEY,type_name VARCHAR(100)UNIQUE );

CREATE TABLE if not exists color (id   serial PRIMARY KEY,name VARCHAR(100) unique );

CREATE TABLE if not exists wd (id   serial PRIMARY KEY,type_name VARCHAR(100)UNIQUE );

CREATE TABLE if not exists brand (id   serial PRIMARY KEY,name VARCHAR(100) UNIQUE );

CREATE TABLE if not exists car (id serial PRIMARY KEY,name VARCHAR(50),id_brand INT REFERENCES brand (id) NOT NULL,id_carcase INT REFERENCES carcase (id) NOT NULL,id_color INT REFERENCES color (id),id_wd INT REFERENCES wd (id));

INSERT INTO brand(name) VALUES ('Nissan') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('Porsche') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('Audi') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('Hyundai') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('Ford') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('Volkswagen') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('Honda') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('BMW') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('Mercedes-Benz') ON CONFLICT DO NOTHING;
INSERT INTO brand(name) VALUES ('Toyota') ON CONFLICT DO NOTHING;
INSERT INTO carcase(type_name) VALUES ('Sedan') ON CONFLICT DO NOTHING;
INSERT INTO carcase(type_name) VALUES ('Coupe') ON CONFLICT DO NOTHING;
INSERT INTO carcase(type_name) VALUES ('Crossover') ON CONFLICT DO NOTHING;
INSERT INTO carcase(type_name) VALUES ('Hatchback') ON CONFLICT DO NOTHING;
INSERT INTO color(name) VALUES ('Белый') ON CONFLICT DO NOTHING;
INSERT INTO color(name) VALUES ('Черный') ON CONFLICT DO NOTHING;
INSERT INTO color(name) VALUES ('Синий') ON CONFLICT DO NOTHING;
INSERT INTO color(name) VALUES ('Красный') ON CONFLICT DO NOTHING;
INSERT INTO color(name) VALUES ('Зеленый') ON CONFLICT DO NOTHING;
INSERT INTO color(name) VALUES ('Коричневый') ON CONFLICT DO NOTHING;
INSERT INTO wd(type_name) VALUES ('Rear-wheel drive') ON CONFLICT DO NOTHING;
INSERT INTO wd(type_name) VALUES ('front-wheel drive') ON CONFLICT DO NOTHING;