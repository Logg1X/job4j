create table if not exists credentional(id_user integer primary key references users(id_user), login varchar(100), password varchar (100));
create table if not exists role(id serial primary key, name_role varchar(50));
ALTER TABLE users ADD COLUMN if not exists id_role integer references role(id);
ALTER TABLE users DROP COLUMN if exists login_user;