create table rule(id_rule serial primary key, grant_rule varchar(50), access_path varchar(100));
create table role_rule(id_role integer references role(id), id_rule integer references rule(id_rule));
