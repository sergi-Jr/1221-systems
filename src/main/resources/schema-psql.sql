DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id UUID NOT NULL,
   name VARCHAR NOT NULL,
   email VARCHAR NOT NULL,
   age INTEGER NOT NULL,
   weight FLOAT NOT NULL,
   height FLOAT NOT NULL,
   goal VARCHAR NOT NULL,
   daily_rate INTEGER NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);

INSERT INTO users (id, name, email, age, weight, height, goal, daily_rate) VALUES (RANDOM_UUID(), 'test1', 'test1@mail.ru', 10, 35.5, 140, 'MAINTENANCE', 100);
INSERT INTO users (id, name, email, age, weight, height, goal, daily_rate) VALUES (RANDOM_UUID(), 'test2', 'test2@mail.ru', 30, 100, 180, 'GAIN', 100);
INSERT INTO users (id, name, email, age, weight, height, goal, daily_rate) VALUES (RANDOM_UUID(), 'test3', 'test3@mail.ru', 28, 80, 190, 'SLIMMING', 100);