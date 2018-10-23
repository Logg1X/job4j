CREATE_TABLE_TRACKER=CREATE TABLE IF NOT EXISTS tracker(
            id serial PRIMARY KEY,
            name VARCHAR(200),
            description TEXT,
            create_date TIMESTAMP WITHOUT TIME ZONE,
            update_date TIMESTAMP WITHOUT TIME ZONE
            );
CREATE_TABLE_COMMENTS=CREATE TABLE IF NOT EXISTS comments(
            id serial PRIMARY KEY,
            message TEXT,
            create_date TIMESTAMP WITHOUT TIME ZONE,
            id_item INTEGER REFERENCES tracker(id))

ADD_ITEM =INSERT INTO tracker(
          name,
          description,
          create_date,
          update_date
          )
          VALUES (?, ?, ?, ?)


ADD_COMMENT=INSERT INTO comments(message, create_date, id_item) VALUES (?,?,?)
FIND_BY_ID=SELECT * FROM tracker WHERE id=?
DELETE_ITEM=DELETE FROM tracker WHERE id = ?
DELETE_COMMENT =DELETE FROM comments WHERE id = ?
DELETE_ALL_COMENTS_WITH_ITEM_ID= DELETE FROM comments WHERE id_item = ?;
GET_ALL_ITEMS = SELECT * FROM tracker
GET_ALL_COMMENTS =SELECT * FROM comments WHERE id_item = ?
FIND_BY_NAME =SELECT * FROM tracker WHERE tracker.name LIKE ?
EDIT_ITEM =UPDATE tracker SET name = ? , description = ?,update_date = ? where id = ?