CREATE TABLE kpp (
  id   serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE carcase (
  id   serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE motor (
  id   serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE color (
  id   serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE player (
  id   serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE auto_switch (
  id   serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE car (
  id             serial PRIMARY KEY,
  name           VARCHAR(50),
  id_kpp         int REFERENCES kpp (id)     NOT NULL,
  id_carcase     INT REFERENCES carcase (id) NOT NULL,
  id_motor       INT REFERENCES motor (id)   NOT NULL,
  id_color       INT REFERENCES color (id),
  id_player      INT REFERENCES player (id),
  id_auto_switch INT REFERENCES auto_switch (id)
);


INSERT INTO kpp (name)
VALUES ('mechanical'),
       ('auto');
INSERT INTO carcase (name)
VALUES ('koupe'),
       ('sedan'),
       ('crossover');
INSERT INTO motor (name)
VALUES ('V12'),
       ('V8');
INSERT INTO color (name)
VALUES ('BLACK'),
       ('BLUE'),
       ('GREEN'),
       ('RAD');
INSERT INTO player (name)
VALUES ('panasonik'),
       ('sony'),
       ('pioner');
INSERT INTO auto_switch (name)
VALUES ('button');
INSERT INTO auto_switch (name)
VALUES ('switcher');

INSERT INTO car (name, id_kpp, id_carcase, id_motor, id_color, id_player, id_auto_switch)
VALUES ('bmv', 2, 2, 1, 2, 2, 1),
       ('LADA', 1, 2, 2, 4, 3, 2);

INSERT INTO car (name, id_kpp, id_carcase, id_motor, id_color, id_player, id_auto_switch)
VALUES ('bmv222', 2, 2, 1, null, 2, null),
       ('LADA', 1, 2, 2, 4, null, null);

INSERT INTO car (name, id_kpp, id_carcase, id_motor, id_color, id_player)
VALUES ('volga', 1, 2, 1, 2, 3),
       ('VAZ', 1, 2, 2, 4, 3);

INSERT INTO car (name, id_kpp, id_carcase, id_motor)
VALUES ('volvo', 2, 2, 1),
       ('porshe', 1, 2, 2);

INSERT INTO auto_switch (name)
VALUES ('HZ');

--вывод неиспользуемых деталей
Select col.name as color, p.name as sound_player, sw.name as switcher, c2.name as carcase,k.name as kpp,m2.name as motor
from car c full
       join color col on c.id_color = col.id full
       join player p ON c.id_player = p.id full
       join auto_switch sw ON c.id_auto_switch = sw.id full
       join carcase c2 on c.id_carcase = c2.id full
       join kpp k on c.id_kpp = k.id full
       join motor m2 on c.id_motor = m2.id
where c.id is null;
-- объединение неиспользуемых деталей в множество
Select col.name as not_used
from car c full
       join color col on c.id_color = col.id
where c.id is null
UNION all
Select p.name
from car c full
       join player p ON c.id_player = p.id
where c.id is null
union all
Select sw.name
from car c full
       join auto_switch sw ON c.id_auto_switch = sw.id
where c.id is null
union all
select c2.name
from car c full
       join carcase c2 on c.id_carcase = c2.id
where c.id is null;


--структурированый вывод неисплользуемых деталей
select color, sound_player, switcher, carcase, kpp,motor
from (select c.id
      from car c ) x5  join
         (select col.name as color, row_number() over (order by col.name) as id
          from car c
                 right join color col on c.id_color = col.id
          where c.id is null) x1 on x5.id = x1.id
         full join
         (Select p.name as sound_player, row_number() over (order by p.name) as id
          from car c
                 right join player p ON c.id_player = p.id
          where c.id is null) x2 on x5.id = x2.id
         full join
         (select sw.name as switcher, row_number() over (order by sw.name) as id
          from car c
                 right join auto_switch sw on c.id_auto_switch = sw.id
          where c.id is null) x3 on x3.id = x5.id
         full join
         (select c1.name as carcase, row_number() over (order by c1.name) as id
          from car c
                 right join   carcase c1 on c.id_carcase = c1.id
          where c.id is null) x4 on x4.id = x5.id
         full join
         (select k.name as kpp, row_number() over (order by k.name) as id
          from car c
                 join   kpp k on c.id_carcase = k.id
          where c.id is null) x6 on x6.id = x5.id
         full join
         (select m2.name as motor, row_number() over (order by m2.name) as id
          from car c
                 right join   motor m2 on c.id_carcase = m2.id
          where c.id is null) x7 on x7.id = x5.id;

--по отдельность каждый:
--color--
select col.name as color
from car c
       right join color col on c.id_color = col.id
where c.id is null;
--sound_player--
Select p.name as sound_player
from car c
       right join player p ON c.id_player = p.id
where c.id is null;
--switcher--
select sw.name as switcher
from car c
       right join auto_switch sw on c.id_auto_switch = sw.id
where c.id is null;
--carcase--
select c1.name as carcase
from car c
       right join   carcase c1 on c.id_carcase = c1.id
where c.id is null;

--вывод всех машин и их деталей
select car.name, k.name as kpp, c2.name as corcase, m2.name as motor, c3.name as color, p.name as sound_player, a.name as switcher
from car
       left join kpp k on car.id_kpp = k.id left
       join carcase c2 on car.id_carcase = c2.id left
       join motor m2 on car.id_motor = m2.id left
       join color c3 on car.id_color = c3.id left
       join player p on car.id_player = p.id left
       join auto_switch a on car.id_auto_switch = a.id;
