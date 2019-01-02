CREATE TABLE IF NOT EXISTS vacancy_sql_ru(id serial PRIMARY KEY NOT NULL ,title text,author text,date_create text ,link text);
CREATE TABLE IF NOT EXISTS vacancy_hh(id serial PRIMARY KEY NOT NULL ,title text,author text,date_create text ,link text);
CREATE TABLE  IF NOT EXISTS update_date(date_last_update text);