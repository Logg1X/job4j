CREATE_USERS_TABLE=CREATE TABLE IF NOT EXISTS users(ID_USER serial PRIMARY KEY NOT NULL,NAME_USER VARCHAR(100),LOGIN_USER VARCHAR (100),EMAIL_USER VARCHAR (50),DATE_CREATE timestamp);
INSERT_USERS=INSERT INTO users(name_user,login_user,email_user,date_create)VALUES(?,?,?,?);
UPDATE_USERS=UPDATE users SET name_user= ?, login_user=?,email_user = ? where id_user = ?;
DELETE_USER_BY_ID=DELETE FROM users WHERE ID_USER = ?;
GET_ALL_USERS=SELECT * from users;
GET_USER_BY_ID=SELECT  * FROM users WHERE ID_USER = ?;


