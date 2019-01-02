-- CREATE_TABLE_VACANCY_SQL_RU=CREATE TABLE IF NOT EXISTS vacancy_sql_ru(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,title text,author text,date_create text ,link text)
-- CREATE_TABLE_VACANCY_HH_RU=CREATE TABLE IF NOT EXISTS vacancy_hh(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,title text,author text,date_create text ,link text);
-- Создание схемы через модуль liquibase
INSERT_VACANSY_IN_TABLE_SQL_RU=INSERT  INTO vacancy_sql_ru(title,author,date_create,link) VALUES (?,?,?,?);
INSERT_VACANSY_IN_TABLE_HH_RU=INSERT  INTO vacancy_hh(title,author,date_create,link) VALUES (?,?,?,?);
GET_VACANCYES_FOR_THE_DAY=SELECT * FROM vacancy_sql_ru WHERE date_create like ?;
GET_ALL_VACANCYES=SELECT * from vacancy_sql_ru;
TRUNCATE_TABLE=DELETE FROM vacancy_sql_ru;
DELETE_BY_ID=DELETE FROM vacancy_sql_ru WHERE id = ?;
-- CREATE_TABLE_UPDATE_DATE=CREATE TABLE  IF NOT EXISTS update_date(date_last_update text);
UPDATE_DATE_UPDATE=UPDATE update_date SET date_last_update = ?;
INSERT_DATE_UPDATE=INSERT INTO update_date values(?);
GET_LAST_UPDATE=SELECT * FROM update_date;
